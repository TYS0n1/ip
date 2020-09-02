import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    static Task[] listInputs = new Task[100];
    static int listPosition = 0;

    public static class Task{
        private String data;
        private boolean isDone;
        private int taskNumber;
        private static int numberOfTasks = 0;

        public Task(String stringInput, boolean isDoneInput){
            data = stringInput;
            isDone = isDoneInput;
            numberOfTasks++;
            taskNumber = numberOfTasks;
        }

        public void setIsDone(boolean isDoneInput){
            isDone = isDoneInput;
        }

        public boolean getIsDone(){
            return isDone;
        }

        public String getData(){
            return data;
        }

        public int getTaskNumber(){
            return taskNumber;
        }

        public String toString(){
            if(isDone == true){
                return String.format("[✓] %s", data);
            }else{
                return String.format("[✗] %s", data);
            }
        }
    }

    public static class Todo extends Task{
        public Todo(String stringInput, boolean isDoneInput){
            super(stringInput, isDoneInput);
        }

        @Override
        public String toString(){
            if(getIsDone() == true){
                return String.format("[T][✓] %s", getData());
            }else{
                return String.format("[T][✗] %s", getData());
            }
        }
    }

    public static class Deadline extends Task{
        private String dateDue;

        public Deadline(String stringInput, boolean isDoneInput, String dateInput){
            super(stringInput, isDoneInput);
            dateDue = dateInput;
        }

        public String getDateDue(){
            return dateDue;
        }

        @Override
        public String toString(){
            if(getIsDone() == true){
                return String.format("[D][✓] %s (by: %s)", getData(), dateDue);
            }else{
                return String.format("[D][✗] %s (by: %s)", getData(), dateDue);
            }
        }
    }

    public static class Event extends Deadline{
        public Event(String stringInput, boolean isDoneInput, String dateInput){
            super(stringInput, isDoneInput, dateInput);
        }

        @Override
        public String toString(){
            if(getIsDone() == true){
                return String.format("[E][✓] %s (at: %s)", getData(), getDateDue());
            }else{
                return String.format("[E][✗] %s (at: %s)", getData(), getDateDue());
            }
        }
    }

    public static void printMessage(String message){
        System.out.println("____________________________________________________________");
        if(message.equals("bye")){
            System.out.println(" Bye. Hope to see you again soon!");
        }else{
            System.out.printf("%s", message);
            System.out.println();
        }

        System.out.println("____________________________________________________________");
    }

    public static void printList(Task[] list, int listLength){
        System.out.println("____________________________________________________________");
        System.out.println(" Here are the tasks in your list:");
        for(int i = 0; i < listLength; i++){
            System.out.printf(" %d.%s\n", i + 1, list[i].toString());
        }
        System.out.println("____________________________________________________________");
    }

    public static void printDoneStatement(Task taskObject){
        String outputMessage = " Nice! I've marked this task as done: \n" +
                 "   " + taskObject.toString();
        printMessage(outputMessage);
    }

    public static void doneOperation(String input){
        // Operation fails if input is not a number, add exception later
        int taskNumber = Integer.parseInt(input.substring(5, input.length())) - 1;
        if(taskNumber < listPosition && taskNumber >= 0){
            listInputs[taskNumber].setIsDone(true);
            printDoneStatement(listInputs[taskNumber]);
        }else{
            printMessage(" Invalid task number");
        }
    }

    public static void printAddedTaskMessage(int taskIndex){
        String outputMessage = " Got it. I've added this task: \n" + "   " +
                listInputs[taskIndex].toString() + "\n" +
                " Now you have " + Integer.toString(listPosition + 1) + " tasks in the list.";
        printMessage(outputMessage);
    }

    public static void todoOperation(String input){
        String todoData = input.substring(5, input.length());
        listInputs[listPosition] = new Todo(todoData, false);
        printAddedTaskMessage(listPosition);
        listPosition++;
    }

    public static void deadlineOperation(String input){
        input = input.substring(9, input.length());
        if(input.startsWith("/by") == true || input.contains(" /by ") == false){
            printMessage(" Invalid deadline declaration\ndeadline {info} /by {date}");
            return;
        }

        int serperatorIndex = input.indexOf(" /by ");
        String data = input.substring(0, serperatorIndex);
        String date = input.substring(serperatorIndex + 5, input.length());
        listInputs[listPosition] = new Deadline(data, false, date);
        printAddedTaskMessage(listPosition);
        listPosition++;
    }

    public static void eventOperation(String input){
        input = input.substring(6, input.length());
        if(input.startsWith("/at") == true || input.contains(" /at ") == false){
            printMessage(" Invalid event declaration\nevent {info} /by {date} {time}");
            return;
        }

        int serperatorIndex = input.indexOf(" /at ");
        String data = input.substring(0, serperatorIndex);
        String dateAndTime = input.substring(serperatorIndex + 5, input.length());
        listInputs[listPosition] = new Event(data, false, dateAndTime);
        printAddedTaskMessage(listPosition);
        listPosition++;
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        String input = "empty";
        Scanner in = new Scanner(System.in);
        boolean isRunning = true;

        while(isRunning == true){
            input = in.nextLine();
            if(input.equals("bye")) {
                printMessage(input);
                isRunning = false;
            }else if (input.equals("list")){
                printList(listInputs, listPosition);
            }else if(input.startsWith("done ") == true) {
                doneOperation(input);
            }else if(input.startsWith("todo ") == true) {
                todoOperation(input);
            }else if(input.startsWith("deadline ") == true) {
                deadlineOperation(input);
            }else if(input.startsWith("event ") == true) {
                eventOperation(input);
            }else {
                printMessage(" added: " + input);
                listInputs[listPosition] = new Task(input, false);
                listPosition++;
            }
        }
    }
}
