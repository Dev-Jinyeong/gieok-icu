<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../static/header.jsp" />
<c:if test="${(sessionScope.grade == 'a' || sessionScope.grade=='s')}">
	<jsp:include page="../static/sidebar.jsp" />
</c:if>
<c:if test="${(sessionScope.grade == 'm' || empty sessionScope.grade)  }">
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
                
                <span class="board_date">기간
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
                <textarea id="with_board_content" class="summernote" name="board_content" maxlength="50"></textarea>
                <br />
				<span style="color:#aaa; right: 20px;" id="with_con_counter">0</span>
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
    /* $(document).ready(function() { */
        $('.summernote').summernote({
            width: 800,
            height: 300,
            minHeight: 350,
            maxHeight: 350,
            focus: false,
            lang: "ko-KR",
            placeholder: '이곳에 내용을 입력하세요! :)',
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
			fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋움체','바탕체'],
			fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72']
        });
    /* }); */
    </script>
    <!-- summernote // -->
    
<script src="/resources/JS/member/board_with_write.js"></script>
<jsp:include page="../static/footer.jsp"/>

