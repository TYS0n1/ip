package duke.task;
import duke.task.Task;

public class Event extends Deadline {
    public Event(String stringInput, boolean isDoneInput, String dateInput){
        super(stringInput, isDoneInput, dateInput);
    }

    @Override
    public String toString(){
        if(getIsDone() == true){
            return String.format("[E][✓] %s (at: %s)", getData(), getDateDue());
        }else{
            return String.format("[E][✗] %s (at: %s)", getData(), getDateDue());
        }
    }
}