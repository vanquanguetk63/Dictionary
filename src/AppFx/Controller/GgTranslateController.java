package AppFx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class GgTranslateController extends InController implements Initializable {
    public ComboBox combobox;
    public TextField input;
    public Button translate;
    public Button add;
    public ImageView speech_in;
    public ImageView speech_out;
    @FXML
    public WebView target;
    private WebEngine webEngineTarget = target.getEngine();

    @FXML
    public WebView webview;
    private WebEngine webEngineWord = webview.getEngine();



    public void handleTranslate(ActionEvent actionEvent) {
    }

    public void handleAdd(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
