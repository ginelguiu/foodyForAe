(function() {
    'use strict';

    angular
        .module('foodyApp')
        .controller('ReviewController', ReviewController);

    ReviewController.$inject = ['$state', '$scope', 'Review', 'ParseLinks', 'AlertService', 'paginationConstants', 'pagingParams', 'Categorie'];

    function ReviewController($state, $scope, Review, ParseLinks, AlertService, paginationConstants, pagingParams, Categorie) {

        var vm = this;

        vm.loadPage = loadPage;
        vm.predicate = pagingParams.predicate;
        vm.reverse = pagingParams.ascending;
        vm.transition = transition;
        vm.itemsPerPage = paginationConstants.itemsPerPage;
        vm.categorii = [];
        vm.getNumeCategorie = getNumeCategorie;
        loadAll();

        $scope.range = function(count){
            return 20*count;
        };

        function getNumeCategorie(id){
            vm.categorii.push(Categorie.get({id : id}));
        }

        function loadAll () {
            Review.query({
                page: pagingParams.page - 1,
                size: vm.itemsPerPage,
                sort: sort()
            }, onSuccess, onError);
            function sort() {
                var result = [vm.predicate + ',' + (vm.reverse ? 'asc' : 'desc')];
                if (vm.predicate !== 'id') {
                    result.push('id');
                }
                return result;
            }
            function onSuccess(data, headers) {
                vm.links = ParseLinks.parse(headers('link'));
                vm.totalItems = headers('X-Total-Count');
                vm.queryCount = vm.totalItems;
                vm.reviews = data;
                vm.page = pagingParams.page;
            }
            function onError(error) {
                AlertService.error(error.data.message);
            }
        }

        function loadPage(page) {
            vm.page = page;
            vm.transition();
        }

        function transition() {
            $state.transitionTo($state.$current, {
                page: vm.page,
                sort: vm.predicate + ',' + (vm.reverse ? 'asc' : 'desc'),
                search: vm.currentSearch
            });
        }
    }
})();
