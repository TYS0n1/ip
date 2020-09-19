package duke.storage;

import java.io.File;
import java.io.IOException;
import static duke.Duke.listPath;

public class StorageHandler {
    public static void createNewFile(String filePath){
        try{
            File newFile = new File(filePath);
            if(newFile.createNewFile() == true){
                //System.out.println("A new file created");
            }
        }catch(IOException e) {
            System.out.println("An error occurred.");
            System.out.println("listPath: " + listPath);
        }
    }






}
