package duke;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;
import duke.task.*;



public class Duke {
    static Task[] listInputs = new Task[100];
    static int listPosition = 0;

    private static final String BYE_MESSAGE = " " + "Bye. Hope to see you again soon!";
    private static final String LIST_HEADER_MESSAGE = " "  + "Here are the tasks in your list:";
    private static final String DONE_HEADER_MESSAGE = " " + "Nice! I've marked this task as done: ";
    private static final String DEADLINE_FORMAT_MESSAGE = " " + "Invalid deadline declaration\n" +
            " " + "deadline {info} /by {date}";
    private static final String EVENT_FORMAT_MESSAGE = " " + "Invalid event declaration\n" +
            " " + "event {info} /by {date} {time}";
    private static final String INVALID_INPUT_MAIN_MESSAGE =
            " ☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    private static final String EMPTY_DONE_INPUT_MESSAGE = "☹ OOPS!!! The description of a done cannot be empty.";
    private static final String EMPTY_TODO_INPUT_MESSAGE = "☹ OOPS!!! The description of a todo cannot be empty.";
    private static final String EMPTY_DEADLINE_INPUT_MESSAGE =
            "☹ OOPS!!! The description of a deadline cannot be empty.";
    private static final String EMPTY_EVENT_INPUT_MESSAGE =
            "☹ OOPS!!! The description of a event cannot be empty.";

    /*public static class Task{
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
    }*/

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
        if(listPosition == 0){
            printMessage(" List is empty");
            return;
        }else if(input.length() == 5){
            printMessage(EMPTY_DONE_INPUT_MESSAGE);
            return;
        }

        try{
            int taskNumber = Integer.parseInt(input.substring(5, input.length())) - 1;
            if(taskNumber < listPosition && taskNumber >= 0){
                listInputs[taskNumber].setIsDone(true);
                printDoneStatement(listInputs[taskNumber]);
            }else{
                printMessage(" Invalid task number\n " +
                        "done {valid index from 1 to " + Integer.toString(listPosition) + "}");
            }
        }catch(NumberFormatException e){
            printMessage(" Invalid index for done operation\n " +
                    "done {valid index from 1 to " + Integer.toString(listPosition) + "}");
        }
    }

    public static void printAddedTaskMessage(int taskIndex){
        String outputMessage = " Got it. I've added this task: \n" + "   " +
                listInputs[taskIndex].toString() + "\n" +
                " Now you have " + Integer.toString(listPosition + 1) + " tasks in the list.";
        printMessage(outputMessage);
    }

    public static void todoOperation(String input){
        if(input.length() == 5){
            printMessage(EMPTY_TODO_INPUT_MESSAGE);
            return;
        }

        String todoData = input.substring(5, input.length());
        listInputs[listPosition] = new Todo(todoData, false);
        printAddedTaskMessage(listPosition);
        listPosition++;
    }

    public static void deadlineOperation(String input){
        if(input.length() == 9){
            printMessage(EMPTY_DEADLINE_INPUT_MESSAGE);
            return;
        }

        input = input.substring(9, input.length());
        if(input.startsWith("/by") == true || input.contains(" /by ") == false){
            printMessage(DEADLINE_FORMAT_MESSAGE);
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
        if(input.length() == 6){
            printMessage(EMPTY_EVENT_INPUT_MESSAGE);
            return;
        }

        input = input.substring(6, input.length());
        if(input.startsWith("/at") == true || input.contains(" /at ") == false){
            printMessage(EVENT_FORMAT_MESSAGE);
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

        /**
         * Special condition for each commands done, todo, deadline, event.
         * Each command are required to have a space after the command to identify itself to not cause
         * conflict with other commands with similar names.
         * For example, current "event" command creates a event object and adds it to the list. However, if
         * required in the future to create a "events" command to return all events on the list, "event"
         * command would be triggered instead of "events" due to sequential code. A space would differentiate
         * commands of similar naming.
         * A temporary check for these commands without spacing is implemented in case of conflict with
         * marking rubric or algorithm.
         */
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
            }else if(input.equals("done") || input.equals("todo") ||
                    input.equals("deadline") || input.equals("event")) {
                printMessage(" ☹ OOPS!!! The description of a " + input + " cannot be empty.");
            }else {
                printMessage(INVALID_INPUT_MAIN_MESSAGE);
            }
        }
    }
}
