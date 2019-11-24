package sample;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class Controller implements Initializable {


    private DataBase dataBase = new DataBase();

    @FXML
    private Label lblSingInMassage;
    @FXML
    private ImageView imageviewBack;
    @FXML
    private ImageView imageviewClose;
    @FXML
    private JFXPasswordField textPasswordHIDE;
    @FXML
    private Label lblSmassage;
    @FXML
    private JFXTextField txtSfirs;
    @FXML
    private JFXTextField txtSlast;
    @FXML
    private JFXTextField txtSuser;
    @FXML
    private JFXTextField txtSemail;
    @FXML
    private JFXTextField txtSpass;
    @FXML
    private JFXTextField txtSpassConf;
    @FXML
    private Pane paneSingin;
    @FXML
    private JFXTextField textUsername;
    @FXML
    private JFXTextField textPassword;
    @FXML
    private Label lblPasswordShow;
    @FXML
    private Pane paneRigister;


    @FXML
    void onClickSingin(MouseEvent event){
        System.out.println("Sing in clicked!!");
        String usr=textUsername.getText();
        String pass="";
        if (textPassword.isVisible())
            pass=textPassword.getText();

        if (textPasswordHIDE.isVisible())
            pass=textPasswordHIDE.getText();

        if (!(dataBase.UserCheck(usr))) {
            lblSingInMassage.setText("Username or Password invalid!\nTry Again");
        }else {
            if (dataBase.getPassword(usr).equals(pass)) {
                lblSingInMassage.setWrapText(true);
                String string = "Welcome\n Name : " + dataBase.getName(usr) + "\nUsername : " + usr + "\n Password : "+dataBase.getPassword(usr)+"\n E-Mail : "+dataBase.getEmail(usr);
                lblSingInMassage.setText(string);
            }else lblSingInMassage.setText("Username or Password invalid!\nTry Again");
        }

    }

    @FXML
    void onClickRgister(MouseEvent event){
        imageviewBack.setVisible(true);
        imageviewBack.setDisable(false);
        paneSingin.setDisable(true);
        paneSingin.setVisible(false);
        paneRigister.setVisible(true);
        paneRigister.setDisable(false);
        txtSpassConf.clear();
        txtSpass.clear();
        txtSemail.clear();
        txtSuser.clear();
        txtSlast.clear();
        txtSfirs.clear();
    }

    @FXML
    void onClickSignUp(MouseEvent event)  {
        if(txtSpass.getText().equals(txtSpassConf.getText())) {
            lblSmassage.setStyle("-fx-text-fill: #61e06a");
            lblSmassage.setText("Congratulation!! let\'s Sing in ");
            dataBase.newPearson(txtSfirs.getText(),txtSlast.getText(),txtSuser.getText(),txtSemail.getText(),txtSpass.getText());
            Timer myTimer = new Timer();
            myTimer.schedule(new TimerTask(){
                @Override
                public void run() {
                    paneRigister.setDisable(true);
                    paneRigister.setVisible(false);
                    paneSingin.setVisible(true);
                    paneSingin.setDisable(false);
                }
            },1400 );
        }else {
            txtSpass.clear();
            txtSpassConf.clear();
            lblSmassage.setStyle("-fx-text-fill:#ff7200");
            lblSmassage.setText("Passwords didn\'t match. Try again ");
        }
        imageviewBack.setDisable(true);
        imageviewBack.setVisible(false);
    }

    @FXML
    void handleClose(MouseEvent event)    {
        System.exit(0);
    }

    @FXML
    void handleBack(MouseEvent event){
        paneSingin.setDisable(false);
        paneSingin.setVisible(true);
        paneRigister.setVisible(false);
        paneRigister.setDisable(true);
        imageviewBack.setDisable(true);
        imageviewBack.setVisible(false);
    }

    @FXML
    void setLblPasswordShow(MouseEvent event){
        if (lblPasswordShow.getText().equals("Show")){
            lblPasswordShow.setText("Hide");
            textPassword.setText(textPasswordHIDE.getText());
            textPasswordHIDE.setDisable(true);
            textPasswordHIDE.setVisible(false);
            textPassword.setDisable(false);
            textPassword.setVisible(true);
        }
        else {
            lblPasswordShow.setText("Show");
            textPasswordHIDE.setText(textPassword.getText());
            textPassword.setDisable(true);
            textPassword.setVisible(false);
            textPasswordHIDE.setDisable(false);
            textPasswordHIDE.setVisible(true);
        }

    }

 @Override
    public void initialize(URL location, ResourceBundle resources) {
     Image imageBack = new Image(getClass().getResourceAsStream("back-button.png"));
     Image imageClose = new Image(getClass().getResourceAsStream("close-button.png"));

     imageviewBack.setImage(imageBack);
     imageviewClose.setImage(imageClose);

 }
}
