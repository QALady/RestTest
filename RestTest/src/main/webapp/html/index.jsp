<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>QA Forum Login</title>

<!-- CSS -->
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
<link href="/RestTest/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="/RestTest/css/font-awesome.min.css" />
<link rel="stylesheet" href="/RestTest/css/form-elements.css">
<link rel="stylesheet" href="/RestTest/css/login-style.css">

<script src="/RestTest/js/ext_lib/angular.min.js"></script>
<script src="/RestTest/js/ext_lib/ngStorage.js"></script>
<script src="/RestTest/js/ext_lib/ui-bootstrap-tpls-0.14.3.js"></script>
<script src="/RestTest/js/login/app.js"></script>
<script src="/RestTest/js/login/service.js"></script>
<script src="/RestTest/js/login/ctrl.js"></script>
<script src="/RestTest/js/login/directive.js"></script>


</head>

<body style="background-color: rgba(181, 216, 143, 1);">
	<div ng-app="loginApp" ng-controller="ctrl as vm" ng-init="init()">
		<login-form></login-form>
	</div>
</body>

</html>