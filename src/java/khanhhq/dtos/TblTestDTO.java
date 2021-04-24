/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhhq.dtos;

 import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class TblTestDTO implements Serializable{
    private int idTest;
    private String subject;
    private String id;
    private int quantityQuestion;
    private String userID;
    Date time;

    public TblTestDTO(int idTest, String subject, String id, int quantityQuestion, String userID, Date time) {
        this.idTest = idTest;
        this.subject = subject;
        this.id = id;
        this.quantityQuestion = quantityQuestion;
        this.userID = userID;
        this.time = time;
    }

    public int getIdTest() {
        return idTest;
    }

    public void setIdTest(int idTest) {
        this.idTest = idTest;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQuantityQuestion() {
        return quantityQuestion;
    }

    public void setQuantityQuestion(int quantityQuestion) {
        this.quantityQuestion = quantityQuestion;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

   
    
    
}
