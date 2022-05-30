package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    static Connection connection = Util.getConnection();

    public void createUsersTable() {

        String sql = "CREATE TABLE USERS ( ID INT NOT NULL AUTO_INCREMENT, NAME VARCHAR(255) NOT NULL, LAST_NAME VARCHAR(255) NOT NULL, AGE TINYINT UNSIGNED NOT NULL, PRIMARY KEY (ID) );";

        try (Statement statement = connection.createStatement()) {

            statement.executeUpdate(sql);

        } catch (SQLException e) {
           // e.printStackTrace();
        }
    }

    public void dropUsersTable() {

        String sql = "DROP TABLE USERS;";

        try (Statement statement = connection.createStatement()){

            statement.executeUpdate(sql);

        } catch (SQLException e) {
           // e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        String sql = "INSERT USERS ( NAME, LAST_NAME, AGE) VALUES (?,?,?);";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");

        } catch (SQLException e) {
           // e.printStackTrace();
        }
    }

    public void removeUserById(long id) {

        String sql = "DELETE FROM USERS WHERE ID=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
           // e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        String sql = "SELECT * FROM USERS";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql);){

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("NAME"));
                user.setLastName(resultSet.getString("LAST_NAME"));
                user.setAge(resultSet.getByte("AGE"));

                userList.add(user);
            }
        } catch (SQLException e) {
          //  e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {

        String sql = "TRUNCATE TABLE USERS";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
          //  e.printStackTrace();
        }
    }
}
