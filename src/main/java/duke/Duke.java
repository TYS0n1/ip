package duke;

import java.util.Scanner;
import duke.storage.StorageHandler;
import duke.ui.UserInterface;

import static duke.parser.Parser.parseCommand;

public class Duke {
    private StorageHandler storageHandler;
    private UserInterface ui;

    public Duke(String filePath){
        storageHandler = new StorageHandler(filePath);
        ui = new UserInterface();
    }

    public void run(){
        String input = "empty";
        Scanner in = new Scanner(System.in);
        int isRunning = 1;


        while(isRunning == 1){
            input = in.nextLine();
            isRunning = parseCommand(input);
        }
    }

    public static void main(String[] args) {
        new Duke(null).run();
    }
}
