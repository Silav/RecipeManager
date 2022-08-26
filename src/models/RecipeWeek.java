package models;

import java.io.Serializable;
import java.util.Map;

public class RecipeWeek implements Serializable {
    private int id;
    private Map<String, Recipe> dayRecipeMapping;

    public RecipeWeek(int id, Map<String, Recipe> dayRecipeMapping) {
        this.id = id;
        this.dayRecipeMapping = dayRecipeMapping;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<String, Recipe> getDayRecipeMapping() {
        return dayRecipeMapping;
    }

    public void setDayRecipeMapping(Map<String, Recipe> dayRecipeMapping) {
        this.dayRecipeMapping = dayRecipeMapping;
    }
}
