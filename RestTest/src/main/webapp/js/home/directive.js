(function() {
var myApp = angular.module('myapp');

myApp.directive('qaSearchForm', function() {
  return {
    templateUrl: '/RestTest/html/home/qaSearchForm.html',
    restrict: 'E'
  };
});

myApp.directive('qaResult', function() {
	  return {
	    templateUrl: '/RestTest/html/home/qaSearchResult.html',
	    restrict: 'E'
	  };
	});

myApp.directive('pageHeader', function() {
	  return {
	    templateUrl: '/RestTest/html/home/header.html',
	    restrict: 'E'
	  };
	});

myApp.directive('pageFooter', function() {
	  return {
	    templateUrl: '/RestTest/html/home/footer.html',
	    restrict: 'E'
	  };
	});

myApp.directive('fileModel', ['$parse', function ($parse) {
    return {
       restrict: 'A',
       link: function(scope, element, attrs) {
          var model = $parse(attrs.fileModel);
          var modelSetter = model.assign;
          
          element.bind('change', function(){
             scope.$apply(function(){
                modelSetter(scope, element[0].files[0]);
             });
          });
       }
    };
 }]);
}());