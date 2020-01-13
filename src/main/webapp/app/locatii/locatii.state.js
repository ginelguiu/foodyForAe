(function() {
    'use strict';

    angular
        .module('foodyApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('locatii', {
            parent: 'app',
            url: '/locatii',
            data: {
                authorities: [],
                pageTitle: 'Locatii'
            },
            views: {
                'content@': {
                    templateUrl: 'app/locatii/locatii.html',
                    controller: 'LocatiiController',
                    controllerAs: 'vm'
                }
            }
        });
    }
})();
