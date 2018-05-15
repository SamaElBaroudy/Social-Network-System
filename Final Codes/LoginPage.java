/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialnetwork;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import socialnetwork.SocialNetwork;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static socialnetwork.SocialNetwork.*;

/**
 *
 * @author Adel Mahmoud
 */
public class LoginPage {
    Button loginButton;
    Button registerButton;
    Button VisualizeButton;
    BorderPane page = new BorderPane();

    Label registerLabel;
    Label loginLabel;
    Label NameOfApp;
    Stage window;

    VBox register;
    HBox login;

    ObservableList<String> gender = FXCollections.observableArrayList("Female","Male");
    ObservableList<String> marritalstatus = FXCollections.observableArrayList("Single","In a Relationship","Engaged","Married");

    Label RegisterGenderLabel;
    ComboBox GenderComboBox;
    Label RegisterMarritalStatusLabel;
    ComboBox MarritalStatusComboBox;
    Label RegisterDateOfBirthLabel;
    DatePicker RegisterDateOfBirth;

    TextField loginUsername;
    PasswordField loginPassword;


    Label RegisterUsernameLabel;
    TextField registerUsername;
    Label RegisterPasswordLabel;
    TextField registerPassword;
    Label RegisterCityLabel;
    TextField RegisterCity;
    Label RegisterPlaceOfBirthLabel;
    TextField RegisterPlaceOfBirth;
   
