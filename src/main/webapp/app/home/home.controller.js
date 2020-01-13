(function() {
    'use strict';

    angular
        .module('foodyApp')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$scope', 'Principal', 'LoginService', '$state', 'Review', '$filter'];

    function HomeController ($scope, Principal, LoginService, $state, Review, $filter) {
        var vm = this;

        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.reviews = [];
        vm.register = register;
        $scope.$on('authenticationSuccess', function() {
            getAccount();
        });

        getAccount();
        loadAll();
        function loadAll () {
            Review.query({

            }, onSuccess, onError);
            function onSuccess(data) {
                vm.reviews = data;
            }
            function onError(error) {
                AlertService.error(error.data.message);
            }
        }

        function getAccount() {
            Principal.identity().then(function(account) {
                vm.account = account;
                vm.isAuthenticated = Principal.isAuthenticated;
            });
        }
        function register () {
            $state.go('register');
        }

        vm.reviewuriDeTop=reviewuriDeTop;
        vm.newReviews = [];

        reviewuriDeTop();
        function reviewuriDeTop(){
            vm.reviews=$filter('orderBy')(vm.reviews,'numarLikeuri', true);
            vm.i=0;
            for(; vm.i<6;vm.i++){
                vm.newReviews.push(vm.reviews[vm.i]);
            }
        }
    }
})();
