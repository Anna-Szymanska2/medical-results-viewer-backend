package pw.telm.telmbackend;

/**
 * Utility class for converting date and time strings between different formats.
 */
public class DataConverter {
    /**
     * Converts a date string from "YYYYMMDD" format to "YYYY-MM-DD" format.
     *
     * @param date The date string in "YYYYMMDD" format.
     * @return The date string in "YYYY-MM-DD" format, or null if input is null.
     * @throws IllegalArgumentException If the input date string length is not 8.
     */
    public static String convertDateFormat(String date) {
        if (date == null) return null;
        String year = date.substring(0,4);
        String month = date.substring(4,6);
        String day = date.substring(6,8);

        return year + "-" + month + "-" + day;
    }
    /**
     * Converts a time string from "HHMMSS" format to "HH:MM:SS" format.
     *
     * @param time The time string in "HHMMSS" format.
     * @return The time string in "HH:MM:SS" format, or null if input is null.
     * @throws IllegalArgumentException If the input time string length is not 6.
     */

    public static String convertTimeFormat(String time) {
        if (time == null) return null;
        String hours = time.substring(0, 2);
        String minutes = time.substring(2, 4);
        String seconds = time.substring(4, 6);

        return hours + ":" + minutes + ":" + seconds;
    }
}
