package AppFx.Controller;

import base.Word;
import javafx.beans.value.WritableObjectValue;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class AddWordController extends InController{
    public  ArrayList<Word> list = mainController.getInitDictionary().getDictionary().getWordArrayList();

    public TextField add_new_word;
    public TextField meaning_word;
    public Button save_word;
    public Label view_word_add;
    public Label notify;

    public void handleClickSave(ActionEvent actionEvent) {
        if (add_new_word.getText() != null
            && meaning_word.getText() != null) {
            String wordTarget = add_new_word.getText();
            String wordExplain = meaning_word.getText();
            Word word = new Word(wordTarget, wordExplain);
            if (mainController.getInitDictionary().addWordToDictionary(word)) {
                notify.setText("Thanh Cong");
            }else {
                notify.setText("Tu da ton tai");
            }
        }
    }

    public ArrayList<Word> update() {
        return list;
    }

//    public boolean addWordToDictionary(Word word) {
//        int length = list.size();
//        if (isExist(word) == false){
//            list.add(length, word);
//            mainController.getInitDictionary().getDictionary().setWordArrayList(list);
//            mainController.getInitDictionary().getDictionary().sortWord();
//            mainController.getInitDictionary().exportToFile();
//            return true;
//        }
//        return false;
//    }
//
//    public boolean isExist(Word word) {
//        ArrayList<Word> list = mainController.getInitDictionary().getDictionary().getWordArrayList();
//        for (Word checkWord : list) {
//            if (checkWord.getWord_target().compareTo(word.getWord_target()) == 0) {
//                return true;
//            }
//        }
//        return false;
//    }
}
