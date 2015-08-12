package com.example.android.apis.viewtest;

import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;

import twitter4j.auth.AccessToken;
import twitter4j.auth.OAuthAuthorization;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationContext;

/**
 * Created by ms2 on 2015/07/21.
 */

public class TwitterService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        AccessToken token = null;

        Uri uri = intent.getData();

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

        CharSequence cs = "token?F:" + token.getToken() + "\r\n" + "token secret?F:" + token.getTokenSecret();
        return Service.START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        //TODO for communication return IBinder implementation
        return null;
    }
}
