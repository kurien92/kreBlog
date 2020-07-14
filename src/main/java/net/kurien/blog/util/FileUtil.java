package net.kurien.blog.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileUtil {
	public static void upload(String uploadFile, byte[] fileBytes) throws IOException {
		OutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(uploadFile);
			outputStream.write(fileBytes);
		} finally {
			outputStream.close();
		}
	}

	public static void delete(File deleteFile) {
		deleteFile.delete();
	}

	public static void view(String filePath, HttpServletResponse response) throws IOException {
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		OutputStream outputStream = response.getOutputStream();

		File file = new File(filePath);

		if(file.exists() == false) {
			throw new FileNotFoundException("파일이 업로드 되지 않았거나 삭제되었습니다.");
		}

		String mimeType = FileUtil.getMimeType(filePath);

		response.setContentType(mimeType);

		try {
			fis = new FileInputStream(filePath);
			bis = new BufferedInputStream(fis);

			int readCount = 0;
			byte[] buffer = new byte[1024];

			while((readCount = bis.read(buffer)) != -1) {
				outputStream.write(buffer, 0, readCount);
			}

			outputStream.flush();
		} finally {
			outputStream.close();
			bis.close();
			fis.close();
		}
	}

	public static String getExtension(String filename) {
		String[] splitExtension = filename.split("\\.");

		if(splitExtension.length <= 1) {
			// 확장자가 없는 파일은 업로드를 거부한다.
			return null;
		}

		return splitExtension[splitExtension.length-1];
	}

	public static String getRandomizeString(String filename) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");

		String randomizeName = UUID.randomUUID().toString() + filename;
		md.update(randomizeName.getBytes());

		byte[] mdBytes = md.digest();

		StringBuffer sb = new StringBuffer();

		for(int i = 0; i < mdBytes.length; i++) {
			sb.append(Integer.toString((mdBytes[i]&0xff) + 0x100, 16).substring(1));
		}

		return sb.toString() + "." + getExtension(filename);
	}

	public static String getMimeType(String filename) throws IOException {
		File file = new File(filename);

		if(file.exists() == false) {
			throw new FileNotFoundException("파일이 업로드 되지 않았거나 삭제되었습니다.");
		}

		Path path = Paths.get(filename);

		String mimeType = Files.probeContentType(path);

		return mimeType;
	}

    public static void download(String filePath, String realFileName, HttpServletRequest request, HttpServletResponse response) throws IOException {
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		OutputStream outputStream = response.getOutputStream();

		File file = new File(filePath);

		if(file.exists() == false) {
			throw new FileNotFoundException("파일이 업로드 되지 않았거나 삭제되었습니다.");
		}

		String fileName = null;

		String userAgent = request.getHeader("User-Agent");

		if(userAgent.indexOf("MSIE") > -1 || userAgent.indexOf("Trident") > -1) {
			fileName = URLEncoder.encode(realFileName, "UTF-8").replaceAll("\\+", "%20");
		} else {
			fileName = new String(realFileName.getBytes("UTF-8"), "8859_1");
		}

		String mimeType = FileUtil.getMimeType(filePath);

		response.setContentType(mimeType);
		response.setContentLengthLong(file.length());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");

		try {
			fis = new FileInputStream(filePath);
			bis = new BufferedInputStream(fis);

			int readCount = 0;
			byte[] buffer = new byte[1024];

			while((readCount = bis.read(buffer)) != -1) {
				outputStream.write(buffer, 0, readCount);
			}

			outputStream.flush();
		} finally {
			outputStream.close();
			bis.close();
			fis.close();
		}
    }
}
