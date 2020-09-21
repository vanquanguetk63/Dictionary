import java.util.ArrayList;
import java.util.Scanner;
import java.util.zip.DeflaterInputStream;

public class DictionaryCommandline {
    Scanner scanner = new Scanner(System.in);
    public static DictionaryManegement dictionaryManegement = new DictionaryManegement();

    public DictionaryCommandline() {

    }

    public void DictionaryBasic() {
        boolean createLoop = true;
        int state;
        while (createLoop) {
            System.out.println("Vui long nhap trang thai ban muon su dung");
            state = scanner.nextInt();
            if (state == 1) {
                System.out.println("Nhap vao tu muon tim");
                String findWord = scanner.next();
                if (findWord != null) {
                    Word word = dictionaryManegement.dictionaryLookup(findWord);
                    if (word != null) {
                        String format = "|%1$-10s|%2$-20s|\n";
                        System.out.format(format, "English", "VietNamese");
                        System.out.format(format, word.getWord_target(), word.getWord_explain());
                    }
                }
            }
            else if (state == 2) {
                showAllWords();
            }
            else if (state == 3) {
                dictionaryManegement.insertFromCommandline();
                dictionaryManegement.exportToFile();
            }
            else if (state == 4) {
                showAllWords();
                System.out.println("Nhap vao tu ban muon sua");
                String input = scanner.next();
                if (input != null) {
                    dictionaryManegement.editWord(input);
                }
                dictionaryManegement.exportToFile();

            }
            else if (state == 5) {
                showAllWords();
                System.out.println("Nhap vao tu ban muon xoa");
                String input = scanner.next();
                if (input != null) {
                    dictionaryManegement.removeWord(input);
                }
                dictionaryManegement.exportToFile();
            }
            else if (state == 6) {
                createLoop = false;
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
        System.out.println("3. Them vao tu dien");
        System.out.println("4. Sua tu dien");
        System.out.println("5. Xoa tu dien");
        System.out.println("6. Luu, tat tu dien");
        DictionaryCommandline dictionaryCommandline = new DictionaryCommandline();
        dictionaryCommandline.DictionaryBasic();
    }
}
