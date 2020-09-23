package duke.task;
import duke.task.Task;

public class Event extends Deadline {
    /**
     * Constructor. Sets Event Task variables to respective states.
     *
     * @param stringInput a String input containing data about the Event Task.
     * @param isDoneInput a Boolean input showing whether the Event Task is done or not.
     * @param dateInput a String input showing the time the Event Task is due.
     */
    public Event(String stringInput, boolean isDoneInput, String dateInput){
        super(stringInput, isDoneInput, dateInput);
    }

    /**
     * Returns Event object as a String of data.
     */
    @Override
    public String toString(){
        if(getIsDone() == true){
            return String.format("[E][✓] %s (at: %s)", getData(), getDateDue());
        }else{
            return String.format("[E][✗] %s (at: %s)", getData(), getDateDue());
        }
    }
}