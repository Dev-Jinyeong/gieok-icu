// 수정
const update_button = document.getElementById('update_button');
let board_no = document.getElementById('board_no').innerText;
update_button.addEventListener('click', () => {
	location.href = `/photo_event_update?photo_no=${board_no}`;
});


// 뒤로가기
let prev_url = document.referrer; // 이전주소값
const back_button = document.querySelector('.board_post_button>input[type="button"]:last-of-type');
back_button.addEventListener('click', () => {
	location.href = prev_url;
});

// 삭제
const delete_button = document.getElementById('delete_button');
delete_button.addEventListener('click', () => {
	location.href = `/photo_event_delete?photo_no=${board_no}`;
});

// 좋아요 & 신고
const like_checkbox = document.getElementById('like_checkbox');
like_checkbox.addEventListener('change', () => {
	like_check();
});

function like_check() {
	fetch(`http://localhost:8080/photo_event_detailLike?photo_no=${board_no}`, {
		method: "GET",
	})
	.then((response) => response.json())
	.then(function(response) {
		if(response.msg != "") {
			alert(response.msg);
			if(response.url != undefined) {
				location.href = response.url;
			}
		} else {
			alert('시스템 오류');
		}
	});
}
/*
const report_checkbox = none;
report_checkbox.addEventlistener('change', () => {
	report_check();
});

function report_check() {
	fetch("http://localhost:8080/photo_event_detailReport", {
		method: "GET",
		body: "photo_event_form",
	})
	.then((response) => response.json())
	.then(function(response) {
		if(response.msg != "") {
			alert(response.msg);
			location.href = response.url;
		} else {
			alert('시스템 오류');
		}
	});
}
*/