package net.kurien.blog.controller.admin;

import java.io.PrintWriter;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.JsonObject;

import net.kurien.blog.module.file.service.FileService;
import net.kurien.blog.util.RequestUtil;

@Controller
@RequestMapping("/admin/file")
public class FileAdminController {
	private static final Logger logger = LoggerFactory.getLogger(FileAdminController.class);
	
	@Inject
	private FileService fileService;

	@RequestMapping(value = "/upload/{service}", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody JsonObject ckeditorImageUpload(@PathVariable String service, MultipartHttpServletRequest multiFile, HttpServletResponse response) throws Exception {
		logger.info("ckeditorImageUpload start");
		
		JsonObject json = new JsonObject();
		
		MultipartFile file = multiFile.getFile("upload");
		
		if(file == null) {
			logger.info("ckeditorImageUpload file is null");
			return null;
		}
		
		if(file.getSize() < 0) {
			logger.info("ckeditorImageUpload file size 0");
			return null;
		}

		String fileName = file.getOriginalFilename();
		
		if(StringUtils.isBlank(fileName)) {
			logger.info("ckeditorImageUpload blank filename");
			return null;
		}
		
		if(file.getContentType().toLowerCase().startsWith("image/") == false) {
			logger.info("ckeditorImageUpload file isn't image");
			return null;
		}

		String uploadPath = multiFile.getServletContext().getRealPath("/") + "../../files/" + service;

		long fileSize = file.getSize();

		int fileNo = fileService.upload(uploadPath, service, file.getBytes(), fileName, fileSize, file.getContentType(), RequestUtil.getRemoteAddr(multiFile));

		// TODO: DB 연동 후에 변경 할 부분
        String fileUrl = multiFile.getContextPath() + "/file/viewer/" + service + "/" + fileNo;
        
        json.addProperty("uploaded", 1);
		json.addProperty("fileName", fileName);
		json.addProperty("fileSize", fileSize);
        json.addProperty("url", fileUrl);
        json.addProperty("fileNo", fileNo);

		logger.info("ckeditorImageUpload file upload complete");
        
		return json;
	}
	
	public void fileDownload() {
		
	}
}
