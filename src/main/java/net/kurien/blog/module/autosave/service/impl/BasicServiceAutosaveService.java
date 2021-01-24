package net.kurien.blog.module.autosave.service.impl;

import net.kurien.blog.module.autosave.dao.ServiceAutosaveDao;
import net.kurien.blog.module.autosave.entity.ServiceAutosave;
import net.kurien.blog.module.autosave.service.ServiceAutosaveService;
import net.kurien.blog.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasicServiceAutosaveService implements ServiceAutosaveService {
    private final ServiceAutosaveDao serviceAutosaveDao;

    @Autowired
    public BasicServiceAutosaveService(ServiceAutosaveDao serviceAutosaveDao) {
        this.serviceAutosaveDao = serviceAutosaveDao;
    }

    @Override
    public void add(ServiceAutosave serviceAutosave) {
        serviceAutosave.setServiceAsWriteTime(TimeUtil.currentTime());

        serviceAutosaveDao.insert(serviceAutosave);
    }

    @Override
    public ServiceAutosave get(String serviceName, Long asNo) {
        return serviceAutosaveDao.selectByAsNo(serviceName, asNo);
    }

    @Override
    public List<ServiceAutosave> getList(String serviceName, String serviceAsUsername) {
        return serviceAutosaveDao.selectByServiceNameAndAsUsername(serviceName, serviceAsUsername);
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

    @Override
    public boolean isExist(Long asNo) {
        return serviceAutosaveDao.selectCount(asNo) == 0 ? false : true;
    }
}
