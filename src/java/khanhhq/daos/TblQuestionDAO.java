
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import khanhhq.dtos.TblQuestionDTO;
import khanhhq.utilies.DbConnection;

/**
 *
 * @author Administrator
 */
public class TblQuestionDAO implements Serializable {

    private Map<Boolean, String> Status;

    public Map<Boolean, String> getStatus() {
        return Status;
    }

    private Map<String, String> AnswerCorrect;

    public Map<String, String> getAnswerCorrect() {
        return AnswerCorrect;
    }

    public void Answer() throws NamingException, SQLException {
        if (this.AnswerCorrect == null) {
            this.AnswerCorrect = new HashMap<>();
        }
        this.AnswerCorrect.put("A", "");
        this.AnswerCorrect.put("B", "");
        this.AnswerCorrect.put("C", "");
        this.AnswerCorrect.put("D", "");
    }

    public void statusBoolean() throws NamingException, SQLException {
        if (this.Status == null) {
            this.Status = new HashMap<>();
        }
        this.Status.put(true, "Active");
        this.Status.put(false, "deActive");

    }

    public List<TblQuestionDTO> SearchList;

    public List<TblQuestionDTO> getSearchList() {
        return SearchList;
    }

    public int Count(String txtSearch, boolean status, String subjectId) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DbConnection.makeConnection();
            String sql = "Select COUNT(*)"
                    + "from tblQuestion "
                    + "where questionContent LIKE ? AND status = ? AND subjectID LIKE ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, "%" + txtSearch + "%");
            stm.setBoolean(2, status);
            if (subjectId.equals("")) {
                stm.setString(3, "%" + subjectId + "%");
            } else {
                stm.setString(3, subjectId);
            }
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

