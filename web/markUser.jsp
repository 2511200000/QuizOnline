<%-- 
    Document   : markUser
    Created on : Feb 10, 2021, 10:44:14 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSS1/khanh.css">
        <title>MarkUser</title>
    </head>
    <body>
        <c:set var="result" value="${sessionScope.FULLNAMEUSER}"/>  
        <c:if test="${empty result}">
            <div class="Fail"></div>
            <h2> <a href="login.jsp">Please login first</a></h2>
        </c:if>

        <c:if test="${not empty result}">
            <c:set var="result" value="${sessionScope.DIEM}"/>
            <c:set var="correct" value="${sessionScope.CORRECT}"/>
            <c:if test="${not empty result}">
                <div class="backgoundCreateItem"></div>
                <div class="displayAdmin">
                    <table border="1" class="tableAdmin">
                        <thead>
                            <tr>                       
                                <th class="columnAdmin">Correct</th>
                                <th class="columnAdmin">Point</th>
                            </tr>
                        </thead>
                        <tbody>                  
                            <tr>                           
                                <td class="rowAdmin">${correct}</td>
                                <td class="rowAdmin">${result}</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="Backto">
                    <b><a href="displayUser.jsp">Back to course</a></b>
                </div>
                <div class="BackgroundSocre"></div>   
            </c:if>
        </c:if>
    </body>
</html>
