package org.example;

public class Task {
    private String taskName;
    private String taskDescription;
    private TaskStatus taskStatus;

    public Task(String taskN, String taskD, TaskStatus ts) {
        taskName = taskN.toLowerCase();
        taskDescription = taskD;
        taskStatus = ts;
    }
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }


}
