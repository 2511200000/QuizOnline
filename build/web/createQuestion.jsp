<%-- 
    Document   : createQuestion
    Created on : Feb 6, 2021, 3:11:22 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSS1/khanh.css">
        <title>CreateQuestion</title>
    </head>
    <body
        <c:if test="${not empty requestScope.CREATEQUESTIONERR}">
            onload="alertMessage()"
        </c:if>
        >
        <c:set var="result" value="${sessionScope.DISPLAYADMIN}"/>  
        <c:if test="${empty result}">
            <div class="Fail"></div>
            <h2> <a href="login.jsp">Please login first</a></h2>
        </c:if>

        <c:if test="${not empty result}">
            <div class="backgoundCreateItem">
                 <b><a href="PrintDataAdmin">Click here back to HOMEPAGE</a></b>
            </div>

            <div class="create">
                <div class="createQuestion">
                    <h2>Create New Question</h2>
                    <form action="CreateQuestion" method="POST">
                        ID: <br><input id="txtText" type="text" name="txtID" value=""  /><br>
                        QuestionContent: <br><input id="txtTextQuestionContent" type="text" name="txtQuestionContent" value=""  /><br>
                        <div class="answerCreate">
                            AnswerContent: <br> 
                            <div class="answer1">
                                A.<input id="txtText" type="text" name="txtAnswerContent1" value="${txtAnswerContent1}" /><br>
                                B. <input id="txtText" type="text" name="txtAnswerContent2" value="${txtAnswerContent2}" /><br>
                                C. <input id="txtText" type="text" name="txtAnswerContent3" value="${txtAnswerContent3}" /><br>
                                D. <input id="txtText" type="text" name="txtAnswerContent4" value="${txtAnswerContent4}" /><br>
                            </div>
                        </div>
                        AnswerCorrect: <br>
                        <select name="cboAnswer">
                            <c:forEach var="cbo" items="${sessionScope.ANSWER}" varStatus="counter">
                                <option label="${cbo.key}" value="${cbo.key}" 

                                        ></option> 
                            </c:forEach>
                        </select>
                        <br>
                        CreateDate:  <br><input id="txtText" type="text" name="txtCreate" value="" disabled=""
                                                placeholder="Set Current" /><br>
                        Subject: <br> <select name="cboSubject">
                            <c:forEach var="cbo" items="${sessionScope.CBONAME}" varStatus="counter">
                                <option label="${cbo.subjectname}" value="${cbo.subjectID}" 

                                        ></option>                                          
                            </c:forEach>
                        </select><br>
                        Status: <br><input id="txtText" type="text" name="txtStatus" value="" disabled=""
                                           placeholder="default status is active" /><br>
                        <div class="buttonCreate">
                            <input id="button" type="submit" value="CreateNewItem" name="btAction" /><br>
                            <input id="buttonCreateQuestion" type="reset" value="Reset" /><br>
                        </div>
                    </form>
                </div>
            </div>
            <script>
                function alertMessage() {
                    alert("${requestScope.CREATEQUESTIONERR}");
                }
            </script>
        </c:if>
    </body>
</html>
