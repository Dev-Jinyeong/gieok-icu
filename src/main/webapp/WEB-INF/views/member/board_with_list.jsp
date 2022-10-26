<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../static/header.jsp" />
<c:if test="${(sessionScope.grade == 'a' || sessionScope.grade=='s')}">
	<jsp:include page="../static/sidebar.jsp" />
</c:if>
<c:if test="${sessionScope.grade == 'm'  }">
	<!-- 회원용 리모컨 -->
</c:if>
<link rel="stylesheet" href="../resources/CSS/static/header.css">
<link rel="stylesheet"
	href="../resources/CSS/member/board_with_list.css">


<!--// contents section start -->
<section class="content">

	<!-- contents: board list box -->
	<div class="board_with">
		<form type="post" name="board_with" action="">
		
			<input type="hidden" value="${page}" id="page" name="page"/>
			<input type="hidden" value="${category}" id="category" name="category" />
			<input type="hidden" value="${keyword}" id="keyword" name="keyword" />
			
			<div class="board_top">
				<h1>동행 등록</h1>
				<input type="button" value="등록" class="add_contents"
					onclick="location.href=`/board_with_write?page=${page}&category=${category}&keyword=${keyword}`">
			</div>

			<div class="board_with_list">
				<ul class="with_list">
				<c:if test="${empty with_li_list}">
					<div class="li_none">
						등록된 동행이 없습니다😭
					</div>
				</c:if>
				<c:if test="${!empty with_li_list}">
					<c:forEach var="with" items="${with_li_list}">
	 					<li>
							<span class="singo">신고</span> 
							<span class="sel_check">
								<input type="checkbox" name="with_checkbox" value="${with.board_no}">
							</span>
							<div class="li_box">
								<h2>${with.board_title}</h2>
								<div>
									<p>
									<b>작성자 :</b>&nbsp
									<span><img alt="Profile"
										src="/resources/upload/profile/default_profile.png"></img>
										${with.board_writer}
									</span>
									&nbsp.&nbsp
									<b>장소 :</b>&nbsp
									<span>${with.board_location}</span>
									</p>
									<p>
									&nbsp.&nbsp
									<b>기간 :</b>&nbsp
									<span>${with.board_startDay} ~ ${with.board_endDay}</span>
									&nbsp.&nbsp
									<b>모집인원 :</b>&nbsp
									<span>${with.board_memCount}</span>
									</p>
								</div>
	
								<textarea readonly>${with.board_content}</textarea>
	
								<input type="button" value="신청하기">
							</div>
						</li>			
					</c:forEach>
				</c:if>
				</ul>
			</div>
			<!-- event end -->
			<button class="with_end_button">선택 동행 종료</button>
		</form>
		<!-- // bottom -->
		<div class="board_last">
		
			<!-- board list page number -->
			<div class="board_pagination">
				<div class="prev">
					<c:if test="${page == 1}">
						<span>처음</span>
					</c:if>
					<c:if test="${page > 1}">
						<a href="/board_with_list?page=1&category=${category}&keyword=${keyword}">
							<span>처음</span>
						</a>
					</c:if>
					<c:if test="${page < 11}">
						<span>이전</span>
					</c:if>
					<c:if test="${page >= 11}">
						<a href="/board_with_list?page=${startPage - 10}&category=${category}&keyword=${keyword}">
							<span>이전</span>
						</a>
					</c:if>
				</div>
				<ul class="board_page">
					<c:forEach var="p" begin="${startPage}" end="${endPage}" step="1">
						<c:if test="${p == page}">
							<li>${p}</li>
						</c:if>
						<c:if test="${p != page}">
							<li>
								<a href="/board_with_list?page=${p}&category=${category}&keyword=${keyword}">
									${p}
								</a>
							</li>
						</c:if>	
					</c:forEach>
				</ul>
				<div class="next">
					<c:if test="${(startPage + 10) > totalPage}">
						<span>다음</span>
					</c:if>
					<c:if test="${(startPage + 10) <= totalPage}">
						<a href="/board_with_list?page=${startPage + 10}&category=${category}&keyword=${keyword}">
							<span>다음</span>
						</a>
					</c:if>
					<c:if test="${page == totalPage}">
						<span>마지막</span>
					</c:if>			
					<c:if test="${page > totalPage || page==1}">
						<a href="/board_with_list?page=${totalPage}&category=${category}&keyword=${keyword}">
							<span>마지막</span>
						</a>
					</c:if>
				</div>
			</div>

			<!-- search, event -->
			<div class="board_bottom">
				<!-- search -->
				<form action="" class="board_src">
					<select name="category" class="with_category">
						<option value="" selected>선택</option>
						<option value="board_writer" <c:if test="${category == 'board_writer'}"> selected</c:if>>작성자</option>
						<option value="board_title" <c:if test="${category == 'board_title'}"> selected</c:if>>제목</option>
						<option value="board_location" <c:if test="${category == 'board_location'}"> selected</c:if>>지역 / 명소</option>
						<option value="board_content" <c:if test="${category == 'board_content'}"> selected</c:if>>내용</option>
					</select>
					<input class="search_field" type="text" name="keyword" maxlength="10" placeholder="검색어">
					<input class="search_cont" type="submit" value="검색">
				</form>

			</div>
		</div>
	</div>

</section>
<!-- container section end //-->


<jsp:include page="../static/footer.jsp" />
