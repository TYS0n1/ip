package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import duke.task.Task;


public class Deadline extends Task {
    private LocalDate dateDue;
    private LocalTime timeDue;

    /**
     * Constructor. Sets Deadline Task variables to respective states.
     *
     * @param stringInput a String input containing data about the Deadline Task.
     * @param isDoneInput a Boolean input showing whether the Deadline Task is done or not.
     * @param dateInput a String input showing the time the Deadline Task is due.
     */
    public Deadline(String stringInput, boolean isDoneInput, LocalDate dateInput, LocalTime timeInput){
        super(stringInput, isDoneInput);
        dateDue = dateInput;
        timeDue = timeInput;
    }
    
    public String getDateDue(){
        return dateDue;
    }

    /**
     * Returns dateDue variable.
     */
    public LocalDate getDateDue(){
        return dateDue;
    }

    public LocalTime getTimeDue(){
        return timeDue;
    }
  
    /**
     * Returns Deadline object as a String of data.
     */
    @Override
    public String toString(){
        if(getIsDone() == true){
            return String.format("[D][✓] %s (by: ", getData()) +
                    dateDue.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + timeDue.toString() + ")";
        }else{
            return String.format("[D][✗] %s (by: ", getData()) +
                    dateDue.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " "  + timeDue.toString() + ")";
        }
    }
}