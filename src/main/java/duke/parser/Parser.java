package duke.parser;

import static duke.taskList.taskListOperations.*;
import static duke.common.Messages.*;
import static duke.ui.UserInterface.*;

public class Parser {
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
    public static int parseCommand(String input){
        if(input.equals("bye")) {
            printMessage(BYE_MESSAGE);
            return 0;
        }else if (input.equals("list")){
            printList();
        }else if(input.startsWith("done ") == true) {
            doneOperation(input);
        }else if(input.equals("save") == true) {
            saveOperation();
            printMessage(SAVED_MESSAGE);
        }else if(input.startsWith("todo ") == true) {
            todoOperation(input);
        }else if(input.startsWith("deadline ") == true) {
            deadlineOperation(input);
        }else if(input.startsWith("event ") == true) {
            eventOperation(input);
        }else if(input.startsWith("delete ") == true) {
            deleteOperation(input);
        }else if(input.startsWith("occur ") == true) {
            occurOperation(input);
        }else if(input.equals("done") || input.equals("todo") ||
                input.equals("deadline") || input.equals("event") ||
                input.equals("delete")) {
            printMessage(" ☹ OOPS!!! The description of a " + input + " cannot be empty.");
        }else {
            printMessage(INVALID_INPUT_MAIN_MESSAGE);
        }
        return 1;
    }
}
