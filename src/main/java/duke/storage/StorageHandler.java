package duke.storage;

import duke.task.Task;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static duke.storage.DukeStorageDecoder.getList;

public class StorageHandler {
    public static String listPath;
    public static ArrayList<Task> listInputs = new ArrayList<>();

    public StorageHandler(String pathInput){
        // set up unique path for the future, for now just set at root
        String currentWorkingDir = System.getProperty("user.dir");
        listPath = currentWorkingDir + "/taskList.txt";

        getList();
    }

    public static void createNewFile(){
        try{
            File newFile = new File(listPath);
            if(newFile.createNewFile() == true){
                //System.out.println("A new file created");
            }
        }catch(IOException e) {
            System.out.println("An error occurred.");
            System.out.println("listPath: " + listPath);
        }
    }

}
