package net.kurien.blog.module.file.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import net.kurien.blog.module.file.dao.ServiceFileDao;
import net.kurien.blog.module.file.service.ServiceFileService;
import net.kurien.blog.module.file.vo.ServiceFile;

@Service
public class BasicServiceFileService implements ServiceFileService {
	@Inject
	private ServiceFileDao serviceFileDao;
	
	@Override
	public ServiceFile get(String serviceName, int serviceNo, int fileNo) {
		// TODO Auto-generated method stub
		return serviceFileDao.selectOne(serviceName, serviceNo, fileNo);
	}

	@Override
	public List<ServiceFile> getFiles(String serviceName, int serviceNo) {
		// TODO Auto-generated method stub
		return serviceFileDao.selectList(serviceName, serviceNo);
	}

	@Override
	public void add(ServiceFile serviceFile) {
		// TODO Auto-generated method stub
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
			ServiceFile serviceFile = new ServiceFile();
			serviceFile.setServiceName(serviceName);
			serviceFile.setServiceNo(serviceNo);
			serviceFile.setFileNo(fileNo);
			serviceFile.setServiceFileWriteTime(today);
			serviceFile.setServiceFileWriteIp(serviceFileWriteIp);
			
			serviceFiles.add(serviceFile);
		}

		serviceFileDao.insertFiles(serviceFiles);
	}

	@Override
	public void remove(String serviceName, int serviceNo, int fileNo) {
		// TODO Auto-generated method stub
		serviceFileDao.delete(serviceName, serviceNo, fileNo);
	}

	@Override
	public void removeFiles(String serviceName, int serviceNo) {
		// TODO Auto-generated method stub
		serviceFileDao.deleteList(serviceName, serviceNo);
	}

	@Override
	public void removeAll() {
		// TODO Auto-generated method stub
		serviceFileDao.deleteAll();
	}
}
