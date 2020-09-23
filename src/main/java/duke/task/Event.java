package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import duke.task.Task;

public class Event extends Deadline {
    public Event(String stringInput, boolean isDoneInput, LocalDate dateInput, LocalTime timeInput){
        super(stringInput, isDoneInput, dateInput, timeInput);
    }

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