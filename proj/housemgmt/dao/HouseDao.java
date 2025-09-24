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
import java.util.List;
import proj.housemgmt.model.House;

public interface HouseDao {
    
    int save(House house) throws SQLException, ClassNotFoundException;

    int update(House house, int houseno) throws SQLException, ClassNotFoundException;

    int remove(int houseno) throws SQLException, ClassNotFoundException;

    House findOne(int houseno) throws SQLException, ClassNotFoundException;

    List<House> findAll() throws SQLException, ClassNotFoundException;

    List<House> search(String query) throws SQLException, ClassNotFoundException;
    
}
