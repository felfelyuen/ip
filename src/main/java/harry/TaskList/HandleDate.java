package harry.TaskList;

import harry.Exceptions.InvalidDateException;
import harry.Exceptions.InvalidDateFormatException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class that contains methods to parse input dates, and to format dates to be printed.
 */
public class HandleDate {

    /**
     * Parses date input into a string to be parsed into a LocalDateTime object.
     * @param date inputted date
     * @return LocalDateTime object of that date
     * @throws DateTimeParseException If the date cannot be parsed for some reason
     * @throws InvalidDateFormatException If the date format is invalid or unaccounted for, ie: DD/MM/YYYY is not accounted for.
     * @throws InvalidDateException If the date is invalid, ie: month == 16
     * @throws NumberFormatException If there are non-numbers inputted for the date.
     */
    public static LocalDateTime parseDate(String date)
            throws DateTimeParseException,
            InvalidDateFormatException,
            InvalidDateException,
            NumberFormatException {
        String formattedDate = formatDate(date);
        return LocalDateTime.parse(formattedDate);
    }

    /**
     * accepts dates of format:
     *     YYYY-MM-DD or YYYYMMDD or DDMMYYYY DD-MM-YYYY
     *     and HH:MM or HHMM
     *     but the date MUST be in front and the time at the back,
     *     and they MUST be separated by one space
     *     output: YYYY-MM-DDTHH:MM
     * @param date date to be formatted
     * @return formattedDate
     * @throws InvalidDateFormatException If the format of the date is not acceptable
     * @throws InvalidDateException If the date is not a valid date, ie: month = 14
     * @throws NumberFormatException If the date is not acceptable format
     */
    public static String formatDate(String date)
            throws InvalidDateFormatException,
            InvalidDateException,
            NumberFormatException {
        String formattedDate;
        int spaceIndex = date.indexOf(" ");
        if (spaceIndex < 8) { //either no space or the time is in front
            throw new InvalidDateFormatException();
        }
        String [] yearParts = date.substring(0, spaceIndex).split("-");
        if (yearParts.length == 1) {
            formattedDate = formatShortYear(yearParts[0]);
        } else {
            formattedDate = formatYearWDashes(yearParts);
        }
        formattedDate += "T";
        String [] timeParts = date.substring(spaceIndex + 1).split(":");
        if (timeParts.length == 2) {
            //in HH:MM format
            if (Integer.parseInt(timeParts[0]) > 23 || Integer.parseInt(timeParts[1]) > 59) {
                throw new InvalidDateException();
            }
            formattedDate += timeParts[0] + ":" + timeParts[1];
        } else {
            //in HHMM format
            String timing = timeParts[0];
            if (Integer.parseInt(timing.substring(0, 2)) > 23 || Integer.parseInt(timing.substring(2, 4)) > 59) {
                throw new InvalidDateException();
            }
            formattedDate += timing.substring(0, 2) + ":" + timing.substring(2, 4);
        }
        return formattedDate;
    }

    /**
     * formats an input of DDMMYYYY/YYYYMMDD into YYYY-MM-DD
     * @param wholeDate data input
     * @return String of YYYY-MM-DD format
     * @throws InvalidDateException If the date is invalid, ie month == 16
     */
    public static String formatShortYear(String wholeDate) throws InvalidDateException {
        //either YYYYMMDD or DDMMYYYY format
        String formattedDate;
        if (wholeDate.charAt(4) >= '2') {
            //assume DDMMYYYY format
            if (Integer.parseInt(wholeDate.substring(0, 2)) > 31 || Integer.parseInt(wholeDate.substring(2, 4)) > 12) {
                throw new InvalidDateException();
            }
            formattedDate = wholeDate.substring(4) + "-" +
                    wholeDate.substring(2, 4) + "-" +
                    wholeDate.substring(0, 2);
        } else {
            //YYYYMMDD format
            if (Integer.parseInt(wholeDate.substring(6, 8)) > 31 || Integer.parseInt(wholeDate.substring(4, 6)) > 12) {
                throw new InvalidDateException();
            }
            formattedDate = wholeDate.substring(0, 4) + "-" +
                    wholeDate.substring(4, 6) + "-" +
                    wholeDate.substring(6);
        }
        return formattedDate;
    }

    /**
     * formats date from string of YYYY, MM, DD into YYYY-MM-DD format
     * @param yearParts String array of YYYY, MM, DD
     * @return String of YYYY-MM-DD format
     * @throws InvalidDateException If date is invalid, ie month == 16
     */
    public static String formatYearWDashes (String [] yearParts) throws InvalidDateException {
        String formattedDate;
        //either YYYY-MM-DD format or DD-MM-YYYY format
        if (yearParts[0].length() == 4) {
            //YYYY-MM-DD format
            if (Integer.parseInt(yearParts[2]) > 31 || Integer.parseInt(yearParts[1]) > 12) {
                throw new InvalidDateException();
            }
            formattedDate = yearParts[0] + "-" + yearParts[1] + "-" + yearParts[2];
        } else {
            //DD-MM-YYYY format
            if (Integer.parseInt(yearParts[0]) > 31 || Integer.parseInt(yearParts[1]) > 12) {
                throw new InvalidDateException();
            }
            formattedDate = yearParts[2] + "-" + yearParts[1] + "-" + yearParts[0];
        }
        return formattedDate;
    }

    public static void PrintDate(LocalDateTime date) {
        System.out.print(date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
    }
}
