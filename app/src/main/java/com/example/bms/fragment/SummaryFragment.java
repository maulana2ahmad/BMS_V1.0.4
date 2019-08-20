package com.example.bms.fragment;


import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.http.SslError;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.bms.R;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class SummaryFragment extends Fragment {

    private ProgressBar mprogressBar;
    private WebView wbSummry;
    private AlertDialog.Builder dialog;

    //url
    private String pageUrl = "http://portal-bams.mncgroup.com:8008";

    public SummaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_summary, container, false);

        //mprogressBar = (ProgressBar) view.findViewById(R.id.progress_loading);
        wbSummry = (WebView) view.findViewById(R.id.wv_Summry);
        mprogressBar = (ProgressBar) view.findViewById(R.id.progress_loading);


        wbSummry.getSettings().setJavaScriptEnabled(true); //active javaScriptEnabled
        wbSummry.setHorizontalScrollBarEnabled(false);
        wbSummry.loadUrl(pageUrl);

        //wbSummry.setWebViewClient(new WebViewClient());
        wbSummry.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);

                mprogressBar.setProgress(newProgress);
                if (newProgress < 100 && mprogressBar.getVisibility() == ProgressBar.GONE) {
                    mprogressBar.setVisibility(ProgressBar.VISIBLE);
                }

                mprogressBar.setProgress(newProgress);
                if (newProgress == 100) {
                    mprogressBar.setVisibility(ProgressBar.GONE);
                }

            }

        });

        wbSummry.setWebViewClient(new WebViewClient() {


            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {

                try {
                    wbSummry.stopLoading();
                } catch (Exception e) {
                }
                try {
                    wbSummry.clearView();
                } catch (Exception e) {
                }
                if (wbSummry.canGoBack()) {
                    wbSummry.goBack();
                }

//                try {
//                    wbSummry.stopLoading();
//                } catch (Exception e) {
//                }
//
//                if (wbSummry.canGoBack()) {
//                    wbSummry.goBack();
//                }

                wbSummry.loadUrl("about:blank");
                new AlertDialog.Builder(getActivity().getApplicationContext())
                        .setTitle("Delete entry")
                        .setMessage("Are you sure you want to delete this entry?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //  deleteSuggestions(position);
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

                super.onReceivedError(view, request, error);

            }

            //@Override
//            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
//                super.onReceivedHttpError(view, request, errorResponse);
//
//                Toast.makeText(getActivity(), "Unexpected error occurred.Reload page again.", Toast.LENGTH_SHORT).show();
//                if(errorResponse.getStatusCode() == 401){
//                    wbSummry.loadUrl(pageUrl);
//                }
//            }


            /*
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {


                String message = null;
                switch (error.getPrimaryError()) {
                    case SslError.SSL_UNTRUSTED:
                        message = "The certificate authority is not trusted.";
                        break;
                    case SslError.SSL_EXPIRED:
                        message = "The certificate has expired.";
                        break;
                    case SslError.SSL_IDMISMATCH:
                        message = "The certificate Hostname mismatch.";
                        break;
                    case SslError.SSL_INVALID:
                        message = "SSL connection is invalid.";
                        break;
                }
                super.onReceivedSslError(view, handler, error);

//            @Override
//            public void onReceivedClientCertRequest(WebView view, ClientCertRequest request) {
//                super.onReceivedClientCertRequest(view, request);
                //           }


            }
            */

        });


        return view;
    }


}