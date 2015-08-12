package com.example.android.apis.viewtest;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import com.example.android.apis.R;

import twitter4j.auth.AccessToken;
import twitter4j.auth.OAuthAuthorization;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationContext;

public class CallBackActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t4j);

        AccessToken token = null;

        Uri uri = getIntent().getData();

        if(uri != null && uri.toString().startsWith("oauth://callback")){
            Configuration conf = ConfigurationContext.getInstance();

            OAuthAuthorization _oauth = new OAuthAuthorization(conf);
            _oauth.setOAuthConsumer(Twitter4jActivity.CONSUMER_KEY, Twitter4jActivity.CONSUMER_SECRET_KEY);

            String verifier = uri.getQueryParameter("oauth_verifier");
            try {
                System.out.println("Got access token.");
                token = _oauth.getOAuthAccessToken(Twitter4jActivity._req,verifier);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        CharSequence cs = "token�F:" + token.getToken() + "\r\n" + "token secret�F:" + token.getTokenSecret();
    }

}