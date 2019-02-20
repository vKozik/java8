<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
  <title>Calories</title>
  <meta charset="utf-8">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/gijgo@1.9.6/css/gijgo.min.css" type="text/css" />
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/gijgo@1.9.6/js/gijgo.min.js" type="text/javascript"></script>
</head>
<body>

<div class="container">
   <h2>All foods</h2>
    <c:if test="${isReadOnly}">
        <button type="button" class="btn btn-outline-primary" disabled>Add food</button>
    </c:if>
    <c:if test="${!isReadOnly}">
        <a href="/foods/add" class="btn btn-outline-primary" role="button">Add food</a>
    </c:if>
    <a href="/stat" class="btn btn-outline-primary" role="button">Statistic</a>
</div>

<div class="container">
  <table class="table table-hover">
    <thead>
      <tr>
        <c:if test="${!isReadOnly}">
           <th>Id</th>
        </c:if>
        <th>Food name</th>
        <th>Date</th>
        <th>Calories</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${foods}" var="food">
        <tr>
          <c:if test="${!isReadOnly}">
            <td>
              <a href="/foods/update/${food.id}">${food.id}</a>
            </td>
          </c:if>
          <td>${food.name}</td>
          <td>${food.dateOfEating}</td>
          <td>${food.calories}</td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</div>

</body>

</html>
