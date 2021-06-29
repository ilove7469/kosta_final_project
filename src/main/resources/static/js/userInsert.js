/**
 * 
 */
$(function(){
		//값 초기화. 회원가입 버튼 비활성화
	    $("#insertBtn").prop("disabled",true);
	    var idOk = false;
	    var pw1Ok = false;
	    var pw2Ok = false;
	    var emailOk = false;
	    var isOk = false;   
		
		//주소 검색
		$("#searchAddress").click(function(){
			addressSearch();
		});
	
	    //정규식 활용 id 체크(영소문자+숫자 5자 이상)------------------
	    $("#inputUserId").keyup(function(){    
	        
	        //배열 초기화
	        var viewData = {};        
	        var inputTxt = $("#inputUserId").val();
	        //data[키] = 밸류
	        viewData["userId"] = inputTxt;
	        var chkOnlyEngNum = /^[a-z0-9_-]{4,20}$/;
	 
	        if( chkOnlyEngNum.test(inputTxt) && inputTxt.length > 3){
	        	
	            //정규식 조건을 통과한 경우
	            //$("#signupWarn").text( '　' );    
	            //초기값 valid 적용
	            $("#idEnable").attr("class","is-invalid");
	            $("#idDisable").attr("class","is-invalid");
	            $.ajax({
	                type: "POST"
	                //동기식으로 변경 >> ajax에서 리턴값을 이용하기 위해
	                , async: false
	                , contentType:"application/json"
	                , url: "/user/userIdChk"
	                , datatype: "json"
	                , data: JSON.stringify(viewData)
	                , success: function(data){
	                    if( data === 0 ){
	                        //컨트롤러에서 넘어온 결과가 0인 경우 사용 가능한 아이디로 판단
	                        //alert('ok data 트루라고 했다');
	                        $("#idEnable").removeClass("is-invalid");
	                        $("#idEnable").addClass("is-valid");
	                        idOk = true;
	                        //$("#signupWarn").text( '　' );            
	                        isOk = allOk( idOk, pw1Ok, pw2Ok, emailOk );
	                        if( isOk == true ){
	                            $("#insertBtn").prop("disabled",false);
	                        }
	                        //$("#chkAllOk").text(idOk + ', ' + pw1Ok + ', ' + pw2Ok +  ', ' + emailOk + '마지막 idOk:' + isOk );
	                    }else{
	                        //중복된 ID인 경우
	                        $("#idDisable").removeClass("is-invalid");
	                        $("#idDisable").addClass("is-valid");
	                        idOk = false;
	                        //$("#chkAllOk").text(idOk + ', ' + pw1Ok + ', ' + pw2Ok + ', ' + emailOk + '마지막 idOk:' + isOk );
	                        $("#idDisable").text( '중복된 ID입니다. 다른 ID를 입력해 주세요.' );
	                        $("#btnConfirm").prop("disabled",true);
	                    }
	                }, error: function( error ){
	                    alert('error : ' + error);
	                }
	            });                    
	        }else{
	            //조건에 맞지 않는 경우
	            idOk = false;
	            $("#insertBtn").prop("disabled",true);
	            $("#idEnable").attr("class","is-invalid");
	            $("#idDisable").text("4~20자 소문자 또는 숫자를 넣어주세요");
	            $("#idDisable").attr("class","is-valid");
	        }
	    });
	    
	  	//pw 체크()------------------
	    $("#confirmPassword").keyup(function(){
	        var pw1 = $("#newPassword").val();
			pw1Ok = true;
	        var pw2 = $("#confirmPassword").val();
			// : 숫자, 특문 각 1회 이상, 영문은 2개 이상 사용하여 8자리 이상 입력
   			//var regExpPw = /(?=.*\d{1,50})(?=.*[~`!@#$%\^&*()-+=]{1,50})(?=.*[a-zA-Z]{2,50}).{8,50}$/;
	        if( pw1 == pw2 ){
	            pw2Ok = true;            
	            //$("#signupWarn").text( '　' );
				isOk = allOk( idOk, pw1Ok, pw2Ok, emailOk ); 
	            if( isOk == true ){
	                $("#insertBtn").prop("disabled",false);
	            }
	            //$("#chkAllOk").text(idOk + ', ' + pw1Ok + ', ' + pw2Ok + ', '  + emailOk + '마지막 idOk:' + isOk );
	            $("#pwDisable").removeClass("is-valid");
	            $("#pwDisable").addClass("is-invalid");
	            $("#pwEnable").removeClass("is-invalid");
	            $("#pwEnable").addClass("is-valid");
	            
	        }else{
	            pw2Ok = false;
				$("#insertBtn").prop("disabled",true);
	            $("#pwEnable").removeClass("is-valid");
	            $("#pwEnable").addClass("is-invalid");
	            $("#pwDisable").removeClass("is-invalid");
	            $("#pwDisable").addClass("is-valid");
	        }
	    });

		//이메일 인증
		var AuthTimer=0;
		$("#emailSendBtn").click(function() {// 메일 입력 유효성 검사
			var mail = $("#email").val(); //사용자의 이메일 입력값. 
			if (mail == "") {
				alert("메일 주소가 입력되지 않았습니다.","info");
			} else {
				//mail = mail+"@"+$(".domain").val(); //셀렉트 박스에 @뒤 값들을 더함.
				$.ajax({
					type : "post",
					url : "/user/email/send",
					data : {
						email:mail
						},
					dataType :'json',
				});
				alert("인증번호가 전송되었습니다.","info");
				emailOk = false;
				if(AuthTimer!=0){
					AuthTimer.fnStop();
				}
				timerStarter();
			}
		});
		
		//이메일 코드 일치 확인
		$("#emailConfirm").click(function() {
			var authNum = $("#authNum").val();
			$.ajax({
				type:"post",
				url:"/user/email/certificate",
				data:{"authNum":authNum},
				dataType :'json',
				success:function(item){
					alert(item.message,item.info);
					emailOk = item.emailOk;
					if(emailOk){
						AuthTimer.fnStop();
						$("#email").attr("readonly","readonly");
					}
					isOk = allOk( idOk, pw1Ok, pw2Ok, emailOk );
					if( isOk == true ){
	                  $("#insertBtn").prop("disabled",false);
	                }
					//$("#chkAllOk").text(idOk + ', ' + pw1Ok + ', ' + pw2Ok + ', ' + emailOk + '마지막 idOk:' + isOk );
					
				}
			});
		});
		
		//이메일 타이머
		// 타이머 구현_daldal
		function $ComTimer(){
		    //prototype extend
		}
		//타이머 로직
		$ComTimer.prototype = {
		    comSecond : ""
		    , fnCallback : function(){}
		    , timer : ""
		    , domId : ""
		    , fnTimer : function(){
		        var m = Math.floor(this.comSecond / 60) + "분 " + (this.comSecond % 60) + "초";	// 남은 시간 계산
		        this.comSecond--;					// 1초씩 감소
		        console.log(m);
		        this.domId.innerText = m;
		        if (this.comSecond < 0) {			// 시간이 종료 되었으면..
		            clearInterval(this.timer);		// 타이머 해제
		            alert("인증시간이 초과하였습니다. 다시 인증해주시기 바랍니다.","warning");
					$.ajax({
						type:"post",
						url:"/user/email/end",
					});
		        }
		    }
		    ,fnStop : function(){
		        clearInterval(this.timer);
		    }
		}
		//타이머 설정
		function timerStarter(){
			AuthTimer = new $ComTimer()
			AuthTimer.comSecond = 180; // 제한 시간
			AuthTimer.fnCallback = function(){alert("다시인증을 시도해주세요.","warning")}; // 제한 시간 만료 메세지
			AuthTimer.timer =  setInterval(function(){AuthTimer.fnTimer()},1000); 
			
			AuthTimer.domId = document.getElementById("emailTimer"); 	
		}
		
		//이메일 인증 알림창
		var alert = function(msg, ifo) {
			swal({
				title : "인증 결과",
				text : msg,
				icon : ifo,
				timer : 3000,
				customClass : "sweet-size",
				 button: {
  					 text: "확인"
  				},
				showConfirmButton : true,
				closeOnConfirm : true
			});
		}
		
		//회원가입 버튼 클릭 이벤트
	    $("#insertBtn").click(function(){
	        $("#signupForm").attr("action", "/user/userInsert")
	        $("#signupForm").submit();
	    });
  	});
	//서밋 버튼 활성화 체크
	function allOk( ok1, ok2, ok3, ok4 ){
	    if( ok1 && ok2 && ok3 && ok4 ){
	        return true;
	    }else{
	        return false;
		}
	}
	