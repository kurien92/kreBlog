package net.kurien.blog.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/file")
public class FileController {
	@RequestMapping("/viewer/{service}/{fileNo}")
	public void imageFileView(@PathVariable String service, @PathVariable int fileNo, HttpServletRequest request, HttpServletResponse response) throws IOException {

		String uploadPath = request.getServletContext().getRealPath("/files/" + service + "/img");

		// TODO: DB 연동 후에 변경할 부분
		String fileName = uploadPath + File.separator + "temp.png";
		
		Path filePath = Paths.get(fileName);
		String mimeType = Files.probeContentType(filePath);

		response.setContentType(mimeType);
		
		FileInputStream fis = new FileInputStream(fileName);
		BufferedInputStream bis = new BufferedInputStream(fis);
		OutputStream out = response.getOutputStream();
		
		int readCount = 0;
		byte[] buffer = new byte[1024];
		
		while((readCount = bis.read(buffer)) != -1) {
			out.write(buffer, 0, readCount);
		}
		
		out.flush();
		bis.close();
	}
}
