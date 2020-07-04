package org.wyjs.fei;

import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;

public class Browser {
    private static volatile WebClient webClient=null;
    private Browser(){
        WebClient webClient=new WebClient();
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setActiveXNative(false);
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
    }
    public static WebClient getWebClient(){
        if(webClient==null){
            synchronized (Browser.class){
                if(webClient==null){
                    webClient=new WebClient();
                }
            }
        }
        return webClient;
    }
}
