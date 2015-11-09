package com.onfengma.androidprac.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

/**
 * Created by chuyifeng on 2015/11/5.
 */
public class LadyJSInterface {

    private Activity activity;
    private WebView webView;

    public LadyJSInterface(Activity activity, WebView webView) {
        this.activity = activity;
        this.webView = webView;
    }

    /**
     * Lady.showToast(str);
     * */
    @JavascriptInterface
    public void showToast(String msg) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * Lady.showDialog(msg, title, successCallbackName, errorCallbackName)
     *
     * function onSuccess(str) {
     * }
     *
     * function onFailed(str) {
     * }
     *
     * var msg = "123";
     * var title = "456";
     *
     * Lady.showDialog(msg, title, "onSuccess", "onFailed")
     *
     *
     * */
    @JavascriptInterface
    public void showDialog(String msg, String title, final String successCallbackName, final String errorCallbackName) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                executeJsFunction(errorCallbackName, "对话框取消");
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                executeJsFunction(errorCallbackName, "对话框确定");
            }
        });
        builder.show();
    }

    public void executeJsFunction(String functionName, String... params) {
        final StringBuilder js = new StringBuilder("javascript:");
        js.append(functionName);
        js.append("(");
        for(int i= 0 ; i< params.length; i++) {
            js.append("'" + params[i] + "'");
            if (i != params.length - 1) {
                js.append(", ");
            }
        }
        js.append(")");
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl("javascript:" + js.toString());
            }
        });
    }
}
