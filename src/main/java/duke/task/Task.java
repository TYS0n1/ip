package duke.task;

/**
 * Holds the methods and variables required to store and transmit
 * information of Tasks added to Duke program.
 */
public class Task {
    private String data;
    private boolean isDone;
    private int taskNumber;
    private static int numberOfTasks = 0;

    /**
     * Constructor. Sets Task variables to respective states.
     *
     * @param stringInput a String input containing data about the Task.
     * @param isDoneInput a Boolean input showing whether the Task is done or not.
     */
    public Task(String stringInput, boolean isDoneInput) {
        data = stringInput;
        isDone = isDoneInput;
        numberOfTasks++;
        taskNumber = numberOfTasks;
    }

    /**
     * Sets isDone variable to the state of its input.
     *
     * @param isDoneInput a Boolean input showing whether the Task is done or not.
     */
    public void setIsDone(boolean isDoneInput) {
        isDone = isDoneInput;
    }

    /**
     * Returns isDone variable.
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Returns data variable.
     */
    public String getData() {
        return data;
    }

    public int getTaskNumber() {
        return taskNumber;
    }

    /**
     * Returns Task object as a String of data.
     *
     * @return String containing information of Task object.
     */
    public String toString() {
        String doneCharacter;

        if (getIsDone() == true) {
            doneCharacter = "1";
        } else {
            doneCharacter = "0";
        }
        return String.format("[%s] %s", doneCharacter, data);
    }
}
