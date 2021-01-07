package net.kurien.blog.module.file.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import com.google.gson.JsonObject;
import net.kurien.blog.exception.NotFoundDataException;
import org.springframework.stereotype.Service;

import net.kurien.blog.module.file.dao.ServiceFileDao;
import net.kurien.blog.module.file.entity.ServiceFile;
import net.kurien.blog.module.file.service.FileService;
import net.kurien.blog.module.file.service.ServiceFileService;

@Service
public class BasicServiceFileService implements ServiceFileService {
	@Inject
	private ServiceFileDao serviceFileDao;
	
	@Inject
	private FileService fileService;
	
	@Override
	public ServiceFile get(String serviceName, Integer serviceNo, Integer fileNo) {
		// TODO Auto-generated method stub
		return serviceFileDao.selectOne(serviceName, serviceNo, fileNo);
	}

	@Override
	public List<ServiceFile> getFiles(String serviceName, Integer serviceNo) {
		// TODO Auto-generated method stub
		return serviceFileDao.selectList(serviceName, serviceNo);
	}

	@Override
	public int getCount(Integer fileNo) {
		return serviceFileDao.selectCount(fileNo);
	}

	@Override
	public int getCount(String serviceName, Integer serviceNo) {
		return serviceFileDao.selectCount(serviceName, serviceNo);
	}

	@Override
	public void add(ServiceFile serviceFile) {
		// TODO Auto-generated method stub
		if(fileService.getCount(serviceFile.getFileNo()) == 0) {
			return;
		}
		
		Timestamp today = new Timestamp(Calendar.getInstance().getTimeInMillis());
		today.setNanos(0);
		
		serviceFile.setServiceFileWriteTime(today);
		serviceFileDao.insert(serviceFile);
	}

	@Override
	public void addFiles(String serviceName, Integer serviceNo, Integer[] fileNos, String serviceFileWriteIp) {
		// TODO Auto-generated method stub
		Timestamp today = new Timestamp(Calendar.getInstance().getTimeInMillis());
		today.setNanos(0);
		
		List<ServiceFile> serviceFiles = new ArrayList<>();
		
		for(Integer fileNo : fileNos) {
			if(fileService.getCount(fileNo) == 0) {
				continue;
			}
			
			ServiceFile serviceFile = new ServiceFile();
			serviceFile.setServiceName(serviceName);
			serviceFile.setServiceNo(serviceNo);
			serviceFile.setFileNo(fileNo);
			serviceFile.setServiceFileWriteTime(today);
			serviceFile.setServiceFileWriteIp(serviceFileWriteIp);
			
			serviceFiles.add(serviceFile);
		}

		if(serviceFiles.size() == 0) {
			return;
		}
		
		serviceFileDao.insertFiles(serviceFiles);
	}

	@Override
	public void remove(Integer fileNo) throws Exception {
		int fileCount = fileService.getCount(fileNo);
		int serviceFileCount = serviceFileDao.selectCount(fileNo);

		if(fileCount == 0 && serviceFileCount == 0) {
			throw new NotFoundDataException("파일이 업로드 되지 않았거나 삭제되었습니다.");
		}

		if(fileCount > 0) {
			fileService.delete(fileNo);
		}

		if(serviceFileCount > 0) {
			serviceFileDao.delete(fileNo);
		}
	}

	@Override
	public void remove(String serviceName, Integer serviceNo, Integer fileNo) {
		// TODO Auto-generated method stub
		serviceFileDao.delete(serviceName, serviceNo, fileNo);
	}

	@Override
	public void removeFilesByNo(String serviceName, Integer serviceNo, Integer[] fileNos) {
		// TODO Auto-generated method stub
		serviceFileDao.deleteList(serviceName, serviceNo, fileNos);
	}

	@Override
	public void removeFiles(String serviceName) {
		// TODO Auto-generated method stub
		serviceFileDao.deleteList(serviceName);
	}

	@Override
	public void removeFiles(String serviceName, Integer serviceNo) {
		// TODO Auto-generated method stub
		serviceFileDao.deleteList(serviceName, serviceNo);
	}

	@Override
	public void removeAll() {
		// TODO Auto-generated method stub
		serviceFileDao.deleteAll();
	}

	@Override
	public void syncFiles(String serviceName, Integer serviceNo, Set<Integer> useFileNos, String serviceFileWriteIp) {
		// TODO Auto-generated method stub
		Timestamp today = new Timestamp(Calendar.getInstance().getTimeInMillis());
		today.setNanos(0);
		
		List<ServiceFile> serviceFiles = serviceFileDao.selectList(serviceName, serviceNo);

		Set<Integer> removeFileNos = new HashSet<>();
		
		Integer[] addFileNosArray = null;
		Integer[] removeFileNosArray = null;
		
		for(ServiceFile serviceFile : serviceFiles) {
			// 저장된 값이 있는 경우 그대로 유지한다.
			if(useFileNos.contains(serviceFile.getFileNo())) {
				useFileNos.remove(serviceFile.getFileNo());
				continue;
			}
		
			// 저장된 값이 없는 경우 삭제할 목록에 추가한다.
			removeFileNos.add(serviceFile.getFileNo());
		}
		
		if(useFileNos.size() > 0) {
			// 추가할 항목이 있다면 추가한다.
			addFileNosArray = useFileNos.toArray(new Integer[useFileNos.size()]);
			addFiles(serviceName, serviceNo, addFileNosArray, serviceFileWriteIp);
		}
		
		if(removeFileNos.size() > 0) {
			removeFileNosArray = removeFileNos.toArray(new Integer[removeFileNos.size()]);
			removeFilesByNo(serviceName, serviceNo, removeFileNosArray);
		}
	}
}