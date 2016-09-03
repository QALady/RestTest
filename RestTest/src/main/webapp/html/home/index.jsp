<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<c:set var="rootPath" value="/RestTest" />
<head>
<script src="<c:out value="${rootPath}"/>/js/ext_lib/angular.min.js"></script>
<script src="<c:out value="${rootPath}"/>/js/ext_lib/angular-animate.js"></script>
<script
	src="<c:out value="${rootPath}"/>/js/ext_lib/jquery-3.1.0.min.js"></script>
<script src="<c:out value="${rootPath}"/>/js/ext_lib/ngStorage.js"></script>
<script
	src="<c:out value="${rootPath}"/>/js/ext_lib/ui-bootstrap-tpls-0.14.3.js"></script>
<link rel="icon" type="image/png" sizes="96x96"
	href="<c:out value="${rootPath}"/>/images/favicon/favicon-96x96.png">
<link href="<c:out value="${rootPath}"/>/css/bootstrap.min.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="<c:out value="${rootPath}"/>/css/font-awesome.min.css" />
<link rel="stylesheet" href="<c:out value="${rootPath}"/>/css/style.css" />
<script src="<c:out value="${rootPath}"/>/js/home/app.js"></script>
<script src="<c:out value="${rootPath}"/>/js/home/service.js"></script>
<script src="<c:out value="${rootPath}"/>/js/home/ctrl.js"></script>
<script src="<c:out value="${rootPath}"/>/js/home/directive.js"></script>
<script src="<c:out value="${rootPath}"/>/js/home/filter.js"></script>

<link rel="stylesheet" type="text/css"
	href="<c:out value="${rootPath}"/>/css/angular-pagedown.css" />
<script
	src="<c:out value="${rootPath}"/>/js/ext_lib/Markdown.Converter.js"></script>
<script
	src="<c:out value="${rootPath}"/>/js/ext_lib/Markdown.Sanitizer.js"></script>
<script src="<c:out value="${rootPath}"/>/js/ext_lib/Markdown.Extra.js"></script>
<script src="<c:out value="${rootPath}"/>/js/ext_lib/Markdown.Editor.js"></script>

<script
	src="<c:out value="${rootPath}"/>/js/ext_lib/angular-pagedown.js"></script>
</head>

<body ng-app="myapp" ng-controller="RegCtrl as vm" ng-init="init()"
	class="container-fluid theme1">
	<page-header></page-header>
	<br />
	<br />
	<div class="row">
		<div class="col-sm-3 menu-row">
			<left-menu></left-menu>
		</div>
		<div class="col-sm-8">
			<qa-search-form></qa-search-form>
		</div>
		<div class="col-sm-1"></div>
	</div>
	<Br />
	<div ng-show="vm.loadingFlag" class="form-group">
		<div class="col-sm-2"></div>
		<div class="col-sm-8">
			<img src="<c:out value="${rootPath}"/>/images/loading (1).gif" />
		</div>
		<div class="col-sm-2"></div>
	</div>
	<div ng-show="vm.resultFlag" class="row">
		<div class="col-sm-2"></div>
		<div class="col-sm-8">
			<qa-result></qa-result>
		</div>
		<div class="col-sm-2"></div>
	</div>
	<page-footer></page-footer>
</body>
</html>