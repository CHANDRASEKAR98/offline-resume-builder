package com.fxcontroller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import com.fxservice.PdfService;
import com.fxutility.CommonMessage;
import com.fxutility.MaximumLengthData;
import com.fxvalidation.CommonValidation;
import com.fxvalidation.LengthValidation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class MainController implements Initializable {
	
	CommonValidation commonValidation = new CommonValidation();
	
	PdfService pdfService = new PdfService();
	
	@FXML
	private AnchorPane anchorId;
	
	@FXML
	private Label label;
	
	// Personal Details
	@FXML
	private TextField fullName;
	
	@FXML
	private TextField emailId;
	
	@FXML
	private TextField mobileNo;
	
	@FXML
	private DatePicker dob;
	
	@FXML
	private TextField fatherName;
	
	@FXML
	private ComboBox<String> nation;
	
	@FXML
	private TextField address1;
	
	@FXML
	private TextField address2;
	
	// Objective
	
	@FXML
	private TextArea objectiveText;
	
	// Education details
	
	@FXML
	private TextField schoolName1;
	
	@FXML
	private TextField schoolName2;
	
	@FXML
	private TextField schoolName3;
	
	@FXML
	private TextField qualification1;
	
	@FXML
	private TextField qualification2;
	
	@FXML
	private TextField qualification3;
	
	@FXML
	private TextField field1;
	
	@FXML
	private TextField field2;
	
	@FXML
	private TextField field3;
	
	@FXML
	private TextField score1;
	
	@FXML
	private TextField score2;
	
	@FXML
	private TextField score3;
	
	@FXML
	private TextField yearOfPassing1;
	
	@FXML
	private TextField yearOfPassing2;
	
	@FXML
	private TextField yearOfPassing3;
	
	// Technical skills
	
	@FXML
	private TextField webTechnologies;
	
	@FXML
	private TextField programmingLanguages;
	
	@FXML
	private TextField database;
	
	@FXML
	private TextField otherSkills;
	
	// Academic Projects
	
	@FXML
	private TextField projectName;
	
	@FXML
	private TextField projectRole;
	
	@FXML
	private TextArea projectDesc;
	
	// Certification and Training
	
	@FXML
	private ComboBox<String> certification1;
	
	@FXML
	private ComboBox<String> certification2;
	
	@FXML
	private RadioButton trainingAttendedRadio;
	
	@FXML
	private RadioButton trainingNotAttendedRadio;
	
	@FXML
	private TextField trainingCompanyName;
	
	@FXML
	private TextField trainingDesignation;
	
	// Workshops and seminars
	
	@FXML
	private RadioButton workshopAttendedRadio;
	
	@FXML
	private RadioButton workshopNotAttendedRadio;
	
	@FXML
	private TextField workshopTitle;
	
	@FXML
	private TextField workshopVenue;
	
	@FXML
	private RadioButton seminarAttendedRadio;
	
	@FXML
	private RadioButton seminarNotAttendedRadio;
	
	@FXML
	private TextField seminarTitle;
	
	@FXML
	private TextField seminarVenue;
	
	// Choose Image
	
	@FXML
	private Button chooseImage;
	
	@FXML
	private Label imageLabel;
	
	@FXML
	private ImageView imageView;
	
	// Declaration
	
	@FXML
	private CheckBox declaration;
	
	private File file;
	
	public void radioSelect(ActionEvent event) {
		if(event.getSource().equals(trainingAttendedRadio) || event.getSource().equals(trainingNotAttendedRadio)) {
			commonValidation.isAttended(trainingAttendedRadio, trainingNotAttendedRadio, trainingCompanyName, 
					trainingDesignation);
		}
		
		if(event.getSource().equals(workshopAttendedRadio) || event.getSource().equals(workshopNotAttendedRadio)) {
			commonValidation.isAttended(workshopAttendedRadio, workshopNotAttendedRadio, workshopTitle, 
					workshopVenue);
		}
		
		if(event.getSource().equals(seminarAttendedRadio) || event.getSource().equals(seminarNotAttendedRadio)) {
			commonValidation.isAttended(seminarAttendedRadio, seminarNotAttendedRadio, seminarTitle, 
					seminarVenue);
		}
	}
	
	public void chooseImageFile(ActionEvent event) {
		try {
			file = commonValidation.chooseFile(anchorId, imageLabel,imageView);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void createResume(ActionEvent event) throws JRException {
		label.setText("");
		if(commonValidation.emptyCheck(fullName) && 
				commonValidation.emptyCheck(emailId) && commonValidation.emptyCheck(mobileNo) && commonValidation.emptyCheck(fatherName)
				&& commonValidation.emptyCheck(address1) && commonValidation.emptyCheck(address2) && !dob.getValue().toString().isEmpty()
				&& !nation.getValue().isEmpty()) {
			if(commonValidation.validateInput(label, fullName) && commonValidation.validateEmail(label, emailId) && 
					commonValidation.validateMobileNo(label, mobileNo) && declaration.isSelected()) {
				Stage stage = (Stage) anchorId.getScene().getWindow();
				final DirectoryChooser directoryChooser = new DirectoryChooser();
				File directoryFile = directoryChooser.showDialog(stage);
				if(directoryFile != null && commonValidation.saveConfirmationDialog()) {
					generateResume(directoryFile, fullName, label);
				}
			} else if(!commonValidation.validateInput(label, fullName)) {
				label.setTextFill(Color.RED);
				label.setText(CommonMessage.INVALID_NAME);
			} else if(!commonValidation.validateEmail(label, emailId)) {
				label.setTextFill(Color.RED);
				label.setText(CommonMessage.INVALID_EMAIL);
			} else if(!commonValidation.validateMobileNo(label, mobileNo)) {
				label.setTextFill(Color.RED);
		    	label.setText(CommonMessage.INVALID_MOBILE);
			} else if(!declaration.isSelected()) {
				label.setTextFill(Color.RED);
		    	label.setText(CommonMessage.DECLARATION);
			}
		} else {
			label.setTextFill(Color.RED);
		    label.setText(CommonMessage.EMPTY);
		}
	}
	
	private void generateResume(File file, TextField fullName, Label label) throws JRException {
		// Compile jrxml file.
		JasperReport jasperReport = JasperCompileManager
				.compileReport(MainController.class.getResourceAsStream("/resume.jrxml"));

		// Parameters for report
		Map<String, Object> parameters = new HashMap<>();	
		setPdfParameters(parameters);
    
		// DataSource
		// This is done offline, so no database.
		// Hence using empty datasource.
		JRDataSource dataSource = new JREmptyDataSource();
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
				parameters, dataSource);

		// Export to PDF.
		JasperExportManager.exportReportToPdfFile(jasperPrint,
				file.getAbsolutePath()+"/"+fullName.getText()+".pdf");
		label.setTextFill(Color.DARKGREEN);
		label.setText("The Resume has been saved successfully!!!");
	}

	private void setPdfParameters(Map<String, Object> parameters) {
		parameters.put("fullName", commonValidation.formatNullCheck(fullName.getText()));
		parameters.put("emailId", commonValidation.formatNullCheck(emailId.getText()));
		parameters.put("mobileNo", commonValidation.formatNullCheck(mobileNo.getText()));
		parameters.put("objectiveText", commonValidation.formatNullCheck(objectiveText.getText()));
		pdfService.educationalBackground(schoolName1,schoolName2,
				schoolName3,qualification1,qualification2,qualification3,field1,field2,field3,score1,score2,score3,
				yearOfPassing1,yearOfPassing2,yearOfPassing3,parameters);
		pdfService.technicalSkills(webTechnologies,programmingLanguages,database,otherSkills,parameters);
		pdfService.academicProject(projectName,projectRole,projectDesc,parameters);
		pdfService.certificationTraining(certification1,certification2,trainingCompanyName,trainingDesignation,parameters);
		pdfService.setYourImage(file,parameters);
		pdfService.setWorkshopAndSeminarDetails(workshopTitle,workshopVenue,seminarTitle,seminarVenue,parameters);
		pdfService.setPersonalDetails(dob,fatherName,nation,address1,address2,parameters);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nation.setItems(CommonMessage.nationality);
		certification1.setItems(CommonMessage.certificationList);
		certification2.setItems(CommonMessage.certificationList);
		maxLength();
	}
	
	private void maxLength() {
		fullName.textProperty().addListener(new LengthValidation(fullName, MaximumLengthData.FULLNAME_MAX));
		emailId.textProperty().addListener(new LengthValidation(emailId, MaximumLengthData.EMAIL_MAX));
		mobileNo.textProperty().addListener(new LengthValidation(mobileNo, MaximumLengthData.MOBILE_MAX));
		fatherName.textProperty().addListener(new LengthValidation(fatherName, MaximumLengthData.FULLNAME_MAX));
		address1.textProperty().addListener(new LengthValidation(address1, MaximumLengthData.ADDRESS));
		address2.textProperty().addListener(new LengthValidation(address2, MaximumLengthData.ADDRESS));
		
		objectiveText.textProperty().addListener(new LengthValidation(objectiveText, MaximumLengthData.OBJECTIVE));
		schoolName1.textProperty().addListener(new LengthValidation(schoolName1, MaximumLengthData.SCHOOLNAME));
		schoolName2.textProperty().addListener(new LengthValidation(schoolName2, MaximumLengthData.SCHOOLNAME));
		schoolName3.textProperty().addListener(new LengthValidation(schoolName3, MaximumLengthData.SCHOOLNAME));
		qualification1.textProperty().addListener(new LengthValidation(qualification1, MaximumLengthData.QUALIFICATION));
		qualification2.textProperty().addListener(new LengthValidation(qualification2, MaximumLengthData.QUALIFICATION));
		qualification3.textProperty().addListener(new LengthValidation(qualification3, MaximumLengthData.QUALIFICATION));
		field1.textProperty().addListener(new LengthValidation(field1, MaximumLengthData.FIELD_OF_STUDY));
		field2.textProperty().addListener(new LengthValidation(field2, MaximumLengthData.FIELD_OF_STUDY));
		field3.textProperty().addListener(new LengthValidation(field3, MaximumLengthData.FIELD_OF_STUDY));
		score1.textProperty().addListener(new LengthValidation(score1, MaximumLengthData.MARKS));
		score2.textProperty().addListener(new LengthValidation(score2, MaximumLengthData.MARKS));
		score3.textProperty().addListener(new LengthValidation(score3, MaximumLengthData.MARKS));
		yearOfPassing1.textProperty().addListener(new LengthValidation(yearOfPassing1, MaximumLengthData.Year));
		yearOfPassing2.textProperty().addListener(new LengthValidation(yearOfPassing2, MaximumLengthData.Year));
		yearOfPassing3.textProperty().addListener(new LengthValidation(yearOfPassing3, MaximumLengthData.Year));
		
		projectName.textProperty().addListener(new LengthValidation(projectName, MaximumLengthData.PROJECT_TITLE));
		projectRole.textProperty().addListener(new LengthValidation(projectRole, MaximumLengthData.PROJECT_ROLE));
		projectDesc.textProperty().addListener(new LengthValidation(projectDesc, MaximumLengthData.PROJECT_DESC));
		
		webTechnologies.textProperty().addListener(new LengthValidation(webTechnologies, MaximumLengthData.SKILLS));
		database.textProperty().addListener(new LengthValidation(database, MaximumLengthData.SKILLS));
		programmingLanguages.textProperty().addListener(new LengthValidation(programmingLanguages, MaximumLengthData.SKILLS));
		otherSkills.textProperty().addListener(new LengthValidation(otherSkills, MaximumLengthData.SKILLS));
		
		workshopTitle.textProperty().addListener(new LengthValidation(workshopTitle, MaximumLengthData.WORKSHOPS));
		seminarTitle.textProperty().addListener(new LengthValidation(seminarTitle, MaximumLengthData.WORKSHOPS));
		seminarVenue.textProperty().addListener(new LengthValidation(seminarVenue, MaximumLengthData.VENUE));
		workshopVenue.textProperty().addListener(new LengthValidation(workshopVenue, MaximumLengthData.VENUE));
	}
}
