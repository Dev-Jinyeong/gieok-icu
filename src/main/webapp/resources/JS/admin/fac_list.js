// 주변 시설 등록 버튼
const fac_regist_btn = document.querySelector(".add_contents");

fac_regist_btn.addEventListener("click", () => {
	let attr_code = document.querySelector("#attr_code").value;
	let page = document.querySelector("#page").value;
	let sort = document.querySelector("#sort").value;
	let category = document.querySelector("#category").value;
	let keyword = document.querySelector("#keyword").value;
	location.href = `/admin/fac_regist?attr_code=${attr_code}&page=${page}&sort=${sort}&category=${category}&keyword=${keyword}`;
});


// 전체 선택 버튼
let select_all_btn_checked = false;

const select_all_btn = document.querySelector(".board_select");
const fac_checkBox = document.querySelectorAll(".fac_checkBox");

select_all_btn.addEventListener("click", () => {

	if(select_all_btn_checked) {
		select_all_btn_checked = false; 
		fac_checkBox.forEach((item) => {
			item.checked = false;
		});
	}else {
		select_all_btn_checked = true; 
		fac_checkBox.forEach((item) => {
			item.checked = true;
		});
	}
});

// 선택 삭제 버튼
const select_delete_btn = document.querySelector(".select_delete");
select_delete_btn.addEventListener("click", () => {
	let checked_fac = new Array(10);

	for (let i = 0; i < fac_checkBox.length; i++) {
		if(fac_checkBox[i].checked === true) {
			checked_fac[i] = fac_checkBox[i].value;
		}
		console.log(checked_fac[i]);
	}
	
	// fetch 
	fetch("http://localhost:8080/admin/fac_delete", {
		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
	})
});

// 정렬 전체 버튼
const sort_btn = document.querySelector("#sort_btn");
sort_btn.addEventListener("click", () => {
	if(search_form.sort.value == "") {
		search_form.category.value="";
		search_form.keyword.value="";
	}	
});


