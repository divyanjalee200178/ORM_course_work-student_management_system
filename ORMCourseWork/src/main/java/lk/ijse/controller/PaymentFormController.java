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
import lk.ijse.bo.custom.PaymentBO;
import lk.ijse.bo.custom.StudentBO;
import lk.ijse.bo.custom.ProgrammeBO;
import lk.ijse.models.PaymentDTO;
import lk.ijse.models.StudentDTO;
import lk.ijse.models.ProgramDTO;
import lk.ijse.view.tdm.PaymentTm;

import java.util.List;

public class PaymentFormController {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<String> cmbProId; // ComboBox for Programme IDs
    @FXML
    private ComboBox<String> cmbStId;  // ComboBox for Student IDs

    @FXML
    private TableColumn<?, ?> colFullPay;

    @FXML
    private TableColumn<?, ?> colLast;

    @FXML
    private TableColumn<?, ?> colPaid;

    @FXML
    private TableColumn<?, ?> colPayId;

    @FXML
    private TableColumn<?, ?> colProgramId;

    @FXML
    private TableColumn<?, ?> colStuId;

    @FXML
    private Label lblAmount;

    @FXML
    private Label lblFullPay;

    @FXML
    private Label lblPayment;

    @FXML
    private Label txtPaymentId;

    @FXML
    private Label lblProName;

    @FXML
    private Label lblStName;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TableView<PaymentTm> tblPayment;

