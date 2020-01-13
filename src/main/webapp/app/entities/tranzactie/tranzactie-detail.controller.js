(function() {
    'use strict';

    angular
        .module('foodyApp')
        .controller('TranzactieDetailController', TranzactieDetailController);

    TranzactieDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Tranzactie'];

    function TranzactieDetailController($scope, $rootScope, $stateParams, previousState, entity, Tranzactie) {
        var vm = this;

        vm.tranzactie = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('foodyApp:tranzactieUpdate', function(event, result) {
            vm.tranzactie = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
