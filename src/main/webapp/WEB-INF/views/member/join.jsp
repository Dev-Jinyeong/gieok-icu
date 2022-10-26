<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<jsp:include page="../static/header.jsp" />
<link rel="stylesheet" href="../resources/CSS/member/join.css">
	
    <!-- contents: board list box -->
    <div class="content">
        <!--// terms of use start -->
        <div class="member_terms_box">
            <h1>회원가입 약관</h1>

            <!-- all agree -->
            <div class="all_agree">
                <input type="checkbox" name="all_agree" id="all_agree">
                <label for="all_agree">기억하자 회원가입 약관 전체 동의</label>
            </div>

            <!--1st terms -->
            <div class="terms">
                <input type="checkbox" name="terms1" id="terms_1">
                <label for="terms_1">기억하자 이용약관 동의<strong>(필수)</strong></label>
                <p>
                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Ex, amet fugiat dolorum cupiditate animi
                    suscipit quia voluptatem aut consequuntur facere. Rerum culpa veritatis facilis! Autem perferendis cum
                    repellendus id voluptate fugit nostrum consequuntur excepturi tenetur quis, labore quos? Aperiam dicta
                    adipisci architecto dolore voluptatum neque eaque explicabo! Error doloremque, earum fuga recusandae
                    quaerat magnam culpa pariatur fugit eaque nostrum aliquam excepturi eveniet ea qui optio rem tempora
                    possimus est corporis natus dolore voluptates ipsum quia. Atque eaque nisi sequi illo temporibus
                    maiores, voluptatibus, explicabo corporis vero aspernatur non at deleniti. Est laboriosam dolore aliquid
                    dolor id eos aperiam veniam consequuntur.
                </p>
            </div>

            <!-- 2nd terms -->
            <div class="terms">
                <input type="checkbox" name="terms2" id="terms_2">
                <label for="terms_2">개인정보 수집 및 이용 동의<strong>(필수)</strong></label>
                <p>
                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Ex, amet fugiat dolorum cupiditate animi
                    suscipit quia voluptatem aut consequuntur facere. Rerum culpa veritatis facilis! Autem perferendis cum
                    repellendus id voluptate fugit nostrum consequuntur excepturi tenetur quis, labore quos? Aperiam dicta
                    adipisci architecto dolore voluptatum neque eaque explicabo! Error doloremque, earum fuga recusandae
                    quaerat magnam culpa pariatur fugit eaque nostrum aliquam excepturi eveniet ea qui optio rem tempora
                    possimus est corporis natus dolore voluptates ipsum quia. Atque eaque nisi sequi illo temporibus
                    maiores, voluptatibus, explicabo corporis vero aspernatur non at deleniti. Est laboriosam dolore aliquid
                    dolor id eos aperiam veniam consequuntur.
                </p>
            </div>

            <!-- 3nd terms -->
            <div class="terms">
                <input type="checkbox" name="terms3" id="terms_3">
                <label for="terms_3">위치기반 서비스 이용약관 동의<strong>(선택)</strong></label>
                <p>
                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Ex, amet fugiat dolorum cupiditate animi
                    suscipit quia voluptatem aut consequuntur facere. Rerum culpa veritatis facilis! Autem perferendis cum
                    repellendus id voluptate fugit nostrum consequuntur excepturi tenetur quis, labore quos? Aperiam dicta
                    adipisci architecto dolore voluptatum neque eaque explicabo! Error doloremque, earum fuga recusandae
                    quaerat magnam culpa pariatur fugit eaque nostrum aliquam excepturi eveniet ea qui optio rem tempora
                    possimus est corporis natus dolore voluptates ipsum quia. Atque eaque nisi sequi illo temporibus
                    maiores, voluptatibus, explicabo corporis vero aspernatur non at deleniti. Est laboriosam dolore aliquid
                    dolor id eos aperiam veniam consequuntur.
                </p>
            </div>
            <!-- terms button -->
            <div class="terms_button">
                <input type="button" value="확인" disabled="disabled" class="dis">
                <input type="button" value="취소">
            </div>
        </div>
        <!-- terms of use end //-->

        <!--// user join form start -->
        <form action="/member/joinEmail" method="POST" class="member_join_box" onsubmit="return join_button_check();">
            <h1>회원가입</h1>
            <input type="checkbox" name="user_terms" id="terms_select">
            <!-- member join id -->
            <div class="member_join_id">
                <h2>아이디</h2>
                <input type="text" name="user_id" maxlength="12">
                <input type="button" value="중복확인" onclick="return idDupCheck();">
                <span>영문소문자, 숫자
                    <svg width="10" height="10" viewBox="0 0 10 10" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path d="M1 3.5L4.5 8.5L8.5 1" stroke-width=" 2" stroke-linecap="round" stroke-linejoin="round" />
                    </svg>

                </span>
                <span>4 ~ 12자리
                    <svg width="10" height="10" viewBox="0 0 10 10" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path d="M1 3.5L4.5 8.5L8.5 1" stroke-width=" 2" stroke-linecap="round" stroke-linejoin="round" />
                    </svg>
                </span>
            </div>

            <!-- member join name -->
            <div class="member_join_name">
                <h2>이름</h2>
                <input type="text" name="user_name">
            </div>

            <!-- member join pw -->
            <div class="member_join_pw">
                <h2>비밀번호</h2>
                <input type="password" name="user_pw" maxlength="18">
                <span>소문자
                    <svg width="10" height="10" viewBox="0 0 10 10" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path d="M1 3.5L4.5 8.5L8.5 1" stroke-width=" 2" stroke-linecap="round" stroke-linejoin="round" />
                    </svg>
                </span>
                <span>대문자
                    <svg width="10" height="10" viewBox="0 0 10 10" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path d="M1 3.5L4.5 8.5L8.5 1" stroke-width=" 2" stroke-linecap="round" stroke-linejoin="round" />
                    </svg>
                </span>
                <span>숫자
                    <svg width="10" height="10" viewBox="0 0 10 10" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path d="M1 3.5L4.5 8.5L8.5 1" stroke-width=" 2" stroke-linecap="round" stroke-linejoin="round" />
                    </svg>
                </span>
                <span>특수문자
                    <svg width="10" height="10" viewBox="0 0 10 10" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path d="M1 3.5L4.5 8.5L8.5 1" stroke-width=" 2" stroke-linecap="round" stroke-linejoin="round" />
                    </svg>
                </span>
                <span>8 ~ 18자리
                    <svg width="10" height="10" viewBox="0 0 10 10" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path d="M1 3.5L4.5 8.5L8.5 1" stroke-width=" 2" stroke-linecap="round" stroke-linejoin="round" />
                    </svg>
                </span>
            </div>

            <!-- member join pw2 -->
            <div class="member_join_pw2">
                <h2>비밀번호 확인</h2>
                <input type="password" name="user_pw2" maxlength="18">
                <span>비밀번호 확인
                    <svg width="10" height="10" viewBox="0 0 10 10" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path d="M1 3.5L4.5 8.5L8.5 1" stroke-width=" 2" stroke-linecap="round" stroke-linejoin="round" />
                    </svg>
                </span>
            </div>

            <!-- member join phone -->
            <div class="member_join_phone">
                <h2>연락처</h2>
                <select name="user_mobcarr" id="member_join_phone">
                    <option value="none">통신사</option>
                    <option value="SKT">SKT</option>
                    <option value="KT">KT</option>
                    <option value="LGU">LGU</option>
                    <option value="SAVE">알뜰</option>
                </select>
                <input type="text" name = "user_ph1" maxlength="3">
                <input type="text" name = "user_ph2" maxlength="4">
                <input type="text" name = "user_ph3" maxlength="4">
            </div>

            <!-- member join email -->
            <div class="member_join_email">
                <h2>이메일</h2>
                <input type="text" name="user_email" maxlength="12">
                <span>@</span>
                <input type="text" name="user_domain" maxlength="12">
                <select name="email" id="member_join_email">
                    <option value="">직접입력</option>
                    <option value="gmail.com">구글</option>
                    <option value="naver.com">네이버</option>
                    <option value="kakao.com">카카오</option>
                    <option value="nate.com">네이트</option>
                    <option value="hanmail.net">다음</option>
                </select>
                <input type="button" value="중복확인" onclick="return emailDupCheck();">
            </div>

            <!-- member join button -->
            <div class="member_join_button">
                <input type="submit" value="가입하기">
                <input type="button" value="취소" id="join_cancel_btn">
            </div>
        </form>
        <!-- user join form end //-->
    </div>
    <!-- container section end //-->
	
    
    <script src="../resources/JS/member/join.js"></script>

<jsp:include page="../static/footer.jsp" />
