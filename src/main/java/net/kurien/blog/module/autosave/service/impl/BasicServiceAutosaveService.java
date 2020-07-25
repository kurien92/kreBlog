package net.kurien.blog.module.autosave.service.impl;

import net.kurien.blog.module.autosave.dao.ServiceAutosaveDao;
import net.kurien.blog.module.autosave.entity.ServiceAutosave;
import net.kurien.blog.module.autosave.service.ServiceAutosaveService;
import net.kurien.blog.util.TimeUtil;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class BasicServiceAutosaveService implements ServiceAutosaveService {
    @Inject
    private ServiceAutosaveDao serviceAutosaveDao;

    @Override
    public void add(ServiceAutosave serviceAutosave) {
        serviceAutosave.setServiceAsWriteTime(TimeUtil.currentTime());

        serviceAutosaveDao.insert(serviceAutosave);
    }

    @Override
    public List<ServiceAutosave> getList(String serviceName, String serviceAsUsername) {
        return serviceAutosaveDao.selectByServiceNameAndAsUsername(serviceName, serviceAsUsername);
    }

    @Override
    public List<ServiceAutosave> getList(Long asNo) {
        return serviceAutosaveDao.selectByAsNo(asNo);
    }

    @Override
    public void remove(String serviceName, String serviceAsUsername) {
        serviceAutosaveDao.deleteByServiceNameAndAsUsername(serviceName, serviceAsUsername);
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
