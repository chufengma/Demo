package com.onfengma.androidprac;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.BaseAdapter;

import com.onfengma.androidprac.views.LadyJSInterface;
import com.onfengma.androidprac.views.listview.XListView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WebViewDemoActivity extends AppCompatActivity {

    WebView web;
    LadyJSInterface ladyInterface;
    @Bind(R.id.list)
    XListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_demo);
        ButterKnife.bind(this);

        web = new WebView(this);
        WebSettings webSettings = web.getSettings();
        webSettings.setJavaScriptEnabled(true);

        list.setAdapter(new WebViewListAdapter(web));

        ladyInterface = new LadyJSInterface(this, web);
        web.addJavascriptInterface(ladyInterface, "Lady");

        list.enablePullRefresh(true);
        list.setAutoLoadMore(true);

        list.setOnRefreshListener(new XListView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                web.loadUrl("http://www.baidu.com");
                list.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list.onRefreshComplete(true);
                        list.enableLoadMore(true);
                    }
                }, 1000);
            }
        });

        list.post(new Runnable() {
            @Override
            public void run() {
                list.onRefreshComplete(true);
            }
        });

        list.setOnLoadMoreListener(new XListView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                web.loadUrl("file:///android_asset/demo.html");
                list.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list.onLoadMoreComplete();
                    }
                }, 1000);
            }
        });

    }

    class WebViewListAdapter extends BaseAdapter {

        private WebView webView;

        private Context context;

        public WebViewListAdapter(WebView webView) {
            this.context = context;
            this.webView = webView;
        }

        @Override
        public int getCount() {
            return 1;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return webView;
        }

    }

    public void onClick(View view) {
        web.loadUrl("javascript:showSuccess('Added from java code')");
    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (resultCode == RESULT_OK) {
//            web.loadUrl("javascript:showSuccess('file://" + ladyInterface.getFileName() + "')");
//        } else {
//            Toast.makeText(this, "result code failed", Toast.LENGTH_SHORT).show();
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }


}
