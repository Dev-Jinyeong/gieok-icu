// 신청 목록 modal
const modalBg = $('#modal_back');
const modalPop = $('#modal_container');
	
function popClose() {
	$(modalBg).hide();
	$(modalPop).hide();
}

// 버튼
const board_no_list = document.querySelectorAll(".with_submit_board_no");
const board_writer_list = document.querySelectorAll(".with_submit_board_writer");
const board_memCount_list = document.querySelectorAll(".with_submit_board_memCount");

const with_delete_btn = document.querySelectorAll("#delBtn");
const with_accept_btn = document.querySelectorAll("#with_accept");

const card_list = document.querySelector(".with_user_con");

for (let i=0; i < with_delete_btn.length; i++) {

	const board_no = board_no_list[i].value;
	
	// 삭제 버튼
	with_delete_btn[i].addEventListener("click", () => {
		
		const user_code = document.querySelector("#user_code");

		if (user_code.value == "") {
			alert("로그인이 필요합니다!");
			window.location.href = "/member/login";
		} else {
			const board_writer = board_writer_list[i].value;
		
			fetch("http://localhost:8080/mywrite_with_delete", {
				method: "POST",
				headers: {
					"Content-Type" : "application/json",
				},
				body: JSON.stringify({
					board_no: board_no,
				})
			})
			.then (res => res.text())
			.then (res => {
				if(res == "") {
					alert("나의 동행 삭제가 완료되었습니다!😊");
				} else if (res == "fail") {
					alert("시스템 오류! 관리자에게 문의하세요😭");
				} else if (res == "noSession") {
					alert("로그인이 필요합니다!😭");
					window.location.href = "/member/login";
				}
					
			});
		}
		
	});
	
	// 신청 목록 버튼
	with_accept_btn[i].addEventListener("click", () => {
	
		const user_code = document.querySelector("#user_code");

		$(modalBg).show();
		$(modalPop).show();

		const with_count = document.getElementById("with_count");
		const memCount = board_memCount_list[i].value;
		with_count.textContent = "모집인원 : " + memCount + " 명";
		
		const accept_count = document.getElementById("accept_count");
		
		// 수락한 동행 수
		fetch("http://localhost:8080/mywrite_with_acceptcount", {
			method: "POST",
			headers: {
				"Content-Type" : "application/json",
			},
			body: JSON.stringify({
				board_no: board_no,
			})
		})
		.then(res => res.text())
		.then(res => {
			accept_count.textContent = "수락한 동행 수 : " + res + " 명";
		})
		
		// 나에게 신청한 동행 수
		fetch("http://localhost:8080/my_with_list", {
			method: "POST",
				headers: {
				"Content-Type" : "application/json",
			},
			body: JSON.stringify({
				board_no: board_no,
			})
		})
		.then (res => res.json())
		.then (res => list(res))
		
	});
	
	function list(data) {
		
		card_list.innerHTML = '';
		
		if (data.length == 0) {
			card_list.innerHTML = `<div class="with_user_con">신청 목록이 없습니다.</div>`;
		} else {
			data.forEach((item) => {
			
				let content = `
								<div class="with_user_con">
									<div class="with_user_info1">
										<div class="with_user_info_text">
											신청자 : ${item.with_user_id}
										</div>
										<div class="with_user_info_btn">
											<button class="accept_btn md_btn" onclick="mywithaccept(${item.board_no}, '${item.with_user_id}')">수락</button>
											<button class="reject_btn md_btn" onclick="mywithreject(${item.board_no}, '${item.with_user_id}')">거절</button>
										</div>
									</div>
									<div class="with_user_info2">
										${item.with_user_info}
									</div>
								</div>
								`;
				
				card_list.innerHTML += content;
				
			});	
		}
	
	}
	
}

// 동행 신청 수락
function mywithaccept(board_no, with_user_id) {

	fetch("http://localhost:8080/my_with_accept", {
		method: "POST",
		headers: {
			"Content-Type": "application/json"
		},
		body: JSON.stringify({
			board_no: board_no,
			with_user_id: with_user_id,
		}),
	})
	.then(res => res.text())
	.then(res => {
		if(res=="") {
			alert("동행 신청이 수락되었습니다!😊");
		}else if(res=="fail") {
			alert("시스템 오류! 관리자에게 문의하세요😭");
		}else if(res == "noSession") {
			alert("로그인이 필요합니다!😭");
			window.location.href = "/member/login";
		}
	});
	
}

// 동행 신청 거절
function mywithreject(board_no, with_user_id) {
	
	fetch("http://localhost:8080/my_with_reject", {
		method: "POST",
		headers: {
			"Content-Type": "application/json"
		},
		body: JSON.stringify({
			board_no: board_no,
			with_user_id: with_user_id,
		}),
	})
	.then(res => res.text())
	.then(res => {
		if(res=="") {
			alert("동행 신청이 거절되었습니다!😊");
		}else if(res=="fail") {
			alert("시스템 오류! 관리자에게 문의하세요😭");
		}else if(res == "noSession") {
			alert("로그인이 필요합니다!😭");
			window.location.href = "/member/login";
		}
	});

}