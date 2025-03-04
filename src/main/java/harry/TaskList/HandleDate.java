package harry.TaskList;

import harry.Exceptions.InvalidDateException;
import harry.Exceptions.InvalidDateFormatException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class HandleDate {
    //convert string data into localDate object
    public static LocalDateTime parseDate(String date)
            throws DateTimeParseException,
            InvalidDateFormatException,
            InvalidDateException,
            NumberFormatException {
            String formattedDate = formatDate(date);
            //System.out.println(formattedDate);
            //formattedDate = date.substring(0, 10) + "T" + date.substring(10);
            return LocalDateTime.parse(formattedDate);
    }
    /* accepts dates of format:
    YYYY-MM-DD HH:MM
    YYYYMMDD HHMM
    DDMMYYYY HH:MM
    DD-MM-YYYY HHMM
    and like permutations of it,
    but the date MUST be in front and the time at the back,
    and they MUST be separated by one space

    output: YYYY-MM-DDTHH:MM
     */

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
            NumberFormatException{

        String formattedDate;
        int spaceIndex = date.indexOf(" ");
        if (spaceIndex < 8) { //either no space or the time is in front
            throw new InvalidDateFormatException();
        }

        //handle year first
        String [] yearParts = date.substring(0, spaceIndex).split("-");
        if (yearParts.length == 1) {
            //either YYYYMMDD or DDMMYYYY format
            String wholeDate = yearParts[0];
            if (wholeDate.charAt(4) >= '2') {
                //assume DDMMYYYY format
                if (Integer.parseInt(wholeDate.substring(0,2)) > 31 || Integer.parseInt(wholeDate.substring(2, 4)) > 12) {
                    throw new InvalidDateException();
                }
                formattedDate = wholeDate.substring(4) + "-" +
                        wholeDate.substring(2,4) +  "-" +
                        wholeDate.substring(0,2);
            } else {
                //YYYYMMDD format
                if (Integer.parseInt(wholeDate.substring(6, 8)) > 31 || Integer.parseInt(wholeDate.substring(4, 6)) > 12) {
                    throw new InvalidDateException();
                }
                formattedDate = wholeDate.substring(0,4) + "-" +
                        wholeDate.substring(4, 6) +  "-" +
                        wholeDate.substring(6);
            }
        } else {
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

    //prints out date in format
    public static void PrintDate(LocalDateTime date) {
        System.out.print(date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
    }
}
