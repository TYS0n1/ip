package duke.task;
import duke.task.Task;

public class Deadline extends Task {
    private String dateDue;

    /**
     * Constructor. Sets Deadline Task variables to respective states.
     *
     * @param stringInput a String input containing data about the Deadline Task.
     * @param isDoneInput a Boolean input showing whether the Deadline Task is done or not.
     * @param dateInput a String input showing the time the Deadline Task is due.
     */
    public Deadline(String stringInput, boolean isDoneInput, String dateInput){
        super(stringInput, isDoneInput);
        dateDue = dateInput;
    }

    /**
     * Returns dateDue variable.
     */
    public String getDateDue(){
        return dateDue;
    }

    /**
     * Returns Deadline object as a String of data.
     */
    @Override
    public String toString(){
        if(getIsDone() == true){
            return String.format("[D][✓] %s (by: %s)", getData(), dateDue);
        }else{
            return String.format("[D][✗] %s (by: %s)", getData(), dateDue);
        }
    }
}