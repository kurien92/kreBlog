<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<section id="post_view" class="post">
	<h3 class="section_subject">Post View</h3>
	
	<article class="kre_article">
		<header class="kre_article_header">
			<h4 class="kre_article_subject">${post.postSubject}</h4>
			<div class="kre_article_info">
				<ul>
					<li><strong>Author</strong> ${post.postAuthor}</li>
					<c:if test="${post.categoryId != ''}">
						<li>
							<strong>Category</strong>
							<a href="${contextPath}/category/${post.categoryId}" class="kre_post_category">${category.categoryName}</a>
						</li>
					</c:if>
					<li><strong>Registration Time</strong> <fmt:formatDate value="${post.postWriteTime}" pattern="yyyy/MM/dd HH:mm" /></li>
				</ul>
				
				<div class="kre_btn_list">
					<ul class="kre_btn_list_left">
						<c:if test="${shortUrl != null}">
						<li><button id="copyUrl" type="button" class="kre_btn" data-clipboard-text="https://www.kurien.net/s/${shortUrl.encodedUrl}">Link Copy</button></li>
						</c:if>
						<sec:authorize access="hasRole('ROLE_ADMIN')">
							<li><a href="${contextPath}/admin/post/modify/${post.postNo}" class="kre_btn">Modify</a></li>
						</sec:authorize>
					</ul>
				</div>
			</div>
		</header>

		<div class="kre_article_body">
			<div id="kre_article_ad1" class="adsense">
				<script async src="https://pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
				<ins class="adsbygoogle"
				     style="display:block; text-align:center;"
				     data-ad-layout="in-article"
				     data-ad-format="fluid"
				     data-ad-client="ca-pub-4805042826277102"
				     data-ad-slot="1010816570"></ins>
				<script>
				     (adsbygoogle = window.adsbygoogle || []).push({});
				</script>
			</div>
						
			${post.postContent}
		</div>
		
		<footer class="kre_article_footer">
			<div class="kre_btn_list">
				<ul class="kre_btn_list_right">
					<li><a href="${contextPath}/post/list" class="kre_btn">List</a></li>
				</ul>
			</div>
		</footer>
	</article>
</section>

