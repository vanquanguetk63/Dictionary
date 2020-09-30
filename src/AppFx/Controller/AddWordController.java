package AppFx.Controller;

import base.Word;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddWordController extends InController{
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
            if (Controller.getInitDictionary().addWordToDictionary(word)) {
                initData(word);
            }else {
                notify.setText("Failed. Word is Exist");
            }
        }
    }

    public void initData(Word word) {
        notify.setText("Succesfull!!!");
        String str = "  New Word:  " + word.getWord_target() + "\n"
                + "  Meaning:  "  + word.getWord_explain();
        view_word_add.setText(str);
    }

}
