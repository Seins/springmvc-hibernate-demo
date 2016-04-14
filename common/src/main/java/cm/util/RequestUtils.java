package cm.util;

import cm.exception.BussinessException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by CM on 2015/4/28.
 */
public class RequestUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestUtils.class);
    private static final String ENCODING = "UTF-8";

    //每个post参数之间的分隔
    static final String BOUNDARY = "----fast-build-wx";

    /**
     * http 模拟 form表单提交数据和文件
     *
     * @param requestUrl
     * @param strParams
     * @param fileParams
     * @return
     * @throws IOException
     * @throws cm.exception.BussinessException 0001:文件未找到
     */
    public static JSONObject httpFormRequest(String requestUrl, List<String[]> strParams, List<String[]> fileParams) throws IOException, BussinessException {
        HttpURLConnection hc = null;  //http连接器
        ByteArrayOutputStream bos = null;//byte输出流，用来读取服务器返回的信息
        InputStream is = null;//输入流，用来读取服务器返回的信息
        byte[] res = null;//保存服务器返回的信息的byte数组
        URL url = new URL(requestUrl);
        hc = (HttpURLConnection) url.openConnection();

        hc.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
        hc.setRequestProperty("Charsert", "UTF-8");
        // 发送POST请求必须设置如下两行
        hc.setDoOutput(true);
        hc.setDoInput(true);
        hc.setUseCaches(false);
        hc.setRequestMethod("POST");

        OutputStream dout = hc.getOutputStream();

        ////1.先写文字形式的post流
        //头
        String boundary = BOUNDARY;
        //中
        StringBuffer resSB = new StringBuffer("\r\n");
        //尾
        String endBoundary = "\r\n--" + boundary + "--\r\n";
        //strParams 1:key 2:value
        if (strParams != null) {
            for (String[] param : strParams) {
                String key = param[0];
                String value = param[1];
                resSB.append("Content-Disposition: form-data; name=\"description").append("\"").append("\r\n")
                        .append("\r\n").append(key + "=\"").append(value).append("\"\r\n").append("--").append(boundary).append("\r\n").append("\r\n");
            }
        }
        String boundaryMessage = resSB.toString();
        //写出流
        dout.write(("--" + boundary + boundaryMessage).getBytes("utf-8"));
        resSB = new StringBuffer();
        if (fileParams != null) {
            for (int i = 0, num = fileParams.size(); i < num; i++) {
                String[] param = fileParams.get(i);
                String fileField = param[0];
                String fileName = param[1];
                String fileType = param[2];
                String filePath = param[3];
                resSB.append("Content-Disposition: form-data; name=\"").append(fileField + "\"").append("filename=\"").append(
                        fileName + "\"").append("\r\n").append("Content-Type: ").append(fileType).append("\r\n\r\n");
                dout.write(resSB.toString().getBytes("utf-8"));
                //开始写文件
                File file = new File(filePath);
                if (!file.exists()) {
                    throw new BussinessException("文件上传失败！错误代码：0001");
                }
                DataInputStream in = new DataInputStream(new FileInputStream(file));
                int bytes;
                byte[] bufferOut = new byte[1024 * 5];
                while ((bytes = in.read(bufferOut)) != -1) {
                    dout.write(bufferOut, 0, bytes);
                }
            }
        }
        dout.write(endBoundary.getBytes("utf-8"));
        dout.close();
        int ch;
        is = hc.getInputStream();
        bos = new ByteArrayOutputStream();
        while ((ch = is.read()) != -1) {
            bos.write(ch);
        }
        res = bos.toByteArray();

        if (bos != null)
            bos.close();
        if (is != null)
            is.close();
        String result = new String(res);
        return JSON.parseObject(result);
    }


    /**
     * 普通get请求
     *
     * @param targetUrl 目标链接
     * @param params    参数
     * @return
     */
    public static String httpRequest(String targetUrl, String... params) {
        String tempStr = null;
        HttpURLConnection url_con = null;
        try {
            URL url = new URL(targetUrl);
            StringBuffer bankXmlBuffer = new StringBuffer();
            //创建URL连接，提交到数据，获取返回结果
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("User-Agent", "micromessenger");
            connection.setRequestProperty("devices", "iphone");
            PrintWriter out = new PrintWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
            if (params != null && params.length > 0) {
                for (String p : params) {
                    out.println(p);
                }
            }
            out.close();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                bankXmlBuffer.append(inputLine);
            }
            in.close();
            tempStr = bankXmlBuffer.toString();
        } catch (Exception e) {
            LOGGER.error("发送GET请求出现异常！", e);
        } finally {
            if (url_con != null)
                url_con.disconnect();
        }
        return tempStr;
    }

    /**
     * 下载文件
     *
     * @param targetUrl
     * @param savePath
     * @param params
     * @throws Exception
     */
    public static void httpRequestFile(String targetUrl, String savePath, String... params) throws Exception {
        HttpURLConnection url_con = null;
        URL url = new URL(targetUrl);
        //创建URL连接，提交到数据，获取返回结果
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("User-Agent", "micromessenger");
        PrintWriter out = new PrintWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
        if (params != null && params.length > 0) {
            for (String p : params) {
                out.println(p);
            }
        }
        out.close();
        DataInputStream in = new DataInputStream(connection.getInputStream());
        byte[] nal = new byte[1024];
        int len = 0;
        FileOutputStream fos = new FileOutputStream(savePath);
        while ((len = in.read(nal)) != -1) {
            fos.write(nal, 0, len);
        }
        in.close();
        fos.close();
        if (url_con != null)
            url_con.disconnect();
    }

