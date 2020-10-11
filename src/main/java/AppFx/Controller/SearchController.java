package main.java.AppFx.Controller;


import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import main.java.base.SoundGoogle;
import main.java.base.Word;

public class SearchController  extends  InController{
    public Label bookmark;
    public Label delete;
    public Label speech;


    public void handleBookmark(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == bookmark) {
            if (search_input.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Search");
                alert.setContentText("Bạn chưa chọn từ.");
                alert.setX(750);
                alert.setY(350);
                alert.showAndWait();
            }
            else  {
                String str = search_list_view.getSelectionModel().getSelectedItem();
                Word word = controller.getInitDictionary().dictionaryLookup(str);
                if (word != null) {
                    Image image;
                    if (checkBookMark(word)) {
                        image = new Image("/Resource/icons/icons8_Star_52px.png");
                        controller.initBookmark.removeWord(word);
                        controller.bookMarkController.search_list_view_bm.getItems().remove(word.getWord_target());
//                        controller.bookMarkController.list_view_explain.getItems().remove(word.getWord_explain());
                    }
                    else {
                        image = new Image("/Resource/icons/icons8_Star_Filled_52px.png");
                        controller.initBookmark.addWordToDictionary(word);
                        controller.bookMarkController.search_list_view_bm.getItems().add(word.getWord_target());
//                        controller.bookMarkController.list_view_explain.getItems().add(word.getWord_explain());
                    }
                    img_book_mark.setImage(image);

                }
            }
        }
    }


    public void handleDelete(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == delete) {
            String str = search_list_view.getSelectionModel().getSelectedItem();
            if (str != null ) {
                Alert confim = new Alert(Alert.AlertType.CONFIRMATION);
                confim.setTitle("Delete");
                confim.setHeaderText("Bạn muốn xóa từ '" + search_input.getText() + "' chứ?");
                confim.setX(750);
                confim.setY(350);
                confim.showAndWait();
                if (confim.getResult() == ButtonType.OK ) {
                    Word word = controller.getInitDictionary().dictionaryLookup(str);
                    if (word != null) {
                        controller.getInitDictionary().getDictionary().getWordArrayList().remove(word);
                        search_input.clear();
                        search_list_view.getItems().remove(word.getWord_target());
                        ipa.setText("/spelling/");
                        webEngineExplain.loadContent("");
                        controller.getInitDictionary().getDictionary().sortWord();
                        controller.getInitDictionary().exportToFile();
                    }
                }
            } else {
                Alert warning = new Alert(Alert.AlertType.WARNING);
                warning.setTitle("Delete");
                warning.setHeaderText("Bạn chưa nhập vào từ muốn xóa.");
                warning.setX(750);
                warning.setY(350);
                warning.showAndWait();
            }
        }
    }

    public void handleSpeech(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == speech) {
            String str = search_input.getText();
            if (str.isEmpty()) {
                Alert warning = new Alert(Alert.AlertType.WARNING);
                warning.setTitle("Speech");
                warning.setHeaderText("Bạn chưa nhập vào từ muốn nghe");
                warning.setX(750);
                warning.setY(350);
                warning.showAndWait();
            }
            else {
                SoundGoogle.speak(str, "en");
            }
        }
    }

    @Override
    public void reset() {
        super.reset();
    }

    public void handleEdit(MouseEvent mouseEvent) {
        Word word = this.controller.getInitDictionary().dictionaryLookup(search_input.getText());
        this.controller.clickEdit(word);
    }

    @Override
    public void setController(Controller state) {
        super.setController(state);
    }
}
