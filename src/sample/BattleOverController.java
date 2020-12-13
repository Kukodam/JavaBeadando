package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;

import static  sample.StatController.*;

public class BattleOverController {

    @FXML
    private AnchorPane battleSummaryPane;

    @FXML
    private Text statusText;

    @FXML
    private Text goldRewardText;

    @FXML
    private Text expRewardText;

    @FXML
    private Text specialText;

    @FXML
    void initialize() {
        int exp = 0;

        if (player.getStats().getHp()<=0) {
            statusText.setText("YOU LOSE");
        } else if (opponent.getStats().getHp()<=0) {
            statusText.setText("YOU WIN");
            exp = opponent.getLevel()*10;
            player.plusExp(exp);
        } else {
            statusText.setText("YOU'VE RAN AWAY");
        }

        expRewardText.setText(String.valueOf(exp));
    }

    @FXML
    void continueButtonEvent() throws IOException {
        player.reset();
        ssh.addSave(player);
        URL url = this.getClass().getClassLoader().getResource("sample/Statistics.fxml");
        if (url == null) return;
        AnchorPane pane = FXMLLoader.load(url);
        battleSummaryPane.getChildren().setAll(pane);
    }

}
