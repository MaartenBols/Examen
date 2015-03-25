'use strict';

angular.module('ProductApp.services', []).

    factory('ProductService', ['$http',
        function($http) {
            var restUrl = 'http://localhost:8080/Examen2503-JAX-RS/products';

            return {
                getProductsJSON: function() {
                	$http.defaults.headers.common.Accept = 'application/json'
                	
                	return $http.get(restUrl).
                		success (function(data) {
                			return data;
                		}).
                	error(function(data) {
                		return data;
                	});
                },
            
            
            getProductJSON: function(name) {
            	$http.defaults.headers.common.Accept = 'application/json'
            	
            	return $http.get(restUrl + "/" + name).
            		success (function(data) {
            			return data;
            		}).
            	error(function(data) {
            		return data;
            	});
            },
            
            addProduct: function(jsonString){
            	
            	$http({
            		method: 'POST',
            		url: restUrl,
            		data: jsonString,
            		headers: {
            			'Content-Type': 'application/json'
            		}}).then(function(result) {
            			alert('success');
            		}, function(error){
            			alrt('error');
            		});
            }
            		
        };
        }
    ]);