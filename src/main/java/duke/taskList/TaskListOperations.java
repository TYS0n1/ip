package duke.taskList;

import duke.common.Messages;
import duke.task.Task;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import duke.ui.UserInterface;
import duke.storage.StorageHandler;

import java.io.IOException;
import java.util.ArrayList;

import static duke.storage.StorageHandler.listInputs;
import static duke.storage.DukeStorageEncoder.writeListToFile;
import static duke.ui.UserInterface.isLoading;
import static duke.ui.UserInterface.isPrintMessageEnabled;

/**
 * Holds the methods for adding, removing, checking as done and saving
 * tasks in Duke Program.
 */
public class TaskListOperations {
    /**
     * Marks a task as done and saves changes into the txt file.
     *
     * @param input a command the user entered into console.
     */
    public static void setTaskDone(String input) {
        if (listInputs.size() == 0) {
            UserInterface.printMessage(" List is empty");
            return;
        } else if (input.length() == 5) {
            UserInterface.printMessage(Messages.EMPTY_DONE_INPUT);
            return;
        }

        try {
            int taskNumber = Integer.parseInt(input.substring(5, input.length())) - 1;
            if (taskNumber < listInputs.size() && taskNumber >= 0) {
                listInputs.get(taskNumber).setIsDone(true);
                UserInterface.printDoneStatement(listInputs.get(taskNumber));
                saveTasks();
            } else {
                UserInterface.printMessage(" Invalid task number\n " +
                        "done " + Messages.VALID_INDEX_RANGE);
            }
        } catch (NumberFormatException e) {
            UserInterface.printMessage(" Invalid index for done operation\n " +
                    "done " + Messages.VALID_INDEX_RANGE);
        }
    }

    /**
     * Creates a Todo Task, adds it to the current list of Tasks
     * and saves data into the txt file.
     *
     * @param input a command the user entered into console.
     */
    public static void createTodo(String input) {
        if (input.length() == 5) {
            UserInterface.printMessage(Messages.EMPTY_TODO_INPUT);
            return;
        }

        int indexAdded = listInputs.size();
        String todoData = input.substring(5, input.length());
        listInputs.add(new Todo(todoData, false));
        UserInterface.printAddedTaskMessage(indexAdded);
        saveTasks();
    }

    /**
     * Creates a Deadline Task, adds it to the current list of Tasks
     * and saves data into the txt file.
     *
     * @param input a command the user entered into console.
     */
    public static void createDeadline(String input) {
        if (input.length() == 9) {
            UserInterface.printMessage(Messages.EMPTY_DEADLINE_INPUT);
            return;
        }

        input = input.substring(9, input.length());
        if (input.startsWith("/by") == true || input.contains(" /by ") == false) {
            UserInterface.printMessage(Messages.DEADLINE_FORMAT);
            return;
        }

        int indexAdded = listInputs.size();
        int separatorIndex = input.indexOf(" /by ");
        String data = input.substring(0, separatorIndex);
        String date = input.substring(separatorIndex + 5, input.length());
        String[] dateArray = new String[2];
            dateArray = date.split(" ", 2);

        LocalDate formattedDate;
        LocalTime formattedTime;

        try {
            formattedDate = formatDate(dateArray[0]);
            formattedTime = formatTime(dateArray[1]);
        } catch (ArrayIndexOutOfBoundsException e){
            UserInterface.printMessage(Messages.DEADLINE_FORMAT);
            return;
        }

        if (formattedDate == null || formattedTime == null) {
            UserInterface.printMessage(Messages.DEADLINE_FORMAT);
            return;
        }

        listInputs.add(new Deadline(data, false, formattedDate, formattedTime));
        UserInterface.printAddedTaskMessage(indexAdded);
        saveTasks();
    }

