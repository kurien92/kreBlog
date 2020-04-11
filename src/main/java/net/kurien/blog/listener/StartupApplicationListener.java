package net.kurien.blog.listener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import net.kurien.blog.module.iPAddress.exception.NotSetupIPv4Exception;
import net.kurien.blog.module.iPAddress.exception.NotUseIPv4Exception;
import net.kurien.blog.module.iPAddress.validation.IPAddressValidation;

@Component
public class StartupApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
	private static final Logger logger = LoggerFactory.getLogger(StartupApplicationListener.class);
	
	@Inject
	private IPAddressValidation iPAddressValidation; 
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		
		// 서버 시간은 한국 시간으로 설정한다.
		// 도커에서 Tomcat을 돌리는 경우 실제서버 설정과 관계 없이 UTC 표준시간으로 설정됨.
		logger.info("user Timezone setup - Asia/Seoul");
		//TimeZone을 null로 설정해야 캐시된 데이터가 삭제되어 user.timezone 설정이 적용된다.
		TimeZone.setDefault(null); 
		System.setProperty("user.timezone", "Asia/Seoul");
		
		Date date = Calendar.getInstance().getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("[yyyy/MM/dd HH:mm:ss.SSS]");
		String formattedDate = sdf.format(date);

		logger.info("[" + formattedDate + "] Startup Application");
		
		// 설정 검증 시작
		logger.info("Check setup start");
		
		try {
			logger.info("Check setup - IPv4");
			
			//IPv4로 설정한다.
			System.setProperty("java.net.preferIPv4Stack", "true");
			
			//IPv4로 설정되어있는지 체크한다.
			iPAddressValidation.checkIPv4Setup();
		} catch (NotSetupIPv4Exception e) {
			// TODO Auto-generated catch block
			logger.error("" + e.getMessage());
		} catch (NotUseIPv4Exception e) {
			// TODO Auto-generated catch block
			logger.error("" + e.getMessage());
			try {
				throw new Exception(e.getMessage());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		logger.info("Check setup done");
		
		// 서버 구동 시 실행되어야 할 메서드 실행
		logger.info("Execute startup method start");
		
		logger.info("Execute startup method done");
	}
}
