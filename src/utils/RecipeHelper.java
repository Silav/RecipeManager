package utils;

import models.Recipe;

public class RecipeHelper {
    public static void displayRecipe (Recipe recipe){
        if (recipe != null){
            System.out.println("Recipe Details");
            System.out.println("  ["+recipe.getId()+"] "+recipe.getTitle());
            displayIngredients (recipe.getIngredients());
            displaySteps(recipe.getSteps());
        }else
            System.out.println("No Recipe Found!");
    }

    private static void displayIngredients(String[] ingredients) {
        System.out.println("  Ingredients:");
        for (int i = 0; i < ingredients.length; i++) {
            System.out.println("      "+(i+1)+". "+ingredients[i]);
        }

    }

    private static void displaySteps(String[] steps) {
        System.out.println("  Steps:");
        for (int i = 0; i < steps.length; i++) {
            System.out.println("      "+(i+1)+". "+steps[i]);
        }

    }
}
