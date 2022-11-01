<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../static/header.jsp" />
<c:if test="${(sessionScope.grade == 'a' || sessionScope.grade=='s')}">
	<jsp:include page="../static/sidebar.jsp" />
</c:if>
<c:if test="${(sessionScope.grade == 'm' || empty sessionScope.grade)  }">
	<jsp:include page="../static/board_sidebar.jsp"/>
	<!-- 회원용 리모컨 -->
</c:if>
<link rel="stylesheet" href="./resources/CSS/static/header.css">
<link rel="stylesheet" href="./resources/CSS/member/board_with_write.css">

	<div class="content">
        <!-- contents: board list box -->
        <form method="post" name="board_with" class="board_with" action="/board_with_write" onsubmit="return formcheck();">
            
			<input type="hidden" value="${page}" id="page" name="page"/>
			<input type="hidden" value="${category}" id="category" name="category" />
			<input type="hidden" value="${keyword}" id="keyword" name="keyword" />
            
            <div class="board_type">
                <h1>동행 등록</h1>
            </div>
            <div class="board_title">
            	
            	<!-- ***** 도 선택 ***** -->
                <select id="province" class="province" name="province_id">
                        <option disabled selected>도 선택</option>
                </select>
                <input type="hidden" id="with_province_name" name="with_province_name">
                
				<!-- ***** 시 선택 ***** -->	 
                <select id="city" name="city_code">
                        <option disabled selected>시 선택</option>
                </select>
                <input type="hidden" id="with_city_name" name="with_city_name">

				<!-- ***** 명소 선택 ***** -->
                <select id="attraction" name="attr_code">
                        <option disabled selected>명소 선택</option>
                </select>
                <input type="hidden" id="with_attr_name" name="with_attr_name">
                
                <span class="board_date">모집기간
                    <input type="date" id="withStartDay" class="withStartDay" name="board_startDay" type="date" >
                    ~
                    <input type="date" id="withEndDay"class="withEndDay" name="board_endDay" type="date" >
                </span>

                <span class="board_people">모집인원
                    <select name="board_memCount" id="memCount" class="board_memCount">
                        <c:forEach var="count" begin="0" end="20" step="1">
                         <option value="${count}">${count}</option>
                        </c:forEach>
                    </select>
                     명
                </span>
            </div>
            <div class="board_title_cont">
                <input type="text" id="with_board_title" class="with_board_title" name="board_title" placeholder="제목">
            </div>
            <div class="board_content">
            	<div id="checktxt" class="checktxt">글자수 : 0 / 100</div>
                <textarea id="with_board_content" class="summernote" name="board_content" maxlength="50"></textarea>
            </div>
            <div class="board_button">
                <input type="submit" value="등록">
                <input type="button" id="with_cancel" value="취소">
            </div>
            
        </form>
    </div>
    <!-- container section end //-->

    <!-- // summernote -->
    <script>
    $(document).ready(function() {
    	//에디터 설정
    	$('.summernote').summernote({
         width: 800,
         height: 300,
         minHeight: 350,
         maxHeight: 350,
         focus: false,
         lang: "ko-KR",
         placeholder: 'ㅇㅇ월 ㅇㅇ일 ㅇㅇ시 여행가실분 구해요~!😊',
         toolbar: [
			    // [groupName, [list of button]]
			    ['fontNames', ['fontname']],
			    ['fontSizes', ['fontsize']],
			    ['style', ['bold', 'italic', 'underline','strikethrough'/* , 'clear' */]],
			    ['color', ['forecolor','color']],
			    /* ['table', ['table']], */
			    /* ['para', ['ul', 'ol', 'paragraph']], */
			    /* ['height', ['height']] */
/* 			    ['insert',['picture','link','video']],
			    ['view', ['fullscreen', 'help']] */
			  ],
			fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋음체','바탕체'],
			fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72'],
	    	callbacks: {
	            onChange:function(contents, $editable){ //텍스트 글자수 및 이미지등록개수
	                setContentsLength(contents, 0);
	            }
	        }
		});

        //$('#summernote').summernote('code', "글 수정시 데이터");

    	//글자수 체크
        //태그와 줄바꿈, 공백을 제거하고 텍스트 글자수만 가져옵니다.
    	function setContentsLength(str, index) {
    	    var status = false;
    	    var textCnt = 0; //총 글자수
    	    var maxCnt = 100; //최대 글자수
    	    var editorText = f_SkipTags_html(str); //에디터에서 태그를 삭제하고 내용만 가져오기
    	    editorText = editorText.replace(/\s/gi,""); //줄바꿈 제거
    	    editorText = editorText.replace(/&nbsp;/gi, ""); //공백제거

            textCnt = editorText.length;
    	    if(maxCnt > 0) {
            	if(textCnt > maxCnt) {
                    status = true;
            	}
    	    }

    	    if(status) {
            	var msg = "등록오류 : 글자수는 최대 "+maxCnt+"까지 등록이 가능합니다. / 현재 글자수 : "+textCnt+"자";
            	alert(msg);
    	    }
    	    
    	    // 글자수 카운터
    	  	const checktxt = document.getElementById("checktxt");
    	  	checktxt.textContent = "글자수 : " + textCnt + "/" + maxCnt + "자";
    	  		
    	} // setContentsLength()
    	
    });
    
  	//에디터 내용 텍스트 제거
    function f_SkipTags_html(input, allowed) {
    	// 허용할 태그는 다음과 같이 소문자로 넘겨받습니다. (<a><b><c>)
        allowed = (((allowed || "") + "").toLowerCase().match(/<[a-z][a-z0-9]*>/g) || []).join('');
        var tags = /<\/?([a-z][a-z0-9]*)\b[^>]*>/gi,
        commentsAndPhpTags = /<!--[\s\S]*?-->|<\?(?:php)?[\s\S]*?\?>/gi;
        return input.replace(commentsAndPhpTags, '').replace(tags, function ($0, $1) {
            return allowed.indexOf('<' + $1.toLowerCase() + '>') > -1 ? $0 : '';
        });
    }
  	

    </script>
    <!-- summernote // -->
    
<script src="/resources/JS/member/board_with_write.js"></script>
<jsp:include page="../static/footer.jsp"/>

