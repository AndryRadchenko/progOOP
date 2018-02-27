<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

    <c:if test="${flatsList eq null}">
        <p>No matches found</p>
    </c:if>
    <c:if test="${fn:length(flatsList) eq 0}">
        <p>No matches found</p>
    </c:if>
    <c:if test="${fn:length(flatsList) gt 0}">
        <table border="1">
            <tr>
                <td>District</td>
                <td>Street</td>
                <td>Rooms</td>
                <td>Area</td>
                <td>Price</td>
            </tr>
            <c:forEach items="${flatsList}" var="flat">
                <tr>
                    <td><c:out value="${flat.district}"/></td>
                    <td><c:out value="${flat.street}"/></td>
                    <td><c:out value="${flat.rooms}"/></td>
                    <td><c:out value="${flat.area}"/></td>
                    <td><c:out value="${flat.price}"/></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <br>

    <form action="${pageContext.request.contextPath}/">
        <input type="submit" value="Back" />
    </form>

</body>
</html>