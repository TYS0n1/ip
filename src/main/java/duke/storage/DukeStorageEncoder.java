package duke.storage;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import static duke.Duke.*;

public class DukeStorageEncoder {
    public static void writeListToFile(String filePath) throws IOException {
        Task task = listInputs.get(0);
        String formatedTaskData;
        formatedTaskData = formatTaskForTxt(task);
        FileWriter writer = new FileWriter(filePath);
        writer.write(formatedTaskData); //Override existing file
        writer.close();

        for (int i = 1; i < listInputs.size(); i++) {
            task = listInputs.get(i);
            formatedTaskData = formatTaskForTxt(task);
            appendToFile(filePath, formatedTaskData);
        }
    }

    public static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter writer = new FileWriter(filePath, true);
        writer.write(textToAppend);
        writer.close();
    }

    public static String formatTaskForTxt(Task task){
        String outputString;
        String taskData = task.getData();
        boolean isTaskDone = task.getIsDone();
        String taskDoneString;
        if(isTaskDone){
            taskDoneString = "1";
        }else{
            taskDoneString = "0";
        }

        if(task instanceof Event) {
            outputString = "E | " + taskDoneString + " | " + taskData
                    + " /at " + ((Event) task).getDateDue() + "\n";
        }else if(task instanceof Deadline) {
            outputString = "D | " + taskDoneString + " | " + taskData +
                    " /by " + ((Deadline) task).getDateDue() + "\n";
        }else if(task instanceof Todo){
            outputString = "T | " + taskDoneString  + " | " + taskData + "\n";
        }else{
            outputString = "? | " + taskDoneString  + " | " + taskData + "\n";
        }

        return outputString;
    }
}
