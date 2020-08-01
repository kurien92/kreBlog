package net.kurien.blog.controller.admin;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import net.kurien.blog.module.file.dto.FileDTO;
import net.kurien.blog.module.file.entity.File;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

	@RequestMapping(value = "/upload/ckeditor/{service}", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody JsonObject ckeditorImageUpload(@PathVariable String service, MultipartHttpServletRequest multiFile, HttpServletResponse response) throws Exception {
		JsonObject json = new JsonObject();
		
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

		String uploadPath = multiFile.getServletContext().getRealPath("/") + "../../files/" + service;

		FileDTO fileDto = new FileDTO();

		fileDto.setBytes(file.getBytes());
		fileDto.setFileName(file.getOriginalFilename());
		fileDto.setSize(file.getSize());
		fileDto.setContentType(file.getContentType());

		fileDto = fileService.upload(uploadPath, service, fileDto, RequestUtil.getRemoteAddr(multiFile));

        String fileUrl = multiFile.getContextPath() + "/file/viewer/" + service + "/" + fileDto.getNo();
        
        json.addProperty("uploaded", 1);
		json.addProperty("fileName", fileDto.getFileName());
		json.addProperty("fileSize", fileDto.getSize());
        json.addProperty("url", fileUrl);
        json.addProperty("fileNo", fileDto.getNo());
        
		return json;
	}

	@RequestMapping(value = "/upload/{service}", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody JsonObject fileUpload(@PathVariable String service, MultipartHttpServletRequest multiFile) throws IOException, NoSuchAlgorithmException {
		JsonObject json = new JsonObject();

		List<MultipartFile> files = multiFile.getFiles("uploadFiles");
		List<FileDTO> fileDtos = new ArrayList<>();
		JsonArray fileJsonArray = new JsonArray();

		if(files == null) {
			return null;
		}

		for(MultipartFile file : files) {
			if(file == null) {
				return null;
			}

			if(file.getSize() < 0) {
				return null;
			}

			if(StringUtils.isBlank(file.getOriginalFilename())) {
				return null;
			}

			FileDTO fileDto = new FileDTO();

			fileDto.setBytes(file.getBytes());
			fileDto.setFileName(file.getOriginalFilename());
			fileDto.setSize(file.getSize());
			fileDto.setContentType(file.getContentType());

			fileDtos.add(fileDto);
		}

		String uploadPath = multiFile.getServletContext().getRealPath("/") + "../../files/" + service;

		fileDtos = fileService.upload(uploadPath, service, fileDtos, RequestUtil.getRemoteAddr(multiFile));

		for(FileDTO fileDto : fileDtos) {
			JsonObject fileJsonObject = new JsonObject();

			String fileUrl = multiFile.getContextPath() + "/file/viewer/" + service + "/" + fileDto.getNo();
			fileJsonObject.addProperty("fileNo", fileDto.getNo());
			fileJsonObject.addProperty("fileName", fileDto.getFileName());
			fileJsonObject.addProperty("fileSize", fileDto.getSize());
			fileJsonObject.addProperty("url", fileUrl);

			fileJsonArray.add(fileJsonObject);
		}

		json.addProperty("result", "success");
		json.add("value", fileJsonArray);
		json.addProperty("message", "");

		return json;
	}

	@RequestMapping(value = "/list/{serviceName}", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody JsonObject fileList(@PathVariable String serviceName, @RequestParam(value="fileNos[]", required = false) Integer[] fileNos, HttpServletRequest request) {
		JsonObject json = new JsonObject();

		if(fileNos == null) {
			json.addProperty("result", "success");
			json.add("value", new JsonArray());
			json.addProperty("message", "");

			return json;
		}

		List<Integer> fileNoList = Arrays.asList(fileNos);
		List<File> files = fileService.getList(fileNoList);

		JsonArray fileJsonArray = new JsonArray();

		for(int i = 0; i < files.size(); i++) {
			File file = files.get(i);

			JsonObject fileObject = new JsonObject();

			String fileUrl = request.getContextPath() + "/file/viewer/" + serviceName + "/" + file.getFileNo();

			fileObject.addProperty("fileNo", file.getFileNo());
			fileObject.addProperty("fileName", file.getFileName());
			fileObject.addProperty("fileSize", file.getFileSize());
			fileObject.addProperty("fileNo", file.getFileNo());
			fileObject.addProperty("url", fileUrl);

			fileJsonArray.add(fileObject);
		}

		json.addProperty("result", "success");
		json.add("value", fileJsonArray);
		json.addProperty("message", "");

		return json;
	}
}
