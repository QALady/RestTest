var app = angular.module('myapp');

app.filter('ceil', function(){
	return function(input){
		return Math.ceil(input);
	};
});

app.filter('floor', function(){
	return function(input){
		return Math.floor(input);
	};
});

app.filter('startFrom', function() {
    return function(input, start) {
        start = +start; // parse to int
        if(typeof input == 'undefined') {
        	return 0;
        }
        return input.slice(start);
    };
});