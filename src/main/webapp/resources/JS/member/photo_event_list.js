/* =====// board list start //===== */
const board_list = document.querySelector(".board_list");

/* =====// board list end //===== */

/* =====// upload button //===== */
const upload_button = document.querySelector('.board_title>input[type="button"]');

if(upload_button != null) {
	upload_button.addEventListener('click', () => {
		location.href = "/photo_event_upload";
	});
}

/* =====// search start //===== */
const board_search_button = document.querySelector('.board_search>input[type="submit"]:last-of-type');
const board_search_input = document.querySelector('.board_search>input[type="text"]');

function search_box_check() {
	board_search_button.addEventListener('click', () => {
		if(board_search_input.value == "") {
			alert('검색어를 입력해주세요');
		}
	});
}

board_search_button.addEventListener('click', () => {
	search_box_check();
});
/* =====// search end //===== */


/* =====//  //===== */
function board_search() {
	
}
/* =====//  //===== */