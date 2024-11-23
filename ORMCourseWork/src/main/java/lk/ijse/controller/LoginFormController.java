package lk.ijse.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.UserBO;
import lk.ijse.entity.User;
import lk.ijse.models.UserDTO;

import java.io.IOException;
import java.sql.SQLException;

import static javafx.fxml.FXMLLoader.load;


public class LoginFormController {
@FXML
private Button btnLogin;

@FXML
private Button btnRegister;

@FXML
private TextField txtPassword;

@FXML
private TextField txtUserID;
@FXML
private AnchorPane rootNode;
@FXML
private TextField txtRoll;



    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException, ClassNotFoundException {
    String userID = txtUserID.getText();
    String password = txtPassword.getText();
    String roll=txtRoll.getText();

    try {
        checkCredential(userID, password,roll);
    } catch (SQLException e) {
        new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
    }
}

    private void checkCredential(String userId, String password, String roll) throws SQLException, IOException, ClassNotFoundException {
        UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);
        UserDTO user = userBO.getUsersIdAndPasswordAndRole(userId, password, roll);
        if (user != null) {
            if (user.getRole().toLowerCase().equals("cordinator")||user.getRole().toLowerCase().equals("Cordinator")) {
                navigateToTheDashboard();
            }
            else if (user.getRole().toLowerCase().equals("admin")||user.getRole().toLowerCase().equals("Admin")) {
                navigateToTheAdminDashboard();
            }
            else {
                new Alert(Alert.AlertType.ERROR, "Invalid role!").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid credentials! Please try again.").show();
        }
    }



    private void navigateToTheDashboard() throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/dashboard_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard Form");
    }

    private void navigateToTheAdminDashboard() throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/admin_dashboard_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard Form");
    }


    public void btnRegisterOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/register_form.fxml"));
        Stage stage = (Stage) this.rootNode.getScene().getWindow();

        Scene scene = new Scene(rootNode);
        //Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Registration Form");
        stage.show();
    }






    @FXML
    void txtUsernameOnAction(ActionEvent event) {

    }

    void txtPassword(ActionEvent event){

    }

    public void txtPasswordOnAction(MouseEvent mouseEvent) {
    }
    @FXML
    void txtRollOnAction(ActionEvent event) {

    }

    public void hypForgetOnAction(ActionEvent actionEvent) throws IOException {
        navigateToThePasswordChangeForm();
    }

    private void navigateToThePasswordChangeForm() throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/Passwordchange_form.fxml"));
        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        Scene scene = new Scene(rootNode);
        //Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Change Password Form");
        stage.show();
    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) {

    }

    @FXML
    void txtUserIdOnAction(ActionEvent event) {

    txtPassword.requestFocus();
    }
}



