package net.kurien.blog.module.autosave.dao;

import net.kurien.blog.module.autosave.entity.Autosave;

import java.util.List;

public interface AutosaveDao {
    Autosave selectOne(Long asNo);

    List<Autosave> selectList(List<Long> asNos);

    void insert(Autosave autosave);

    void delete(Long asNo);

    int selectCount();

    int selectCount(Long asNo);

    void deleteAll();
}
