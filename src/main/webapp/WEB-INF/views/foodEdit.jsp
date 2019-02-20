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
   <h2>${pageHeader}</h2>
</div>

<div class="container">
   <form action="/foods/update" method="post">
      <div class="form-group row">
         <label for="foodId" class="col-2 col-form-label">Id</label>
         <div class="col-10">
            <input class="form-control" type="text" value="${foodId}" id="foodId" name="foodId" readonly="readonly">
         </div>
      </div>

      <div class="form-group row">
         <label for="foodName" class="col-2 col-form-label">Food name</label>
         <div class="col-10">
            <input class="form-control" type="text" value="${foodName}" id="foodName" name="foodName">
         </div>
      </div>

      <div class="form-group row">
        <label class="control-label col-sm-2" for="toDate">dateTo </label>
        <div class="col-sm-10">
          <input id="foodDate" name="foodDate" class="input-append" width="275" value="${foodDate}"/>
        </div>
      </div>

      <div class="form-group row">
         <label for="foodCalories" class="col-2 col-form-label">Calories</label>
         <div class="col-10 input-append">
            <input class="form-control" type="text" value="${foodCalories}" id="foodCalories" name="foodCalories">
         </div>
      </div>

      <script>
          $('#foodDate').datetimepicker({
              format: 'yyyy-mm-dd HH:MM:ss',
              pickTime: true,
              uiLibrary: 'bootstrap4'
          });
      </script>

   <button type="submit" class="btn btn-primary">Submit</button>
   <a href="/foods/delete/${foodId}" class="btn btn-danger" role="button">Delete</a>
   </form>
</div>

</body>

</html>
