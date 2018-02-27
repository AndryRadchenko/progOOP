<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 12.02.2018
  Time: 9:57
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>


    <table border="1">
      <tr>
          <form action="${pageContext.request.contextPath}/view">
              <td>
                  <button name="parameter" value="clients" type="submit">View clients</button>
              </td>
              <td>
                  <button name="parameter" value ="goods" type="submit">View goods</button>
              </td>
              <td>
                  <button name="parameter" value="orders" type="submit">View orders</button>
              </td>
        </form>
      </tr>
        <tr>
            <form action="${pageContext.request.contextPath}/adddialog">
                <td>
                    <button name="parameter" value="clients" type="submit">Add clients</button>
                </td>
                <td>
                    <button name="parameter" value ="goods" type="submit">Add goods</button>
                </td>
                <td>
                    <button name="parameter" value="orders" type="submit">Add orders</button>
                </td>
            </form>
        </tr>
    </table>


  </body>
</html>
