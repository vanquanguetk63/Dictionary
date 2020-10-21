package AppFx.Controller;

import AppFx.Support.SoundGoogle;
import base.Word;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BookMarkController extends InController {
    public TextField search_input_bm;
    public ListView<String> search_list_view_bm;
    public WebView web_explain_bm;
    private WebEngine webEngineExplainBm;
    public Label ipa_bm;

    public void searchListWord(String wordTarget) {
        ArrayList<Word> list = controller.initBookmark.searchWordFromFX(wordTarget);
        if (!list.isEmpty()) {
            ArrayList<String> listTarget = new ArrayList<>();
            for (Word str : list) {
                listTarget.add(str.getWord_target());
            }
            search_list_view_bm.getItems().setAll(listTarget);
        }

        Word word = controller.initBookmark.dictionaryLookup(wordTarget);
        if (word != null) {
            initExplain(word);
        } else initList(list);

    }

    public void searchWordExactly(String wordTarget) {
        Word word = controller.initBookmark.dictionaryLookup(wordTarget);
        if (word != null) {
            initExplain(word);
        }
    }

    public void handleSearchInput(ActionEvent actionEvent) {
        if (!search_input_bm.getText().isEmpty()) {
            searchListWord(search_input_bm.getText());
        }

    }

    public void handleChangeInputSearch(KeyEvent keyEvent) {
        if (keyEvent.getSource() == search_input_bm) {
            String wordTarget = search_input_bm.getText();
            if (!wordTarget.isEmpty()) {
                searchListWord(wordTarget);
            } else {
                initData();
            }
        }
    }

    public void handleSelectListView(MouseEvent mouseEvent) {
        String wordTarget = search_list_view_bm.getSelectionModel().getSelectedItem();
        if (wordTarget != null) {
            searchWordExactly(wordTarget);
            search_input_bm.setText(wordTarget);
        }
    }


    public void handleDelete(MouseEvent mouseEvent) {
        String str = search_input_bm.getText();
        Word word = controller.initBookmark.dictionaryLookup(str);
        if (word != null) {
            Alert confim = new Alert(Alert.AlertType.CONFIRMATION);
            confim.setTitle("Delete");
            confim.setHeaderText("Bạn muốn xóa từ '" + search_input_bm.getText() + "' ra khỏi list bookmark chứ?");
            confim.setX(750);
            confim.setY(350);
            confim.showAndWait();
            if (confim.getResult() == ButtonType.OK ) {
                controller.initBookmark.removeWord(word.getWord_target());
                controller.bookMarkController.search_list_view_bm.getItems().remove(word.getWord_target());
                this.controller.initBookmark.exportToFile();
                Alert success = new Alert(Alert.AlertType.INFORMATION);
                success.setTitle("Delete");
                success.setHeaderText("Xóa thành công.");
                success.setX(750);
                success.setY(350);
                success.showAndWait();
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

    public void handleSpeech(MouseEvent mouseEvent) {
        String str = search_input_bm.getText();
        if (str.isEmpty()) {
            Alert warning = new Alert(Alert.AlertType.WARNING);
            warning.setTitle("Speech");
            warning.setHeaderText("Bạn chưa nhập vào từ muốn nghe.");
            warning.setX(750);
            warning.setY(350);
            warning.showAndWait();
        }
        else {
            SoundGoogle.speak(str, "en");
        }
    }

    public void initExplain(Word word) {
        String[] list = this.controller.initBookmark.initExplainWebView(word.getWord_explain());
        ipa_bm.setText(list[0]);
        webEngineExplainBm = web_explain_bm.getEngine();
        webEngineExplainBm.loadContent(list[1]);
    }

    public void initList(ArrayList<Word> word) {
        ArrayList<String> wordTarget = new ArrayList<>();
        ArrayList<String> wordExplain = new ArrayList<>();
        for (Word str : word) {
            wordTarget.add(str.getWord_target());
            wordExplain.add(str.getWord_explain());
        }
        search_list_view_bm.getItems().setAll(wordTarget);
    }

    public void initData() {
        ArrayList<Word> wordArrayList = controller.initBookmark.getDictionary().getWordArrayList();
        ArrayList<String> listTarget = new ArrayList<>();
        for (Word word : wordArrayList) {
            listTarget.add(word.getWord_target());
        }
        search_list_view_bm.getItems().setAll(listTarget);
    }

    public void reset() {
        search_input_bm.setText("");
        search_list_view_bm.getItems().clear();
        this.ipa_bm.setText("/spelling/");
        webEngineExplainBm = web_explain_bm.getEngine();
        webEngineExplainBm.loadContent("");
        initData();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initData();
    }

    @Override
    public void setController(Controller state) {
        this.ipa_bm.setText("/spelling/");
        super.setController(state);
    }

    public void handleEdit(MouseEvent mouseEvent) {
        Word word = this.controller.initBookmark.dictionaryLookup(search_input_bm.getText());
        this.controller.clickEdit(word);
        this.ipa_bm.setText("/spelling/");
    }

    public void handleBookmark(MouseEvent mouseEvent) {
    }
}
