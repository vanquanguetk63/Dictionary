package AppFx.Controller;

import base.Word;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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

    public void searchListWord(String wordTarget) {
        ArrayList<Word> list = Controller.bookMark.searchWordFromFX(wordTarget);
        if (!list.isEmpty()) {
            ArrayList<String> listTarget = new ArrayList<>();
            for (Word str : list) {
                listTarget.add(str.getWord_target());
            }
            search_list_view.getItems().setAll(listTarget);
        }

        Word word = Controller.bookMark.dictionaryLookup(wordTarget);
        if (word != null) {
            initExplain(word);
        } else initList(list);

    }

    public void searchWordExactly(String wordTarget) {
        Word word = Controller.bookMark.dictionaryLookup(wordTarget);
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
        }
    }

    public void handleEdit(MouseEvent mouseEvent) {
    }

    public void handleBookmark(MouseEvent mouseEvent) {

    }

    public void handleDelete(MouseEvent mouseEvent) {
    }

    public void handleSpeech(MouseEvent mouseEvent) {
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
        ArrayList<Word> wordArrayList = Controller.bookMark.getDictionary().getWordArrayList();
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

        Word word = new Word("", "");
//        initExplain(word);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initData();
    }
}
