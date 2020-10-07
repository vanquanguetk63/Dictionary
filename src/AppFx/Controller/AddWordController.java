package AppFx.Controller;

import base.Word;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
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
            if (controller.getInitDictionary().addWordToDictionary(word)) {
                controller.getInitDictionary().getDictionary().sortWord();
                controller.getInitDictionary().exportToFile();
                initData(word);
            }else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Add");
                alert.setContentText("Từ đã tồn tại.");
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
    }

}
