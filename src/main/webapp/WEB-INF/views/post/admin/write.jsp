<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
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
				<button type="button" id="postAutosave" class="kre_btn">임시저장</button>
				<button type="button" id="postAutosaveListBtn" class="kre_btn">임시저장 목록</button>

				<ol id="postAutosaveList">
				</ol>

				<script>
					var autosaveJsonData = {}

					$(function() {
						$("#postAutosaveListBtn").on("click", function() {
							$("#postAutosaveList").toggle(0);
						});

						getAutosaveList("post").then(function(data) {
							var autosaveList = addAutosaveList(data);

							$("#postAutosaveList").html(autosaveList);
						});

						autosaveInterval();
					});

					$("#postAutosaveList").on("click", ".autosaveListItem", function() {
						var autosaveJson = autosaveJsonData[$(this).attr("data-no")];

						setAutosaveData(autosaveJson);
					});

					$("#postAutosaveList").on("click", ".autosaveListRemoveBtn", function() {
						if(!confirm("삭제된 임시저장은 복구할 수 없습니다.\n삭제하시겠습니까?")) {
							return false;
						}

						var asNo = $(this).attr("data-no");

						removeAutosave(asNo);
					});

					$("#postAutosave").on("click", writeAutosave);

					function autosaveInterval() {
						//5분마다 저장한다.
						setInterval(writeAutosave, 1000 * 60 * 5);
					}

					function writeAutosave() {
						$("#postAutosave").text("저장중...");

						var fileNos = [];

						$(".fileNos").each(function() {
							fileNos.push($(this).val());
						});

						var jsonData = {
							"title": $("#postSubject").val(),
							"content": CKEDITOR.instances.postContent.getData(),
							"fileList": fileNos
						}

						saveAutosave("post", jsonData).then(function(data) {
							var autosaveListItem = addAutosaveItem(data);

							// 20건 이상이면 19개가 되도록 삭제
							if($("#postAutosaveList").children("li").length >= 20) {
								var asNo = $("#postAutosaveList").children("li").first().children(".autosaveListItem").attr("data-no");
								removeAutosave(asNo);
							}

							$("#postAutosaveList").append(autosaveListItem);

							$("#postAutosave").text("임시저장");
						});
					}

					function addAutosaveList(autosaveItems) {
						var autosaveList = "";

						for(var i = 0; i < autosaveItems.length; i++) {
							autosaveList += addAutosaveItem(autosaveItems[i]);
						}

						return autosaveList;
					}

					function addAutosaveItem(autosaveItem) {
						var autosave = JSON.parse(autosaveItem.jsonData);
						var title = autosave.title === "" ? "Untitled" : autosave.title;

						autosaveJsonData[autosaveItem.no] = autosave;

						var autosaveListItem = "<li id='autosaveList" + autosaveItem.no + "' data-no='" + autosaveItem.no + "'>"
						 + "<button id='autosaveListItem" + autosaveItem.no + "' class='autosaveListItem kre_btn' type='button' data-no='" + autosaveItem.no + "'>"
						 + "제목: " + title + " / 시간: " + autosaveItem.time + " / 만료시간: " + autosaveItem.expireTime
						 + "</button>"
						 + "<button class='autosaveListRemoveBtn kre_btn' type='button' data-no='" + autosaveItem.no + "'>"
						 + "삭제"
						 + "</button>"
						 + "</li>";

						return autosaveListItem;
					}

					function setAutosaveData(autosaveJson) {
						$("#postSubject").val(autosaveJson.title);
						CKEDITOR.instances.postContent.setData(autosaveJson.content);

						getFileList(autosaveJson.fileList).then(function(data) {
							removeFileInput();

							for(var i = 0; i < data.length; i++) {
								addFileList(data[i]);
							}
						});
					}

					function removeAutosave(asNo) {
						removeAutosaveList('post', asNo).then(function() {
							$("#autosaveList" + asNo).remove();
						}).catch(function(err) {
							if(err.message !== "") {
								alert(err.message);
								return;
							}

							alert("알 수 없는 오류가 발생했습니다.");
						});
					}

					function getAutosaveList(serviceName) {
						return new Promise(function(resolve, reject) {
							$.ajax({
								url: '${contextPath}/autosave/list/' + serviceName,
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

					function saveAutosave(serviceName, jsonData) {
						return new Promise(function(resolve, reject) {
							$.ajax({
								url: '${contextPath}/autosave/save/' + serviceName,
								type: "post",
								dataType: "json",
								data: {
									jsonData: JSON.stringify(jsonData)
								}
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

					function removeAutosaveList(serviceName, asNo) {
						return new Promise(function(resolve, reject) {
							$.ajax({
								url: '${contextPath}/autosave/remove/' + serviceName + '/' + asNo,
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
			</div>

			<div>
				<input type="text" id="postSubject" class="kre_inp" name="postSubject" value="${post.postSubject}" placeholder="제목">
			</div>
			
			<div>
				<textarea name="postContent" id="postContent" placeholder="내용" rows="10" cols="80">${post.postContent}</textarea>
				<script>
					$(function() {
						CKEDITOR.replace("postContent", {
							filebrowserUploadUrl: '${contextPath}/admin/file/upload/ckeditor/post',
							uploadUrl: '${contextPath}/admin/file/upload/ckeditor/post?responseType=json',
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
								data.message = response[1];
								evt.cancel();
							} else {
								data.url = responseData.url;

								addFileList(responseData);
							}
						});
					});

					function addFileList(file) {
						var postFilesListTemplate = '<li>'
								+ '<a href="${contextPath}' + file.url + '" target="_blank">' + file.fileName + '</a>'
								+ '(' + file.fileSize + ')'
								+ '<button type="button" class="deletePostFile kre_btn" data-key="' + file.fileNo + '">삭제</button>'
								+ '</li>';

						$("#admin_post_write_form").prepend($("<input>", {
							"type": "hidden",
							"class": "fileNos",
							"name": "fileNos",
							"value": file.fileNo
						}));

						$("#postFiles > ul").append(postFilesListTemplate);
					}

					function addFileContent(files) {
						var fileHtml = '';

						for( var i in files) {
							fileHtml += '<p><a href="${contextPath}/file/download/post/' + files[i].fileNo + '" class="fileLink">' + files[i].fileName + '</a></p>';
						}

						CKEDITOR.instances["postContent"].insertHtml(fileHtml, 'unfiltered_html');
					}
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
					<c:if test="${files != null}">
					<c:forEach var="file" items="${files}">
						<li>
							<a href="${contextPath}/file/viewer/post/${file.fileNo}" target="_blank">${file.fileName}</a>
							(${file.fileSize})
							<button type="button" class="deletePostFile kre_btn" data-key="${file.fileNo}">삭제</button>
						</li>
					</c:forEach>
					</c:if>
				</ul>

				<div>
					<input type="file" id="uploadFiles" multiple><button type="button" id="fileUploadBtn" class="kre_btn">전송</button>
					<script>
						$("#fileUploadBtn").on("click", function() {
							var uploadFiles = $("#uploadFiles")[0];
							var formData = new FormData();

							for(var i = 0; i < uploadFiles.files.length; i++) {
								formData.append("uploadFiles", uploadFiles.files[i]);
							}

							$.ajax({
								url: '/admin/file/upload/post',
								processData: false,
								contentType: false,
								data: formData,
								type: 'POST'
							}).done(function(data) {
								var files = data.value;

								for(var i in files) {
									addFileList(files[i]);
								}

								addFileContent(files);

								$("#uploadFiles").val("");
							}).fail(function() {
								console.log("error");
							});
						});

						function getFileList(fileNos) {
							return new Promise(function(resolve, reject) {
								$.ajax({
									url: '${contextPath}/admin/file/list/post',
									type: "post",
									dataType: "json",
									data: {
										fileNos: fileNos
									}
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

						function removeFileInput() {
							$(".fileNos").remove();
							$("#postFiles > ul").empty();
						}
					</script>
				</div>
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