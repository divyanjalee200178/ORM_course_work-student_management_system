package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.ProgrammeBO;
import lk.ijse.bo.custom.StudentBO;
import lk.ijse.bo.custom.StudentProgrameBO;
import lk.ijse.entity.Program;
import lk.ijse.entity.Student;
import lk.ijse.models.ProgramDTO;
import lk.ijse.models.RegisterDTO;
import lk.ijse.models.StudentDTO;
import lk.ijse.view.tdm.RegisterTm;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class StudentProgramController {

    @FXML
    private Button btnRegi;

    @FXML
    private Button btnSave;

    @FXML
    private ComboBox<String> cmbPrId;

    @FXML
    private ComboBox<String> cmbstId;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colPrFee;

    @FXML
    private TableColumn<?, ?> colPrId;

    @FXML
    private TableColumn<?, ?> colPrName;

    @FXML
    private TableColumn<?, ?> colReFee;

    @FXML
    private TableColumn<?, ?> colReId;

    @FXML
    private TableColumn<?, ?> colStId;

    @FXML
    private TableColumn<?, ?> colStName;

    @FXML
    private DatePicker datePicker;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TableView<RegisterTm> tblRegisters;

    @FXML
    private Label txtDate;

    @FXML
    private Label txtId;

    @FXML
    private TextField txtPrFee;

    @FXML
    private TextField txtPrName;

    @FXML
    private TextField txtRegiFee;

    @FXML
    private TextField txtStName;

    StudentProgrameBO studentProgrameBO = (StudentProgrameBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENTPROGRAME);
    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);
    ProgrammeBO programBO = (ProgrammeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PROGRAMME);



    public void initialize() {
        generateRegiId();
        loadRegIds();
        getProgramIds();
        getStudentIds();
        addTableSelectionListener();
        setCellValueFactory();
        loadAllRegistrations();



//        cmbPrId.setOnAction(event -> fillProgrammeDetails());

//        colReId.setCellValueFactory(new PropertyValueFactory<>("registerId"));
//        colStId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
//        colPrId.setCellValueFactory(new PropertyValueFactory<>("programId"));
//        colReFee.setCellValueFactory(new PropertyValueFactory<>("programFee"));
//        colPrFee.setCellValueFactory(new PropertyValueFactory<>("regiFee"));

    }



    private void loadAllRegistrations(){

        ObservableList<RegisterTm> obList = FXCollections.observableArrayList();
        List<RegisterDTO> registerList = studentProgrameBO.getAllRegistrations();

        for (RegisterDTO registrationDTO : registerList) {

            RegisterTm registrationTm = new RegisterTm(
                    registrationDTO.getRegister_id(),
                    registrationDTO.getDate(),
                    registrationDTO.getStudent().getId(),
                    registrationDTO.getProgram().getCode(),
                    registrationDTO.getStudentName(),
                    registrationDTO.getProgramName(),
                    registrationDTO.getProgramFee(),
                    registrationDTO.getRegiFee()
            );
            obList.add(registrationTm);
        }
        tblRegisters.setItems(obList);


    }

    private void setCellValueFactory(){
        colReId.setCellValueFactory(new PropertyValueFactory<>("register_id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colStId.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        colPrId.setCellValueFactory(new PropertyValueFactory<>("program_id"));
        colStName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colPrName.setCellValueFactory(new PropertyValueFactory<>("programName"));
        colPrFee.setCellValueFactory(new PropertyValueFactory<>("programFee"));
        colReFee.setCellValueFactory(new PropertyValueFactory<>("regiFee"));
        System.out.println("done");
    }

    private void addTableSelectionListener() {
        tblRegisters.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                getRegisterDetails(newValue);
            }
        });
    }
    private void getRegisterDetails(RegisterTm registrationTm) {
        txtId.setText(registrationTm.getRegister_id());
        txtDate.setText(registrationTm.getDate());
        cmbstId.setValue(registrationTm.getStudent_id());
        cmbPrId.setValue(registrationTm.getProgram_id());
        txtStName.setText(registrationTm.getStudentName());
        txtPrName.setText(registrationTm.getProgramName());
        txtPrFee.setText(String.valueOf(registrationTm.getProgramFee()));
        txtRegiFee.setText(String.valueOf(registrationTm.getRegiFee()));
    }

    private void getStudentIds() {
        List<StudentDTO> studentsList = studentBO.getAllStudents();

        for (StudentDTO studentDTO : studentsList){
            cmbstId.getItems().add(studentDTO.getId());
        }
    }

    private void getProgramIds() {
        List<ProgramDTO> programsList = programBO.getAllPrograms();

        for (ProgramDTO programDTO : programsList){
            cmbPrId.getItems().add(programDTO.getCode());
        }
    }

    @FXML
    void btnRegiOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

        String registerId = txtId.getText();
        String date = String.valueOf(Date.valueOf(LocalDate.now()));
        String studentId = cmbstId.getValue();
        String programId = cmbPrId.getValue();
        String studentName = txtStName.getText();
        String programName = txtPrName.getText();
        double programFee = Double.parseDouble(txtPrFee.getText());
        double upfrontPayment = Double.parseDouble(txtRegiFee.getText());

        StudentDTO studentDTO = studentBO.searchById(studentId);

        Student student = new Student();
        student.setId(studentId);
        student.setUser(studentDTO.getUser());
        student.setName(studentDTO.getName());
        student.setAddress(studentDTO.getAddress());
        student.setEmail(studentDTO.getEmail());
        student.setTel(studentDTO.getTel());

        ProgramDTO programDTO = programBO.searchById(programId);

        Program program = new Program();

        program.setCode(programId);
        program.setName(programDTO.getName());
        program.setDuration(programDTO.getDuration());
        program.setPrice(programDTO.getPrice());

        boolean isSaved = studentProgrameBO.saveRegistration(new RegisterDTO(registerId,date,student,program,studentName,programName,programFee,upfrontPayment));

        if (isSaved){
            new Alert(Alert.AlertType.CONFIRMATION,"Registration completed!").show();
            loadAllRegistrations();
        }
        else {
            new Alert(Alert.AlertType.ERROR,"Registration not completed!").show();
        }

    }

    @FXML
    void cmbPrNameOnAction(ActionEvent event) {
        String programId = cmbPrId.getValue();

        ProgramDTO programDTO = studentProgrameBO.searchProgram(programId);

        if(programDTO != null){
            txtPrName.setText(programDTO.getName());
            txtPrFee.setText(String.valueOf(programDTO.getPrice()));
        }
        txtRegiFee.requestFocus();
    }

    @FXML
    void cmbStIdOnAction(ActionEvent event) {
        String studentId = cmbstId.getValue();

        StudentDTO studentDTO = studentProgrameBO.searchStudent(studentId);

        if(studentDTO != null){
            txtStName.setText(studentDTO.getName());
        }
        txtRegiFee.requestFocus();
    }

    @FXML
    void txtDateOnAction(MouseEvent event) {

    }

    @FXML
    void txtIdOnAction(MouseEvent event) {

    }

    @FXML
    void txtPrFeeOnAction(ActionEvent event) {

    }

    @FXML
    void txtPrIdOnAction(ActionEvent event) {

    }

    @FXML
    void txtRegiFeeOnAction(ActionEvent event) {

    }

    @FXML
    void txtStNameOnAction(ActionEvent event) {

    }


    private void loadRegIds() {
        // You can implement logic to load existing registration IDs if needed
    }





    @FXML
    void DateOnAction(ActionEvent event) {
        LocalDate myDate = datePicker.getValue();
        String myFormattedDate = myDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        txtDate.setText(myFormattedDate);
    }

    private String generateRegiId() {
        try {
            String currentId = studentProgrameBO.getCurrentId();
            if (currentId != null) {
                String[] split = currentId.split("R00");
                int idNum = Integer.parseInt(split[1]);
                String availableId = "R00" + ++idNum;
                txtId.setText(availableId);
                return availableId;
            } else {
                txtId.setText("R001");
                return "R001";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    private void clearFormFields() {
        cmbstId.setValue(null);
        txtStName.clear();
        cmbPrId.setValue(null);
        txtPrName.clear();
        txtPrFee.clear();
        txtRegiFee.clear();
    }

}

