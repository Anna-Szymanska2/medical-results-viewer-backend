package pw.telm.telmbackend;


public class DataConverter {

    public static String convertDateFormat(String date) {
        if (date == null) return null;
        String year = date.substring(0,4);
        String month = date.substring(4,6);
        String day = date.substring(6,8);

        return year + "-" + month + "-" + day;
    }


    public static String convertTimeFormat(String time) {
        if (time == null) return null;
        String hours = time.substring(0, 2);
        String minutes = time.substring(2, 4);
        String seconds = time.substring(4, 6);

        return hours + ":" + minutes + ":" + seconds;
    }
}
