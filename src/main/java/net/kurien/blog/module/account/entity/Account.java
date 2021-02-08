package net.kurien.blog.module.account.entity;

import lombok.Data;
import net.kurien.blog.common.type.TrueFalseType;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@Data
public class Account {
    private Integer accountNo;
    private String accountId;
    private String accountPassword;
    private String accountEmail;
    private String accountNickname;
    private TrueFalseType accountBlock;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date accountSignUpDate;
    private String accountSignUpIp;
    private TrueFalseType accountCertYn;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date accountCertDate;
    private String accountCertKey;
}