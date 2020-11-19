/*
 * Filename: DetailedModelViewController.java
 * Short description: 
 * @author Mike Wagner
 * @version Nov 19, 2020
 */

package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.MedicalProfessionalModel;


/**
 *
 * @author Owner
 */
public class DetailedModelViewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    
    @FXML
    private Label detailedMedProfIDLabel;
    
    @FXML
    private Label detailedNameLabel;
    
    @FXML
    private ImageView imageView;
    
    @FXML
    private Button backBtn;
    
    @FXML
    private void detailedBackBtn(ActionEvent event){
        // option 1: get current stage -- from event
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        //  option 2: get current stage -- from backbutton        
        // Stage stage = (Stage)backButton.getScene().getWindow();
        
        if (previousScene != null) {
            stage.setScene(previousScene);
        }
    }
    
    MedicalProfessionalModel selectedModel;
    Scene previousScene;
    
    public void setPreviousScene(Scene scene) {
        previousScene = scene;
        backBtn.setDisable(false);

    }

    public void initData(MedicalProfessionalModel model) {
        selectedModel = model;
        detailedMedProfIDLabel.setText(model.getId().toString());
        detailedNameLabel.setText(model.getFirstname() + " " + model.getLastname());

        try {
            // path points to /resource/images/
            String imagename = "/resource/images/" + model.getFirstname() + model.getLastname() + ".png";
            System.out.println(imagename);
            Image profile = new Image(getClass().getResourceAsStream(imagename));
            imageView.setImage(profile);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
     @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert backBtn != null : "fx:id=\"backButtong\" was not injected: check your FXML file 'DetailModelView.fxml'.";
        assert detailedMedProfIDLabel != null : "fx:id=\"labelID\" was not injected: check your FXML file 'DetailModelView.fxml'.";
        assert detailedNameLabel != null : "fx:id=\"labelValue\" was not injected: check your FXML file 'DetailModelView.fxml'.";
        assert imageView != null : "fx:id=\"image\" was not injected: check your FXML file 'DetailModelView.fxml'.";

        backBtn.setDisable(true);

    }

}