   private void setTop()
    {
        HBox top = new HBox(300);

        Button VisualizeButton = new Button ("  Users graph  ");
        VisualizeButton.setOnAction(e->{

            try {
                Process P = Runtime.getRuntime().exec("python graph.py");
            } catch (IOException e1) {
                //
            }
        });
        Button StatisticsButton = new Button("Users Statistics");
        StatisticsButton.setOnAction(e->{
          Stage s_stat = new Stage();
          GridPane root = new GridPane();
          Statistics statistics = new Statistics();
          GridPane.setConstraints(statistics.getBc_bp(),0,0);
          GridPane.setConstraints(statistics.getBc_cities(),1,0);
          GridPane.setConstraints(statistics.getPc_gender(),0,1);
          GridPane.setConstraints(statistics.getPc_status(),1,1);
          GridPane.setConstraints(statistics.getBc_most_friends(),0,2);
          GridPane.setConstraints(statistics.getBc_posts(),1,2);
          GridPane.setConstraints(statistics.getBc_bd(),2,0);
          GridPane.setConstraints(statistics.getBc_fr(),2,2);
          GridPane.setConstraints(statistics.getPc_lang(),2,1);
          root.getChildren().addAll(statistics.getBc_bp(),statistics.getBc_cities(),statistics.getPc_gender(),
                  statistics.getPc_status(),statistics.getBc_most_friends(),statistics.getBc_posts(),statistics.getBc_bd(),
                  statistics.getBc_fr(),statistics.getPc_lang());
          s_stat.setScene(new Scene(root,600,600));
          root.setGridLinesVisible(true);
          s_stat.setTitle("Statistics");
          s_stat.show();
        });

        NameOfApp = new Label ("    Our Social Network");
        NameOfApp.setStyle("-fx-font:40px \"Serif\";\n" + "-fx-text-fill: white;");
        NameOfApp.setAlignment(Pos.BASELINE_LEFT);


        login = new HBox (20);

        loginLabel = new Label("Login Here");
        loginLabel.setStyle("-fx-font:15px \"Verdana\";\n" + "-fx-text-fill: white;");

        loginButton = new Button();
        loginButton.setText("  Login  ");
        loginButton.setOnAction(e->{
        String username = loginUsername.getText();
            String Password = loginPassword.getText();
            user loggeduser=SocialNetwork.searchUsersHashTable(username);
            
           System.out.println(SocialNetwork.searchUsersHashTable(username));
              // user loggeduser = SocialNetwork.usersHashTable[432].get(0);
               
            if(loggeduser==null)
            {
                MessageBox.display("User name  error","Please sign up first");
            }
            else if(!loggeduser.getPassword().equals(Password))
            {
                //say that password is inccorrect
                MessageBox.display("Password error","Incorrect password,try again.");
            }
            else
            {
                SocialNetwork.currentUser = loggeduser;
                SocialNetwork.window.setScene(new HomePage().homePage(SocialNetwork.currentUser));

                SocialNetwork.window.show();
            }

        });

        loginUsername = new TextField();
        loginUsername.setPromptText("Enter username");
        loginPassword = new PasswordField();
        loginPassword.setPromptText("Enter your password");

        VBox sec_vb = new VBox();
        sec_vb.getChildren().addAll(VisualizeButton,StatisticsButton);
        sec_vb.setAlignment(Pos.CENTER);
        sec_vb.setSpacing(10);
        login.getChildren().addAll(loginLabel,loginUsername,loginPassword,loginButton,sec_vb);
        login.setAlignment(Pos.CENTER);

        top.getChildren().addAll(NameOfApp,login);
        top.setAlignment(Pos.CENTER_LEFT);
        top.setStyle("-fx-background-color: #2f4f4f");
        top.setPrefHeight(100);



        page.setTop(top);

    }
    private void setCenter ()
    {

        registerLabel = new Label("Register First");
        registerLabel.setStyle("-fx-font:20px \"Serif\";\n" + "-fx-text-fill: white;");
        registerLabel.setAlignment(Pos.TOP_LEFT);
        registerLabel.setPrefWidth(500);

        registerUsername = new TextField();

        registerUsername.setPromptText("ex: SamaELBaroudy , Adel3 ,...");
        RegisterUsernameLabel = new Label("Choose Username");
        HBox UsernameBox = new HBox(20);
        UsernameBox.getChildren().addAll(RegisterUsernameLabel,registerUsername);
        UsernameBox.setPrefWidth(400);

        RegisterPasswordLabel = new Label("Choose password");
        registerPassword = new TextField();
        HBox PasswordBox = new HBox(20);
        PasswordBox.getChildren().addAll(RegisterPasswordLabel,registerPassword);
        PasswordBox.setPrefWidth(400);

        RegisterDateOfBirthLabel = new Label ("Your Date of Birth");
        RegisterDateOfBirth = new DatePicker();
        HBox DateBox = new HBox(20);
        DateBox.getChildren().addAll(RegisterDateOfBirthLabel,RegisterDateOfBirth);
        DateBox.setPrefWidth(400);

        RegisterGenderLabel = new Label ("Gender");
        GenderComboBox = new ComboBox(gender);
        RegisterMarritalStatusLabel = new Label ("Marrital Status");
        MarritalStatusComboBox = new ComboBox(marritalstatus);
        HBox GenderMarritalBox = new HBox(20);
        GenderMarritalBox.getChildren().addAll(RegisterGenderLabel,GenderComboBox,RegisterMarritalStatusLabel,MarritalStatusComboBox);
        GenderMarritalBox.setPrefWidth(400);

        RegisterPlaceOfBirthLabel= new Label ("Place oF Birth");
        RegisterPlaceOfBirth = new TextField();
        HBox PlaceOfBirthBOx = new HBox(50);
        PlaceOfBirthBOx.getChildren().addAll(RegisterPlaceOfBirthLabel,RegisterPlaceOfBirth);
        PlaceOfBirthBOx.setPrefWidth(400);

        RegisterCityLabel = new Label("Where you Live now");
        RegisterCity = new TextField();
        HBox CityBox = new HBox(15);
        CityBox.getChildren().addAll(RegisterCityLabel,RegisterCity);
        CityBox.setPrefWidth(400);

        registerButton= new Button();
        registerButton.setText("  register  ");
        registerButton.setOnAction(e->{
               //new user data
            // we have to check that he filled all
            if(searchUsersHashTable(registerUsername.getText())!=null)
            {
                MessageBox.display("Error","Try another user name");
            }
            else if(RegisterDateOfBirth.getValue()==null ||
                    registerPassword.getText().equals("")||
                    registerPassword.getText().equals(null)
                    ||RegisterPlaceOfBirth.getText().equals(null)||
                    RegisterPlaceOfBirth.getText().equals("")||
                    GenderComboBox.getSelectionModel().getSelectedItem()==null
                    ||MarritalStatusComboBox.getSelectionModel().getSelectedItem()==null
                    ||RegisterCity.getText().equals(null)||
                    RegisterCity.getText().equals("")
                    ||registerUsername.getText().equals("")
                    ||registerUsername.getText().equals(null)

                    )
                {
                  MessageBox.display("Registration incomplete","Please fill all the fields");
                }
                else{
                MessageBox.display("Registration Complete","Successful registration");
                user NewEntry = new user();
                int day = RegisterDateOfBirth.getValue().getDayOfMonth();
                int month = RegisterDateOfBirth.getValue().getMonthValue();
                int year = RegisterDateOfBirth.getValue().getYear();
                Date_Of_Birth dateofbirthnew = new Date_Of_Birth(day, month, year);
                Informations newEntryInfo = new Informations();
                newEntryInfo.setDateOfBirth(dateofbirthnew);
                newEntryInfo.setBirth_place(RegisterPlaceOfBirth.getText());
                newEntryInfo.setCity(RegisterCity.getText());

                NewEntry.setPassword(registerPassword.getText());
                NewEntry.setUsername(registerUsername.getText());
                if (GenderComboBox.getSelectionModel().getSelectedItem() == "Female") {
                    newEntryInfo.setGender(Informations.Gender.female);

                } else if (GenderComboBox.getSelectionModel().getSelectedItem() == "Male") {
                    newEntryInfo.setGender(Informations.Gender.male);
                }
                //"Single","In a Relationship","Engaged","Married"

                if (MarritalStatusComboBox.getSelectionModel().getSelectedItem() == "Single") {
                    newEntryInfo.setStatus(Informations.MaritalStatus.single);

                } else if (MarritalStatusComboBox.getSelectionModel().getSelectedItem() == "In a Relationship") {
                    newEntryInfo.setStatus(Informations.MaritalStatus.inarelationship);
                } else if (MarritalStatusComboBox.getSelectionModel().getSelectedItem() == "Engaged") {
                    newEntryInfo.setStatus(Informations.MaritalStatus.engaged);
                } else if (MarritalStatusComboBox.getSelectionModel().getSelectedItem() == "Married") {
                    newEntryInfo.setStatus(Informations.MaritalStatus.married);
                }

                NewEntry.setInfo(newEntryInfo);
                NewEntry.setpp("maleavatar.jpg");
                SocialNetwork.SaveUserInFile(NewEntry);
                addToHashTable(NewEntry);
                SocialNetwork.UsersInSystem.add(NewEntry);


                    Map<String,Integer> in = new HashMap<String,Integer>();
                    for (user j: UsersInSystem)
                    {

                        in.put(j.getUsername(),0);

                    }
                SocialNetwork.adjencyMatrix.put(NewEntry.getUsername(), in );

            }
           // System.out.println(USERS.toJSONString());         
        });
        registerButton.setAlignment(Pos.CENTER);

        register = new VBox(20);
        register.getChildren().addAll(registerLabel,UsernameBox,PasswordBox,DateBox,GenderMarritalBox,PlaceOfBirthBOx,CityBox,registerButton);
        register.setAlignment(Pos.CENTER);
        register.setMaxWidth(400);


        page.setStyle("-fx-background-color: #B3B6B7");

        page.setCenter(register);

    }
    public Scene loginPage (Stage window)
    {
        window.setTitle("Our Social Network");
        setTop();
        setCenter ();
        Scene login = new Scene(page, 1300, 650);
        return login;
    }
    

}
