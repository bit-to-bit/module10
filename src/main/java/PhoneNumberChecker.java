import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberChecker {

    private PhoneNumberChecker(){}

    /**
     * Метод послідовно перевіряє усі рядки у файлі, і якщо рядок є корректним
     * номером телефону, то виводить цей номер у консоль.
     *
     * @param  filePath - абсолютний або відносний шлях до файлу, який потрібно проаналізувати
     */
    public static void checkFile(String filePath) {
        try (InputStream fis = new FileInputStream(filePath);
             Scanner scanner = new Scanner(fis);) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if (isCorrectPhoneNumber(line)) {
                    System.out.println(line);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            }
    }

    /**
     * Метод перевіряє рядок, чи є він корректним номером телефону у форматі
     * "(xxx) xxx-xxxx" або "xxx-xxx-xxxx", та повертає результат перевірки true або false
     *
     * @param  phoneNumber абсолютний або відносний шлях до файлу, який потрібно проаналізувати
     * @return результат перевірки у форматі boolean
     */
    private static boolean isCorrectPhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile("(^\\(\\d{3}\\)\\s\\d{3}-\\d{4}$)|(^\\d{3}-\\d{3}-\\d{4}$)");
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
}
