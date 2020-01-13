(function() {
    'use strict';

    angular
        .module('foodyApp')
        .controller('ReviewDetailController', ReviewDetailController);

    ReviewDetailController.$inject = ['$scope', 'Auth', 'Principal','$rootScope', '$window','$stateParams', 'previousState', 'entity', 'Review', 'User', 'Comentariu', '$http'];

    function ReviewDetailController($scope, Auth, Principal, $rootScope, $window, $stateParams, previousState, entity, Review, User, Comentariu, $http) {
        var vm = this;

        vm.isAuthenticated = Principal.isAuthenticated;

        vm.getNumber = getNumber;
        vm.cresteLike = cresteLike;
        vm.cresteDislike = cresteDislike;
        vm.comentarii = [];
        vm.users = [];
        vm.categories = [];
        vm.cartProducts = [];

        vm.addToCart = addToCart;

        function addToCart(id) {
            if(localStorage.getItem('products')!=null)
                vm.cartProducts = localStorage.getItem('products').split(',');
            vm.cartProducts.push(id);
            localStorage.setItem('products', vm.cartProducts);
            window.alert('Produsul a fost adaugat in cosul de cumparaturi');
        }

        function cresteLike(){
            vm.review.numarLikeuri++;
            $scope.buttonClicked = true;
            Review.update(vm.review, function () {
            });
        }

        function cresteDislike() {
            vm.review.numarDislikeuri++;
            $scope.buttonClicked = true;
            Review.update(vm.review, function () {
            });
        }

        function getNumber(num){
            return new Array(num);
        }



        vm.review = entity;
        vm.getComments = getComments;
        vm.comentariuNou = null;
        vm.saveComment = saveComment;

        function saveComment() {
            vm.comentariuNou.reviewId = vm.review.id;
            vm.comentariuNou.userId = null;
            vm.comentariuNou.dataComentariu = null;
            Comentariu.save(vm.comentariuNou, onSaveSuccess, onSaveError);
        }
        function onSaveSuccess(result){
            getComments();
        }
        function onSaveError() {
            $window.alert('Comentariul nu a putut fi postat. Va rugam incercati mai tarziu');
        }
        getComments();
        function getComments(){
            $http({method: 'GET', url: 'http://localhost:8080/api/comentarii/review/'+vm.review.id}).
            then(function(response) {
                $scope.status = response.status;
                vm.comentarii = response.data;
            }, function(response) {
                vm.comentarii = response.data || 'Request failed';
                $scope.status = response.status;
            });
        }


        vm.previousState = previousState.name;
        var unsubscribe = $rootScope.$on('foodyApp:reviewUpdate', function(event, result) {
            vm.review = result;
        });
        $scope.$on('$destroy', unsubscribe);
        vm.user = null;
        vm.getUser=getUser;


        function getUser(id){
            $http({method:'GET', url: 'http://localhost:8080/api/user/'+id}).
                then(function(response){
                    $scope.status = response.status;
                    vm.users.push(response.data);
            }, function(response){
                $scope.status = response.status;
                vm.user = response.data || 'Request failed';
            });
        }

        vm.categorie = null;
        vm.getCategorie = getCategorie;

        function getCategorie(id){
            $http({method:'GET', url: 'http://localhost:8080/api/categorii/'+id}).
            then(function(response){
                $scope.status = response.status;
                vm.categories.push(response.data);
            }, function(response){
                $scope.status = response.status;
                vm.categorie = response.data || 'Request failed';
            });
        }

        $scope.$back = function() {
            window.history.back();
        }
    }
})();
