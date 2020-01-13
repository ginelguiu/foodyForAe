(function() {
    'use strict';

    angular
        .module('foodyApp')
        .controller('ComentariuDeleteController',ComentariuDeleteController);

    ComentariuDeleteController.$inject = ['$uibModalInstance', 'entity', 'Comentariu'];

    function ComentariuDeleteController($uibModalInstance, entity, Comentariu) {
        var vm = this;

        vm.comentariu = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Comentariu.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
