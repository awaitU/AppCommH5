package com.awaitu.allen.appcommh5;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MainActivity extends Activity {

    //声明一个webview
    private WebView mWebView;

    private MyWebViewClient WVClient;

    private WebSettings webSettings;

    private WebChromeClient chromeClient;

    //Object对象，用来跟JS网页绑定
    private JSObject jsobject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        // TODO Auto-generated method stub
        mWebView = findViewById(R.id.webview);

        WVClient = new MyWebViewClient();
        chromeClient = new WebChromeClient();
        jsobject = new JSObject(MainActivity.this);

        webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSavePassword(false);

        //支持多种分辨率，需要js网页支持
        webSettings.setUserAgentString("mac os");
        webSettings.setDefaultTextEncodingName("utf-8");

        //显示本地js网页
        mWebView.loadUrl(Contents.LOACL);

        //setWebClient帮助WebView处理各种通知、请求事件
        mWebView.setWebViewClient(WVClient);
        //setWebChromeClient辅助WebView处理JavaScript的对话框，网站图标，网站title，加载进度等
        mWebView.setWebChromeClient(chromeClient);

        //注意第二个参数JsTest，这个是JS网页调用Android方法的一个类似ID的东西
        mWebView.addJavascriptInterface(jsobject, "JsTest");

    }

    /*
     *在android调用js有参的函数的时候参数要加单引号
     */
    public void androidCallJs(View view) {
        mWebView.loadUrl("javascript:androidCallJS('" + "Hello world" + "')");
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            this.finish();
        }
    }

}
