package utils;

import models.RecipeWeek;

import java.io.*;
import java.util.List;

public class FileRecipeWeek {

    private static final String FILE_NAME = "./RecipeWeek.txt";
    private static File file;
    private static FileOutputStream fileOutputStream ;

    public static void write(List<RecipeWeek> recipeWeeks) throws IOException {
        file = new File(FILE_NAME);
        if (file.exists())
            file.delete();
        file.createNewFile();
        fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(recipeWeeks);
        objectOutputStream.close();
        fileOutputStream.close();
    }

    public static List<RecipeWeek> read() throws IOException, ClassNotFoundException {
        file = new File(FILE_NAME);
        if (file.exists()){
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            List<RecipeWeek> recipes = (List<RecipeWeek>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            return recipes;
        }
        return null;
    }
}
