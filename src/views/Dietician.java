package views;

import controllers.RecipePool;
import models.Recipe;
import utils.RecipeHelper;
import views.base.IDietician;
import views.base.Person;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Dietician extends Person implements IDietician {

    private boolean titleUpdated;
    private boolean ingredientsUpdated;
    private boolean stepsUpdated;
    private Scanner inputScanner = new Scanner(System.in);
    private RecipePool recipePool = new RecipePool();
    private Recipe updatedRecipe;

    @Override
    public void createRecipe() {
        Recipe recipe = getRecipeFromInput();
        try {
            recipePool.addRecipe(recipe);
            System.out.println("Recipe Created Successfully!");
        } catch (IOException e) {
            System.out.println("Error! Try Again.");
        }
        displayDieticianMenu();
    }

    @Override
    public void viewRecipe() {
        System.out.println("Enter Title of Recipe");
        inputScanner.nextLine();
        System.out.print("Title : ");
        String title = inputScanner.nextLine();
        RecipeHelper.displayRecipe (recipePool.getRecipeByTitle(title));
        displayDieticianMenu();
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
        displayDieticianMenu();
    }

    @Override
    public void updateRecipe() {
        System.out.println("Enter Recipe's Title");
        inputScanner.nextLine();
        System.out.print("Title : ");
        String recipeTitle = inputScanner.nextLine();
        displayUpdateChoice (recipeTitle);
        displayDieticianMenu();
    }

    @Override
    public void deleteRecipe() {
        System.out.println("Enter Recipe's Title");
        inputScanner.nextLine();
        System.out.print("Title : ");
        String recipeTitle = inputScanner.nextLine();
        if (recipePool.getRecipeByTitle(recipeTitle) != null){
            if (recipePool.deleteRecipe(recipeTitle))
                System.out.println("Recipe Deleted");
            else
                System.out.println("Error!");
        }
        else
            System.out.println("Recipe Not Found!");
        displayDieticianMenu();
    }

    public void displayDieticianMenu() {
        try {
            System.out.println("Please choose one of the following operations:");
            System.out.println("[1] View Recipe");
            System.out.println("[2] List Recipes");
            System.out.println("[3] Create a New Recipe");
            System.out.println("[4] Update Recipe");
            System.out.println("[5] Delete Recipe");
            System.out.println("[6] Back");

            System.out.print("Option: ");
            switch (inputScanner.nextInt()) {
                case 1:
                    viewRecipe();
                    break;
                case 2:
                    viewRecipePool();
                    break;
                case 3:
                    createRecipe();
                    break;

                case 4:
                    updateRecipe();
                    break;
                case 5:
                    deleteRecipe();
                    break;
                case 6:
                    printBannerLoop();
                    break;
                default:
                    System.out.println("Invalid Option");
                    displayDieticianMenu();
            }
        }catch (InputMismatchException e){
            System.out.println("Invalid Option");
            displayDieticianMenu();
        }

    }

    private Recipe getRecipeFromInput() {
        int id = 0;
        String title = "";
        String[] ingredients = null;
        String[] steps = null;

        System.out.println("Enter Recipe Details As Described in Steps");
        System.out.print("Title : ");
        title = inputScanner.nextLine();

        inputScanner.nextLine();

        System.out.println("Enter Number (Ingredients Count)");

        ingredients = new String[getQuantityofIngredients()];

        inputScanner.nextLine();

        System.out.println("Enter Ingredients (XYZ)");
        for (int i = 0; i < ingredients.length; i++){
            ingredients[i] = ""+(i+1)+"."+ inputScanner.nextLine();
        }

        System.out.println("Enter Number (Step Count)");
        System.out.print("How many?");

        steps = new String[getQuantityofSteps()];

        inputScanner.nextLine();

        System.out.println("Enter Steps (XYZ)");
        for (int i = 0; i < steps.length; i++){
            steps[i] = ""+(i+1)+"."+ inputScanner.nextLine();
        }

        List<Recipe> recipes = recipePool.getAllRecipes();
        if (recipes != null && recipes.size() > 0 ){
            id = recipes.get(recipes.size()-1).getId() + 1;
        }

        return new Recipe(id, title, ingredients, steps);

    }

    private void displayUpdateChoice(String recipeTitle) {
        updatedRecipe = recipePool.getRecipeByTitle(recipeTitle);
        if (updatedRecipe != null){
            RecipeHelper.displayRecipe(updatedRecipe);
            getUpdatedRecipe(recipeTitle);
        }
        else
            System.out.println("Recipe Not Found!");
    }

    private void getUpdatedRecipe(String recipeTitle) {
        try {
            System.out.println("Choose One of the Options to Update (1,2 or 3):");
            System.out.println("[1] Title");
            System.out.println("[2] Ingredients");
            System.out.println("[3] Steps");
            System.out.println("[4] Do Update");
            System.out.println("[5] Back");

            System.out.print("Option : ");
            switch (inputScanner.nextInt()) {
                case 1 -> {
                    updateRecipeTitle();
                    getUpdatedRecipe(recipeTitle);
                }
                case 2 -> {
                    updateRecipeIngredients();
                    getUpdatedRecipe(recipeTitle);
                }
                case 3 -> {
                    updateRecipeSteps();
                    getUpdatedRecipe(recipeTitle);
                }
                case 4 -> doUpdate();
                case 5 -> displayDieticianMenu();
                default -> {
                    System.out.println("Invalid Option");
                    displayUpdateChoice(recipeTitle);
                }
            }
        }catch (InputMismatchException e){
            System.out.println("Invalid Option");
            getUpdatedRecipe(recipeTitle);
        }

    }

    private void updateRecipeTitle() {
        inputScanner.nextLine();
        System.out.print("Title : ");
        updatedRecipe.setTitle(inputScanner.nextLine());
    }

    private void updateRecipeIngredients() {
        String[] ingredients ;
        System.out.println("Write ingredients below all from start:");
        System.out.println("Enter Number (Ingredients Count)");

        ingredients = new String[getQuantityofIngredients()];

        inputScanner.nextLine();

        System.out.println("Enter Ingredients (XYZ)");
        for (int i = 0; i < ingredients.length; i++){
            ingredients[i] = ""+(i+1)+"."+ inputScanner.nextLine();
        }

        updatedRecipe.setIngredients(ingredients);
    }

    private int getQuantityofIngredients() {
        int n;
        try {
            inputScanner.nextLine();
            System.out.print("How many?");
            n = inputScanner.nextInt();
        }catch (InputMismatchException e){
            System.out.println("Invalid Option");
            n = getQuantityofIngredients();
        }
        return n;
    }

    private void updateRecipeSteps() {
        inputScanner.nextLine();
        String[] steps ;
        int n;
        System.out.println("Enter Number (Step Count)");

        System.out.println("Enter Number (Step Count)");

        steps = new String[getQuantityofSteps()];

        inputScanner.nextLine();

        System.out.println("Enter Steps (XYZ)");
        for (int i = 0; i < steps.length; i++){
            steps[i] = ""+(i+1)+"."+ inputScanner.nextLine();
        }

        updatedRecipe.setSteps(steps);
    }

    private int getQuantityofSteps() {
        int n;
        try {
            inputScanner.nextLine();
            System.out.print("How many?");
            n = inputScanner.nextInt();
        }catch (InputMismatchException e){
            System.out.println("Invalid Option");
            n = getQuantityofIngredients();
        }
        return n;
    }


    public void doUpdate(){
        if (recipePool.updateRecipe(updatedRecipe))
            System.out.println("Recipe Updated Successfully!");
        else
            System.out.println("Error");
    }
}
