package duke.task;
import duke.task.Task;

public class Deadline extends Task {
    private String dateDue;

    public Deadline(String stringInput, boolean isDoneInput, String dateInput){
        super(stringInput, isDoneInput);
        dateDue = dateInput;
    }

    public String getDateDue(){
        return dateDue;
    }

    @Override
    public String toString(){
        if(getIsDone() == true){
            return String.format("[D][✓] %s (by: %s)", getData(), dateDue);
        }else{
            return String.format("[D][✗] %s (by: %s)", getData(), dateDue);
        }
    }
}