(function() {
    'use strict';

    angular
        .module('foodyApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('comentariu', {
            parent: 'entity',
            url: '/comentariu?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Comentarius'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/comentariu/comentarius.html',
                    controller: 'ComentariuController',
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
                }],
            }
        })
        .state('comentariu-detail', {
            parent: 'comentariu',
            url: '/comentariu/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Comentariu'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/comentariu/comentariu-detail.html',
                    controller: 'ComentariuDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Comentariu', function($stateParams, Comentariu) {
                    return Comentariu.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'comentariu',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('comentariu-detail.edit', {
            parent: 'comentariu-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/comentariu/comentariu-dialog.html',
                    controller: 'ComentariuDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Comentariu', function(Comentariu) {
                            return Comentariu.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('comentariu.new', {
            parent: 'comentariu',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/comentariu/comentariu-dialog.html',
                    controller: 'ComentariuDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                continutComentariu: null,
                                dataComentariu: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('^', null, { reload: '^' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('comentariu.edit', {
            parent: 'comentariu',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/comentariu/comentariu-dialog.html',
                    controller: 'ComentariuDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Comentariu', function(Comentariu) {
                            return Comentariu.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('comentariu', null, { reload: 'comentariu' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('comentariu.delete', {
            parent: 'comentariu',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/comentariu/comentariu-delete-dialog.html',
                    controller: 'ComentariuDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Comentariu', function(Comentariu) {
                            return Comentariu.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('comentariu', null, { reload: 'comentariu' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
