var app = angular.module('myApp', ['webcam','gm.datepickerMultiSelect','ui.bootstrap']);

app.controller('indexController', function request($scope,$window, $location,$http, $log){
    $scope.getIndex = function() {
        var host = $location.host();
        $window.location.href='/attendance/index';
    }
    $scope.getTeacherView = function() {
        var host = $location.host();
        $window.location.href='/attendance/dashboard';
    }
    $scope.getStudentView = function() {
        var host = $location.host();
        $window.location.href='/attendance/studentview';
    }
    $scope.subjectPage = function() {
        var host = $location.host();
        $window.location.href='/attendance/subjects';
    }
    $scope.teacherspage = function() {
        var host = $location.host();
        $window.location.href='/attendance/get/teacherpage';
    }

    $scope.addteacher = function() {
        var host = $location.host();
        $window.location.href='/attendance/get/addteacherpage';
    }
    $scope.getStudents = function () {
        var host = $location.host();
        $window.location.href='/attendance/students';
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
        $window.location.href='/attendance/upload';
    }
});

app.controller('landingPageController', function request($scope,$window, $location,$http, $log){
    $scope.username=""
    $scope.password=""
    $scope.hasError=false;
    $scope.error="";
    $scope.teacherLogin = function() {
        // var host = $location.host();
        // $window.location.href='/attendance/dashboard';
        var data= {
            username:$scope.username,
            password:$scope.password
        }
        $http({
            method: 'POST',
            url: 'http://localhost:8080/attendance/teacher/login',data,
        }).then(function successCallback(response) {
            // $scope.subjects=response.data;
            if(response.data.message === "SUCCESS"){
                var teacherId=response.data.teacherId;
                $window.location.href='/attendance/'+teacherId+'/dashboard';
            }else{
                $scope.hasError=true;
                $scope.error="Incorrect Credentials";
            }

            // this callback will be called asynchronously
            // when the response is available
        }, function errorCallback(response) {
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });
    }

    $scope.getStudentView = function() {
        var host = $location.host();
        $window.location.href='/attendance/studentview';
    }

});

