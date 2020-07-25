package net.kurien.blog.module.autosave.dao.impl;

import net.kurien.blog.module.autosave.dao.ServiceAutosaveDao;
import net.kurien.blog.module.autosave.entity.ServiceAutosave;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BasicServiceAutosaveDao implements ServiceAutosaveDao {
    @Inject
    private SqlSession sqlSession;

    private final static String mapper = "net.kurien.blog.module.autosave.mapper.ServiceAutosaveMapper";


    @Override
    public void insert(ServiceAutosave serviceAutosave) {
        sqlSession.insert(mapper + ".insert", serviceAutosave);
    }

    @Override
    public List<ServiceAutosave> selectByServiceNameAndAsUsername(String serviceName, String serviceAsUsername) {
        Map<String, Object> param = new HashMap<>();
        param.put("serviceName", serviceName);
        param.put("serviceAsUsername", serviceAsUsername);

        return sqlSession.selectList(mapper + ".selectByServiceNameAndAsUsername", param);
    }

    @Override
    public List<ServiceAutosave> selectByAsNo(Long asNo) {
        return sqlSession.selectList(mapper + ".selectByAsNo", asNo);
    }

    @Override
    public void deleteByServiceNameAndAsUsername(String serviceName, String serviceAsUsername) {
        Map<String, Object> param = new HashMap<>();
        param.put("serviceName", serviceName);
        param.put("serviceAsUsername", serviceAsUsername);

        sqlSession.delete(mapper + ".deleteByServiceNameAndAsUsername", param);
    }

    @Override
    public void deleteByAsNo(Long asNo) {
        sqlSession.delete(mapper + ".deleteByAsNo", asNo);
    }

    @Override
    public int selectCount() {
        return sqlSession.selectOne(mapper + ".selectCount");
    }

    @Override
    public void deleteAll() {
        sqlSession.delete(mapper + ".deleteAll");
    }
}
