<%-- 
    Document   : updateErr.jsp
    Created on : Feb 20, 2021, 3:05:23 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSS1/khanh.css">
        <title>UpdateErr</title>
    </head>
    <body>
        <c:set var="result" value="${sessionScope.DISPLAYADMIN}"/>  
        <c:if test="${empty result}">
            <div class="Fail"></div>
            <h2> <a href="login.jsp">Please login first</a></h2>
        </c:if>

        <c:if test="${not empty result}">
            <div class="Fail"></div>
            <h2> <a href="displayAdmin.jsp">Back to HomePage</a></h2>
        </c:if>
    </body>
</html>
