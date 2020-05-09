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
        var commentReplyInput = `
            <div id="comment_reply_input" class="comment_input">
                <h4 id="comment_reply_header">Reply</h3>
            
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
            </div>`;
            
        var commentPasswordCheckInput = `
            <div id="comment_reply_input" class="comment_input">
                <h4 id="comment_reply_header">Password Check</h3>

            	<div class="kre_row">
            		<input type="text" id="comment_password" class="kre_inp" placeholder="Password">
            	</div>

            	<div class="kre_row">
            		<input type="submit" id="comment_write_btn" class="kre_btn reverse_btn" value="Check">
            	</div>
            </div>`;
        
        var commentReplyAction = "${contextPath}/comment/reply/";
        var commentModifyAction = "${contextPath}/comment/reply/";
        var commentDeleteAction = "${contextPath}/comment/reply/";
        
        var commentPasswordCheckAction = "${contextPath}/comment/passwordCheck/";
        var parentCommentNo = 0;
    
        $("#comment_view").on("click", ".kre_reply_btn", function() {
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
                    return;
                }
            }
            
            parentCommentNo = parentComment.data("key");
            
            replyBtn.addClass("comment_btn_active");
            replyBtn.html('<span class="material-icons">reply</span> 취소')
            parentComment.after(commentReplyInput);
        });
        
        $("#comment_view").on("click", ".kre_modify_btn", function() {
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
                    return;
                }
            }
            
            replyBtn.addClass("comment_btn_active");
            replyBtn.html('<span class="material-icons">edit</span> 취소');
            parentComment.after(commentPasswordCheckInput);
        });

        $("#comment_view").on("click", ".kre_delete_btn", function() {
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
                    return;
                }
            }
            
            replyBtn.addClass("comment_btn_active");
            replyBtn.html('<span class="material-icons">edit</span> 취소');
            parentComment.after(commentPasswordCheckInput);
        });

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