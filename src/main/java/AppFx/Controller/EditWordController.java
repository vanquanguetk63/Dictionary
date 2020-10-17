package AppFx.Controller;

import base.Word;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class EditWordController extends InController{
    public TextField add_new_word;
    public TextArea meaning_word;
    public TextField spelling;

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
                System.out.println(word.getWord_target());
                String wordTarget = add_new_word.getText();
                String wordExplain = spelling.getText() + "</>" + meaning_word.getText();
                word = new Word(wordTarget, wordExplain);
                int index = this.controller.getInitDictionary().binarySearchIndex(this.controller.getInitDictionary().getDictionary().getWordArrayList(),
                                                                                0,
                                                                                    this.controller.getInitDictionary().getDictionary().getWordArrayList().size() - 1,
                                                                                word.getWord_target());
                if (index != -1) {
                    this.controller.getInitDictionary().editWord(word, index);
                }

                //update trong list bookmark.
                int indexOfBm = this.controller.initBookmark.indexOfWordBm(word);
                if (indexOfBm != -1) {
                    this.controller.initBookmark.editWord(word, indexOfBm);
                }

                if (index != -1 || indexOfBm != -1) {
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
}
