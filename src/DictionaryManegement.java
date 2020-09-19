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
}

