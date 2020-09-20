import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryCommandline {
    Scanner scanner = new Scanner(System.in);
    DictionaryManegement dictionaryManegement = new DictionaryManegement();

    public DictionaryCommandline() {
        dictionaryManegement.insertFromFile();
        DictionaryBasic();
    }

    public void DictionaryBasic() {
        boolean createLoop = true;
        int state;
        while (createLoop) {
            System.out.println("Vui long nhap trang thai ban muon su dung");
            state = scanner.nextInt();
            switch (state){
                case 1:
                    System.out.println("Nhap vao tu muon tim");
                    Word word = dictionaryManegement.dictionaryLookup();
                    String format = "|%1$-10s|%2$-20s|\n";
                    System.out.format(format, "English", "VietNamese");
                    System.out.format(format, word.getWord_target(), word.getWord_explain());
                    break;
                case 2:
                    showAllWords();
                    break;
                case 3:
                    createLoop = false;
                    break;
                default:
                    break;
            }

        }
    }

    public void showAllWords() {
        ArrayList<Word> words = dictionaryManegement.dictionary.getWordArrayList();
        int index = 1;
        String format = "|%1$-10s|%2$-20s|%3$-20s|\n";
        System.out.format(format, "STT", "English", "VietNamese");
        for (Word word : words) {
            System.out.format(format, index, word.getWord_target(), word.getWord_explain());
            index++;
        }
    }

    public static void main(String[] args) {
        System.out.println("Bang dieu khien");
        System.out.println("1. Tim tu");
        System.out.println("2. Mo tu dien");
        System.out.println("3. Dong");
        DictionaryCommandline dictionaryCommandline = new DictionaryCommandline();
    }
}
