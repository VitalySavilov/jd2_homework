<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<h1>Cars</h1>
<table style="width:100%" class="table">
    <tr>
        <th>Mark</th>
        <th>Type</th>
        <th>Price</th>
        <th>Image</th>
    </tr>
    <c:forEach items="${cars}" var="car">
        <tr>
            <td><c:out value="${car.carModel.mark} ${car.carModel.productionDate}"/></td>
            <td><c:out value="${car.type} ${car.carModel.color}"/></td>
            <td><c:out value="${car.price}"/></td>
            <td>
                <image src="/tiles/image/${car.carModel.image.id}/photo" class="img-thumbnail"/>
            <td>
        </tr>
    </c:forEach>
</table>
</body>
</html>


