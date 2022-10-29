<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 <!-- ***** header ***** -->
<jsp:include page="../static/header.jsp" />
<c:if test="${(sessionScope.grade == 'a' || sessionScope.grade=='s')}">
	<jsp:include page="../static/sidebar.jsp" />
</c:if>
<c:if test="${sessionScope.grade == 'm'  }">
	<!-- 회원용 리모컨 -->
</c:if>

<link type="text/css" rel="stylesheet" href="../resources/CSS/admin/report_list.css">

		 <!-- contents: board list box -->
    <div class="content">
        <div class="report_list_box">
            <h1 class="report_title">신고 목록</h1>

            <div class="report_col">
                <span>번호</span>
                <span>신고 계정</span>
                <span>신고 유형</span>
                <span>게시글/리뷰</span>
                <span>피신고 계정</span>
            </div>

            <div class="line_s"></div>

            <ul class="report_list">
                <li>
                    <a href="">
                        <span>1</span>
                        <span>member</span>
                        <span>부적절한 내용(게시물/사진)</span>
                        <span>게시물 제목 제목ㅁ목제목제목</span>
                        <span>badmember</span>
                    </a>
                </li>
                <li>
                    <a href="">
                        <span>1</span>
                        <span>member</span>
                        <span>부적절한 내용(게시물/사진)asdf</span>
                        <span>게시물 제목 제목ㅁ목제목제dsafsdfsa목</span>
                        <span>badmember</span>
                    </a>
                </li>
                <li>
                    <a href="">
                        <span>1</span>
                        <span>member</span>
                        <span>부적절한 내용(게시물/사진)</span>
                        <span>게시물 제목 제목ㅁ목제목제목</span>
                        <span>badmember</span>
                    </a>
                </li>
                <li>
                    <a href="">
                        <span>1</span>
                        <span>member</span>
                        <span>부적절한 내용(게시물/사진)</span>
                        <span>게시물 제목 제목ㅁ목제목제목</span>
                        <span>badmember</span>
                    </a>
                </li>
                <li>
                    <a href="">
                        <span>1</span>
                        <span>member</span>
                        <span>부적절한 내용(게시물/사진)</span>
                        <span>게시물 제목 제목ㅁ목제목제목</span>
                        <span>badmember</span>
                    </a>
                </li>
                <li>
                    <a href="">
                        <span>1</span>
                        <span>member</span>
                        <span>부적절한 내용(게시물/사진)</span>
                        <span>게시물 제목 제목ㅁ목제목제목</span>
                        <span>badmember</span>
                    </a>
                </li>
                <li>
                    <a href="">
                        <span>1</span>
                        <span>member</span>
                        <span>부적절한 내용(게시물/사진)</span>
                        <span>게시물 제목 제목ㅁ목제목제목</span>
                        <span>badmember</span>
                    </a>
                </li>
                <li>
                    <a href="">
                        <span>1</span>
                        <span>member</span>
                        <span>부적절한 내용(게시물/사진)</span>
                        <span>게시물 제목 제목ㅁ목제목제목</span>
                        <span>badmember</span>
                    </a>
                </li>
                <li>
                    <a href="">
                        <span>1</span>
                        <span>member</span>
                        <span>부적절한 내용(게시물/사진)</span>
                        <span>게시물 제목 제목ㅁ목제목제목</span>
                        <span>badmember</span>
                    </a>
                </li>
                <li>
                    <a href="">
                        <span>1</span>
                        <span>member</span>
                        <span>부적절한 내용(게시물/사진)</span>
                        <span>게시물 제목 제목ㅁ목제목제목</span>
                        <span>badmember</span>
                    </a>
                </li>
            </ul>

            <div class="line_s"></div>

            <div class="report_page">
                <span>시작</span>
                <span>이전</span>

                <ul class="page">
                    <li><a href="">1</a></li>
                    <li><a href="">2</a></li>
                    <li><a href="">3</a></li>
                    <li><a href="">4</a></li>
                    <li><a href="">5</a></li>
                    <li><a href="">6</a></li>
                    <li><a href="">7</a></li>
                    <li><a href="">8</a></li>
                    <li><a href="">9</a></li>
                    <li><a href="">10</a></li>
                </ul>

                <span>다음</span>
                <span>마지막</span>
            </div>

            <div class="report_box_button">
                <input type="button" value="뒤로">
            </div>
        </div>

    </div>
    <!-- container section end //-->
<!-- 이미지 업로드 -->

<!-- ***** footer ***** -->
<jsp:include page="../static/footer.jsp" />