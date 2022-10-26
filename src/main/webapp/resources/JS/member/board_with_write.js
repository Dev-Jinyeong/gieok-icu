/**
 * board_with_write.js
 */


/**
 * 도 목록 선택
 */

const provinceOption = document.querySelector("#province");

provinceOption.addEventListener("focus", () => {
	
	fetch("http://localhost:8080/board_with_province", {
		method: "POST",
		headers: {
			"Content-Type": "application/json",
		}
	})
	.then(res => res.json())
	.then(res => listProv(res))
});

function listProv(data) {
	provinceOption.innerHTML = "<option value='' disabled selected> 도 선택 </option>";
	data.forEach((item) => {
		let province = `<option value=${item.province_id}>
						${item.province_name}
						</option>`
		provinceOption.innerHTML += province;
	})
}


// 도 선택 값 hidden으로 전달
provinceOption.addEventListener("click", () => {
	
	let p_id = document.querySelector("#province");
	let p_nm = document.querySelector("#with_province_name");
	
	p_nm.value = p_id.options[p_id.selectedIndex].text;
	
//	console.log(p_id);
//	console.log(p_nm);
	
});



/**
 * 시 목록 선택
 */

//const provinceOption = document.querySelector("#province");
const cityOption = document.querySelector("#city");

provinceOption.addEventListener("change", () => {

   fetch("http://localhost:8080/board_with_city", {
      method: "POST",
      headers: {
         "Content-Type": "application/json",
      },
      body: JSON.stringify({
         province_id: provinceOption.value,
      }),
   })
   .then(res => res.json())
   .then(res => listCity(res))
});

function listCity(data) {
   cityOption.innerHTML = "<option value='' selected> 시 선택 </option>";
   data.forEach((item) => {
      let city = `<option value=${item.city_code}>
                  ${item.city_name}
               </option>`
      cityOption.innerHTML += city;
   })
}


// 시 선택 값 hidden으로 전달
cityOption.addEventListener("click", () => {
	
	let c_id = document.querySelector("#city");
	let c_nm = document.querySelector("#with_city_name");
	
	c_nm.value = c_id.options[c_id.selectedIndex].text;
	
	console.log(c_id);
	console.log(c_nm);
	
});



/**
 * 명소 목록 선택
 */

//const citylistOption = document.querySelector("#city");
const attrOption = document.querySelector("#attraction");

cityOption.addEventListener("change", () => {
   fetch("http://localhost:8080/board_with_attr", {
      method: "POST",
      headers: {
         "Content-Type": "application/json",
      },
      body: JSON.stringify({
         city_id: cityOption.value,
      }),
   })
   .then(res => res.json())
   .then(res => listAttr(res))
});

function listAttr(data) {
   attrOption.innerHTML = "<option value='' selected> 명소 선택 </option>";
   data.forEach((item) => {
      let attr = `<option value=${item.attr_code}>
                  ${item.attr_name}
               </option>`
      attrOption.innerHTML += attr;
   })
}


//시 선택 값 hidden으로 전달
attrOption.addEventListener("click", () => {
	
	let a_id = document.querySelector("#attraction");
	let a_nm = document.querySelector("#with_attr_name");
	
	a_nm.value = a_id.options[a_id.selectedIndex].text;
	
	console.log(a_id);
	console.log(a_nm);
	
});



/**
 * 기간 선택
 */

// 시작일 오늘 날짜로 설정
document.querySelector("#withStartDay").value = new Date().toISOString().substring(0, 10);

let toDay = document.querySelector("#withStartDay").value;
let withStartDay = document.querySelector("#withStartDay");
let withEndDay = document.querySelector("#withEndDay");

// 시작일 유효성 검사
withStartDay.addEventListener("input", () => {
	
   if(withStartDay.value < toDay){
	   	alert('오늘 날짜 이후로 선택가능합니다 😊');
	   	document.querySelector("#withStartDay").value = toDay;
	   	
	   }
	
});


//// 종료일 유효성 검사
withEndDay.addEventListener("input", function(event) {
   
   if(withStartDay.value > withEndDay.value){
   	alert('시작 날짜 이후로 선택해주세요 😊');
   	document.querySelector("#withEndDay").value = "";
   	
   }
   
});



/**
 * 전체 유효성 검사
 */

let form = board_with;
let memCount = form.memCount;
let with_board_content = form.with_board_content;

function formcheck() {
	
	// 모집인원 유효성 검사
	if (memCount.value == 0) {
		alert('모집인원은 1인 이상부터 가능합니다 😊');
		
		return false;
		
	}
	
	// 내용 유효성 검사
	if (with_board_content.value == "") {
		alert('내용을 입력하세요 😊');
		$('.note-editable').focus();
		
		return false;
		
	}
	
	return true;
	
}



/**
 * 취소버튼
 */
const cancelBtn = document.querySelector("#with_cancel");

cancelBtn.addEventListener("click", () => {
	let page = document.querySelector("#page").value;
	let category = document.querySelector("#category").value;
	let keyword = document.querySelector("#keyword").value;
	
	location.href = `/board_with_list?page=${page}&category=${category}&keyword=${keyword}`;
});


