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
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static sample.StatController.player;
import static sample.StatController.opponent;

public class BattleController {

    @FXML
    private AnchorPane battlePane;

    @FXML
    private Text playerName;

    @FXML
    private Text playerHp;

    @FXML
    private Text playerLevel;

    @FXML
    private Text playerType;

    @FXML
    private ProgressBar playerHpBar;

    @FXML
    private ImageView playerImage;

    @FXML
    private Text playerDamage;

    @FXML
    private Text opponentName;

    @FXML
    private Text opponentHp;

    @FXML
    private Text opponentLevel;

    @FXML
    private Text opponentType;

    @FXML
    private ProgressBar opponentHpBar;

    @FXML
    private ImageView opponentImage;

    @FXML
    private Text opponentDamage;

    @FXML
    private Button attackButton;

    @FXML
    private Button skill1Button;

    @FXML
    private Button skill2Button;

    @FXML
    private Button runAwayButton;

    @FXML
    void initialize() {
        playerName.setText(player.getName());
        playerImage.setImage(player.getImage(false));
        playerType.setText(String.valueOf(player.getHero_class()));
        playerLevel.setText(String.valueOf(player.getLevel()));
        playerHpBar.setProgress(player.getStats().getHp() / player.getStats().getMaxHp());
        playerHp.setText(String.valueOf(player.getStats().getHp()));

        opponentName.setText(opponent.getName());
        opponentImage.setImage(opponent.getImage(true));
        opponentType.setText(String.valueOf(opponent.getHero_class()));
        opponentLevel.setText(String.valueOf(opponent.getLevel()));
        opponentHpBar.setProgress(opponent.getStats().getHp() / opponent.getStats().getMaxHp());
        opponentHp.setText(String.valueOf(opponent.getStats().getHp()));

        skill1Button.setText(player.getSkill()[0]);
        if (player.getLevel() < 7) {
            skill2Button.setDisable(true);
            skill2Button.setText("Unavailable");
        } else {
            skill2Button.setText(player.getSkill()[1]);
        }
    }

    @FXML
    void actionButtonEvent(ActionEvent event) throws IOException {
        if (event.getSource().equals(attackButton)) {
            perform(player.getStats().getAtk(), opponent.getStats().getAtk());
        } else if (event.getSource().equals(skill1Button)) {
            perform(player.getStats().getAtk() * 1.1, opponent.getStats().getAtk());
        } else if (event.getSource().equals(skill2Button)) {
            perform(player.getStats().getAtk() * 1.2, opponent.getStats().getAtk());
        } else if (event.getSource().equals(runAwayButton)) {
            URL url = this.getClass().getClassLoader().getResource("sample/BattleOver.fxml");
            if (url == null) return;
            AnchorPane pane = FXMLLoader.load(url);
            battlePane.getChildren().setAll(pane);
        }
    }

    private KeyFrame[] playerAction(double damage) {
        if (player.getStats().getHp() <= 0) return new KeyFrame[0];
        // all the keyframe stuff
        KeyValue oppHpBar = new KeyValue(opponentHpBar.progressProperty(), opponent.getStats().getHp() / opponent.getStats().getMaxHp());
        KeyValue oppHp = new KeyValue(opponentHp.textProperty(), String.valueOf(opponent.getStats().getHp()));
        KeyValue atkBtn = new KeyValue(attackButton.disableProperty(), true);
        KeyValue sk1Btn = new KeyValue(skill1Button.disableProperty(), true);
        KeyValue sk2Btn = new KeyValue(skill2Button.disableProperty(), true);
        KeyValue rnaBtn = new KeyValue(runAwayButton.disableProperty(), true);

        KeyFrame framedot0s = new KeyFrame(new Duration(0), atkBtn, sk1Btn, sk2Btn, rnaBtn, oppHpBar, oppHp);

        KeyValue playerYValue = new KeyValue(playerImage.yProperty(), playerImage.getY());
        KeyValue playerXValue = new KeyValue(playerImage.xProperty(), playerImage.getX());

        KeyFrame framedot100s = new KeyFrame(new Duration(1000), playerXValue, playerYValue);

        playerXValue = new KeyValue(playerImage.xProperty(), playerImage.getX() + 10);
        playerYValue = new KeyValue(playerImage.yProperty(), playerImage.getY() - 10);

        KeyFrame framedot125s = new KeyFrame(new Duration(1250), playerXValue, playerYValue);

        opponent.getStats().setDamage(damage);

        KeyValue oppDmg = new KeyValue(playerDamage.textProperty(), String.valueOf(damage));
        KeyValue oppYValue = new KeyValue(opponentImage.yProperty(), opponentImage.getY());
        KeyValue oppXValue = new KeyValue(opponentImage.xProperty(), opponentImage.getX());
        playerXValue = new KeyValue(playerImage.xProperty(), playerImage.getX());
        playerYValue = new KeyValue(playerImage.yProperty(), playerImage.getY());

        KeyFrame framedot15s = new KeyFrame(new Duration(1350), oppDmg, oppXValue, oppYValue, playerXValue, playerYValue);

        oppXValue = new KeyValue(opponentImage.xProperty(), opponentImage.getX() + 10);
        oppYValue = new KeyValue(opponentImage.yProperty(), opponentImage.getY() - 10);

        KeyFrame framedot175s = new KeyFrame(new Duration(1550), oppXValue, oppYValue, oppHpBar, oppDmg);

        oppHpBar = new KeyValue(opponentHpBar.progressProperty(), opponent.getStats().getHp() / opponent.getStats().getMaxHp());
        oppXValue = new KeyValue(opponentImage.xProperty(), opponentImage.getX());
        oppYValue = new KeyValue(opponentImage.yProperty(), opponentImage.getY());
        oppDmg = new KeyValue(playerDamage.textProperty(), null);
        atkBtn = new KeyValue(attackButton.disableProperty(), false);
        sk1Btn = new KeyValue(skill1Button.disableProperty(), false);
        if (player.getLevel() >= 7) sk2Btn = new KeyValue(skill2Button.disableProperty(), false);
        rnaBtn = new KeyValue(runAwayButton.disableProperty(), false);
        oppHp = new KeyValue(opponentHp.textProperty(), String.valueOf(opponent.getStats().getHp()));

        KeyFrame framedot200s = new KeyFrame(new Duration(1800), oppHpBar, oppXValue, oppYValue, oppDmg, atkBtn, sk1Btn, sk2Btn, rnaBtn, oppHp);
        return new KeyFrame[]{framedot0s, framedot15s, framedot100s, framedot125s, framedot175s, framedot200s};
    }

