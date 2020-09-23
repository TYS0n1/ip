package duke.task;
import duke.task.Task;

public class Todo extends Task {
    /**
     * Constructor. Sets Todo Task variables to respective states.
     *
     * @param stringInput a String input containing data about the Todo Task.
     * @param isDoneInput a Boolean input showing whether the Todo Task is done or not.
     */
    public Todo(String stringInput, boolean isDoneInput){
        super(stringInput, isDoneInput);
    }

    /**
     * Returns Todo object as a String of data.
     */
    @Override
    public String toString(){
        if(getIsDone() == true){
            return String.format("[T][✓] %s", getData());
        }else{
            return String.format("[T][✗] %s", getData());
        }
    }
}
