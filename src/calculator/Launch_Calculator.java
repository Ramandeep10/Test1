/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Raman
 */
public class Launch_Calculator extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
       
        // this will load the .fxml document
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CalculatorView.fxml"));
        
        AnchorPane calculatorPane = loader.load();
        
        Scene scene = new Scene(calculatorPane);
        
        primaryStage.setTitle("The Calculator!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
