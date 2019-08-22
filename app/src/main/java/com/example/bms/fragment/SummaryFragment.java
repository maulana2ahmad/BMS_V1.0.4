package com.example.bms.fragment;


import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.bms.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SummaryFragment extends Fragment {

    private ProgressBar mprogressBar;
    private WebView wbSummry;
    private SwipeRefreshLayout mswipeRefreshLayout;


    //url
    private String pageUrl = "http://portal-bams.mncgroup.com:8008";

    private String DEFAULT_ERROR_PAGE_PATH = "file:///android_asset/html/colorlib_error_404_10/index.html";

    private static final String TAG = "WebViewCustomization";

    public SummaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_summary, container, false);

        wbSummry = (WebView) view.findViewById(R.id.wv_Summry);
        //mprogressBar = (ProgressBar) view.findViewById(R.id.progress_loading);
        mswipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe);

        LoadWeb();

        mswipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                LoadWeb();
            }
        });

        LoadWeb();

        return view;
    }

    private void LoadWeb() {

        wbSummry.getSettings().setJavaScriptEnabled(true);
        wbSummry.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        //wbSummry.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        wbSummry.getSettings().setDomStorageEnabled(true);
        wbSummry.getSettings().setAppCacheEnabled(true);
        wbSummry.getSettings().setSupportZoom(true);
        wbSummry.getSettings().setSupportMultipleWindows(true);
        wbSummry.loadUrl(pageUrl);
        //wbSummry.addJavascriptInterface(new SimpleWebJavascriptInterface(getActivity()), "Android");
        mswipeRefreshLayout.setRefreshing(true);
        mswipeRefreshLayout.setColorSchemeResources(R.color.greenPrimary, R.color.yellowPrimary, R.color.redPrimary, R.color.bluePrimary);
        wbSummry.setWebViewClient(new WebViewClient() {

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

                wbSummry.loadUrl(DEFAULT_ERROR_PAGE_PATH);

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                mswipeRefreshLayout.setRefreshing(false);
            }
        });

    }
}