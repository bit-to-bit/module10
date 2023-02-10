import com.google.gson.Gson;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class UsersToJson {

    /**
     * Метод конвертує користувачів із текстового файлу у файл формату json заданого формату
     *
     * @param  inTxtFilePath абсолютний або відносний шлях до вихідного файлу TXT
     * @param  outJsonFilePath абсолютний або відносний шлях до результативного файлу JSON
     */
    public static void convertUsersToJson(String inTxtFilePath, String outJsonFilePath) {

        List<UsersDto> users = new ArrayList<>();

        try (InputStream fis = new FileInputStream(inTxtFilePath);
             Scanner scanner = new Scanner(fis);) {

            String line = scanner.hasNext() ? scanner.nextLine() : " ";

            while (scanner.hasNext()) {
                line = scanner.nextLine();
                users.add(new UsersDto(line.split(" ")[0], Integer.valueOf(line.split(" ")[1])));
            }

            Gson gson = new Gson();

            FileWriter fileWriter = new FileWriter(outJsonFilePath);
            gson.toJson(users, fileWriter);
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
