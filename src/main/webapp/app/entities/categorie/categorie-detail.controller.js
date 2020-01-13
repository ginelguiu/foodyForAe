(function() {
    'use strict';

    angular
        .module('foodyApp')
        .controller('CategorieDetailController', CategorieDetailController);

    CategorieDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Categorie', 'Review', '$http'];

    function CategorieDetailController($scope, $rootScope, $stateParams, previousState, entity, Categorie, Review, $http) {
        var vm = this;

        vm.categorie = entity;
        vm.previousState = previousState.name;

        vm.optiuneSortare = null;

        var unsubscribe = $rootScope.$on('foodyApp:categorieUpdate', function(event, result) {
            vm.categorie = result;
        });
        $scope.$on('$destroy', unsubscribe);

        vm.reviews = [];
        vm.filteredReviews = [];
        vm.getReviews=getReviews;
        vm.currentPage=1;
        vm.numPerPage=10;
        vm.maxSize=5;



        $scope.$watch('currentPage + numPerPage', function() {
            var begin = ((vm.currentPage - 1) * vm.numPerPage)
                , end = begin + vm.numPerPage;

            vm.filteredReviews = vm.reviews.slice(begin, end);
        });

        getReviews();
        function getReviews(){
            $http({method: 'GET', url: 'http://localhost:8080/api/reviews/categorie/'+vm.categorie.id}).
            then(function(response) {
                $scope.status = response.status;
                vm.reviews = response.data;
            }, function(response) {
                vm.reviews = response.data || 'Request failed';
                $scope.status = response.status;
            });
        }
    }
})();
