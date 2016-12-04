var app = angular.module('music-store', [ 'ui.bootstrap', 'ngMaterial' ]);

app.controller('artist', function($scope, $http, $mdDialog, $mdToast) {

	$scope.totalItems = 0;
	$scope.currentPage = 1;
	$scope.pageSize = 0;

	$scope.searchTopArtists = function() {
		getArtists($scope.currentPage);
	};

	$scope.getTopTracks = function(artist) {
		$http({
			method : 'GET',
			url : '/artists/' + artist.id + '/top-tracks'
		}).success(function(data, status, headers, config) {
			$scope.tracks = data;
			$scope.artist = artist;
			showDialog('/pages/artist/tracks.html');
		}).error(function(data, status, headers, config) {
			showError(data, status);
		});
	}
	
	$scope.$watch('artists', function(newValue, oldValue) {
		if (newValue) {
			$scope.totalItems = newValue.totalElements;
			$scope.currentPage = newValue.currentPage;
			$scope.pageSize = newValue.pageSize;
		}
	});

	$scope.pageChanged = function pageChanged(currentPage) {
		getArtists(currentPage);
	};

	var showError = function(data, status) {
		var msg = 'Error';
		if (data && data.message) {
			msg = data.message;
		}
		$mdToast.show($mdToast.simple()
				.textContent(msg)
				.position('top right')
				.hideDelay(3000)
				.highlightAction(true)
				.highlightClass('md-warn'));
	}
	
	var getArtists = function(currentPage) {
		$http({
			method : 'GET',
			url : '/artists/top/' + $scope.country,
			params : {
				page : currentPage
			}
		}).success(function(data, status, headers, config) {
			$scope.artists = {};
			$scope.artists = data;
			$scope.templateURL = '/pages/artist/top-by-country.html';
		}).error(function(data, status, headers, config) {
			showError(data, status);
		});
	}

	var showDialog = function(template) {
		$mdDialog.show({
			controller : DialogController,
			templateUrl : template,
			scope : $scope.$new(),
			parent : angular.element(document.body),
			//targetEvent : ev,
			clickOutsideToClose : true
		})
	};

	function DialogController($scope, $mdDialog) {
		$scope.hide = function() {
			$mdDialog.hide();
		};

	}

});