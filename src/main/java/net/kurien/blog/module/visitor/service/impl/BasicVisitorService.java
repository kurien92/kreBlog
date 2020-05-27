package net.kurien.blog.module.visitor.service.impl;

import java.util.Calendar;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import net.kurien.blog.module.visitor.dao.BasicVisitorDao;
import net.kurien.blog.module.visitor.entity.Visitor;
import net.kurien.blog.module.visitor.service.VisitorService;

@Service
public class BasicVisitorService implements VisitorService {
	@Inject
	private BasicVisitorDao visitorDao;
	
	public void collect(Visitor visitor) {
		visitor.setVisitTime(Calendar.getInstance().getTime());
		visitorDao.insert(visitor);
	}
}
