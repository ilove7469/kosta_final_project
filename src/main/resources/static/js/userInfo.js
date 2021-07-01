/**
 * 
 */
$(function(){
	
	//주소찾기
	$("#searchAddress").click(function(){
		addressSearch();
	});
	
	//이메일 인증
	var AuthTimer=0;
	$("#emailSendBtn").click(function() {// 메일 입력 유효성 검사
		var mail = $("#authemail").val(); //사용자의 이메일 입력값. 
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
			$("#authNum").prop("disabled",false);
			$("#authSendBtn").prop("disabled",false);
			if(AuthTimer!=0){
				AuthTimer.fnStop();
			}
			timerStarter();
		}
	});
		
	//이메일 코드 일치 확인
	$("#authSendBtn").click(function() {
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
					$("#authNum").prop("disabled",true);
					$("#authSendBtn").prop("disabled",true);
				}
				isOk = allOk( idOk, pw1Ok, pw2Ok, emailOk );
				if( isOk == true ){
                  $("#insertBtn").prop("disabled",false);
                }
				$("#chkAllOk").text(idOk + ', ' + pw1Ok + ', ' + pw2Ok + ', ' + emailOk + '마지막 idOk:' + isOk );
				$("#authSendBtn").prop("disabled",false);
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
	
	//모달 버튼 선택으로 회사정보 넣기
	$(".tdCenter").on("click",function(){
		var companyNo = $(this).find("button").val();
		var companyName = $(this).parent().find(".modalCompany").text();
		$("#companyName").attr("value",companyName);
		$("#companyNo").val(companyNo);
	})
	//회사정보 삭제
	$("#initBtn").click(function(){
		$("#companyName").attr("value","");
		$("#companyNo").removeAttr("value");
		$("#department").removeAttr("value");
		$("#position").removeAttr("value");
		$("#duty").removeAttr("value");
		$("#officePhone").removeAttr("value");
	});
	
	//저장버튼 이벤트 추가
	$("#submitBtn").click(function(e){
		e.preventDefault();
		var ss1 = $("#ssn1").val();
		var ss2 = $("#ssn2").val();
		var total = ""+ss1+ss2;
		$("#userIdentity").val(total);
		$("#updateForm").submit();
	})
	
	//재직자 채용예정자 변환
	$("#roleChange").click(function(){
		var userNo  = $("#userNoInfo").val();
		var hrefURI = "/user/changetrainee/"+userNo;
    	if($("#roleChange").text()=="채용예정자 전환"){
    		location.href=hrefURI;
    	}else if($("#roleChange").text()=="재직자 전환"){
			if($("#companyInfo").val()==0){
				alert("회사정보가 저장되어야합니다. \n업데이트 후 다시 시도 해주세요","info");
			}else{
				location.href=hrefURI;
			}
		}
	})
	
	//회원탈퇴
	$("#userDeleteBtn").click(function(){
		var userNo  = $("#userNoInfo").val();
		var userId = $("#userId").val();
		var hrefURI ="/user/deleteUser/"+userNo+"/"+userId;
		alert(hrefURI);
		
		location.href=hrefURI;
	});
	
});
