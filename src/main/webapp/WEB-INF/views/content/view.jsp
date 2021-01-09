<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<section id="content_view" class="content">
    <h3 class="section_subject">Content View</h3>

    <section class="kre_content" data-key="${content.contentId}">
        <header class="kre_content_header">
            <h4 class="kre_content_title">${content.contentTitle}</h4>
        </header>

        <div class="kre_content_body">
            ${content.content}
        </div>

        <footer class="kre_content_footer">
            <div class="kre_btn_list">
            </div>
        </footer>
    </section>
</section>

<script src="${contextPath}/js/plugin/kreFullImage.min.js"></script>
<script>
    var fullImage = kreFullImage({
        targets: ".kre_content_body"
    }).start();
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