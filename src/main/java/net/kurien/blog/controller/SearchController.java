package net.kurien.blog.controller;

import net.kurien.blog.module.search.dto.SearchDTO;
import net.kurien.blog.module.comment.service.CommentService;
import net.kurien.blog.module.file.service.FileService;
import net.kurien.blog.module.post.service.PostService;
import net.kurien.blog.module.search.dto.Searchable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

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
    @RequestMapping("{keyword}")
    public String search(@PathVariable(required = true) String keyword, Model model) {
        String[] keywords = keyword.split(" ");

        List<Searchable> searcher = new ArrayList<>();
        List<SearchDTO> searchedDto = new ArrayList<>();

        searcher.add((Searchable)postService);
        searcher.add((Searchable)commentService);
        searcher.add((Searchable)fileService);


        for(int i = 0; i < searcher.size(); i++) {
            searchedDto.add(searcher.get(i).search(keywords));
        }

        model.addAttribute(searchedDto);

        return "search/keyword";
    }
}
