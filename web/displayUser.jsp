<%-- 
    Document   : displayUser
    Created on : Jan 27, 2021, 8:20:10 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSS1/khanh.css">
        <title>User</title>
    </head>
    <body>
        <c:set var="name" value="${sessionScope.FULLNAMEUSER}"/>
        <c:if test="${empty name}">
            <div class="Fail"></div>
            <h2> <a href="login.jsp">Please login first</a></h2>
        </c:if>
        <c:if test="${not empty name}">
            <div class="hearderDisplayAdmin">
                <ul class="nav-listAdmin">
                    <li class="nav-itemAdmin">
                        <font color="red">
                        Welcome, ${sessionScope.FULLNAMEUSER}
                        </font><br>
                    </li>
                </ul>
                <ul class="nav-listAdmin">
                    <li class="nav-itemAdmin">
                        <form action="LogOut">
                            <input type="submit" value="LogOut" name="btAction" />
                        </form>
                    </li>
                    <li class="nav-itemAdmin">
                        <form action="HistoryUser">
                            <b> <input class="buttonHistory" type="submit" value="History" name="btAction" /></b>
                        </form>
                    </li>
                </ul>
            </div>

            <h1 class="h1Admin">User</h1>

            <div class="searchUser" >
                <form action="UserTest">
                    <div class="searchUserList">
                        <select name="cboSubject">
                            <option label="Ple choose subject" value=""></option>
                            <c:forEach var="cbo" items="${sessionScope.CBONAME}" varStatus="counter">
                                <option label="${cbo.subjectname}" value="${cbo.subjectID}"></option>
                            </c:forEach>
                        </select><br>
                        <div class="buttonSearchAdmin">  
                            <input class="searchButton" type="submit" value="TakeQuiz" name="btAction" />
                        </div>
                    </div>
                </form>
            </div>
        </c:if>
    </body>
</html>
