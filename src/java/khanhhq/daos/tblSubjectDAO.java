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
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import khanhhq.dtos.tblSubjectDTO;
import khanhhq.utilies.DbConnection;

/**
 *
 * @author Administrator
 */
public class tblSubjectDAO implements Serializable {

    public List<tblSubjectDTO> getAllSubject() throws SQLException, NamingException {
        List<tblSubjectDTO> list = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DbConnection.makeConnection();
            String sql = "select subjectID, subjectname "
                    + "from tblSubject ";
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                String subjectID = rs.getString("subjectID");
                String subjectName = rs.getString("subjectname");
                tblSubjectDTO dto = new tblSubjectDTO(subjectID, subjectName);
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(dto);
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
        return list;
    }

    public String getSubjectName(String id) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String name = "";
        try {
            con = DbConnection.makeConnection();
            String sql = "select subjectname "
                    + "from tblSubject "
                    + "where subjectID = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, id);
            rs = stm.executeQuery();
            if (rs.next()) {
                name = rs.getString("subjectname");
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
