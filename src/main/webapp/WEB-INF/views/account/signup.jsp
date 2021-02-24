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

                <div id="accountIdAlert" class="kre_row kre_row_alert"></div>

                <div class="kre_row">
                    <label for="accountPassword" class="row_column">PW</label><input type="password" id="accountPassword" name="accountPassword" class="kre_inp" placeholder="Password">
                </div>

                <div id="accountPasswordAlert" class="kre_row kre_row_alert"></div>

                <div class="kre_row">
                    <label for="viewPassword" class="row_column">View PW</label><button type="button" id="viewPassword" class="kre_btn reverse_btn">View</button>
                </div>

                <div class="kre_row">
                    <label for="accountEmail" class="row_column">Email</label><input type="text" id="accountEmail" name="accountEmail" class="kre_inp" placeholder="Email">
                </div>

                <div id="accountEmailAlert" class="kre_row kre_row_alert"></div>

                <div class="kre_row">
                    <label for="sendCertKey" class="row_column">Certification</label><button type="button" id="sendCertKey" name="sendCertKey" class="kre_btn reverse_btn">Send</button>
                </div>

                <div id="inputCertKey" class="kre_row">
                    <label for="certKey" class="row_column hide_column"></label><input type="text" id="certKey" name="certKey" class="kre_inp" placeholder="Enter a certification key">
                </div>

                <div id="checkCert" class="kre_row">
                    <label for="checkCertBtn" class="row_column hide_column"></label><button type="button" id="checkCertBtn" name="checkCertBtn" class="kre_btn reverse_btn">Check</button>
                </div>

                <div class="kre_row">
                    <label for="accountNickname" class="row_column">Nickname</label><input type="text" id="accountNickname" name="accountNickname" class="kre_inp" placeholder="Nickname">
                </div>

                <div id="accountNicknameAlert" class="kre_row kre_row_alert"></div>

                <div class="kre_row">
                    <button type="button" id="signupBtn" class="kre_btn reverse_btn signup_btn" disabled>Sign up</button>
                </div>
            </div>
        </form>
    </div>

    <script>
        let accountCheck = {
            id: false,
            password: false,
            email: false,
            certedEmail: false,
            nickname: false
        };

        let validTimeout = {
            id: null,
            password: null,
            email: null,
            nickname: null
        };

        $("#accountId").on("keyup", function() {
            if(validTimeout.id !== null) {
                return false;
            }

            validTimeout.id = setTimeout(function() {
                validId();
                validTimeout.id = null;
            }, 1000);
        });

        $("#accountPassword").on("keyup", function() {
            if(validTimeout.password !== null) {
                return false;
            }

            validTimeout.password = setTimeout(function() {
                validPassword();
                validTimeout.password = null;
            }, 1000);
        });

        $("#accountEmail").on("keyup", function() {
            if(validTimeout.email !== null) {
                return false;
            }

            validTimeout.email = setTimeout(function() {
                validEmail();
                validTimeout.email = null;
            }, 1000);
        });

        $("#accountNickname").on("keyup", function() {
            if(validTimeout.nickname !== null) {
                return false;
            }

            validTimeout.nickname = setTimeout(function() {
                validNickname();
                validTimeout.nickname = null;
            }, 1000);
        });

        function validId() {
            var data = {
                "accountId": $("#accountId").val()
            };

            ajax("post", contextPath + "/account/checkId", data).then(function() {
                accountCheck.id = true;
                $("#accountIdAlert").hide(0);
                checkedAccount();
            }).catch(function(err) {
                accountCheck.id = false;
                checkedAccount();

                if(err.message !== "") {
                    $("#accountIdAlert").show(0);
                    $("#accountIdAlert").text(err.message);
                    return;
                }

                alert("알 수 없는 오류가 발생했습니다.");
            });
        }

        function validPassword() {
            accountCheck.password = false;

            let password = $("#accountPassword").val();

            if(password.length < 8) {
                $("#accountPasswordAlert").show(0);
                $("#accountPasswordAlert").text("비밀번호는 8자 이상 입력하시기 바랍니다.");
                return;
            }

            $("#accountPasswordAlert").hide(0);
            accountCheck.password = true;
            checkedAccount();
        }

        function validEmail() {
            var data = {
                "accountEmail": $("#accountEmail").val()
            };

            ajax("post", contextPath + "/account/checkEmail", data).then(function() {
                accountCheck.email = true;
                $("#accountEmailAlert").hide(0);
                $("#sendCertKey").prop("disabled", false);
                checkedAccount();
            }).catch(function(err) {
                accountCheck.email = false;
                $("#sendCertKey").prop("disabled", true);
                checkedAccount();

                if(err.message !== "") {
                    $("#accountEmailAlert").show(0);
                    $("#accountEmailAlert").text(err.message);
                    return;
                }

                alert("알 수 없는 오류가 발생했습니다.");
            });
        }

        function validNickname() {
            var data = {
                "accountNickname": $("#accountNickname").val()
            };

            ajax("post", contextPath + "/account/checkNickname", data).then(function() {
                accountCheck.nickname = true;
                $("#accountNicknameAlert").hide(0);
                checkedAccount();
            }).catch(function(err) {
                accountCheck.nickname = false;
                checkedAccount();

                if(err.message !== "") {
                    $("#accountNicknameAlert").show(0);
                    $("#accountNicknameAlert").text(err.message);
                    return;
                }

                alert("알 수 없는 오류가 발생했습니다.");
            });
        }

        function checkedAccount() {
            var accountCheckKeys = Object.keys(accountCheck);

            for(var i = 0; i < accountCheckKeys.length; i++) {
                if(accountCheck[accountCheckKeys[i]] === false) {
                    return false;
                }
            }

            $("#signupBtn").prop("disabled", false);
        }

        $("#viewPassword").on({
            touchstart: function() {
                viewPassword();
            },
            touchend: function() {
                hidePassword();
            },
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
            $("#sendCertKey").prop("disabled", true);

            var data = {
                "accountEmail": $("#accountEmail").val(),
                "certType": "signup"
            };

            ajax("post", contextPath + "/account/sendCertKey", data).then(function() {
                $("#inputCertKey").show(0);
                $("#checkCert").show(0);

                alert("입력하신 이메일로 인증번호를 발송하였습니다.");
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
                "certKey": $("#certKey").val(),
                "certType": "signup"
            };

            ajax("post", contextPath + "/account/checkCertKey", data).then(function() {
                $("#accountEmail").prop("readonly", true);
                $("#sendCertKey").prop("disabled", true);
                $("#inputCertKey").hide(0);
                $("#checkCert").hide(0);

                accountCheck.certedEmail = true;
                checkedAccount();

                alert("인증되었습니다.");
            }).catch(function(err) {
                accountCheck.certedEmail = false;
                checkedAccount();

                if(err.message !== "") {
                    alert(err.message);
                    return;
                }

                alert("알 수 없는 오류가 발생했습니다.");
            });
        });

        $("#signupBtn").on("click", function() {
            $(this).prop("disabled", true);

            var data = {
                "token": $("#token").val(),
                "accountId": $("#accountId").val(),
                "accountPassword": $("#accountPassword").val(),
                "accountEmail": $("#accountEmail").val(),
                "accountNickname": $("#accountNickname").val()
            }

            ajax("post", contextPath + "/account/signupCheck", data).then(function() {
                alert("회원가입이 완료되었습니다.");
                location.replace(contextPath + "/auth/signin");
            }).catch(function(err) {
                $(this).prop("disabled", false);
                if(err.message !== "") {
                    alert(err.message);
                    return;
                }

                alert("알 수 없는 오류가 발생했습니다.");
            });
        });

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