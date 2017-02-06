import com.crawler.domain.json.Result;
import com.crawler.pbccrc.domain.json.LoginData;
import com.crawler.pbccrc.domain.json.ResultData;
import com.crawlermanage.controller.api.pbccrc.Chp3PbcCreditController;
import com.crawlermanage.controller.api.pbccrc.PbcCreditController;
import com.crawlermanage.service.pbccrc.PbccrcService;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Type;
import java.util.Set;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
        "classpath*:/applicationContext-quartz.xml",
        "classpath*:/applicationContext-shiro.xml",
        "classpath*:/datasource/datasource-auth.xml",
        "classpath*:/datasource/datasource-data.xml",
        "classpath*:/datasource/datasource-doc.xml",
        "classpath*:/datasource/datasource-timeTask.xml",
        "classpath*:/datasource/datasource-gsxt.xml",
        "classpath*:/datasource/datasource-pbccrc.xml",
        "classpath*:/applicationContext.xml", //注意顺序
        "classpath*:/applicationContext-email.xml",
        "classpath*:/applicationContext-redis.xml"})
public class TestChp3 {

    @Autowired
    private PbcCreditController pbcCreditController;

    @Autowired
    private Chp3PbcCreditController chp3PbcCreditController;

    @org.junit.Test
    public void testChp3() throws Exception{

        Gson gson = new GsonBuilder().create();

        String loginPage = pbcCreditController.getLoginPage(true, "");

        Type type = new TypeToken<Result<ResultData>>(){}.getType();
        Result<ResultData> result = gson.fromJson(loginPage, type);

        ResultData resultData = result.getData();
        Set<Cookie> resultDataCookies = resultData.getCookies();
        String img = resultData.getCodeImageUrl();
        String cookie = gson.toJson(resultDataCookies);

        System.out.println(img);
        String verifyCode = "";


        String loginResult = pbcCreditController.login(cookie,"md19841002","md87315450",verifyCode,true,"");
        Result<LoginData> loginDataResult = gson.fromJson(loginResult, new TypeToken<Result<LoginData>>() {}.getType());


        if("0".equals(loginDataResult.getData().getStatusCode())){
            LoginData loginData =loginDataResult.getData();

            Set<Cookie> loginCookie = loginData.getCookies();

            String loginCookieStr = gson.toJson(loginCookie);


//            String chp3url = "http://localhost:8080/crawler/api/pbccrc/chp3/logged/getcredit";
//            String chp3param = "cookies=" + loginCookieStr + "&tradecode=xs5bn7";

            String  chpResult = chp3PbcCreditController.getCredit(loginCookieStr,"xs5bn7",true,"");


//            String  chpResult = PbccrcRequest.sendPost(chp3url, chp3param);

            System.out.println(chpResult);
        }



    }
}
