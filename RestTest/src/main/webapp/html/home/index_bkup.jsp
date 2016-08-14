<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<script src="/RestTest/js/ext_lib/angular.min.js"></script>
<script src="/RestTest/js/ext_lib/ngStorage.js"></script>
<script src="/RestTest/js/ext_lib/ui-bootstrap-tpls-0.14.3.js"></script>
<link href="/RestTest/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="/RestTest/css/font-awesome.min.css" />
<link rel="stylesheet" href="/RestTest/css/style.css" />
<script src="/RestTest/js/home/app.js"></script>
<script src="/RestTest/js/home/service.js"></script>
<script src="/RestTest/js/home/ctrl.js"></script>
<script src="/RestTest/js/home/directive.js"></script>

<link rel="stylesheet" type="text/css" href="/RestTest/css/angular-pagedown.css" />
<script src="/RestTest/js/ext_lib/Markdown.Converter.js"></script>
<script src="/RestTest/js/ext_lib/Markdown.Sanitizer.js"></script>
<script src="/RestTest/js/ext_lib/Markdown.Extra.js"></script>
<script src="/RestTest/js/ext_lib/Markdown.Editor.js"></script>

<script src="/RestTest/js/ext_lib/angular-pagedown.js"></script>
</head>

<body ng-app="myapp" ng-controller="RegCtrl as vm" ng-init="init()"
	class="container theme1">
	<page-header></page-header>
	<br />
	<br />
	<qa-search-form></qa-search-form>
	<Br />
	<div ng-show="vm.loadingFlag" class="form-group">
		<div class="col-sm-4"></div>
		<div class="col-sm-3">
			<img src="/RestTest/images/loading (1).gif" />
		</div>
	</div>
	<div ng-show="vm.resultFlag">
		<qa-result></qa-result>
	</div>
	<page-footer></page-footer>
</body>
</html>