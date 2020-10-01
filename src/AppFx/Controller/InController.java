package AppFx.Controller;


import base.Word;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class InController implements Initializable {

    @FXML
    protected TextField search_input;

    @FXML
    protected ListView<String> search_list_view;

    @FXML
    protected ListView <String> list_view_explain;

    private AnchorPane viewExplainAnchor = null;

    @FXML
    protected ImageView img_book_mark;

    public void searchListWord(String wordTarget) {
        ArrayList<Word> list = Controller.getInitDictionary().searchWordFromFX(wordTarget);
        if (!list.isEmpty()) {
            initData(list);
        } else {
            search_list_view.getItems().clear();
            list_view_explain.getItems().clear();
        }

    }

    public void searchWordExactly(String wordTarget) {
        Word word = Controller.getInitDictionary().dictionaryLookup(wordTarget);
        if (word != null ) {
            initStar(word);
        }
    }

    public void initStar(Word word) {
        list_view_explain.getItems().setAll(word.getWord_explain());
        Image image;
        if (checkBookMark(word)) {
            image = new Image("/Resource/icons/icons8_Star_Filled_52px.png");
        }
        else {
            image = new Image("/Resource/icons/icons8_Star_52px.png");
        }
        img_book_mark.setImage(image);
    }

    public void handleSearchInput(ActionEvent actionEvent) {
        if (actionEvent.getSource() == search_input) {
            searchListWord(search_input.getText());
        }
    }

    public void handleChangeInputSearch(KeyEvent keyEvent) {
        if (keyEvent.getSource() == search_input) {
            String wordTarget = search_input.getText();
            if (!wordTarget.isEmpty()) {
                searchListWord(wordTarget);
            } else {
                search_list_view.getItems().clear();
                list_view_explain.getItems().clear();
            }
        }
    }

    public void handleSelectListView(MouseEvent mouseEvent) {
        String wordTarget = search_list_view.getSelectionModel().getSelectedItem();
        if (wordTarget != null) {
            searchWordExactly(wordTarget);
        }
    }

    public void initData(ArrayList<Word> list) {
        ArrayList<String> listTarget = new ArrayList<>();
        ArrayList<String> listExplain = new ArrayList<>();
        for (Word str : list) {
            listTarget.add(str.getWord_target());
            listExplain.add(str.getWord_explain());
        }
        search_list_view.getItems().setAll(listTarget);
        list_view_explain.getItems().setAll(listExplain);
    }

    public boolean checkBookMark(Word word) {
        ArrayList<Word> wordArrayList = Controller.bookMark.getDictionary().getWordArrayList();

        for (Word word1 : wordArrayList) {
            if (word.getWord_target().compareTo(word1.getWord_target()) == 0) {
                return true;
            }
        }

        return false;
    }

    public void reload() {
        String searchText = search_input.getText();
        if (!searchText.isEmpty()) {
            searchListWord(searchText);
        } else {
            search_list_view.getItems().clear();

        }
    }

    public void reset() {
        search_input.setText("");
        search_list_view.getItems().clear();
        Word word = new Word("","");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
