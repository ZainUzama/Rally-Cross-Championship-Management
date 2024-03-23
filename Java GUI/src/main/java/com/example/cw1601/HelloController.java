package com.example.cw1601;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.time.LocalDate;

public class HelloController implements Initializable {
    @FXML
    private Button AddButton;

    @FXML
    private Pane AddPane;

    @FXML
    private Button DateSortButton;

    @FXML
    private Button DeleteButton;

    @FXML
    private Pane DeletePane;

    @FXML
    private TextField deleteNameTxt;

    @FXML
    private Button HomeButton;

    @FXML
    private Pane HomePane;

    @FXML
    private Button LoadDataButton;

    @FXML
    private Pane LoadDataPane;

    @FXML
    private Button RaceSimulateButton;

    @FXML
    private Pane RaceSimulatePane;

    @FXML
    private Button SaveDataButton;

    @FXML
    private Pane SaveDataPane;

    @FXML
    private Pane SortByDatePane;

    @FXML
    private Button UpdateButton;

    @FXML
    private Pane UpdatePane;

    @FXML
    private Button ViewPointsButton;

    @FXML
    private Pane ViewPointsPane;

    @FXML
    private TextField addAgeTxt;

    @FXML
    private TextField addCarTxt;

    @FXML
    private TextField addNameTxt;

    @FXML
    private TextField addPointsTxt;

    @FXML
    private TextField addTeamTxt;

    @FXML
    private TableColumn<Driver, Integer> ageCol;

    @FXML
    private TableColumn<Driver, String> carCol;

    @FXML
    private TableColumn<Driver, String> nameCol;

    @FXML
    private TableColumn<Driver, Integer> pointsCol;

    @FXML
    private TableView<Driver> pointsTable;

    @FXML
    private TableColumn<Driver,String> teamCol;

    @FXML
    private TextField uddAgeTxt;

    @FXML
    private TextField uddCarTxt;

    @FXML
    private TextField uddNameTxt;

    @FXML
    private TextField uddPointsTxt;

    @FXML
    private TextField uddSearchTxt;

    @FXML
    private TextField uddTeamTxt;

    @FXML
    private Label addLabel;
    @FXML
    private Label delLabel;
    @FXML
    private Label uddLabel;
    @FXML
    private Label saveLabel;

    @FXML
    private TableView<Race> raceTable;

    @FXML
    private TableColumn<Race, String> sCol;
    @FXML
    private TableColumn<Race, String> fCol;
    @FXML
    private TableColumn<Race, String> tCol;

    @FXML
    private TableColumn<Race, LocalDate> srrDateCol;

    @FXML
    private TableColumn<Race, String> srrLocationCol;
    @FXML
    private Label srrLabel;

    @FXML
    private TableColumn<Race, LocalDate> vrlDateCol;

    @FXML
    private TableColumn<Race, String> vrlFplaceCol;

    @FXML
    private TableColumn<Race, String> vrlLocationCol;

    @FXML
    private TableView<Race> vrlRaceTable;
    @FXML
    private Label loadLabel;

    @FXML
    private TableColumn<Race, String> vrlSplaceCol;

    @FXML
    private TableColumn<Race, String> vrlTplaceCol;


    final ObservableList<Driver> D_list = FXCollections.observableArrayList();

    final ObservableList<Race> R_list = FXCollections.observableArrayList();
    final ObservableList<Race> Sorted_R_list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameCol.setCellValueFactory(new PropertyValueFactory<Driver, String>("Name"));
        ageCol.setCellValueFactory(new PropertyValueFactory<Driver, Integer>("Age"));
        teamCol.setCellValueFactory(new PropertyValueFactory<Driver, String>("Team"));
        carCol.setCellValueFactory(new PropertyValueFactory<Driver, String>("Car"));
        pointsCol.setCellValueFactory(new PropertyValueFactory<Driver, Integer>("CurrentPoints"));

        pointsTable.setItems(D_list);

        srrDateCol.setCellValueFactory(new PropertyValueFactory<Race, LocalDate>("date"));
        srrLocationCol.setCellValueFactory(new PropertyValueFactory<Race, String>("Location"));
        fCol.setCellValueFactory(new PropertyValueFactory<Race, String>("Player1"));
        sCol.setCellValueFactory(new PropertyValueFactory<Race, String>("Player2"));
        tCol.setCellValueFactory(new PropertyValueFactory<Race, String>("Player3"));

        raceTable.setItems(R_list);

        vrlDateCol.setCellValueFactory(new PropertyValueFactory<Race, LocalDate>("date"));
        vrlLocationCol.setCellValueFactory(new PropertyValueFactory<Race, String>("Location"));
        vrlFplaceCol.setCellValueFactory(new PropertyValueFactory<Race, String>("Player1"));
        vrlSplaceCol.setCellValueFactory(new PropertyValueFactory<Race, String>("Player2"));
        vrlTplaceCol.setCellValueFactory(new PropertyValueFactory<Race, String>("Player3"));

