(function() {
    'use strict';
    angular
        .module('foodyApp')
        .factory('Tranzactie', Tranzactie);

    Tranzactie.$inject = ['$resource'];

    function Tranzactie ($resource) {
        var resourceUrl =  'api/tranzacties/:id';

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
