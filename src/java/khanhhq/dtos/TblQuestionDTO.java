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
public class TblQuestionDTO implements Serializable {

    private String id;
    private String questionContent;
    private String answerContent1;
    private String answerContent2;
    private String answerContent3;
    private String answerContent4;
    private String answerCorrect;
    Date createDate;
    private String subjectID;
    boolean status;

    public TblQuestionDTO(String id, String questionContent, String answerContent1, String answerContent2, String answerContent3, String answerContent4, String answerCorrect, Date createDate, String subjectID, boolean status) {
        this.id = id;
        this.questionContent = questionContent;
        this.answerContent1 = answerContent1;
        this.answerContent2 = answerContent2;
        this.answerContent3 = answerContent3;
        this.answerContent4 = answerContent4;
        this.answerCorrect = answerCorrect;
        this.createDate = createDate;
        this.subjectID = subjectID;
        this.status = status;
    }

    public TblQuestionDTO(String id, String answerContent1, String answerContent2, String answerContent4, String answerCorrect) {
        this.id = id;
        this.answerContent1 = answerContent1;
        this.answerContent2 = answerContent2;
        this.answerContent4 = answerContent4;
        this.answerCorrect = answerCorrect;
    }

    public TblQuestionDTO(String answerCorrect) {
        this.answerCorrect = answerCorrect;
    }

    

   
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public String getAnswerContent1() {
        return answerContent1;
    }

    public void setAnswerContent1(String answerContent1) {
        this.answerContent1 = answerContent1;
    }

    public String getAnswerContent2() {
        return answerContent2;
    }

    public void setAnswerContent2(String answerContent2) {
        this.answerContent2 = answerContent2;
    }

    public String getAnswerContent3() {
        return answerContent3;
    }

    public void setAnswerContent3(String answerContent3) {
        this.answerContent3 = answerContent3;
    }

    public String getAnswerContent4() {
        return answerContent4;
    }

    public void setAnswerContent4(String answerContent4) {
        this.answerContent4 = answerContent4;
    }

    public String getAnswerCorrect() {
        return answerCorrect;
    }

    public void setAnswerCorrect(String answerCorrect) {
        this.answerCorrect = answerCorrect;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
