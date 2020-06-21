var kreVisitorCollect = (function(contextPath) {
	"use strict";
	function visitorCollect() {
		var visitor = {
			userAgent: navigator.userAgent,
			currentUrl: location.href,
			referrer: document.referrer,
			resolutionX: screen.width,
			resolutionY: screen.height
		}
		
		getUserCookie().then(function(token) {
			setCookie("kreUserCookie", token, 30);
			visitor.userCookie = token;
			return sendVisitorCollect(visitor);
		}).then(function(result) {
		}).catch(function(err) {
		});
	}
	
	function getUserCookie() {
		return new Promise(function(resolve, reject) {
			var userCookie = getCookie("kreUserCookie");
			
			if(userCookie === null) {
				getUserCookieToken().then(function(userCookieToken) {
					resolve(userCookieToken.token);
				});
				return;
			}
			
			resolve(userCookie);
		});
	}
	
	function sendVisitorCollect(visitor) {
        return new Promise(function(resolve, reject) {
        	$.ajax({
    			url: contextPath + '/visitor/collect',
    			method: "post",
    			dataType: "json",
    			data: visitor
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
	
	function getUserCookieToken() {
        return new Promise(function(resolve, reject) {
        	$.ajax({
    			url: contextPath + '/visitor/getUserCookieToken',
    			method: "get",
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
	};
	
	function setCookie(name, value, day) {
        var date = new Date();
        date.setTime(date.getTime() + day * 60 * 60 * 24 * 1000);
        document.cookie = name + '=' + value + ';expires=' + date.toUTCString() + ';path=/';
    };
    
    function getCookie(name) {
        var value = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');
        return value? value[2] : null;
    };
    
    function deleteCookie(name) {
        var date = new Date();
        document.cookie = name + "= " + "; expires=" + date.toUTCString() + "; path=/";
    }
    

	visitorCollect();
})(contextPath);