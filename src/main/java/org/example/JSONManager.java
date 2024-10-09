package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JSONManager {
    public static List<Task> readJson(String path) {
        Gson gs = new GsonBuilder().setPrettyPrinting().create();
        List<Task> list = new ArrayList<>();

        try (FileReader fileReader = new FileReader(path)) {
            Type personListType = new TypeToken<List<Task>>() {}.getType();
            list = gs.fromJson(fileReader, personListType);
        } catch (IOException exception) {

        }

        return list;
    }
    public static void saveJson(String path, List<Task> tasks) {
        Gson gs = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter fileWriter = new FileWriter(path)) {
            String json = gs.toJson(tasks);
            fileWriter.write(json);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
