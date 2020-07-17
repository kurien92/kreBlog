package net.kurien.blog.module.autosave.service.impl;

import net.kurien.blog.module.autosave.dao.ServiceAutosaveDao;
import net.kurien.blog.module.autosave.entity.ServiceAutosave;
import net.kurien.blog.module.autosave.service.ServiceAutosaveService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class BasicServiceAutosaveService implements ServiceAutosaveService {
    @Inject
    private ServiceAutosaveDao serviceAutosaveDao;

    @Override
    public void add(ServiceAutosave serviceAutosave) {
        serviceAutosaveDao.insert(serviceAutosave);
    }

    @Override
    public List<ServiceAutosave> get(String serviceName, Long serviceNo) {
        return serviceAutosaveDao.selectByServiceNameAndServiceNo(serviceName, serviceNo);
    }

    @Override
    public List<ServiceAutosave> get(Long asNo) {
        return serviceAutosaveDao.selectByAsNo(asNo);
    }

    @Override
    public void remove(String serviceName, Long serviceNo) {
        serviceAutosaveDao.deleteByServiceNameAndServiceNo(serviceName, serviceNo);
    }

    @Override
    public void remove(Long asNo) {
        serviceAutosaveDao.deleteByAsNo(asNo);
    }

    @Override
    public int count() {
        return serviceAutosaveDao.selectCount();
    }

    @Override
    public void removeAll() {
        serviceAutosaveDao.deleteAll();
    }
}
