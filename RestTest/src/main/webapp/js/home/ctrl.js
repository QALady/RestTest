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

	
	myApp.controller('RegCtrl', ['$scope', 'qaService', '$uibModal', '$localStorage', '$window', 
	                             function($scope, qaService, $uibModal, $localStorage, $window) {
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
			qaService.logout().then(function(response){
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
			vm.searchShowMessage= false;
			vm.resultFlag = false;
			vm.loadingFlag = true;
			
			if(typeof vm.searchForm == 'undefined') {
				vm.searchForm = {};
			}
			
			if (vm.searchForm.searchValue == '' || typeof vm.searchForm.searchValue == 'undefined') {
				var res = qaService.getAllQaInfo();
				res.then(function(response) {
					vm.qaInfos = response.qaInfos;
					vm.resultFlag = true;
					vm.loadingFlag = false;
				});
			} else {
				angular.forEach(vm.searchFm.$error.required, function(field) {
				    field.$setDirty();
				});
				
				if(vm.searchFm.$valid) {
					qaService.getQaInfo(vm.searchForm).then(function(response) {
						vm.currentPage = 0;
						vm.qaInfos = response.qaInfos;
						vm.resultFlag = true;
					});
				} else {
					vm.searchShowMessage= true;
				}
				vm.loadingFlag = false;
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
		vm.showDeleteWindow = function(qaId) {
			var modalInstance = $uibModal.open({
				templateUrl : '/RestTest/html/home/deleteModal.html',
				size : 'lg',
				scope:$scope,
				controller : 'DeleteModalCtrl',
				resolve: {
					qaId: function(){
						return qaId;
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
			qaService.exportQaInfo(fileType).then(function(data){
			    var blob = new Blob([data], {
			        type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
			    });
			    var objectUrl = URL.createObjectURL(blob);
			    window.open(objectUrl);
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
			}, function() {});
		};
		
		vm.showImagesWindow = function(qaId) {
			var modalInstance = $uibModal.open({
				templateUrl : '/RestTest/html/home/imagesModal.html',
				size : 'lg',
				scope:$scope,
				controller : 'ImagesModalCtrl',
				resolve: {
					vm: function() {
						return vm;
					},
					qaId: function() {
						return qaId;
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
					'ModalInstanceCtrl', ['$scope','$uibModalInstance','qaService','uploadQaImage', 'vm',
					function($scope, $uibModalInstance, qaService, uploadQaImage, vm) {
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
								qaService.addQaInfo(vm.addForm).then(function(data) {
									if (typeof data != 'undefined'
											&& data.type == 'success') {
										//vm.addForm.resultFlag = true;
										vm.addForm.qaId = data.qaInfos[0].qaId;
										vm.addForm.message = "QA Info has been saved successfully and its ID is : "
												+ data.qaInfos[0].qaId;
										uploadQaImage.uploadQaImageToUrl(vm.file1, vm);
									}
								});
							} else {
								vm.addForm.showMessage = true;
							}
						};

						vm.closeWindow = function() {
							$uibModalInstance.dismiss('cancel');
							vm.addForm = {};
							vm.file1 = '';
							vm.submit();
						};
					}]);
	
	// updQaForm - form name
	// updForm - form model nested values
	myApp.controller(
			'UpdateModalCtrl', ['$scope','$uibModalInstance','qaInfo','updUploadQaImage', 'vm', 'qaService',
			function($scope, $uibModalInstance, qaInfo, updUploadQaImage, vm, qaService) {
				if(typeof vm.updForm === 'undefined') {
					vm.updForm = {};
				}
				vm.updForm.resultFlag = false;
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
					var uploadQaUrl = "http://localhost:8080/RestTest/webapi/qaInfo/uploadQaImage";
					if(typeof vm.updForm === 'undefined') {
						vm.updForm = {};
					}
					vm.updForm.resultFlag = false;
					
					qaService.updQaInfo(vm.updForm).then(function(data) {
						if (typeof data != 'undefined'
								&& data.type == 'success') {
							
							vm.updForm.resultFlag = true;
							vm.updForm.message = "QA Info has been saved successfully and its ID is : "
									+ data.qaInfos[0].qaId;
							updUploadQaImage.uploadQaImageToUrl(vm.updForm.file1, vm);
						}
					});
				};

				vm.updateCloseWindow = function() {
					$uibModalInstance.dismiss('cancel');
					vm.updForm = {};
					vm.submit();
				};
			}]);
	
	myApp.controller(
			'DeleteModalCtrl',['$scope','$uibModalInstance','qaId','vm', 'qaService',
			function($scope, $uibModalInstance, qaId, vm, qaService) {
				vm.deleteQa = function() {
					qaService.delQaInfo(qaId).then(function(data) {
						if (typeof data != 'undefined'
								&& data.type == 'success') {
							vm.delete = {};
							vm.delete.resultFlag = true;
							vm.delete.message="Record has been deleted successfully";
						};
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
			'ImportModalCtrl',['$scope','$uibModalInstance', 'fileUpload', 'vm',
			function($scope, $uibModalInstance, fileUpload, vm) {
				if(typeof vm.importForm === 'undefined') {
					vm.importForm = {};
				}
				vm.importQa = function() {
					var file = vm.myFile;
					if(typeof file === 'undefined') {
						vm.importForm.showMessage = true;
					} else {
						vm.importForm.showMessage = false;
						fileUpload.uploadFileToUrl(file, vm);
					};
		        };

				vm.importCloseWindow = function() {
					$uibModalInstance.dismiss('cancel');
					vm.importForm = {};
					vm.importForm.resultFlag = false;
					vm.submit();
				};
			}]);
	
	myApp.controller(
			'ImagesModalCtrl',['$scope','$uibModalInstance', 'vm', 'qaId', 'qaService',
			function($scope, $uibModalInstance, vm, qaId, qaService) {
				if(typeof vm.imgDelForm === 'undefined') {
					vm.imgDelForm = {};
				}
				vm.imgDelForm.resultFlag = false;
				
				vm.imageCloseWindow = function() {
					$uibModalInstance.dismiss('cancel');
					vm.slides = {};
				};
				
				vm.deleteImage = function(imageObj) {
					if(typeof vm.imgDelForm === 'undefined') {
						vm.imgDelForm = {};
					}	
					vm.imgDelForm.resultFlag = false;
					qaService.delQaImage(imageObj).then(function(data) {
						if (typeof data != 'undefined'
								&& data.type == 'success') {
							vm.getImages();
							vm.imgDelForm.resultFlag = true;
							vm.imgDelForm.message = "Image has been deleted successfully.";
						}
					});
				};
				
				vm.getImages = function() {
					qaService.getQaImages(qaId).then(function(response) {
						vm.slides = response.infoDtos;
					});
				};
				
				vm.getImages();
				
			}]);
}());