    /**
     * Creates a Event Task, adds it to the current list of Tasks
     * and saves data into the txt file.
     *
     * @param input a command the user entered into console.
     */
    public static void createEvent(String input) {
        if (input.length() == 6) {
            UserInterface.printMessage(Messages.EMPTY_EVENT_INPUT);
            return;
        }

        input = input.substring(6, input.length());
        if (input.startsWith("/at") == true || input.contains(" /at ") == false) {
            UserInterface.printMessage(Messages.EVENT_FORMAT);
            return;
        }

        int indexAdded = listInputs.size();
        int separatorIndex = input.indexOf(" /at ");
        String data = input.substring(0, separatorIndex);
        String date = input.substring(separatorIndex + 5, input.length());
        String[] dateArray = new String[2];
        dateArray = date.split(" ", 2);

        LocalDate formattedDate;
        LocalTime formattedTime;

        try {
            formattedDate = formatDate(dateArray[0]);
            formattedTime = formatTime(dateArray[1]);
        } catch (ArrayIndexOutOfBoundsException e){
            UserInterface.printMessage(Messages.DEADLINE_FORMAT);
            return;
        }

        if (formattedDate == null || formattedTime == null) {
            UserInterface.printMessage(Messages.DEADLINE_FORMAT);
            return;
        }

        listInputs.add(new Event(data, false, formattedDate, formattedTime));
        UserInterface.printAddedTaskMessage(indexAdded);
        saveTasks();
    }

    /**
     * Saves the current list of Tasks into a txt file.
     */
    public static void saveTasks() {
        if (isLoading == true) {
            return;
        }

        isPrintMessageEnabled = false;
        try {
            writeListToFile();
        } catch (IOException e) {
            StorageHandler.createNewFile();
            saveTasks();
        } finally {
            isPrintMessageEnabled = true;
        }
    }

    /**
     * Deletes a Task from the current list of Tasks
     * and saves changes into the txt file.
     *
     * @param input a command the user entered into console.
     */
    public static void deleteTask(String input) {
        if (listInputs.size() <= 0) {
            UserInterface.printMessage(" List is empty");
            return;
        } else if (input.length() == 7) {
            UserInterface.printMessage(Messages.EMPTY_DELETE_INPUT);
            return;
        }

        try {
            int taskNumber = Integer.parseInt(input.substring(7, input.length())) - 1;
            if (taskNumber < listInputs.size() && taskNumber >= 0) {
                String taskData = listInputs.get(taskNumber).toString();
                listInputs.remove(taskNumber);
                UserInterface.printDeleteStatement(taskData);
                saveTasks();
            } else {
                UserInterface.printMessage(" Invalid task number\n " +
                        "delete " + Messages.VALID_INDEX_RANGE);
            }
        } catch (NumberFormatException e) {
            UserInterface.printMessage(" Invalid index for delete operation\n " +
                    "delete " + Messages.VALID_INDEX_RANGE);
        }
    }

    /**
     * Finds Tasks from the current list of Tasks that has the same
     * due date as the input time from the user. Then it prints out
     * the Tasks found.
     *
     * @param input a command the user entered into console.
     */
    public static void occuranceTask(String input){
        if (input.length() <= 6) {
            UserInterface.printMessage(Messages.EMPTY_OCCUR_INPUT);
            return;
        }

        input = input.substring(6, input.length());
        LocalDate targetDate = formatDate(input);
        ArrayList<Deadline> tasksOnTargetDate = new ArrayList<>();

        if (targetDate == null) {
            UserInterface.printMessage(Messages.OCCUR_FORMAT);
            return;
        }

        for (int i = 0; i < listInputs.size(); i++) {
            if (listInputs.get(i) instanceof Deadline &&
                    ((Deadline) listInputs.get(i)).getDateDue().equals(targetDate)) {
                tasksOnTargetDate.add((Deadline) listInputs.get(i));
            }
        }

        UserInterface.printOccurences(tasksOnTargetDate, targetDate);
    }

    /**
     * Reformats a String data of date into a LocalDate variable.
     *
     * @param dateInput String data to be reformatted into a LocalDate.
     * @return a LocalDate variable of the initial input String.
     * @throws DateTimeParseException if an invalid String of date is given.
     */
    public static LocalDate formatDate(String dateInput) {
        String[] dateStringArray = new String[3];
        LocalDate formattedDate;

        if (isLoading == true) {
            dateStringArray[0] = dateInput.substring(8,10);
            dateStringArray[1] = dateInput.substring(5,7);
            dateStringArray[2] = dateInput.substring(0,4);
        } else if (dateInput.contains("-")) {
            dateStringArray = formatDateUsingSeparator(dateInput, "-");
        } else if (dateInput.contains("/")) {
            dateStringArray = formatDateUsingSeparator(dateInput, "/");
        } else {
            return null;
        }
        dateInput = dateStringArray[2] + "-" + dateStringArray[1] + "-" + dateStringArray[0];

        try {
            formattedDate = LocalDate.parse(dateInput);
        } catch (DateTimeParseException e){
            return null;
        }

        return formattedDate;
    }

