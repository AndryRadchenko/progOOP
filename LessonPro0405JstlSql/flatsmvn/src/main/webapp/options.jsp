<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Options</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/filter">
    <table border="1">
        <tr>
            <td>District</td>
            <td>Street</td>
            <td>Rooms From</td>
            <td>Rooms To</td>
            <td>Area From</td>
            <td>Area To</td>
            <td>Price From</td>
            <td>Price To</td>
        </tr>
        <tr>
            <td>
                <select name = "district">
                    <option value="">ВСЕ</option>
                    <c:forEach items="${districtList}" var="district">
                        <option value="${district}" ><c:out value="${district}"/></option>
                    </c:forEach>
                </select>
            </td>
            <td>
                <select name = "street">
                    <option value="">ВСЕ</option>
                    <c:forEach items="${streetList}" var="street">
                        <option value="${street}" ><c:out value="${street}"/></option>
                    </c:forEach>
                </select>
            </td>
            <td>
                <input type="text" name = "roomsfrom" value="${roomsMin}">
            </td>
            <td>
                <input type="text" name = "roomsto" value="${roomsMax}">
            </td>
            <td>
                <input type="text" name = "areafrom" value="${areaMin}">
            </td>
            <td>
                <input type="text" name = "areato" value="${areaMax}">
            </td>
            <td>
                <input type="text" name = "pricefrom" value="${priceMin}">
            </td>
            <td>
                <input type="text" name = "priceto" value="${priceMax}">
            </td>
        </tr>
    </table>
    <input value="FILTER" type="submit">
</form>

</body>
</html>