        vrlRaceTable.setItems(R_list);

    }


    public void sort(ActionEvent actionEvent){
        int n = D_list.size();
        for(int i = 0; i < D_list.size()-1; i++){
            for(int j = i+1; j < D_list.size(); j++){
                if(D_list.get(i).getCurrentPoints() < D_list.get(j).getCurrentPoints()){
                    Driver temp = D_list.get(i);
                    D_list.set(i, D_list.get(j));
                    D_list.set(j, temp);
                }
            }
        }
    }
    @FXML
    public void controller(ActionEvent event) {
        if (event.getSource() == AddButton) {
            AddPane.toFront();
        }
        if (event.getSource() == DeleteButton) {
            DeletePane.toFront();
        }
        if (event.getSource() == UpdateButton) {
            UpdatePane.toFront();
        }
        if (event.getSource() == HomeButton) {
            HomePane.toFront();
        }
        if (event.getSource() == ViewPointsButton) {
            ViewPointsPane.toFront();
        }
        if (event.getSource() == RaceSimulateButton) {
            RaceSimulatePane.toFront();
        }
        if (event.getSource() == DateSortButton) {
            SortByDatePane.toFront();
        }
        if (event.getSource() == SaveDataButton) {
            SaveDataPane.toFront();
        }
        if (event.getSource() == LoadDataButton) {
            LoadDataPane.toFront();
        }

    }

    @FXML
    public void ADD(ActionEvent actionEvent) throws IOException {
        int age = 0;
        int points = 0;
        boolean error = false;
        String name = addNameTxt.getText();
        if (name.equals("")) {
            addLabel.setText("Invalid input. Driver's name cannot be empty.");
            error = true;
        } else if (!name.matches("[a-zA-Z]+")) {
            addLabel.setText("Invalid input. Name can be only contain alphabets.");
            error = true;
        }
        boolean driverExists = false;
        for (Driver driver : D_list) {
            if (driver.getName().equals(name)) {
                driverExists = true;
                break;
            }
        }
        if (driverExists) {
            addLabel.setText("Driver already exists.");
            error = true;
        }

        String ageInput = addAgeTxt.getText();
        if (ageInput.equals("")) {
            addLabel.setText("Invalid input. Age cannot be empty.");
            error = true;
        } else {
            try {
                age = Integer.parseInt(ageInput);
            } catch (NumberFormatException e) {
                addLabel.setText("Invalid age.");
                error = true;
            }
            if (age < 18) {
                addLabel.setText("Invalid input. Driver age should be above 18.");
                error = true;
            } else if (age > 55) {
                addLabel.setText("Invalid input. Driver age should be below 55.");
                error = true;
            }
        }

        String team = addTeamTxt.getText();
        if (team.equals("")) {
            addLabel.setText("Invalid input. Team name cannot be empty.");
            error = true;
        }

        String car = addCarTxt.getText();
        if (car.equals("")) {
            addLabel.setText("Invalid input. Car name cannot be empty.");
            error = true;
        }

        String pointsInput = addPointsTxt.getText();
        if (pointsInput.equals("")) {
            addLabel.setText("Invalid input. Fields cannot be empty.");
            error = true;
        } else {
            try {
                points = Integer.parseInt(pointsInput);
            } catch (NumberFormatException e) {
                addLabel.setText("Points should be a number.");
                error = true;
            }
            if (points < 0) {
                addLabel.setText("Points cannot be negative.");
                error = true;
            }
        }

        if (!error) {
            Driver newDriver = new Driver(name, age, team, car, points);
            D_list.add(newDriver);
            addLabel.setText("Driver added successfully.");
            addNameTxt.clear();
            addAgeTxt.clear();
            addTeamTxt.clear();
            addCarTxt.clear();
            addPointsTxt.clear();
        }
    }

    public void DELETE(ActionEvent event) {
        String d_name = deleteNameTxt.getText();
        Iterator<Driver> iterator = D_list.iterator();
        boolean found = false;
        while (iterator.hasNext()) {
            Driver driver = iterator.next();
            if (driver.getName().equals(d_name)) {
                iterator.remove();
                found = true;
                delLabel.setText("Driver deleted successfully.");
            }
        }
        if(d_name.equals("")){
            delLabel.setText("Invalid input. Driver name cannot be empty");
            found = true;
        }
        if (!found) {
            delLabel.setText("Driver not found. Enter a valid name.");
        }
    }

    public void UPDATE(ActionEvent event) {
        String d_name = uddSearchTxt.getText();
        for(Driver driver : D_list){
            if(driver.getName().equals(d_name)){
                D_list.remove(driver);
                String new_name = uddNameTxt.getText();
                int new_age = Integer.parseInt(uddAgeTxt.getText());
                String new_team = uddTeamTxt.getText();
                String new_car = uddCarTxt.getText();
                int new_points = Integer.parseInt(uddPointsTxt.getText());
                driver = new Driver(new_name, new_age, new_team, new_car, new_points);
                D_list.add(driver);
            }else{
                uddLabel.setText("Driver not found. Enter a valid name.");
            }
        }
    }
    public void SimulateERace(ActionEvent event)  {
        if(D_list.size() > 3) {
            //LocalDate date = LocalDate.now();//get today's date.
            Random random = new Random();
            long minDay = LocalDate.of(2023, 1, 1).toEpochDay();
            long maxDay = LocalDate.now().toEpochDay();
            long randomDay = minDay + random.nextInt((int) (maxDay - minDay));
            LocalDate randomDate = LocalDate.ofEpochDay(randomDay);


//            ArrayList<LocalDate> dates = new ArrayList<LocalDate>();
//            dates.add(randomDate);
//            LocalDate[] newDate = new LocalDate[dates.size()];
//            newDate = dates.toArray(newDate);
//            bubbleSort(newDate);
            //System.out.println(newDate);

            ArrayList<String> nameList = new ArrayList<String>();
            ArrayList<String> location = new ArrayList<String>();
            location.add("Nyirád");
            location.add("Höljes");
            location.add("Montalegre");
            location.add("Barcelona");
            location.add("Rīga");
            for (Driver driver : D_list) {
                driver.getName();
                nameList.add(driver.getName());
            }
            Collections.shuffle(nameList);
            Collections.shuffle(location);
            for (int i = 0; i < 3; i++) {
                for (Driver driver : D_list) {
                    if (nameList.get(i).equals(driver.getName())) {
                        if (i == 0) {
                            driver.setCurrentPoints(driver.getCurrentPoints() + 10);
                        } else if (i == 1) {
                            driver.setCurrentPoints(driver.getCurrentPoints() + 7);
                        } else if (i == 2) {
                            driver.setCurrentPoints(driver.getCurrentPoints() + 5);
                        }
                    }
                }
            }
            for(int j = 0; j < 1; j++){
                String name1 = nameList.get(0);
                String name2 = nameList.get(1);
                String name3 = nameList.get(2);
                Race race = new Race(randomDate, location.get(0), name1, name2, name3);
                R_list.add(race);
            }


        }else{
            srrLabel.setText("There are not enough drivers for a race.");
        }
    }

    @FXML
    public void SORT_DATE(ActionEvent event) {
        Sorted_R_list.clear();
        Sorted_R_list.addAll(R_list);
        for(int i = 0; i < Sorted_R_list.size()-1; i++){
            for(int j = 0; j < Sorted_R_list.size()-i-1; j++){
                if(Sorted_R_list.get(j).getDate().isAfter(Sorted_R_list.get(j+1).getDate())){
                    String temp = Sorted_R_list.get(j).getDate().toString();
                    Sorted_R_list.get(j).setDate(Sorted_R_list.get(j+1).getDate());
                    Sorted_R_list.get(j+1).setDate(LocalDate.parse(temp));
                }

            }
        }
        vrlRaceTable.setItems(Sorted_R_list);
    }

    @FXML
   public void SAVE(ActionEvent actionEvent) throws IOException {
       FileWriter writer = new FileWriter("C:\\Users\\Wizzu\\OneDrive\\Desktop\\cw\\cw.txt");

        // Write each driver's information to the file
       for (Driver driver : D_list) {
           String driverInfo = driver.getName() + "\t" + driver.getAge() + "\t" + driver.getTeam() + "\t" + driver.getCar() + "\t" + driver.getCurrentPoints() + "\n";
           writer.write(driverInfo);
       }
       writer.close();
       saveLabel.setText("Saved successfully.");
    }

    public void loadData() {
        try {
            // Open the file for reading
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Wizzu\\OneDrive\\Desktop\\cw\\cw.txt"));

            // Read each line from the file
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line into fields
                String[] fields = line.split("\t");

                // Create a new Driver object with the fields from the line
                String name = fields[0];
                int age = Integer.parseInt(fields[1]);
                String team = fields[2];
                String car = fields[3];
                int points = Integer.parseInt(fields[4]);
                Driver driver = new Driver(name, age, team, car, points);

                // Add the Driver object to the ObservableList
                D_list.add(driver);
                loadLabel.setText("Loaded successfully to points table.");
            }

            // Close the file
            reader.close();
        } catch (IOException e) {
            loadLabel.setText("Error reading from file. ");
        }
    }




}