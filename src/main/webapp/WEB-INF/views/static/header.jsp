<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <!--// link css src -->
    <link rel="icon" href="/resources/images/Sea_favicon.ico">
    <link rel="stylesheet" href="/resources/CSS/static/header.css">
    <!-- link css src //-->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Vampiro+One&display=swap" rel="stylesheet">
    
    <!--// summernote -->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>

    <script src="../resources/summernote/JS/summernote-ko-KR.js"></script>
    <script src="../resources/summernote/JS/summernote-lite.js"></script>
    <link rel="stylesheet" href="../resources/summernote/CSS/summernote-lite.css">
    <!-- summernote //-->

</head>

<body>
    <!--// header section start -->
    <div class="background_image"></div>
    <header class="header">
        <!-- logo box -->
        <div class="header_logo">
            <a href="/"></a>
        </div>
        <!-- menu box -->
        <div class="header_menu">
            <ul>
                <li><a href="/member/map">어디 갈래</a></li>
                <li><a href="#">여행 일정</a></li>
                <li><a href="/board_list">소통 하자</a></li>
                <li><a href="#">기억 해줘!</a></li>
            </ul>
            <!-- underline -->
            <div class="menu_underline"></div>
        </div>
        <!-- member stat box -->
        <div class="header_member_stat">
        
            <!-- member profile image -->
            
            
            <c:if test="${empty sessionScope.id }">
	            <!-- non-member menu -->
            	<a href="#" class="member_image"></a>
            	<input type="checkbox" id="member_name">
	            <label for="member_name" class="member_name">로그인 / 회원가입</label>
	            <div class="menu_non_member">
	                <ul>
	                    <li>
	                        <a href="/member/login">로그인</a>
	                    </li>
	                    <li>
	                        <a href="/member/join">회원 가입</a>
	                    </li>
	                </ul>
	            </div>
	           
            </c:if>
            
        	<c:if test="${!empty sessionScope.id}">

	            <!-- member login status -->
	            <a href="/member/inform_edit" class="member_image"><img src="/resources/upload/profile/${sessionScope.profile }" width="50" height="50"></a>
	            <input type="checkbox" id="member_name">
 	            <label for="member_name" class="member_name">${sessionScope.name }님</label>
	            
	            <c:if test="${sessionScope.grade=='m' }">
		            <div class="menu_member">
		                <ul>
		                    <li>
		                        <a href="#">내 일정 관리</a>
		                    </li>
		                    <li>
		                        <a href="#">내 메시지</a>
		                    </li>
		                    <li>
		                        <a href="/member/logout">로그아웃</a>
		                    </li>
		                </ul>
		            </div>
	            </c:if>
	            
	            <c:if test="${sessionScope.grade!='m' }">
		            <div class="menu_admin">
		                <ul>
		                    <li>
		                        <a href="#">어드민 관리</a>
		                    </li>
		                    <li>
		                        <a href="/board_list">게시판 관리</a>
		                    </li>
		                    <li>
		                        <a href="/admin/attr_list">명소 관리</a>
		                    </li>
		                    <li>
		                        <a href="/board_with_list">동행 관리</a>
		                    </li>
		                    <li>
		                        <a href="#">메시지</a>
		                    </li>
		                    <li>
		                        <a href="/member/logout">로그아웃</a>
		                    </li>
		                </ul>
		            </div>
	            </c:if>
            </c:if>
        </div>
    </header>
    <!-- header section end //-->


