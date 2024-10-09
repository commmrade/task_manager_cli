package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TasksManager {
    private List<Task> tasks;

    public TasksManager(String path) throws Exception {
        File n = new File(path);

        tasks = new ArrayList<>();
        if (!n.exists()) {
            if (!n.createNewFile()) {
                throw new Exception("File could not be created");
            }
        }

        tasks = JSONManager.readJson(path);
        if (tasks == null) {
            tasks = new ArrayList<>();
        }
    }
    public void addTask(Task task){
        tasks.add(task);
    }
    public boolean exists(String name) {
        return tasks.stream().anyMatch(task -> task.getTaskName().equals(name.toLowerCase()));
    }
    public void saveTasks(String path) {
        JSONManager.saveJson(path, tasks);
    }
    public boolean removeTask(String name) {
        return tasks.removeIf(task -> task.getTaskName().equals(name.toLowerCase()));
    }
    public void updateTask(String taskName, Task updatedTask) {
        for (var task : tasks) {
            if (task.getTaskName().equals(taskName.toLowerCase())) {
                task.setTaskName(updatedTask.getTaskName());
                task.setTaskDescription(updatedTask.getTaskDescription());
                task.setTaskStatus(updatedTask.getTaskStatus());
                System.out.println("Updated");
            }
        }
    }
    public void showTasks() {
        System.out.println("------------------------------------------");
        for (var task : tasks) {
            System.out.println("Name: " + task.getTaskName());
            System.out.println("Description: " + task.getTaskDescription());
            switch (task.getTaskStatus()) {
                case TaskStatus.COMPLETED -> System.out.println("Status: completed");
                case TaskStatus.NOT_DONE -> System.out.println("Status: not done");
                case TaskStatus.IN_PROGRESS -> System.out.println("Status: in progress");
            }
            System.out.println("------------------------------------------");
        }

    }
    public void showTask(String name) {
        tasks.forEach(task -> {
            if (task.getTaskName().equals(name.toLowerCase())) {
                System.out.println("------------------------------------------");
                System.out.println("Name: " + task.getTaskName());
                System.out.println("Description: " + task.getTaskDescription());
                switch (task.getTaskStatus()) {
                    case TaskStatus.COMPLETED -> System.out.println("Status: completed");
                    case TaskStatus.NOT_DONE -> System.out.println("Status: not done");
                    case TaskStatus.IN_PROGRESS -> System.out.println("Status: in progress");
                }
                System.out.println("------------------------------------------");
            }
        });
    }
}