    private KeyFrame[] opponentAction(int time, double damage) {

        if (opponent.getStats().getHp() <= 0) return new KeyFrame[0];
        // all the keyframe stuff
        KeyValue plyHpBar = new KeyValue(playerHpBar.progressProperty(), player.getStats().getHp() / player.getStats().getMaxHp());
        KeyValue plyHp = new KeyValue(playerHp.textProperty(), String.valueOf(player.getStats().getHp()));
        KeyValue atkBtn = new KeyValue(attackButton.disableProperty(), true);
        KeyValue sk1Btn = new KeyValue(skill1Button.disableProperty(), true);
        KeyValue sk2Btn = new KeyValue(skill2Button.disableProperty(), true);
        KeyValue rnaBtn = new KeyValue(runAwayButton.disableProperty(), true);

        KeyFrame framedot0s = new KeyFrame(new Duration(time), atkBtn, sk1Btn, sk2Btn, rnaBtn, plyHpBar, plyHp);

        KeyValue oppYValue = new KeyValue(opponentImage.yProperty(), opponentImage.getY());
        KeyValue oppXValue = new KeyValue(opponentImage.xProperty(), opponentImage.getX());

        KeyFrame framedot1000s = new KeyFrame(new Duration(time + 1000), oppXValue, oppYValue);

        oppXValue = new KeyValue(opponentImage.xProperty(), opponentImage.getX() - 10);
        oppYValue = new KeyValue(opponentImage.yProperty(), opponentImage.getY() - 10);

        KeyFrame framedot1250s = new KeyFrame(new Duration(time + 1250), oppXValue, oppYValue);

        player.getStats().setDamage(damage);

        KeyValue oppDmg = new KeyValue(opponentDamage.textProperty(), String.valueOf(damage));
        KeyValue playerYValue = new KeyValue(playerImage.yProperty(), playerImage.getY());
        KeyValue playerXValue = new KeyValue(playerImage.xProperty(), playerImage.getX());
        oppXValue = new KeyValue(opponentImage.xProperty(), opponentImage.getX());
        oppYValue = new KeyValue(opponentImage.yProperty(), opponentImage.getY());

        KeyFrame framedot1350s = new KeyFrame(new Duration(time + 1350), oppXValue, oppYValue, playerYValue, playerXValue, oppDmg);

        playerXValue = new KeyValue(playerImage.xProperty(), playerImage.getX() - 10);
        playerYValue = new KeyValue(playerImage.yProperty(), playerImage.getY() + 10);

        KeyFrame framedot1550s = new KeyFrame(new Duration(time + 1550), playerYValue, playerXValue, plyHpBar, oppDmg);

        plyHpBar = new KeyValue(playerHpBar.progressProperty(), player.getStats().getHp() / player.getStats().getMaxHp());
        playerXValue = new KeyValue(playerImage.xProperty(), playerImage.getX());
        playerYValue = new KeyValue(playerImage.yProperty(), playerImage.getY());
        oppDmg = new KeyValue(opponentDamage.textProperty(), null);
        atkBtn = new KeyValue(attackButton.disableProperty(), false);
        sk1Btn = new KeyValue(skill1Button.disableProperty(), false);
        if (player.getLevel() >= 7) sk2Btn = new KeyValue(skill2Button.disableProperty(), false);
        rnaBtn = new KeyValue(runAwayButton.disableProperty(), false);
        plyHp = new KeyValue(playerHp.textProperty(), String.valueOf(player.getStats().getHp()));

        KeyFrame framedot1800s = new KeyFrame(new Duration(time + 1800), playerXValue, playerYValue, oppDmg, plyHpBar, atkBtn, sk1Btn, sk2Btn, rnaBtn, plyHp);
        return new KeyFrame[]{framedot0s, framedot1000s, framedot1250s, framedot1350s, framedot1550s, framedot1800s};
    }

    private void perform(double pdamage, double odamage) {
        Timeline timeline = new Timeline();
        List<KeyFrame> keyFrames = new ArrayList<>();
        int time = 0;

        Collections.addAll(keyFrames, playerAction(pdamage));

        Collections.addAll(keyFrames, opponentAction(1880, odamage));


        timeline.getKeyFrames().addAll(keyFrames);
        timeline.play();

        timeline.setOnFinished(e -> {
            if (player.getStats().getHp() <= 0 || opponent.getStats().getHp() <= 0) {
                runAwayButton.fire();
            }
        });
    }


}
