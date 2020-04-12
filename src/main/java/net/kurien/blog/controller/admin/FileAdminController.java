package net.kurien.blog.controller.admin;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.JsonObject;

@Controller
@RequestMapping("/admin/file")
public class FileAdminController {
	private static final Logger logger = LoggerFactory.getLogger(FileAdminController.class);
	
	@RequestMapping(value = "/upload/{service}")
	@ResponseBody
	public String fileUpload(MultipartHttpServletRequest multiFile, @PathVariable String service, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JsonObject json = new JsonObject();
		PrintWriter printWriter = null;
		OutputStream outputStream = null;
		
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
		
		try {
			String[] splitExtension = fileName.split("\\.");
			
			if(splitExtension.length <= 1) {
				// 확장자가 없는 파일은 업로드를 거부한다.
				return null;
			}
			
			String extension = splitExtension[splitExtension.length-1];

			String uploadPath = request.getServletContext().getRealPath("/files/" + service + "/img");
			File uploadFile = new File(uploadPath);
			
			if(uploadFile.exists() == false) {
				uploadFile.mkdirs();
			}

			byte[] bytes = file.getBytes();

			// TODO: DB 연동 후에 활성화 할 부분
//			MessageDigest md = MessageDigest.getInstance("SHA-256");
//			fileName = UUID.randomUUID().toString() + fileName;
//			md.update(fileName.getBytes());
//			
//			byte[] mdBytes = md.digest();
//			
//			StringBuffer sb = new StringBuffer();
//			
//			for(int i = 0; i < mdBytes.length; i++) {
//				sb.append(Integer.toString((bytes[i]&0xff) + 0x100, 16).substring(1));
//			}
//			
//			fileName = sb.toString() + "." + extension;

			uploadPath = uploadPath + File.separator + fileName;
    		
			outputStream = new FileOutputStream(uploadPath);
			
			outputStream.write(bytes);
            
            printWriter = response.getWriter();
            response.setContentType("text/html");
            
			// TODO: DB 연동 후에 변경 할 부분
            String fileUrl = request.getContextPath() + "/file/viewer/" + service + "/1";
            
            json.addProperty("uploaded", 1);
            json.addProperty("fileName", fileName);
            json.addProperty("url", fileUrl);
            
            printWriter.println(json);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if(outputStream != null) {
				outputStream.close();
			}
			
			if(printWriter != null) {
				printWriter.close();
			}
		}
		
		return null;
	}
	
	public void fileDownload() {
		
	}
}
