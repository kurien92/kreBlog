<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="${contextPath}/js/plugin/ckeditor/ckeditor.js"></script>
<section id="admin_post_write" class="post admin">
	<h3 class="section_subject">Post Write</h3>
	
	<form id="admin_post_write_form" action="${contextPath}/admin/post/${formAction}" method="post">
		<c:if test="${post != null}">
			<input type="hidden" name="postNo" value="${post.postNo}">
		</c:if>
		
		<div class="kre_writing">
			<div>
				<select class="kre_inp" name="categoryId">
					<option value="">Uncategorized</option>
					
					<c:forEach var="category" items="${categories}">
						<option value="${category.categoryId}" ${post.categoryId eq category.categoryId ? "selected" : ""}>${category.categoryName}</option>v
					</c:forEach>
				</select>
			</div>
			
			<div>
				<input type="text" id="postSubject" class="kre_inp" name="postSubject" value="${post.postSubject}" placeholder="제목">
			</div>
			
			<div>
				<textarea name="postContent" id="postContent" placeholder="내용" rows="10" cols="80">${post.postContent}</textarea>
				<script>
					$(function() {
						CKEDITOR.replace("postContent", {
							filebrowserUploadUrl: '${contextPath}/admin/file/upload/post',
							uploadUrl: '${contextPath}/admin/file/upload/post?responseType=json',
							contentsCss: "${contextPath}/css/plugin/ckeditor.css",
							height: '500px',
						    on: {
						        loaded: function( evt ) {
				    				resetScroll();
						        }
						    }
						});

						CKEDITOR.instances["postContent"].on('fileUploadResponse', function( evt ) {
							// Prevent the default response handler.
							evt.stop();
	
							// Get XHR and response.
							var data = evt.data,
								xhr = data.fileLoader.xhr,
								response = xhr.responseText.split( '|' );
	
							var responseData = JSON.parse(response[0]);
	
							if(responseData.uploaded !== 1) {
								// An error occurred during upload.
								data.message = response[ 1 ];
								evt.cancel();
							} else {
								data.url = responseData.url;
	
								$("#admin_post_write_form").prepend($("<input>", {
									"type": "hidden",
									"name": "fileNos",
									"value": responseData.fileNo
								}));
							}
						});
					});
				</script>
			</div>
			
			<div>
				<label><input type="checkbox" name="postView" value="TRUE" ${post.postView=="TRUE"?"checked":""}> 공개</label>
			</div>
			
			<div>
				<label><input type="checkbox" name="postPublish" value="TRUE" ${post.postPublish=="TRUE"?"checked":""}> 예약발행</label>
			</div>
			
			<div>
				<label>예약일시 <input type="text" class="kre_inp" name="postReservationTime" value="<fmt:formatDate value="${post.postReservationTime}" pattern="yyyy-MM-dd HH:mm:ss" />"></label>
			</div>
		</div>
            
		<div class="kre_btn_list">
			<ul class="kre_btn_list_left">
				<li><a href="${contextPath}/admin/post/list" class="kre_btn">목록</a></li>
				<li><button id="post_submit" type="submit" class="kre_btn">${formSubmit}</button></li>
			</ul>
		</div>
	</form>
</section>
<script>
	$(window).on("beforeunload", function() {
		return "작성중인 글이 존재합니다. 페이지를 나가시겠습니까?";
	});

	$("#admin_post_write_form").on("submit", function() {
        $(window).off("beforeunload");
    });
</script>