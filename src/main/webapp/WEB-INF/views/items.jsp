<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<style>
body {
    background: linear-gradient(to right,#2c5364, #203a43, #0f2027);
}

.button {
  background-color: #008CBA;
  border: none;
  color: white;
  font-size: 16px;
  margin: 0px 1px;
  opacity: 0.6;
  transition: 0.3s;
  display: inline-block;
  text-decoration: none;
  cursor: pointer;
  border-radius: 4px;
}

.button:hover {opacity: 1}

#custom {
  font-family: Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

#custom td, #custom th {
  border: 1px solid lightblue;
  padding: 8px;
}
#custom tr {background-color: lightblue;}

#custom tr:hover {background-color: #008cff;}

#custom th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: #008CBA;
  color: white;
}
</style>
</head>
    <body>
    <table id="custom">
        <tr>
            <th>Inventory Number</th>
            <th>Description</th>
            <th>Count items</th>
            <th>Office number</th>
            <th>Operations</th>
        </tr>
    <c:forEach var="item" items="${listItems}">

    <c:url var="updateButton" value="/updateInfo">
    <c:param name="itemId" value="${item.id}"/></c:url>

    <c:url var="deleteButton" value="/deleteItem">
    <c:param name="itemId" value="${item.id}"/></c:url>

        <tr>
            <td>${item.number}</td>
            <td>${item.description}</td>
            <td>${item.countItems}</td>
            <td>${item.office.officeNumber}</td>
            <td>
            <button class="button" onclick="window.location.href='${updateButton}'">Update</button>
            <button class="button" onclick="window.location.href='${deleteButton}'">Delete</button>
            </td>
        </tr>
    </c:forEach>
    </table>
    </body>
</html>