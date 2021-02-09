package net.kurien.blog.controller;

import com.google.gson.JsonObject;
import net.kurien.blog.common.template.Template;
import net.kurien.blog.exception.InvalidRequestException;
import net.kurien.blog.module.account.entity.Account;
import net.kurien.blog.module.account.service.AccountService;
import net.kurien.blog.util.CertificationUtil;
import net.kurien.blog.util.RequestUtil;
import net.kurien.blog.util.TokenUtil;
import net.kurien.blog.util.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;

@Controller
public class AccountController {
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    private final AccountService accountService;
    private final Template template;

    @Autowired
    public AccountController(AccountService accountService, Template template) {
        this.accountService = accountService;
        this.template = template;
    }

    @RequestMapping(value="/account/signup", method = RequestMethod.GET)
    public String signup(HttpServletRequest request, HttpServletResponse response, Model model) throws NoSuchAlgorithmException {
        template.setSubTitle("Sign up");
        template.getCss().add("<link rel=\"stylesheet\" href=\"/css/module/account.css\">");

        model.addAttribute("token", TokenUtil.createToken(request, "signup", 30 * 60 * 1000));

        return "account/signup";
    }

    @ResponseBody
    @RequestMapping(value="/account/sendCertKey", method = RequestMethod.POST)
    public JsonObject sendCertKey(String accountEmail, HttpServletRequest request) {
        /**
         * 1. 사용자 메일 주소 형태 검증
         * 2. 이미 사용중인 주소인지 확인
         * 3. 사용중이지 않다면 아이디와 인증번호 세션에 저장
         * 4. 저장한 인증번호 발송
         * 5. success 리턴
         */
        JsonObject json = new JsonObject();

        if(ValidationUtil.email(accountEmail) == false) {
            json.addProperty("result", "fail");
            json.add("value", new JsonObject());
            json.addProperty("message", "메일주소 형식이 잘못되었습니다. 확인하신 후 다시 시도하시기 바랍니다.");

            return json;
        }

        String certKey = CertificationUtil.createCertKey(request, "signup", accountEmail, 5,  3 * 60 * 1000);

        accountService.sendCertKey(accountEmail, certKey);

        json.addProperty("result", "success");
        json.add("value", new JsonObject());
        json.addProperty("message", "");

        return json;
    }

    @ResponseBody
    @RequestMapping(value="/account/checkCertKey")
    public JsonObject checkCertKey(String accountEmail, String certKey, HttpServletRequest request) {
        /**
         * 1. 세션에 저장된 인증번호와 이메일과 동일한지 확인
         * 2. 동일하다면 success 리턴
         */
        JsonObject json = new JsonObject();

        if(CertificationUtil.checkCertKey(request, "signup", accountEmail, certKey) == false) {
            json.addProperty("result", "fail");
            json.add("value", new JsonObject());
            json.addProperty("message", "인증을 실패했습니다. 다시한번 시도하시기 바랍니다.");

            return json;
        }

        json.addProperty("result", "success");
        json.add("value", new JsonObject());
        json.addProperty("message", "");

        return json;
    }

    @ResponseBody
    @RequestMapping(value="/account/signupCheck", method = RequestMethod.POST)
    public JsonObject signupCheck(String token, Account account, Model model, HttpServletRequest request) {
        /**
         * 세션에 입력된 메일과 등록하려는 메일주소가 같은지 확인.
         * 인증이 되었는지 확인
         */
        JsonObject json = new JsonObject();

        try {
            if(TokenUtil.checkToken(request, "signup", token) == false) {
                throw new InvalidRequestException("회원가입 가능 시간이 만료되었습니다. 다시 시도해주세요.");
            }

            if(CertificationUtil.checkCerted(request, "signup", account.getAccountEmail()) == false) {
                throw new InvalidRequestException("인증된 메일 주소가 아닙니다. 다시 시도하시기 바랍니다.");
            }

            account.setAccountSignUpIp(RequestUtil.getRemoteAddr(request));

            accountService.signUp(account);
        } catch(InvalidRequestException e) {
            json.addProperty("result", "fail");
            json.add("value", new JsonObject());
            json.addProperty("message", e.getMessage());

            return json;
        }

        json.addProperty("result", "success");
        json.add("value", new JsonObject());
        json.addProperty("message", "");

        return json;
    }

    @ResponseBody
    @RequestMapping(value="/account/checkId", method = RequestMethod.POST)
    public JsonObject checkId(String accountId) {
        JsonObject json = new JsonObject();

        try {
            accountService.checkId(accountId);
        } catch(InvalidRequestException e) {
            json.addProperty("result", "fail");
            json.add("value", new JsonObject());
            json.addProperty("message", e.getMessage());

            return json;
        }

        json.addProperty("result", "success");
        json.add("value", new JsonObject());
        json.addProperty("message", "");

        return json;
    }

    @ResponseBody
    @RequestMapping(value="/account/checkEmail", method = RequestMethod.POST)
    public JsonObject checkEmail(String accountEmail) {
        JsonObject json = new JsonObject();

        try {
            accountService.checkEmail(accountEmail);
        } catch(InvalidRequestException e) {
            json.addProperty("result", "fail");
            json.add("value", new JsonObject());
            json.addProperty("message", e.getMessage());

            return json;
        }

        json.addProperty("result", "success");
        json.add("value", new JsonObject());
        json.addProperty("message", "");

        return json;
    }

    @ResponseBody
    @RequestMapping(value="/account/checkNickname", method = RequestMethod.POST)
    public JsonObject checkNickname(String accountNickname) {
        JsonObject json = new JsonObject();

        try {
            accountService.checkNickname(accountNickname);
        } catch(InvalidRequestException e) {
            json.addProperty("result", "fail");
            json.add("value", new JsonObject());
            json.addProperty("message", e.getMessage());

            return json;
        }

        json.addProperty("result", "success");
        json.add("value", new JsonObject());
        json.addProperty("message", "");

        return json;
    }

}
