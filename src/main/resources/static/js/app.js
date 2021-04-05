var app = angular.module('myApp', []);

app.controller('indexController', function request($scope,$window, $location,$http, $log){
    $scope.subjectPage = function() {
        var host = $location.host();
        $window.location.href='/attendance/get/subjects';
    }
});

app.controller('subjectController', function request($scope,$window, $location,$http, $log){
    $scope.addSubject = function() {
        var host = $location.host();
        $window.location.href='/attendance/get/subjectpage';
    }
});