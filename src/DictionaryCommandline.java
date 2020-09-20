import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;
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
        boolean createLoop = true;
        int state =  scanner.nextInt();
        while (createLoop) {
            switch (state){
                case 1:
                    System.out.println("Vui long nhap so tu ban muon nhap");
                    int numBer = scanner.nextInt();
                    while (numBer > 0) {
                        dictionaryManegement.insertFromCommandline();
                        numBer--;
                    }
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
            System.out.println("Vui long nhap trang thai ban muon su dung");
            state = scanner.nextInt();
        }
    }

    public static void main(String[] args) {
        System.out.println("Bang dieu khien");
        System.out.println("1. Them tu");
        System.out.println("2. Mo tu dien");
        System.out.println("3. Dong");
        DictionaryCommandline dictionaryCommandline = new DictionaryCommandline();
    }


}
