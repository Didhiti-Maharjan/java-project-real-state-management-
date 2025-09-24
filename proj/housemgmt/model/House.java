/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proj.housemgmt.model;

/**
 *
 * @author didhitimaharjan
 */
public class House {
    private int houseno;
    private String type;
    private double area;
    private String address;
    private double price;
    private String status;

    public House() {
    }

    public House(int houseno, String type, double area, String address, double price,String status) {
        this.houseno = houseno;
        this.type = type;
        this.area = area;
        this.address = address;
        this.price = price;
        this.status = status;
    }

    public House(String type, double area, String address, double price, String status) {
        this.type = type;
        this.area = area;
        this.address = address;
        this.price = price;
        this.status = status;
    }
    

    public int getHouseno() {
        return houseno;
    }

    public String getType() {
        return type;
    }

    public double getArea() {
        return area;
    }

    public String getAddress() {
        return address;
    }

    public double getPrice() {
        return price;
    }

    public void setHouseno(int houseno) {
        this.houseno = houseno;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "House{" + "house no.: " + houseno + ", type: " + type + ", area: " +area +"sq.ft" + ", address: " + address + ", price: Rs." + price+ ", status: " + status + "}" ;
    }
}
    
