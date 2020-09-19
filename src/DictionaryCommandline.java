import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryCommandline {
    Scanner scanner = new Scanner(System.in);
    DictionaryManegement dictionaryManegement = new DictionaryManegement();

    public DictionaryCommandline() {
        DictionaryBasic();
    }

    public void showAllWords() {
        ArrayList<Word> words = dictionaryManegement.dictionary.getWordArrayList();
        int index = 1;
        System.out.println("STT  " + "English   " + "VietNamese" );
        for (Word word : words) {
            System.out.println(index + " " + word.getWord_target() + " " + word.getWord_explain());
            index++;
        }
    }

    public void DictionaryBasic() {
        
    }



}
