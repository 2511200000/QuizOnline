<%-- 
    Document   : login
    Created on : Jan 27, 2021, 7:56:37 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSS1/khanh.css">
        <title>Login Page</title>
    </head>
    <body <c:if test="${not empty requestScope.ERRORLGOIN}">
            onload="alertMessage()"
        </c:if> >
        <div class="imageHanaShop"></div>
        <div class="loginPage">
            <div class="loginForm">
                <h1>Login Page</h1>
                <form action="Login" method="POST">
                    <b> Username: </b><input id="txtTextfile" type="text" name="txtUsername" value="" /><br />
                    <b> Password:</b> <input id="txtTextfile" type="password" name="txtPassword" value="" /><br />
                    <div class="btLogin">
                        <input id="buttonLogin" type="submit" value="Login" name="btAction" />
                        <input id="buttonLoginReset" type="reset" value="Reset" />

                    </div>
                    <div class="createAccount">
                        <b>  <a href="createAccount.jsp">Create New Account</a></b>
                    </div>
                </form>
            </div>
        </div>
        <div class="imageHanaShopBottom"></div>   

        <script>
            function alertMessage() {
                alert("${requestScope.ERRORLGOIN}");
            }
        </script>

    </body>
</html>
