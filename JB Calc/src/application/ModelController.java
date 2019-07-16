package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ModelController {
	
	private double var1,var2,result;
	private String op_old="",op="";
	private boolean start=true,calFlag=true,opFlag=true;
	
	@FXML
	private Label label,label1;
	
	@FXML
	public void processOperator(ActionEvent event)
	{
		String value=((Button)event.getSource()).getText();
		if(!value.equals("="))
		{
			if(opFlag)
			{
				op=value;
				opFlag=false;
			}
			else
			{
				op_old=op;
				op=value;
			}
			if(!label.getText().isEmpty())
			{
				var1=Double.parseDouble(label.getText());
				if(calFlag)
				{
					calFlag=false;
					result=var1;
				}
				else
				{
					result=Model.calculate(result, var1, op_old);
					System.out.println(result);
				}
				label.setText("");
			}
			String c=label1.getText();
			int t=c.length()-1;
			if(c.charAt(t)=='+' || c.charAt(t)=='-' || c.charAt(t)=='*' || c.charAt(t)=='/')
			{
				label1.setText(c.substring(0,t)+value);
			}
			else
			{
				label1.setText(label1.getText()+value);
			}
		}
		else
		{
			if(!op.isEmpty())
			{
				var2=Double.parseDouble(label.getText());
				result=Model.calculate(result, var2, op);
				label.setText(String.valueOf(result));
				label1.setText(label1.getText()+"="+String.valueOf(result));
				op="";
				op_old="";
				start=true;
				calFlag=true;
				opFlag=true;
			}
			else
			{
				label.setText("Error....  please press C to reset");
			}
		}
	}
	
	@FXML
	public void pressC(ActionEvent event)
	{
		label.setText("");
		label1.setText("");
		op="";
		op_old="";
		start=true;
		calFlag=true;
		opFlag=true;
	}
	
	@FXML
	public void processNumber(ActionEvent event)
	{
		if(start)
		{
			start=false;
			label.setText("");
			label1.setText("");
		}
		String value=((Button)event.getSource()).getText();
		label.setText(label.getText()+value);
		label1.setText(label1.getText()+value);
	}
	
	@FXML
	public void processAbout(ActionEvent event)
	{
		try
		{
			Stage primaryStage=new Stage();
			Parent root=FXMLLoader.load(getClass().getResource("About.fxml"));
			Scene scene=new Scene(root);
			primaryStage.setTitle("JB Calc");
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		catch(Exception e) {}
	}
}