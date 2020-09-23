package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Holds the methods and variables required to store and transmit
 * information of Event Tasks added to Duke program.
 */
public class Event extends Deadline {
    /**
     * Constructor. Sets Event Task variables to respective states.
     *
     * @param stringInput a String input containing data about the Event Task.
     * @param isDoneInput a Boolean input showing whether the Event Task is done or not.
     * @param dateInput a String input showing the time the Event Task is due.
     */
    public Event(String stringInput, boolean isDoneInput, LocalDate dateInput, LocalTime timeInput) {
        super(stringInput, isDoneInput, dateInput, timeInput);
    }

    /**
     * Returns Deadline object as a String of data.
     *
     * @return String containing information of Event object.
     */
    @Override
    public String toString() {
        String doneAsciiArt;

        if (getIsDone() == true) {
            doneAsciiArt = "✓";
        } else {
            doneAsciiArt = "✗";
        }

        return String.format("[E][%s] %s (at: ", doneAsciiArt, getData()) +
                getDateDue().format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " "  + getTimeDue() + ")";
    }
}