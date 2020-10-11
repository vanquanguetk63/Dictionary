package AppFx.Controller;


import base.Word;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class InController implements Initializable {
    protected Controller controller;

    @FXML
    public Label edit;

    @FXML
    protected TextField search_input;

    @FXML
    protected ListView<String> search_list_view;


    private AnchorPane viewExplainAnchor = null;

    @FXML
    protected ImageView img_book_mark;

    @FXML
    public WebView web_explain;

    @FXML
    public Label ipa;

    protected WebEngine webEngineExplain;

    public void searchListWord(String wordTarget) {
        ArrayList<Word> list = controller.getInitDictionary().searchWordFromFX(wordTarget);
        if (!list.isEmpty()) {
            initData(list);
        } else {
            ArrayList<String> listStr = this.controller.getInitDictionary().searchIfNone(wordTarget);
            if (listStr != null) {
                search_list_view.getItems().setAll(listStr);
            } else {
                search_list_view.getItems().clear();
                ipa.setText("/spelling/");
                webEngineExplain = web_explain.getEngine();
                webEngineExplain.loadContent("");
            }
        }

    }

    public void searchWordExactly(String wordTarget) {
        Word word = controller.getInitDictionary().dictionaryLookup(wordTarget);
        if (word != null ) {
            String[] str = this.controller.getInitDictionary().initExplainWebView(word.getWord_explain());
            ipa.setText(str[0]);
            webEngineExplain = web_explain.getEngine();
            webEngineExplain.loadContent(str[1]);
        }
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
                ipa.setText("/spelling/");
                webEngineExplain = web_explain.getEngine();
                webEngineExplain.loadContent("");
            }
        }
    }

    public void handleSelectListView(MouseEvent mouseEvent) {
        String wordTarget = search_list_view.getSelectionModel().getSelectedItem();
        if (wordTarget != null) {
            searchWordExactly(wordTarget);
            search_input.setText(wordTarget);
            Word word = controller.getInitDictionary().dictionaryLookup(search_input.getText());
            starBookMark(word);
        }
    }

    public void starBookMark(Word word) {
        if (checkBookMark(word)) {
            Image image = new Image("/Resource/icons/icons8_Star_Filled_52px.png");
            img_book_mark.setImage(image);
        } else {
            Image image = new Image("/Resource/icons/icons8_Star_52px.png");
            img_book_mark.setImage(image);
        }
    }


    public void initData(ArrayList<Word> list) {
        ArrayList<String> listTarget = new ArrayList<>();
        for (Word str : list) {
            listTarget.add(str.getWord_target());
        }
        search_list_view.getItems().setAll(listTarget);
    }

    public boolean checkBookMark(Word word) {
        ArrayList<Word> wordArrayList = Controller.initBookmark.getDictionary().getWordArrayList();

        for (Word word1 : wordArrayList) {
            if (word.getWord_target().compareTo(word1.getWord_target()) == 0) {
                return true;
            }
        }
        return false;
    }


    public void reset() {
        search_input.clear();
        search_list_view.getItems().clear();
        ipa.setText("/spelling/");
        webEngineExplain = web_explain.getEngine();
        webEngineExplain.loadContent("");
        Image image = new Image("/Resource/icons/icons8_Star_52px.png");
        img_book_mark.setImage(image);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void setController(Controller state) {
        this.controller = state;
    }
}
