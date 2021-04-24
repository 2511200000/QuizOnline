/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhhq.daos;

 import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import khanhhq.utilies.DbConnection;

/**
 *
 * @author Administrator
 */
public class TblTestDAO implements Serializable{
      public boolean createTest(int idTest, String subjectID, String id, int quantityQuestion, String userID, Date time) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;    
        try {
            con = DbConnection.makeConnection();
            
            if (con != null) {
              
                String sql = "Insert into tblTest(IdTest, subjectID, id, quantityQuestion, userID, Time) "
                          + "Values(?,?,?,?,?,?)";
                
                stm = con.prepareStatement(sql);
                
               stm.setInt(1, idTest);
               stm.setString(2, subjectID);
               stm.setString(3, id);
               stm.setInt(4, quantityQuestion);
               stm.setString(5, userID);
               stm.setDate(6, time);
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
        public int printIdTest() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DbConnection.makeConnection();
            String sql = "Select IdTest=MAX(IdTest) from tblTest";
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("IdTest");
                return id;
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
        return 0;
    }
}
