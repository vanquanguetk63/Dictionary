package AppFx.Controller;

import base.Word;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class EditWordController extends InController{
    public TextField add_new_word;
    public TextField meaning_word;

    @FXML
    private Button edit_word;


    public void handleClickEdit(ActionEvent actionEvent) {
        if (add_new_word.getText().isEmpty() || meaning_word.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Edit");
            alert.setContentText("Hãy nhập đầy đủ.");
            alert.setX(750);
            alert.setY(350);
            alert.showAndWait();
        } else {
            Word word = controller.getInitDictionary().dictionaryLookup(add_new_word.getText());
            if (word != null) {
                word = controller.getInitDictionary().editWordFX(word, meaning_word.getText());
                if (word != null) {
                    initData(word);
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Edit");
                alert.setContentText("Từ không có trong từ điển, không thể sửa.");
                alert.setX(750);
                alert.setY(350);
                alert.showAndWait();
            }
        }

    }


    public void initData(Word word) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Edit");
        alert.setX(750);
        alert.setY(350);
        alert.setContentText("Sửa từ " + word.getWord_target() + " thành công");
        alert.showAndWait();
        controller.getInitDictionary().getDictionary().sortWord();
        controller.getInitDictionary().exportToFile();
    }
}
