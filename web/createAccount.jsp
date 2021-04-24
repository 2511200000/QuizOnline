<%-- 
    Document   : createAccount
    Created on : Jan 29, 2021, 2:54:44 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSS1/khanh.css">
        <title>CreateAccount</title>
    </head>
    <body  
        <c:if test="${not empty requestScope.CREATEERR}">
            onload="alertMessage()"
        </c:if>
        >
        
        <div class="backgoundCreateAccount"> <b><a href="login.jsp">Click here back to loginPage</a></b></div>
        <div class="createPage">
            <div class="createForm">
                <form action="CreateAccount" method="POST">
                    <h1>CreateAccount</h1>
                    Email:</b> <input id="txtTextfile" type="text" name="txtEmail" value="" /><br>
                    Name: </b> <input id="txtTextfile" type="text" name="txtName" value="" /><br>
                    Password: </b> <input id="txtTextfile" type="password" name="txtPassword" value="" /><br>
                    Confirm </b> <input id="txtTextfile" type="password" name="txtConfirm" value="" /><br>
                    <div class="buttonCreate">
                        <input id="buttonLogin" type="submit" value="CreateNewAccount" name="btAction" />
                        <input id="buttonLoginReset" type="reset" value="Reset" /><br>
                    </div>
                </form>
            </div>
        </div>

        <script>
            function alertMessage() {
                alert("${requestScope.CREATEERR}");
            }
        </script>
    </body>
</html>
