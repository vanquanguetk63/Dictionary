package AppFx.Controller;

import base.Word;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;


public class ExplainController extends InController {
    public ImageView sound_btn;
    public Label delete;
    @FXML
    private ListView<String> list_view_explain;



    public void initData(ArrayList<Word> list) {
        ArrayList<String> listTarget = new ArrayList<>();
        for (Word str : list) {
            listTarget.add(str.getWord_explain());
        }
        list_view_explain.getItems().setAll(listTarget);
    }

    public void handleSpeech(MouseEvent mouseEvent) {
    }

    public void handleDelete(MouseEvent mouseEvent) {
    }

    public void handleEdit(MouseEvent mouseEvent) {
    }

    public void handleBookmark(MouseEvent mouseEvent) {
    }
}
