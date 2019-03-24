<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="${contextPath}/js/plugin/ckeditor/ckeditor.js"></script>
<section id="admin_post_write" class="post admin">
	<h3 class="section_subject">Post Write</h3>
	
	<form action="${contextPath}/admin/post/${formAction}" method="post">
		<c:if test="${post != null}">
			<input type="hidden" name="postNo" value="${post.postNo}">
		</c:if>
		
		<div class="kre_writing">
			<div>
				<input type="text" name="postSubject" value="${post.postSubject}" placeholder="제목">
			</div>
			
			<div>
				<textarea name="postContent" id="postContent" placeholder="내용" rows="10" cols="80">${post.postContent}</textarea>
			</div>
			
			<div>
				<label><input type="checkbox" name="postView" value="TRUE" ${post.postView=="TRUE"?"checked":""}> 공개</label>
			</div>
			
			<div>
				<label><input type="checkbox" name="postPublish" value="TRUE" ${post.postPublish=="TRUE"?"checked":""}> 발행</label>
			</div>
			
			<div>
				<label><input type="text" name="postReservationTime" value="<fmt:formatDate value="${post.postReservationTime}" pattern="yyyy-MM-dd HH:mm:ss" />"> 예약일시</label>
			</div>
		</div>
            
		<div class="kre_btn_list">
			<ul class="kre_btn_list_left">
				<li><a href="${contextPath}/admin/post/list" class="kre_btn">목록</a></li>
				<li><button type="submit" class="kre_btn">${formSubmit}</button></li>
			</ul>
		</div>
	</form>
</section>

<script>
	$(window).on("load", function() {
		CKEDITOR.replace("postContent");
	});
</script>