/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhhq.daos;

 import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import khanhhq.utilies.DbConnection;

/**
 *
 * @author Administrator
 */
public class TblLoginDAO implements Serializable {

    public String checklogin(String userID, String password, boolean status) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String name = "";
        try {
            con = DbConnection.makeConnection();
            if (con != null) {
                String sql = "Select fullname "
                        + "From tblLogin "
                        + "where userID = ? And password = ? And status = ?";
                stm = con.prepareStatement(sql);
                String sha256hex = org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);
                stm.setString(1, userID);
                stm.setString(2, sha256hex);
                stm.setBoolean(3, status);
                rs = stm.executeQuery();
                if (rs.next()) {
                    name = rs.getString(1);
                     return name;
                }

            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

   

    public String getRoleID(String userID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String name = "";
        try {
            con = DbConnection.makeConnection();
            if (con != null) {
                String sql = "Select roleID "
                        + "From tblLogin "
                        + "where userID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, userID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    name = rs.getString(1);
                    return name;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public boolean createAccount(String email, String name, String password, String role, boolean status) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DbConnection.makeConnection();
            if (con != null) {
                String sql = "Insert into tblLogin(userID,fullname,password,roleID,status)"
                        + "Values(?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, name);
                stm.setString(3, password);
                stm.setString(4, role);
                stm.setBoolean(5, status);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
     public String getFullname(String id) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String name = "";
        try {
            con = DbConnection.makeConnection();
            String sql = "select fullname "
                    + "from tblLogin "
                    + "where userID = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                name = rs.getString("fullname");
                return name;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }
}
