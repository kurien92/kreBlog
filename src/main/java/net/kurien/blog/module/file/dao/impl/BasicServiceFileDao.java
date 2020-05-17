package net.kurien.blog.module.file.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import net.kurien.blog.module.file.dao.ServiceFileDao;
import net.kurien.blog.module.file.entity.ServiceFile;

@Repository
public class BasicServiceFileDao implements ServiceFileDao {
	@Inject
	private SqlSession sqlSession;
	
    private final static String mapper = "net.kurien.blog.module.serviceFile.mapper";
    
	@Override
	public ServiceFile selectOne(String serviceName, int serviceNo, int fileNo) {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<>();
		param.put("serviceName", serviceName);
		param.put("serviceNo", serviceNo);
		param.put("fileNo", fileNo);

		return sqlSession.selectOne(mapper + ".selectOne", param);
	}

	@Override
	public List<ServiceFile> selectList(String serviceName, int serviceNo) {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<>();
		param.put("serviceName", serviceName);
		param.put("serviceNo", serviceNo);

		return sqlSession.selectList(mapper + ".selectList", param);
	}

	@Override
	public void insert(ServiceFile serviceFile) {
		// TODO Auto-generated method stub
		sqlSession.insert(mapper + ".insert", serviceFile);
	}

	@Override
	public void insertFiles(List<ServiceFile> serviceFiles) {
		// TODO Auto-generated method stub
		sqlSession.insert(mapper + ".insertFiles", serviceFiles);
	}

	@Override
	public void delete(String serviceName, int serviceNo, int fileNo) {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<>();
		param.put("serviceName", serviceName);
		param.put("serviceNo", serviceNo);
		param.put("fileNo", fileNo);
		
		sqlSession.delete(mapper + ".delete", param);
	}

	@Override
	public void deleteList(String serviceName, int serviceNo) {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<>();
		param.put("serviceName", serviceName);
		param.put("serviceNo", serviceNo);

		sqlSession.delete(mapper + ".deleteList", param);
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		sqlSession.delete(mapper + ".deleteAll");
	}
}
