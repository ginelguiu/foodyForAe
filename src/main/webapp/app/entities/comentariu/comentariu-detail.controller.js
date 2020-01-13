(function() {
    'use strict';

    angular
        .module('foodyApp')
        .controller('ComentariuDetailController', ComentariuDetailController);

    ComentariuDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Comentariu', 'Review', 'User'];

    function ComentariuDetailController($scope, $rootScope, $stateParams, previousState, entity, Comentariu, Review, User) {
        var vm = this;

        vm.comentariu = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('foodyApp:comentariuUpdate', function(event, result) {
            vm.comentariu = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
