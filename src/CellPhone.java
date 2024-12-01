// -----------------------------------------------------
// Assignment 3
// Written by: Ayush Patel (40285846) and Krishna Patel (40200870)
// -----------------------------------------------------

import java.util.Objects;
import java.util.Scanner;

public class CellPhone {
    long serialNum;
    String brand;
    int year;
    double price;

    public CellPhone() {
        serialNum = 0;
        brand = "";
        year = 0;
        price = 0;
    }

    public CellPhone(long serialNum, String brand, double price, int year) {
        this.serialNum = serialNum;
        this.brand = brand;
        this.year = year;
        this.price = price;
    }

    public CellPhone(CellPhone other){
        this.serialNum = other.serialNum;
        this.brand = other.brand;
        this.year = other.year;
    }

    public CellPhone clone(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter new serial number: ");
        long newSerialNum = sc.nextLong();
        return new CellPhone(newSerialNum, this.brand, this.price, this.year);
    }

    // Getter and setter methods for all attributes
    public void setBrand(String brand) {this.brand = brand;}
    public void setYear(int year) {this.year = year;}
    public void setPrice(double price) {this.price = price;}
    public void setSerialNum(long serialNum) {this.serialNum = serialNum;}
    public String getBrand() {return brand;}
    public int getYear() {return year;}
    public double getPrice() {return price;}
    public long getSerialNum() {return serialNum;}

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CellPhone cellPhone = (CellPhone) o;
        return serialNum == cellPhone.serialNum && year == cellPhone.year && Double.compare(price, cellPhone.price) == 0 && Objects.equals(brand, cellPhone.brand);
    }

    public String toString(){
        return "The serial number is: "+serialNum+"\nThe brand is: "+brand+"\nThe year is: "+year+"\nThe price is: "+price;
    }

}
