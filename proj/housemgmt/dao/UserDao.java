/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proj.housemgmt.dao;

/**
 *
 * @author didhitimaharjan
 */
import java.sql.SQLException;
import proj.housemgmt.model.User;
public interface UserDao {
    int save(User user) throws SQLException, ClassNotFoundException;      
    User findByUsername(String username) throws SQLException, ClassNotFoundException;
    boolean login(String username, String password) throws SQLException, ClassNotFoundException;

    
}
