package net.kurien.blog.module.file.dao;

import java.util.List;

import net.kurien.blog.module.file.entity.ServiceFile;

public interface ServiceFileDao {
	public ServiceFile selectOne(String serviceName, Integer serviceNo, Integer fileNo);
	public List<ServiceFile> selectList(String serviceName, Integer serviceNo);
	public int selectCount(Integer fileNo);
	public int selectCount(String serviceName, Integer serviceNo);
	public void insert(ServiceFile serviceFile);
	public void insertFiles(List<ServiceFile> serviceFiles);
	public void delete(Integer fileNo);
	public void delete(String serviceName, Integer serviceNo, Integer fileNo);
	public void deleteList(String serviceName, Integer serviceNo, Integer[] fileNos);
	public void deleteList(String serviceName, Integer serviceNo);
	public void deleteAll();

}
