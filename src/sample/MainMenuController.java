package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

import static sample.StatController.player;
import static sample.StatController.ssh;

public class MainMenuController {

    private Hero[] heroes;

    @FXML
    private AnchorPane loadGamePane;

    @FXML
    private ImageView imageSlot1;

    @FXML
    private ImageView imageSlot2;

    @FXML
    private ImageView imageSlot3;

    @FXML
    private Button buttonSlot1;

    @FXML
    private Button buttonSlot2;

    @FXML
    private Button buttonSlot3;

    @FXML
    private Label nameSlot1;

    @FXML
    private Label nameSlot2;

    @FXML
    private Label nameSlot3;

    @FXML
    private Label typeSlot1;

    @FXML
    private Label typeSlot2;

    @FXML
    private Label typeSlot3;

    @FXML
    private Label goldSlot1;

    @FXML
    private Label goldSlot2;

    @FXML
    private Label goldSlot3;

    @FXML
    private Label levelSlot1;

    @FXML
    private Label levelSlot2;

    @FXML
    private Label levelSlot3;

    @FXML
    private Button deleteSlot1;

    @FXML
    private Button deleteSlot2;

    @FXML
    private Button deleteSlot3;

    @FXML
    private Label labelSlot1;

    @FXML
    private Label labelSlot2;

    @FXML
    private Label labelSlot3;

    @FXML
    private void initialize() {
        update();
    }

    @FXML
    void slotButtonEvent(ActionEvent event) throws IOException {
        int slot = 0;
        if (event.getSource().equals(buttonSlot1)) slot = 1;
        else if (event.getSource().equals(buttonSlot2)) slot = 2;
        else if (event.getSource().equals(buttonSlot3)) slot = 3;

        URL url;
        if (heroes.length >= slot) {
            player = heroes[slot - 1];
            url = this.getClass().getClassLoader().getResource("sample/Statistics.fxml");
        } else {
            url = this.getClass().getClassLoader().getResource("sample/HeroCreation.fxml");
        }
        if (url == null) return;
        AnchorPane pane = FXMLLoader.load(url);
        loadGamePane.getChildren().setAll(pane);
    }

    @FXML
    void deleteButtonEvent(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Save Confirmation");
        alert.setHeaderText("Delete comfirmation");
        alert.setContentText("This is irreverable process. Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.CANCEL) return;

        if (event.getSource().equals(deleteSlot1)) ssh.deleteSave(heroes[0]);
        else if (event.getSource().equals(deleteSlot2)) ssh.deleteSave(heroes[1]);
        else ssh.deleteSave(heroes[2]);
        update();
    }

    @FXML
    void backButtonEvent() throws IOException {
        URL url = this.getClass().getClassLoader().getResource("sample/sample.fxml");
        if (url == null) return;
        AnchorPane pane = FXMLLoader.load(url);
        loadGamePane.getChildren().setAll(pane);
    }

    private void update() {
        clear();
        ssh.loadSave();
        heroes = ssh.getHeroes();
        if (heroes.length > 0) {
            imageSlot1.setImage(heroes[0].getImage(false));
            nameSlot1.setText(heroes[0].getName());
            typeSlot1.setText(heroes[0].getHero_class());
            goldSlot1.setText(String.valueOf(heroes[0].getExp()));
            levelSlot1.setText(String.valueOf(heroes[0].getLevel()));
            labelSlot1.setText("LV");
            deleteSlot1.setDisable(false);
        }
        if (heroes.length > 1) {
            imageSlot2.setImage(heroes[1].getImage(false));
            nameSlot2.setText(heroes[1].getName());
            typeSlot2.setText(heroes[1].getHero_class());
            goldSlot2.setText(String.valueOf(heroes[1].getExp()));
            levelSlot2.setText(String.valueOf(heroes[1].getLevel()));
            labelSlot2.setText("LV");
            deleteSlot2.setDisable(false);
        }
        if (heroes.length > 2) {
            imageSlot3.setImage(heroes[2].getImage(false));
            nameSlot3.setText(heroes[2].getName());
            typeSlot3.setText(heroes[2].getHero_class());
            goldSlot3.setText(String.valueOf(heroes[2].getExp()));
            levelSlot3.setText(String.valueOf(heroes[2].getLevel()));
            labelSlot3.setText("LV");
            deleteSlot3.setDisable(false);
        }
    }

    private void clear() {
        imageSlot1.setImage(null);
        nameSlot1.setText("EMPTY");
        typeSlot1.setText("Create");
        goldSlot1.setText(null);
        levelSlot1.setText(null);
        deleteSlot1.setDisable(true);
        labelSlot1.setText(null);

        imageSlot2.setImage(null);
        nameSlot2.setText("EMPTY");
        typeSlot2.setText("Create");
        goldSlot2.setText(null);
        levelSlot2.setText(null);
        deleteSlot2.setDisable(true);
        labelSlot2.setText(null);

        imageSlot3.setImage(null);
        nameSlot3.setText("EMPTY");
        typeSlot3.setText("Create");
        goldSlot3.setText(null);
        levelSlot3.setText(null);
        deleteSlot3.setDisable(true);
        labelSlot3.setText(null);
    }

}
