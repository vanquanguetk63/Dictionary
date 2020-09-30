package AppFx.Controller;

import base.Word;
import javafx.beans.value.WritableObjectValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;


public class SearchController  extends  InController{
    public Label edit;
    public Label bookmark;
    public Label delete;
    public ImageView sound_btn;



    public void handleDeleteWord(MouseEvent mouseEvent) {

    }

    public void handleBookmark(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == bookmark) {

        }
    }

    public void handleDelete(MouseEvent mouseEvent) {
    }

    public void handleEdit(MouseEvent mouseEvent) {
    }

    public void handleSpeech(MouseEvent mouseEvent) {
    }
}
