(function() {
    'use strict';

    angular
        .module('foodyApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('cart', {
            parent: 'app',
            url: '/cart',
            data: {
                authorities: [],
                pageTitle: 'global.menu.cart'
            },
            views: {
                'content@': {
                    templateUrl: 'app/cart/cart.html',
                    controller: 'CartController',
                    controllerAs: 'vm'
                }
            }
        });
    }
})();
