package AppFx.Controller;

import AppFx.Advanced.InitBookmark;
import AppFx.Advanced.InitDictionary;
import base.Word;
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
    protected static InitDictionary initDictionary = new InitDictionary();
    protected static InitBookmark initBookmark = new InitBookmark();

    public Controller() {
        System.out.println("11");
    }

    @FXML
    private Button btn_search;

    @FXML
    private Button btn_add;

    @FXML
    private Button btn_edit;

    @FXML
    private Button btn_book_mark;

    @FXML
    private Button btn_gg;

    @FXML
    private AnchorPane anchor_view;

    private AnchorPane searchAnchorPane = null;
    private AnchorPane addAnchorPane = null;
    private AnchorPane editAnchorPane = null;
    private AnchorPane bookMarkPane = null;
    private AnchorPane ggTranslatePane = null;
    private AnchorPane curAnchor;

    private SearchController searchController;
    private AddWordController addWordController;
    private EditWordController editWordController;
    public static BookMarkController bookMarkController;
    private GgTranslateController ggTranslateController;


    public void setAnchor(AnchorPane anchorPane) {
        if (anchorPane == null){
            System.out.println("hello");
        }
        this.anchor_view.getChildren().setAll(anchorPane);
        this.curAnchor = anchorPane;
    }

    public void showSearchBTN() {
        this.setAnchor(searchAnchorPane);
        this.resetStyleNav();
        btn_search.setStyle("-fx-background-color:  #394357;");
    }

    public void showAddBTN() {
        this.setAnchor(addAnchorPane);
        this.resetStyleNav();
        btn_add.setStyle("-fx-background-color:  #394357;");
    }

    public void showEditBTN() {
        this.setAnchor(this.getEditAnchorPane());
        this.resetStyleNav();
        btn_edit.setStyle("-fx-background-color:  #394357;");
    }

    public void clickEdit(Word word) {
        showEditBTN();
        String[] list = this.getInitDictionary().initExplain(word.getWord_explain());
        editWordController.add_new_word.setText(word.getWord_target());
        editWordController.meaning_word.setText(list[1]);
        editWordController.spelling.setText(list[0]);

    }

    public void showBookMark() {
        this.setAnchor(bookMarkPane);
        this.resetStyleNav();
        btn_book_mark.setStyle("-fx-background-color:  #394357;");
    }

    public void showGG() {
        this.setAnchor(ggTranslatePane);
        this.resetStyleNav();
        btn_gg.setStyle("-fx-background-color:  #394357;");
    }

    @FXML
    public void handleClickEvent(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btn_search) {
            searchController.reset();
            showSearchBTN();
        } else if (actionEvent.getSource() == btn_add) {
            addWordController.reset();
            showAddBTN();
        } else if (actionEvent.getSource() == btn_edit) {
            editWordController.reset();
            showEditBTN();
        } else if (actionEvent.getSource() == btn_book_mark) {
            bookMarkController.reset();
            showBookMark();
        } else if (actionEvent.getSource() == btn_gg) {
            ggTranslateController.reset();
            showGG();
        }
     }


    public void resetStyleNav() {
        btn_search.setStyle("-fx-background-color:  #443A37");
        btn_add.setStyle("-fx-background-color:  #443A37");
        btn_edit.setStyle("-fx-background-color:  #443A37");
        btn_book_mark.setStyle("-fx-background-color: #443A37");
        btn_gg.setStyle("-fx-background-color:  #443A37;");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/search_screen.fxml"));
            searchAnchorPane = fxmlLoader.load();
            searchController = fxmlLoader.getController();
            searchController.setController(this);
        } catch (IOException e) {
            System.out.println("Error load SearchBTN.");
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/add_word.fxml"));
            addAnchorPane = fxmlLoader.load();
            addWordController = fxmlLoader.getController();
            addWordController.setController(this);
        }
        catch (IOException e) {
            System.out.println("Error load add_word.");
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/edit_word.fxml"));
            editAnchorPane = fxmlLoader.load();
            editWordController = fxmlLoader.getController();
            editWordController.setController(this);

        }
        catch  (IOException e) {
            System.out.println("Error load edit_word.");
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/book_mark.fxml"));
            bookMarkPane = fxmlLoader.load();
            bookMarkController = fxmlLoader.getController();
            bookMarkController.setController(this);
        } catch (IOException e) {
            System.out.println("Error load InitBookmark.");
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/gg_translate.fxml"));
            ggTranslatePane = fxmlLoader.load();
            ggTranslateController = fxmlLoader.getController();
            ggTranslateController.setController(this);
        } catch (IOException e) {
            System.out.println("Error load gg.");
        }

        this.showSearchBTN();
    }
    public AddWordController getAddWordController() {
        return addWordController;
    }

    public InitDictionary getInitDictionary() {
        return initDictionary;
    }


    public BookMarkController getBookMarkController() {
        return bookMarkController;
    }

    public void setBookMarkController(BookMarkController bookMarkController) {
        this.bookMarkController = bookMarkController;
    }


    public AnchorPane getEditAnchorPane() {
        return editAnchorPane;
    }

    public void setEditAnchorPane(AnchorPane editAnchorPane) {
        this.editAnchorPane = editAnchorPane;
    }
}
