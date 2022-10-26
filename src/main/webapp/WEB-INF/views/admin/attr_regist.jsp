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


<link type="text/css" rel="stylesheet" href="../resources/CSS/admin/attr_regist.css">

		<!-- 관리자 > 관광 명소 등록 container -->
		<div class="content">
			
			<!-- 관광 명소 등록 -->
			<div class="attregist_container">
				<div class="attregist_default">
					<form action="/admin/attr_regist" method="POST" enctype="multipart/form-data" name="attr_regist" onsubmit="return formValidate();">
						<input type="hidden" value="${attr_code }" id="attr_code">
						<!-- title -->
						<div class="attreg1">
							<label class="attreg_title wf-gangwonTT">명소등록</label>
						</div>
						<!-- 제목 -->
						<div class="attreg2">
							<input type="text" name="attr_name" class="attreg_inp_title" placeholder="명소 이름" maxlength="20">
						</div>
						<!-- 주소 검색 -->
						<div class="attreg3 flex_row">
							<select id="province" name="province_id">
								<option value="" disabled selected>도 선택</option>
								<option value="Tseoul">서울특별시</option>
								<option value="Tgyeonggi">경기도</option>
								<option value="Tincheon">인천광역시</option>
								<option value="Tgangwon">강원도</option>
								<option value="Tchungbuk">충청북도</option>
								<option value="Tchungnam">충청남도</option>
								<option value="Tsejong">세종특별자치시</option>
								<option value="Tdaejeon">대전광역시</option>
								<option value="Tgyeongbuk">경상북도</option>
								<option value="Tdaegu">대구광역시</option>
								<option value="Tgyeongnam">경상남도</option>
								<option value="Tulsan">울산광역시</option>
								<option value="Tbusan">부산광역시</option>
								<option value="Tjeonbuk">전라북도</option>
								<option value="Tjeonnam">전라남도</option>
								<option value="Tgwangju">광주광역시</option>
								<option value="Tjeju">제주특별자치도</option>
							</select>
							<select id="city" name="city_code">
								<option disabled selected>시 선택</option>
							</select>
							<input type="text" id="attreg_inp_addr" name="attr_addr" class="attreg_inp_addr" placeholder="주소" maxlength="" readonly>
							<input type="button" id="attreg_inp_addrbtn" class="attreg_inp_addrbtn" value="검색" onclick="find_addr()">
						</div>
						<!-- 내용 -->
						<div class="attreg4 flex_row">
							<!-- 내용 입력 -->
							<div class="inp_cont flex_column">
								<input type="text" name="attr_link" class="attreg_inp_cont" maxlength="" placeholder="홈페이지 링크">
								<input type="text" name="attr_map" class="attreg_inp_cont" maxlength="" placeholder="네이버 지도 링크">
								<textarea name="attr_info" class="attreg_inp_textarea" placeholder="설명"></textarea>
							</div>
							<!-- 이미지 업로드 -->
							<div class="attreg_inp_img flex_column">
								<div class="attr_inp_imgcon1 flex_column">
								<!-- 명소 main 이미지 -->
									<label class="preview_title">미리보기</label>	
									<label class="preview_size">500px * 300px</label>
									<img id="preview_img1" class="preview_img" src="#" onerror="this.src='../resources/images/attraction/no_image.png'"/>
									<img id="preview_img2" class="preview_img" src="#" onerror="this.src='../resources/images/attraction/no_image.png'"/>
									<label class="preview_btn" for="pre_img">사진 등록</label>
									<input type="file" id="pre_img" accept="image/*" name="images" onchange="preview1()" multiple>
								</div>
								<div class="attr_inp_imgcon2 flex_column">
									<!-- main 페이지 thumb 이미지 -->
									<label class="preview_size">150px * 40px</label>
									<img id="preview_thimg" class="preview_thimg" src="#" onerror="this.src='../resources/images/attraction/no_image.png'"/>
									<label class="preview_btn" for="pre_thimg">사진 등록</label>
									<input type="file" id="pre_thimg" accept="image/*" name="photo" onchange="preview2()"/>
								</div>
							</div>
						</div>
						<!-- 하단 버튼 -->
						<div class="attreg5 flex_row">
							<input type="submit" name="" class="attreg_reg_btn" value="등록">
							<input type="button" name="" class="attreg_cac_btn" value="취소">
						</div>
					</form>
				</div>
			</div> <!-- class="attregist_container" -->
		</div> <!-- class="admin_container" -->
	</div>
<!-- 주소 api -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<!-- 이미지 업로드 -->
<script type="text/javascript" src="../resources/JS/admin/attr_regist.js"></script>

<!-- ***** footer ***** -->
<jsp:include page="../static/footer.jsp" />