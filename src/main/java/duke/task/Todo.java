package duke.task;

/**
 * Holds the methods and variables required to store and transmit
 * information of Todo Tasks added to Duke program.
 */
public class Todo extends Task {
    /**
     * Constructor. Sets Todo Task variables to respective states.
     *
     * @param stringInput a String input containing data about the Todo Task.
     * @param isDoneInput a Boolean input showing whether the Todo Task is done or not.
     */
    public Todo(String stringInput, boolean isDoneInput) {
        super(stringInput, isDoneInput);
    }

    /**
     * Returns Todo object as a String of data.
     *
     * @return String containing information of Todo object.
     */
    @Override
    public String toString() {
        String doneAsciiArt;

        if (getIsDone() == true) {
            doneAsciiArt = "✓";
        } else {
            doneAsciiArt = "✗";
        }

        return String.format("[T][%s] %s", doneAsciiArt, getData());
    }
}
