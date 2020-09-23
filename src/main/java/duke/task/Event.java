package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import duke.task.Task;

public class Event extends Deadline {
    /**
     * Constructor. Sets Event Task variables to respective states.
     *
     * @param stringInput a String input containing data about the Event Task.
     * @param isDoneInput a Boolean input showing whether the Event Task is done or not.
     * @param dateInput a String input showing the time the Event Task is due.
     */
    public Event(String stringInput, boolean isDoneInput, LocalDate dateInput, LocalTime timeInput){
        super(stringInput, isDoneInput, dateInput, timeInput);
    }

    /**
     * Returns Event object as a String of data.
     */
    @Override
    public String toString(){
        if(getIsDone() == true){
            return String.format("[E][✓] %s (at: ", getData()) +
                    getDateDue().format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " "  + getTimeDue() + ")";
        }else{
            return String.format("[E][✗] %s (at: ", getData()) +
                    getDateDue().format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " "  + getTimeDue() + ")";
        }
    }
}