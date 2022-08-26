package controllers;

import models.Recipe;
import utils.FileRecipeWeek;

import java.io.IOException;
import java.rmi.server.RemoteRef;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecipeWeek {

    private int weekId;
    private Map<String, Recipe> dayRecipeMapping = new HashMap<>();
    private List<models.RecipeWeek> recipeWeeks = new ArrayList<>();

    public RecipeWeek() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    List<models.RecipeWeek> recipeWeeksTemp = FileRecipeWeek.read();
                    if (recipeWeeksTemp != null)
                        recipeWeeks.addAll(recipeWeeksTemp);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public List<models.RecipeWeek> getRecipeWeeks() {
        return recipeWeeks;
    }

    public Map<String, Recipe> getDayRecipeMapping() {
        RecipePool recipePool = new RecipePool();
        if (recipePool.getIterator() == null )
            return null;
        dayRecipeMapping.put("Monday", recipePool.getRandomRecipe());
        dayRecipeMapping.put("Tuesday", recipePool.getRandomRecipe());
        dayRecipeMapping.put("Wednesday", recipePool.getRandomRecipe());
        dayRecipeMapping.put("Thursday", recipePool.getRandomRecipe());
        dayRecipeMapping.put("Friday", recipePool.getRandomRecipe());
        dayRecipeMapping.put("Saturday", recipePool.getRandomRecipe());
        dayRecipeMapping.put("Sunday", recipePool.getRandomRecipe());
        return dayRecipeMapping;
    }

    public boolean createRecipeWeek (){
        Map<String, Recipe> dayRecipeMapping = getDayRecipeMapping();
        if (dayRecipeMapping == null){
            System.out.println("null");
            return false;
        }
        int idRecipeWeek = 1;
        List<models.RecipeWeek> recipeWeeksTemp = new ArrayList<>();
        recipeWeeksTemp.addAll(recipeWeeks);
        if (recipeWeeksTemp.size() > 0){
            idRecipeWeek = recipeWeeksTemp.get(recipeWeeks.size() - 1).getId();
            idRecipeWeek++;
        }
        recipeWeeksTemp.add(new models.RecipeWeek(idRecipeWeek, dayRecipeMapping));
        try {
            FileRecipeWeek.write(recipeWeeksTemp);
            recipeWeeks.clear();
            recipeWeeks.addAll(recipeWeeksTemp);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
