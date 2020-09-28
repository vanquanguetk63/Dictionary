package AppFx.Controller;


import base.Word;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class InController implements Initializable {
    private Controller mainController = new Controller();
    private ExplainController explainController;
    @FXML
    private TextField search_input;

    @FXML
    private ListView<String> search_list_view;

    private AnchorPane viewExplainAnchor = null;

    @FXML
    private AnchorPane right_content;

    public void searchWord(String wordTarget) {
        ArrayList<String> list = this.mainController.getInitDictionary().searchWordFromFX(wordTarget);
        if (!list.isEmpty())
            search_list_view.getItems().setAll(list);

        Word word = this.mainController.getInitDictionary().dictionaryLookup(wordTarget);
        if (word != null)
            LoadExplainScreen(word);
    }

    public void handleSearchInput(ActionEvent actionEvent) {
        if (actionEvent.getSource() == search_input) {
            System.out.println("Enter input search");
        }
    }

    public void handleChangeInputSearch(KeyEvent keyEvent) {
        if (keyEvent.getSource() == search_input) {
            String wordTarget = search_input.getText();
            if (!wordTarget.isEmpty()) {
                searchWord(wordTarget);
            } else {
                search_list_view.getItems().clear();
            }
        }
        String wordTarget = search_list_view.getSelectionModel().getSelectedItem();
        if (wordTarget == null) return;
        search_input.setText(wordTarget);
        searchWord(wordTarget);
    }

    public void handleSelectListView(MouseEvent mouseEvent) {
        String wordTarget = search_list_view.getSelectionModel().getSelectedItem();
        if (wordTarget != null) {
            search_input.setText(wordTarget);
            searchWord(wordTarget);
        }
    }

    public void LoadExplainScreen(Word word) {
        explainController = new ExplainController();

        if (!right_content.getChildren().isEmpty()) {
            right_content.getChildren().clear();
        }
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/Resource/fxml/explain_screen.fxml"));
        VBox vBox = new VBox();
        try {
            vBox = fxmlLoader.load();
            right_content.getChildren().addAll(vBox);
            explainController = fxmlLoader.getController();
        }
        catch (IOException e) {
            System.out.println("Error to load explain screen");
            return;
        }

        if (explainController !=null) {
            explainController.initData(mainController, word);
        }
    }

    public void reset() {
        search_input.setText("");
        search_list_view.getItems().clear();
        Word word = new Word("","");
        explainController.initData(this.mainController, word);
    }

//    public void reload() {
//        if (mainController == null) return;
//
//        if (explainController != null) {
//            explainController.reload();
//        }
//    }
//
//    public void initData(Controller controller) {
//        this.mainController = controller;
//        if (explainController == null) {
//            Word word = new Word("","");
//            LoadExplainScreen(word);
//        }
//        if (this != null)
//            this.reload();
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        System.out.println(mainController.getInitDictionary().);
    }
}
