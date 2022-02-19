package com.fxservice;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import javax.imageio.ImageIO;

import com.fxvalidation.CommonValidation;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PdfService {
	CommonValidation commonValidation = new CommonValidation();
	
	public Map<String, Object> educationalBackground(TextField schoolName1, TextField schoolName2, TextField schoolName3, TextField qualification1, TextField qualification2, TextField qualification3, TextField field1, TextField field2, TextField field3, TextField score1, TextField score2, TextField score3, TextField yearOfPassing1, TextField yearOfPassing2, TextField yearOfPassing3, Map<String, Object> parameters) {
		
		parameters.put("line1",1);
		parameters.put("line2",2);
		parameters.put("line3",3);
		
		parameters.put("schoolName1",commonValidation.formatNullCheck(schoolName1.getText()));
		parameters.put("schoolName2",commonValidation.formatNullCheck(schoolName2.getText()));
		parameters.put("schoolName3",commonValidation.formatNullCheck(schoolName3.getText()));
		
		parameters.put("qualification1",commonValidation.formatNullCheck(qualification1.getText()));
		parameters.put("qualification2",commonValidation.formatNullCheck(qualification2.getText()));
		parameters.put("qualification3",commonValidation.formatNullCheck(qualification3.getText()));
		
		parameters.put("field1",commonValidation.formatNullCheck(field1.getText()));
		parameters.put("field2",commonValidation.formatNullCheck(field2.getText()));
		parameters.put("field3",commonValidation.formatNullCheck(field3.getText()));
		
		parameters.put("score1",commonValidation.formatNullCheck(score1.getText()));
		parameters.put("score2",commonValidation.formatNullCheck(score2.getText()));
		parameters.put("score3",commonValidation.formatNullCheck(score3.getText()));
		
		parameters.put("yearOfPassing1",commonValidation.formatNullCheck(yearOfPassing1.getText()));
		parameters.put("yearOfPassing2",commonValidation.formatNullCheck(yearOfPassing2.getText()));
		parameters.put("yearOfPassing3",commonValidation.formatNullCheck(yearOfPassing3.getText()));
		
		return parameters;
	}

	public Map<String, Object> technicalSkills(TextField webTechnologies, TextField programmingLanguages, TextField database,
			TextField otherSkills, Map<String, Object> parameters) {
		parameters.put("webTechnologies",commonValidation.formatNullCheck(webTechnologies.getText()));
		parameters.put("programmingLanguages",commonValidation.formatNullCheck(programmingLanguages.getText()));
		parameters.put("database",commonValidation.formatNullCheck(database.getText()));
		parameters.put("otherSkills",commonValidation.formatNullCheck(otherSkills.getText()));
		
		return parameters;
	}

	public void academicProject(TextField projectName, TextField projectRole, TextArea projectDesc,
			Map<String, Object> parameters) {
		parameters.put("projectName",commonValidation.formatNullCheck(projectName.getText()));
		parameters.put("projectRole",commonValidation.formatNullCheck(projectRole.getText()));
		parameters.put("projectDesc",commonValidation.formatNullCheck(projectDesc.getText()));
	}

	public void certificationTraining(ComboBox<String> certification1, ComboBox<String> certification2,
			TextField trainingCompanyName, TextField trainingDesignation, Map<String, Object> parameters) {
		parameters.put("certification1",commonValidation.formatNullCheck(certification1.getValue()));
		parameters.put("certification2",commonValidation.formatNullCheck(certification2.getValue()));
		parameters.put("trainingCompanyName",commonValidation.formatNullCheck(trainingCompanyName.getText()));
		parameters.put("trainingDesignation",commonValidation.formatNullCheck(trainingDesignation.getText()));
		
	}

	public void setYourImage(File file, Map<String, Object> parameters) {
		try {
			if(fileFormatCheck(file).exists()) {
			Image image = ImageIO.read(file);
			parameters.put("yourImage",image);
			}
		} catch(IOException exception) {
			exception.printStackTrace();
		}
	}

	public void setWorkshopAndSeminarDetails(TextField workshopTitle, TextField workshopVenue, TextField seminarTitle,
			TextField seminarVenue, Map<String, Object> parameters) {
		parameters.put("workshopTitle",commonValidation.formatNullCheck(workshopTitle.getText()));
		parameters.put("workshopVenue",commonValidation.formatNullCheck(workshopVenue.getText()));
		parameters.put("seminarTitle",commonValidation.formatNullCheck(seminarTitle.getText()));
		parameters.put("seminarVenue",commonValidation.formatNullCheck(seminarVenue.getText()));
	}

	public void setPersonalDetails(DatePicker dob, TextField fatherName, ComboBox<String> nation, TextField address1,
			TextField address2, Map<String, Object> parameters) {
		parameters.put("dateOfBirth",commonValidation.formatNullCheck(dob.getValue()));
		parameters.put("fatherName",commonValidation.formatNullCheck(fatherName.getText()));
		parameters.put("nationality",commonValidation.formatNullCheck(nation.getValue()));
		parameters.put("address1",commonValidation.formatNullCheck(address1.getText()));
		parameters.put("address2",commonValidation.formatNullCheck(address2.getText()));
		
	}
	
	private File fileFormatCheck(File file) {
		return (Objects.nonNull(file)) ? file : new File("");
	}

}

