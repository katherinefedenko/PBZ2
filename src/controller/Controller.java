package controller;

import modelP.Patient;
import ui.MainWindow;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Controller {

    private List<Patient> listOfPatient;
    private Map<Integer, String> allRooms;
    private Connection connection;

    public void openDatabase() {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String username = "postgres";
        String password = "postgres";
        try {
            //Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public List<Patient> getAllPatients() {
        try {
            listOfPatient = new ArrayList<>();
            String command = "SELECT * FROM patients JOIN room USING (room)";
            PreparedStatement preparedStatement = connection.prepareStatement(command);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("getall");
           
            while (resultSet.next()) {
            	
                Patient patient = new Patient();
                patient.setKey(resultSet.getInt("id"));
                patient.setNumberOfRoom(Integer.toString(resultSet.getInt("room")));
                patient.setSurname(resultSet.getString(3));
                patient.setName(resultSet.getString(4));
                patient.setSecondName(resultSet.getString(5));
                patient.setSex(resultSet.getString(6));
                patient.setAge(resultSet.getString(7));
                patient.setDiagnosis(resultSet.getString(8));
                patient.setHowArrive(resultSet.getString(9));
                patient.setDateOfArrival(resultSet.getString(10));
                patient.setGrowth(resultSet.getString(11));
                patient.setHairColor(resultSet.getString(12));
                patient.setSpecialSigns(resultSet.getString(13));
                patient.setPossibleSigns(resultSet.getString(14));
                //patient.setTransfer(resultSet.getString("transfer"));
                patient.setDateOfDischarge(resultSet.getString("dateOfDischarge"));
                patient.setCauseOfDischarge(resultSet.getString("causeOfDischarge"));
                patient.setTelephoneNumber(resultSet.getString("phone"));
                //if (resultSet.getString(18).equals("")) patient.setTransfer("");
                //else patient.setTransfer(resultSet.getString(18) + "/" + resultSet.getString(19) + ";");
                //System.out.println("tr17 " + resultSet.getString(17)+"tr18 " + resultSet.getString(18)+ "tr19" + resultSet.getString(19));
                listOfPatient.add(patient);
                }
            preparedStatement.close();
            resultSet.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return listOfPatient;
    }
    
    public List<Patient> getAllTransfer() {
        try {
            listOfPatient = new ArrayList<>();
            String command = "SELECT * FROM patients JOIN transfer USING (id)";
            PreparedStatement preparedStatement = connection.prepareStatement(command);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("getall");
           
            while (resultSet.next()) {
            	
                Patient patient = new Patient();
                /*patient.setKey(resultSet.getInt("id"));
                patient.setNumberOfRoom(Integer.toString(resultSet.getInt("room")));
                patient.setSurname(resultSet.getString(3));
                patient.setName(resultSet.getString(4));
                patient.setSecondName(resultSet.getString(5));
                patient.setSex(resultSet.getString(6));
                patient.setAge(resultSet.getString(7));
                patient.setDiagnosis(resultSet.getString(8));
                patient.setHowArrive(resultSet.getString(9));
                patient.setDateOfArrival(resultSet.getString(10));
                patient.setGrowth(resultSet.getString(11));
                patient.setHairColor(resultSet.getString(12));
                patient.setSpecialSigns(resultSet.getString(13));
                patient.setPossibleSigns(resultSet.getString(14));
                //patient.setTransfer(resultSet.getString("transfer"));
                patient.setDateOfDischarge(resultSet.getString("dateOfDischarge"));
                patient.setCauseOfDischarge(resultSet.getString("causeOfDischarge"));
                patient.setTelephoneNumber(resultSet.getString("phone"));*/
                //if (resultSet.getString(18).equals("")) patient.setTransfer("");
                patient.setTransfer(resultSet.getString(18) + "/" + resultSet.getString(19) + ";");
                //System.out.println("tr17 " + resultSet.getString(17)+"tr18 " + resultSet.getString(18)+ "tr19" + resultSet.getString(19));
                listOfPatient.add(patient);
                }
            preparedStatement.close();
            resultSet.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return listOfPatient;
    }

    private int getPatientKey(String surname, String name, String dateOfArrival) {
        int key = 0;
        try {
            String command = "SELECT * FROM patients WHERE surname = ? && name = ? && dateOfArrival = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(command);
            preparedStatement.setString(1, surname);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, dateOfArrival);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                key = resultSet.getInt(1);
            }
            preparedStatement.close();
            resultSet.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return key;
    }

    public List<String> getTransfer(int id) {
        List<String> transfer = new ArrayList<>();
        try {
            String command = "SELECT * FROM transfer WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(command);
            preparedStatement.setString(1, String.valueOf(id));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                transfer.add(resultSet.getString(2));
                transfer.add(resultSet.getString(3));
                //transfer.add(resultSet.getString(4));
            }
            preparedStatement.close();
            resultSet.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return transfer;
    }

    public void updateRoom(int id, List<String> transfer, int room, String date) {
        try {
        	//String command = "UPDATE patients SET transfer = ?, date = ?  WHERE id = ?";
            //String command = "UPDATE transfer SET room = ?, date = ?  WHERE id = ?";
        	String command = "INSERT INTO transfer (id, room, date) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(command);
            /*System.out.println("tr0 "+transfer.get(0));
            System.out.println("tr1 "+transfer.get(1));*/
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, room);
            preparedStatement.setString(3, date);
            //preparedStatement.setString(4, String.valueOf(id));
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }

        try{
            String command = "UPDATE patients SET room = ? transfer = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(command);
            preparedStatement.setInt(1, room);
            preparedStatement.setInt(2, id);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        
        
        
  
        
    }

    public Map<Integer, String> getAllRooms() {
        try {
            allRooms = new LinkedHashMap<>();
            String command = "SELECT * FROM room";
            PreparedStatement preparedStatement = connection.prepareStatement(command);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                allRooms.put(resultSet.getInt(1), resultSet.getString(2));
            }
            preparedStatement.close();
            resultSet.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return allRooms;
    }

    public void addPatient(List<String> informationAboutPatient) {
        try {
            String command = "INSERT INTO patients (surname, name, secondName, sex, age, diagnosis, " +
                    "howArrive, dateOfArrival, growth, hairColor, specialSings, " +
                    "possibleAge, dateOfDischarge, causeOfDischarge, room) VALUES (?, ?, ?, ?, ?, ?, ?," +
                    "?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(command);

            preparedStatement.setString(1, informationAboutPatient.get(0));
            preparedStatement.setString(2, informationAboutPatient.get(1));
            preparedStatement.setString(3, informationAboutPatient.get(2));
            preparedStatement.setString(4, informationAboutPatient.get(3));
            preparedStatement.setString(5, informationAboutPatient.get(4));
            preparedStatement.setString(6, informationAboutPatient.get(5));
            preparedStatement.setString(7, informationAboutPatient.get(6));
            preparedStatement.setString(8, informationAboutPatient.get(7));
            preparedStatement.setString(9, informationAboutPatient.get(8));
            preparedStatement.setString(10, informationAboutPatient.get(9));
            preparedStatement.setString(11, informationAboutPatient.get(10));
            preparedStatement.setString(12, informationAboutPatient.get(11));
            //preparedStatement.setString(13, "");
            preparedStatement.setString(13, "");
            preparedStatement.setString(14, "");
            preparedStatement.setString(15, informationAboutPatient.get(12));

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }

        /*try {
            System.out.println("HERE1");
            int key = getPatientKey(informationAboutPatient.get(0), informationAboutPatient.get(1), informationAboutPatient.get(7));
            System.out.println("HERE1/2 " + key);
            String command = "INSERT INTO transfer (id, room, date) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(command);
            System.out.println("HERE2");
            preparedStatement.setString(1, Integer.toString(key));
            preparedStatement.setString(2, "");
            preparedStatement.setString(3, "");
            //preparedStatement.setString(4, "");
            System.out.println("HERE3");
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }*/
    }

    public void addRoom(List<String> informationAboutRoom) {
        int index = 1;
        String command = "INSERT INTO room (room, phone) VALUES (?, ?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(command)) {
            for (String inf : informationAboutRoom) {
                preparedStatement.setString(index, inf);
                index++;
            }
            preparedStatement.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void deletePatient(int id) {
        try{
            String command = "DELETE FROM transfer WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(command);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch(Exception ex){
            System.out.println(ex);
        }

        try{
            String command = "DELETE FROM patients WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(command);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }

    public void deleteRoom(int room) {
        try{
            String command = "DELETE FROM room WHERE room = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(command);
            preparedStatement.setInt(1, room);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }

    public void updateInformationAboutPatient(List<String> informationAboutPatient, int id) {
        int index = 1;
        try{
            String command = "UPDATE patients SET surname = ?, name = ?, " +
                    "secondName = ?, sex = ?, age = ?, diagnosis = ?, " +
                    "howArrive = ?, dateOfArrival = ?, growth = ?, " +
                    "hairColor = ?, specialSings = ?, possibleAge = ?, dateOfDischarge = ?, " +
                    "causeOfDischarge = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(command);
            for (String inf : informationAboutPatient) {
                preparedStatement.setString(index, inf);
                index++;
            }
            preparedStatement.setInt(15, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }

    public void updateInformationAboutRoom(String informationAboutRoom, int room) {
        try{
            String command = "UPDATE room SET telephone = ? WHERE room = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(command);
            preparedStatement.setString(1, informationAboutRoom);
            preparedStatement.setInt(2, room);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }

    public List<Patient> findRoom(String surname, String name, String secondName) {
        listOfPatient = getAllPatients();
        List<Patient> list = new ArrayList<>();
        for (Patient patient : listOfPatient) {
            if(surname.equals(patient.getSurname()) && name.equals(patient.getName())
                    && secondName.equals(patient.getSecondName())) list.add(patient);
        }
        return list;
    }

    private int[] convertToInt(String[] date) {
        int[] dateInt = new int[3];
        dateInt[0] = Integer.parseInt(date[0]);
        dateInt[1] = Integer.parseInt(date[1]);
        dateInt[2] = Integer.parseInt(date[2]);
        return dateInt;
    }

    public List<Patient> findDate(String dateIn) {
    	/*int key = 0;
    	try {
            String command = "SELECT * FROM patients WHERE dateOfArrival = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(command);
            preparedStatement.setString(1, dateIn);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                key = resultSet.getInt("id");
            }
            preparedStatement.close();
            resultSet.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }*/
        int[] date = convertToInt(dateIn.split("\\."));
        listOfPatient = getAllPatients();
        List<Patient> list = new ArrayList<>();
        for (Patient patient : listOfPatient) {
            int[] dateOfArrival = convertToInt(patient.getDateOfArrival().split("\\."));
            if (patient.getDateOfDischarge().isEmpty()) {
                if(date[2] >= dateOfArrival[2] && date[1] >= dateOfArrival[1] && date[0] >= dateOfArrival[0]) list.add(patient);
            } else {
                int[] dateOfDischarge = convertToInt(patient.getDateOfDischarge().split("\\."));
                if(date[2] >= dateOfArrival[2] && date[1] >= dateOfArrival[1] && date[0] >= dateOfArrival[0]
                        && date[2] <= dateOfDischarge[2] && date[1] <= dateOfDischarge[1] && date[0] <= dateOfDischarge[0]) list.add(patient);
            }
        }
        return list;
    	
    }

    public List<Patient> findWoman(int age) {
        listOfPatient = getAllPatients();
        List<Patient> list = new ArrayList<>();
        for (Patient patient : listOfPatient) {
            if (patient.getDateOfDischarge().isEmpty()) {
                if(Integer.parseInt(patient.getAge()) >= age && patient.getSex().equals("ì")) list.add(patient);
            }
        }
        return list;
    }

    public List<String[]> findTransfers(int key) {
        List<String[]> transfer = new ArrayList<>();
        String[] room;
        String[] dates;
        
        try {
            String command = "SELECT * FROM transfer WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(command);
            preparedStatement.setInt(1, key);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
            	System.out.println("room1"+resultSet.getString(2));
            	System.out.println("date2"+resultSet.getString(3));
                room = resultSet.getString(2).split(" ");
                dates = resultSet.getString(3).split(" ");
                //telephones = resultSet.getString(4).split(" ");
                transfer.add(room);
                transfer.add(dates);
                //transfer.add(telephones);
            }
            preparedStatement.close();
            resultSet.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return transfer;
    }

    public void closeDatabase() {
        try {
            connection.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}