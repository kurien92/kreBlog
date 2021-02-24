<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<section id="account_find" class="account">
    <h3 class="section_subject">Find Account</h3>

    <div class="kre_form">
        <form action="${contextPath}/account/findCheck" method="post">
            <input type="hidden" id="token" name="token" value="${token}" />

            <div id="account_signup_form">
                <div class="kre_row">
                    <label for="accountEmail" class="row_column">Email</label><input type="text" id="accountEmail" name="accountEmail" class="kre_inp" placeholder="Email">
                </div>

                <div id="accountEmailAlert" class="kre_row kre_row_alert"></div>

                <div class="kre_row">
                    <label for="sendCertKey" class="row_column">Certification</label><button type="button" id="sendCertKey" name="sendCertKey" class="kre_btn reverse_btn">Send</button>
                </div>

                <div id="inputCertKey" class="kre_row">
                    <label for="certKey" class="row_column"></label><input type="text" id="certKey" name="certKey" class="kre_inp" placeholder="Enter a certification key">
                </div>

                <div id="checkCert" class="kre_row">
                    <label for="checkCertBtn" class="row_column"></label><button type="button" id="checkCertBtn" name="checkCertBtn" class="kre_inp kre_btn reverse_btn">Check</button>
                </div>

                <div id="inputId" class="kre_row">
                    <label for="accountId" class="row_column">ID</label><input type="text" id="accountId" name="accountId" class="kre_inp" placeholder="ID" readonly>
                </div>

                <div id="inputPassword" class="kre_row">
                    <label for="accountPassword" class="row_column">PW</label><input type="password" id="accountPassword" name="accountPassword" class="kre_inp" placeholder="Password">
                </div>

                <div id="accountPasswordAlert" class="kre_row kre_row_alert"></div>

                <div id="viewPasswordBtn" class="kre_row">
                    <label for="viewPassword" class="row_column">View PW</label><button type="button" id="viewPassword" class="kre_inp kre_btn reverse_btn">View</button>
                </div>

                <div class="kre_row">
                    <button type="button" id="changePasswordBtn" class="kre_btn reverse_btn" disabled>Change Password</button>
                </div>
            </div>
        </form>
    </div>

    <script>
        let accountCheck = {
            password: false
        };

        $("#accountPassword").on("keyup", function() {
            validPassword();
        });

        function checkAccountCheck() {
            if(accountCheck.password === false) {
                return;
            }

            $("#changePasswordBtn").prop("disabled", false);
        }

        function validPassword() {
            accountCheck.password = false;

            let password = $("#accountPassword").val();

            if(password.length < 8) {
                $("#accountPasswordAlert").show(0);
                $("#accountPasswordAlert").text("비밀번호는 8자 이상 입력하시기 바랍니다.");
                checkAccountCheck();
                return;
            }

            $("#accountPasswordAlert").hide(0);
            accountCheck.password = true;
            checkAccountCheck();
        }

        $("#sendCertKey").on("click", function() {
            $("#sendCertKey").prop("disabled", true);

            var data = {
                "accountEmail": $("#accountEmail").val()
            };

            ajax("post", contextPath + "/account/sendFindCertKey", data).then(function() {
                $("#inputCertKey").show(0);
                $("#checkCert").show(0);

                alert("입력하신 이메일로 인증번호를 발송하였습니다.")
                $("#sendCertKey").prop("disabled", false);
            }).catch(function(err) {
                $("#sendCertKey").prop("disabled", false);

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

            ajax("post", contextPath + "/account/checkFindCertKey", data).then(function(result) {
                $("#accountEmail").prop("readonly", true);
                $("#sendCertKey").prop("disabled", true);
                $("#inputCertKey").hide(0);
                $("#checkCert").hide(0);

                alert("인증되었습니다. 변경할 비밀번호를 입력하세요.");

                $("#accountId").val(result.id);
                $("#inputId").show(0);
                $("#inputPassword").show(0);
                $("#viewPasswordBtn").show(0);
            }).catch(function(err) {
                if(err.message !== "") {
                    alert(err.message);
                    return;
                }

                alert("알 수 없는 오류가 발생했습니다.");
            });
        });

        $("#changePasswordBtn").on("click", function() {
            var data = {
                "token": $("#token").val(),
                "accountPassword": $("#accountPassword").val(),
                "accountEmail": $("#accountEmail").val()
            }

            ajax("post", contextPath + "/account/findCheck", data).then(function() {
                alert("비밀번호가 변경되었습니다.");
                location.replace(contextPath + "/auth/signin");
            }).catch(function(err) {
                if(err.message !== "") {
                    alert(err.message);
                    return;
                }

                alert("알 수 없는 오류가 발생했습니다.");
            });
        });

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

        function viewPassword() {
            $("#accountPassword").attr("type", "text");
            $("#viewPassword").removeClass("reverse_btn");
        }

        function hidePassword() {
            $("#accountPassword").attr("type", "password");
            $("#viewPassword").addClass("reverse_btn");
        }
    </script>
</section>