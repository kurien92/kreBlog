package net.kurien.blog.controller;

import net.kurien.blog.common.template.Template;
import net.kurien.blog.exception.InvalidRequestException;
import net.kurien.blog.module.search.dto.SearchDTO;
import net.kurien.blog.module.comment.service.CommentService;
import net.kurien.blog.module.file.service.FileService;
import net.kurien.blog.module.post.service.PostService;
import net.kurien.blog.module.search.dto.Searchable;
import net.kurien.blog.util.HtmlUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/search")
public class SearchController {
    @Inject
    private PostService postService;

    @Inject
    private CommentService commentService;

    @Inject
    private FileService fileService;

    /**
     * 블로그 내 컨텐츠를 검색한다.
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
        Map<String, SearchDTO> searchDtos = new HashMap<>();

        searcher.add((Searchable)postService);
        searcher.add((Searchable)commentService);
//        searcher.add((Searchable)fileService);


        for(int i = 0; i < searcher.size(); i++) {
            SearchDTO searchDto = searcher.get(i).search(queries);

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

    @Inject
    private Template template;

    private SearchDTO escapeSearchData(SearchDTO searchDto) {
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
