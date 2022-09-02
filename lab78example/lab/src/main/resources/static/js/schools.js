var app = angular.module('schools', []).config(function ($httpProvider) {
    csrftoken = $("meta[name='_csrf']").attr("content");
    csrfheader = $("meta[name='_csrf_header']").attr("content");
    $httpProvider.defaults.headers.common["X-CSRF-TOKEN"] = csrftoken;
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(csrfheader, csrftoken);
    });
});

app.controller("SchoolsController", function ($scope, $http) {

    $scope.successGetSchoolsCallback = function (response) {
        $scope.schoolsList = response.data;
        $scope.errormessage = "";
    };

    $scope.errorGetSchoolsCallback = function (error) {
        console.log(error);
        $scope.errormessage = "Unable to get list of schools";
    };

    $scope.getSchools = function () {
        $http.get('/public/rest/schools').then($scope.successGetSchoolsCallback, $scope.errorGetSchoolsCallback);
    };

    $scope.successDeleteSchoolCallback = function (response) {
        for (var i = 0; i < $scope.schoolsList.length; i++) {
            var j = $scope.schoolsList[i];
            if (j.id === $scope.deletedId) {
                $scope.schoolsList.splice(i, 1);
                break;
            }
        }
        $scope.errormessage = "";
    };

    $scope.errorDeleteSchoolCallback = function (error) {
        console.log(error);
        if (error.status === 405) {
            $scope.errormessage = "You do not have permissions to do that";
        } else
        if (error.status === 403) {
            $scope.errormessage = "You do not have permissions to do that";
        } else
            $scope.errormessage = "Unable to delete school; check if there are any related records exist. Such records should be removed first";
    };

    $scope.deleteSchool = function (id) {
        $scope.deletedId = id;

        $http.delete('/public/rest/schools/' + id).then($scope.successDeleteSchoolCallback, $scope.errorDeleteSchoolCallback);
    };


    $scope.successGetSchoolCallback = function (response) {
        $scope.schoolsList.splice(0, 0, response.data);
        $scope.errormessage = "";
    };

    $scope.errorGetSchoolCallback = function (error) {
        console.log(error);
        $scope.errormessage = "Unable to get information on school number " + $scope.inputnumber;
    };

    $scope.successAddSchoolCallback = function (response) {

        $http.get('/public/rest/schools/' + $scope.inputnumber).then($scope.successGetSchoolCallback, $scope.errorGetSchoolCallback);
        $scope.errormessage = "";
    };

    $scope.errorAddSchoolCallback = function (error) {
        console.log(error);
        if (error.status === 405) {
            $scope.errormessage = "You do not have permissions to do that";
        } else
        if (error.status === 403) {
            $scope.errormessage = "You do not have permissions to do that";
        } else
            $scope.errormessage = "Impossible to add new school; check if it's number is unique";
    };

    $scope.addSchool = function () {
        $http.post('/public/rest/schools/' + $scope.inputnumber + "/" + $scope.inputname).then($scope.successAddSchoolCallback, $scope.errorAddSchoolCallback);
    };

});
