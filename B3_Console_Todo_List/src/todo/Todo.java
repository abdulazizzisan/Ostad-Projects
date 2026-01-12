package todo;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Scanner;

public class Todo {
    private String title;
    private String description;
    private Boolean isCompleted;
    private LocalDateTime dueDate;
    private Integer priority;

    public Todo(String title, String description, Boolean isCompleted, LocalDateTime dueDate, Integer priority) {
        this.title = title;
        this.description = description;
        this.isCompleted = isCompleted;
        this.dueDate = dueDate;
        this.priority = priority;
    }

    public Todo() {
        this.isCompleted = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", isCompleted=" + isCompleted +
                ", dueDate=" + dueDate.toLocalDate() +
                ", priority=" + priority +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return Objects.equals(title, todo.title) && Objects.equals(description, todo.description) && Objects.equals(isCompleted, todo.isCompleted) && Objects.equals(dueDate, todo.dueDate) && Objects.equals(priority, todo.priority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, isCompleted, dueDate, priority);
    }

    public static Todo getTodoFromUserInput(Scanner sc) {

        System.out.print("Please enter the title of your todo: ");
        String title = sc.nextLine();
        System.out.print("Please enter the description of your todo: ");
        String description = sc.nextLine();
        System.out.println("Please enter the number of days until the due date: ");
        int daysUntilDue = sc.nextInt();
        sc.nextLine();
        System.out.println("Please enter the priority of the todo (1-5): ");
        Integer priority = sc.nextInt();
        sc.nextLine();

        Todo todo = new Todo();

        todo.setTitle(title);
        todo.setDescription(description);
        todo.setDueDate(LocalDateTime.now().plusDays(daysUntilDue));
        todo.setPriority(priority);

        return todo;

    }
}
