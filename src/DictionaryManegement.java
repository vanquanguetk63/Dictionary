import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryManegement {
    Dictionary dictionary = new Dictionary();
    Scanner scanner = new Scanner(System.in);

    public DictionaryManegement() {

    }

    public void insertFromCommandline() {
        String word_target = scanner.nextLine();
        String word_explain = scanner.nextLine();
        Word word = new Word(word_target, word_explain);
        dictionary.addWordToDictionary(word);
    }

    public void insertFromFile() {
        try {
            File file = new File("src/Dictionary.txt");
            Scanner myReader = new Scanner(file);
            int index = 0;
            String data;
            String[] data2;
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                data2 = data.split("\t");
                Word word = new Word(data2[0], data2[1]);
                dictionary.addWordToDictionary(word);
            }
            myReader.close();
        }
        catch (Exception e) {
            System.out.println("Can't read file");
            e.printStackTrace();
        }
    }

    public Word dictionaryLookup() {
        String findWord = scanner.nextLine();
        Word word = new Word();
        for (Word foundWord : dictionary.getWordArrayList()) {
            String str = foundWord.getWord_target();
            str = str.toLowerCase();
            if (findWord.compareTo(str) == 0) {
                word.setWord_target(foundWord.getWord_target());
                word.setWord_explain(foundWord.getWord_explain());
                break;
            }
        }
        return word;
    }

    public static int isSubstring(String s1, String s2) {
        int M = s1.length();
        int N = s2.length();

        for (int i = 0; i <= N - M; i++) {
            int j;
            for (j = 0; j < M; j++)
                if (s2.charAt(i + j) != s1.charAt(j))
                    break;
            if (j == M)
                return 1;
        }
        return -1;
    }
}

