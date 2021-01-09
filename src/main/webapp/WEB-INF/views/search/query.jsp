<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<section id="search_query" class="search">
    <h3 class="section_subject">Search</h3>

    <section>
        <h4 class="search_item_title">${searchedItems.CONTENT.title}</h4>

        <c:choose>
            <c:when test="${searchedItems.CONTENT.contents.size() == 0}">
                <section>
                    <ul class="search_item search_item_empty">
                        <li class="search_item_list">
                            검색된 컨텐츠가 없습니다.
                        </li>
                    </ul>
                </section>
            </c:when>
            <c:otherwise>
                <section>
                    <ul class="search_item">
                        <c:forEach var="searchedItemContents" items="${searchedItems.CONTENT.contents}">
                            <li class="search_item_list">
                                <dl>
                                    <dt>
                                        <a href="${contextPath}/content/search/${searchedItemContents.id}" class="searchViewLink">${searchedItemContents.title}</a>
                                    </dt>
                                    <dd>
                                        ${searchedItemContents.description}
                                    </dd>
                                </dl>
                            </li>
                        </c:forEach>
                    </ul>
                </section>
            </c:otherwise>
        </c:choose>
    </section>

    <section>
        <h4 class="search_item_title">${searchedItems.POST.title}</h4>

        <c:choose>
            <c:when test="${searchedItems.POST.contents.size() == 0}">
                <section>
                    <ul class="search_item search_item_empty">
                        <li class="search_item_list">
                            검색된 포스트가 없습니다.
                        </li>
                    </ul>
                </section>
            </c:when>
            <c:otherwise>
                <section>
                    <ul class="search_item">
                        <c:forEach var="searchedItemContents" items="${searchedItems.POST.contents}">
                            <li class="search_item_list">
                                <dl>
                                    <dt>
                                        <a href="${contextPath}/post/search/${searchedItemContents.id}" class="searchViewLink">${searchedItemContents.title}</a>
                                    </dt>
                                    <dd>
                                        ${searchedItemContents.description}
                                    </dd>
                                </dl>
                            </li>
                        </c:forEach>
                    </ul>
                </section>
            </c:otherwise>
        </c:choose>
    </section>

    <section>
        <h4 class="search_item_title">${searchedItems.COMMENT.title}</h4>

        <c:choose>
            <c:when test="${searchedItems.COMMENT.contents.size() == 0}">
                <section>
                    <ul class="search_item search_item_empty">
                        <li class="search_item_list">
                            검색된 댓글이 없습니다.
                        </li>
                    </ul>
                </section>
            </c:when>
            <c:otherwise>
                <section>
                    <ul class="search_item">
                        <c:forEach var="searchedItemContents" items="${searchedItems.COMMENT.contents}">
                            <li class="search_item_list">
                                <dl>
                                    <dt>
                                        <a href="${contextPath}/comment/search/${searchedItemContents.id}" class="searchViewLink">
                                            [${searchedItemContents.name}] ${searchedItemContents.title}
                                        </a>
                                    </dt>
                                </dl>
                            </li>
                        </c:forEach>
                    </ul>
                </section>
            </c:otherwise>
        </c:choose>
    </section>

</section>