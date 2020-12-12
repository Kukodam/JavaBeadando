package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;

import static sample.StatController.player;
import static sample.StatController.ssh;

public class HeroCreationController {

    @FXML
    private AnchorPane createCharacterPane;

    @FXML
    private ProgressBar atkBar;

    @FXML
    private ProgressBar hpBar;

    @FXML
    private ProgressBar defBar;

    @FXML
    private Button createCharacterButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button nextClass;

    @FXML
    private Button prevClass;

    @FXML
    private ImageView imageView;

    @FXML
    private TextField nameField;

    @FXML
    private Label atkLabel;

    @FXML
    private Label hpLabel;

    @FXML
    private Label typeLabel;

    @FXML
    private Label defLabel;

    @FXML
    void initialize() {
        player = new Hero("temp", "KUKODAM");
        createCharacterButton.setDisable(true);
        update();
    }
    @FXML
    void nameInputEvent() {
        if (nameField.getText().equals("") || nameField.getText().contains(" "))
            createCharacterButton.setDisable(true);
        else
            createCharacterButton.setDisable(false);
    }
    private int type = 1;
    @FXML
    void heroClassSelectEvent(ActionEvent event) {
        if (event.getSource().equals(prevClass)) type--;
        else type++;
        if (type > 3) type = 1;
        else if (type < 1) type = 3;
        if(type == 1)
        {
            player.setHero_class("KUKODAM");
        }
        else if(type == 2)
        {
            player.setHero_class("SOMA");
        }
        else if(type == 3)
        {
            player.setHero_class("ROLAND");
        }
        update();
    }

    @FXML
    void createButtonEvent(ActionEvent event) throws IOException {
        if (event.getSource().equals(cancelButton)) player = null;
        else {
            player.setName(nameField.getText());
            ssh.addSave(player);
        }
        URL url = this.getClass().getClassLoader().getResource("sample/MainMenu.fxml");
        if (url == null) return;
        AnchorPane pane = FXMLLoader.load(url);
        createCharacterPane.getChildren().setAll(pane);
    }

    private void update() {
        imageView.setImage(player.getImage(false));
        typeLabel.setText(String.valueOf(player.getHero_class()));
        Timeline timeline = new Timeline();

        KeyValue hpValue = new KeyValue(hpBar.progressProperty(), player.getStats().getHp() / 1000);
        KeyValue atkValue = new KeyValue(atkBar.progressProperty(), player.getStats().getAtk() / 250);
        KeyValue defValue = new KeyValue(defBar.progressProperty(), player.getStats().getDef() / 150);

        KeyFrame frame = new KeyFrame(new Duration(1000), hpValue, atkValue, defValue);
        timeline.getKeyFrames().add(frame);
        timeline.play();
        hpLabel.setText(String.format("%.0f", player.getStats().getHp()));
        atkLabel.setText(String.format("%.0f", player.getStats().getAtk()));
        defLabel.setText(String.format("%.0f", player.getStats().getDef()));
    }

}
