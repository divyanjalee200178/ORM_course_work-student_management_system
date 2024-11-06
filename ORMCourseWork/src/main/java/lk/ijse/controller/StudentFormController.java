package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.StudentBO;
import lk.ijse.models.StudentDTO;
import lk.ijse.view.tdm.StudentTm;

import java.io.IOException;
import java.util.List;

public class StudentFormController {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnEnter;


    @FXML
    private Button btnSave;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnExit;


    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colTel;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TableView<StudentTm> tblCustomer;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtTel;

    @FXML
    private TextField txtTelSearch;

    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);

    public void initialize(){
        setTable();
        setValueFactory();
        selectTableRow();
        generateCustomerId();
    }


    private void loadAllStudents() {
    }

    private void setValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("tel"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    void clearTextFields(){
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtEmail.clear();
        txtTel.clear();
    }

    private void getCurrentStId() {

    }


    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane= FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        Stage stage=(Stage) rootNode.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        boolean isDeleted = studentBO.delete(new StudentDTO(txtId.getText(), txtName.getText(), txtAddress.getText(), txtTel.getText(), txtEmail.getText()));
        if (isDeleted){
            clearTextFields();
            setTable();
            setValueFactory();
            tblCustomer.refresh();
            txtId.setText(generateCustomerId());
            new Alert(Alert.AlertType.CONFIRMATION,"Customer delete successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR,"Customer delete unsuccessfully").show();
        }
    }

    @FXML
    void btnEnterOnAction(ActionEvent event) {

    }

    @FXML
    void btnExitOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane=FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
        Stage stage=(Stage) rootNode.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Login Form");
        stage.centerOnScreen();
    }

    @FXML
    void btnSavetOnAction(ActionEvent event) {
        boolean isSaved = studentBO.save(new StudentDTO(txtId.getText(), txtName.getText(), txtAddress.getText(), txtTel.getText(), txtEmail.getText()));
        if (isSaved){
            clearTextFields();
            setTable();
            setValueFactory();
            tblCustomer.refresh();
            txtId.setText(generateCustomerId());
            new Alert(Alert.AlertType.CONFIRMATION,"Customer save successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR,"Customer save unsuccessfully").show();
        }

    }

    private String generateCustomerId() {
        try {
            String currentId = studentBO.getCurrentId();
            if (currentId != null) {
                String[] split = currentId.split("C00");
                int idNum = Integer.parseInt(split[1]);
                String availableId = "C00" + ++idNum;
                txtId.setText(availableId);
                return availableId;
            } else {
                txtId.setText("C001");
                return "C001";
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }



    private void setTable() {
        ObservableList<StudentTm> studentTms = FXCollections.observableArrayList();
        List<StudentDTO> all = studentBO.getAll();
        for (StudentDTO studentDTO : all){
            StudentTm studentTm = new StudentTm(studentDTO.getId(), studentDTO.getName(), studentDTO.getAddress(), studentDTO.getEmail(), studentDTO.getTel());
            studentTms.add(studentTm);
        }

        tblCustomer.setItems(studentTms);
    }

    private void selectTableRow() {
        tblCustomer.setOnMouseClicked(event -> {
            int focusedIndex = tblCustomer.getFocusModel().getFocusedIndex();
            StudentTm studentTm = tblCustomer.getItems().get(focusedIndex);
            txtId.setText(studentTm.getId());
            txtName.setText(studentTm.getName());
            txtAddress.setText(studentTm.getAddress());
            txtTel.setText(String.valueOf(studentTm.getTel()));
            txtEmail.setText(studentTm.getEmail());


        });
    }
    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        boolean isUpdated = studentBO.update(new StudentDTO(txtId.getText(), txtName.getText(), txtAddress.getText(), txtTel.getText(), txtEmail.getText()));
        if (isUpdated){
            clearTextFields();
            setTable();
            setValueFactory();
            tblCustomer.refresh();
            txtId.setText(generateCustomerId());
            new Alert(Alert.AlertType.CONFIRMATION,"Customer update successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR,"Customer update unsuccessfully").show();
        }
    }




    @FXML
    void txtAddressOnAction(ActionEvent event) {
        txtEmail.requestFocus();
    }

    @FXML
    void txtEmailOnAction(ActionEvent event) {

    }

    @FXML
    void txtIdOnAction(ActionEvent event) {
        txtName.requestFocus();
    }

    @FXML
    void txtNameOnAction(ActionEvent event) {
        txtTel.requestFocus();
    }

    @FXML
    void txtTelOnAction(ActionEvent event) {
        txtAddress.requestFocus();
    }

}