<section id="comment_view" class="post comment">
	<h3 id="comment_header" class="section_subject">Comments</h3>

    <div id="comment_input" class="comment_input">
    	<div class="kre_row">
    		<input type="text" id="comment_name" class="kre_inp" placeholder="Name">
    	</div>
    	<div class="kre_row">
    		<input type="text" id="comment_password" class="kre_inp" placeholder="Password">
    	</div>
    	<div class="kre_row">
    		<textarea id="comment_text" class="kre_text"></textarea>
    	</div>
    	<div class="kre_row">
    		<input type="submit" id="comment_write_btn" class="kre_btn reverse_btn" value="Write">
    	</div>
    </div>

	<ul class="comment_list">
		<li>
			<div id="comment_item_123" class="comment_wrap" data-key="123">
				<div class="comment_header kre_btn_list">
				    <ul class="kre_btn_list_left">
				        <li><span class="comment_name">kurien</span></li>
				        <li><span class="comment_write_time">2020/05/02 10:57</span></li>
				    </ul>
				    <ul class="kre_btn_list_right">
				        <li><button type="button" class="kre_btn kre_reply_btn"><span class="material-icons">reply</span> 답글</a></li>
				        <li><button type="button" class="kre_btn kre_modify_btn"><span class="material-icons">edit</span> 수정</a></li>
				        <li><button type="button" class="kre_btn kre_delete_btn"><span class="material-icons">delete</span> 삭제</a></li>
				    </ul>
				</div>
				<div class="comment_body">
					내용입니다....
				</div>
			</div>
			
			<ul class="comment_children">
				<li>
					<div class="comment_wrap">
        				<div class="comment_header kre_btn_list">
        				    <ul class="kre_btn_list_left">
        				        <li><span class="comment_name">kurien</span></li>
        				        <li><span class="comment_write_time">2020/05/02 10:57</span></li>
        				    </ul>
        				    <ul class="kre_btn_list_right">
        				        <li><button type="button" class="kre_btn kre_reply_btn"><span class="material-icons">reply</span> 답글</a></li>
        				        <li><button type="button" class="kre_btn kre_modify_btn"><span class="material-icons">edit</span> 수정</a></li>
        				        <li><button type="button" class="kre_btn kre_delete_btn"><span class="material-icons">delete</span> 삭제</a></li>
        				    </ul>
        				</div>
						<div class="comment_body">
							답글 내용입니다....
						</div>
					</div>
				</li>
				<li>
					<div class="comment_wrap">
        				<div class="comment_header kre_btn_list">
        				    <ul class="kre_btn_list_left">
        				        <li><span class="comment_name">kurien</span></li>
        				        <li><span class="comment_write_time">2020/05/02 10:57</span></li>
        				    </ul>
        				    <ul class="kre_btn_list_right">
        				        <li><button type="button" class="kre_btn kre_reply_btn"><span class="material-icons">reply</span> 답글</a></li>
        				        <li><button type="button" class="kre_btn kre_modify_btn"><span class="material-icons">edit</span> 수정</a></li>
        				        <li><button type="button" class="kre_btn kre_delete_btn"><span class="material-icons">delete</span> 삭제</a></li>
        				    </ul>
        				</div>
						<div class="comment_body">
							답글 내용입니다....
						</div>
					</div>
				</li>
				<li>
					<div class="comment_wrap">
        				<div class="comment_header kre_btn_list">
        				    <ul class="kre_btn_list_left">
        				        <li><span class="comment_name">kurien</span></li>
        				        <li><span class="comment_write_time">2020/05/02 10:57</span></li>
        				    </ul>
        				    <ul class="kre_btn_list_right">
        				        <li><button type="button" class="kre_btn kre_reply_btn"><span class="material-icons">reply</span> 답글</a></li>
        				        <li><button type="button" class="kre_btn kre_modify_btn"><span class="material-icons">edit</span> 수정</a></li>
        				        <li><button type="button" class="kre_btn kre_delete_btn"><span class="material-icons">delete</span> 삭제</a></li>
        				    </ul>
        				</div>
						<div class="comment_body">
							답글 내용입니다....
						</div>
					</div>
				</li>
			</ul>
		</li>
		<li>
			<div class="comment_wrap">
				<div class="comment_header kre_btn_list">
				    <ul class="kre_btn_list_left">
				        <li><span class="comment_name">kurien</span></li>
				        <li><span class="comment_write_time">2020/05/02 10:57</span></li>
				    </ul>
				    <ul class="kre_btn_list_right">
				        <li><button type="button" class="kre_btn kre_reply_btn"><span class="material-icons">reply</span> 답글</a></li>
				        <li><button type="button" class="kre_btn kre_modify_btn"><span class="material-icons">edit</span> 수정</a></li>
				        <li><button type="button" class="kre_btn kre_delete_btn"><span class="material-icons">delete</span> 삭제</a></li>
				    </ul>
				</div>
				<div class="comment_body">
					내용입니다....
				</div>
			</div>
		</li>
		<li>
			<div class="comment_wrap">
				<div class="comment_header kre_btn_list">
				    <ul class="kre_btn_list_left">
				        <li><span class="comment_name">kurien</span></li>
				        <li><span class="comment_write_time">2020/05/02 10:57</span></li>
				    </ul>
				    <ul class="kre_btn_list_right">
				        <li><button type="button" class="kre_btn kre_reply_btn"><span class="material-icons">reply</span> 답글 취소</a></li>
				        <li><button type="button" class="kre_btn kre_modify_btn"><span class="material-icons">edit</span> 수정</a></li>
				        <li><button type="button" class="kre_btn kre_delete_btn"><span class="material-icons">delete</span> 삭제</a></li>
				    </ul>
				</div>
				<div class="comment_body">
					내용입니다....
				</div>
			</div>
		</li>
	</ul>
</section>

