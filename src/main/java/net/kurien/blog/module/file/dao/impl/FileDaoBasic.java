package net.kurien.blog.module.file.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import net.kurien.blog.module.file.dao.FileDao;
import net.kurien.blog.module.file.entity.File;

@Repository
public class FileDaoBasic implements FileDao {
	@Inject
	private SqlSession sqlSession;
	
    private final static String mapper = "net.kurien.blog.module.file.mapper.FileMapper";
    
    @Override
    public List<File> selectList(List<Integer> fileNos) {
        // TODO Auto-generated method stub
        return sqlSession.selectList(mapper + ".selectList", fileNos);
    }

    @Override
    public File selectOne(int fileNo) {
        // TODO Auto-generated method stub
        return sqlSession.selectOne(mapper + ".selectOne", fileNo);
    }

    @Override
    public int selectCount(Integer fileNo) {
        // TODO Auto-generated method stub
        return sqlSession.selectOne(mapper + ".selectCount", fileNo);
    }

	@Override
	public boolean isExistFilename(String randomizeString) {
		// TODO Auto-generated method stub
		int isCount = sqlSession.selectOne(mapper + ".isExistFilename", randomizeString);
		boolean isExist = isCount > 0;
		
		return isExist;
	}
	
    @Override
    public void insert(File file) {
        // TODO Auto-generated method stub
        sqlSession.insert(mapper + ".insert", file);
    }

    @Override
    public void delete(int fileNo) {
        // TODO Auto-generated method stub
        sqlSession.delete(mapper + ".delete", fileNo);
    }

    @Override
    public void deleteList(List<Integer> fileNos) {
        // TODO Auto-generated method stub
        sqlSession.delete(mapper + ".deleteList", fileNos);
    }

    @Override
    public void deleteAll() {
        // TODO Auto-generated method stub
        sqlSession.delete(mapper + ".deleteAll");
    }
}