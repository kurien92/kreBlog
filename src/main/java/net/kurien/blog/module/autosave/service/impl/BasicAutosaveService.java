package net.kurien.blog.module.autosave.service.impl;

import net.kurien.blog.module.autosave.dao.AutosaveDao;
import net.kurien.blog.module.autosave.entity.Autosave;
import net.kurien.blog.module.autosave.service.AutosaveService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class BasicAutosaveService implements AutosaveService {
    @Inject
    private AutosaveDao autosaveDao;

    @Override
    public Autosave get(Long atNo) {
        return autosaveDao.selectOne(atNo);
    }

    @Override
    public void save(Autosave autosave) {
        autosaveDao.insert(autosave);
    }

    @Override
    public void remove(Long atNo) {
        autosaveDao.delete(atNo);
    }

    @Override
    public int count() {
        return autosaveDao.selectCount();
    }

    @Override
    public void removeAll() {
        autosaveDao.deleteAll();
    }
}
