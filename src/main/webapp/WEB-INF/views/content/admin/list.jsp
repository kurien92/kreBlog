<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<section id="admin_content_list" class="content admin">
    <h3 class="section_subject">Content List</h3>

    <div class="kre_btn_list">
        <ul class="kre_btn_list_left">
            <li>
                <form action="${contextPath}/admin/content/list" method="get">
                    <select name="searchType">
                        <option value="title">제목</option>
                        <option value="content">내용</option>
                    </select>

                    <input type="text" name="keyword">

                    <button type="submit">검색</button>
                </form>
            </li>
        </ul>

        <ul class="kre_btn_list_right">
            <li><a href="${contextPath}/admin/content/write" class="kre_btn">추가</a></li>
        </ul>
    </div>

    <div class="kre_list">
        <ul>
            <c:forEach var="content" items="${contents}">
                <li class="content_item">
                    <div>
                        <a href="${contextPath}/admin/content/modify/${content.contentId}" class="contentViewLink">[${content.contentId}] ${content.contentTitle}</a>
                    </div>
                    <div>
                        <fmt:formatDate value="${content.contentWriteTime}" pattern="yy/MM/dd HH:mm" />
                    </div>
                    <div>
                        <a href="${contextPath}/admin/content/preview/${content.contentId}" class="contentPreviewLink kre_btn" target="_blank">미리보기</a>
                        <a href="${contextPath}/admin/content/delete/${content.contentId}" class="contentDeleteLink kre_btn">삭제</a>
                    </div>
                </li>
            </c:forEach>
        </ul>

        <div class="pagination">
            <c:if test="${pageMaker.prev}">
                <a href="${pageUrl}${pageMaker.makeQuery(pageMaker.startPage - 1)}" class="pagination_item pagination_prev">◀</a></li>
            </c:if>

            <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="i">
                <c:choose>
                    <c:when test="${pageMaker.criteria.page != i}">
                        <a href="${pageUrl}${pageMaker.makeQuery(i)}" class="pagination_item">${i}</a>
                    </c:when>
                    <c:otherwise>
                        <span class="pagination_item pagination_current">${i}</span>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
                <a href="${pageUrl}/${pageMaker.makeQuery(pageMaker.endPage + 1)}" class="pagination_item pagination_next">▶</a>
            </c:if>
        </div>
    </div>
</section>
<script>
    $(function() {
        $(".contentDeleteLink").on("click touch", function() {
            if(!confirm("삭제된 게시물은 복구할 수 없습니다.\n삭제하시겠습니까?")) {
                return false;
            }
        });
    });
</script>