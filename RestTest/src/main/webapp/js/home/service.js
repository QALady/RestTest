(function() {
	var myApp = angular.module('myapp');

	myApp.service('fileUpload', [ '$http', function($http) {
		this.uploadFileToUrl = function(file, vm) {
			var importQaUrl = "http://localhost:8080/RestTest/webapi/qaInfo/import";
			var fd = new FormData();
			fd.append('file', file);

			if (typeof file != 'undefined') {
				$http.post(importQaUrl, fd, {
					transformRequest : angular.identity,
					headers : {
						'Content-Type' : undefined
					}
				})

				.success(function(response) {
					if (typeof vm.importForm == 'undefined') {
						vm.importForm = {};
					}
					vm.importForm.message = response;
					vm.importForm.resultFlag = true;
				})

				.error(function() {
				});
			} else {
				vm.importForm.resultFlag = true;
			}
		};
	} ]);

	myApp.service('uploadQaImage', [ '$http', function($http) {
		this.uploadQaImageToUrl = function(file, vm) {
			var uploadQaUrl = "http://localhost:8080/RestTest/webapi/qaInfo/uploadQaImage";
			var fd = new FormData();
			fd.append('file', file);
			fd.append('qaId', vm.addForm.qaId);

			if (typeof file != 'undefined') {
				$http.post(uploadQaUrl, fd, {
					transformRequest : angular.identity,
					headers : {
						'Content-Type' : undefined
					}
				}).success(function(response) {
					if (typeof vm.addForm == 'undefined') {
						vm.addForm = {};
					}
					vm.addForm.resultFlag = true;
				})
				.error(function() {
				});
			} else {
				vm.addForm.resultFlag = true;
			}
		};
	} ]);

	myApp.service('updUploadQaImage', [ '$http', function($http) {
		this.uploadQaImageToUrl = function(file, vm) {
			var uploadQaUrl = "http://localhost:8080/RestTest/webapi/qaInfo/uploadQaImage";
			var fd = new FormData();
			fd.append('file', file);
			fd.append('qaId', vm.updForm.qaId);

			if (typeof file != 'undefined') {
				$http.post(uploadQaUrl, fd, {
					transformRequest : angular.identity,
					headers : {
						'Content-Type' : undefined
					}
				})
				.success(function(response) {
					if (typeof vm.addForm == 'undefined') {
						vm.addForm = {};
					}
					vm.updForm.resultFlag = true;
				})
				.error(function() {
				});
			} else {
				vm.updForm.resultFlag = true;
			}
		};
	} ]);
	
	var qaService = function($http, $window) {
		var getQaInfo = function(searchForm){
			var searchQaUrl = "http://localhost:8080/RestTest/webapi/qaInfo/searchInfo";
			var dataObj = {
					searchOption : searchForm.searchOption.key,
					operator : searchForm.searchOperator.key,
					searchValue : searchForm.searchValue
			};
			
			return $http.post(searchQaUrl, dataObj).then(function(response) {
				return response.data;
			});
		};
		
		var getAllQaInfo = function() {
			var searchQaUrl = "http://localhost:8080/RestTest/webapi/qaInfo/qaList";
			return $http.get(searchQaUrl).then(function(response) {
				return response.data;
			});
		};
		
		var addQaInfo = function(addForm) {
			var addQaUrl = "http://localhost:8080/RestTest/webapi/qaInfo/saveQaInfo";
			var dataObj = {
					question : addForm.question,
					answer : addForm.answer,
					type : addForm.type
				};
			var res = $http.post(addQaUrl, dataObj);
			return res.then(function(response) {
				return response.data;
			});
		};
		
		var updQaInfo = function(updForm) {
			var updateQaUrl = "http://localhost:8080/RestTest/webapi/qaInfo/updateQaInfo";
			var dataObj = {
					qaId: updForm.qaId,
					question : updForm.question,
					answer : updForm.answer,
					type : updForm.type
			};
				
			var res = $http.put(updateQaUrl, dataObj);
			return res.then(function(response) {
				return response.data;
			});
		};
		
		var delQaInfo = function(qaId) {
			var delQaUrl = "http://localhost:8080/RestTest/webapi/qaInfo/"+qaId;
			var res = $http.delete(delQaUrl);
			return res.then(function(response) {
				return response.data;
			});
		};
		
		var delQaImage = function(imageObj) {
			var deleteImageUrl = "http://localhost:8080/RestTest/webapi/qaInfo/deleteQaImage";
			var dataObj = {
				qaId: imageObj.qaId,
				imageId : imageObj.imageId,
				imageType : imageObj.imageType,
				imagePath : imageObj.imagePath
			};
			
			var res = $http.post(deleteImageUrl, dataObj);

			return res.then(function(response) {
				return response.data;
			});
		};
		
		var getQaImages = function(qaId) {
			var imageUrl = 'http://localhost:8080/RestTest/webapi/qaInfo/findQaImage/'+qaId;
			
			return $http.get(imageUrl).then(function(response) {
				return response.data;
			});
		};
		
		var exportQaInfo = function(fileType) {
			var extension = '';
			var mimetype='';
			var exportUrl='http://localhost:8080/RestTest/webapi/qaInfo/exportQaInfo/';
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
			
			return $http({
			    url: exportUrl,
			    method: 'GET',
			    responseType: 'arraybuffer'}).then(function(response){
				return response.data;
			});
		};
		
		var logout = function() {
			var logoutUrl = "http://localhost:8080/RestTest/webapi/login/logout";
			var dataObj = {
					loginId : $window.localStorage.getItem('loginId')
			};
			return $http.post(logoutUrl, dataObj).then(function(response) {
				return response.data;
			});
		};
		
		return {
			getQaInfo : getQaInfo,
			getAllQaInfo : getAllQaInfo,
			addQaInfo : addQaInfo,
			updQaInfo : updQaInfo,
			delQaInfo : delQaInfo,
			delQaImage : delQaImage,
			getQaImages : getQaImages,
			exportQaInfo : exportQaInfo,
			logout : logout
		};
	};

	myApp.factory('qaService', qaService);
	
}());