package duke.parser;

import duke.common.Messages;
import duke.ui.UserInterface;
import duke.taskList.TaskListOperations;

/**
 * Holds the method required for deciding actions for Duke.
 */
public class Parser {
    /**
     * Receives command from the user and decides on what action to take.
     * Returns an Integer (1 or 0) to tell Duke whether to continue.
     * <p>
     * Special condition for each commands done, todo, deadline, event, delete.
     * Each command are required to have a space after the command to identify itself to not cause
     * conflict with other commands with similar names.
     * For example, "event" command creates a event object and adds it to the list. However, if
     * required in the future to create a "events" command to return all events on the list, "event"
     * command would be triggered instead of "events" due to sequential code. A space would differentiate
     * commands of similar naming.
     * A temporary check for these commands without spacing is implemented in case of conflict with
     * marking rubric or algorithm.
     *
     * @param input a command the user entered into console
     * @return returns returnValue, if 0 tells Duke to shut down.
     */
    public static int parseCommand(String input) {
        int returnValue = 1;
        if (input.equals("bye")) {
            UserInterface.printMessage(Messages.BYE);
            returnValue = 0;
        } else if (input.equals("list")){
            UserInterface.printList();
        } else if (input.startsWith("done ") == true) {
            TaskListOperations.setTaskDone(input);
        } else if (input.equals("save") == true) {
            TaskListOperations.saveTasks();
            UserInterface.printMessage(Messages.SAVED);
        } else if (input.startsWith("todo ") == true) {
            TaskListOperations.createTodo(input);
        } else if (input.startsWith("deadline ") == true) {
            TaskListOperations.createDeadline(input);
        } else if (input.startsWith("event ") == true) {
            TaskListOperations.createEvent(input);
        } else if (input.startsWith("delete ") == true) {
            TaskListOperations.deleteTask(input);
        } else if (input.startsWith("find ") == true) {
            TaskListOperations.findTask(input);
        } else if (input.startsWith("occur ") == true) {
            TaskListOperations.occuranceTask(input);
        } else if (input.equals("done") || input.equals("todo") ||
                input.equals("deadline") || input.equals("event") ||
                input.equals("delete")) {
            UserInterface.printMessage(Messages.emptyDescription(input));
        } else {
            UserInterface.printMessage(Messages.INVALID_INPUT_MAIN);
        }
        return returnValue;
    }
}
