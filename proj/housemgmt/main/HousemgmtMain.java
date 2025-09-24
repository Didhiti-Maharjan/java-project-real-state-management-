/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proj.housemgmt.main;

//import proj.housemgmt.dao.HouseDao;
//import proj.housemgmt.dao.impl.HouseDaoimpl;

import proj.housemgmt.ui.Login;
//import proj.housemgmt.ui.Login;

/**
 *
 * @author didhitimaharjan
 */
public class HousemgmtMain {
    public static void main(String[] args) {
        
        Login dashboard = new Login();
        dashboard.setTitle("Login");
        dashboard.setLocationRelativeTo(null);
        dashboard.setVisible(true);
    }
}
