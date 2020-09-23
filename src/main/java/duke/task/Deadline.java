package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import duke.task.Task;


public class Deadline extends Task {
    private LocalDate dateDue;
    private LocalTime timeDue;

    public Deadline(String stringInput, boolean isDoneInput, LocalDate dateInput, LocalTime timeInput){
        super(stringInput, isDoneInput);
        dateDue = dateInput;
        timeDue = timeInput;
    }

    public LocalDate getDateDue(){
        return dateDue;
    }

    public LocalTime getTimeDue(){
        return timeDue;
    }

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