package net.kurien.blog.module.autosave.dao;

import net.kurien.blog.module.autosave.entity.Autosave;

public interface AutosaveDao {
    Autosave selectOne(Long asNo);

    void insert(Autosave autosave);

    void delete(Long asNo);

    int selectCount();

    void deleteAll();
}
