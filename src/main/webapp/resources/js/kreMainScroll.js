var one = null;
var two = null;

scrollInit();

$(window).on("resize orientationchange", function() {
    scrollInit();
});

if(typeof kreGrid !== 'undefined') {
    kreGrid.on('layoutComplete', function() {
        if(isDesktopDevices()) {
            // 그리드가 전부 렌더링 되기 전에 이벤트가 실행되므로, setTimeout을 통해 동작 순서를 늦춤.
            setTimeout(function() {
                resetScroll();
            }, 0);
        }
    });
}

function scrollInit() {
    if(isDesktopDevices()) {
        if(alreadyCreateScroll(one) && alreadyCreateScroll(two)) {
            // 이벤트가 이미 실행되어있다면 갱신만 한다.
            if(checkStoppedScroll(one)) {
                one.startScroll();
            }

            if(checkStoppedScroll(two)) {
                two.startScroll();
            }

            resetScroll();
            return;
        }

        // 이벤트가 실행되어있지 않다면 새로 만든다.
        createScroll();
    } else {
        //모바일 때 이벤트가 있다면 중지시킨다.
        if(alreadyCreateScroll(one)) {
            one.stopScroll();
        }

        if(alreadyCreateScroll(two)) {
            two.stopScroll();
        }
    }
}

function alreadyCreateScroll(scroll) {
    if(scroll === null) {
        return false;
    }

    return true;
}

function checkStoppedScroll(scroll) {
    if(scroll === null) {
        return false;
    }

    if(scroll.checkStop()) {
        return true;
    }

    return false;
}

function resetScroll() {
    if(alreadyCreateScroll(one)) {
        one.resetValues();
    }

    if(alreadyCreateScroll(two)) {
        two.resetValues();
    }
}

function createScroll() {
    var kreAsideScroll = document.querySelectorAll('#kre_aside');

    // Apply slim scroll plugin
    one = new slimScroll(kreAsideScroll[0], {
        wrapperClass: 'scroll-wrapper1',
        scrollBarContainerClass: 'scrollBarContainer',
        scrollBarContainerSpecialClass: 'animate',
        scrollBarClass: 'scroll',
        keepFocus: false
    });

    var kreMainScroll = document.querySelectorAll('#kre_main');

    two = new slimScroll(kreMainScroll[0], {
        wrapperClass: 'scroll-wrapper2',
        scrollBarContainerClass: 'scrollBarContainer',
        scrollBarContainerSpecialClass: 'animate',
        scrollBarClass: 'scroll',
        keepFocus: false
    });
}