package models;

import java.io.Serializable;

public class Recipe implements Serializable {
    private int id;
    private String title;
    private String[] ingredients;
    private String[] steps;

    public Recipe(int id, String title, String[] ingredients, String[] steps) {
        this.id = id;
        this.title = title;
        this.ingredients = ingredients;
        this.steps = steps;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public String[] getSteps() {
        return steps;
    }

    public void setSteps(String[] steps) {
        this.steps = steps;
    }
}