    /**
     * Separates a String data of time into Year, Month and Day.
     *
     * @param dateInput String data to be separated into Year, Month and Day.
     * @param separator the type of separator used in dateInput.
     * @return a String array containing the Year, Month and Day of the input date.
     * @throws NumberFormatException if an invalid String of date is given.
     */
    public static String[] formatDateUsingSeparator(String dateInput, String separator) {
        String[] dateString = new String[3];
        int[] dateInt = new int[3];
        int indexSeparator;

        for (int i = 0; i < 2; i ++) {
            indexSeparator = dateInput.indexOf(separator);
            if(indexSeparator == -1){
                return null;
            }

            try {
                if (i == 0) {
                    dateString[0] = dateInput.substring(0, indexSeparator);
                    dateInt[0] = Integer.parseInt(dateString[0]);
                    if(dateInt[0] < 0 && dateInt[0] > 31){
                        return null;
                    }else if(dateInt[0] > 0 && dateInt[0] < 10){
                        dateString[0] = "0" + dateString[0];
                    }
                    dateInput = dateInput.substring(indexSeparator + 1, dateInput.length());
                } else {
                    dateString[1] = dateInput.substring(0, indexSeparator);
                    dateInt[1] = Integer.parseInt(dateString[1]);
                    if (dateInt[1] < 0 && dateInt[1] > 12) {
                        return null;
                    } else if (dateInt[1] > 0 && dateInt[1] < 10){
                        dateString[1] = "0" + dateString[1];
                    }

                    dateString[2] = dateInput.substring(indexSeparator + 1, dateInput.length());
                    dateInt[2] = Integer.parseInt(dateString[2]);
                    if (dateInt[2] < 0 && dateInt[2] > 9999) {
                        return null;
                    } else if (dateInt[2] > 0 && dateInt[2] < 10){
                        dateString[2] = "000" + dateString[2];
                    } else if (dateInt[2] >= 10 && dateInt[2] < 100){
                        dateString[2] = "00" + dateString[2];
                    } else if (dateInt[2] >= 100 && dateInt[2] < 1000){
                        dateString[2] = "0" + dateString[2];
                    }
                }
            } catch (NumberFormatException e){
                return null;
            }

        }

        return dateString;
    }

    /**
     * Converts a String data of time into a LocalTime variable.
     *
     * @param inputTime String data to be converted to LocalTime.
     * @throws StringIndexOutOfBoundsException if an invalid String of time is given.
     * @throws DateTimeParseException if an invalid String of time is given.
     * @throws NumberFormatException if an invalid String of time is given.
     */
    public static LocalTime formatTime(String inputTime) {
        try {
            String timeHour = inputTime.substring(0,2);
            String timeMin = inputTime.substring(3,5);
            int intHour = Integer.parseInt(timeHour);
            int intMin = Integer.parseInt(timeMin);
            if (intHour < 0 && intHour >= 24) {
                return null;
            } else if (intMin < 0 && intMin > 59){
                return null;
            }

            return LocalTime.parse(timeHour + ":" + timeMin);
        } catch (StringIndexOutOfBoundsException e){
            return null;
        } catch (DateTimeParseException e){
            return null;
        } catch (NumberFormatException e){
            return null;
        }
    }

    /**
     * Finds Tasks from the current list of Tasks that contains the same
     * String as the input time from the user. Then it prints out
     * the Tasks found.
     *
     * @param input a command the user entered into console.
     */
    public static void findTask(String input) {
        if (listInputs.size() == 0) {
            UserInterface.printMessage(" List is empty");
            return;
        } else if (input.length() == 5) {
            UserInterface.printMessage(Messages.EMPTY_FIND_INPUT);
            return;
        }

        String searchPhrase = input.substring(5, input.length());
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (int i = 0; i < listInputs.size(); i++) {
            if (listInputs.get(i).toString().contains(searchPhrase)) {
                matchingTasks.add(listInputs.get(i));
            }
        }

        UserInterface.printSearchedTasks(matchingTasks);
    }
}
