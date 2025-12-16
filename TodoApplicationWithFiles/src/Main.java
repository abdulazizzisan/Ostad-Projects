import file.FileHandler;
import todo.Todo;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String msg = FileHandler.createNewJsonFile("todo.json");
        System.out.println(msg);

//        Todo todo = new Todo();
//        todo.setName("Learn Java File Handling");
//        todo.setCompleted(false);

        List<Todo> todoList = new ArrayList<>();

        Todo todo1 = new Todo("Learn Java File Handling", false);
        Todo todo2 = new Todo("Build a Todo App", false);
        Todo todo3 = new Todo("Hello World", true);
        Todo todo4 = new Todo("Last Todo", true);
        todoList.add(todo1);
        todoList.add(todo2);
        todoList.add(todo3);
        todoList.add(todo4);

//        String json = todo1.toJson();
//        msg = FileHandler.writeJsonToFile("src", "todo.json", json);
//        System.out.println(msg);

        String readJson = FileHandler.readJsonFromFile("src", "todo.json");

        Todo todoFromFile = Todo.fromJson(readJson);

        System.out.println("Todo from file - Name: " + todoFromFile.getName() + ", Completed: " + todoFromFile.isCompleted());
    }
}