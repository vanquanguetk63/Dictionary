package AppFx.Controller;

import AppFx.Advanced.InitDictionary;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private InitDictionary initDictionary = new InitDictionary();

    @FXML
    private Button btn_search;

    @FXML
    private AnchorPane anchor_view;

    private AnchorPane searchAnchorPane = null;
    private AnchorPane curAnchor;
    private SearchController searchController;

    public void setAnchor(AnchorPane anchorPane) {
        this.anchor_view.getChildren().setAll(anchorPane);
        this.curAnchor = anchorPane;
    }

    public void showSearchBTN() {
        this.setAnchor(searchAnchorPane);
        this.resetStyleNav();
        btn_search.setStyle("-fx-background-color:  #394357;");
    }

    @FXML
    public void handleClickEvent(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btn_search) {
            showSearchBTN();
        }
    }

    public void resetStyleNav() {
        btn_search.setStyle(null);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/Resource/fxml/search_screen.fxml"));
            searchAnchorPane = fxmlLoader.load();
            searchController = fxmlLoader.getController();
//            searchController.initData(this);
        } catch (IOException e) {
            System.out.println("Error load SearchBTN.");
        }

        this.showSearchBTN();
    }

    public void setInitDictionary(InitDictionary initDictionary) {
        this.initDictionary = initDictionary;
    }

    public InitDictionary getInitDictionary() {
        return initDictionary;
    }
}
