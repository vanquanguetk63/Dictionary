package AppFx.Controller;

import AppFx.Support.SoundGoogle;
import base.Word;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BookMarkController extends InController {
    public TextField search_input;
    public ListView<String> search_list_view;
    public Label delete;
    public ImageView sound_btn;
    public ListView<String> list_view_explain;
    public Label sound_speech;

    public void searchListWord(String wordTarget) {
        ArrayList<Word> list = controller.bookMark.searchWordFromFX(wordTarget);
        if (!list.isEmpty()) {
            ArrayList<String> listTarget = new ArrayList<>();
            for (Word str : list) {
                listTarget.add(str.getWord_target());
            }
            search_list_view.getItems().setAll(listTarget);
        }

        Word word = controller.bookMark.dictionaryLookup(wordTarget);
        if (word != null) {
            initExplain(word);
        } else initList(list);

    }

    public void searchWordExactly(String wordTarget) {
        Word word = controller.bookMark.dictionaryLookup(wordTarget);
        if (word != null) {
            initExplain(word);
        }
    }

    public void handleSearchInput(ActionEvent actionEvent) {
        if (!search_input.getText().isEmpty()) {
            searchListWord(search_input.getText());
        }

    }

    public void handleChangeInputSearch(KeyEvent keyEvent) {
        if (keyEvent.getSource() == search_input) {
            String wordTarget = search_input.getText();
            if (!wordTarget.isEmpty()) {
                searchListWord(wordTarget);
            } else {
                initData();
            }
        }
    }

    public void handleSelectListView(MouseEvent mouseEvent) {
        String wordTarget = search_list_view.getSelectionModel().getSelectedItem();
        if (wordTarget != null) {
            searchWordExactly(wordTarget);
            search_input.setText(wordTarget);
        }
    }


    public void handleDelete(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == delete) {
            String str = search_input.getText();
            Word word = controller.bookMark.dictionaryLookup(str);
            if (word != null) {
                Alert confim = new Alert(Alert.AlertType.CONFIRMATION);
                confim.setTitle("Delete");
                confim.setHeaderText("Bạn muốn xóa từ '" + search_input.getText() + "' chứ?");
                confim.setX(750);
                confim.setY(350);
                confim.showAndWait();
                if (confim.getResult() == ButtonType.OK ) {
                    controller.bookMark.removeWord(word.getWord_target());
                    controller.bookMarkController.search_list_view.getItems().remove(word.getWord_target());
                    controller.bookMarkController.list_view_explain.getItems().remove(word.getWord_explain());
                    this.controller.bookMark.exportToFile();
                }
                Alert success = new Alert(Alert.AlertType.INFORMATION);
                success.setTitle("Delete");
                success.setHeaderText("Xoa thanh cong.");
                success.setX(750);
                success.setY(350);
                success.showAndWait();
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
        if (mouseEvent.getSource() == sound_speech) {
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

    public void initExplain(Word word) {
        list_view_explain.getItems().clear();
        list_view_explain.getItems().setAll(word.getWord_explain());
    }

    public void initList(ArrayList<Word> word) {
        ArrayList<String> wordTarget = new ArrayList<>();
        ArrayList<String> wordExplain = new ArrayList<>();
        for (Word str : word) {
            wordTarget.add(str.getWord_target());
            wordExplain.add(str.getWord_explain());
        }
        search_list_view.getItems().setAll(wordTarget);
        list_view_explain.getItems().setAll(wordExplain);
    }

    public void initData() {
        ArrayList<Word> wordArrayList = controller.bookMark.getDictionary().getWordArrayList();
        ArrayList<String> listExplain = new ArrayList<>();
        ArrayList<String> listTarget = new ArrayList<>();
        for (Word word : wordArrayList) {
            listTarget.add(word.getWord_target());
            listExplain.add(word.getWord_explain());
        }
        search_list_view.getItems().setAll(listTarget);
        list_view_explain.getItems().setAll(listExplain);
    }

    public void reset() {
        search_input.setText("");
        search_list_view.getItems().clear();
        list_view_explain.getItems().clear();
        initData();
//        this.controller.bookMark.insertFromFile();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initData();
    }
}
