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

								var postFilesListTemplate = '<li>'
									+ '<a href="${contextPath}' + responseData.url + '" target="_blank">' + responseData.fileName + '</a>'
									+ '(' + responseData.fileSize + ')'
									+ '<button type="button" class="deletePostFile kre_btn" data-key="' + responseData.fileNo + '">삭제</button>'
									+ '</li>';

								$("#admin_post_write_form").prepend($("<input>", {
									"type": "hidden",
									"name": "fileNos",
									"value": responseData.fileNo
								}));

								$("#postFiles > ul").append(postFilesListTemplate);
							}
						});
					});
				</script>
			</div>
			
			<c:if test="${shortUrl != null}">
			<div id="shortUrl">
				shortedUrl : <a href="${contextPath}/s/${shortUrl.encodedUrl}" target="_blank">https://www.kurien.net/s/${shortUrl.encodedUrl}</a><br>
				realURL: ${shortUrl.realUrl}
			</div>
			</c:if>
			
			<div id="postFiles">
				<ul>
					<if test="${files != null}">
					<c:forEach var="file" items="${files}">
						<li>
							<a href="${contextPath}/file/viewer/post/${file.fileNo}" target="_blank">${file.fileName}</a>
							(${file.fileSize})
							<button type="button" class="deletePostFile kre_btn" data-key="${file.fileNo}">삭제</button>
						</li>
					</c:forEach>
					</if>
				</ul>
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


	$("#postFiles").on("click", ".deletePostFile", function() {
		var that = this;

		if(confirm("해당 파일을 삭제하시겠습니까?") === false) {
			return;
		}

		var deleteFileNo = $(that).data("key");

		deletePostFile(deleteFileNo).then(function() {
			alert("파일이 삭제되었습니다.");
			$(that).closest("li").remove();

			//이미지 삭제 후 재추가
			var removeImgPattern = new RegExp("<img.*?[\'|\"]\/file\/viewer\/post\/" + deleteFileNo + "[\'|\"].*?>");
			removeImgPattern.global = true;

			var replaceEditorData = CKEDITOR.instances["postContent"].getData().replace(removeImgPattern, "");
			CKEDITOR.instances["postContent"].setData(replaceEditorData);
		}).catch(function(err) {
			if(err.message !== "") {
				alert(err.message);
				return;
			}

			alert("알 수 없는 오류가 발생했습니다.");
		});
	});

	function deletePostFile(key) {
		return new Promise(function(resolve, reject) {
			$.ajax({
				url: '${contextPath}/admin/post/deleteFile/' + key,
				type: "post",
				dataType: "json"
			}).done(function(data) {
				if(data.result === "fail") {
					reject(new Error(data.message));
					return;
				}

				resolve(data.value); // true or false
			}).fail(function(err) {
				reject(err);
			});
		});
	}
</script>