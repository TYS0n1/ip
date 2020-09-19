package duke.parser;

import static duke.Duke.*;
import static duke.common.Messages.*;

import duke.common.Messages;

public class Parser {
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
