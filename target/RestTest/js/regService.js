(function() {
var myApp = angular.module('myapp');

myApp.service('fileUpload', ['$http', function ($http) {
    this.uploadFileToUrl = function(file, uploadUrl, vm){
       var fd = new FormData();
       fd.append('file', file);
    
       $http.post(uploadUrl, fd, {
          transformRequest: angular.identity,
          headers: {'Content-Type': undefined}
       })
    
       .success(function(response){
    	   if(typeof vm.importForm == 'undefined') {
    		   vm.importForm = {};
    	   }
    	   vm.importForm.message = response;
    	   vm.importForm.resultFlag = true;
       })
    
       .error(function(){
       });
    }
 }]);

}())