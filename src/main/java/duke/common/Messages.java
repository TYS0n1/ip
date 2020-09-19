package duke.common;

import static duke.Duke.listInputs;

public class Messages {
    public static final String BYE_MESSAGE = " " + "Bye. Hope to see you again soon!";
    public static final String SAVED_MESSAGE = " " + "Nice! I have saved your list.";
    public static final String LIST_HEADER_MESSAGE = " "  + "Here are the tasks in your list:";
    public static final String DONE_HEADER_MESSAGE = " " + "Nice! I've marked this task as done: ";
    public static final String DEADLINE_FORMAT_MESSAGE = " " + "Invalid deadline declaration\n" +
            " " + "deadline {info} /by {date}";
    public static final String EVENT_FORMAT_MESSAGE = " " + "Invalid event declaration\n" +
            " " + "event {info} /by {date} {time}";
    public static final String INVALID_INPUT_MAIN_MESSAGE =
            " " + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String EMPTY_DONE_INPUT_MESSAGE = " " + "☹ OOPS!!! The description of a done cannot be empty.";
    public static final String EMPTY_TODO_INPUT_MESSAGE = " " + "☹ OOPS!!! The description of a todo cannot be empty.";
    public static final String EMPTY_DEADLINE_INPUT_MESSAGE =
            " " + "☹ OOPS!!! The description of a deadline cannot be empty.";
    public static final String EMPTY_EVENT_INPUT_MESSAGE =
            " " + "☹ OOPS!!! The description of a event cannot be empty.";
    public static final String EMPTY_DELETE_INPUT_MESSAGE =
            " " + "☹ OOPS!!! The description of a delete cannot be empty.";

    public static final String VALID_INDEX_RANGE =
            "{valid index from 1 to " + Integer.toString(listInputs.size()) + "}";
}
