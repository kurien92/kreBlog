package net.kurien.blog.module.file.service.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import net.kurien.blog.module.file.dao.FileDao;
import net.kurien.blog.module.file.entity.File;
import net.kurien.blog.module.file.service.FileService;
import net.kurien.blog.util.FileUtil;

@Service
public class BasicFileService implements FileService {
	@Inject
	private FileDao fileDao;
	
	@Override
	public int upload(String uploadPath, String serviceName, byte[] fileBytes, String originalFilename, long fileSize, String contentType, String uploadIp) throws NoSuchAlgorithmException, IOException {
		// TODO Auto-generated method stub
		String fileExtension = FileUtil.getExtension(originalFilename);
		
		java.io.File uploadFile = new java.io.File(uploadPath);
		
		if(uploadFile.exists() == false) {
			uploadFile.mkdirs();
		}

		String randomizeString = null;
		
		do {
			randomizeString = FileUtil.getRandomizeString(originalFilename);
		} while(fileDao.isExistFilename(randomizeString));
		
		String uploadFilePath = uploadPath + java.io.File.separator + randomizeString;

		FileUtil.upload(uploadFilePath, fileBytes);
		
		Timestamp today = new Timestamp(Calendar.getInstance().getTimeInMillis());
		today.setNanos(0);
		
		File file = new File();
		file.setFileExtension(fileExtension);
		file.setFileMime(contentType);
		file.setFileName(originalFilename);
		file.setFilePath(uploadPath);
		file.setFileSize(fileSize);
		file.setFileStoredName(randomizeString);
		file.setFileType("file");
		file.setFileUploadIp(uploadIp);
		file.setFileUploadTime(today);
		
		fileDao.insert(file);
		
		return file.getFileNo();
	}

	@Override
	public java.io.File download(String serviceName, int serviceNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<File> getList(List<Integer> fileNos) {
		// TODO Auto-generated method stub
		return fileDao.selectList(fileNos);
	}

	@Override
	public File get(int fileNo) {
		// TODO Auto-generated method stub
		return fileDao.selectOne(fileNo);
	}

	@Override
	public int getCount(Integer fileNo) {
		// TODO Auto-generated method stub
		return fileDao.selectCount(fileNo);
	}

	@Override
	public void insert(File file) {
		// TODO Auto-generated method stub
		fileDao.insert(file);
	}

	@Override
	public void delete(int fileNo) throws Exception {
		// TODO Auto-generated method stub
		if(fileDao.selectCount(fileNo) == 0) {
			return;
		}

		File file = fileDao.selectOne(fileNo);

		java.io.File deleteFile = new java.io.File(file.getFilePath() + java.io.File.separator + file.getFileStoredName());

		if(deleteFile.exists() == false) {
			throw new Exception("삭제하려는 파일이 없습니다.");
		}

		if(deleteFile.isDirectory() == true) {
			throw new Exception("폴더는 지울 수 없습니다.");
		}

		// 무조건 deleteFile.isFile() == true임.

		// TODO: 삭제 이력 남기고, 파일은 다른 곳으로 이동하여 보관처리할 것.
		FileUtil.delete(deleteFile);

		fileDao.delete(fileNo);
	}

	@Override
	public void deleteList(List<Integer> fileNos) {
		// TODO Auto-generated method stub
		fileDao.deleteList(fileNos);
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		fileDao.deleteAll();
	}
}