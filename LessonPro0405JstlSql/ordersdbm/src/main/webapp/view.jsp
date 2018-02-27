<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 15.02.2018
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View</title>
</head>
<body>

<%--<c:set var="object" value="${list}" />--%>

<%--<c:if test="${not empty object['class'].declaredFields}">--%>
    <%--<h2>Declared fields <em>&dollar;{object.name}</em></h2>--%>
    <%--<ul>--%>
        <%--<c:forEach var="field" items="${object['class'].declaredFields}">--%>
            <%--<c:catch><li><span>${field.name}</span>${object[field.name]}</li></c:catch>--%>
        <%--</c:forEach>--%>
    <%--</ul>--%>
<%--</c:if>--%>


<table border="1">
    <tr>
        <c:forEach var="item" items="${tableHeader}">
            <td><c:out value="${item}"/></td>
        </c:forEach>
    </tr>


        <c:if test="${clientsList ne null}">
            <c:forEach var="client" items="${clientsList}">
                <tr>
                    <td>
                        <c:out value="${client.id}"/>
                    </td>
                    <td>
                        <c:out value="${client.name}"/>
                    </td>
                    <td>
                        <c:out value="${client.lastName}"/>
                    </td>
                    <td>
                        <c:out value="${client.birthDate}"/>
                    </td>
                    <td>
                        <c:out value="${client.phone}"/>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${goodsList ne null}">
            <c:forEach var="good" items="${goodsList}">
                <tr>
                    <td>
                        <c:out value="${good.id}"/>
                    </td>
                    <td>
                        <c:out value="${good.name}"/>
                    </td>
                    <td>
                        <c:out value="${good.quantity}"/>
                    </td>
                    <td>
                        <c:out value="${good.price}"/>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${ordersList ne null}">
            <c:forEach var="order" items="${ordersList}">
                <tr>
                    <td>
                        <c:out value="${order.number}"/>
                    </td>
                    <td>
                        <c:out value="${order.clientName}"/>
                    </td>
                    <td>
                        <c:out value="${order.clientLastName}"/>
                    </td>
                    <td>
                        <c:out value="${order.goodId}"/>
                    </td>
                    <td>
                        <c:out value="${order.goodQuantity}"/>
                    </td>
                </tr>
            </c:forEach>
        </c:if>

</table>

<form action="${pageContext.request.contextPath}/">
    <input type="submit" value="Back" />
</form>

</body>
</html>
