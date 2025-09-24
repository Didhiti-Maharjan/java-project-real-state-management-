/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proj.housemgmt.model;

/**
 *
 * @author didhitimaharjan
 */
public class User {
    private int userid;
    private String username;
    private String password;
    private String role;

    public User(int userid, String username,String password, String role) {
        this.userid = userid;
        this.username = username;
        this.password= password;
        this.role = role;
        
    }

    public User() {
    }
    

    public int getUserId() {
        return userid;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    

    public String getRole() {
        return role;
    }

    public void setUserId(int userid) {
        this.userid = userid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" + "userid=" + userid + ", username=" + username + ", password=" + password + ", role=" + role + '}';
    }

    
    
    
}
