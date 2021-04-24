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
public class TblRoleDAO implements Serializable{
     public String getRoleName(String roleID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String name = "";
        try {
            con = DbConnection.makeConnection();
            if (con != null) {
                String sql = "Select rolename "
                        + "From tblRole "
                        + "where roleID = ?";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, roleID);
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
}
