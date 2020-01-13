(function() {
    'use strict';

    angular
        .module('foodyApp')
        .controller('TranzactieDeleteController',TranzactieDeleteController);

    TranzactieDeleteController.$inject = ['$uibModalInstance', 'entity', 'Tranzactie'];

    function TranzactieDeleteController($uibModalInstance, entity, Tranzactie) {
        var vm = this;

        vm.tranzactie = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Tranzactie.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
