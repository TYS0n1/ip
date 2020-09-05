package duke.task;
import duke.task.Task;

public class Todo extends Task {
    public Todo(String stringInput, boolean isDoneInput){
        super(stringInput, isDoneInput);
    }

    @Override
    public String toString(){
        if(getIsDone() == true){
            return String.format("[T][✓] %s", getData());
        }else{
            return String.format("[T][✗] %s", getData());
        }
    }
}
