package ap.project2;

import java.text.DecimalFormat;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class APProject2 extends Application {

    private final DataBaseWorkStation DB = new DataBaseWorkStation(); // create object 

    private final DataBaseCommands DBCom = new DataBaseCommands();  // create object 

    @Override
    public void start(Stage primaryStage) {
        // header section
        Label name1 = new Label("Hamad Ibrahim AL-laaboun 441001902"); //first label
        name1.setFont(new Font("Arial", 24)); // change the font size for first label
        name1.setTextFill(Color.RED); // change the color of first label
        Label name2 = new Label("Saif Hassan Alharbi 439009961"); // second label
        name2.setFont(new Font("Arial", 24)); // change the font size for second label
        name2.setTextFill(Color.RED); // change the color of second label
        Label group = new Label("Group 2"); // third label
        group.setFont(new Font("Arial", 24)); // change the font size for third label
        group.setTextFill(Color.RED); // change the color for third label

        VBox topSection = new VBox(); // create VBox to contain all header nodes
        topSection.setAlignment(Pos.CENTER); // align all nodes in the center
        topSection.getChildren().addAll(name1, name2, group); // add all nodes to VBox
        //end of header section

        Button exitBtn = new Button("Exit"); // create button with text exit -------------------------------------------------------------------
        exitBtn.setFont(new Font("Arial", 16)); // change the font size of the text in the button

        //TabPane section
        TabPane tabs = new TabPane(); // create TabPane to contain all tabs
        Tab addStudentTab = new Tab("Add students"); // create first tab to add students
        Tab searchStudentTab = new Tab("Search for students"); // create second tab to search for students
        tabs.getTabs().addAll(addStudentTab, searchStudentTab); // add all tabs to TabPane
        addStudentTab.setClosable(false); // set the closable property to false
        searchStudentTab.setClosable(false); // set the closable property to  false 

        //Add student tab section
        BorderPane addStudentBorder = new BorderPane(); // create borderpane for addStudentTab
        addStudentBorder.setPadding(new Insets(20, 20, 20, 20)); // set padding to 20px from each side

        GridPane addStudentPane = new GridPane(); // create GridPane to contain all nodes for add student tab
        addStudentPane.setPadding(new Insets(20, 20, 20, 20)); // set padding to 20px from each side
        addStudentPane.setHgap(10); // set Hgap to 10px
        addStudentPane.setVgap(10); // set Vgap to 10px
        addStudentPane.setAlignment(Pos.CENTER); // align all items to center

        Label errorText = new Label(""); // create empty label to display error

        errorText.setFont(new Font("Arial", 14)); // change the font size for second label
        errorText.setTextFill(Color.RED); // change the color of second label
        addStudentPane.add(errorText, 1, 7); // add errorText to grid 

        Label titleLabel = new Label("Fill out the students information"); // create Label to have the title
        titleLabel.setFont(new Font("Arial", 24)); // change the font size of titleLabel
        addStudentPane.add(titleLabel, 0, 1, 2, 1); // add the label with colspan 2 columns

        Label nameLabel = new Label("Full name "); // create label for full name TextField
        nameLabel.setFont(new Font("Arial", 16)); // change the font size of nameLabel
        addStudentPane.add(nameLabel, 0, 2); // add label to GridPane
        TextField fullNameField = new TextField(); // create TextField for full name ---------------------------------------------------------------
        addStudentPane.add(fullNameField, 1, 2); // add TextField to GridPane

        Label birthdateLabel = new Label("Birthdate "); // create label for birthdate datepicker
        birthdateLabel.setFont(new Font("Arial", 16)); // change the font size for birthdateLabel
        addStudentPane.add(birthdateLabel, 0, 3); // add label to GridPane
        DatePicker dateOfBirth = new DatePicker(null); // create datepicker ----------------------------------------------------------------------------
        dateOfBirth.getEditor().setDisable(true); // for disable text inputs from user 
        dateOfBirth.getEditor().setOpacity(1); // set opacity to 1 for good view
        addStudentPane.add(dateOfBirth, 1, 3); // add datepicker to GridPane

        Label sliderValue = new Label("0.0"); //----------------------------------------------- make it responsive to the value of slider with setText
        Label gpaLabel = new Label("GPA  "); // create label for GPA
        gpaLabel.setFont(new Font("Arial", 16)); // change the font size of gpaLabel
        addStudentPane.add(gpaLabel, 0, 5); // add gpaLabel to GridPane
        addStudentPane.add(sliderValue, 1, 4); // add sliderValue label to GridPane
        Slider gpa = new Slider(0, 4, 0); // create slider and make max value 4 --------------------------------------------------------------------
        addStudentPane.add(gpa, 1, 5); // add slider to GridPane

        gpa.valueProperty().addListener(handler -> { // Event listener for slider action
            DecimalFormat formatter = new DecimalFormat("#0.0"); // format style 
            String stringGPA = formatter.format(gpa.getValue()); // format the gpa value - getValue() for get the value from the input
            sliderValue.setText(stringGPA); // set the value of gpa to lable
            // set gpa to db worklstations 
            DB.setGPA(stringGPA); // set gpa value to db class
        });

        Button saveBtn = new Button("Save"); // create save button ---------------------------------------------------------------------------------
        addStudentPane.add(saveBtn, 1, 6); // add save button to grid
        TextField searchNameField = new TextField(); // create textfield for search ----------------------------------------------------------------

        saveBtn.setOnAction(handler -> { // event onAction for save button
            errorText.setTextFill(Color.RED); // set color to red
            errorText.setText(""); // set default value to empty

            String tempName = fullNameField.getText(); // store fullNameField value to tempName
            if (tempName.matches("[a-zA-Z ]*")) { // regex for only letters and whitespace
                if (tempName.length() <= 40 && tempName.length() > 3) { // check length must be between 4 and 40
                    DB.setFullName(tempName);// set tempName value to DB class
                } else {//else
                    DB.setFullName(null); // set default to null
                }
            } else {//else
                DB.setFullName(null);//set default to null
            }
            int counter = 0; // create counter to 0
            if (DB.getFullName() == null) { // check if fullName is null
                errorText.setText("Enter the correct name\n");// add error message if there is an error
                counter++;// increase counter for error handling latter
            }
            if (DB.getDateOfBirth() == null) { // check if DateOfBirth is null
                errorText.setText(errorText.getText() + "Choose a date\n");// add error message if there is an error
                counter++;// increase counter for error handling latter
            }
            if (DB.getGPA() == 0.0) {// check if gpa is null
                errorText.setText(errorText.getText() + "Choose the GPA");// add error message if there is an error
                counter++;// increase counter for error handling latter
            }
            if (counter > 0) { // if counter > 0  that`s mean there is an error get out of the function
                return; // get out of the function
            }

            
            boolean successfull = DBCom.InsertInto(DB.getFullName(), DB.getDateOfBirth(), DB.getGPA()); // parse values to insertInto function , and the values are getting from the prvious statments , and the function will return boolean , true for seccessfuly added , and false for unseccessful command

            if (successfull) {
                errorText.setTextFill(Color.GREEN); //recolor for successfuly added
                errorText.setText("add student Successfully"); // set  Successfully message 
                clearInputs(fullNameField, dateOfBirth, gpa,searchNameField); // clear the previous values in inputFileds
            } else {
                errorText.setTextFill(Color.RED); //recolor for successfuly added
                errorText.setText("failed to add student"); // set unSuccessfully message 
            }

        });
        addStudentBorder.setRight(exitBtn); //  set exitBtn at the right side if the border pane
        addStudentBorder.setCenter(addStudentPane); // sets GridPane to center of BorderPane

        addStudentTab.setContent(addStudentBorder); // set content of addStudentTab with addStudentPane
        //end of add student tab

        // events handlers
       
        //search for students tab section
        BorderPane searchStudentPane = new BorderPane(); // create borderpane for searchforstudent tab
        searchStudentPane.setPadding(new Insets(20, 20, 20, 20)); // set padding for borderpane
        //right and left section
        Button refreshBtn = new Button("Refresh"); // create refresh button ------------------------------------------------------------------------
        refreshBtn.setFont(new Font("Arial", 16)); // change the font size of refreshBtn
        searchStudentPane.setLeft(refreshBtn); // set refresh button to left
        Button exitBtn1 = new Button("Exit"); // create button with text exit -------------------------------------------------------------------
        exitBtn1.setFont(new Font("Arial", 16)); // change the font size of the text in the button
        searchStudentPane.setRight(exitBtn1); // set exit button to right
        //top section
        Label searchNameLabel = new Label("Enter name"); // create label for search field
        Button searchBtn = new Button("Search"); // create button for search -----------------------------------------------------------------------
        HBox searchHBox = new HBox(); // create HBox to contain all nodes for top
        searchHBox.setSpacing(10); // set spacing for HBox
        searchHBox.setAlignment(Pos.CENTER); // set alignment for HBox to center
        searchHBox.getChildren().addAll(searchNameLabel, searchNameField, searchBtn); // add all nodes to HBox
        searchStudentPane.setTop(searchHBox); // set HBox to top of BorderPane

        //center section
        TableView table = new TableView<Student>(); // create tableview for center

        TableColumn firstColumn = new TableColumn<Student, String>("ID"); // create first column for ID
        firstColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("id")); // set value of cell

        TableColumn secondColumn = new TableColumn<>("Full name"); // create second column for Full name
        secondColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("fullName")); // set value of cell

        TableColumn thirdColumn = new TableColumn<Student, String>("Birthdate"); // create third column for Birthdate
        thirdColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("dateOfBirth")); // set value of cell

        TableColumn forthColumn = new TableColumn<Student, String>("GPA"); // create forth column for GPA
        forthColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("gpa")); // set value of cell

        table.getColumns().addAll(firstColumn, secondColumn, thirdColumn, forthColumn); // add all columns to table
        searchStudentPane.setCenter(table); // set table to center of BorderPane

        Label tableErrorText = new Label(""); // create label with default value
        tableErrorText.setFont(new Font("Arial", 14)); // change the font size for label
        tableErrorText.setTextFill(Color.RED); // change the color of label
        HBox box = new HBox(); // create HBox to  center the tableErrorText
        box.getChildren().add(tableErrorText); // add tableErrorText to HBox
        box.setAlignment(Pos.CENTER); // center the nodes inside the HBox
        searchStudentPane.setBottom(box);// set the hbox in the searchStudentPane
        DBCom.getAllStudentsRecord(table,tableErrorText); // set all studnets in table view and parse the table view to set in 
        
        //event handler for change tab action
         addStudentTab.selectedProperty().addListener(handler ->{
            clearInputs(fullNameField, dateOfBirth, gpa, searchNameField); // clear inputs for every tabs changed
            searchNameField.clear(); // clear values for searchNameField
            tableErrorText.setText(""); // clear values in tableErrorText
            table.getItems().clear(); // clear values in table
            DBCom.getAllStudentsRecord(table,tableErrorText); // set all studnets in table view and parse the table view to set in  after refresh'
        });
         
        refreshBtn.setOnAction(handler -> { // event handler OnAction fot refreshButton
            searchNameField.clear(); // clear values for searchNameField
            tableErrorText.setText(""); // clear values in tableErrorText
            table.getItems().clear(); // clear values in table
            DBCom.getAllStudentsRecord(table,tableErrorText); // set all studnets in table view and parse the table view to set in  after refresh'
        });

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); // set the resize policy for table

        searchStudentTab.setContent(searchStudentPane); // set the content of search student tab to borderpane
        //end of search for students tab section

        //Events handler
        exitBtn.setOnAction(handler -> { // event action on click
            DBCom.disconnect(); // close connection 
            Platform.exit(); // terminate the proccess
        });
        exitBtn1.setOnAction(handler -> {// event action on click
            DBCom.disconnect();// close connection 
            Platform.exit();// terminate the proccess
        });
        dateOfBirth.setOnAction((handler) -> { // evemt listener
            DB.setDateOfBirth(dateOfBirth.getValue() + ""); // send date to DB workStat
        });
        
        //event listener for searchBtn
        searchBtn.setOnAction(handler -> { // event handler for searchbtn 
            if (searchNameField.getText().matches("[a-zA-Z ]+")) { // regex 
                tableErrorText.setText(""); // set tableErrorText to ""
                table.getItems().clear(); // delete the previous data
                DBCom.searchForAStudent(searchNameField.getText(), table, tableErrorText );
            }else{
                tableErrorText.setText("Enter the correct name"); // set this text if invalid value entered
                if(searchNameField.getText().length()==0){ // if empty select all values in DB 
                    tableErrorText.setText("");// set tableErrorText to ""
                    table.getItems().clear(); // delete the previous data
                    DBCom.searchForAStudent(searchNameField.getText(), table, tableErrorText); // call searchForAStudent function , with name , table to add and tableErrorText for displayed error if found 
                }
            }
        });

        //end of TabPane section
        BorderPane root = new BorderPane(); // create BorderPane as base to contain all elements and panes
        root.setPadding(new Insets(10, 0, 10, 0)); // set padding for top and bottom
        root.setTop(topSection); // set top section with VBox topSection
        root.setCenter(tabs); // set center with TabPane tabs

        Scene scene = new Scene(root, 700, 600); // create scene with root and width 700px and height 600px
        primaryStage.minHeightProperty().set(500); // set min height
        primaryStage.minWidthProperty().set(600); // set min width
        primaryStage.setTitle("Student Information Program"); // set title of window
        primaryStage.setScene(scene); // set scene to stage
        primaryStage.show();  // show stage
    }

    private void clearInputs(TextField fullname, DatePicker date, Slider slider ,TextField searchName) { //reset inputs after successfully added
        fullname.clear(); // clear fullname input 
        slider.setValue(0.0);// clear gpa slider input 
        date.setValue(null);// clear date input value
        searchName.clear(); // clear searchName when this func called
        
    }

    public static void main(String[] args) {
        launch(args);
    }

}
