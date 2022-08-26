package views;

import controllers.RecipePool;
import controllers.RecipeWeek;
import models.Recipe;
import utils.RecipeHelper;
import views.base.IUser;
import views.base.Person;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class User extends Person implements IUser {

    private Scanner inputScanner = new Scanner(System.in);
    private RecipePool recipePool = new RecipePool();
    private RecipeWeek recipeWeek = new RecipeWeek();
    private List<models.RecipeWeek> recipeWeeksDataList = new ArrayList<>();
    private String day;

    @Override
    public void viewRecipeWeek() {
        recipeWeeksDataList.clear();
        recipeWeeksDataList.addAll(recipeWeek.getRecipeWeeks());
        displayRecipeWeeks ();
    }

    @Override
    public void generateRecipeWeek() {
        if (recipeWeek.createRecipeWeek())
            System.out.println("Recipe Week Generated Successfully!");
        else
            System.out.println("There is no recipe or, Error there!");
        displayUserMenu();
    }

    @Override
    public void viewRecipe() {
        System.out.println("Enter Title of Recipe");
        inputScanner.nextLine();
        System.out.print("Title : ");
        String title = inputScanner.nextLine();
        RecipeHelper.displayRecipe (recipePool.getRecipeByTitle(title));
        displayUserMenu();
    }

    @Override
    public void viewRecipePool() {
        Iterator<Recipe> recipeIterator = recipePool.getIterator();
        if (recipeIterator != null){
            System.out.println("List of Recipes");
            while (recipeIterator.hasNext()){
                RecipeHelper.displayRecipe(recipeIterator.next());
            }
        }
        else
            System.out.println("No Recipe Found!");
        displayUserMenu();
    }

    public void displayUserMenu() {
        try{
            System.out.println("Please choose one of the following operations:");
            System.out.println("[1] List My Weeks");
            System.out.println("[2] List Recipes");
            System.out.println("[3] View Recipe");
            System.out.println("[4] Generate a New Week");
            System.out.println("[5] Back");

            System.out.print("Option: ");
            switch (inputScanner.nextInt()) {
                case 1:
                    viewRecipeWeek();
                    break;
                case 2:
                    viewRecipePool();
                    break;
                case 3:
                    viewRecipe();
                    break;
                case 4:
                    generateRecipeWeek();
                    displayUserMenu();
                    break;
                case 5:
                    printBannerLoop();
                    break;
                default:
                    System.out.println("Invalid Option");
                    displayUserMenu();
                    break;
            }
        }catch (InputMismatchException e){
            System.out.println("Invalid Option");
            inputScanner.nextLine();
            displayUserMenu();
        }


    }

    private void displayRecipeWeeks() {
        if (recipeWeeksDataList.size() > 0){
            System.out.println("List of Weeks");
            int index = 1;
            for (models.RecipeWeek element : recipeWeeksDataList) {
                System.out.println("["+element.getId()+"] "+"Week "+index);
                index++;
            }
            displayWeekSelection ();
        }else {
            System.out.println("No Week Found!");
            displayUserMenu();
        }
    }

    private void displayWeekSelection() {
        try {
            System.out.println("Please choose one of the following operations:");
            System.out.println("[1] Select Current Week");
            System.out.println("[2] Back");

            System.out.print("Option: ");
            switch (inputScanner.nextInt()) {
                case 1:
                    displayCurrentWeekRecipes ();
                    break;
                case 2:
                    displayUserMenu();
                    break;
                default:
                    System.out.println("Invalid Option");
                    displayWeekSelection();
                    break;
            }
        }catch (InputMismatchException e){
            System.out.println("Invalid Option");
            inputScanner.nextLine();
            displayWeekSelection();
        }

    }

    private void displayCurrentWeekRecipes() {
        AtomicInteger i = new AtomicInteger(1);
        System.out.println("Week "+ recipeWeeksDataList.size()+" Selected");
        Map<String, Recipe> wrTemp = recipeWeeksDataList.get(recipeWeeksDataList.size() - 1).getDayRecipeMapping();

        wrTemp.forEach((key, value) -> {
            System.out.println("[" + i + "] " + key + "          " + value.getTitle());
            i.getAndIncrement();
        });

        displayDaySelection ();

    }

    private void displayDaySelection() {
        try {
            System.out.println("Please choose one of the following operations:");
            System.out.println("[1] Select Today Recipe");
            System.out.println("[2] Back");

            System.out.print("Option: ");
            switch (inputScanner.nextInt()) {
                case 1 -> displayTodayRecipe();
                case 2 -> displayWeekSelection();
                default -> {
                    System.out.println("Invalid Option");
                    displayDaySelection();
                }
            }
        }catch (InputMismatchException e){
            System.out.println("Invalid Option");
            inputScanner.nextLine();
            displayDaySelection();
        }

    }

    private void setDay(Map<String, Recipe> wr){
        List<String> keys = new ArrayList<String>();
        for(String key : wr.keySet()){
            keys.add(key);
        }
        day = keys.get(keys.size() - 1);
    }
    private void displayTodayRecipe() {
        setDay(recipeWeeksDataList.get(recipeWeeksDataList.size() - 1).getDayRecipeMapping());
        System.out.println("Week "+recipeWeeksDataList.size()+" : "+ day);
        RecipeHelper.displayRecipe(recipeWeeksDataList.get(recipeWeeksDataList.size() - 1).getDayRecipeMapping().get(day));
        displayUserMenu();
    }

}
