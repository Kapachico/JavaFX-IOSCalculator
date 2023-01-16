package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Controller {
	
	
	private int nb1;
	private int nb2;
	private String op="";
	private boolean start =true;
	
	@FXML
	private Label Output;
	@FXML
	public void pressedNumPad(ActionEvent e) {
		//To remove the Initial 0 found in Label
		if(start) {
			Output.setText("");
			start=false;
		}
		String value = ((Button)e.getSource()).getText();
		Output.setText(Output.getText()+value);
	}
	@FXML
	public void pressedOp(ActionEvent e) {
		String value = ((Button)e.getSource()).getText();
		//Case Operators
		if(!(value.equals("="))) {
			//If already there is an operator
			if(!(op.isEmpty())) {
				return;
			}
			op=value;
			nb1= Integer.parseInt(Output.getText());
			Output.setText("");
		}
		//Case of =
		else {
			if(op.isEmpty()) {
				return;
			}
			//If no second number
			if(Output.getText().isEmpty()) {
				Output.setText("INVALID");
				op="";
				start=true;
			}
			Output.setText(calculate(nb1,Integer.parseInt(Output.getText()),op));
			op="";
			start=true;
		}
		
	}
	
	@FXML
	public void clear(ActionEvent e) {
		Output.setText("");
		op="";
		start=true;
	}
	
	public String calculate(int nb1,int nb2,String op) {
		switch(op) {
			case "+":
			{
				return String.valueOf(nb1+nb2);
			}
			case "-":
			{
				return String.valueOf(nb1-nb2);
			}
			case "x":
			{
				return String.valueOf(nb1*nb2);
			}
			case "%":
			{
				return String.valueOf(nb1%nb2);
			}
			case "/":
			{
				if(nb2==0) {
					return "INVALID";
				}
				return String.valueOf(nb1/nb2);
			}
		}
		return "INVALID";	
	}
}
