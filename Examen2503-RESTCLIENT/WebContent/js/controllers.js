'use strict';

angular.module('ProductApp.controllers', []).

    controller('ProductsController', ['$scope', 'ProductService',
      function($scope, ProductService)  {
    	$scope.products = [];
    	
    	ProductService.getProductsJSON().success(function(response) {
    		$scope.products = response.products;
    		});
    	}
    
    ]).
    
    controller('ProductController', ['$scope', '$routeParams', 'ProductService',
        function($scope, $routeParams, ProductService)  {
    	$scope.product = null;
    	var shortname = $routeParams.shortname;
    	
    	ProductService.getProductJSON(shortname).success(function(response) {
    		$scope.product = response;
    		});
    	}
        
    ]).
    
    controller('NewProductController', ['$scope', 'ProductService',
       function($scope, ProductService) {
    	$scope.addProduct = function (){
    	var jsonString =  "{\"products\" : [";
    	jsonString += "{\"name\" : \"" + $scope.newProduct.name + "\",";
    	jsonString += "\"description\" : \"" + $scope.newProduct.description + "\",";
    	jsonString += "\"id\" : " + $scope.newProduct.id + ",";
    	jsonString += "\"price\" : " + $scope.newProduct.price + "},";
    	jsonString += "]}";
  
    	ProductService.addProduct(jsonString); 
    	};
    	}
    ]);
