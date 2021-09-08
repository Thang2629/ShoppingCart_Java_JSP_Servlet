<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
	integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU"
	crossorigin="anonymous">
<link href="css/signup.css" rel="stylesheet" type="text/css" />
<title>Sign Up Form</title>
</head>
<body>

	<div id="logreg-forms">
		<form action="signup" method="post" class="form-signup">
			<h1 class="h3 mb-3 font-weight-normal" style="text-align: center">
				Sign up</h1>
				 <p class="text-danger">${mess}</p>
			<input name="user" type="text" id="user-name" class="form-control"
				placeholder="User name" required="" autofocus="">
			<input onkeyup='check();' name="pass" type="password" id="user-pass" class="form-control"
				placeholder="Password" required autofocus=""> 
			<span style="color: red;" id="notify"></span>
			<input name="repass" type="password" id="user-repeatpass"
				class="form-control" placeholder="Repeat Password" required
				autofocus="" onkeyup='check();'>
				

			<button class="btn btn-primary btn-block" type="submit" id="submit">
				<i class="fas fa-user-plus"></i> Sign Up
			</button>
			<a href="Login.jsp" id="cancel_signup"><i
				class="fas fa-angle-left"></i> Back</a>
		</form>
	</div>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
	<script>
	var check = function() {
      if (document.getElementById('user-pass').value ==
          document.getElementById('user-repeatpass').value) {
          document.getElementById('notify').style.color = 'green';
          document.getElementById('notify').innerHTML = 'matching';
          document.getElementById('submit').disabled = false;
          document.getElementById('user-repeatpass').style.borderColor = 'green';
      } else {
      		document.getElementById('notify').style.color = 'red';
          document.getElementById('notify').innerHTML = 'not matching';
          document.getElementById('submit').disabled = true;
          document.getElementById('user-repeatpass').style.borderColor = 'red';
      }
  }
	</script>
</body>
</html>