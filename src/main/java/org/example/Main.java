package org.example;

import java.io.BufferedReader;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String pathLoad;
        String pathSave;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter load path: ");
        pathLoad = scanner.nextLine();

        pathSave = pathLoad;

        boolean running = true;
        try {
            TasksManager tm = new TasksManager(pathLoad);
            while (running) {
                String action;
                System.out.print("Please enter an action (add, remove, update, showall, show, or anything else to exit): ");
                action = scanner.nextLine();

                switch (action) {
                    case "add":
                        addTask(scanner, tm);
                        break;
                    default:
                        System.out.println("Exiting...");
                        running = false;
                        break;
                    case "remove":
                        removeTask(scanner, tm);
                        break;
                    case "update":
                        updateTask(scanner, tm);
                        break;
                    case "showall":
                        tm.showTasks();
                        break;
                    case "show":
                        showTask(scanner, tm);
                        break;

                }
            }
            tm.saveTasks(pathSave);
        } catch (Exception exception) {
            exception.printStackTrace();;
        }

    }
    public static TaskStatus getStatus(Scanner scanner) {
        while (true) {
            System.out.print("Enter status (completed, not completed, in progress): ");
            String input = scanner.nextLine();
            switch (input) {
                case "completed":
                    return TaskStatus.COMPLETED;
                case "not completed":
                    return TaskStatus.NOT_DONE;
                case "in progress":
                    return TaskStatus.IN_PROGRESS;
                default:
                    break;
            }
        }

    }
    public static void addTask(Scanner scanner, TasksManager tm) {
        String name;
        String description;
        TaskStatus taskStatus;

        System.out.println("Enter name: ");
        name = scanner.nextLine();
        System.out.println("Enter description: ");
        description = scanner.nextLine();
        taskStatus = getStatus(scanner);

        if (name.isEmpty()) {
            System.out.println("One of entered values is empty, try again");
            return;
        }

        Task task = new Task(name, description, taskStatus);
        //System.out.println(tm.exists(task));
        if (tm.exists(name)) {
            System.out.println("Task with such name exists");
            return;
        }
        tm.addTask(task);
    }
    public static void removeTask(Scanner scanner, TasksManager tm) {
        System.out.print("Enter task name: ");
        String name = scanner.nextLine();
        if (name.isEmpty()) {
            System.out.println("Entered value is empty, try again");
            return;
        }
        if (!tm.removeTask(name)) {
            System.out.println("A task with such name doesn't exist");
            return;
        }
    }
    public static void updateTask(Scanner scanner, TasksManager tm) {
        System.out.print("Enter task name: ");
        String name = scanner.nextLine();
        if (!tm.exists(name)) {
            System.out.println("Such task doesn't exist, create one");
            return;
        }
        String nameNew;
        System.out.print("Enter updated task name: ");
        nameNew = scanner.nextLine();
        System.out.print("Enter updated task description: ");
        String description = scanner.nextLine();
        System.out.print("Enter updated task status: ");
        TaskStatus taskStatus = getStatus(scanner);

        tm.updateTask(name, new Task(nameNew, description, taskStatus));
    }
    public static void showTask(Scanner scanner, TasksManager tm) {
        System.out.print("Enter task name: ");
        String name = scanner.nextLine();
        if (!tm.exists(name)) {
            System.out.println("Such task doesn't exist");
            return;
        }
        tm.showTask(name);
    }

}