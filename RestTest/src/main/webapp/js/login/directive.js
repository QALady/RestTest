(function() {
var app = angular.module('loginApp');

app.directive('loginForm', function() {
  return {
    templateUrl: '/RestTest/html/login/loginForm.html',
    restrict: 'E'
  };
});
}());