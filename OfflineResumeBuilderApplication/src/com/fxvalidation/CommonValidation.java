package com.fxvalidation;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

import javax.imageio.ImageIO;

import com.fxutility.CommonMessage;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.ButtonType;

public class CommonValidation {
	
	private static boolean flag = false;
	
	public void isAttended(RadioButton radio1, RadioButton radio2, TextField field1, TextField field2) {
		if(radio1.isSelected()) {
			field1.setDisable(false);
			field2.setDisable(false);
		}
		
		if(radio2.isSelected()) {
			field1.clear();
			field2.clear();
			field1.setDisable(true);
			field2.setDisable(true);
		}
	}
	
	public String formatNullCheck(Object obj) {
		return Objects.nonNull(obj) ? obj.toString() : "";
	}
	
	public boolean validateInput(Label label, TextField value) {
		if(!value.getText().matches("[A-Za-z\\s]+")) {
			flag = false;
		} else {
			label.setText("");
			flag = true;
		}
		return flag;
	}

	public boolean validateEmail(Label label, TextField emailId) {
		if(!emailId.getText().matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
			flag = false;
		} else {
			label.setText("");
			flag = true;
		}
		return flag;
	}

	public boolean validateMobileNo(Label label, TextField mobileNo) {
		if(!mobileNo.getText().matches("(0/91)?[7-9][0-9]{9}")) {
			flag = false;
		} else {
			label.setText("");
			flag = true;
		}
		return flag;
	}
	
	public boolean emptyCheck(TextField value) {
		if(value.getText().isEmpty()) {
			return false;
		}
		return true;
	}
	
	public boolean saveConfirmationDialog() {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText(CommonMessage.SAVE_CONFIRMATION);
		Optional<ButtonType> result = alert.showAndWait();
		if(result.get() == ButtonType.OK) {
			return true;
		}
		return false;
	}
	
	public File chooseFile(AnchorPane anchorId, Label imageLabel, ImageView imageView) throws IOException {
		FileChooser fileChooser = new FileChooser();
		
		// set extension filter
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(extFilterJPG,extFilterPNG);
		
		// show open file dialog
		Stage stage = (Stage) anchorId.getScene().getWindow();
		File file = fileChooser.showOpenDialog(stage);
		if(fileFormatCheck(file).exists()) {
			BufferedImage buffImage = ImageIO.read(file);
			WritableImage image = SwingFXUtils.toFXImage(buffImage, null);
		
			// set file name and image
			imageLabel.setText(fileFormatCheck(file).getName());
			imageView.setImage(image);
		}
		return fileFormatCheck(file);
	}
	
	private File fileFormatCheck(File file) {
		return (Objects.nonNull(file)) ? file : new File("");
	}
}
