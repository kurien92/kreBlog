package net.kurien.blog.module.visitor.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.kurien.blog.domain.SearchCriteria;
import net.kurien.blog.module.visitor.dao.VisitorDao;
import net.kurien.blog.module.visitor.entity.Visitor;
import net.kurien.blog.module.visitor.service.VisitorService;

@Service
public class BasicVisitorService implements VisitorService {
	private VisitorDao visitorDao;

	@Autowired
	public BasicVisitorService(VisitorDao visitorDao) {
		this.visitorDao = visitorDao;
	}

	public void collect(Visitor visitor) {
		visitor.setVisitTime(Calendar.getInstance().getTime());
		visitorDao.insert(visitor);
	}

	@Override
	public List<Visitor> getList(SearchCriteria criteria) {
		// TODO Auto-generated method stub
		return visitorDao.selectList(criteria);
	}

	@Override
	public Integer getCount(SearchCriteria criteria) {
		// TODO Auto-generated method stub
		return visitorDao.getCount(criteria);
	}
}
