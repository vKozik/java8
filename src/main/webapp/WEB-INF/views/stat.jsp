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
  <h2>Calorie statistics</h2>
  <form class="form-inline" action="/stat">
    <div class="form-group row">
      <label class="control-label col-sm-2" for="fromDate">dateFrom </label>
      <div class="col-sm-10">
        <input id="fromDate" name="fromDate" width="175"  value="${fromDate}"/>
      </div>
    </div>

    <div class="form-group row">
      <label class="control-label col-sm-2" for="toDate">dateTo </label>
      <div class="col-sm-10">
        <input id="toDate" name="toDate" width="175" value="${toDate}"/>
      </div>
    </div>

    <div class="form-group row">
      <label class="control-label col-sm-2" for="norma">norma </label>
      <div class="col-sm-10">
        <input id="norma" name="norma" width="10" value = "${norma}"/>
      </div>
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
  </form>
</div>

      <script>
          $('#fromDate').datepicker({
              format: 'yyyy-mm-dd',
              uiLibrary: 'bootstrap4'
          });
          $('#toDate').datepicker({
              format: 'yyyy-mm-dd',
              uiLibrary: 'bootstrap4'
          });
      </script>

<div class="container">
  <table class="table table-hover">
    <thead>
      <tr>
        <c:if test="${!isReadOnly}">
           <th>Id</th>
        </c:if>
        <th>food</th>
        <th>Date</th>
        <th>calories</th>
        <th>calories per day</th>
        <th>result</th>
      </tr>
    </thead>

    <tbody>
      <c:forEach items="${foodStats}" var="foodStat">
        <tr <c:if test="${foodStat.caloriesPerDay > norma}"> class="table-danger" </c:if>>
          <c:if test="${!isReadOnly}">
            <td>
             <a href="/foods/update/${foodStat.id}">${foodStat.id}</a>
            </td>
          </c:if>
          <td>${foodStat.name}</td>
          <td>${foodStat.dateOfEating}</td>
          <td>${foodStat.calories}</td>
          <td>${foodStat.caloriesPerDay}</td>
          <td>
            <c:if test="${foodStat.caloriesPerDay > norma}">
              exceeded
            </c:if>
          <td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</div>

</body>

</html>