    public void searchName(String searchValue, boolean statusSearch, String subjectIdSearch, int index) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DbConnection.makeConnection();
            if (con != null) {
                String sql = "with x as(Select ROW_NUMBER() over (order by id asc) as r,id, questionContent, answerContent1, answerContent2, answerContent3 ,answerContent4, answerCorrect, createDate, subjectID, status "
                        + "From tblQuestion "
                        + "Where questionContent LIKE ? AND status = ? AND subjectID LIKE ?)"
                        + "select * from x where r between ?*5-4 and ? * 5";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                stm.setBoolean(2, statusSearch);

                if (subjectIdSearch.equals("")) {
                    stm.setString(3, "%" + subjectIdSearch + "%");
                } else {
                    stm.setString(3, subjectIdSearch);
                }
                stm.setInt(4, index);
                stm.setInt(5, index);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String id = rs.getString(2);
                    String questionContent = rs.getString(3);
                    String answerContent1 = rs.getString(4);
                    String answerContent2 = rs.getString(5);
                    String answerContent3 = rs.getString(6);
                    String answerContent4 = rs.getString(7);
                    String answerCorrect = rs.getString(8);
                    Date createDate = rs.getDate(9);
                    String subjectID = rs.getString(10);

                    tblSubjectDAO daoSubject = new tblSubjectDAO();
                    String subjectname = daoSubject.getSubjectName(subjectID);
                    if (subjectID.equals(subjectIdSearch)) {
                        subjectID = subjectname;
                    }

                    String subjectOfQuestion = getSubjectName(questionContent);
                    String subjectNameOfQuestion = daoSubject.getSubjectName(subjectOfQuestion);
                    if (searchValue != null) {
                        if (subjectOfQuestion.equals(subjectID)) {
                            subjectID = subjectNameOfQuestion;
                        }
                    }

                    boolean status = rs.getBoolean(11);
                    TblQuestionDTO dto = new TblQuestionDTO(id, questionContent, answerContent1, answerContent2, answerContent3, answerContent4, answerCorrect, createDate, subjectID, status);
                    if (this.SearchList == null) {
                        this.SearchList = new ArrayList<>();
                    }
                    this.SearchList.add(dto);
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

    public int countAllQuesionAdmin() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DbConnection.makeConnection();
            String sql = "Select COUNT(*)"
                    + "from tblQuestion";
            stm = con.prepareStatement(sql);

            rs = stm.executeQuery();
            if (rs.next()) {
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

    private List<TblQuestionDTO> DataAdmin;

    public List<TblQuestionDTO> getDataAdmin() {
        return DataAdmin;
    }

    public void printData(int index) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DbConnection.makeConnection();
            if (con != null) {
                String sql = "with x as(Select ROW_NUMBER() over (order by questionContent desc) as r,id, questionContent, answerContent1, answerContent2, answerContent3, answerContent4, answerCorrect, createDate, subjectID, status "
                        + " From tblQuestion)"
                        + " select id, questionContent, answerContent1, answerContent2, answerContent3, answerContent4, answerCorrect, createDate, subjectID, status from x where r between ?*10-9 and ? * 10";
                stm = con.prepareStatement(sql);
                stm.setInt(1, index);
                stm.setInt(2, index);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("id");
                    String questionContent = rs.getString("questionContent");
                    String answerContent1 = rs.getString("answerContent1");
                    String answerContent2 = rs.getString("answerContent2");
                    String answerContent3 = rs.getString("answerContent3");
                    String answerContent4 = rs.getString("answerContent4");
                    String answerCorrect = rs.getString("answerCorrect");
                    Date createDate = rs.getDate("createDate");
                    String subjectID = rs.getString("subjectID");

                    tblSubjectDAO daoSubject = new tblSubjectDAO();
                    String subjectname = daoSubject.getSubjectName(subjectID);
                    if (subjectname != null) {
                        subjectID = subjectname;
                    }
                    boolean status = rs.getBoolean("status");
                    TblQuestionDTO dto = new TblQuestionDTO(id, questionContent, answerContent1, answerContent2, answerContent3, answerContent4, answerCorrect, createDate, subjectID, status);
                    if (this.DataAdmin == null) {
                        this.DataAdmin = new ArrayList<>();
                    }
                    this.DataAdmin.add(dto);
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

    public String getSubjectName(String questionContent) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String name = "";
        try {
            con = DbConnection.makeConnection();
            String sql = "select subjectID "
                    + "from tblQuestion "
                    + "where questionContent = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, questionContent);
            rs = stm.executeQuery();
            if (rs.next()) {
                name = rs.getString("subjectID");
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

    public boolean createQuestion(String id, String questionContent, String answerContent1, String answerContent2, String answerContent3, String answerContent4, String answerCorrect, Date createDate, String subjectID, boolean status) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DbConnection.makeConnection();
            if (con != null) {
                String sql = "Insert into tblQuestion(id, questionContent, answerContent1, answerContent2, answerContent3, answerContent4, answerCorrect, createDate, subjectID, status)"
                        + "Values(?, ?, ? ,?, ?, ?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                stm.setString(2, questionContent);
                stm.setString(3, answerContent1);
                stm.setString(4, answerContent2);
                stm.setString(5, answerContent3);
                stm.setString(6, answerContent4);
                stm.setString(7, answerCorrect);
                stm.setDate(8, createDate);
                stm.setString(9, subjectID);
                stm.setBoolean(10, status);
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

    public boolean updateQuestion(String questionContent, String answerContent1, String answercontent2, String answerContent3, String answerContent4, String answerCorrect, String subjectID, boolean status, String id) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DbConnection.makeConnection();
            if (con != null) {
                String sql = "Update tblQuestion "
                        + "Set questionContent = ?, answercontent1 = ?, answercontent2 = ?, answercontent3 = ?, answercontent4 = ?, answerCorrect = ?, subjectID = ?, status =? "
                        + "Where id = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, questionContent);
                stm.setString(2, answerContent1);
                stm.setString(3, answercontent2);
                stm.setString(4, answerContent3);
                stm.setString(5, answerContent4);
                stm.setString(6, answerCorrect);
                stm.setString(7, subjectID);
                stm.setBoolean(8, status);
                stm.setString(9, id);
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

    public boolean deleteQuestion(String id, boolean status) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DbConnection.makeConnection();
            if (con != null) {
                String sql = "Update tblQuestion "
                        + "Set status = ? Where id = ?";
                stm = con.prepareStatement(sql);
                stm.setBoolean(1, status);
                stm.setString(2, id);
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

    private List<TblQuestionDTO> QuestionUser;

    public List<TblQuestionDTO> getQuestionUser() {
        return QuestionUser;
    }

    public void printQuestionUser(boolean statusCheck, String subject) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DbConnection.makeConnection();
            if (con != null) {
                String sql = "Select Top 10 id, questionContent, answerContent1, answerContent2, answerContent3, answerContent4, answerCorrect, createDate, subjectID, status "
                        + "from tblQuestion "
                        + "where status = ? AND subjectID = ? "
                        + "ORDER BY NEWID()";
                stm = con.prepareStatement(sql);
                stm.setBoolean(1, statusCheck);
                stm.setString(2, subject);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String id = rs.getString(1);
                    String questionContent = rs.getString(2);
                    String answerContent1 = rs.getString(3);
                    String answerContent2 = rs.getString(4);
                    String answerContent3 = rs.getString(5);
                    String answerContent4 = rs.getString(6);
                    String answerCorrect = rs.getString(7);
                    Date createDate = rs.getDate(8);
                    String subjectID = rs.getString(9);

                    tblSubjectDAO daoSubject = new tblSubjectDAO();
                    String subjectname = daoSubject.getSubjectName(subjectID);
                    if (subjectname != null) {
                        subjectID = subjectname;
                    }
                    boolean status = rs.getBoolean(10);
                    if (status == statusCheck) {
                        status = true;
                    }
                    TblQuestionDTO dto = new TblQuestionDTO(id, questionContent, answerContent1, answerContent2, answerContent3, answerContent4, answerCorrect, createDate, subjectID, status);
                    if (this.QuestionUser == null) {
                        this.QuestionUser = new ArrayList<>();
                    }
                    this.QuestionUser.add(dto);
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

    public List<TblQuestionDTO> getAnswerCorrect(String id) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<TblQuestionDTO> list = null;

        try {
            con = DbConnection.makeConnection();
            String sql = "select answerCorrect "
                    + "from tblQuestion "
                    + "where id = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, id);
            rs = stm.executeQuery();
            if (rs.next()) {
                String answer = rs.getString("answerCorrect");
                TblQuestionDTO dto = new TblQuestionDTO(answer);
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
        return null;
    }

    public TblQuestionDTO findItemByID(String id) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DbConnection.makeConnection();
            String sql = "Select id, questionContent, answerContent1, answerContent2, answerContent3, answerContent4, answerCorrect, createDate, subjectID, status "
                    + "from tblQuestion "
                    + "where id = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                String idQuestion = rs.getString(1);
                String questionContent = rs.getString(2);
                String answerContent1 = rs.getString(3);
                String answerContent2 = rs.getString(4);
                String answerContent3 = rs.getString(5);
                String answerContent4 = rs.getString(6);
                String answerCorrect = rs.getString(7);
                Date createDate = rs.getDate(8);
                String subjectID = rs.getString(9);

                boolean status = rs.getBoolean(10);
                TblQuestionDTO dto = new TblQuestionDTO(idQuestion, questionContent, answerContent1, answerContent2, answerContent3, answerContent4, answerCorrect, createDate, subjectID, status);
                return dto;

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

    public String getQuestion(String id) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String name = "";
        try {
            con = DbConnection.makeConnection();
            String sql = "select questionContent "
                    + "from tblQuestion "
                    + "where id = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                name = rs.getString("questionContent");
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
