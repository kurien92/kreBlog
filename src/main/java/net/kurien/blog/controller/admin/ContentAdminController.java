package net.kurien.blog.controller.admin;

import com.google.gson.JsonObject;
import net.kurien.blog.common.template.Template;
import net.kurien.blog.domain.PageMaker;
import net.kurien.blog.domain.SearchCriteria;
import net.kurien.blog.exception.NotFoundDataException;
import net.kurien.blog.module.content.entity.Content;
import net.kurien.blog.module.content.entity.ContentViewStatus;
import net.kurien.blog.module.content.service.ContentService;
import net.kurien.blog.module.file.entity.File;
import net.kurien.blog.module.file.entity.ServiceFile;
import net.kurien.blog.module.file.service.FileService;
import net.kurien.blog.module.file.service.ServiceFileService;
import net.kurien.blog.module.shortUrl.entity.ServiceShortUrl;
import net.kurien.blog.module.shortUrl.entity.ShortUrl;
import net.kurien.blog.module.shortUrl.service.ServiceShortUrlService;
import net.kurien.blog.module.shortUrl.service.ShortUrlService;
import net.kurien.blog.util.HtmlUtil;
import net.kurien.blog.util.RequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/content")
public class ContentAdminController {
	private static final Logger logger = LoggerFactory.getLogger(ContentAdminController.class);

	@Inject
	private ContentService contentService;

	@Inject
	private ShortUrlService shortUrlService;
	
	@Inject
	private ServiceShortUrlService serviceShortUrlService;
	
	@Inject
	private ServiceFileService serviceFileService;

	@Inject
	private FileService fileService;
	
	@Inject
	private Template template;

	/**
	 * 관리자가 목록 화면에 접속한다.
	 * 포스트 전체를 보여준다.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(SearchCriteria criteria, Model model, HttpServletRequest request, HttpServletResponse response) {
		int totalRowCount = contentService.getCount("Y");
		PageMaker pageMaker = new PageMaker(criteria, totalRowCount);

		List<Content> contents = contentService.getList("Y", criteria);

		model.addAttribute("pageUrl", request.getContextPath() + "/admin/content/list");
		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("contents", contents);

		template.setTitle("Content List &dash; Kurien's Blog");
		template.getCss().add("<link rel=\"stylesheet\" href=\"/css/module/content.css\">");
		
		return "admin/content/admin/list";
	}

	/**
	 * 관리자가 포스트를 쓰는 화면을 출력한다.
	 *
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write(Model model) throws Exception {
		model.addAttribute("formAction", "writeUpdate");
		model.addAttribute("formSubmit", "작성");

		template.setTitle("Content Write &dash; Kurien's Blog");
		template.getCss().add("<link rel=\"stylesheet\" href=\"/css/module/content.css\">");

		return "admin/content/admin/write";
	}

	/**
	 * 관리자가 작성한 포스트를 저장한다.
	 *
	 * @param content
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/writeUpdate", method = RequestMethod.POST)
	public String writeUpdate(HttpServletRequest request, Content content, Integer[] fileNos) throws Exception {
		contentService.create(content, fileNos);

		try {
			createShortUrl(request, content);
		} catch(Exception e) {
			logger.warn("shortUrl 생성을 실패했습니다.");
		}

		return "redirect:/admin/content/list";
	}

	/**
	 * 관리자가 작성했던 포스트를 수정한다.
	 *
	 * @param contentId
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/modify/{contentId}", method = RequestMethod.GET)
	public String modify(@PathVariable String contentId, Model model) throws Exception {
		ShortUrl shortUrl = null;
		List<File> files = null;

		Content content = contentService.get(contentId, "Y");

		String htmlEscapeContent = HtmlUtil.escapeHtml(content.getContent());
		content.setContent(htmlEscapeContent);

		ServiceShortUrl serviceShortUrl = serviceShortUrlService.get("content", content.getContentNo());

		if(serviceShortUrl != null) {
			shortUrl = shortUrlService.get(serviceShortUrl.getShortUrlNo());
		}

		List<ServiceFile> serviceFiles = serviceFileService.getFiles("content", content.getContentNo());

		if(serviceFiles.size() > 0) {
			List<Integer> fileNos = new ArrayList<>();

			for(ServiceFile serviceFile : serviceFiles) {
				fileNos.add(serviceFile.getFileNo());
			}

			files = fileService.getList(fileNos);
		}

		model.addAttribute("content", content);
		model.addAttribute("shortUrl", shortUrl);
		model.addAttribute("files", files);

		model.addAttribute("formAction", "modifyUpdate");
		model.addAttribute("formSubmit", "수정");

		template.setTitle(content.getContentTitle() + " : Content Modify &dash; Kurien's Blog");
		template.getCss().add("<link rel=\"stylesheet\" href=\"/css/module/content.css\">");

		return "admin/content/admin/write";
	}

	/**
	 * 관리자가 수정한 포스트를 저장한다.
	 *
	 * @param content
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/modifyUpdate", method = RequestMethod.POST)
	public String modify(Content content, Integer[] fileNos, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(content.getContentView() == null) {
			content.setContentView(ContentViewStatus.FALSE);
		}

		contentService.update(content, fileNos);

		return "redirect:/admin/content/list";
	}

	/**
	 * 관리자가 작성했던 포스트를 삭제한다.
	 *
	 * @param contentId
	 * @return
	 */
	@RequestMapping(value = "/delete/{contentId}", method = RequestMethod.GET)
	public String delete(@PathVariable String contentId) {
		contentService.delete(contentId);

		return "redirect:/admin/content/list";
	}

