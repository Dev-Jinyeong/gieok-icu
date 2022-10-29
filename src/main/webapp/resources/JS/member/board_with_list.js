const with_accept_btn = document.querySelectorAll(".with_accept");
const board_no_list = document.querySelectorAll(".with_submit_board_no");
const board_writer_list = document.querySelectorAll(".with_submit_board_writer");

for(let i=0; i<with_accept_btn.length; i++) {
	
	with_accept_btn[i].addEventListener("click", () => {
		
		const user_code = document.querySelector("#user_code");

		if(user_code.value=="") {
			alert("로그인이 필요합니다!");
			window.location.href = "/member/login";
		}else {
			const board_no = board_no_list[i].value;
			const board_writer = board_writer_list[i].value;
			const info = window.prompt("신청을 보내시려는 분에게 간단한 소개를 해주세요!😊\n" +
									   "ex) 이름: ㅇㅇㅇ / 나이: ㅇㅇ세 / 성별: 남|여 / 동행신청합니다~ ");
			
			if(info=="") {
				alert("신청자의 정보를 입력해주세요!😊");
			}else if(info.length > 50) {
				alert("소개글이 너무 길어요😭");
			}else if(info!=""&&info!=null) {
				fetch("http://localhost:8080/board_with_sinchung", {
					method: "POST",
					headers: {
						"Content-Type" : "application/json",
					},
					body: JSON.stringify({
						board_no: board_no,
						board_writer: board_writer,
						with_user_info: info,
					})
				})
				.then(res => res.text())
				.then(res => {
					if(res=="") {
						alert("신청이 완료되었습니다!😊");
					}else if(res=="fail") {
						alert("시스템 오류! 관리자에게 문의하세요😭");
					}else if(res == "false") {
						alert("이미 신청한 동행입니다!😭");
					}else if(res == "noSession") {
						alert("로그인이 필요합니다!😭");
						window.location.href = "/member/login";
					}
						
				});
			}
		}
		
		
		
		
		
	});
	
}