var app = angular.module('myApp', ['webcam']);

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
    $scope.studentspage = function () {
        var host = $location.host();
        $window.location.href='/attendance/get/studentspage';
    }
    $scope.addstudent = function (){
        var host= $location.host();
        $window.location.href='/attendance/get/addstudentpage';
    }
    $scope.takeAttendance = function (){
        var host= $location.host();
        $window.location.href='/attendance/get/attendancepage';
    }
    $scope.upload = function (){
        var host= $location.host();
        $window.location.href='/attendance/get/upload';
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
app.controller('studentController',function request ($scope,$window,$location,$http,$log){
    $scope.addstudent = function (){
        debugger
        var host= $location.host();
        $window.location.href='/attendance/get/addstudentpage';
    }
    $scope.editStudent=function (studentId){
        debugger
        var host= $location.host();
        $window.location.href='/attendance/get/editstudentpage';
    }
    }
)

app.controller('attendanceController',function request ($scope,$window,$location,$http,$log){


    var _video = null,
        patData = null;
    $scope.student="";

    $scope.patOpts = {x: 0, y: 0, w: 25, h: 25};

    // Setup a channel to receive a video property
    // with a reference to the video element
    // See the HTML binding in main.html
    $scope.channel = {};

    $scope.webcamError = false;
    $scope.onError = function (err) {
        $scope.$apply(
            function() {
                $scope.webcamError = err;
            }
        );
    };

    $scope.onSuccess = function () {
        // The video element contains the captured camera data
        _video = $scope.channel.video;
        $scope.$apply(function() {
            $scope.patOpts.w = _video.width;
            $scope.patOpts.h = _video.height;
            //$scope.showDemos = true;
        });
    };

    $scope.onStream = function (stream) {
        // You could do something manually with the stream.
    };

    $scope.makeSnapshot = function() {
        if (_video) {
            var patCanvas = document.querySelector('#snapshot');
            if (!patCanvas) return;

            patCanvas.width = _video.width;
            patCanvas.height = _video.height;
            var ctxPat = patCanvas.getContext('2d');

            var idata = getVideoData($scope.patOpts.x, $scope.patOpts.y, $scope.patOpts.w, $scope.patOpts.h);
            ctxPat.putImageData(idata, 0, 0);

            sendSnapshotToServer(patCanvas.toDataURL());

            patData = idata;
        }
    };

    /**
     * Redirect the browser to the URL given.
     * Used to download the image by passing a dataURL string
     */
    $scope.downloadSnapshot = function downloadSnapshot(dataURL) {
        window.location.href = dataURL;
    };

    var getVideoData = function getVideoData(x, y, w, h) {
        var hiddenCanvas = document.createElement('canvas');
        hiddenCanvas.width = _video.width;
        hiddenCanvas.height = _video.height;
        var ctx = hiddenCanvas.getContext('2d');
        ctx.drawImage(_video, 0, 0, _video.width, _video.height);
        return ctx.getImageData(x, y, w, h);
    };

    /**
     * This function could be used to send the image data
     * to a backend server that expects base64 encoded images.
     *
     * In this example, we simply store it in the scope for display.
     */
    var sendSnapshotToServer = function sendSnapshotToServer(imgBase64) {
        $scope.snapshotData = imgBase64;

        // var url = 'posturl', data = 'parameters',config='contenttype';



        $http.post("http://localhost:8080/attendance/save/pic", imgBase64).then(function (response) {


            $scope.student=response;
        }, function (response) {

debugger
        });

    };
    }
)