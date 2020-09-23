package duke;

import java.util.Scanner;
import duke.storage.StorageHandler;
import duke.ui.UserInterface;

import static duke.parser.Parser.parseCommand;

/**
 * Main class for the Duke Program. Initializes peripheral class during
 * setup. Then runs program.
 */
public class Duke {
    private StorageHandler storageHandler;
    private UserInterface ui;

    /**
     * Constructor. Initialize storageHandler and ui classes.
     */
    public Duke(String filePath){
        storageHandler = new StorageHandler(filePath);
        ui = new UserInterface();
    }

    /**
     * Runs the program in a loop until exit code is executed.
     */
    public void run(){
        String input = "empty";
        Scanner in = new Scanner(System.in);
        int isRunning = 1;


        while(isRunning == 1){
            input = in.nextLine();
            isRunning = parseCommand(input);
        }
    }

    /**
     * Starts the Duke program.
     *
     * @param args command prompt input.
     */
    public static void main(String[] args) {
        new Duke(null).run();
    }
}
