"use strict";

let background_image = document.querySelector(".background_image");
let contents = document.querySelector(".contents");
let search_box = document.querySelector(".search_box");
let search_text = document.querySelector(".search_box>h1");
let search_svg = document.querySelector(".search_box>form>label>svg");

/*full screen 떄문에 추가함 일단~ */
let viewport = document.querySelector(".viewport");
let banner = document.querySelector(".banner");

function wheelAction() {
    window.addEventListener("wheel", (event) => {
        let wheelAction = event.deltaY;

        if (wheelAction > 0) {
            // 휠 업
            background_image.style.backgroundPositionY = "-700px";
            search_box.style.top = "calc(15vh)";
            contents.style.bottom = "0vh";

            if (window.innerWidth <= 1920) {
                background_image.style.height = "300px";

                search_box.style.top = "115px";
                // search_text.style.fontSize = "0px";
                // search_svg.style.top = "100px";
            } else {
                background_image.style.height = "calc(100vh - 600px)";
            }
        } else if (wheelAction < 0) {
            // 휠 다운
            background_image.style.height = "100vh";

            background_image.style.backgroundPositionY = "-35vh";
            search_box.style.top = "calc(50vh - 220px / 2)";
            contents.style.bottom = "-100vh";

            if (window.innerWidth <= 1920) {
                search_box.style.top = "calc(50vh - 220px / 2)";
                search_text.style.fontSize = "60px";
                search_svg.style.top = "175px";
            } else {
            }
        }
    });
}

wheelAction();
/*change full screen*/


function keyword() {
let search_value = document.querySelector('#search').value;
	if(search_value == ""){
		alert("검색어를 입력해주세요!");
		return false;
	}
}

