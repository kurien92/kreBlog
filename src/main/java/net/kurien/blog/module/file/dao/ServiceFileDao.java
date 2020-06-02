package net.kurien.blog.module.file.dao;

import java.util.List;

import net.kurien.blog.module.file.entity.ServiceFile;

public interface ServiceFileDao {
	public ServiceFile selectOne(String serviceName, int serviceNo, int fileNo);
	public List<ServiceFile> selectList(String serviceName, int serviceNo);
	public void insert(ServiceFile serviceFile);
	public void insertFiles(List<ServiceFile> serviceFiles);
	public void delete(String serviceName, int serviceNo, int fileNo);
	public void deleteList(String serviceName, int serviceNo, Integer[] fileNos);
	public void deleteList(String serviceName, int serviceNo);
	public void deleteAll();
}
