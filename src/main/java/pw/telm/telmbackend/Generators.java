package pw.telm.telmbackend;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Random;

public class Generators {
    public static Integer generateLogin(){
        Random random = new Random();
        // Generujemy losową liczbę całkowitą w przedziale od 10000000 do 99999999
        return 10000000 + random.nextInt(90000000);
    }

    public static String generatePesel(Date birthDate, char gender) {
        if (birthDate == null || (gender != 'M' && gender != 'F')) {
            throw new IllegalArgumentException("Niepoprawne dane wejściowe");
        }

        LocalDate date = birthDate.toLocalDate();
        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();

        // Zakodowanie wieku i miesiąca do PESEL (dla lat po 2000 trzeba dodać 20 do miesiąca)
        if (year >= 2000) {
            month += 20;
        }

        // Pierwsze 6 cyfr PESEL - data urodzenia
        String yearPart = String.format("%02d", year % 100); // ostatnie dwie cyfry roku
        String monthPart = String.format("%02d", month);
        String dayPart = String.format("%02d", day);

        // Kolejne 3 cyfry - losowy numer seryjny
        Random random = new Random();
        int serialNumber = random.nextInt(1000); // losowa liczba od 000 do 999
        String serialPart = String.format("%03d", serialNumber);

        // Dziesiąta cyfra - płeć (parzysta dla F, nieparzysta dla M)
        int genderDigit = random.nextInt(5) * 2; // generuje parzystą cyfrę (0, 2, 4, 6, 8)
        if (gender == 'M') {
            genderDigit += 1; // zamienia na nieparzystą cyfrę (1, 3, 5, 7, 9)
        }

        // Łączenie wszystkich części przed obliczeniem sumy kontrolnej
        String peselWithoutChecksum = yearPart + monthPart + dayPart + serialPart + genderDigit;

        // Obliczenie ostatniej cyfry (sumy kontrolnej)
        int checksum = calculateChecksum(peselWithoutChecksum);

        // Zwracanie pełnego PESEL
        return peselWithoutChecksum + checksum;
    }

    private static int calculateChecksum(String peselWithoutChecksum) {
        int[] weights = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};
        int sum = 0;
        for (int i = 0; i < weights.length; i++) {
            sum += Character.getNumericValue(peselWithoutChecksum.charAt(i)) * weights[i];
        }
        int checksum = (10 - (sum % 10)) % 10; // Cyfra kontrolna
        return checksum;
    }

    public static void main(String[] args) {
        // Przykładowe użycie funkcji
        Date birthDate = Date.valueOf("2002-05-15"); // data urodzenia
        char gender = 'M'; // płeć: M - mężczyzna, F - kobieta

        String pesel = generatePesel(birthDate, gender);
        System.out.println("Wygenerowany PESEL: " + pesel);
    }
}
