
function logout(){

	$.ajax({
		url : '/Five/member/logout',
		success : function (){
			loaction.href='/Five/main.jsp';
		}
	})	
}

$(function() {

	let mno = $("#my_num").val();
	
	if(mno != null) {
		$.ajax({
			type : 'post',
			url : '/Five/member/getaccount',
			data : {"ac_no" : mno},
			success:function(result){
				$("#mymypro").attr("src", "/Five/img/profile/" + result["ac_profileimg"]);
				$("#headernick").html(result["ac_nickname"] + "님");
			}
		});
	}
});
