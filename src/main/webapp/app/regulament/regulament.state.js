(function() {
    'use strict';

    angular
        .module('foodyApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('regulament', {
            parent: 'app',
            url: '/regulament',
            data: {
                authorities: [],
                pageTitle: 'Regulament'
            },
            views: {
                'content@': {
                    templateUrl: 'app/regulament/regulament.html',
                    controller: 'RegulamentController',
                    controllerAs: 'vm'
                }
            }
        });
    }
})();
