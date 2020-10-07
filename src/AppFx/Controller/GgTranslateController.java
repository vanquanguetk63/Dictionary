package AppFx.Controller;

import AppFx.Support.GoogleTranslate;
import AppFx.Support.SoundGoogle;
import base.Word;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.IOException;
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
    public ImageView img;
    private WebEngine webEngineTarget;

    @FXML
    public WebView webview;
    private WebEngine webEngineWord;

    private ObservableList<String> language = FXCollections.observableArrayList("Anh - Việt", "Việt - Anh", "Nhật - Việt", "Việt - Nhật",
            "Pháp - Việt", "Việt - Pháp", "Hàn - Việt", "Việt - Hàn");

    public void handleTranslate(ActionEvent actionEvent) {
        try{
            boolean isEmpty = combobox.getSelectionModel().isEmpty();
            if (!isEmpty){
                webEngineTarget.loadContent("");
                webEngineWord.loadContent("");
                if(combobox.getValue().equals("Anh - Việt")) {
                    isTranslating("vi");
                } else if (combobox.getValue().equals("Việt - Anh")) {
                    isTranslating("en");
                } else if (combobox.getValue().equals("Nhật - Việt")) {
                    isTranslating("vi");
                } else if (combobox.getValue().equals("Việt - Nhật")) {
                    isTranslating("ja");
                } else if (combobox.getValue().equals("Pháp - Việt")) {
                    isTranslating("vi");
                } else if (combobox.getValue().equals("Việt - Pháp")) {
                    isTranslating("fr");
                } else if (combobox.getValue().equals("Hàn - Việt")) {
                    isTranslating("vi");
                } else if (combobox.getValue().equals("Việt - Hàn")) {
                    isTranslating("ko");
                }
            }
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Notification");
                alert.setContentText("Bạn chưa chọn chế độ dịch.");
                alert.setX(750);
                alert.setY(350);
                alert.show();
            }
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public void isTranslating(String language) {
        try {
            String words = input.getText();
            String mean = GoogleTranslate.translate(language, words);
            String[] str = mean.split(" : ");
            String htmlword = "<html>" + str[0] +"</html>";
            webEngineTarget.loadContent(htmlword);
            String html = "<html>" + str[1] +"</html>";
            webEngineWord.loadContent(html);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void handleAdd(ActionEvent actionEvent) {
        try {
            if(combobox.getValue().equals("Anh - Việt")) {
                String words = input.getText();
                String mean = GoogleTranslate.translate("vi", words);
                if (!mean.isEmpty()) {
                    String[] str = mean.split(" : ");
                    Word word = new Word(str[0], str[1]);
                    if (controller.getInitDictionary().addWordToDictionary(word)) {
                        Alert notify = new Alert(Alert.AlertType.INFORMATION);
                        notify.setTitle("Add");
                        notify.setContentText("Bạn đã thêm từ " + str[0] + " thành công." );
                        notify.setX(750);
                        notify.setY(350);
                        notify.showAndWait();
                    } else {
                        Alert notify = new Alert(Alert.AlertType.WARNING);
                        notify.setTitle("Add");
                        notify.setContentText("Từ đã có trong từ điển.");
                        notify.setX(750);
                        notify.setY(350);
                        notify.showAndWait();
                    }
                }
            } else {
                Alert notify = new Alert(Alert.AlertType.WARNING);
                notify.setTitle("Add");
                notify.setContentText("Xin lỗi, chỉ thêm được từ điển Anh - Việt.");
                notify.setX(750);
                notify.setY(350);
                notify.showAndWait();
            }
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        webEngineTarget = target.getEngine();
        webEngineWord = webview.getEngine();
        combobox.setItems(language);
    }

    public void handleMouseOut(MouseEvent mouseEvent) {
        if(combobox.getValue().equals("Anh - Việt")) {
            getMP3("vi");
        } else if (combobox.getValue().equals("Việt - Anh")) {
            getMP3("en");
        } else if (combobox.getValue().equals("Nhật - Việt")) {
            getMP3("vi");
        } else if (combobox.getValue().equals("Việt - Nhật")) {
            getMP3("ja");
        } else if (combobox.getValue().equals("Pháp - Việt")) {
            getMP3("vi");
        } else if (combobox.getValue().equals("Việt - Pháp")) {
            getMP3("fr");
        } else if (combobox.getValue().equals("Hàn - Việt")) {
            getMP3("vi");
        } else if (combobox.getValue().equals("Việt - Hàn")) {
            getMP3("ko");
        }
    }

    public void getMP3(String laguage) {
        String str = input.getText();
        try {
            String mean = GoogleTranslate.translate(laguage, str);
            String[] spl = mean.split(" : ");
            SoundGoogle.speak(spl[1], laguage);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleClickIn(MouseEvent mouseEvent) {
        String str = input.getText();
        if (str.isEmpty()) {
            Alert warning = new Alert(Alert.AlertType.WARNING);
            warning.setTitle("Speech");
            warning.setHeaderText("Bạn chưa nhập vào từ muốn nghe");
            warning.setX(750);
            warning.setY(350);
            warning.showAndWait();
        }
        else {
            if(combobox.getValue().equals("Anh - Việt")) {
                SoundGoogle.speak(str, "en");
            } else if (combobox.getValue().equals("Việt - Anh")) {
                SoundGoogle.speak(str, "vi");
            } else if (combobox.getValue().equals("Nhật - Việt")) {
                SoundGoogle.speak(str, "ja");
            } else if (combobox.getValue().equals("Việt - Nhật")) {
                SoundGoogle.speak(str, "vi");
            } else if (combobox.getValue().equals("Pháp - Việt")) {
                SoundGoogle.speak(str, "fr");
            } else if (combobox.getValue().equals("Việt - Pháp")) {
                SoundGoogle.speak(str, "vi");
            } else if (combobox.getValue().equals("Hàn - Việt")) {
                SoundGoogle.speak(str, "ko");
            } else if (combobox.getValue().equals("Việt - Hàn")) {
                SoundGoogle.speak(str, "vi");
            }
        }

    }
}
