(function() {
    'use strict';
    angular
        .module('foodyApp')
        .factory('Review', Review);

    Review.$inject = ['$resource', 'DateUtils'];

    function Review ($resource, DateUtils) {
        var resourceUrl =  'api/reviews/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.dataReview = DateUtils.convertLocalDateFromServer(data.dataReview);
                    }
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.dataReview = DateUtils.convertLocalDateToServer(copy.dataReview);
                    return angular.toJson(copy);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.dataReview = DateUtils.convertLocalDateToServer(copy.dataReview);
                    return angular.toJson(copy);
                }
            }
        });
    }
})();
