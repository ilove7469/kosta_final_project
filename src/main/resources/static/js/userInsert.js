/**
 * 
 */

$(function(){
		//주소 검색
		$("#searchAddress").click(function(){
			addressSearch();
		});
	
	
  		 //값 초기화. 회원가입 버튼 비활성화
	    $("#btnConfirm").prop("disabled",true);
	    var idOk = false;
	    var pw1Ok = false;
	    var pw2Ok = false;
	    var nameOk = true;
	    var emailOk = true;
	    var isOk = false;    
	    
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
	            $("#signupWarn").text( '　' );    
	            //Ajax 활용 ID 중복체크, 중복이 아니면? true = 사용가능 ID            
	            var varIsNotDupId = false;
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
	                        varIsNotDupId = true;
	                        idOk = true;
	                        $("#signupWarn").text( '　' );            
	                        isOk = allOk( idOk, pw1Ok, pw2Ok, nameOk, emailOk );
	                        if( isOk == true ){
	                            $("#btnConfirm").prop("disabled",false);
	                        }
	                        $("#chkAllOk").text(idOk + ', ' + pw1Ok + ', ' + pw2Ok + ', ' + nameOk + ', ' + emailOk + '마지막 idOk:' + isOk );
	                    }else{
	                        //중복된 ID인 경우
	                        $("#idDisable").removeClass("is-invalid");
	                        $("#idDisable").addClass("is-valid");
	                        idOk = false;
	                        $("#chkAllOk").text(idOk + ', ' + pw1Ok + ', ' + pw2Ok + ', ' + nameOk + ', ' + emailOk + '마지막 idOk:' + isOk );
	                        $("#signupWarn").text( '중복된 ID입니다. 다른 ID를 입력해 주세요.' );
	                        $("#btnConfirm").prop("disabled",true);
	                    }
	                }, error: function( error ){
	                    alert('error : ' + error);
	                }
	            });                    
	        }else{
	            //조건에 맞지 않는 경우
	            idOk = false;
	            $("#btnConfirm").prop("disabled",true);
	            $("#idEnable").attr("class","is-invalid");
	            $("#idDisable").text("4~20자 소문자 또는 숫자를 넣어주세요");
	            $("#idDisable").attr("class","is-valid");
	        }
	    });
	    
	  	//pw 체크(영소문자+숫자 5자 이상)------------------
	    $("#confirmPassword").keyup(function(){
	        var pw1 = $("#newPassword").val();
			pw1Ok = true;
	        var pw2 = $("#confirmPassword").val();
			// : 숫자, 특문 각 1회 이상, 영문은 2개 이상 사용하여 8자리 이상 입력
   			//var regExpPw = /(?=.*\d{1,50})(?=.*[~`!@#$%\^&*()-+=]{1,50})(?=.*[a-zA-Z]{2,50}).{8,50}$/;
	        if( pw1 == pw2 ){
	            pw2Ok = true;            
	            $("#signupWarn").text( '　' );
				isOk = allOk( idOk, pw1Ok, pw2Ok, nameOk, emailOk ); 
				alert(isOk);        
	            if( isOk == true ){
	                $("#btnConfirm").prop("disabled",false);
	            }
	            $("#chkAllOk").text(idOk + ', ' + pw1Ok + ', ' + pw2Ok + ', ' + nameOk + ', ' + emailOk + '마지막 idOk:' + isOk );
	            $("#pwDisable").removeClass("is-valid");
	            $("#pwDisable").addClass("is-invalid");
	            $("#pwEnable").removeClass("is-invalid");
	            $("#pwEnable").addClass("is-valid");
	            
	        }else{
	            pw2Ok = false;
	            $("#pwEnable").removeClass("is-valid");
	            $("#pwEnable").addClass("is-invalid");
	            $("#pwDisable").removeClass("is-invalid");
	            $("#pwDisable").addClass("is-valid");
	        }
	    });
	function allOk( ok1, ok2, ok3, ok4, ok5 ){
		    if( ok1 && ok2 && ok3 && ok4 && ok5 ){
		        return true;
		    }else{
		        return false;
    		}
	}
  	});
	