    PaymentBO paymentBO = (PaymentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PAYMENT);
    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);
    ProgrammeBO programmeBO = (ProgrammeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PROGRAMME);

    public void initialize() {
        setTable();
        setValueFactory();
        generatePaymentId();
        loadComboBoxes();
        selectTableRow();



        cmbStId.setOnAction(event -> fillStudentDetails());
        cmbProId.setOnAction(event -> fillProgrammeDetails());
    }

    private void loadComboBoxes() {
        try {
            List<StudentDTO> studentList = studentBO.getAll();
            ObservableList<String> studentIds = FXCollections.observableArrayList();
            for (StudentDTO student : studentList) {
                studentIds.add(student.getId());
            }
            cmbStId.setItems(studentIds);


            List<ProgramDTO> programmeList = programmeBO.getAll();
            ObservableList<String> programmeIds = FXCollections.observableArrayList();
            for (ProgramDTO programme : programmeList) {
                programmeIds.add(programme.getCode());
            }
            cmbProId.setItems(programmeIds);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error loading ComboBoxes").show();
        }
    }

    private void setValueFactory() {
        colPayId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colProgramId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colStuId.setCellValueFactory(new PropertyValueFactory<>("ProgrammeId"));
        colFullPay.setCellValueFactory(new PropertyValueFactory<>("fee"));
        colPaid.setCellValueFactory(new PropertyValueFactory<>("registerFee"));
        colLast.setCellValueFactory(new PropertyValueFactory<>("totalFee"));
    }

    private String generatePaymentId() {
        try {
            String currentId = paymentBO.getCurrentId();
            if (currentId != null) {
                String[] split = currentId.split("P00");
                int idNum = Integer.parseInt(split[1]);
                String availableId = "P00" + ++idNum;
                txtPaymentId.setText(availableId);
                return availableId;
            } else {
                txtPaymentId.setText("P001");
                return "P001";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private void setTable() {
        ObservableList<PaymentTm> paymentTms = FXCollections.observableArrayList();
        List<PaymentDTO> all = paymentBO.getAll();
        for (PaymentDTO paymentDTO : all) {
            PaymentTm paymentTm = new PaymentTm(
                    paymentDTO.getId(),
                    paymentDTO.getStudentId(),
                    paymentDTO.getProgrammeId(),
                    paymentDTO.getFee(),
                    paymentDTO.getRegisterFee(),
                    paymentDTO.getTotalFee()
            );
            paymentTms.add(paymentTm);
        }

        tblPayment.setItems(paymentTms);
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        calculateFullPayment();
        String studentId = cmbStId.getValue();
        String programmeId = cmbProId.getValue();
        if (studentId == null || programmeId == null) {
            new Alert(Alert.AlertType.ERROR, "Please select both Student and Programme").show();
            return;
        }

        boolean isSaved = paymentBO.save(new PaymentDTO(
                txtPaymentId.getText(),
                studentId,
                programmeId,
                Double.parseDouble(lblPayment.getText()),
                Double.parseDouble(lblAmount.getText()),
                Double.parseDouble(lblFullPay.getText())
        ));

        if (isSaved) {
            txtPaymentId.setText(generatePaymentId());
            setTable();
            setValueFactory();
            tblPayment.refresh();
            new Alert(Alert.AlertType.CONFIRMATION, "Payment saved successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Payment saved unsuccessfully").show();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String studentId = cmbStId.getValue();
        String programmeId = cmbProId.getValue();
        if (studentId == null || programmeId == null) {
            new Alert(Alert.AlertType.ERROR, "Please select both Student and Programme").show();
            return;
        }

        boolean isDeleted = paymentBO.delete(new PaymentDTO(
                txtPaymentId.getText(),
                studentId,
                programmeId,
                Double.parseDouble(lblAmount.getText()),
                Double.parseDouble(lblPayment.getText()),
                Double.parseDouble(lblFullPay.getText())
        ));

        if (isDeleted) {
            setTable();
            setValueFactory();
            tblPayment.refresh();
            txtPaymentId.setText(generatePaymentId());
            new Alert(Alert.AlertType.CONFIRMATION, "Payment deleted successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Payment deletion unsuccessful").show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }


    private void fillStudentDetails() {
            String selectedStudentId = cmbStId.getValue();
            if (selectedStudentId != null) {
                try {

                    StudentDTO student = studentBO.get(selectedStudentId);
                    if (student != null) {
                        lblStName.setText(student.getName());
                        lblAmount.setText(String.valueOf(student.getPayment()));
                    } else {
                        lblStName.setText("Student not found");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    lblStName.setText("Error fetching student");
                }
            } else {
                lblStName.setText("");
            }
    }


    private void fillProgrammeDetails() {
        String selectedProgrammeId = cmbProId.getValue();
        if (selectedProgrammeId != null) {
            try {
                ProgramDTO programme = programmeBO.get(selectedProgrammeId);
                if (programme != null) {
                    lblProName.setText(programme.getName());
                    lblPayment.setText(String.valueOf(programme.getPrice()));
                } else {
                    lblProName.setText("Programme not found");
                }
            } catch (Exception e) {
                e.printStackTrace();
                lblProName.setText("Error fetching programme");
            }
        } else {
            lblProName.setText("");
        }
    }



    public void cmbProIdOnAction(ActionEvent actionEvent) {
    }

    public void cmbStIdOnAction(ActionEvent actionEvent) {
    }



    public void lblPaymentOnAction(MouseEvent mouseEvent) {
    }

    public void lblAmountOnAction(MouseEvent mouseEvent) {
    }

    public void lblFullPayOnAction(MouseEvent mouseEvent) {
    }

    public void lblPaymentIdOnAction(MouseEvent mouseEvent) {
    }

    public void lblProNameOnAction(MouseEvent mouseEvent) {
    }

    public void lblStNameOnAction(MouseEvent mouseEvent) {
    }
    public void calculateFullPayment() {
        try {

            String paymentText = lblPayment.getText().trim();
            String amountText = lblAmount.getText().trim();


            if (paymentText.isEmpty() || amountText.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Payment and Amount fields cannot be empty").show();
                return;
            }


            double payment = tryParseDouble(paymentText);
            double amount = tryParseDouble(amountText);


            if (payment == -1 || amount == -1) {
                new Alert(Alert.AlertType.ERROR, "Invalid number format in Payment or Amount").show();
                return;
            }


            double fullPayment = payment - amount;


            lblFullPay.setText(String.format("%.2f", fullPayment));

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "An unexpected error occurred while calculating the full payment").show();
        }
    }


    private double tryParseDouble(String value) {
        try {
            return Double.parseDouble(value);  // Try parsing as Double
        } catch (NumberFormatException e) {
            return -1;  // Return -1 if the parsing fails
        }
    }

    private void selectTableRow() {
        tblPayment.setOnMouseClicked(event -> {

            int focusedIndex = tblPayment.getFocusModel().getFocusedIndex();
            PaymentTm paymentTm = tblPayment.getItems().get(focusedIndex);
            txtPaymentId.setText(paymentTm.getId());
            cmbStId.setValue(paymentTm.getStudentId());
            cmbProId.setValue(paymentTm.getProgrammeId());
            lblPayment.setText(String.valueOf(paymentTm.getFee()));
            lblAmount.setText(String.valueOf(paymentTm.getRegisterFee()));
            lblFullPay.setText(String.valueOf(paymentTm.getTotalFee()));


        });
    }

}
