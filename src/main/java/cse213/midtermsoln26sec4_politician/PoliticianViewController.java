package cse213.midtermsoln26sec4_politician;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.ArrayList;

public class PoliticianViewController
{
    @javafx.fxml.FXML
    private TextField memberIdTextField;
    @javafx.fxml.FXML
    private TextField annualMembershipFeeTextField;
    @javafx.fxml.FXML
    private DatePicker dateOfJoiningDatePicker;
    @javafx.fxml.FXML
    private ComboBox<String> memberTypeComboBox;
    @javafx.fxml.FXML
    private TableColumn<Politician, String> memberIdTableColumn;
    @javafx.fxml.FXML
    private TableColumn<Politician, String> memberTypeTableColumn;
    @javafx.fxml.FXML
    private ComboBox<String> filterMemberType;
    @javafx.fxml.FXML
    private TableColumn<Politician, LocalDate> dateOfJoiningTableColumn;
    @javafx.fxml.FXML
    private TextField partyNameTextField;
    @javafx.fxml.FXML
    private Label outputLabel;
    @javafx.fxml.FXML
    private TableView<Politician> politicianTableView;
    @javafx.fxml.FXML
    private TableColumn<Politician, Boolean> hasWonAnyElectionTableColumn;
    @javafx.fxml.FXML
    private CheckBox isInGovtPositionCheckBox;
    @javafx.fxml.FXML
    private CheckBox filterHasWonAnyElectionCheckBox;
    @javafx.fxml.FXML
    private CheckBox hasWonAnyElectionCheckBox;
    @javafx.fxml.FXML
    private TableColumn<Politician, Boolean> isInGovtPositionTableColumn;
    private ArrayList<Politician> politicianList = new ArrayList<>();

    @javafx.fxml.FXML
    public void initialize() {

        memberTypeComboBox.getItems().addAll("President", "General Secretary", "Treasurer", "General Member");
        filterMemberType.getItems().addAll("President", "General Secretary", "Treasurer", "General Member");

        memberIdTableColumn.setCellValueFactory(new PropertyValueFactory<Politician, String>("memberId"));
        memberTypeTableColumn.setCellValueFactory(new PropertyValueFactory<Politician, String>("memberType"));
        isInGovtPositionTableColumn.setCellValueFactory(new PropertyValueFactory<Politician, Boolean>("isInGovtPosition"));
        dateOfJoiningTableColumn.setCellValueFactory(new PropertyValueFactory<Politician, LocalDate>("dateOfJoining"));
        hasWonAnyElectionTableColumn.setCellValueFactory(new PropertyValueFactory<Politician, Boolean>("hasWonAnyElection"));
    }

    @javafx.fxml.FXML
    public void filterButtonOnMouseClicked(Event event) {
        politicianTableView.getItems().clear();

        for (Politician p : politicianList){
            if (filterMemberType.getValue().equals(p.getMemberType())){
                if (filterHasWonAnyElectionCheckBox.isSelected() == p.isHasWonAnyElection()
                || !filterHasWonAnyElectionCheckBox.isSelected()){
                    politicianTableView.getItems().add(p);
                }
            }
        }
    }

    public void showAlert(String s) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setContentText(s);
        a.showAndWait();
    }

    @javafx.fxml.FXML
    public void addPoliticianButtonOnAction(ActionEvent actionEvent) {

        if (memberTypeComboBox.getValue().equals("General Member")){
            if (Float.parseFloat(annualMembershipFeeTextField.getText()) < 500
                    && Float.parseFloat(annualMembershipFeeTextField.getText()) > 5000){
                showAlert("Annual membership fee must be greater than 499 and less than 5001!");
                return;
            }
        } else {
            if (Float.parseFloat(annualMembershipFeeTextField.getText()) < 10000){
                showAlert("Annual membership fee must be greater than 9999!");
                return;
            }
        }

        if(isInGovtPositionCheckBox.isSelected() && memberTypeComboBox.getValue().equals("General Member")){
            showAlert("General Member can't be in Govt. position.");
            return;
        }

        // String memberId, String partyName, String memberType, boolean isInGovtPosition, boolean hasWonAnyElection,
        // float annualMembershipFee, LocalDate dateOfJoining
        Politician p = new Politician(
                memberIdTextField.getText(),
                partyNameTextField.getText(),
                memberTypeComboBox.getValue(),
                isInGovtPositionCheckBox.isSelected(),
                hasWonAnyElectionCheckBox.isSelected(),
                Float.parseFloat(annualMembershipFeeTextField.getText()),
                dateOfJoiningDatePicker.getValue()
        );
        politicianList.add(p);
        politicianTableView.getItems().clear();
        politicianTableView.getItems().addAll(politicianList);
    }

    @javafx.fxml.FXML
    public void showAverageMonthlyPayableButtonOnAction(ActionEvent actionEvent) {
        int count = 0;
        float average;
        float totalAmount = 0;
        if (politicianTableView.getItems().isEmpty()) {
            average = 0;
            outputLabel.setText("Average Monthly Payable: " + average);
        } else {
            for (Politician p : politicianList) {
                totalAmount += p.returnMonthlyPayableToPartyFund();
                count ++;
            }
            average = totalAmount / count;
            outputLabel.setText("Average Monthly Payable: " + average);
        }
    }
}