app.controller('studentDashboardController', function request($scope,$window, $location,$http, $log){
    var subjects=[]
    $http({
        method: 'GET',
        url: 'http://localhost:8080/attendance/subjects/today',
    }).then(function successCallback(response) {
        $scope.subjects=response.data;
        // this callback will be called asynchronously
        // when the response is available
    }, function errorCallback(response) {
        debugger
        // called asynchronously if an error occurs
        // or server returns response with an error status.
    });

    $scope.takeAttendance=function (subjectId){
        var host = $location.host();
        $window.location.href='/attendance/subject/'+subjectId+'/attendancepage';
    }

})
app.controller('subjectController', function request($scope,$window, $location,$http, $log){
    $scope.addSubject = function() {
        var host = $location.host();
        $window.location.href='/attendance/get/subjectpage';
    }

    $scope.logout = function() {
        debugger
        var host = $location.host();
        $window.location.href='/attendance/index';

    }
    // $scope.subjects=[];
    let subjectData=[]
    var teacherId=document.getElementsByName("teacherId")[0].value;
    $http({
        method: 'GET',
        url: 'http://localhost:8080/attendance/get/'+teacherId+'/subjects',
    }).then(function successCallback(response) {
        let subjectDates=[]
        let subjectDate={
            subject:"",
            dates:[]
        }

        // data=response.data.map((item)=>{
        //     var subjData=[]
        //     item.dates.map((subjDate)=>{
        //         var date=new Date(subjDate.date);
        //         if(subjDate.indexOf(date) !== -1) {
        //             subjData.add(date.getMonth());
        //         }
        //     })
        //     subjectDate.subject=item.id;
        //     subjectDate.dates=subjData;
        //     subjectDates.add(subjectDate);
        // })


        $scope.subjects=response.data;
        // $scope.subjectsData=subjectDates
        // this callback will be called asynchronously
        // when the response is available
    }, function errorCallback(response) {
        debugger
        // called asynchronously if an error occurs
        // or server returns response with an error status.
    });

    $http({
        method: 'GET',
        url: 'http://localhost:8080/attendance/get/semester',
    }).then(function successCallback(response) {
        $scope.semesters=response.data;
        // this callback will be called asynchronously
        // when the response is available
    }, function errorCallback(response) {
        debugger
        // called asynchronously if an error occurs
        // or server returns response with an error status.
    });

    // $scope.getStudentsData=function (subject){
    //     var students=subject.students
    //     var ele=""
    //     var complete=<td>
    //         <li className="nav-item" style="list-style-type: none;">
    //             <a className="nav-link collapsed" href="#" data-toggle="collapse" data-target="#subject"
    //                aria-expanded="true" aria-controls="subjectCollapse">
    //                 <i className="fas fa-fw fa-book"></i>
    //                 <span>Show Students</span>
    //             </a>
    //             <div style="margin: 10px 10px 10px 10px" id="subjectCollapse" className="collapse"
    //                  aria-labelledby="headingTwo" data-parent="#accordionSidebar">
    //
    //             </div>
    //         </li>
    //     </td>
    //     angular.forEach(students,function (value){
    //         this.push(
    //             <div className="bg-white py-2 collapse-inner rounded">
    //             </div>
    //         )
    //     },ele)
    //
    // }
$scope.getReport=function (subjectId){
    $http({
        method: 'GET',
        url: 'http://localhost:8080/attendance/subject/'+subjectId,
    }).then(function successCallback(response) {
        debugger
        var anchor = angular.element('<a/>');
        anchor.attr({
            href: 'data:attachment/csv;charset=utf-8,' + encodeURI(response.data),
            target: '_blank',
            download: 'report.csv'
        })[0].click();
        // $scope.subjects=response.data
        // this callback will be called asynchronously
        // when the response is available
    }, function errorCallback(response) {
        debugger
        // called asynchronously if an error occurs
        // or server returns response with an error status.
    });
    return d;
}


    $scope.options = {
        startingDay:1,
        minDate:new Date(),
        customClass: function(data) {
            if($scope.selectedDates.indexOf(data.date.setHours(0, 0, 0, 0)) > -1) {
                return 'selected';
            }
            return '';
        }
    }

    $scope.subjectName="";
    $scope.semesterId="";
    $scope.subjectCode="";
    $scope.activeDate="";
    $scope.selectedDates=[]

    $scope.addSubject=function (){
        var data = {
            subjectName:$scope.subjectName,
            semesterId:$scope.semesterId.id,
            subjectCode:$scope.subjectCode,
            dates:$scope.selectedDates
        }
        var teacherId=document.getElementsByName("teacherId")[0].value;
        debugger
        $http({
            method: 'POST',
            url: 'http://localhost:8080/attendance/'+teacherId+'/subject',
            data:data
        }).then(function successCallback(response) {
           $window.alert("Subject Added Successfully")
            $scope.subjects=response.data
            // this callback will be called asynchronously
            // when the response is available
        }, function errorCallback(response) {
            debugger
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });


    }

    $scope.subjectMonth=""
    // $scope.subjectMonth=""

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

    $scope.attempt=true;
    $scope.hideCamera=true;
    $scope.makeSnapshot = function() {
        if (_video) {
            var patCanvas = document.querySelector('#snapshot');
            if (!patCanvas) return;

            patCanvas.width = _video.width;
            patCanvas.height = _video.height;
            $scope.attempt=false;
            $scope.hideCamera=false;
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


    $scope.responseReceived=false;
    var sendSnapshotToServer = function sendSnapshotToServer(imgBase64) {
        // $scope.snapshotData = imgBase64;
        var subjectId=document.getElementById("subjectId").value
        debugger
        var subject=subjectId
        var attendance={
            image:imgBase64,
            subject:subject
        }

        // var url = 'posturl', data = 'parameters',config='contenttype';



        $http.post("http://localhost:8080/attendance/save/pic", attendance).then(function (response) {

            $scope.response=response.data;
            $scope.responseReceived=true;
            $window.alert(response.data.message);
        }, function (response) {
        });

    };
    }
)