/*
    public static JSONObject httpClientFormRequest(String url, Map params, File file) throws IOException {
        HttpClient httpclient = new DefaultHttpClient();
        //请求处理页面
        HttpPost httppost = new HttpPost( url );
        //创建待处理的文件
        FileBody fileItem = new FileBody(file);

        MultipartEntity reqEntity = new MultipartEntity();
        reqEntity.addPart("file", fileItem);
        if(params!=null ){
            Iterator it = params.entrySet().iterator();
            while(it.hasNext() ){
                Map.Entry entry = (Map.Entry)it.next();
                String key =  (String) entry.getKey();
                String value = (String)entry.getValue();
                //创建待处理的表单域内容文本
                StringBody bodyValue = new StringBody(value);
                reqEntity.addPart(key , bodyValue);
            }
        }

        //设置请求
        httppost.setEntity(reqEntity);
        //执行
        HttpResponse response = httpclient.execute(httppost);

        HttpEntity httpEntity = response.getEntity();
        BufferedReader br = new BufferedReader(new InputStreamReader(httpEntity.getContent(), "gb2312"));
        StringBuffer backData = new StringBuffer();
        String line = null;
        while ((line = br.readLine()) != null) {
            backData.append(line);
        }
        System.out.println(backData.toString());
        return JSON.parseObject(backData.toString());
    }*/


    /**
     * 基于HttpClient 4.3的通用POST方法
     *
     * @param url       提交的URL
     * @param paramsMap 提交<参数，值>Map
     * @return 提交响应
     */

    public static String post(String url, Map<String, String> paramsMap) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        String responseText = null;
        CloseableHttpResponse response = null;
        try {
            HttpPost method = new HttpPost(url);
            if (paramsMap != null) {
                List<NameValuePair> paramList = new ArrayList<NameValuePair>();
                for (Map.Entry<String, String> param : paramsMap.entrySet()) {
                    NameValuePair pair = new BasicNameValuePair(param.getKey(), param.getValue());
                    paramList.add(pair);
                }
                method.setEntity(new UrlEncodedFormEntity(paramList, ENCODING));
            }
            response = client.execute(method);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseText = EntityUtils.toString(entity);
            }
        } catch (Exception ex) {
            LOGGER.error("post request occur error:", ex);
            throw ex;
        } finally {
            try {
                response.close();
            } catch (Exception e) {
                LOGGER.error("close request response occur error:", e);
            }
        }
        return responseText;
    }
}