	@RequestMapping(value = "/preview/{contentId}", method = RequestMethod.GET)
	public String preview(@PathVariable String contentId, Model model) throws Exception {
		Content content = contentService.get(contentId, "Y");

		ShortUrl shortUrl = null;
		ServiceShortUrl serviceShortUrl = serviceShortUrlService.get("content", content.getContentNo());

		if(serviceShortUrl != null) {
			shortUrl = shortUrlService.get(serviceShortUrl.getShortUrlNo());
		}

		model.addAttribute("content", content);
		model.addAttribute("shortUrl", shortUrl);

		template.setSubTitle(content.getContentTitle());
		template.setDescription(HtmlUtil.stripHtml(content.getContent()));
		template.getCss().add("<link rel=\"stylesheet\" href=\"/css/module/content.css\">");

		return "content/view";
	}

	@RequestMapping(value = "/deleteFile/{fileNo}")
	public @ResponseBody JsonObject deleteFile(@PathVariable int fileNo) {
		JsonObject json = new JsonObject();

		try {
			serviceFileService.remove(fileNo);
		} catch(NotFoundDataException e) {
			e.printStackTrace();
			json.addProperty("result", "fail");
			json.add("value", new JsonObject());
			json.addProperty("message", e.getMessage());

			return json;
		} catch (Exception e) {
			e.printStackTrace();
			json.addProperty("result", "fail");
			json.add("value", new JsonObject());
			json.addProperty("message", "알 수 없는 오류가 발생했습니다.");

			return json;
		}

		json.addProperty("result", "success");
		json.add("value", new JsonObject());
		json.addProperty("message", "");

		return json;
	}

	private void createShortUrl(HttpServletRequest request, Content content) {
		// TODO Auto-generated method stub
		String url = "https://" + request.getServerName() + request.getContextPath() + "/content/view/" + content.getContentNo();

		ShortUrl shortUrl = new ShortUrl();
		shortUrl.setRealUrl(url);
		shortUrl.setCreateIp(RequestUtil.getRemoteAddr(request));

		shortUrlService.set(shortUrl);

		ServiceShortUrl serviceShortUrl = new ServiceShortUrl();
		serviceShortUrl.setServiceName("post");
		serviceShortUrl.setServiceNo(content.getContentNo());
		serviceShortUrl.setShortUrlNo(shortUrl.getShortUrlNo());
		serviceShortUrl.setCreateIp(RequestUtil.getRemoteAddr(request));

		serviceShortUrlService.add(serviceShortUrl);
	}
}
