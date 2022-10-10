<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<style>
table, th, td {
  border:1px solid black;
}
</style>
<h1>Информация о пользователе</h1>
<table style="width:100%">
  <tr>
    <th>Имя</th>
    <th>Номер телефона</th>
    <th>Emale</th>
  </tr>
  <tr>
    <td><c:out value="${user.name}"/></td>
    <td><c:out value="${user.telNumber}"/></td>
    <td><c:out value="${user.emale}"/></td>
  </tr>
</table>
</html>