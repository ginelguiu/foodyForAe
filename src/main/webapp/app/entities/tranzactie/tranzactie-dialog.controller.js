(function() {
    'use strict';

    angular
        .module('foodyApp')
        .controller('TranzactieDialogController', TranzactieDialogController);

    TranzactieDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Tranzactie', '$http'];

    function TranzactieDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Tranzactie, $http) {
        var vm = this;

        vm.tranzactie = entity;
        vm.clear = clear;
        vm.save = save;
        vm.produse = [];
        vm.listaProduse = [];
        vm.categorie = null;
        vm.pretTotal = 0;


        populareProduse();

        vm.populareProduse = populareProduse;
        

        function populareProduse(){
            if(localStorage.getItem('products')){
                vm.produse = localStorage.getItem('products').split(',');
                for(let i = 0; i<vm.produse.length; i++){
                    $http({method:'GET', url: 'http://localhost:8080/api/reviews/'+vm.produse[i]}).
                    then(function(response){
                        $scope.status = response.status;
                        if(vm.tranzactie.continut == null)
                            vm.tranzactie.continut =response.data.titlu;
                        else
                            vm.tranzactie.continut = vm.tranzactie.continut + ", " + response.data.titlu;
                        vm.listaProduse.push(response.data.titlu);
                        getCategorie(response.data.categorieId);
                        console.log(vm.tranzactie);
                    });
                }
            }

        }

        function getCategorie(id){
            $http({method:'GET', url: 'http://localhost:8080/api/categorii/'+id}).
            then(function(response){
                $scope.status = response.status;
                vm.pretTotal = vm.pretTotal + response.data.pret;
            });
        }
        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            localStorage.clear();
            vm.tranzactie.valoare = vm.pretTotal;
            vm.pretTotal = 0;
            vm.isSaving = true;
            if (vm.tranzactie.id !== null) {
                Tranzactie.update(vm.tranzactie, onSaveSuccess, onSaveError);
            } else {
                Tranzactie.save(vm.tranzactie, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('foodyApp:tranzactieUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
