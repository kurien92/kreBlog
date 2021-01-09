<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<section id="admin_visitor_list" class="visitor admin">
	<h3 class="section_subject">Visitor statistics List</h3>

	<div class="kre_list">
		<div class="kre_table_responsive">
			<table class="kre_table nowrap">
				<colgroup>
					<col class="visitorNo">
					<col class="currentUrl">
					<col class="referrer">
					<col class="browser">
					<col class="browserType">
					<col class="platform">
					<col class="deviceType">
					<col class="resolution">
					<col class="visitTime">
					<col class="visitorIp">
				</colgroup>
				
			
				<thead>
					<tr>
						<th scope="col">번호</th>
						<th scope="col">접근 페이지</th>
						<th scope="col">이전 페이지</th>
						<th scope="col">브라우저</th>
						<th scope="col">브라우저 타입</th>
						<th scope="col">플랫폼</th>
						<th scope="col">장치 타입</th>
						<th scope="col">해상도</th>
						<th scope="col">방문시간</th>
						<th scope="col">아이피</th>
					</tr>
				</thead>
				<c:choose>
					<c:when test="${visitorsInfo.size() == 0}">
						<td colspan="10" class="visitor_info_item kre_list_empty">
							방문자가 없습니다.
						</td>
					</c:when>
					<c:otherwise>
						<c:forEach var="visitorInfo" items="${visitorsInfo}">
						<tr class="visitor_info_item">
							<td class="visitorNo">${visitorInfo.visitor.visitorNo}</td>
							<td class="currentUrl"><a href="${visitorInfo.visitor.currentUrl}" class="visitorLink" target="_blank">${visitorInfo.visitor.currentUrl}</a></td>
							<td class="referrer">
								<c:if test="${visitorInfo.visitor.referrer != ''}">
									<a href="${visitorInfo.visitor.referrer}" class="visitorLink" target="_blank">${visitorInfo.visitor.referrer}</a>
								</c:if>
							</td>
							<td class="browser">${visitorInfo.capabilities.browser}</td>
							<td class="browserType">${visitorInfo.capabilities.browserType}</td>
							<td class="platform">${visitorInfo.capabilities.platform}</td>
							<td class="deviceType">${visitorInfo.capabilities.deviceType}</td>
							<td class="resolution">${visitorInfo.visitor.resolutionX} * ${visitorInfo.visitor.resolutionY}</td>
							<td class="visitTime"><fmt:formatDate value="${visitorInfo.visitor.visitTime}" pattern="yy/MM/dd HH:mm" /></td>
							<td class="visitorIp">${visitorInfo.visitor.visitorIp}</td>
						</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</table>
		</div>

		<div class="pagination">
            <c:if test="${pageMaker.prev}">
                <a href="${contextPath}/admin/visitor/list${pageMaker.makeQuery(pageMaker.startPage - 1)}" class="pagination_item pagination_prev">◀</a></li>
            </c:if>
            
            <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="i">
                <c:choose>
                	<c:when test="${pageMaker.criteria.page != i}">
		                <a href="${contextPath}/admin/visitor/list${pageMaker.makeQuery(i)}" class="pagination_item">${i}</a>
                	</c:when>
                	<c:otherwise>
                		<span class="pagination_item pagination_current">${i}</span>
                	</c:otherwise>
               	</c:choose>
            </c:forEach>
            
            <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
                <a href="${contextPath}/admin/visitor/list${pageMaker.makeQuery(pageMaker.endPage + 1)}" class="pagination_item pagination_next">▶</a>
            </c:if>
		</div>
	</div>
</section>