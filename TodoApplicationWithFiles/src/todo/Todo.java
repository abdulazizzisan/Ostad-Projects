package todo;

import java.util.List;

public class Todo {
    private String name;
    private boolean completed;


    public String toJson() {
        return "{ " +
                "\"name\": \"" + name + "\", " +
                "\"completed\": " + completed +
                " }";
    }

    public static String todoListToJson(List<Todo> todos){
        StringBuilder json = new StringBuilder();
        json.append("[");
        for (int i = 0; i < todos.size(); i++) {
            json.append(todos.get(i).toJson());
            if (i < todos.size() - 1) {
                json.append(", ");
            }
        }
        json.append("]");
        return json.toString();
    }

    public static Todo fromJson(String json) {
        if(json == null || json.isEmpty()) {
            return new Todo();
        }
        String name = json.split("\"name\": \"")[1].split("\",")[0];
        boolean completed = Boolean.parseBoolean(json.split("\"completed\": ")[1].split(" }")[0]);
        return new Todo(name, completed);
    }

//    public static List<Todo> todoListFromJson(String json) {
//        json.split()
//    }



    public Todo(String name, boolean completed) {
        this.name = name;
        this.completed = completed;
    }

    public Todo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

}
