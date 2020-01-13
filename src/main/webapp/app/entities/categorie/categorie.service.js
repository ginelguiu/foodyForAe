(function() {
    'use strict';
    angular
        .module('foodyApp')
        .factory('Categorie', Categorie);

    Categorie.$inject = ['$resource'];

    function Categorie ($resource) {
        var resourceUrl =  'api/categorii/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
