package AppFx.Controller;


import base.Word;
import javafx.beans.value.WritableObjectValue;
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
import java.net.CookiePolicy;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class InController implements Initializable {

    private ExplainController explainController;
    @FXML
    private TextField search_input;

    @FXML
    private ListView<String> search_list_view;

    private AnchorPane viewExplainAnchor = null;

    @FXML
    private AnchorPane right_content;

    public void searchWord(String wordTarget) {


        ArrayList<Word> list = Controller.getInitDictionary().searchWordFromFX(wordTarget);
        if (!list.isEmpty()) {
            ArrayList<String> listTarget = new ArrayList<>();
            for (Word str : list) {
                listTarget.add(str.getWord_target());
            }
            search_list_view.getItems().setAll(listTarget);
        }

        LoadExplainScreen(list);

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

    public void reload() {
        String searchText = search_input.getText();
        if (!searchText.isEmpty()) {
            searchWord(searchText);
        } else {
            search_list_view.getItems().clear();
        }

    }

    public void initData(Controller state) {
        if (this != null)
            this.reload();
    }

    public void LoadExplainScreen(ArrayList<Word> list) {
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
            explainController.initData(list);
        }
        catch (IOException e) {
            System.out.println("Error to load explain screen");
            return;
        }

    }

    public void reset() {
        search_input.setText("");
        search_list_view.getItems().clear();
        Word word = new Word("","");
//        explainController.initData(Controller, word);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}
