package main.java.AppFx.Controller;


import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import main.java.AppFx.Support.GoogleTranslate;
import main.java.base.Word;

import java.io.IOException;

public class AddWordController extends InController {
    public TextField add_new_word;
    public TextField meaning_word;
    public Button save_word;
    public TextField spelling;


    public void handleClickSave(ActionEvent actionEvent) {
        if (add_new_word.getText() != null && meaning_word.getText() != null && spelling.getText() != null) {
            if (spelling.getText().charAt(0) == '/' && spelling.getText().charAt(spelling.getText().length() - 1) == '/') {
                String wordTarget = add_new_word.getText();
                String wordExplain = spelling.getText() + "</>" + meaning_word.getText();
                Word word = new Word(wordTarget, wordExplain);
                if (controller.getInitDictionary().addWordToDictionary(word)) {
                    initData(word);
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Add");
                    alert.setContentText("Từ đã tồn tại.");
                    alert.setX(750);
                    alert.setY(350);
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Add");
                alert.setContentText("Vui long nhap dung mau spelling.");
                alert.setX(750);
                alert.setY(350);
                alert.showAndWait();
            }

        }
    }

    public void initData(Word word) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Add");
        alert.setContentText("Thêm từ '" + word.getWord_target() + "' thành công");
        alert.setX(750);
        alert.setY(350);
        alert.showAndWait();
        controller.getInitDictionary().getDictionary().sortWord();
        controller.getInitDictionary().exportToFile();
    }

    @Override
    public void reset() {
        add_new_word.clear();
        meaning_word.clear();
        spelling.clear();
    }

    @Override
    public void setController(Controller state) {
        super.setController(state);
    }

    public void handleInput(ActionEvent actionEvent) {
        String autoGetMeaning = null;
        try {
            autoGetMeaning = GoogleTranslate.translate("en", "vi", add_new_word.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (autoGetMeaning != null) {
            spelling.setText("/null/");
            meaning_word.setText(autoGetMeaning);
        }
    }
}
