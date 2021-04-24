<%-- 
    Document   : test
    Created on : Feb 8, 2021, 2:03:51 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSS1/khanh.css">
        <title>Exams</title>
    </head>
    <body >
        <c:set var="result" value="${sessionScope.FULLNAMEUSER}"/>  
        <c:if test="${empty result}">
            <div class="Fail"></div>
            <h2> <a href="login.jsp">Please login first</a></h2>
        </c:if>

        <c:if test="${not empty result}">
            <div class="backgoundCreateItem">
                <c:set var="name" value="${sessionScope.FULLNAMEUSER}"/>
                <c:if test="${not empty name}">
                    <font color="red">
                    Name: ${sessionScope.FULLNAMEUSER}
                    </font><br>
                </c:if>
            </div>


            <c:set var="result" value="${sessionScope.QUESTIONUSER}"/>
            <c:if test="${not empty result}">

                <div >
                    <table border="1" class="tableAdmin">
                        <thead>
                            <tr>
                                <th  class="columnAdmin">QuestionContent</th>
                                <th  class="columnAdmin">AnswerContent</th>
                            </tr>
                        </thead>
                        <tbody>
                        <form action="Test">

                            <div class="displayAdmin">
                                <div id="clockdiv">                       
                                    <div>
                                        <span class="hours"></span>
                                        <div class="smalltext">Hours</div>
                                    </div>
                                    <div>
                                        <span class="minutes"></span>
                                        <div class="smalltext">Minutes</div>
                                    </div>
                                    <div>
                                        <span class="seconds"></span>
                                        <div class="smalltext">Seconds</div>
                                    </div>
                                </div>
                            </div>

                            <c:forEach var="dto" items="${result}" varStatus="counter">
                                <tr>
                                    <td  class="rowContent">
                                        <div class="question">
                                            Question ${counter.count}: <br>
                                        </div>
                                        ${dto.questionContent}
                                        <input type="hidden" name="txtSubject" value="${dto.subjectID}" />
                                        <input type="hidden" name="txtID" value="${dto.id}" />
                                    </td>
                                    <td class="rowAnswer">
                                        <div class="choose">
                                             (Only choose one) <br>  
                                        </div>                                     
                                        <input type="checkbox" name="chkItem" value="${dto.answerContent1}" />
                                        A.${dto.answerContent1}<br>
                                        <input type="checkbox" name="chkItem" value="${dto.answerContent2}" />
                                        B.${dto.answerContent2}<br>
                                        <input type="checkbox" name="chkItem" value="${dto.answerContent3}" />
                                        C.${dto.answerContent3}<br>
                                        <input type="checkbox" name="chkItem" value="${dto.answerContent4}" />
                                        D.${dto.answerContent4}<br>
                                    </td>                  
                                </tr>                 

                            </c:forEach> 

                            <input id="submit" type="submit" value="Finish" name="btAction" />
                        </form>
                        </tbody>
                    </table>
                </div>
            </c:if>
            <script>
                function getTimeRemaining(endtime) {
                    var t = Date.parse(endtime) - Date.parse(new Date());
                    var seconds = Math.floor((t / 1000) % 60);
                    var minutes = Math.floor((t / 1000 / 60) % 60);
                    var hours = Math.floor((t / (1000 * 60 * 60)) % 24);
                    return {
                        'total': t,
                        'hours': hours,
                        'minutes': minutes,
                        'seconds': seconds
                    };
                }

                function initializeClock(id, endtime) {
                    var clock = document.getElementById(id);
                    var hoursSpan = clock.querySelector('.hours');
                    var minutesSpan = clock.querySelector('.minutes');
                    var secondsSpan = clock.querySelector('.seconds');

                    function updateClock() {
                        var t = getTimeRemaining(endtime);

                        hoursSpan.innerHTML = ('0' + t.hours).slice(-2);
                        minutesSpan.innerHTML = ('0' + t.minutes).slice(-2);
                        secondsSpan.innerHTML = ('0' + t.seconds).slice(-2);

                        if (t.total <= 0) {
                            document.getElementById('submit').click();
                            clearInterval(timeinterval);
                        }
                    }

                    updateClock();
                    var timeinterval = setInterval(updateClock, 1000);
                }

                var deadline = new Date(Date.parse(new Date()) + 10 * 60 * 1000);
                initializeClock('clockdiv', deadline);
            </script>

        </c:if>
    </body>
</html>
