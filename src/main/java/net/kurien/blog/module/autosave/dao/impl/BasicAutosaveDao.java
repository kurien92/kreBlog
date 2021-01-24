package net.kurien.blog.module.autosave.dao.impl;

import net.kurien.blog.module.autosave.dao.AutosaveDao;
import net.kurien.blog.module.autosave.entity.Autosave;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BasicAutosaveDao implements AutosaveDao {
    private final SqlSession sqlSession;
    private final String mapper = "net.kurien.blog.module.autosave.mapper.AutosaveMapper";

    @Autowired
    public BasicAutosaveDao(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public Autosave selectOne(Long asNo) {
        return sqlSession.selectOne(mapper + ".selectOne", asNo);
    }

    @Override
    public List<Autosave> selectList(List<Long> asNos) {
        return sqlSession.selectList(mapper + ".selectList", asNos);
    }

    @Override
    public void insert(Autosave autosave) {
        sqlSession.insert(mapper + ".insert", autosave);
    }

    @Override
    public void delete(Long asNo) {
        sqlSession.delete(mapper + ".delete", asNo);
    }

    @Override
    public int selectCount() {
        return sqlSession.selectOne(mapper + ".selectCount");
    }

    @Override
    public int selectCount(Long asNo) {
        return sqlSession.selectOne(mapper + ".selectCount", asNo);
    }

    @Override
    public void deleteAll() {
        sqlSession.delete(mapper + ".deleteAll");
    }
}
