/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proj.housemgmt.dao.impl;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import proj.housemgmt.dao.HouseDao;
import proj.housemgmt.model.House;

import static proj.housemgmt.connection.ConnectionFactory.getConnection;
/**
 *
 * @author didhitimaharjan
 */
public class HouseDaoimpl implements HouseDao{

    @Override
     public int save(House house) throws SQLException, ClassNotFoundException {
        String insertSQL = "insert into houses (type, area, address, price, status) values(?,?,?,?,?)";
        PreparedStatement preparedStatement = getConnection().prepareStatement(insertSQL);
        preparedStatement.setString(1, house.getType());
        preparedStatement.setDouble(2, house.getArea());
        preparedStatement.setString(3, house.getAddress());
        preparedStatement.setDouble(4, house.getPrice());
        preparedStatement.setString(5, house.getStatus());
        return preparedStatement.executeUpdate();
    }
      
    
   

    @Override
    public int update(House house, int houseno) throws SQLException, ClassNotFoundException {
        String updateSQL = "update houses set type = ?, area = ?,"
                + " address = ?, price = ?, status = ? where houseno = ?";
        PreparedStatement preparedStatement = getConnection().prepareStatement(updateSQL);
        preparedStatement.setString(1, house.getType());
        preparedStatement.setDouble(2, house.getArea());
        preparedStatement.setString(3, house.getAddress());
        preparedStatement.setDouble(4, house.getPrice());
        preparedStatement.setString(5, house.getStatus());
        preparedStatement.setInt(6, house.getHouseno());
        return preparedStatement.executeUpdate();
    }

    @Override
    public int remove(int houseno) throws SQLException, ClassNotFoundException {
        String deleteSQL = "delete from houses where houseno = ?";
        PreparedStatement preparedStatement = getConnection().prepareStatement(deleteSQL);
        preparedStatement.setInt(1, houseno);
        return preparedStatement.executeUpdate();
    }

    @Override
    public House findOne(int houseno) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = getConnection()
                .prepareStatement("select *from houses where houseno = ?");
        preparedStatement.setInt(1, houseno);
        ResultSet resultSet = preparedStatement.executeQuery();
        House house = new House();
        while (resultSet.next()) {
            house.setHouseno(resultSet.getInt("houseno"));
            house.setType(resultSet.getString("type"));
            house.setArea(resultSet.getDouble("area"));
            house.setAddress(resultSet.getString("address"));
            house.setPrice(resultSet.getDouble("price"));
            house.setStatus(resultSet.getString("status"));

        }
        return house;
    }

    @Override
    public List<House> findAll() throws SQLException, ClassNotFoundException {
        List<House> houses = new ArrayList<>();
        PreparedStatement preparedStatement = getConnection()
                .prepareStatement("select *from houses");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            House house = new House();
            house.setHouseno(resultSet.getInt("houseno"));
            house.setType(resultSet.getString("type"));
            house.setArea(resultSet.getDouble("area"));
            house.setAddress(resultSet.getString("address"));
            house.setPrice(resultSet.getDouble("price"));
            house.setStatus(resultSet.getString("status"));
            houses.add(house);
        }
        return houses;
    }

    @Override
    public List<House> search(String query) throws SQLException, ClassNotFoundException {
        List<House> houses = new ArrayList<>();
        PreparedStatement preparedStatement = getConnection().prepareStatement(
                "select *from houses where type like concat ('%' ? '%')"
                + "or address like concat ('%' ? '%') "
                + "or status like concat ('%' ? '%') "
                + "or cast(houseno as char) like ('%' ? '%') "
                + "or cast(area as char) like ('%' ? '%') "
                + "or cast(price as char) like ('%' ? '%')");
        preparedStatement.setString(1, query);
        preparedStatement.setString(2, query);
        preparedStatement.setString(3, query);
        preparedStatement.setString(4, query);
        preparedStatement.setString(5, query);
        preparedStatement.setString(6, query);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            House house = new House();
            house.setHouseno(resultSet.getInt("houseno"));
            house.setType(resultSet.getString("type"));
            house.setArea(resultSet.getDouble("area"));
            house.setAddress(resultSet.getString("address"));
            house.setPrice(resultSet.getDouble("price"));
            house.setStatus(resultSet.getString("status"));
            houses.add(house);
        }
        return houses;
    }
}
