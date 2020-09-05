package duke.task;

public class Task{
    private String data;
    private boolean isDone;
    private int taskNumber;
    private static int numberOfTasks = 0;

    public Task(String stringInput, boolean isDoneInput){
        data = stringInput;
        isDone = isDoneInput;
        numberOfTasks++;
        taskNumber = numberOfTasks;
    }

    public void setIsDone(boolean isDoneInput){
        isDone = isDoneInput;
    }

    public boolean getIsDone(){
        return isDone;
    }

    public String getData(){
        return data;
    }

    public int getTaskNumber(){
        return taskNumber;
    }

    public String toString(){
        if(isDone == true){
            return String.format("[✓] %s", data);
        }else{
            return String.format("[✗] %s", data);
        }
    }
}
