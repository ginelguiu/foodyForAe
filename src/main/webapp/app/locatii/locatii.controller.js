(function () {
    'use strict';

    angular
        .module('foodyApp')
        .controller('LocatiiController', LocatiiController);

    LocatiiController.$inject = ['$scope', 'LocatiiService', 'Review'];

    function LocatiiController($scope, LocatiiService, Review) {
        var vm = this;

        vm.loadAllReviews = loadAllReviews;
        vm.reviews = [];
        vm.locations = [];


        function loadAllReviews() {
            Review.query({

            }, onSuccess, onError);
            function onSuccess(data) {
                vm.reviews = data;
                vm.reviewsLength = vm.reviews.length;
                for(var i =0;i<vm.reviewsLength;i++){
                    vm.locations.push({nume: vm.reviews[i].coordonateLocatie, nrLikeuri:0, rating:[0, 0, 0, 0, 0], nrReviewuri:0});
                    console.log(vm.locations);
                }
                vm.locations = _.uniq(vm.locations, false, 'nume');
                vm.locationsLength = vm.locations.length;
                for(i=0;i<vm.locationsLength;i++){
                    for(var j=0;j<vm.reviewsLength;j++){
                        if(vm.locations[i].nume===vm.reviews[j].coordonateLocatie && vm.reviews[j].moderat === true){
                            vm.locations[i].nrLikeuri+= vm.reviews[j].numarLikeuri;
                            vm.locations[i].rating[vm.reviews[j].numarStele-1]++;
                            vm.locations[i].nrReviewuri++;
                        }
                    }
                }
                console.log(vm.locations);
            }
            function onError(error) {
                AlertService.error(error.data.message);
            }
        }
        vm.buildingLocations = buildingLocations;


        vm.testLocatii = testLocatii;

        testLocatii();
        function testLocatii() {
            loadAllReviews();
            console.log(vm.reviews.length);
            for (var i = 0; i<vm.reviews.length;i++) {
                console.log("suntem in for");
                vm.locations.push(vm.reviews[i].coordonateLocatie);
            }
        }

        function buildingLocations(){
            for(var review in vm.reviews){
                if(vm.locations.includes(review.coordonateLocatie)){
                    vm.locations.find(function(element){
                        element.rating[review.numarStele-1]++;
                        element.nrLikeuri++;
                    })
                }
                else {
                    vm.locations.push({nume: review.coordonateLocatie, rating: [0, 0, 0, 0, 0], nrLikeuri: 0});
                }
            }
        }
    }
})();
