package proj.housemgmt.dao.impl;

import proj.housemgmt.dao.UserDao;
import proj.housemgmt.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static proj.housemgmt.connection.ConnectionFactory.getConnection;

public class UserDaoimpl implements UserDao {

    @Override
    public int save(User user) throws SQLException, ClassNotFoundException {
        String insertSQL = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
        PreparedStatement preparedstatement = getConnection().prepareStatement(insertSQL);
        preparedstatement.setString(1, user.getUsername());
        preparedstatement.setString(2, user.getPassword());
        preparedstatement.setString(3, user.getRole()); // 'seller' or 'buyer'
        return preparedstatement.executeUpdate();
    }

    @Override
    public User findByUsername(String username) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM users WHERE username = ?";
        PreparedStatement preparedstatement = getConnection().prepareStatement(query);
        preparedstatement.setString(1, username);
        ResultSet rs = preparedstatement.executeQuery();

        if (rs.next()) {
            User user = new User();
            user.setUserId(rs.getInt("userId"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setRole(rs.getString("role"));
            return user;
        }
        return null;
    }

    @Override
    public boolean login(String username, String password) throws SQLException, ClassNotFoundException {
        User user = findByUsername(username);
        if (user != null) {
            return user.getPassword().equals(password);
        }
        return false;
    }
}
