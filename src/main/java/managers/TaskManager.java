package managers;

import dbConnection.DBConnection;
import models.Task;
import models.TaskStatusEnum;
import models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskManager {

    private Connection conn = DBConnection.getProvider().getConnection();
    private User user = new User();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private Task task = new Task();

    public void addTask(Task task) {

        String query = "INSERT INTO tasks (id, title, description, status, deadline, user) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, task.getId());
            statement.setString(2, task.getTitle());
            statement.setString(3, task.getDescription());
            statement.setString(4, task.getStatus().name());
            statement.setString(5, sdf.format(task.getDeadline()));
            statement.setString(6, task.getEmail());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Task updateTaskStatus (TaskStatusEnum e){

        String sql = "UPDATE tasks SET status = '" + e + "' + WHERE user = '" + task.getEmail() + "'";
        try {
            Task task = new Task();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(4, task.getStatus().name());
            statement.executeUpdate();
            return task;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
            return null;
    }



    public List<Task> getTaskByUser (String email){

        List<Task> list = new ArrayList<>();
        String sql = "SELECT * from tasks WHERE user = '" + email + "'";
        try {
            PreparedStatement stat = conn.prepareStatement(sql);
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()){
//                Task task = new Task();
//                task.setId(rs.getInt(1));
//                task.setTitle(rs.getString(2));
//                task.setDescription(rs.getString(3));
//                task.setStatus(rs.getString(4));
//                String deadline = rs.getString(5);
//                Date date = sdf.parse(deadline);
//                task.setDeadline(date);
//                task.setEmail(rs.getString(6));
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String description = rs.getString(3);
                String status = rs.getString(4);
                String date = rs.getString(5);
                String email1 = rs.getString(6);
                Date deadline = sdf.parse(date);
                Task task = new Task(id, title, description, TaskStatusEnum.valueOf(status), deadline, email1);
                list.add(task);
            }
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
            return null;
    }

//    public Task getTask(String title) {
//
//        String query = "SELECT * from tasks WHERE title = '" + title + "'";
//        try {
//            PreparedStatement statement = conn.prepareStatement(query);
//            ResultSet rs = statement.executeQuery(query);
//            while (rs.next()) {
//                Task task = new Task();
//                task.setId(rs.getInt(1));
//                task.setTitle(rs.getString(2));
//                task.setDescription(rs.getString(3));
//                task.setStatus(rs.getString(4));
//                String deadline = rs.getString(5);
//                Date date = sdf.parse(deadline);
//                task.setDeadline(date);
//                task.setEmail(user.getEmail());
//                return task;
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public List<Task> getAllTasks() {

        List<Task> list = new ArrayList<>();
        try {
            String query = "Select * from tasks";
            PreparedStatement stat = conn.prepareStatement(query);
            ResultSet rs = stat.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String description = rs.getString(3);
                String status = rs.getString(4);
                String date = rs.getString(5);
                String email = rs.getString(6);
                Date deadline = sdf.parse(date);
                Task task = new Task(id, title, description, TaskStatusEnum.valueOf(status), deadline, email);
                list.add(task);
            }
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public void deleteTask(String title) {
//
//        try {
//            String sql = "Delete from tasks where title='" + title + "'";
//            PreparedStatement stat = conn.prepareStatement(sql);
//            stat.executeUpdate();
//            System.out.println("Task was deleted successfully!");
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
}
