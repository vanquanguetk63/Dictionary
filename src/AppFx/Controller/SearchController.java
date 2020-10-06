package AppFx.Controller;

import base.Word;
import AppFx.Support.SoundGoogle;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

public class SearchController  extends  InController{
    public Label bookmark;
    public Label delete;
    public Label speech;
    public EditWordController editWordController;

    public void handleBookmark(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == bookmark) {
            String str = search_list_view.getSelectionModel().getSelectedItem();
            Word word = controller.getInitDictionary().dictionaryLookup(str);
            if (word != null) {
                Image image;
                if (checkBookMark(word)) {
                    image = new Image("/Resource/icons/icons8_Star_52px.png");
                    controller.bookMark.getDictionary().getWordArrayList().remove(word);
                    controller.bookMarkController.search_list_view.getItems().remove(word.getWord_target());
                    controller.bookMarkController.list_view_explain.getItems().remove(word.getWord_explain());
                }
                else {
                    image = new Image("/Resource/icons/icons8_Star_Filled_52px.png");
                    controller.bookMark.getDictionary().getWordArrayList().add(word);
                    controller.bookMarkController.search_list_view.getItems().add(word.getWord_target());
                    controller.bookMarkController.list_view_explain.getItems().add(word.getWord_explain());
                }
                if (controller == null) {
                    System.out.println("null");
                }
                img_book_mark.setImage(image);
            }
        }
    }

    public void handleDelete(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == delete) {
            String str = search_list_view.getSelectionModel().getSelectedItem();
            if (str != null ) {
                Alert confim = new Alert(Alert.AlertType.CONFIRMATION);
                confim.setTitle("Delete");
                confim.setHeaderText("Bạn muốn xóa từ " + search_input.getText() + " chứ?");
                confim.setX(750);
                confim.setY(350);
                confim.showAndWait();
                if (confim.getResult() == ButtonType.OK ) {
                    Word word = controller.getInitDictionary().dictionaryLookup(str);
                    if (word != null) {
                        controller.getInitDictionary().getDictionary().getWordArrayList().remove(word);
                        search_input.clear();
                        search_list_view.getItems().remove(word.getWord_target());
                        list_view_explain.getItems().clear();
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
}
