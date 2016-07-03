(function() {
	var myApp = angular.module('myapp');

	myApp.filter('startFrom', function() {
	    return function(input, start) {
	        start = +start; // parse to int
	        if(typeof input == 'undefined') {
	        	return 0;
	        }
	        return input.slice(start);
	    };
	});

	
	myApp.controller('RegCtrl', ['$scope', '$http', '$uibModal', '$localStorage', '$window', 
	                             function($scope, $http, $uibModal, $localStorage, $window) {
		var vm = this;
		vm.resultFlag = false;
		vm.currentPage = 0;
	    vm.pageSize = 6;
	    vm.searchOptionA = [{key: 'questionId', value:'Question #'},{key:'question', value:'Question'},
	                        {key:'answer', value:'Answer'}, {key:'type', value:'Type'}];
	    vm.operatorA = [{key:'equals', value:'Equals'},{key:'greaterThan', value:'Greater Than'},
	                    {key:'lessThan', value:'Less Than'},{key:'startsWith', value:'Starts With'},
	                    {key:'endsWith', value:'Ends With'},{key:'contains', value:'Contains'}];
	    vm.readMoreFlag = true;
	    vm.readTitle = "Read More";
	    vm.numLimit=100;
	    vm.readMore=function(){
	    	if(vm.readMoreFlag) {
	    		vm.numLimit=10000;
	    		vm.readMoreFlag = false;
	    		vm.readTitle = "Read Less";
	    	} else {
	    		vm.numLimit=100;
	    		vm.readMoreFlag = true;
	    		vm.readTitle = "Read More";
	    	}
	    };

	    
	    $scope.init = function() {
			if($window.localStorage.getItem('loginId') == null || 
					$window.localStorage.getItem('sessionId') == null) {
				$window.location.href = "/RestTest/html/login/index.html";
			} else {
				vm.loginId = $window.localStorage.getItem('loginId');
			}
		};
		
		vm.logout = function() {
			var logoutUrl = "http://localhost:8080/RestTest/webapi/login/logout";
			var dataObj = {
					loginId : $window.localStorage.getItem('loginId')
				};
			$http.post(logoutUrl, dataObj).success(function(response) {
				if(response != null && response.type == 'success') {
					$window.localStorage.removeItem('loginId');
					$window.localStorage.removeItem('sessionId');
					$window.location.href = "/RestTest/html/login/index.html";
				}
			});
		};

	    vm.numberOfPages=function(){
	    	if(typeof vm.qaInfos == 'undefined') {
	    		return 0;
	    	}
	        return Math.ceil(vm.qaInfos.length/vm.pageSize);                
	    }
		// add submit event
		vm.submit = function() {
			var searchQaUrl = "http://localhost:8080/RestTest/webapi/qaInfo/";
			vm.searchShowMessage= false;
			if(typeof vm.searchForm == 'undefined') {
				vm.searchForm = {};
			}
			
			if (vm.searchForm.searchValue == '' || typeof vm.searchForm.searchValue == 'undefined') {
				searchQaUrl += "qaList";
				$http.get(searchQaUrl).success(function(response) {
					vm.qaInfos = response.qaInfos;
					vm.resultFlag = true;
				});
			} else {
				angular.forEach(vm.searchFm.$error.required, function(field) {
				    field.$setDirty();
				});
				
				if(vm.searchFm.$valid) {
					searchQaUrl += "searchInfo";
					var dataObj = {
							searchOption : vm.searchForm.searchOption.key,
							operator : vm.searchForm.searchOperator.key,
							searchValue : vm.searchForm.searchValue
						};
					$http.post(searchQaUrl, dataObj).success(function(response) {
						vm.currentPage = 0;
						vm.qaInfos = response.qaInfos;
						vm.resultFlag = true;
					});
				} else {
					vm.searchShowMessage= true;
				}
			}
		};

		// add show add window event
		vm.showAddWindow = function() {
			var modalInstance = $uibModal.open({
				templateUrl : '/RestTest/html/home/addModal.html',
				size : 'lg',
				controller : 'ModalInstanceCtrl',
				scope:$scope,
				resolve: {
					vm: function(){
						return vm;
					}
				}
			});

			modalInstance.result.then(function() {

			}, function() {
			});

		};
		
		// Add show update window event
		vm.showUpdateWindow = function(index) {
			var itemIndex = vm.pageSize*vm.currentPage+index;
			$uibModal.open({
				templateUrl : '/RestTest/html/home/updateModal.html',
				size : 'lg',
				controller : 'UpdateModalCtrl',
				scope:$scope,
				resolve: {
					qaInfo: function(){
						var qaInfo={};
						qaInfo.qaId = vm.qaInfos[itemIndex].qaId;
						qaInfo.question = vm.qaInfos[itemIndex].question;
						qaInfo.answer = vm.qaInfos[itemIndex].answer;
						qaInfo.type = vm.qaInfos[itemIndex].type;
						return qaInfo;
					},
					vm: function() {
						return vm;
					}
				}
			});
		};
		
		// Add show delete window event
		vm.showDeleteWindow = function(index) {
			var modalInstance = $uibModal.open({
				templateUrl : '/RestTest/html/home/deleteModal.html',
				size : 'lg',
				scope:$scope,
				controller : 'DeleteModalCtrl',
				resolve: {
					qaId: function(){
						return vm.qaInfos[index].qaId;
					},
					vm: function() {
						return vm;
					}
				}
			});

			modalInstance.result.then(function() {
				
			}, function() {
			});

		};
		
		vm.exportQaInfo = function(fileType) {
			var exportUrl='http://localhost:8080/RestTest/webapi/qaInfo/exportQaInfo/';
			var extension = '';
			var mimetype='';
			if(fileType == 'excel') {
				exportUrl += 'excel';
				extension="xlsx";
				mimetype="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
			} else if(fileType == 'pdf') {
				exportUrl += 'pdf';
				extension="pdf";
				mimetype="";
			} else {
				exportUrl += 'word';
				extension="doc";
				mimetype="";
			}
			
			$http({
			    url: exportUrl,
			    method: 'GET',
			    responseType: 'arraybuffer'
			}).success(function(data){
			    var blob = new Blob([data], {
			        type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
			    });
			    var objectUrl = URL.createObjectURL(blob);
			    window.open(objectUrl);
			}).error(function(){
			    // Some error log
			});
		};
	
		// Import Pop-up
		vm.showImportWindow = function(index) {
			var modalInstance = $uibModal.open({
				templateUrl : '/RestTest/html/home/importModal.html',
				size : 'lg',
				scope:$scope,
				controller : 'ImportModalCtrl',
				resolve: {
					vm: function() {
						return vm;
					}
				}
			});

			modalInstance.result.then(function() {
				
			}, function() {
			});

		};
	}]);
	
	// addQaForm - form name
	// addForm - form model nested values
	myApp.controller(
					'ModalInstanceCtrl', ['$scope','$uibModalInstance','$http','vm',
					function($scope, $uibModalInstance, $http, vm) {
						console.log($scope);
						if(typeof vm.addForm == 'undefined') {
							vm.addForm = {};
						}
						
						vm.add = function() {
							angular.forEach(vm.addQaForm.$error.required, function(field) {
							    field.$setDirty();
							});
							
							if(vm.addQaForm.$valid) {
								vm.addForm.showMessage = false;
								var addQaUrl = "http://localhost:8080/RestTest/webapi/qaInfo/saveQaInfo";
								var dataObj = {
									question : vm.addForm.question,
									answer : vm.addForm.answer,
									type : vm.addForm.type
								};
								var res = $http.post(addQaUrl, dataObj);
	
								res.success(function(data, status, headers,
												config) {
											if (typeof data != 'undefined'
													&& data.type == 'success') {
												vm.addForm.resultFlag = true;
												vm.addForm.message = "QA Info has been saved successfully and its ID is : "
														+ data.qaInfos[0].qaId;
											}
										});
	
								res.error(function(data, status, headers, config) {
									alert("Failed");
								});
							} else {
								vm.addForm.showMessage = true;
							}
						};

						vm.closeWindow = function() {
							$uibModalInstance.dismiss('cancel');
							vm.addForm = {};
							vm.submit();
						};
					}]);
	
	// updQaForm - form name
	// updForm - form model nested values
	myApp.controller(
			'UpdateModalCtrl', ['$scope','$uibModalInstance','$http','qaInfo','vm',
			function($scope, $uibModalInstance, $http, qaInfo, vm) {
				$uibModalInstance.opened.then(function(){
					vm.updForm = {};
					vm.updForm.qaId = qaInfo.qaId;
					vm.updForm.question = qaInfo.question;
					vm.updForm.answer = qaInfo.answer;
					vm.updForm.type = qaInfo.type;
				}, function(){
					console.log("Modal Dismissed!");
				});				
				
				vm.update = function() {
					var updateQaUrl = "http://localhost:8080/RestTest/webapi/qaInfo/updateQaInfo";
					var dataObj = {
						qaId: vm.updForm.qaId,
						question : vm.updForm.question,
						answer : vm.updForm.answer,
						type : vm.updForm.type
					};
					
					var res = $http.put(updateQaUrl, dataObj);

					res.success(function(data, status, headers,
									config) {
								if (typeof data != 'undefined'
										&& data.type == 'success') {
									vm.updForm.resultFlag = true;
									vm.updForm.message = "QA Info has been saved successfully and its ID is : "
											+ data.qaInfos[0].qaId;
								}
							});

					res.error(function(data, status, headers, config) {
						alert("Failed");
					});
				};

				vm.updateCloseWindow = function() {
					$uibModalInstance.dismiss('cancel');
					vm.updForm = {};
					vm.submit();
				};
			}]);
	
	myApp.controller(
			'DeleteModalCtrl',['$scope','$uibModalInstance','$http','qaId','vm',
			function($scope, $uibModalInstance, $http, qaId, vm) {
				vm.deleteQa = function() {
					var updateQaUrl = "http://localhost:8080/RestTest/webapi/qaInfo/"+qaId;
					
					var res = $http.delete(updateQaUrl);

					res.success(function(data, status, headers,
									config) {
								if (typeof data != 'undefined'
										&& data.type == 'success') {
									vm.delete = {};
									vm.delete.resultFlag = true;
									vm.delete.message="Record has been deleted successfully";
								}
							});

					res.error(function(data, status, headers, config) {
						alert("Failed");
					});
				};

				vm.deleteCloseWindow = function() {
					$uibModalInstance.dismiss('cancel');
					vm.delete = {};
					vm.delete.resultFlag = false;
					vm.submit();
				};
			}]);
	// importQaForm - form name
	// importForm - form model nested values
	myApp.controller(
			'ImportModalCtrl',['$scope','$uibModalInstance','$http', 'fileUpload', 'vm',
			function($scope, $uibModalInstance, $http, fileUpload, vm) {
				if(typeof vm.importForm === 'undefined') {
					vm.importForm = {};
				}
				vm.importQa = function() {
					var importQaUrl = "http://localhost:8080/RestTest/webapi/qaInfo/import";
					var file = vm.myFile;
					
					if(typeof file === 'undefined') {
						vm.importForm.showMessage = true;
					} else {
						vm.importForm.showMessage = false;
						fileUpload.uploadFileToUrl(file, importQaUrl, vm);
					}
		        };

				vm.importCloseWindow = function() {
					$uibModalInstance.dismiss('cancel');
					vm.importForm = {};
					vm.importForm.resultFlag = false;
					vm.submit();
				};
			}]);
}())