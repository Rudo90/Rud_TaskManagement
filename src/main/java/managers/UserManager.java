package managers;

import dbConnection.DBConnection;
import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserManager {

    private Connection conn = DBConnection.getProvider().getConnection();

    public void addUser(User user) {

        String query = "INSERT INTO users (id,`name`, surname, type, email, password) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, user.getId());
            statement.setString(2, user.getName());
            statement.setString(3, user.getSurname());
            statement.setString(4, user.getType());
            statement.setString(5, user.getEmail());
            statement.setString(6, user.getPassword());
            statement.executeUpdate();
            String sql = "Select name from users where exists (select email from users where " +
                    "email='" + user.getEmail() + "')";
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                System.out.println("Person already exists!");
            } else {
                statement.execute();
                System.out.println("Adding was succeeded!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public User getUser(String email, String password){

        String query = "SELECT * from users WHERE email = '" + email + "' AND password = '" + password + "'";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                User user = new User();
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setSurname(rs.getString(3));
                user.setType(rs.getString(4));
                user.setEmail(rs.getString(5));
                user.setPassword(rs.getString(6));
                return user;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public List<User> getAllUsers (){

        List<User> list = new ArrayList<>();

        try {
            String query = "Select * from users";
            PreparedStatement stat = conn.prepareStatement(query);
            ResultSet rs = stat.executeQuery(query);
            while (rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String surname = rs.getString(3);
                String type = rs.getString(4);
                String email = rs.getString(5);
                String password = rs.getString(6);
                User user = new User(id, name, surname, type, email, password);
                list.add(user);
            }
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    public void deleteUser (String email){

        String sql = "DELETE from users WHERE email='" + email + "'";
        try {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }




    }




}
