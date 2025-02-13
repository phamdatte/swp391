/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import model.DBConnection;
import entity.Account;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;

/**
 *
 * @author pdatt
 */
public class DAOAccount extends DBConnection {

    public Account AccountLogin(String email, String password) {
        Account customer = null;
        String sql = "SELECT * FROM Account WHERE Email = ? AND Password = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                customer = new Account();
                customer.setAccountID(resultSet.getInt("CustomerID"));
                customer.setName(resultSet.getString("Name"));
                customer.setEmail(resultSet.getString("Email"));
                customer.setPassword(resultSet.getString("Password"));
                customer.setPhoneNum(resultSet.getString("PhoneNum"));
                customer.setAddress(resultSet.getString("Address"));
                customer.setYearOfBirth(resultSet.getInt("DoB"));
                customer.setStatus(resultSet.getBoolean("Status"));
                customer.setRole(resultSet.getString("Role"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customer;
    }

    public Account getAccountByEmail(String email) {
        String sql = "SELECT Email FROM Account WHERE Email = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return new Account(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getInt(7), true,
                        resultSet.getString(8));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean isEmailExist(String email) {
        try {
            String sql = "SELECT * FROM Account WHERE email = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true; // Email already exists
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // Email does not exist
    }

    public boolean isPhoneExist(String phone) {
        try {
            String sql = "SELECT * FROM Account WHERE PhoneNum = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, phone);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true; // Email already exists
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // Email does not exist
    }

    public int updateAccount(Account cus) {
        int affectedRow = 0;
        String sql = "UPDATE [dbo].[Account]\n"
                + "   SET [Name] = ?\n"
                + "      ,[Email] = ?\n"
                + "      ,[Password] = ?\n"
                + "      ,[PhoneNum] = ?\n"
                + "      ,[Address] = ?\n"
                + "      ,[YearOfBirth] = ?\n"
                + "      ,[Status] = ?\n"
                + " WHERE CustomerID like ?"; // Thêm điều kiện WHERE
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cus.getName());
            pre.setString(2, cus.getEmail());
            pre.setString(3, cus.getPassword());
            pre.setString(4, cus.getPhoneNum());
            pre.setString(5, cus.getAddress());
            pre.setInt(6, cus.getYearOfBirth());
            pre.setInt(7, (cus.isStatus() == true ? 1 : 0));

            affectedRow = pre.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAOAccount.class.getName()).log(Level.SEVERE, null, ex);
        }

        return affectedRow;
    }

    public int resetPasswordByEmail(String mail, String newPass) {
        int n = 0;
        String sql = "UPDATE Account SET Password = ? WHERE Email = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newPass); // Nếu có bcrypt, dùng BCrypt.hashpw(newPass, BCrypt.gensalt())
            ps.setString(2, mail);
            n = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOAccount.class.getName()).log(Level.SEVERE, "Lỗi khi cập nhật mật khẩu: " + ex.getMessage(), ex);
        }
        return n;
    }

    public int createAccount(Account customer) {
        int affectedRow = 0;
        String sql = "INSERT INTO Account (Name, Email, Password, PhoneNum, Address, YearOfBirth, Status, Role) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getEmail());
            preparedStatement.setString(3, customer.getPassword());
            preparedStatement.setString(4, customer.getPhoneNum());
            preparedStatement.setString(5, customer.getAddress());
            preparedStatement.setInt(6, customer.getYearOfBirth());
            preparedStatement.setBoolean(7, customer.isStatus());
            preparedStatement.setString(8, customer.getRole());
            affectedRow = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return affectedRow;
    }

}
