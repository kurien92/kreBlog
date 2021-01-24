package net.kurien.blog.module.autosave.service.impl;

import net.kurien.blog.module.autosave.dao.AutosaveDao;
import net.kurien.blog.module.autosave.entity.Autosave;
import net.kurien.blog.module.autosave.service.AutosaveService;
import net.kurien.blog.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasicAutosaveService implements AutosaveService {
    private final AutosaveDao autosaveDao;

    @Autowired
    public BasicAutosaveService(AutosaveDao autosaveDao) {
        this.autosaveDao = autosaveDao;
    }

    @Override
    public Autosave get(Long asNo) {
        return autosaveDao.selectOne(asNo);
    }

    @Override
    public List<Autosave> getList(List<Long> asNos) {
        return autosaveDao.selectList(asNos);
    }

    @Override
    public void save(Autosave autosave) {
        autosave.setAsJsonData(autosave.getAsJsonData());
        autosave.setAsSaveTime(TimeUtil.currentTime());

        autosaveDao.insert(autosave);
    }

    @Override
    public void remove(Long asNo) {
        autosaveDao.delete(asNo);
    }

    @Override
    public int count() {
        return autosaveDao.selectCount();
    }

    @Override
    public void removeAll() {
        autosaveDao.deleteAll();
    }

    @Override
    public boolean isExist(Long asNo) {
        return autosaveDao.selectCount(asNo) == 0 ? false : true;
    }
}
