package net.kurien.blog.module.iPAddress.validation;

import org.springframework.stereotype.Component;

import net.kurien.blog.module.iPAddress.exception.NotSetupIPv4Exception;
import net.kurien.blog.module.iPAddress.exception.NotUseIPv4Exception;

@Component
public class IPAddressValidation {
	public void checkIPv4Setup() throws NotSetupIPv4Exception, NotUseIPv4Exception {
		String setupIPv4 = System.getProperty("java.net.preferIPv4Stack");

		if(setupIPv4 == null) {
			throw new NotSetupIPv4Exception("시스템을 사용하려면 IPv4를 설정해주십시오.");
		}
		
		if(!setupIPv4.equals("true")) {
			throw new NotUseIPv4Exception("시스템을 사용하려면 IPv4 설정 값을 true로 변경해주십시오.");
		}
	}
}
