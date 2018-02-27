<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 15.02.2018
  Time: 21:18
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>Проверки уникальности товаров и клиентов,
<p>а также доступности товаров на складе нет!!!
<p>Размер любого текстового поля Varchar(20)!!!
<p>
<form action="${pageContext.request.contextPath}/add">
    <table border="1">
        <tr>
            <c:forEach var="item" items="${tableHeader}">
                <td><c:out value="${item}"/></td>
            </c:forEach>
        </tr>
        <c:if test="${parameter eq 'client'}">
            <tr name="parameter" value="client">
                <td>
                    <input type="text" name = "name">
                </td>
                <td>
                    <input type="text" name = "lastname">
                </td>
                <td>
                    <input type="date" name = "birthdate">
                </td>
                <td>
                    <input type="text" name = "phone">
                </td>
            </tr>
        </c:if>
        <c:if test="${parameter eq 'good'}">
            <tr>
                <td>
                    <input type="text" name = "name">
                </td>
                <td>
                    <input type="number" name = "quantity">
                </td>
                <td>
                    <input type="number" name = "price">
                </td>
            </tr>
        </c:if>
        <c:if test="${parameter eq 'order'}">
            <tr>
                <td>

                    <c:if test="${clients ne null}">
                        <select name = "clientid">
                            <option value=""></option>
                            <c:forEach items="${clients}" var="client">
                                <option value="${client.id}" ><c:out value="${client.name}  ${client.lastName}"/></option>
                            </c:forEach>
                        </select>
                    </c:if>

                </td>
                <td>
                    <c:if test="${goods ne null}">
                        <select name = "goodid">
                            <option value=""></option>
                            <c:forEach items="${goods}" var="good">
                                <option value="${good.id}" ><c:out value="${good.name}"/></option>
                            </c:forEach>
                        </select>
                    </c:if>
                </td>
                <td>
                    <input type="number" name = "quantity">
                </td>
            </tr>
        </c:if>
    </table>
    <button name="parameter" value="${parameter}" type="submit">Add a new ${parameter}</button>
</form>
<form action="/">
    <input type="submit" value="Back" />
</form>
</body>
</html>
