package com.shk.tester;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.util.Random;

public class ActivityWebView extends Activity {
	private WebView mWebView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_webview);

		mWebView = findViewById(R.id.wv_webview);

		WebSettings ws = mWebView.getSettings();
		ws.setAllowContentAccess(true);
		ws.setAllowFileAccess(true);
		ws.setAppCacheEnabled(true);
		ws.setDatabaseEnabled(true);
		ws.setDomStorageEnabled(true);
		ws.setJavaScriptCanOpenWindowsAutomatically(true);
		ws.setJavaScriptEnabled(true);
		ws.setUseWideViewPort(true);
		ws.setLoadWithOverviewMode(true);
		ws.setSupportZoom(true);

		ws.setAllowFileAccessFromFileURLs(true);
		ws.setAllowUniversalAccessFromFileURLs(true);

		CookieManager.getInstance().setAcceptCookie(true);
		if (Build.VERSION.SDK_INT >= 21) {
			CookieManager.getInstance().setAcceptThirdPartyCookies(mWebView, true);
		}

		if (Build.VERSION.SDK_INT >= 21) {
			ws.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
		}

		// mWebView.loadUrl("http://ckt.cocosbcx.net/");
		mWebView.loadUrl("http://testupdate.dappx.store/dappxWebView/backRunner/cocosbcx.html?v=" + new Random().nextFloat());
	}
}