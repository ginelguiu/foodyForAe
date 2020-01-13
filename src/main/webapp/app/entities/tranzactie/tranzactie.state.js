(function() {
    'use strict';

    angular
        .module('foodyApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('tranzactie', {
            parent: 'entity',
            url: '/tranzactie?page&sort&search',
            data: {
                authorities: [],
                pageTitle: 'Tranzacties'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/tranzactie/tranzacties.html',
                    controller: 'TranzactieController',
                    controllerAs: 'vm'
                }
            },
            params: {
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'id,asc',
                    squash: true
                },
                search: null
            },
            resolve: {
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort),
                        search: $stateParams.search
                    };
                }]
            }
        })
        .state('tranzactie-detail', {
            parent: 'tranzactie',
            url: '/tranzactie/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Tranzactie'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/tranzactie/tranzactie-detail.html',
                    controller: 'TranzactieDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Tranzactie', function($stateParams, Tranzactie) {
                    return Tranzactie.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'tranzactie',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('tranzactie-detail.edit', {
            parent: 'tranzactie-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tranzactie/tranzactie-dialog.html',
                    controller: 'TranzactieDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Tranzactie', function(Tranzactie) {
                            return Tranzactie.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('tranzactie.new', {
            parent: 'entity',
            url: '/tranzactie/new',
            data: {
                authorities: []
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tranzactie/tranzactie-dialog.html',
                    controller: 'TranzactieDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                destinatar: null,
                                adresa: null,
                                contact: null,
                                valoare: null,
                                continut: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('home');
                }, function() {
                    $state.go('home');
                });
            }]
        })
        .state('tranzactie.edit', {
            parent: 'tranzactie',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tranzactie/tranzactie-dialog.html',
                    controller: 'TranzactieDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Tranzactie', function(Tranzactie) {
                            return Tranzactie.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('tranzactie', null, { reload: 'tranzactie' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('tranzactie.delete', {
            parent: 'tranzactie',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tranzactie/tranzactie-delete-dialog.html',
                    controller: 'TranzactieDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Tranzactie', function(Tranzactie) {
                            return Tranzactie.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('tranzactie', null, { reload: 'tranzactie' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
