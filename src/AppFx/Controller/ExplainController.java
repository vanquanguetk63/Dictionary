package AppFx.Controller;

import AppFx.Advanced.InitDictionary;
import base.Word;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.awt.*;

public class ExplainController extends InController {
    private Controller controller = new Controller();

    @FXML
    private Label view_word_target;

    @FXML
    private Label view_word_explain;


    public ExplainController(){
    }

    public void initData(Controller controller, Word word) {
        this.controller = controller;
        view_word_target.setText(word.getWord_target());
        String str = "  " + word.getWord_explain();
        view_word_explain.setText(str);
    }

    public void setView_word_target(Label view_word_target) {
        this.view_word_target = view_word_target;
    }

    public Label getView_word_target() {
        return view_word_target;
    }
}
