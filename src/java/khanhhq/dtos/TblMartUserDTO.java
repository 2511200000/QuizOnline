/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhhq.dtos;

 import java.io.Serializable;

/**
 *
 * @author Administrator
 */
public class TblMartUserDTO implements Serializable {

    private int idTest;
    private String id;
    private String answerUser;
    private boolean answerCorrectUser;
    private String userID;
    private String subject;
    private int correct;
    private float score;

    public TblMartUserDTO(int idTest, String id, String answerUser, boolean answerCorrectUser, String userID, String subject, int correct, float score) {
        this.idTest = idTest;
        this.id = id;
        this.answerUser = answerUser;
        this.answerCorrectUser = answerCorrectUser;
        this.userID = userID;
        this.subject = subject;
        this.correct = correct;
        this.score = score;
    }

    public int getIdTest() {
        return idTest;
    }

    public void setIdTest(int idTest) {
        this.idTest = idTest;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnswerUser() {
        return answerUser;
    }

    public void setAnswerUser(String answerUser) {
        this.answerUser = answerUser;
    }

    public boolean isAnswerCorrectUser() {
        return answerCorrectUser;
    }

    public void setAnswerCorrectUser(boolean answerCorrectUser) {
        this.answerCorrectUser = answerCorrectUser;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    
    

   

}
