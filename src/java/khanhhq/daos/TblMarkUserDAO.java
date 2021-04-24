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
import khanhhq.dtos.TblMartUserDTO;
import khanhhq.utilies.DbConnection;

/**
 *
 * @author Administrator
 */
public class TblMarkUserDAO implements Serializable {

    public boolean createMark(String userID, String subjectID, int idTest, String id, String answerUser, boolean answerCorrect, int correct, float score) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DbConnection.makeConnection();
            if (con != null) {
                String sql = "Insert into tblMarkUser(IdTest,id,answerUser,answerCorrectUser,userID,subjectID,Correct,Score)"
                        + "Values(?, ?, ?, ?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, idTest);
                stm.setString(2, id);
                stm.setString(3, answerUser);
                stm.setBoolean(4, answerCorrect);
                stm.setString(5, userID);
                stm.setString(6, subjectID);
                stm.setInt(7, correct);
                stm.setFloat(8, score);
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

    private List<TblMartUserDTO> listMarkUser;

    public List<TblMartUserDTO> getlistMarkUser() {
        return listMarkUser;
    }

    public void printListMarkUser(String userIDCheck) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DbConnection.makeConnection();
            String sql = "Select IdTest, id, answerUser, answerCorrectUser, userID, subjectID, Correct, Score "
                    + "from tblMarkUser "
                    + "where userID = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, userIDCheck);
            rs = stm.executeQuery();
            while (rs.next()) {
                int IdTest = rs.getInt("IdTest");
                String id = rs.getString("id");
                TblQuestionDAO daoQuestion = new TblQuestionDAO();
                String question = daoQuestion.getQuestion(id);
                String answerUser = rs.getString("answerUser");
                boolean answerCorrectUser = rs.getBoolean("answerCorrectUser");
                String userID = rs.getString("userID");

                TblLoginDAO daoLogin = new TblLoginDAO();
                String fullname = daoLogin.getFullname(userID);

                String subjectID = rs.getString("subjectID");
                int correct = rs.getInt("Correct");
                float score = rs.getFloat("Score");

                TblMartUserDTO dto = new TblMartUserDTO(IdTest, question, answerUser, answerCorrectUser, fullname, subjectID, correct, score);
                if (this.listMarkUser == null) {
                    this.listMarkUser = new ArrayList<>();
                }
                this.listMarkUser.add(dto);
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
    }
    public List<TblMartUserDTO> SearchHistory;

    public List<TblMartUserDTO> getSearchHistory() {
        return SearchHistory;
    }

    public int Count(String subjectId,String id, String userID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DbConnection.makeConnection();
            String sql = "Select COUNT(*)"
                    + "from tblMarkUser "
                    + "Where id in (select id from tblQuestion where questionContent LIKE ?) AND subjectID LIKE ? AND userID = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, "%" + id + "%");
            stm.setString(2, "%" + subjectId + "%");
            stm.setString(3, userID);
            rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
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

    public void searchSubject(String subjectIdSearch,String idCheck, String userIDCheck, int index) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DbConnection.makeConnection();
            if (con != null) {
                String sql = "with x as(Select ROW_NUMBER() over (order by IdTest asc) as r,IdTest, id, answerUser, answerCorrectUser, userID, subjectID "
                        + "From tblMarkUser "
                        + "Where id in (select id from tblQuestion where questionContent LIKE ?) AND subjectID LIKE ? AND userID = ?)"
                        + "select * from x where r between ?*5-4 and ? * 5";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + idCheck + "%");
                stm.setString(2, "%" + subjectIdSearch + "%");
                stm.setString(3, userIDCheck);
                stm.setInt(4, index);
                stm.setInt(5, index);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int idTest = rs.getInt(2);
                    String id = rs.getString(3);
                    TblQuestionDAO dao = new TblQuestionDAO();
                    String question = dao.getQuestion(id);

                    String answerUser = rs.getString(4);
                    boolean answerCorrectUser = rs.getBoolean(5);
                    String userID = rs.getString(6);
                    TblLoginDAO daoLogin = new TblLoginDAO();
                    String fullname = daoLogin.getFullname(userID);

                    String subjectID = rs.getString(7);
                    if (subjectID.equals(subjectIdSearch)) {
                        subjectID = subjectIdSearch;
                    }
                    TblMartUserDTO dto = new TblMartUserDTO(idTest, question, answerUser, answerCorrectUser, fullname, subjectID, idTest, index);
                    if (this.SearchHistory == null) {
                        this.SearchHistory = new ArrayList<>();
                    }
                    this.SearchHistory.add(dto);
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
    }
}
