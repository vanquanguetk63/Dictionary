package AppFx.Controller;

import base.Word;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class EditWordController extends InController{
    @FXML
    private TextField old_word, add_new_word, meaning_word;

    @FXML
    private Button edit_word;

    @FXML
    private Label view_word_edit, notify;


    public void handleClickEdit(ActionEvent actionEvent) {
        Word word = Controller.getInitDictionary().dictionaryLookup(old_word.getText());
        if (word == null) {
            notify.setText("Please Enter AN OLD WORD YOU WANT TO EDIT");
        }
        else if (word.getWord_target() != null) {
            if (add_new_word.getText()==null) {
                notify.setText("Please Enter New Word");
            }
            else {
                if (meaning_word.getText() == "") {
                    notify.setText("Please Enter Meaning Word");
                } else {
                    word = Controller.getInitDictionary().editWordFX(word, add_new_word.getText(), meaning_word.getText());
                    initData(word);
                }
            }
        }
    }

    public void initData1(Controller state) {
//        this = state;
    }

    public void initData(Word word) {
        notify.setText("Succesfull!!!");
        String str = "  New Word:  " + word.getWord_target() + "\n"
                + "  Meaning:  "  + word.getWord_explain();
        view_word_edit.setText(str);
    }

    public TextField getOld_word() {
        return old_word;
    }

    public void setOld_word(String str) {
        this.old_word.setText(str);
    }
}
