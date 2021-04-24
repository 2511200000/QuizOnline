<%-- 
    Document   : displayAdmin
    Created on : Jan 27, 2021, 8:19:43 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSS1/khanh.css">
        <title>Admin</title>
    </head>
    <body>
        <c:set var="result" value="${sessionScope.DISPLAYADMIN}"/>  
        <c:if test="${empty result}">
            <div class="Fail"></div>
            <h2> <a href="login.jsp">Please login first</a></h2>
        </c:if>
        <c:if test="${not empty result}">
            <div class="hearderDisplayAdmin">
                <ul class="nav-listAdmin">
                    <li class="nav-itemAdmin">
                        <c:set var="name" value="${sessionScope.FULLNAMEADMIN}"/>
                        <c:if test="${not empty name}">
                            <font color="red">
                            Welcome, ${sessionScope.FULLNAMEADMIN}
                            </font><br>
                        </c:if>
                    </li>
                </ul>
                <ul class="nav-listAdmin">
                    <li class="nav-itemAdmin">
                        <form action="LogOut">
                            <input type="submit" value="LogOut" name="btAction" />
                        </form>
                    </li>
                </ul>
            </div>

            <h1 class="h1Admin">Admin</h1>

            <div class="searchAdmin">
                <div class="searchAdminList">
                    <form action="SearchQuestionName">
                        <div class="textSearch">
                            Search Value <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" />


                            <select name="cboSubject">
                                <option label="Ple choose subject" value=""></option>
                                <c:forEach var="cbo" items="${sessionScope.CBONAME}" varStatus="counter">
                                    <option label="${cbo.subjectname}" value="${cbo.subjectID}"></option>
                                </c:forEach>
                            </select>


                            <select name="cboStatus">
                                <c:set var="cbo" value="${sessionScope.STATUS}" />

                                <c:forEach var="cboName" items="${cbo}" varStatus="counter">

                                    <option label="${cboName.value}" value="${cboName.key}"
                                            <c:if test="${cboName.key eq 'true'}">
                                                selected="selected"
                                            </c:if>
                                            >

                                    </option>
                                </c:forEach>
                            </select><br>
                        </div>

                        <div class="buttonSearchAdmin">  
                            <input type="hidden" name="txtIndex" value="1" />
                            <input class="searchButton" type="submit" value="Search" name="btAction" />
                        </div>
                    </form>
                </div>
            </div>
            <div class="createNewItem">
                <b> <a href="createQuestion.jsp">Create New Question</a></b>

            </div>
        </c:if> 

        <c:set var="result" value="${sessionScope.DISPLAYADMIN}"/>
        <c:if test="${not empty result}">                    
            <div class="displayAdmin">
                <table border="1" class="tableAdmin">
                    <thead>
                        <tr>
                            <th class="columnAdmin">No.</th>
                            <th class="columnAdmin">id</th>
                            <th class="columnAdmin">question Content</th>
                            <th class="columnAdmin">answer Content</th>                       
                            <th class="columnAdmin">answer Correct</th>
                            <th class="columnAdmin">create Date</th>
                            <th class="columnAdmin">subject ID</th>
                            <th class="columnAdmin">status</th>
                            <th class="columnAdmin">Delete</th>
                            <th class="columnAdmin">Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                        <form action="UpdateQuestion">
                            <tr>
                                <td class="rowAdmin">${counter.count}
                                    .</td>
                                <td class="rowAdmin">${dto.id}
                                    <input type="hidden" name="txtID" value="${dto.id}" />
                                </td>
                                <td class="rowAdmin">
                                    <input type="text" name="txtquestionContent" value="${dto.questionContent}" />
                                </td>
                                <td class="rowAdmin">
                                    <div  class="answer">

                                        A. <input type="text" name="txtanswerContent1" value="${dto.answerContent1}" />
                                    </div><br>

                                    <div class="answer">

                                        B.<input type="text" name="txtanswerContent2" value="${dto.answerContent2}" />
                                    </div><br>

                                    <div class="answer">
                                        C.<input type="text" name="txtanswerContent3" value="${dto.answerContent3}" />
                                    </div><br>

                                    <div class="answer">
                                        D. <input type="text" name="txtanswerContent4" value="${dto.answerContent4}" />
                                    </div><br>
                                </td>
                                <td class="rowAdmin">
                                    <select name="cboAnswer">
                                        <option
                                            <c:if test="${dto.answerContent1 eq dto.answerCorrect}">
                                                selected="selected"
                                            </c:if>
                                            >${dto.answerContent1}</option>
                                        <option
                                            <c:if test="${dto.answerContent2 eq dto.answerCorrect}">
                                                selected="selected"
                                            </c:if>
                                            >${dto.answerContent2}</option>
                                        <option
                                            <c:if test="${dto.answerContent3 eq dto.answerCorrect}">
                                                selected="selected"
                                            </c:if>
                                            >${dto.answerContent3}</option>
                                        <option
                                            <c:if test="${dto.answerContent4 eq dto.answerCorrect}">
                                                selected="selected"
                                            </c:if>
                                            >${dto.answerContent4}</option>
                                    </select>
                                </td>
                                <td class="rowAdmin">${dto.createDate}</td>
                                <td class="rowAdmin"> 
                                    <select name="cboSubject">
                                        <c:forEach var="cbo" items="${sessionScope.CBONAME}" varStatus="counter">
                                            <option label="${cbo.subjectname}" value="${cbo.subjectID}" 
                                                    <c:if test="${dto.subjectID eq cbo.subjectname}">
                                                        selected="selected"
                                                    </c:if>
                                                    ></option>                                          
                                        </c:forEach>
                                    </select>
                                </td>
                                <td class="rowAdmin">                                  
                                    <select name="cboStatus">
                                        <c:set var="cbo" value="${sessionScope.STATUS}" />
                                        <c:forEach var="cboName" items="${cbo}" varStatus="counter">
                                            <option label="${cboName.value}" value="${cboName.key}"
                                                    <c:if test="${dto.status eq cboName.key}">
                                                        selected="selected"
                                                    </c:if>
                                                    ></option>                                        
                                        </c:forEach>
                                    </select><br>
                                </td>
                                <td class="rowAdmin"><input type="submit" value="Delete" name="btAction" /></td>

                                <td class="rowAdmin"><input type="submit" value="Update" name="btAction" /></td>

                            </tr>
                        </form>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>
        <div class="paging">
            <c:forEach begin="1" end="${ENDPAGE}" var="i">
                <a id="${i}" href="PrintDataAdmin?txtIndex=${i}">${i}</a> 
            </c:forEach>
        </div>
        <script>
            document.getElementById('${INDEX}').style.color = "red";
        </script>
    </body>
</html>
