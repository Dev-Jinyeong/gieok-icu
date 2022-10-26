// 주소 검색
function find_addr() {
   new daum.Postcode({
       oncomplete: function(data) { // 선택시 입력값 세팅
           document.getElementById("facreg_inp_addr").value = data.address; // 주소 넣기
       }
   }).open();
}

const main_image = document.querySelector("#pre_img");
let facimageCheck = false;

function preview() {
   if(main_image.value!="") {
      preview_img.src=URL.createObjectURL(event.target.files[0]);
      facimageCheck = true;
   }else{
      alert('메인 이미지를 다시 선택해주세요!');
      preview_img.src="";
      facimageCheck = false;
   }
}

// 현재시간
//document.getElementById('optime').value = new Date().toISOString().slice(11, 16);
//document.getElementById('cltime').value = new Date().toISOString().slice(11, 16);

// 유효성 검사
let form = fac_regist;

function formValidate1() {
   const type = form.fac_type;
   const name = form.fac_name; 
   const addr = form.fac_addr; 
   const link = form.fac_link; 
   
   if(type.value=="") {
      alert("타입은 필수 선택 사항입니다!");
      type.focus();
      return false;
   }
   if(name.value=="") {
      alert("이름은 필수 입력 사항입니다!");
      name.focus();
      return false;
   }
   if(addr.value=="") {
      alert("주소는 필수 검색 사항입니다!");
      addr.focus();
      return false;
   }
   if(link.value=="") {
      alert("링크는 필수 입력 사항입니다!");
      map.focus();
      return false;
   }
   if(facimageCheck == false) {
      alert("메인 이미지를 선택해주세요!");
      return false;
   }
   
   return true;
}

function formValidate2() {
   const type = form.fac_type;
   const name = form.fac_name; 
   const addr = form.fac_addr; 
   const link = form.fac_link; 
   
   if(type.value=="") {
      alert("타입은 필수 선택 사항입니다!");
      type.focus();
      return false;
   }
   if(name.value=="") {
      alert("이름은 필수 입력 사항입니다!");
      name.focus();
      return false;
   }
   if(addr.value=="") {
      alert("주소는 필수 검색 사항입니다!");
      addr.focus();
      return false;
   }
   if(link.value=="") {
      alert("링크는 필수 입력 사항입니다!");
      map.focus();
      return false;
   }
   
   return true;
}


// 취소 버튼
const cancel_btn = document.querySelector(".facreg_cac_btn");

cancel_btn.addEventListener("click", () => {
   let attr_code = document.querySelector("#attr_code").value;
   location.href = `/admin/fac_list?attr_code=${attr_code}`;
});
