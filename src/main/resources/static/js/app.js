var app = angular.module('myApp', []);

app.controller('indexController', function request($scope,$window, $location,$http, $log){
    $scope.subjectPage = function() {
        var host = $location.host();
        $window.location.href='/attendance/get/subjects';
    }
    $scope.teacherspage = function() {
        var host = $location.host();
        $window.location.href='/attendance/get/teacherpage';
    }

    $scope.addteacher = function() {
        var host = $location.host();
        $window.location.href='/attendance/get/addteacherpage';
    }
});

app.controller('subjectController', function request($scope,$window, $location,$http, $log){
    $scope.addSubject = function() {
        var host = $location.host();
        $window.location.href='/attendance/get/subjectpage';
    }
});
app.controller('teacherController', function request($scope,$window, $location,$http, $log){
    $scope.addTeacher = function() {
        debugger
        var host = $location.host();
        $window.location.href='/attendance/get/addteacherpage';
    }
    $scope.editTeacher=function (teacherId){
        debugger
        var host = $location.host();
        $window.location.href='/attendance/get/editteacherpage';
    }
});