<script>
    $(function() {
        var commentView = $("#comment_view");
        
        var commentReplyInput = `
            <div id="comment_reply_input" class="comment_input">
                <h4 id="comment_reply_header">Reply</h3>
            
            	<div class="kre_row">
            		<input type="text" id="comment_reply_name" class="kre_inp" placeholder="Name">
            	</div>
            	<div class="kre_row">
            		<input type="text" id="comment_reply_password" class="kre_inp" placeholder="Password">
            	</div>
            	<div class="kre_row">
            		<textarea id="comment_reply_text" class="kre_text"></textarea>
            	</div>
            	<div class="kre_row">
            		<input type="submit" id="comment_reply_write_btn" class="kre_btn reverse_btn" value="Write">
            	</div>
            </div>`;
            
        var commentPasswordCheckInput = `
            <div id="comment_reply_input" class="comment_input">
                <h4 id="comment_reply_header">Password Check</h3>

            	<div class="kre_row">
            		<input type="text" id="comment_reply_password" class="kre_inp" placeholder="Password">
            	</div>

            	<div class="kre_row">
            		<input type="submit" id="comment_password_check_btn" class="kre_btn reverse_btn" value="Check">
            	</div>
            </div>`;
        
        var commentReplyAction = "${contextPath}/comment/reply/";
        var commentModifyAction = "${contextPath}/comment/modify/";
        var commentDeleteAction = "${contextPath}/comment/delete/";
        var commentPasswordCheckAction = "${contextPath}/comment/passwordCheck/";
        
        var commentState = "";
        var targetCommentNo = 0;
        var commentToken = "";
    
        commentView.on("click", ".kre_reply_btn", function() {
            var replyBtn = $(this);
            var parentComment = replyBtn.closest(".comment_wrap");
            
            // 이미 버튼을 누른상태라면 취소한 뒤 실행한다.
            var activeReplyBtn = $(".comment_btn_active");
            
            if(activeReplyBtn.length > 0) {
                var activeReplyBtn = $(".comment_btn_active");
                parentComment.find(".kre_reply_btn").html('<span class="material-icons">reply</span> 답글');
                parentComment.find(".kre_modify_btn").html('<span class="material-icons">edit</span> 수정');
                parentComment.find(".kre_delete_btn").html('<span class="material-icons">delete</span> 삭제');
                activeReplyBtn.removeClass("comment_btn_active");
                
                $("#comment_reply_input").remove();
                
                // 취소를 누른 것이라면, 복구 후 종료
                if(replyBtn[0] === activeReplyBtn[0]) {
                    commentState = "";
                    return;
                }
            }
            
            commentState = "reply";
            
            targetCommentNo = parentComment.data("key");
            
            replyBtn.addClass("comment_btn_active");
            replyBtn.html('<span class="material-icons">reply</span> 취소')
            parentComment.after(commentReplyInput);
        });
        
        commentView.on("click", ".kre_modify_btn", function() {
            var replyBtn = $(this);
            var parentComment = replyBtn.closest(".comment_wrap");
            
            // 이미 버튼을 누른상태라면 취소한 뒤 실행한다.
            var activeReplyBtn = $(".comment_btn_active");
            
            if(activeReplyBtn.length > 0) {
                var activeReplyBtn = $(".comment_btn_active");
                parentComment.find(".kre_reply_btn").html('<span class="material-icons">reply</span> 답글');
                parentComment.find(".kre_modify_btn").html('<span class="material-icons">edit</span> 수정');
                parentComment.find(".kre_delete_btn").html('<span class="material-icons">delete</span> 삭제');
                activeReplyBtn.removeClass("comment_btn_active");
                
                $("#comment_reply_input").remove();
                
                // 취소를 누른 것이라면, 복구 후 종료
                if(replyBtn[0] === activeReplyBtn[0]) {
                    commentState = "";
                    return;
                }
            }
            
            commentState = "edit";
            
            targetCommentNo = parentComment.data("key");
            
            replyBtn.addClass("comment_btn_active");
            replyBtn.html('<span class="material-icons">edit</span> 취소');
            parentComment.after(commentPasswordCheckInput);
            $("#comment_reply_header").text("Edit - Password check");
        });

        commentView.on("click", ".kre_delete_btn", function() {
            var replyBtn = $(this);
            var parentComment = replyBtn.closest(".comment_wrap");
            
            // 이미 버튼을 누른상태라면 취소한 뒤 실행한다.
            var activeReplyBtn = $(".comment_btn_active");
            
            if(activeReplyBtn.length > 0) {
                parentComment.find(".kre_reply_btn").html('<span class="material-icons">reply</span> 답글');
                parentComment.find(".kre_modify_btn").html('<span class="material-icons">edit</span> 수정');
                parentComment.find(".kre_delete_btn").html('<span class="material-icons">delete</span> 삭제');
                activeReplyBtn.removeClass("comment_btn_active");
                
                $("#comment_reply_input").remove();
                
                // 취소를 누른 것이라면, 복구 후 종료
                if(replyBtn[0] === activeReplyBtn[0]) {
                    commentState = "";
                    return;
                }
            }
            
            commentState = "delete";
            
            targetCommentNo = parentComment.data("key");
            
            replyBtn.addClass("comment_btn_active");
            replyBtn.html('<span class="material-icons">edit</span> 취소');
            parentComment.after(commentPasswordCheckInput);
            $("#comment_reply_header").text("Delete - Password check");
        });
        
        commentView.on("click", "#comment_password_check_btn", function() {
            if(["edit", "delete"].includes(commentState) === false) {
                return;
            }
            
            // 비밀번호 체크 통신(targetCommentNo, password)
            passwordCheck(targetCommentNo, $("#comment_reply_password").val()).then(function(result) {
                if(result === false) {
                    reject(new Error("notMatchedPassword"));
                    return;
                }
            
                switch(commentState) {
                    case "edit":
                        showEdit();
                    break;
                    case "delete":
                        if(confirm("댓글을 정말로 삭제하시겠습니까?") === false) {
                            reject(new Error("doNotDelete"));
                            return;
                        }
                        
                        return confirmedDelete(targetCommentNo).then(function() {
                            // 해당 댓글 삭제
                            $("#comment_reply_input").remove();
                            
                            var commentItem = $("#comment_item_" + targetCommentNo);
                            
                            commentItem.children(".comment_header").remove();
                            commentItem.children(".comment_body").text("삭제된 댓글입니다.");
            
                            commentState = "";
                        }).catch(function(err) {
                            Promise.reject(err); 
                        });
                    break;
                }
            }).catch(function(err) {
                if(err.message === "notMatchedPassword") {
                    alert("비밀번호가 일치하지 않습니다.");
                    return;
                } else if(err.message === "doNotDelete") {
                    return;
                } else if(err.message === "failedDelete") {
                    return;
                } else if(err.message === "invalidToken") {
                    alert("정상적인 경로를 이용하여주십시오.")
                    return;
                }
                
                alert("알 수 없는 오류가 발생했습니다.");
            });
        });
        
        commentView.on("click", "#comment_reply_write_btn", function() {
            if(["reply", "edit"].includes(commentState) === false) {
                return;
            }
            
            var parentComment = $("#comment_reply_input").prev(".comment_wrap");
            var activeReplyBtn = $(".comment_btn_active");
            
            var commentData = {
                name: $("comment_reply_name").val(),
                password: $("comment_reply_password").val(),
                text: $("comment_reply_text").val()
            }

            switch(commentState) {
                case "reply":
                    parentComment.find(".kre_reply_btn").html('<span class="material-icons">reply</span> 답글');
                    
                    writeReply(targetCommentNo, commentData).then(function(comment) {
                        activeReplyBtn.removeClass("comment_btn_active");
                        $("#comment_reply_input").remove();
                        
                        addCommentChildren(targetCommentNo);
                        var replyCommentItem = getReplyCommentItem(comment.no, comment.name, comment.writeTime, comment.text);
                        
                        parentComment.next(".comment_children").append(replyCommentItem);
                        commentState = "";
                    }).catch(function(err) {
                        alert("알 수 없는 오류가 발생했습니다.");
                    });
                break;
                case "edit":
                    parentComment.find(".kre_modify_btn").html('<span class="material-icons">edit</span> 수정');
                    
                    editReply(targetCommentNo, commentData, commentToken).then(function(comment) {
                        activeReplyBtn.removeClass("comment_btn_active");
                        $("#comment_reply_input").remove();
                        
                        addCommentChildren(targetCommentNo);
                        var commentItem = getCommentItem(comment.name, comment.writeTime, comment.text);
                        
                        parentComment.html(commentItem);
                        commentState = "";
                    }).catch(function(err) {
                        alert("알 수 없는 오류가 발생했습니다.");
                    });
                break;
            }
        });
        
        function writeReply(commentNo, commentData) {
            return new Promise(function(resolve, reject) {
                $.ajax({
                    url: commentReplyAction + commentNo,
                    type: "post",
                    dataType: "json",
                    data: commentData
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
        
        function editReply(commentNo, commentData, token) {
            return new Promise(function(resolve, reject) {
                commentData["token"] = token;
                
                $.ajax({
                    url: commentModifyAction + commentNo,
                    type: "post",
                    dataType: "json",
                    data: commentData
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
        
        function passwordCheck(commentNo, password) {
            return new Promise(function(resolve, reject) {
                $.ajax({
                    url: commentPasswordCheckAction + commentNo,
                    type: "post",
                    dataType: "json",
                    data: {
                        password: $("#comment_reply_password").val()
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
        
        function showEdit() {
            // 이미 버튼을 누른상태라면 취소한 뒤 실행한다.
            var parentComment = $("#comment_reply_input").prev(".comment_wrap");
            
            $("#comment_reply_input").remove();
            parentComment.after(commentReplyInput);
            
            $("#comment_reply_header").text("Edit reply");
        }
        
        function confirmedDelete(commentNo) {
            return new Promise(function(resolve, reject) {
                // 해당 댓글 삭제
                $.ajax({
                    url: commentDeleteAction + commentNo,
                    type: "post",
                    dataType: "json",
                    data: {
                        token: commentToken
                    }
                }).done(function(data) {
                    if(data.result === "fail") {
                        reject(new Error(data.message));
                        return;
                    }
                    
                    resolve();
                }).fail(function(err) {
                    reject(err);
                });
            })
        }
        
        function addCommentChildren(commentNo) {
            var targetComment = $("#comment_item_" + commentNo);
            var commentChildren = targetComment.next(".comment_children");
            
            if(commentChildren.length > 0) {
                return;
            }
            
            targetComment.after('<ul class="comment_children"></ul>')
        }
        
        function getReplyCommentItem(no, name, writeTime, text) {
            var commentItem = getCommentItem(name, writeTime, text);
            
            var replyCommentItem = `
                <li>
    				<div id="comment_item_\${no}" class="comment_wrap" data-key="\${no}">
    				    \${commentItem}
    				</div>
    			</li>`;
    			
    		return replyCommentItem;
        }
        
        function getCommentItem(name, writeTime, text) {
            var commentItem = `
    			<div class="comment_header kre_btn_list">
    			    <ul class="kre_btn_list_left">
    			        <li><span class="comment_name">\${name}</span></li>
    			        <li><span class="comment_write_time">\${writeTime}</span></li>
    			    </ul>
    			    <ul class="kre_btn_list_right">
    			        <li><button type="button" class="kre_btn kre_reply_btn"><span class="material-icons">reply</span> 답글</button></li>
    			        <li><button type="button" class="kre_btn kre_modify_btn"><span class="material-icons">edit</span> 수정</button></li>
    			        <li><button type="button" class="kre_btn kre_delete_btn"><span class="material-icons">delete</span> 삭제</button></li>
    			    </ul>
    			</div>
    			<div class="comment_body">
    				\${text}
    			</div>`;
    			
    		return commentItem;
        }
    });
</script>

<script src="${contextPath}/js/plugin/clipboard.min.js"></script>
<script>
	if($("#copyUrl").length > 0) {
		var clipboard = new ClipboardJS('#copyUrl');
		
		clipboard.on('success', function(e) {
			alert("주소가 복사되었습니다.");

		    e.clearSelection();
		});
	}
</script>