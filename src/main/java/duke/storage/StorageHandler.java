package duke.storage;

import duke.task.Task;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static duke.storage.DukeStorageDecoder.getList;

/**
 * Holds the methods and global variables required for setting up file I/O
 * functions of Duke program.
 */
public class StorageHandler {
    /**
     * Global variable listPath is the directory path of the txt file
     * which the Task ArrayList data will be retrieved and stored to.
     * Global variable listInputs is the current Task ArrayList stored in the program.
     */
    public static String listPath;
    public static ArrayList<Task> listInputs = new ArrayList<>();

    /**
     * Creates a directory path of the txt file which the Task ArrayList data
     * will be retrieved and stored to. Then retrieve Tasks from that txt file.
     *
     * @param pathInput a path String provided by the user which would be the
     * directory path of the txt file which the Task ArrayList data
     * will be retrieved and stored to if valid.
     */
    public StorageHandler(String pathInput){
        // set up unique path for the future, for now just set at root
        String currentWorkingDir = System.getProperty("user.dir");
        listPath = currentWorkingDir + "/taskList.txt";

        getList();
    }

    /**
     * Creates a new txt file at the directory given by listPAth variable.
     */
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
