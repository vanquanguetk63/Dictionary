package AppFx.Controller;

import base.Word;
import AppFx.Support.SoundGoogle;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class SearchController  extends  InController{
    public Label edit;
    public Label bookmark;
    public Label delete;
    public Label speech;

    public void handleBookmark(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == bookmark) {
            String str = search_list_view.getSelectionModel().getSelectedItem();
            Word word = Controller.getInitDictionary().dictionaryLookup(str);
            if (word != null) {
                Image image;
                if (checkBookMark(word)) {
                    image = new Image("/Resource/icons/icons8_Star_52px.png");
                    Controller.bookMark.getDictionary().getWordArrayList().remove(word);
                    Controller.bookMarkController.search_list_view.getItems().remove(word.getWord_target());
                    Controller.bookMarkController.list_view_explain.getItems().remove(word.getWord_explain());
                }
                else {
                    image = new Image("/Resource/icons/icons8_Star_Filled_52px.png");
                    Controller.bookMark.getDictionary().getWordArrayList().add(word);
                    Controller.bookMarkController.search_list_view.getItems().add(word.getWord_target());
                    Controller.bookMarkController.list_view_explain.getItems().add(word.getWord_explain());
                }
                img_book_mark.setImage(image);
            }
        }
    }

    public void handleDelete(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == delete) {
            String str = search_list_view.getSelectionModel().getSelectedItem();
            Word word = Controller.getInitDictionary().dictionaryLookup(str);
            if (word != null) {
                Controller.getInitDictionary().getDictionary().getWordArrayList().remove(word);
                search_list_view.getItems().remove(word.getWord_target());
                list_view_explain.getItems().clear();
            }
        }
    }

    public void handleEdit(MouseEvent mouseEvent) {
    }

    public void handleSpeech(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == speech) {
            String str = search_input.getText();
            SoundGoogle.speak(str, "en");
        }
    }
}
