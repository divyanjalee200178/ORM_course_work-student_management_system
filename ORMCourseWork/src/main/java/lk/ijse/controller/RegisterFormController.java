package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.UserBO;
import lk.ijse.models.UserDTO;
import lk.ijse.view.tdm.UserTm;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

public class RegisterFormController {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPass;

    @FXML
    private TableColumn<?, ?> colRole;

    @FXML
    private TableColumn<?, ?> colTel;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TableView<UserTm> tblUser;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtRole;

    @FXML
    private TextField txtTel;

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    public void initialize() {
        setTable();
        setValueFactory();
        selectTableRow();
        generateUserId();
    }

    private void setValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("tel"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPass.setCellValueFactory(new PropertyValueFactory<>("password"));
    }

    void clearTextFields() {
        txtId.clear();
        txtName.clear();
        txtRole.clear();
        txtEmail.clear();
        txtTel.clear();
        txtPassword.clear();
    }

    private String generateUserId() {
        try {
            String currentId = userBO.getCurrentId();
            if (currentId != null) {
                String[] split = currentId.split("U00");
                int idNum = Integer.parseInt(split[1]);
                String availableId = "U00" + ++idNum;
                txtId.setText(availableId);
                return availableId;
            } else {
                txtId.setText("U001");
                return "U001";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private void setTable() {
        ObservableList<UserTm> userTms = FXCollections.observableArrayList();
        List<UserDTO> all = userBO.getAll();
        for (UserDTO userDTO : all) {
            UserTm userTm = new UserTm(userDTO.getId(), userDTO.getName(), userDTO.getRole(),
                    userDTO.getEmail(), userDTO.getTel(), userDTO.getPassword());
            userTms.add(userTm);
        }
        tblUser.setItems(userTms);
    }

    private void selectTableRow() {
        tblUser.setOnMouseClicked(event -> {
            int focusedIndex = tblUser.getFocusModel().getFocusedIndex();
            UserTm userTm = tblUser.getItems().get(focusedIndex);
            txtId.setText(userTm.getId());
            txtName.setText(userTm.getName());
            txtRole.setText(userTm.getRole());
            txtTel.setText(userTm.getTel());
            txtEmail.setText(userTm.getEmail());
            txtPassword.setText(userTm.getPassword());
        });
    }

    @FXML
    void btnBackOnAction(ActionEvent event) {

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearTextFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        boolean isDeleted = userBO.delete(new UserDTO(txtId.getText(), txtName.getText(), txtRole.getText(),
                txtTel.getText(), txtEmail.getText(), txtPassword.getText()));
        if (isDeleted) {
            clearTextFields();
            setTable();
            tblUser.refresh();
            txtId.setText(generateUserId());
            new Alert(Alert.AlertType.CONFIRMATION, "User deleted successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "User delete unsuccessful").show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String encryptedPassword = BCrypt.hashpw(txtPassword.getText(), BCrypt.gensalt());
        boolean isSaved = userBO.save(new UserDTO(txtId.getText(), txtName.getText(), txtRole.getText(),
                txtTel.getText(), txtEmail.getText(), encryptedPassword));
        if (isSaved) {
            clearTextFields();
            setTable();
            tblUser.refresh();
            txtId.setText(generateUserId());
            new Alert(Alert.AlertType.CONFIRMATION, "User saved successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "User save unsuccessful").show();
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String encryptedPassword = BCrypt.hashpw(txtPassword.getText(), BCrypt.gensalt());
        boolean isUpdated = userBO.update(new UserDTO(txtId.getText(), txtName.getText(), txtRole.getText(),
                txtTel.getText(), txtEmail.getText(), encryptedPassword));
        if (isUpdated) {
            clearTextFields();
            setTable();
            tblUser.refresh();
            txtId.setText(generateUserId());
            new Alert(Alert.AlertType.CONFIRMATION, "User updated successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "User update unsuccessful").show();
        }
    }

    @FXML
    void txtEmailOnAction(ActionEvent event) {

    }

    @FXML
    void txtIdOnAction(ActionEvent event) {

    }

    @FXML
    void txtNameOnAction(ActionEvent event) {

    }

    @FXML
    void txtRoleOnAction(ActionEvent event) {

    }

    @FXML
    void txtTelOnAction(ActionEvent event) {

    }

    public void txtUserIdOnAction(ActionEvent actionEvent) {
    }

    public void txtPasswordOnAction(ActionEvent actionEvent) {
    }

    public void btnExitOnAction(ActionEvent actionEvent) {
    }
}
