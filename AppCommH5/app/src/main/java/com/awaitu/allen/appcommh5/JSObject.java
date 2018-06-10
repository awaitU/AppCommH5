package com.awaitu.allen.appcommh5;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class JSObject {

	/*
	 * 绑定的object对象
	 */
	private Context context;

	public JSObject(Context context) {
		this.context = context;
	}

	/*
	 * JS调用android的方法
	 *
	 * @JavascriptInterface仍然必不可少
	 * 
	 */
	@JavascriptInterface
	public void JsCallAndroid(String name) {
		Toast.makeText(context, name+"android的JsCallAndroid方法", Toast.LENGTH_SHORT).show();
	}
}
