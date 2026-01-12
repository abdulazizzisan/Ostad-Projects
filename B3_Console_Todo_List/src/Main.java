import todo.Todo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        List<Todo> todoList = new ArrayList<>();

        System.out.println("Welcome to the Todo List Application!");

        while (true){

            System.out.println("""
                    Enter 1 to add a new Todo.
                    Enter 2 to complete a todo.
                    Enter 3 to delete a Todo.
                    Enter 4 to view all Todos.
                    Enter 0 to exit.
                    """);
            int choice = sc.nextInt();
            sc.nextLine();

            if(choice == 0){
                System.out.println("Exiting the application. Goodbye!");
                break;
            } else if(choice == 1){
                Todo todo = Todo.getTodoFromUserInput(sc);

                todoList.add(todo);
            } else if(choice == 4){
                System.out.println("Here are all your todos: ");
                for (Todo todo: todoList){
                    System.out.println(todo);
                    if(!todo.getCompleted() && todo.getDueDate().isBefore(LocalDateTime.now())){
                        System.out.println("Your due date is already passed, please complete the todo.");
                    } else if(!todo.getCompleted() && todo.getDueDate().isBefore(LocalDateTime.now().plusDays(1))){
                        System.out.println("You only have 24 hours left to complete the todo. Please complete it fast.");
                    }
                }
            } else if (choice == 2){

                int todoIndex = getIndexOfTodo("Enter %d to complete the todo below: ", sc, todoList);

                todoList.get(todoIndex - 1).setCompleted(true);
            } else if(choice == 3){

                int todoIndex = getIndexOfTodo("Enter %d to delete the todo below: ", sc, todoList);

                todoList.remove(todoIndex - 1);
            }
        }


        System.out.println(todoList);
    }

    private static int getIndexOfTodo(String message, Scanner sc, List<Todo> todoList){
        for (int i = 0; i < todoList.size(); i++){
            String msg = String.format(message, i + 1);
            System.out.println(msg); // 0 -> 1, 1 -> 2
            System.out.println(todoList.get(i));
        }

        int todoIndex = sc.nextInt();

        return todoIndex;
        
    }
}