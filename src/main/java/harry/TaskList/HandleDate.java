package harry.TaskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class HandleDate {
    //convert string data into localDate object
    public static LocalDateTime parseDate(String date) throws DateTimeParseException, StringIndexOutOfBoundsException{
            String formattedDate;
            formattedDate = date.substring(0, 10) + "T" + date.substring(10);
            return LocalDateTime.parse(formattedDate);
    }

    //prints out date in format
    public static void PrintDate(LocalDateTime date) {
        System.out.print(date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
    }
}
