<%-- 
    Document   : searchHistory
    Created on : Feb 18, 2021, 10:59:06 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSS1/khanh.css">
        <title>searchHistory</title>
    </head>
    <body>
        <c:set var="result" value="${sessionScope.FULLNAMEUSER}"/>  
        <c:if test="${empty result}">
            <div class="Fail"></div>
            <h2> <a href="login.jsp">Please login first</a></h2>
        </c:if>

        <c:if test="${not empty result}">
            <div class="backgoundCreateItem">

                <form action="SearchHistory">
                    <div class="searchHistory">
                          Search Value <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" /><br>
                        <select name="cboSubjectHistory">
                            <option label="Ple choose subject" value=""></option>
                            <c:forEach var="cbo" items="${sessionScope.CBONAME}" varStatus="counter">
                                <option label="${cbo.subjectname}" value="${cbo.subjectname}"></option>
                            </c:forEach>
                        </select>
                        <input class="searchButton" type="submit" value="Search" name="btAction" />
                        <input type="hidden" name="txtIndex" value="1" />
                    </div>
                </form>
            </div>
            <c:set var="resultHistory" value="${requestScope.SEARCHHISTORY}"/>
            <c:if test="${not empty resultHistory}">
                <div class="displayAdmin">
                    <table border="1" class="tableAdmin">
                        <thead>
                            <tr>
                                <th class="columnAdmin">Id Test</th>
                                <th class="columnAdmin">ID</th>
                                <th class="columnAdmin">answer User</th>
                                <th class="columnAdmin">answer Correct User</th>
                                <th class="columnAdmin">user ID</th>
                                <th class="columnAdmin">subject</th>
                            </tr>
                        </thead>
                        <tbody> 
                            <c:forEach var="dto" items="${resultHistory}" varStatus="counter">
                                <tr>
                                    <td class="rowAdmin">${dto.idTest}</td>
                                    <td class="rowAdmin">${dto.id}</td>                            
                                    <td class="rowAdmin">${dto.answerUser}</td>                         
                                    <td class="rowAdmin">${dto.answerCorrectUser}</td>                         
                                    <td class="rowAdmin">${dto.userID}</td>
                                    <td class="rowAdmin">${dto.subject}</td>
                                </tr>
                            </c:forEach>       
                        </tbody>

                    </table>
                </div>
                <div class="paging">
                    <c:forEach begin="1" end="${ENDPAGE}" var="i">
                        <a id="${i}" href="SearchHistory?txtIndex=${i}&cboSubjectHistory=${SUBJECT}&txtSearchValue=${SEARCHVALUE}">${i}</a>
                    </c:forEach>
                </div>
            </c:if>

            <script>
                document.getElementById('${INDEX}').style.color = "red";
            </script>

            <c:if test="${empty resultHistory}">
                <h2>
                    No records
                </h2>
            </c:if>
            <div class="Backto">
                <b><a href="history.jsp">Back to History</a></b>
            </div>
        </c:if>
    </body>
</html>
