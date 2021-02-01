var kreComment = function(customOptions) {
    "use strict";

    if(customOptions === undefined) {
        customOptions = {};
    }

    var options = {
        postNo: "0",
        userNo: "",
        ui: {
            commentView: null,
            writeBtn: null,
            userCheckBtn: null,
            replyWriteBtn: null,
            replyWriteIconBtns: null,
            replyModifyIconBtns: null,
            replyDeleteIconBtns: null
        },
        action: {
            list: "/comment/list/",
            write: "/comment/write/",
            reply: "/comment/reply/",
            modify: "/comment/modify/",
            delete: "/comment/delete/",
            userCheck: "/comment/userCheck/"
        },
        event: {
            afterGetCommentList: function(commentList) {

            }
        }
    };

    for(var i in customOptions) {
        options[i] = customOptions[i];
    }

    var privateOptions = {
        state: "",
        targetCommentNo: 0,
        token: ""
    };

    var commentView = $(options.ui.commentView);
    var writeBtn = $(options.ui.writeBtn);

    var replyWriteClassName = options.ui.replyWriteIconBtns.replace(".", "");
    var replyModifyClassName = options.ui.replyModifyIconBtns.replace(".", "");
    var replyDeleteClassName = options.ui.replyDeleteIconBtns.replace(".", "");

    var commentReplyInputName = '';

    if(options.userNo === "") {
        commentReplyInputName = ''
        + ' <div class="kre_row">'
        + '		<input type="text" id="comment_reply_name" class="kre_inp" placeholder="Name">'
        + '	</div>'
        + '	<div class="kre_row">'
        + '		<input type="password" id="comment_reply_password" class="kre_inp" placeholder="Password">'
        + '	</div>'
    }

    var commentReplyInput = ''
        + '<div id="comment_reply_input" class="comment_input">'
        + ' <h4 id="comment_reply_header">Reply</h3>'
        + commentReplyInputName
        + '	<div class="kre_row">'
        + '		<textarea id="comment_reply_text" class="kre_text"></textarea>'
        + '	</div>'
        + '	<div class="kre_row">'
        + '		<input type="submit" id="comment_reply_write_btn" class="kre_btn reverse_btn" value="Write">'
        + '	</div>'
        + '</div>';

    var commentUserCheckInput = ''
        + '<div id="comment_reply_input" class="comment_input">'
        + '    <h4 id="comment_reply_header">Password Check</h3>'
        + ''
        + '    <div class="kre_row">'
        + '        <input type="password" id="comment_reply_password" class="kre_inp" placeholder="Password">'
        + '    </div>'
        + ''
        + '    <div class="kre_row">'
        + '        <input type="submit" id="comment_user_check_btn" class="kre_btn reverse_btn" value="Check">'
        + '    </div>'
        + '</div>';

    function getCommentList(postNo) {
        return ajax("get", options.action.list + postNo);
    }

    function writeComment(postNo, commentData) {
        return ajax("post", options.action.write + postNo, commentData);
    }

    function replyComment(commentNo, commentData) {
        return ajax("post", options.action.reply + commentNo, commentData);
    }

    function modifyComment(commentNo, commentData, token) {
        commentData.token = token;

        return ajax("post", options.action.modify + commentNo, commentData);
    }

    function deleteComment(commentNo, token) {
        var data = {};
        data.token = token;

        return ajax("post", options.action.delete + commentNo, data);
    }

    function commentUserCheck(commentNo, password) {
        var data = {};

        if(password !== undefined) {
            data.password = password;
        }

        return ajax("post", options.action.userCheck + commentNo, data);
    }

    function ajax(ajaxType, ajaxUrl, ajaxData) {
        return new Promise(function(resolve, reject) {
            var ajaxOpts = {
                url: ajaxUrl,
                type: ajaxType,
                dataType: "json"
            };

            if(ajaxData !== undefined) {
                ajaxOpts.data = ajaxData;
            }

            $.ajax(ajaxOpts).done(function (data) {
                if (data.result === "fail") {
                    reject(new Error(data.message));
                    return;
                }

                resolve(data.value);
            }).fail(function (err) {
                reject(err);
            });
        });
    }

    function getCommentListItem(commentList) {
        var comment = getLoadCommentItem(commentList);

        $(".comment_list").append(comment);
    }

    function getLoadCommentItem(commentList) {
        var html = [];

        var commentWrap = null;
        var commentChildren = null;

        for(var i = 0; i < commentList.length; i++) {
            var commentItemClass = "comment_wrap";

            if(commentList[i].delYn === "Y") {
                commentItemClass += " comment_deleted";
                commentList[i].text = "작성자가 삭제한 댓글입니다.";
            }

            var commentListTag = $("<li>", {
                class: "commentDepth_" + commentList[i].depth
            }).append($("<div>", {
                id: "comment_item_" + commentList[i].no,
                class: commentItemClass,
                "data-key":  + commentList[i].no
            }).append(getCommentItem(commentList[i].name, commentList[i].time, commentList[i].text, commentList[i].depth, commentList[i].delYn)));

            if(commentList[i].depth === 1) {
                if(commentChildren !== null) {
                    commentWrap.append(commentChildren);
                    commentChildren = null;
                }

                if(commentWrap !== null) {
                    html.push(commentWrap);
                    commentWrap = null;
                }

                commentWrap = commentListTag;
            } else {
                if(commentChildren === null) {
                    commentChildren = $("<ul>", {
                        class: "comment_children"
                    });
                }

                commentChildren.append(commentListTag);
            }
        }

        if(commentChildren !== null) {
            commentWrap.append(commentChildren);
        }

        if(commentWrap !== null) {
            html.push(commentWrap);
            commentWrap = null;
        }

        return html;
    }

    function getReplyCommentItem(no, name, time, text, depth, delYn) {
        var commentItem = getCommentItem(name, time, text, depth, delYn);

        var commentItemClass = "comment_wrap";

        if(delYn === "Y") {
            commentItemClass += " comment_deleted";
            text = "작성자가 삭제한 댓글입니다.";
        }

        var replyCommentItem = ''
            + '<li class="commentDepth_' + depth + '">'
            + '    <div id="comment_item_' + no + '" class="' + commentItemClass + '" data-key="' + no + '">'
            + '        ' + commentItem + ''
            + '	   </div>'
            + '</li>';

        return replyCommentItem;
    }

    function showEdit(comment) {
        // 이미 버튼을 누른상태라면 취소한 뒤 실행한다.
        var parentComment = $("#comment_reply_input").prev(".comment_wrap");

        $("#comment_reply_input").remove();
        parentComment.after(commentReplyInput);
        $("#comment_reply_name").val(comment.name);
        $("#comment_reply_text").val(comment.text);

        $(options.ui.replyWriteBtn).val("Modify");
        $("#comment_reply_header").text("Edit reply");
    }

    function addCommentChildren(commentNo) {
        var targetComment = $("#comment_item_" + commentNo);
        var commentChildren = targetComment.next(".comment_children");

        if(commentChildren.length > 0) {
            return;
        }

        targetComment.after('<ul class="comment_children"></ul>')
    }

    function getCommentItem(name, time, text, depth, delYn) {
        var commentBtn = '';


        if(depth === 1) {
            commentBtn += '<li><button type="button" class="kre_btn ' + replyWriteClassName + '"><span class="material-icons">reply</span> <span class="comment_btn_text">답글</span></button></li>';
        }

        if(delYn === "N") {
            commentBtn += ''
                + '<li><button type="button" class="kre_btn ' + replyModifyClassName + '"><span class="material-icons">edit</span> <span class="comment_btn_text">수정</span></button></li>'
                + '<li><button type="button" class="kre_btn ' + replyDeleteClassName + '"><span class="material-icons">delete</span> <span class="comment_btn_text">삭제</span></button></li>';
        }

        if(commentBtn !== "") {
            commentBtn = ''
                + '<ul class="kre_btn_list_right">'
                + '    ' + commentBtn + ''
                + '</ul>';
        }

        var commentItem = ''
            + '<div class="comment_header kre_btn_list">'
            + '    <ul class="kre_btn_list_left">'
            + '        <li><span class="comment_name">' + name + '</span></li>'
            + '        <li><span class="comment_write_time">' + time + '</span></li>'
            + '    </ul>'
            + '    ' + commentBtn + ''
            + '</div>'
            + '<div class="comment_body">'
            + '	' + text.replace(/\n/g, "<br>"); + ''
        + '</div>';

        return commentItem;
    }

    function userCheck(targetCommentNo, password) {
        if(["edit", "delete"].indexOf(privateOptions.state) === -1) {
            return;
        }

        commentUserCheck(targetCommentNo, password).then(function(result) {
            privateOptions.token = result.token;

            switch(privateOptions.state) {
                case "edit":
                    showEdit(result);
                    break;
                case "delete":
                    if(confirm("댓글을 정말로 삭제하시겠습니까?") === false) {
                        throw new Error("doNotDelete");
                        return;
                    }

                    return deleteComment(privateOptions.targetCommentNo, privateOptions.token).then(function() {
                        // 해당 댓글 삭제
                        $("#comment_reply_input").remove();

                        var commentItem = $("#comment_item_" + privateOptions.targetCommentNo);

                        commentItem.addClass("comment_deleted");
                        commentItem.find(options.ui.replyModifyIconBtns).parent("li").remove();
                        commentItem.find(options.ui.replyDeleteIconBtns).parent("li").remove();
                        commentItem.children(".comment_body").text("작성자가 삭제한 댓글입니다.");

                        privateOptions.state = "";
                    }).catch(function(err) {
                        throw new Error(err);
                    });
                    break;
            }
        }).catch(function(err) {
            // 이미 버튼을 누른상태라면 취소한 뒤 실행한다.
            var activeReplyBtn = $(".comment_btn_active");

            if(activeReplyBtn.length > 0) {
                activeReplyBtn.filter(options.ui.replyWriteIconBtns).children(".comment_btn_text").text('답글');
                activeReplyBtn.filter(options.ui.replyModifyIconBtns).children(".comment_btn_text").text('수정');
                activeReplyBtn.filter(options.ui.replyDeleteIconBtns).children(".comment_btn_text").text('삭제');
                activeReplyBtn.removeClass("comment_btn_active");

                $("#comment_reply_input").remove();

                privateOptions.state = "";
            }

            if(err.message === "doNotDelete") {
                return;
            } else if(err.message === "failedDelete") {
                return;
            } else if(err.message === "invalidToken") {
                alert("정상적인 경로를 이용하여주십시오.")
                return;
            } else if(err.message !== "") {
                alert(err.message);
                return;
            }

            alert("알 수 없는 오류가 발생했습니다.");
        });
    }

    function validComment(commentData) {
        if(options.userNo === "") {
            if (commentData.name.length < 2) {
                alert("작성자명은 2자 이상으로 입력해주세요.");
                return false;
            }

            if (commentData.name.length > 30) {
                alert("작성자명은 30자 미만으로 입력해주세요.");
                return false;
            }

            if (commentData.password.length === 0) {
                alert("비밀번호를 입력해주세요.");
                return false;
            }

            if (commentData.password.length > 30) {
                alert("비밀번호는 30자 미만으로 입력해주세요.");
                return false;
            }
        }

        if(commentData.text.length === 0) {
            $("#comment_text").focus();
            alert("댓글을 입력해주세요.");
            return false;
        }

        if(commentData.text.length > 10000) {
            $("#comment_text").focus();
            alert("댓글은 10000자 미만으로 입력해주세요.");
            return false;
        }
    }

    $(window).on("load", function() {
        getCommentList(options.postNo).then(function(commentList) {
            getCommentListItem(commentList);

            options.event.afterGetCommentList(commentList);
        });
    });

    writeBtn.on("click", function() {
        var commentData = {
            text: $("#comment_text").val()
        }

        if(options.userNo === "") {
            commentData.name = $("#comment_name").val();
            commentData.password = $("#comment_password").val();
        } else {
            commentData.no = options.userNo;
        }

        if(validComment(commentData) === false) {
            return;
        }

        writeComment(options.postNo, commentData).then(function(comment) {
            var commentItem = getReplyCommentItem(comment.no, comment.name, comment.time, comment.text, comment.depth, comment.delYn);

            $(".comment_list").append(commentItem);

            if(options.userNo === "") {
                $("#comment_name").val("");
                $("#comment_password").val("");
            }

            $("#comment_text").val("");
        }).catch(function(err) {
            if(err.message !== "") {
                alert(err.message);
                return;
            }

            alert("알 수 없는 오류가 발생했습니다.");
        });
    });

    commentView.on("click", options.ui.replyWriteIconBtns, function() {
        var replyBtn = $(this);
        var parentComment = replyBtn.closest(".comment_wrap");

        // 이미 버튼을 누른상태라면 취소한 뒤 실행한다.
        var activeReplyBtn = $(".comment_btn_active");

        if(activeReplyBtn.length > 0) {
            activeReplyBtn.filter(options.ui.replyWriteIconBtns).children(".comment_btn_text").text('답글');
            activeReplyBtn.filter(options.ui.replyModifyIconBtns).children(".comment_btn_text").text('수정');
            activeReplyBtn.filter(options.ui.replyDeleteIconBtns).children(".comment_btn_text").text('삭제');

            activeReplyBtn.removeClass("comment_btn_active");

            $("#comment_reply_input").remove();

            // 취소를 누른 것이라면, 복구 후 종료
            if(replyBtn[0] === activeReplyBtn[0]) {
                privateOptions.state = "";
                return;
            }
        }

        privateOptions.state = "reply";
        privateOptions.targetCommentNo = parentComment.data("key");

        replyBtn.addClass("comment_btn_active");
        replyBtn.children(".comment_btn_text").text('취소');
        parentComment.after(commentReplyInput);
    });

    commentView.on("click", options.ui.replyModifyIconBtns, function() {
        var replyBtn = $(this);
        var parentComment = replyBtn.closest(".comment_wrap");

        // 이미 버튼을 누른상태라면 취소한 뒤 실행한다.
        var activeReplyBtn = $(".comment_btn_active");

        if(activeReplyBtn.length > 0) {
            activeReplyBtn.filter(options.ui.replyWriteIconBtns).children(".comment_btn_text").text('답글');
            activeReplyBtn.filter(options.ui.replyModifyIconBtns).children(".comment_btn_text").text('수정');
            activeReplyBtn.filter(options.ui.replyDeleteIconBtns).children(".comment_btn_text").text('삭제');

            activeReplyBtn.removeClass("comment_btn_active");

            $("#comment_reply_input").remove();

            // 취소를 누른 것이라면, 복구 후 종료
            if(replyBtn[0] === activeReplyBtn[0]) {
                privateOptions.state = "";
                return;
            }
        }

        privateOptions.state = "edit";
        privateOptions.targetCommentNo = parentComment.data("key");

        $(options.ui.replyWriteBtn).val("Modify");

        replyBtn.addClass("comment_btn_active");
        replyBtn.children(".comment_btn_text").text('취소');

        parentComment.after(commentUserCheckInput);

        if(options.userNo !== "") {
            $("#comment_reply_input").hide(0);
            userCheck(privateOptions.targetCommentNo);
            return;
        }

        $("#comment_reply_header").text("Edit - Password check");
    });

    commentView.on("click", options.ui.replyDeleteIconBtns, function() {
        var replyBtn = $(this);
        var parentComment = replyBtn.closest(".comment_wrap");

        // 이미 버튼을 누른상태라면 취소한 뒤 실행한다.
        var activeReplyBtn = $(".comment_btn_active");

        if(activeReplyBtn.length > 0) {
            activeReplyBtn.filter(options.ui.replyWriteIconBtns).children(".comment_btn_text").text('답글');
            activeReplyBtn.filter(options.ui.replyModifyIconBtns).children(".comment_btn_text").text('수정');
            activeReplyBtn.filter(options.ui.replyDeleteIconBtns).children(".comment_btn_text").text('삭제');
            activeReplyBtn.removeClass("comment_btn_active");

            $("#comment_reply_input").remove();

            // 취소를 누른 것이라면, 복구 후 종료
            if(replyBtn[0] === activeReplyBtn[0]) {
                privateOptions.state = "";
                return;
            }
        }

        privateOptions.state = "delete";
        privateOptions.targetCommentNo = parentComment.data("key");

        replyBtn.addClass("comment_btn_active");
        replyBtn.children(".comment_btn_text").text('취소');

        if(options.userNo !== "") {
            $("#comment_reply_input").hide(0);
            userCheck(privateOptions.targetCommentNo);
            return;
        }

        parentComment.after(commentUserCheckInput);
        $("#comment_reply_header").text("Delete - Password check");
    });

    commentView.on("click", options.ui.userCheckBtn, function() {
        var password = $("#comment_reply_password").val();

        if(password.length === 0) {
            $("#comment_reply_password").focus();
            alert("비밀번호를 입력해주세요.");
            return false;
        }

        userCheck(privateOptions.targetCommentNo, password);
    });

    commentView.on("click", options.ui.replyWriteBtn, function() {
        if(["reply", "edit"].indexOf(privateOptions.state) === -1) {
            return;
        }

        var parentComment = $("#comment_reply_input").prev(".comment_wrap");
        var activeReplyBtn = $(".comment_btn_active");

        var commentData = {
            text: $("#comment_reply_text").val()
        }

        if(options.userNo === "") {
            commentData.name = $("#comment_reply_name").val();
            commentData.password = $("#comment_reply_password").val();
        }

        if(validComment(commentData) === false) {
            return;
        }

        switch(privateOptions.state) {
            case "reply":
                parentComment.find(options.ui.replyWriteIconBtns).children(".comment_btn_text").text('답글');

                replyComment(privateOptions.targetCommentNo, commentData).then(function(comment) {
                    activeReplyBtn.removeClass("comment_btn_active");
                    $("#comment_reply_input").remove();

                    addCommentChildren(privateOptions.targetCommentNo);
                    var replyCommentItem = getReplyCommentItem(comment.no, comment.name, comment.time, comment.text, comment.depth, comment.delYn);

                    parentComment.next(".comment_children").append(replyCommentItem);
                    privateOptions.state = "";
                }).catch(function(err) {
                    if(err.message !== "") {
                        alert(err.message);
                        return;
                    }

                    alert("알 수 없는 오류가 발생했습니다.");
                });
                break;
            case "edit":
                parentComment.find(options.ui.replyModifyIconBtns).children(".comment_btn_text").text('수정');

                modifyComment(privateOptions.targetCommentNo, commentData, privateOptions.token).then(function(comment) {
                    activeReplyBtn.removeClass("comment_btn_active");
                    $("#comment_reply_input").remove();

                    addCommentChildren(privateOptions.targetCommentNo);
                    var commentItem = getCommentItem(comment.name, comment.time, comment.text, comment.depth, comment.delYn);

                    parentComment.html(commentItem);
                    privateOptions.state = "";
                }).catch(function(err) {
                    if(err.message !== "") {
                        alert(err.message);
                        return;
                    }

                    alert("알 수 없는 오류가 발생했습니다.");
                });
                break;
        }
    });
};