<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<section id="account_signup" class="account">
    <h3 class="section_subject">Sign up</h3>

    <div class="kre_form">
        <form action="${contextPath}/account/signupCheck" method="post">
            <input type="hidden" id="token" name="token" value="${token}" />

            <div id="account_signup_form">
                <div class="kre_row">
                    <label for="accountId" class="row_column">ID</label><input type="text" id="accountId" name="accountId" class="kre_inp" placeholder="ID">
                </div>

                <div class="kre_row">
                    <label for="accountPassword" class="row_column">PW</label><input type="password" id="accountPassword" name="accountPassword" class="kre_inp" placeholder="Password">
                </div>

                <div class="kre_row">
                    <label for="viewPassword" class="row_column">View PW</label><button type="button" id="viewPassword" class="kre_inp kre_btn reverse_btn">View</button>
                </div>

                <div class="kre_row">
                    <label for="accountEmail" class="row_column">Email</label><input type="text" id="accountEmail" name="accountEmail" class="kre_inp" placeholder="Email">
                </div>

                <div class="kre_row">
                    <label for="sendCertKey" class="row_column">Certification</label><button type="button" id="sendCertKey" name="sendCertKey" class="kre_inp kre_btn reverse_btn">Send</button>
                </div>

                <div id="inputCertKey" class="kre_row">
                    <label for="certKey" class="row_column"></label><input type="text" id="certKey" name="certKey" class="kre_inp" placeholder="Enter a certification key">
                </div>

                <div id="checkCert" class="kre_row">
                    <label for="checkCertBtn" class="row_column"></label><button type="button" id="checkCertBtn" name="checkCertBtn" class="kre_inp kre_btn reverse_btn">Check</button>
                </div>

                <div class="kre_row">
                    <label for="accountNickname" class="row_column">Nickname</label><input type="text" id="accountNickname" name="accountNickname" class="kre_inp" placeholder="Nickname">
                </div>

                <div class="kre_row">
                    <button type="button" id="signupBtn" class="kre_btn reverse_btn signup_btn">Sign up</button>
                </div>
            </div>
        </form>
    </div>

    <script>
        $("#accountId").on("keyup", function() {
            var data = {
                "accountId": $("#accountId").val()
            };

            ajax("post", contextPath + "/account/checkId", data).then(function() {

            }).catch(function(err) {
                if(err.message !== "") {
                    alert(err.message);
                    return;
                }

                alert("알 수 없는 오류가 발생했습니다.");
            });
        });
        $("#accountEmail").on("keyup", function() {
            var data = {
                "accountEmail": $("#accountEmail").val()
            };

            ajax("post", contextPath + "/account/checkEmail", data).then(function() {

            }).catch(function(err) {
                if(err.message !== "") {
                    alert(err.message);
                    return;
                }

                alert("알 수 없는 오류가 발생했습니다.");
            });
        });

        $("#accountNickname").on("keyup", function() {
            var data = {
                "accountNickname": $("#accountNickname").val()
            };

            ajax("post", contextPath + "/account/checkNickname", data).then(function() {

            }).catch(function(err) {
                if(err.message !== "") {
                    alert(err.message);
                    return;
                }

                alert("알 수 없는 오류가 발생했습니다.");
            });
        });

        $("#viewPassword").on({
            mousedown: function() {
                viewPassword();
            },
            mouseup: function() {
                hidePassword();
            },
            keydown: function(e) {
                if(e.keyCode === 13) {
                    viewPassword();
                }
            },
            keyup: function(e) {
                if(e.keyCode === 13) {
                    hidePassword();
                }
            }
        });

        $("#sendCertKey").on("click", function() {
            var data = {
                "accountEmail": $("#accountEmail").val()
            };

            ajax("post", contextPath + "/account/sendCertKey", data).then(function() {
                $("#inputCertKey").show(0);
                $("#checkCert").show(0);

                alert("입력하신 이메일로 인증번호를 발송하였습니다.")
            }).catch(function(err) {
                if(err.message !== "") {
                    alert(err.message);
                    return;
                }

                alert("알 수 없는 오류가 발생했습니다.");
            });
        });

        $("#checkCertBtn").on("click", function() {
            var data = {
                "accountEmail": $("#accountEmail").val(),
                "certKey": $("#certKey").val()
            };

            ajax("post", contextPath + "/account/checkCertKey", data).then(function() {
                $("#accountEmail").prop("readonly", true);
                $("#sendCertKey").prop("disabled", true);
                $("#inputCertKey").hide(0);
                $("#checkCert").hide(0);

                alert("인증되었습니다.");
            }).catch(function(err) {
                if(err.message !== "") {
                    alert(err.message);
                    return;
                }

                alert("알 수 없는 오류가 발생했습니다.");
            });
        });

        $("#signupBtn").on("click", function() {
            var data = {
                "token": $("#token").val(),
                "accountId": $("#accountId").val(),
                "accountPassword": $("#accountPassword").val(),
                "accountEmail": $("#accountEmail").val(),
                "accountNickname": $("#accountNickname").val()
            }

            ajax("post", contextPath + "/account/signupCheck", data).then(function() {
                location.replace(contextPath + "/auth/signin");
            }).catch(function(err) {
                if(err.message !== "") {
                    alert(err.message);
                    return;
                }

                alert("알 수 없는 오류가 발생했습니다.");
            });
        })

        function viewPassword() {
            $("#accountPassword").attr("type", "text");
            $("#viewPassword").removeClass("reverse_btn");
        }

        function hidePassword() {
            $("#accountPassword").attr("type", "password");
            $("#viewPassword").addClass("reverse_btn");
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
    </script>
</section>