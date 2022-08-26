package controllers;

import models.Recipe;
import utils.FileRecipe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class RecipePool {
    private List<Recipe> allRecipes = new ArrayList<>();

    public RecipePool() {
        getRecipes ();
    }
    private void getRecipes() {
        try {
            List<Recipe> recipesTemp = FileRecipe.read();
            allRecipes.clear();
            if (recipesTemp != null){
                allRecipes.addAll(recipesTemp);
            }
        } catch (IOException e) {
            System.out.println("File Not Found!");
        } catch (ClassNotFoundException e) {
            System.out.println("Recipe Class Error");
        }
    }

    public Iterator<Recipe> getIterator(){
        getRecipes();
        if (allRecipes.size() > 0)
            return allRecipes.iterator();
        return null;
    }

    public List<Recipe> getAllRecipes() {
        getRecipes();
        return allRecipes;
    }

    public void addRecipe (Recipe recipe) throws IOException {
        getRecipes();
        List<Recipe> recipesTemp = new ArrayList<>();
        recipesTemp.addAll(allRecipes);
        recipesTemp.add(recipe);
        FileRecipe.write(recipesTemp);
        allRecipes.clear();
        allRecipes.addAll(recipesTemp);
    }

    public boolean updateRecipe (Recipe recipe){
        getRecipes();
        List<Recipe> allRecipesTemp = new ArrayList<>();
        allRecipesTemp.addAll(allRecipes);
        for (int i = 0; i < allRecipesTemp.size(); i++) {
            if (allRecipesTemp.get(i).getTitle().equals(recipe.getTitle())){
                allRecipesTemp.set(i, recipe);
                try {
                    FileRecipe.write(allRecipesTemp);
                    allRecipes.clear();
                    allRecipes.addAll(allRecipesTemp);
                } catch (IOException e) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    public boolean deleteRecipe (String title){
        getRecipes();
        List<Recipe> allRecipesTemp = new ArrayList<>();
        allRecipesTemp.addAll(allRecipes);
        int i = 0;
        while (i < allRecipesTemp.size()){
            if (allRecipesTemp.get(i).getTitle().equals(title)){
                allRecipesTemp.remove(i);
                try {
                    FileRecipe.write(allRecipesTemp);
                    allRecipes.clear();
                    allRecipes.addAll(allRecipesTemp);
                    return true;
                } catch (IOException e) {
                    return false;
                }
            }
        }
        return false;
    }

    public Recipe getRecipeByTitle (String title){
        getRecipes();
        for (Recipe recipe: allRecipes) {
            if (recipe.getTitle().equals(title))
                return recipe;
        }
        return null;
    }

    public Recipe getRandomRecipe (){
        getRecipes();
        if (allRecipes.size() > 0)
            return allRecipes.get(new Random().nextInt(allRecipes.size()));
        return null;
    }
}
