package net.kurien.blog.controller.admin;

import java.io.PrintWriter;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.JsonObject;

import net.kurien.blog.module.file.service.FileService;

@Controller
@RequestMapping("/admin/file")
public class FileAdminController {
	private static final Logger logger = LoggerFactory.getLogger(FileAdminController.class);
	
	@Inject
	private FileService fileService;
	
	@RequestMapping(value = "/upload/{service}")
	@ResponseBody
	public String ckeditorImageUpload(MultipartHttpServletRequest multiFile, @PathVariable String service, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JsonObject json = new JsonObject();
		PrintWriter printWriter = null;
		
		MultipartFile file = multiFile.getFile("upload");
		
		if(file == null) {
			return null;
		}
		
		if(file.getSize() < 0) {
			return null;
		}
		
		String fileName = file.getOriginalFilename();
		
		if(StringUtils.isBlank(fileName)) {
			return null;
		}
		
		if(file.getContentType().toLowerCase().startsWith("image/") == false) {
			return null;
		}

		String uploadPath = request.getServletContext().getRealPath("/") + "../../files/" + service;
		
		int fileNo = fileService.upload(uploadPath, service, file.getBytes(), fileName, file.getSize(), file.getContentType(), request.getRemoteAddr());
		
        printWriter = response.getWriter();
        response.setContentType("text/html");
        
		// TODO: DB 연동 후에 변경 할 부분
        String fileUrl = request.getContextPath() + "/file/viewer/" + service + "/" + fileNo;
        
        json.addProperty("uploaded", 1);
        json.addProperty("fileName", fileName);
        json.addProperty("url", fileUrl);
        json.addProperty("fileNo", fileNo);
        
        printWriter.println(json);
        
		return null;
	}
	
	public void fileDownload() {
		
	}
}