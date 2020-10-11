package main.java.AppFx.Controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import main.java.AppFx.Support.GoogleTranslate;
import main.java.base.SoundGoogle;
import main.java.base.Word;

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
    public ImageView img;

    @FXML
    public WebView webview;
    private WebEngine webEngineWord;

    private ObservableList<String> language = FXCollections.observableArrayList("Anh - Việt", "Việt - Anh", "Nhật - Việt", "Việt - Nhật",
            "Pháp - Việt", "Việt - Pháp", "Hàn - Việt", "Việt - Hàn");

    public void handleTranslate(ActionEvent actionEvent) {
        try{
            boolean isEmpty = combobox.getSelectionModel().isEmpty();
            if (!isEmpty){
                webEngineWord.loadContent("");
                if(combobox.getValue().equals("Anh - Việt")) {
                    isTranslating("en", "vi");
                } else if (combobox.getValue().equals("Việt - Anh")) {
                    isTranslating("vi", "en");
                } else if (combobox.getValue().equals("Nhật - Việt")) {
                    isTranslating("ja", "vi");
                } else if (combobox.getValue().equals("Việt - Nhật")) {
                    isTranslating("vi", "ja");
                } else if (combobox.getValue().equals("Pháp - Việt")) {
                    isTranslating("fr", "vi");
                } else if (combobox.getValue().equals("Việt - Pháp")) {
                    isTranslating("vi", "fr");
                } else if (combobox.getValue().equals("Hàn - Việt")) {
                    isTranslating("ko", "vi");
                } else if (combobox.getValue().equals("Việt - Hàn")) {
                    isTranslating("vi", "ko");
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

    public void isTranslating(String from, String to) {
        try {
            String words = input.getText();
            String mean = GoogleTranslate.translateToWeb(from, to, words);
            String[] str = mean.split(" @ ");
            String[] dataExplain = str[1].split("</>");
            input.setText(str[0]);
            String html = "<html>" + dataExplain[1] +"</html>";
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
                String mean = GoogleTranslate.translateToWeb("en", "vi", words);
                if (!mean.isEmpty()) {
                    String[] str = mean.split(" @ ");
                    Word word = new Word(str[0], str[1]);
                    if (controller.getInitDictionary().addWordToDictionary(word)) {
                        Alert notify = new Alert(Alert.AlertType.INFORMATION);
                        notify.setTitle("Add");
                        notify.setContentText("Bạn đã thêm từ " + str[0] + " thành công." );
                        notify.setX(750);
                        notify.setY(350);
                        notify.showAndWait();
                        controller.getInitDictionary().getDictionary().sortWord();
                        controller.getInitDictionary().exportToFile();
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
            String[] spl = mean.split(" @ ");
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

    public void handleSelect(ActionEvent actionEvent) {
        Image image;
        if (combobox.getValue().equals("Nhật - Việt") || combobox.getValue().equals("Việt - Nhật") ) {
            image = new Image("main/resources/icons/jap-vie.png");

        } else if (combobox.getValue().equals("Việt - Pháp") || combobox.getValue().equals("Pháp - Việt") ) {
            image = new Image("main/resources/icons/jap-vie.png");
        } else if (combobox.getValue().equals("Hàn - Việt") || combobox.getValue().equals("Việt - Hàn") ) {
            image = new Image("main/resources/icons/jap-vie.png");
        } else {
            image = new Image("main/resources/icons/el-vie.png");
        }
        img.setImage(image);
    }

    @Override
    public void reset() {
        input.clear();
        Image image = new Image("main/resources/icons/el-vie.png");
        img.setImage(image);
    }

    @Override
    public void setController(Controller state) {
        super.setController(state);
    }

    public void handleEnter(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            try{
                boolean isEmpty = combobox.getSelectionModel().isEmpty();
                if (!isEmpty){
                    webEngineWord.loadContent("");
                    if(combobox.getValue().equals("Anh - Việt")) {
                        isTranslating("en", "vi");
                    } else if (combobox.getValue().equals("Việt - Anh")) {
                        isTranslating("vi", "en");
                    } else if (combobox.getValue().equals("Nhật - Việt")) {
                        isTranslating("ja", "vi");
                    } else if (combobox.getValue().equals("Việt - Nhật")) {
                        isTranslating("vi", "ja");
                    } else if (combobox.getValue().equals("Pháp - Việt")) {
                        isTranslating("fr", "vi");
                    } else if (combobox.getValue().equals("Việt - Pháp")) {
                        isTranslating("vi", "fr");
                    } else if (combobox.getValue().equals("Hàn - Việt")) {
                        isTranslating("ko", "vi");
                    } else if (combobox.getValue().equals("Việt - Hàn")) {
                        isTranslating("vi", "ko");
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
    }
}
