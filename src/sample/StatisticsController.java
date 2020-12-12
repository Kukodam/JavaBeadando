package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;

import static sample.StatController.player;
import static sample.StatController.opponent;
import static sample.StatController.ssh;

public class StatisticsController {

    @FXML
    private AnchorPane lockerPane;

    @FXML
    private ProgressBar expBar;

    @FXML
    private ProgressBar hpBar;

    @FXML
    private ProgressBar atkBar;

    @FXML
    private ProgressBar defBar;

    @FXML
    private Text nameText;

    @FXML
    private Text levelText;

    @FXML
    private Text expCriteriaText;

    @FXML
    private Text expCurrentText;

    @FXML
    private Text hpText;

    @FXML
    private Text atkText;

    @FXML
    private Text defText;

    @FXML
    private Button battleButton;

    @FXML
    private Button backButton;

    @FXML
    private ImageView imageView;

    @FXML
    void initialize() {
        imageView.setImage(player.getImage(false));
        nameText.setText(player.getName());
        levelText.setText(String.valueOf(player.getLevel()));
        expCurrentText.setText(String.format("%d", player.getExp()));
        expCriteriaText.setText(String.format("/%d", player.getCriteria()));
        expBar.setProgress(player.getExp() / (double) player.getCriteria());
        hpText.setText(String.format("%.0f", player.getStats().getHp()));
        hpBar.setProgress(player.getStats().getHp() / 1000);
        atkText.setText(String.format("%.0f", player.getStats().getAtk()));
        atkBar.setProgress(player.getStats().getAtk() / 250);
        defText.setText(String.format("%.0f", player.getStats().getDef()));
        defBar.setProgress(player.getStats().getDef() / 150);
    }

    @FXML
    void buttonEvent(ActionEvent event) throws IOException {
        URL url = null;
        if (event.getSource().equals(battleButton)) {
            opponent = ssh.getOpponent(player.getLevel());
            url = this.getClass().getClassLoader().getResource("sample/Battle.fxml");
        } else if (event.getSource().equals(backButton)) {
            url = this.getClass().getClassLoader().getResource("sample/MainMenu.fxml");
            player = null;
        }
        if (url == null) return;
        AnchorPane pane = FXMLLoader.load(url);
        lockerPane.getChildren().setAll(pane);
    }

}
