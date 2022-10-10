<%@ page language="java" contentType="text/html;charset=utf-8"%>

<html>
<body>
<h2>Заполните форму</h2>
<form action="/home/information.do" method="GET">
  <label for="uname">Имя:</label><br>
  <input type="text" id="uname" name="uname"><br>
  <label for="tnumber">Номер телефона:</label><br>
  <input type="text" id="tnumber" name="tnumber"><br>
  <label for="emale">Emale:</label><br>
  <input type="text" id="emale" name="emale"><br><br>
  <input type="submit" value="Submit">
</form>
</body>
</html>