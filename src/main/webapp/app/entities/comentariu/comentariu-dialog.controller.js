(function() {
    'use strict';

    angular
        .module('foodyApp')
        .controller('ComentariuDialogController', ComentariuDialogController);

    ComentariuDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Comentariu', 'Review', 'User'];

    function ComentariuDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Comentariu, Review, User) {
        var vm = this;

        vm.comentariu = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.reviews = Review.query();
        vm.users = User.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.comentariu.id !== null) {
                Comentariu.update(vm.comentariu, onSaveSuccess, onSaveError);
            } else {
                Comentariu.save(vm.comentariu, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('foodyApp:comentariuUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.dataComentariu = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
