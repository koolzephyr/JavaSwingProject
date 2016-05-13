package Project.Services;

import Project.Database;
import Project.Domain.Visit;

import java.sql.*;

/**
 * Created by anons on 5/11/16.
 */
public class VisitService {
    Connection connection;
    PreparedStatement statement;
    String query;
    ResultSet resultSet;
    public VisitService(){
        try {
            connection = new Database().getDbConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean storeVisit(Visit visit){
        query = "insert into visit values (?,?,?,?,?)";
        int updated = 0;

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1,visit.getVisitId());
            statement.setInt(2,visit.getPersonId());
            statement.setTime(3, visit.getVisitTime());
            statement.setDate(4, visit.getVisitDate());
            statement.setString(5,visit.getVisitPurpose());
            updated = statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updated != 0;
    }
    public int getUniqueVisitId(){
        query = "select id from visit order by id desc limit 1";
        int id=1;
        try {
            statement=connection.prepareStatement(query);
            resultSet=statement.executeQuery();
            if (resultSet.next()){
                id= resultSet.getInt("id");
                id++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return id;
    }

    public boolean loggedVisit(int id,Date date){
        query = "select id from visit where pid = ? and visitdate = ?";
        boolean record = false;
        try {
            statement=connection.prepareStatement(query);
            statement.setInt(1,id);
            statement.setDate(2,date);
            resultSet=statement.executeQuery();

            if (resultSet.next()){
                record = true;
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return record;
    }

    public void getHistory(){

    }
    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
