(function() {
    'use strict';
    angular
        .module('foodyApp')
        .factory('Comentariu', Comentariu);

    Comentariu.$inject = ['$resource', 'DateUtils'];

    function Comentariu ($resource, DateUtils) {
        var resourceUrl =  'api/comentarii/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.dataComentariu = DateUtils.convertLocalDateFromServer(data.dataComentariu);
                    }
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.dataComentariu = DateUtils.convertLocalDateToServer(copy.dataComentariu);
                    return angular.toJson(copy);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.dataComentariu = DateUtils.convertLocalDateToServer(copy.dataComentariu);
                    return angular.toJson(copy);
                }
            }
        });
    }
})();
