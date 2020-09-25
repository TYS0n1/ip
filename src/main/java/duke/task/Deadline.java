package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Holds the methods and variables required to store and transmit
 * information of Deadline Tasks added to Duke program.
 */
public class Deadline extends Task {
    private LocalDate dateDue;
    private LocalTime timeDue;

    /**
     * Constructor. Sets Deadline Task variables to respective states.
     *
     * @param stringInput a String input containing data about the Deadline Task.
     * @param isDoneInput a Boolean input showing whether the Deadline Task is done or not.
     * @param dateInput a String input showing the time the Deadline Task is due.
     */
    public Deadline(String stringInput, boolean isDoneInput, LocalDate dateInput, LocalTime timeInput) {
        super(stringInput, isDoneInput);
        dateDue = dateInput;
        timeDue = timeInput;
    }

    /**
     * Returns dateDue variable.
     */
    public LocalDate getDateDue() {
        return dateDue;
    }

    public LocalTime getTimeDue() {
        return timeDue;
    }

    /**
     * Returns Deadline object as a String of data.
     *
     * @return String containing information of Deadline object.
     */
    @Override
    public String toString() {
        String doneCharacter;

        if (getIsDone() == true) {
            doneCharacter = "1";
        } else {
            doneCharacter = "0";
        }

        return String.format("[D][%s] %s (by: ", doneCharacter, getData()) +
                dateDue.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + timeDue.toString() + ")";
    }
}