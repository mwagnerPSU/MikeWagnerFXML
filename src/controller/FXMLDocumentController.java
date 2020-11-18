/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JButton;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.MedicalProfessionalModel;

/**
 *
 * @author Owner
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    private JButton createBtn;
    private JButton readBtn;
    private JButton updateBtn;
    private JButton deleteBtn;
    private JButton clickBtn;
    private JButton fullNameButton;
    private JButton firstCredsButton;
    private Label inputLabel;
    private TextField inputTextField;
    private JButton enterBtn;
    @FXML
    private TableView <MedicalProfessionalModel> tableView;
    @FXML
    private TableColumn <MedicalProfessionalModel, Integer> profID;
    @FXML
    private TableColumn <MedicalProfessionalModel, String> profFName;
    @FXML
    private TableColumn <MedicalProfessionalModel, String> profLName;
    @FXML
    private TableColumn <MedicalProfessionalModel, String> profCreds;
    
    private ObservableList<MedicalProfessionalModel> medProfData;
    
    public void setTableData(List<MedicalProfessionalModel> profList){
        medProfData = FXCollections.observableArrayList();
        
        profList.forEach(mP -> {
            medProfData.add(mP);
        });
        
        tableView.setItems(medProfData);
        tableView.refresh();
    }
    
    //create operation
    public void create(MedicalProfessionalModel mpPerson) {
        try {
            // begin transaction
            manager.getTransaction().begin();
            
            // sanity check
            if (mpPerson.getId() != null) {
                
                // create student
                manager.persist(mpPerson);
                
                // end transaction
                manager.getTransaction().commit();
                
                System.out.println("Added: ID: " + mpPerson.getId() + " | Name: " + mpPerson.getFirstname() + " " + mpPerson.getLastname() + " | Credentials: " + mpPerson.getCredentials());
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
        //update operation
    public void update(MedicalProfessionalModel model) {
        try {

            MedicalProfessionalModel existingMedicalProfessional = manager.find(MedicalProfessionalModel.class, model.getId());

            if (existingMedicalProfessional != null) {
                // begin transaction
                manager.getTransaction().begin();
                
                // update all atttributes
                existingMedicalProfessional.setFirstname(model.getFirstname());
                existingMedicalProfessional.setLastname(model.getLastname());
                existingMedicalProfessional.setCredentials(model.getCredentials());
                
                // end transaction
                manager.getTransaction().commit();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

     //delete operation
    public void delete(MedicalProfessionalModel mpPerson) {
        try {
            MedicalProfessionalModel existingMedicalProfessional = manager.find(MedicalProfessionalModel.class, mpPerson.getId());

            // sanity check
            if (existingMedicalProfessional != null) {
                
                // begin transaction
                manager.getTransaction().begin();
                
                //remove student
                manager.remove(existingMedicalProfessional);
                
                // end transaction
                manager.getTransaction().commit();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    //read operation
    public List<MedicalProfessionalModel> readAll(){
        Query query = manager.createNamedQuery("MedicalProfessionalModel.findAll");
        List<MedicalProfessionalModel> mpPersons = query.getResultList();

        for (MedicalProfessionalModel p : mpPersons) {
            System.out.println("ID: " + p.getId() + " | Name: " + p.getFirstname() + " " + p.getLastname() + " | Credentials: " + p.getCredentials());
        }
        
        return mpPersons;
    }
    
    public MedicalProfessionalModel readById(int id){
        Scanner userInput = new Scanner(System.in);
        Query query = manager.createNamedQuery("MedicalProfessionalModel.findById");
        
        // setting query parameter
        query.setParameter("id", id);
        
        // execute query
        MedicalProfessionalModel mpPerson = (MedicalProfessionalModel) query.getSingleResult();
        if (mpPerson != null){
            System.out.println("ID: " + mpPerson.getId() + " | Name: " + mpPerson.getFirstname() + " " + mpPerson.getLastname() + " | Credentials: " + mpPerson.getCredentials());
        }
        
        return mpPerson;        
    }
    
    public List<MedicalProfessionalModel> readByFName(String fName){
        Query query = manager.createNamedQuery("MedicalProfessionalModel.findByFirstname");
        
        // setting query parameter
        query.setParameter("firstname", fName);
        
        // execute query
        List<MedicalProfessionalModel> mpPerson =  query.getResultList();
        for (MedicalProfessionalModel person: mpPerson) {
            System.out.println("ID: " + person.getId() + " | Name: " + person.getFirstname() + " " + person.getLastname() + " | Credentials: " + person.getCredentials());
        }
        
        return mpPerson;
    }
    
    public List<MedicalProfessionalModel> readByLName(String lName){
        Query query = manager.createNamedQuery("MedicalProfessionalModel.findByLastname");
        
        // setting query parameter
        query.setParameter("lastname", lName);
        
        // execute query
        List<MedicalProfessionalModel> mpPerson =  query.getResultList();
        for (MedicalProfessionalModel person: mpPerson) {
            System.out.println("ID: " + person.getId() + " | Name: " + person.getFirstname() + " " + person.getLastname() + " | Credentials: " + person.getCredentials());
        }
        
        return mpPerson;
    }
    
    public List<MedicalProfessionalModel> readByCredentials(String credentials){
        Query query = manager.createNamedQuery("MedicalProfessionalModel.findByCredentials");
        
        query.setParameter("credentials", credentials);
        
        // execute query
        List<MedicalProfessionalModel> mpPerson =  query.getResultList();
        for (MedicalProfessionalModel person: mpPerson) {
            System.out.println("ID: " + person.getId() + " | Name: " + person.getFirstname() + " " + person.getLastname() + " | Credentials: " + person.getCredentials());
        }
        
        return mpPerson;
    }
    
    public List<MedicalProfessionalModel> readByFullName(String fName, String lName){
        Query query = manager.createNamedQuery("MedicalProfessionalModel.findByFullName");
        
        // setting query parameter
        query.setParameter("firstname", fName);
        query.setParameter("lastname", lName);
        
        // execute query
        List<MedicalProfessionalModel> mpPersons =  query.getResultList();
        for (MedicalProfessionalModel person: mpPersons) {
            System.out.println("ID: " + person.getId() + " | Name: " + person.getFirstname() + " " + person.getLastname() + " | Credentials: " + person.getCredentials());
        }
        
        return mpPersons;
    }   
    
     
    public List<MedicalProfessionalModel> readByNameAndCredentials(String fName, String credentials){
        Query query = manager.createNamedQuery("MedicalProfessionalModel.findByFirstNameAndCredentials");
        
        // setting query parameter
        query.setParameter("credentials", credentials);
        query.setParameter("firstname", fName);
        
        
        // execute query
        List<MedicalProfessionalModel> mpPersons =  query.getResultList();
        for (MedicalProfessionalModel person: mpPersons) {
            System.out.println("ID: " + person.getId() + " | Name: " + person.getFirstname() + " " + person.getLastname() + " | Credentials: " + person.getCredentials());
        }
        
        return mpPersons;
    }       
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @FXML
    private void createMedicalProfessional(ActionEvent event){  
        //used source code as help
        Scanner userInput = new Scanner(System.in);
        
        System.out.print("Enter ID: ");
        int id = userInput.nextInt();
        System.out.println("");
        
        System.out.print("Enter Credentials: ");
        String creds = userInput.next();
        System.out.println("");
        
        System.out.print("Enter First Name: ");
        String fName = userInput.next();
        System.out.println("");
        
        System.out.print("Enter Last Name: ");
        String lName = userInput.next();
        System.out.println("");
        
        MedicalProfessionalModel newMPPerson = new MedicalProfessionalModel();
        
        newMPPerson.setId(id);
        newMPPerson.setCredentials(creds);
        newMPPerson.setFirstname(fName);
        newMPPerson.setLastname(lName);
        
        create(newMPPerson);
    }
    
    @FXML
    private void readMedicalProfessionalInfo(ActionEvent event){  
        Scanner userInput = new Scanner (System.in);
        
        System.out.println("How would like to find the person?");
        System.out.println("(1) All, (2) ID, (3) First Name, (4) Last Name, (5) Credentials");
        System.out.print("Enter a number (1, 2, 3, 4, 5): ");
        int choice = userInput.nextInt();
        
        if (choice == 1){
            List <MedicalProfessionalModel> mpPerson = readAll();
        }
        
        if (choice == 2){
            // read input from command line
            System.out.print("Enter ID: ");
            int id = userInput.nextInt();
            System.out.println("");

            MedicalProfessionalModel mpPerson = readById(id);
        }
        
        if (choice == 3){
            // read input from command line
            System.out.print("Enter First Name: ");
            String name = userInput.next();
            System.out.println("");

            List<MedicalProfessionalModel> mpPersonFName = readByFName(name);
        }
        
        if (choice == 4){
            // read input from command line
            System.out.print("Enter Last Name: ");
            String name = userInput.next();
            System.out.println("");
            
            List<MedicalProfessionalModel> mpPersonLName = readByLName(name);
        }
        
        if (choice == 5){
            // read input from command line
            System.out.print("Enter Credentials: ");
            String credential = userInput.next();
            System.out.println("");

            List<MedicalProfessionalModel> mpPerson = readByCredentials(credential);
        }
    }
    
    @FXML
    private void updateMedicalProfessionalInfo(ActionEvent event){  
        Scanner input = new Scanner(System.in);
        
        MedicalProfessionalModel mpPerson = new MedicalProfessionalModel();
        
        // read input from command line
        System.out.print("Enter ID: ");
        int id = input.nextInt();
        System.out.println("");
        
        System.out.print("Original: "); readById(id);
        System.out.println("");
        
        System.out.print("Enter new or original first name: ");
        String fName = input.next();
        System.out.println("");
        
        System.out.print("Enter new or original last name: ");
        String lName = input.next();
        System.out.println("");
        
        System.out.print("Enter new or original credentials: ");
        String credentials = input.next();
        System.out.println("");
        
        
        // set properties
        mpPerson.setId(id);
        mpPerson.setFirstname(fName);
        mpPerson.setLastname(lName);
        mpPerson.setCredentials(credentials);
        
        // save this student to database by calling Create operation        
        update(mpPerson);
        
        System.out.print("Update: "); readById(id);
        System.out.println("");
    }
    
    @FXML
    private void deleteMedicalProfessional(ActionEvent event){  
        Scanner userInput = new Scanner(System.in);
        MedicalProfessionalModel mpPerson = new MedicalProfessionalModel();
        
        System.out.print("Enter ID: ");
        int id = userInput.nextInt();
        System.out.println("");

        System.out.println("Are you sure you want to delete: ");
        mpPerson = readById(id);
        
        System.out.print("Type Y/N: ");
        String confirmation = userInput.next();
        System.out.println("");
        
        if(confirmation.equals("Y")){
            System.out.println("This medical professional has been deleted: ");
            System.out.println("ID: " + mpPerson.getId() + " | Name: " + mpPerson.getFirstname() + " " + mpPerson.getLastname() + " | Credentials: " + mpPerson.getCredentials());
            System.out.println("");
            delete(mpPerson);
        }
    }
    
    @FXML
    private void readMedicalProfessionalInfoByFullName(ActionEvent event){
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter First Name: ");
        String fName = input.next();
        System.out.println("");

        System.out.print("Enter Last Name: ");
        String lName = input.next();
        System.out.println("");
            
        List<MedicalProfessionalModel> mpPersons = readByFullName(fName, lName);
    }
    
    @FXML
    private void readMedicalProfessionalInfoByFirstNameAndCreds(ActionEvent event){
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter First Name: ");
        String fName = input.next();
        System.out.println("");

        System.out.print("Enter Credentials: ");
        String credentials = input.next();
        System.out.println("");
            
        List<MedicalProfessionalModel> mpPersons = readByNameAndCredentials(fName, credentials);
    }
    
    @FXML 
    private void searchData(ActionEvent event){
        System.out.println("Clicked");
        String name = inputTextField.getText();
        
        List<MedicalProfessionalModel> mpPersons = readByFName(name);
        
        if (mpPersons == null || mpPersons.isEmpty()){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error: Table is empty");
            alert.setHeaderText("Add a Medical Professional to the table");
            alert.setContentText("Table is empty");
        }
        else{
            setTableData(mpPersons);
        }
    }
    
    EntityManager manager;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        manager = (EntityManager) Persistence.createEntityManagerFactory("MikeWagnerFXMLPU").createEntityManager();
        
        profFName.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        profID.setCellValueFactory(new PropertyValueFactory<>("Id"));
        profLName.setCellValueFactory(new PropertyValueFactory<>("LASTNAME"));
        profCreds.setCellValueFactory(new PropertyValueFactory<>("CREDENTIALS"));

        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }    
    
}
