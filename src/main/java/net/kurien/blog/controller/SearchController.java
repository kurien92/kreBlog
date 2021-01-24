package net.kurien.blog.controller;

import net.kurien.blog.common.template.Template;
import net.kurien.blog.exception.InvalidRequestException;
import net.kurien.blog.module.content.service.ContentService;
import net.kurien.blog.module.search.dto.SearchDto;
import net.kurien.blog.module.comment.service.CommentService;
import net.kurien.blog.module.post.service.PostService;
import net.kurien.blog.module.search.dto.Searchable;
import net.kurien.blog.util.HtmlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/search")
public class SearchController {
    private final Template template;
    private final ContentService contentService;
    private final PostService postService;
    private final CommentService commentService;

    @Autowired
    public SearchController(Template template, ContentService contentService, PostService postService, CommentService commentService) {
        this.template = template;
        this.contentService = contentService;
        this.postService = postService;
        this.commentService = commentService;
    }

    /**
     * 블로그 내 컨텐츠를 검색한다.
     *  - 컨텐츠(제목, 내용)
     *  - 포스트(제목, 내용)
     *  - 코멘트(작성자, 내용)
     *  - 파일(파일명, 파일설명)
     *
     *  1. 권한을 검사한다.
     *  2. 검색할 수 있는 컨텐츠의 서비스를 찾는다.
     *  3. 검색한다.
     */
    @RequestMapping("")
    public String search(@RequestParam(required = true) String q, Model model) throws InvalidRequestException {
        if(q.length() < 2) {
            throw new InvalidRequestException("검색어는 2자 이상 입력해주세요.");
        }

        String[] queries = q.split(" ");

        List<Searchable> searcher = new ArrayList<>();
        Map<String, SearchDto> searchDtos = new HashMap<>();

        searcher.add((Searchable)contentService);
        searcher.add((Searchable)postService);
        searcher.add((Searchable)commentService);
//        searcher.add((Searchable)fileService);

        for(int i = 0; i < searcher.size(); i++) {
            SearchDto searchDto = searcher.get(i).search(queries);

            searchDtos.put(searchDto.getTitle(), escapeSearchData(searchDto));
        }

        model.addAttribute("searchedItems", searchDtos);

        String escapeQuery = HtmlUtil.escapeHtml(q);

        template.setSubTitle("\"" + escapeQuery + "\" 검색");
        template.setDescription("검색어에 해당하는 컨텐츠를 검색합니다.");
        template.getCss().add("<link rel=\"stylesheet\" href=\"/css/module/search.css\">");
        template.setSearchQuery(escapeQuery);

        return "search/query";
    }

    private SearchDto escapeSearchData(SearchDto searchDto) {
        for(Map<String, Object> searchContents : searchDto.getContents()) {
            // 타이틀 내의 HTML을 삭제
            String title = (String) searchContents.get("title");
            title = HtmlUtil.stripHtml(title);
            title = HtmlUtil.escapeHtml(title);
            searchContents.put("title", title);

            // 설명 내의 HTML을 삭제, 최대 길이를 200자로 제한
            Object descriptionObject = searchContents.get("description");

            if(descriptionObject == null) {
                continue;
            }

            String description = (String)descriptionObject;

            description = HtmlUtil.stripHtml(description);
            description = HtmlUtil.escapeHtml(description);

            if(description.length() > 200) {
                description = description.substring(0, 200) + "...";
            }

            searchContents.put("description", description);
        }

        return searchDto;
    }
}
