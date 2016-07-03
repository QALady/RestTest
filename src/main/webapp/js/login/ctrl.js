(function() {
	var app = angular.module('loginApp');

	app.controller('ctrl', ['$scope', '$http', '$localStorage', '$window', function($scope, $http, $localStorage, $window) {
		var vm = this;
		/*$window.$storage = $localStorage.$default({
	          loginId: ''
	    });*/
		
		$scope.init = function() {
			var loginId = $window.localStorage.getItem('loginId');
			console.log('Login Id : '+loginId);
			if(loginId != '' && typeof loginId != 'undefined' && loginId != null) {
				$window.location.href = "/RestTest/html/home/index.html";
			}
		};
		
		vm.login = function() {
			var loginUrl = "http://localhost:8080/RestTest/webapi/login/";
			if(typeof vm.loginData == 'undefined') {
				vm.loginData = {};
			}
			vm.loginData.showLoginMessage = false;
			vm.loginData.loginErrorMsg = '';
			
			angular.forEach(vm.loginForm.$error.required, function(field) {
			    field.$setDirty();
			});
			
			if(vm.loginForm.$valid) {
				console.log('Valid');
				loginUrl += "authenticate";
				var dataObj = {
						loginId : vm.loginData.username,
						password : vm.loginData.password
					};
				$http.post(loginUrl, dataObj).success(function(response) {
					console.log(response.type);
					console.log(response.description);
					if(response.type == 'success') {
						$window.localStorage.setItem('loginId', response.loginDto.loginId);
						$window.localStorage.setItem('sessionId', response.loginDto.sessionId);
						$window.location.href = "/RestTest/html/home/index.html";
					} else {
						vm.loginData.loginErrorMsg = "User name and password do not match!";
						vm.loginData.showLoginMessage = true;
					}
				});
			} else {
				vm.loginData.showLoginMessage = true;
			}
		};
		
	}]);
}());