package utils;

import models.Recipe;

import java.io.*;
import java.util.List;

public class FileRecipe {

    private static final String FILE_NAME = "./Recipes.txt";
    private static File file;
    private static FileOutputStream fileOutputStream ;

    public static void write(List<Recipe> recipes) throws IOException {
        file = new File(FILE_NAME);
        if (file.exists())
            file.delete();
        file.createNewFile();
        fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(recipes);
        objectOutputStream.close();
        fileOutputStream.close();
    }

    public static List<Recipe> read() throws IOException, ClassNotFoundException {
        file = new File(FILE_NAME);
        if (file.exists()){
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            List<Recipe> recipes = (List<Recipe>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            return recipes;
        }
        return null;
    }
}
