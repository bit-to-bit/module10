import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class WordsCounter {

    private WordsCounter() {
    }

    /**
     * Метод зчитує файл, та підраховує кількість кожного слова у тексті, що міститться у ньому
     *
     * @param  filePath абсолютний або відносний шлях до файлу, який потрібно проаналізувати
     * @return текст, що містить інформацію про частоту уживання слова
     */
    public static String countWords(String filePath) {

        String result = "";
        Map<String, Integer> wordBook = new HashMap<>();

        try (InputStream fis = new FileInputStream(filePath); Scanner scanner = new Scanner(fis);) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine().trim().replaceAll(" +", " ").toLowerCase();
                String[] wordsArray = line.split(" ");
                for (String word : wordsArray) {
                    if (wordBook.containsKey(word)) {
                        int thisWordCount = wordBook.get(word);
                        wordBook.put(word, ++thisWordCount);
                    } else {
                        wordBook.put(word, 1);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, Integer> sortedWordBook = sortMapByValue(wordBook);
        result = formatMapForConsole(sortedWordBook);
        return result;
    }

    /**
     * Метод, що дозволяє відсортувати Map<K,V> за значенням V у спадаючому порядку
     *
     * @param map мапа Map<K,V>
     * @param <K> key
     * @param <V> value
     * @return Map<K, V> відсортована у спадаючому порядку за значенням
     */
    private static <K, V extends Comparable<V>> Map<K, V> sortMapByValue(Map<K, V> map) {

        Comparator<K> comparator = new Comparator<K>() {
            @Override
            public int compare(K k1, K k2) {
                int res = map.get(k2).compareTo(map.get(k1));
                return res != 0 ? res : 1;
            }
        };
        Map<K, V> sortedMap = new TreeMap<K, V>(comparator);
        sortedMap.putAll(map);
        return sortedMap;
    }

    /**
     * Метод, що форматує Map для зручного виводу у консоль
     *
     * @param map мапа Map<K,V>
     * @param <K> key
     * @param <V> value
     * @return String з усіма елементами Map у зручному вигляді для виводу у консоль
     */
    private static <K, V> String formatMapForConsole(Map<K, V> map) {

        StringBuilder result = new StringBuilder();
        Set<Map.Entry<K, V>> entrySet = map.entrySet();
        for (Map.Entry<K, V> currentEntry : entrySet) {
            result.append(currentEntry.getValue()).append("\t->\t").append(currentEntry.getKey()).append("\n");
        }
        return result.toString();
    }
}
