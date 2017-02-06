import com.crawler.domain.json.Result;
import com.crawler.pbccrc.domain.json.LoginData;
import com.crawler.pbccrc.domain.json.ResultData;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.module.ocr.service.ChaoJiYingOCRService;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

public class Test {

    public static void main(String[] args) {
//        String json = "[{\"name_\":\"Secure\",\"value_\":\"\",\"domain_\":\"ipcrs.pbccrc.org.cn\",\"path_\":\"/page/login\",\"secure_\":false,\"httponly_\":true},{\"name_\":\"JSESSIONID\",\"value_\":\"wfv1XjmGkB9KpcTXThGQ1LTvXBhfLTYrms2vpTd5NZHtJFfpXHym!1240707328\",\"domain_\":\"ipcrs.pbccrc.org.cn\",\"path_\":\"/\",\"secure_\":false,\"httponly_\":false},{\"name_\":\"BIGipServerpool_ipcrs_app\",\"value_\":\"KPuIlUhUD1qa+16FxRjkQ/ItLb4uVFCug/lshbW7WpehaNxLlDxxLeBByZ2DDnuSXHICmoX1jKYIUghlplfMyjJWqjR0KHwJ6OWrVMfVVnPkDCeV86cI7Vt7JI4rNRqkZdgUQx22eqUFqTPq6YzJ0xh8n/l7JA==\",\"domain_\":\"ipcrs.pbccrc.org.cn\",\"path_\":\"/\",\"secure_\":true,\"httponly_\":true},{\"name_\":\"BIGipServerpool_ipcrs_web\",\"value_\":\"pPRAHDHT2z7qBGuFxRjkQ/ItLb4uVBL7vqPt9HKDP+dV5J5gawBwWrQlk6rHD8O8y6e8RmJXZq+/\",\"domain_\":\"ipcrs.pbccrc.org.cn\",\"path_\":\"/\",\"secure_\":true,\"httponly_\":true},{\"name_\":\"TSf75e5b\",\"value_\":\"706d5a38c6115ebde603c699c064fe77893de3dd03d309e057636664\",\"domain_\":\"ipcrs.pbccrc.org.cn\",\"path_\":\"/\",\"secure_\":false,\"httponly_\":false}]";

        String url = "http://54.223.194.5:8080/data/api/pbccrc/getloginpage";
//        String params = "cookies=" + json + "&tradecode=xs5bn7";


        String sr= PbccrcRequest.sendPost(url, "");

        Gson gson = new GsonBuilder().create();
        Type type = new TypeToken<Result<ResultData>>(){}.getType();

        Result<ResultData> result = gson.fromJson(sr, type);

        /////////////////////////////////////////////////////////////////////////////
        ResultData resultData = result.getData();
        Set<Cookie> resultDataCookies = resultData.getCookies();
        String img = resultData.getCodeImageUrl();
        System.out.println(img);
        String cookie = gson.toJson(resultDataCookies);


        String loginurl = "http://54.223.194.5:8080/data/api/pbccrc/login";
        String verifyCode = "";

//        new ChaoJiYingOCRService().getVerifycode()
        System.out.println("cookie--"+cookie);

        String params = "cookies=" + cookie + "&username=md19841002&password=md87315450&verifycode="+ verifyCode;

        String loginResult = PbccrcRequest.sendPost(loginurl, params);

        System.out.println(loginResult);

        Result<LoginData> loginDataResult = gson.fromJson(loginResult, new TypeToken<Result<LoginData>>() {}.getType());

        if("0".equals(loginDataResult.getData().getStatusCode())){
            LoginData loginData =loginDataResult.getData();

            Set<Cookie> loginCookie = loginData.getCookies();

            String loginCookieStr = gson.toJson(loginCookie);


            String chp3url = "http://localhost:8080/crawler/api/pbccrc/chp3/logged/getcredit";
            String chp3param = "cookies=" + loginCookieStr + "&tradecode=n5i3b4";


            String  chpResult = PbccrcRequest.sendPost(chp3url, chp3param);

            System.out.println(chpResult);
        }

    }
}
