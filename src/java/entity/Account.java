/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author pdatt
 */
public class Account {

    private int accountID;
    private String Name;
    private String Email;
    private String Password;
    private String PhoneNum;
    private String Address;
    private int YearOfBirth;
    private boolean Status;
    private String role;

    public Account() {
    }

    public Account(String Name, String Email, String Password, String PhoneNum, String Address, int YearOfBirth, boolean Status, String role) {
        this.Name = Name;
        this.Email = Email;
        this.Password = Password;
        this.PhoneNum = PhoneNum;
        this.Address = Address;
        this.YearOfBirth = YearOfBirth;
        this.Status = Status;
        this.role = role;
    }
    
    

    public Account(int accountID, String Name, String Email, String Password, String PhoneNum, String Address, int YearOfBirth, boolean Status, String role) {
        this.accountID = accountID;
        this.Name = Name;
        this.Email = Email;
        this.Password = Password;
        this.PhoneNum = PhoneNum;
        this.Address = Address;
        this.YearOfBirth = YearOfBirth;
        this.Status = Status;
        this.role = role;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getPhoneNum() {
        return PhoneNum;
    }

    public void setPhoneNum(String PhoneNum) {
        this.PhoneNum = PhoneNum;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public int getYearOfBirth() {
        return YearOfBirth;
    }

    public void setYearOfBirth(int YearOfBirth) {
        this.YearOfBirth = YearOfBirth;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Account{" + "accountID=" + accountID + ", Name=" + Name + ", Email=" + Email + ", Password=" + Password + ", PhoneNum=" + PhoneNum + ", Address=" + Address + ", YearOfBirth=" + YearOfBirth + ", Status=" + Status + ", role=" + role + '}';
    }

    
}