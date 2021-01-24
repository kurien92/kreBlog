package net.kurien.blog.controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.blueconic.browscap.Capabilities;
import com.blueconic.browscap.ParseException;

import net.kurien.blog.common.template.Template;
import net.kurien.blog.domain.PageMaker;
import net.kurien.blog.domain.SearchCriteria;
import net.kurien.blog.module.visitor.entity.Visitor;
import net.kurien.blog.module.visitor.service.VisitorService;
import net.kurien.blog.util.UserAgentUtil;

@Controller
@RequestMapping("/admin/visitor")
public class VisitorAdminController {
	private final Template template;
	private final VisitorService visitorService;

	@Autowired
	public VisitorAdminController(Template template, VisitorService visitorService) {
		this.template = template;
		this.visitorService = visitorService;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(SearchCriteria criteria, Model model) throws IOException, ParseException {
		PageMaker pageMaker = new PageMaker();
		criteria.setRowCount(30);
		pageMaker.setCriteria(criteria);
		pageMaker.setTotalRowCount(visitorService.getCount(criteria));
		
		List<Visitor> visitors = visitorService.getList(criteria);

		List<Map<String, Object>> visitorsInfo = new ArrayList<>();
		
		for(int i = 0; i < visitors.size(); i++) {
			Map<String, Object> visitorInfo = new HashMap<>();
			Capabilities capabilities = UserAgentUtil.parseUserAgent(visitors.get(i).getUserAgent());
			
			visitorInfo.put("visitor", visitors.get(i));
			visitorInfo.put("capabilities", capabilities);
			
			visitorsInfo.add(visitorInfo);
		}

		model.addAttribute("visitorsInfo", visitorsInfo);
		model.addAttribute("pageMaker", pageMaker);
		
		template.getCss().add("<link rel=\"stylesheet\" href=\"/css/module/visitor.css\">");
		
		return "admin/visitor/admin/list";
	}
}
