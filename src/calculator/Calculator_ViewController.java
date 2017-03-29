/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Raman
 */
public class Calculator_ViewController implements Initializable {

    @FXML private Button oneButton;
    @FXML private Button twoButton;
    @FXML private Button threeButton;
    @FXML private Button fourButton;
    @FXML private Button fiveButton;
    @FXML private Button sixButton;
    @FXML private Button sevenButton;
    @FXML private Button eightButton;
    @FXML private Button nineButton;
    @FXML private Button zeroButton;
    @FXML private Button decimalButton;
    @FXML private Button multiplyButton;
    @FXML private Button divideButton;
    @FXML private Button addButton;
    @FXML private Button subtractButton;
    @FXML private Button equalButton;
    @FXML private TextField display;
    @FXML private Label numberStackLabel;
            
    private ArrayList<String> numberStack;
    private boolean overWriteNumberInDisplay;
    
    /**
     * When the number buttons are pushed, this method will update the 
     * display with the new number
     */
    public void numberButtonPushed(ActionEvent event)
    {
       String buttonValue = ((Button)event.getSource()).getText();      
       
       //check if 1 decimal is already in the number
       if (buttonValue.equals(".") && display.getText().contains("."))
            {}//ignore the button because there is already a decimal
       else if (overWriteNumberInDisplay)
       {
           display.setText(buttonValue);
           overWriteNumberInDisplay=false;
       }           
       else
           display.setText(display.getText() + buttonValue);
    }
    
    /**
     * This method will add the number from the display and the operator to the 
     * numberStack, it will then calculate the value of the stack and update the display
     */
    public void operatorButtonPushed(ActionEvent event)
    {
        String operator = ((Button)event.getSource()).getText();
        
        numberStack.add(display.getText()); //push the number on the stack
        numberStack.add(operator);          //push the operator on the stack
        numberStackLabel.setText(formatNumberStack());  //update the label to show the series of calculations
        
        DecimalFormat formatWithoutTrailingZeros = new DecimalFormat("0.#");    //this removes trailing zeros

        //update the result   
        display.setText(formatWithoutTrailingZeros.format(calculateStack()));
        overWriteNumberInDisplay = true;    
        
        //If the = sign was used, after the calculation, clear the stack
        if (operator.equals("="))
        {
            numberStack = new ArrayList<>();
            numberStackLabel.setText("");
        }        
    }
    

    /**
     * This method will parse over the ArrayList of entries.  Note: it does not support
     * order of operations.  It will calculate the numbers as they were entered (same as a regular calculator)
     * @return 
     */
    public double calculateStack()
    {        
        double result = 0;
        double num1=0;
        
        //get the first number in the stack
        if (!numberStack.isEmpty())
            num1 = Double.parseDouble(numberStack.get(0));
        
        double num2;
        String operator = "=";
        
        for(int i=1; i<numberStack.size(); i++)
        {
            String element = numberStack.get(i);
            
            //check if it is a number or an operator
            if (element.matches("[0-9]"))
            {
                num2 = Double.parseDouble(element);                
                result = calculate(num1, operator, num2);
                num1 = result;
            }                
            else
                operator = element;
        }
        return result;
    }       
    
    
    /**
     * This method will return the result of a calculation
     * @param num1 - a double
     * @param operator - +, -, / or *
     * @param num2 - a double
     * @return 
     */
    public double calculate(double num1, String operator, double num2)
    {
    if (operator.equals("+"))
        return num1+num2;
    else if (operator.equals("-"))
        return num1-num2;
    else if (operator.equals("*"))
        return num1*num2;
    else if (operator.equals("/"))
        return num1/num2;
    else
        return 0;
}
    
    
    /**
     * This method will return the elements of an
     * ArrayList as a formatted String
     */
    public String formatNumberStack()
    {
        String result = "";
        
        for (String element:numberStack)
        {
            result += String.format("%s",element);            
        }
        return result;
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        numberStack = new ArrayList<>();
        display.setText("0");
        overWriteNumberInDisplay = true;
        numberStackLabel.setText("");
    }    
}