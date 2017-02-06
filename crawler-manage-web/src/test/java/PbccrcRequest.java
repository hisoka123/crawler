

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;


public class PbccrcRequest {

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }finally {//使用finally块来关闭输出流、输入流
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    public static void main(String[] args) {
//        String json = "[{\"name_\":\"Secure\",\"value_\":\"\",\"domain_\":\"ipcrs.pbccrc.org.cn\",\"path_\":\"/page/login\",\"secure_\":false,\"httponly_\":true},{\"name_\":\"JSESSIONID\",\"value_\":\"wfv1XjmGkB9KpcTXThGQ1LTvXBhfLTYrms2vpTd5NZHtJFfpXHym!1240707328\",\"domain_\":\"ipcrs.pbccrc.org.cn\",\"path_\":\"/\",\"secure_\":false,\"httponly_\":false},{\"name_\":\"BIGipServerpool_ipcrs_app\",\"value_\":\"KPuIlUhUD1qa+16FxRjkQ/ItLb4uVFCug/lshbW7WpehaNxLlDxxLeBByZ2DDnuSXHICmoX1jKYIUghlplfMyjJWqjR0KHwJ6OWrVMfVVnPkDCeV86cI7Vt7JI4rNRqkZdgUQx22eqUFqTPq6YzJ0xh8n/l7JA==\",\"domain_\":\"ipcrs.pbccrc.org.cn\",\"path_\":\"/\",\"secure_\":true,\"httponly_\":true},{\"name_\":\"BIGipServerpool_ipcrs_web\",\"value_\":\"pPRAHDHT2z7qBGuFxRjkQ/ItLb4uVBL7vqPt9HKDP+dV5J5gawBwWrQlk6rHD8O8y6e8RmJXZq+/\",\"domain_\":\"ipcrs.pbccrc.org.cn\",\"path_\":\"/\",\"secure_\":true,\"httponly_\":true},{\"name_\":\"TSf75e5b\",\"value_\":\"706d5a38c6115ebde603c699c064fe77893de3dd03d309e057636664\",\"domain_\":\"ipcrs.pbccrc.org.cn\",\"path_\":\"/\",\"secure_\":false,\"httponly_\":false}]";

        String url = "http://localhost:8080/crawler/api/pbccrc/chp3/logged/getcredit";
        String params = "cookies=test&tradecode=xs5bn7";


        String sr= sendPost(url, params);

        System.out.println(sr);

    }
}
