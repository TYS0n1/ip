package duke.common;

import static duke.storage.StorageHandler.listInputs;

/**
 * Holds the constants required for displaying information to the user.
 */
public class Messages {
    public static final String BYE = " " + "Bye. Hope to see you again soon!";
    public static final String SAVED = " " + "Nice! I have saved your list.";
    public static final String LIST_HEADER = " "  + "Here are the tasks in your list:";
    public static final String DONE_HEADER = " " + "Nice! I've marked this task as done: ";
    public static final String SEARCH_HEADER = " " + "Here are the matching tasks in your list:";
    public static final String OCCUR_HEADER = " " + "Tasks due on: ";
    public static final String DEADLINE_FORMAT = " " + "Invalid deadline declaration\n" +
            " " + "deadline {info} /by {dd/mm/yyyy} {hr:mn}";
    public static final String EVENT_FORMAT = " " + "Invalid event declaration\n" +
            " " + "event {info} /at {dd/mm/yyyy} {hr:mn)}";
    public static final String OCCUR_FORMAT = " " + "Invalid occur declaration\n" +
            " " + "occur {dd/mm/yyyy}";
    public static final String INVALID_INPUT_MAIN =
            " " + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String EMPTY_DONE_INPUT = " " + "☹ OOPS!!! The description of a done cannot be empty.";
    public static final String EMPTY_TODO_INPUT = " " + "☹ OOPS!!! The description of a todo cannot be empty.";
    public static final String EMPTY_DEADLINE_INPUT =
            " " + "☹ OOPS!!! The description of a deadline cannot be empty.";
    public static final String EMPTY_EVENT_INPUT =
            " " + "☹ OOPS!!! The description of a event cannot be empty.";
    public static final String EMPTY_DELETE_INPUT =
            " " + "☹ OOPS!!! The description of a delete cannot be empty.";
    public static final String EMPTY_FIND_INPUT =
            " " + "☹ OOPS!!! The description of a find cannot be empty.";
    public static final String EMPTY_OCCUR_INPUT =
            " " + "☹ OOPS!!! The description of a occur cannot be empty.";

    public static final String VALID_INDEX_RANGE =
            "{valid index from 1 to " + Integer.toString(listInputs.size()) + "}";
    public static final String TASK_READ_FAIL = "Invalid task read.";

    /**
     * Returns a message telling user that the command typed in cannot be empty.
     *
     * @param inputString the command the user has typed in.
     * @return a message telling user that the command typed in cannot be empty.
     */
    public static String emptyDescription(String inputString){
        return " ☹ OOPS!!! The description of a " + inputString + " cannot be empty.";
    }
}
