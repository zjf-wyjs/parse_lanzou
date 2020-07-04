package org.wyjs.fei;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.util.WebConnectionWrapper;

import java.io.IOException;

public class Resolve {
    private WebClient webClient;
    //蓝奏云外链地址
    private String path;
    public Resolve(){}

    public Resolve(String path) {
        this.path = path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public void init() throws IOException {
        webClient=Browser.getWebClient();
        
        //监听资源加载
        webClient.setWebConnection(new WebConnectionWrapper(webClient){
            @Override
            public WebResponse getResponse(WebRequest request) throws IOException {
                WebResponse response = super.getResponse(request);
                String data=response.getContentAsString();
                //过滤得到下载链接
                if(data.contains("{\"zt\":1,\"dom\":\"https")) {
                    JSONObject jsonObject= (JSONObject) JSON.parse(data);
                    //解析得到下载链接
                    String url = "https://vip.d0.baidupan.com/file/" + jsonObject.get("url");
                    System.out.println("下载地址");
                    System.out.println(url);
                }
                return response;
            }
        });
        //
        webClient.getPage(path);
    }
}
