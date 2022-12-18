<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
    <!-- Image -->
<form method="post" action="/tiles/add" enctype="multipart/form-data">
    <div class="mb-3">
        <label for="image" class="form-label">Car Image</label>
        <input type="file" name="image" class="form-control" id="image">
    </div>
    <!-- Price -->
    <div class="mb-3">
        <label for="price" class="form-label">Price</label>
        <input type="text" name="price" class="form-control" id="price" aria-describedby="nameHelp">
        <div id="nameHelp" class="form-text">Enter price</div>
    </div>
    <!-- Production Date -->
    <div class="mb-3">
        <label for="productionDate" class="form-label">Production Date</label>
        <input type="text" name="carModel.productionDate" class="form-control" id="productionDate" aria-describedby="nameHelp">
        <div id="nameHelp" class="form-text">Enter Production Date</div>
    </div>
    <!-- Mark -->
    <div class="mb-3">
        <label for="mark" class="form-label">Mark</label>
        <input type="text" name="carModel.mark" class="form-control" id="mark" aria-describedby="nameHelp">
        <div id="nameHelp" class="form-text">Enter Mark</div>
    </div>
    <!-- Color -->
    <div class="mb-3">
        <label for="color" class="form-label">Color</label>
        <input type="text" name="carModel.color" class="form-control" id="color"
               aria-describedby="nameHelp">
        <div id="nameHelp" class="form-text">Enter city name</div>
    </div>
    <!-- Type -->
    <div class="mb-3">
        <label for="type">Choose a Car Type:</label>
        <select class="form-select" id="type" name="type">
            <c:forEach items="${types}" var="type">
                <option value="${type}">${type}</option>
            </c:forEach>
        </select>
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>

</body>
</html>
