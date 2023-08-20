/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package uiu.event.management.system.ui.addSlot;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import uiu.event.management.system.database.DatabaseHandler;

/**
 * FXML Controller class
 *
 * @author offic
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField assign;
    @FXML
    private TextField purpose;
    @FXML
    private TextField slot;
    @FXML
    private TextField atime;
    @FXML
    private TextField adate;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;
    
    DatabaseHandler databaseHandler;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        datbaseHandler = new DatabaseHandler();
    }    
    private void addSlot(ActionEvent event){
        String SlotID=slot.getText();
        String Epurpose=purpose.getText();
        String Eassign=assign.getText();
        String Etime=atime.getText();
        String Edate=adate.getText();
        if(SlotID.isEmpty()||Epurpose.isEmpty()||Eassign.isEmpty()||Etime.isEmpty()){
            Alert.alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Enter in all Fields");
            alert.showAndWait();
            return;
        }
        stmt.execute("CREATE TABLE" + TABLE_NAME + " ("
                    +"slot varchar(100)primary key, \n" 
                    +"assign varchar (200), \n"
                    +"purpose varchar(200), \n"
                    +"atime varchar(100), \n" 
                    +"adate varchar(100), \n"
                    +"isAvail boolean default true"
                    +"}");
        String qu = "INSERT INTO EVENT VALUES("+
            "'"+SlotID+"'"+
            "'"+Epurpose+"'"+
            "'"+Eassign+"'"+
            "'"+Etime+"'"+
            "'"+true+"'"+
                ")";
        System.out.println(qu);
        if(databaseHandler.execAction(qu))
        {
          Alert.alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("SUCCESS");
            alert.showAndWait();
        }else
        {
            Alert.alert = new Alert(Alert.AlertType.ERROE);
            alert.setHeaderText(null);
            alert.setContentText("FAILED");
            alert.showAndWait();
        }
    }

    @FXML
    private void save(ActionEvent event) {
    }

    @FXML
    private void cancel(ActionEvent event) {
    }
    
}
