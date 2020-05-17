package net.kurien.blog.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import net.kurien.blog.module.file.entity.File;
import net.kurien.blog.module.file.service.FileService;
import net.kurien.blog.util.FileUtil;

@Controller
@RequestMapping("/file")
public class FileController {
	@Inject
	FileService fileService;
	
	@RequestMapping("/viewer/{service}/{fileNo}")
	public void imageFileView(@PathVariable String service, @PathVariable int fileNo, HttpServletRequest request, HttpServletResponse response) throws IOException {
		File file = fileService.get(fileNo);
		
		String uploadPath = request.getServletContext().getRealPath("/") + "../../files/" + service;
		
		// TODO: DB 연동 후에 변경할 부분
		String filename = uploadPath + java.io.File.separator + file.getFileStoredName();
		
		String mimeType = FileUtil.getMimeType(filename);

		response.setContentType(mimeType);
		
		FileUtil.view(filename, response.getOutputStream());
	}
}
