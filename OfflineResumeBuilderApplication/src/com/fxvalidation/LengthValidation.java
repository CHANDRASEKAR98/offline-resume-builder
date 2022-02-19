package com.fxvalidation;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextInputControl;

public class LengthValidation implements javafx.beans.value.ChangeListener<Object>{
	
	private Integer maxLength;
	private TextInputControl textField;
	
	public LengthValidation(TextInputControl textField, Integer maxLength) {
		this.maxLength = maxLength;
		this.textField = textField;
	}

	public Integer getMaxLength() {
		return maxLength;
	}

	@Override
	public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
		if(newValue == null) {
			return;
		}
		
		if(newValue.toString().length() > maxLength.intValue()) {
			textField.setText(oldValue.toString());
		}
		else {
			textField.setText(newValue.toString());
		}
		
	}
}
