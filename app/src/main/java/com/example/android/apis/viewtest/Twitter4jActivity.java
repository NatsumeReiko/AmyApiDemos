/**
 * Copyright (c) 2010, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.apis.viewtest;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.android.apis.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.OAuthAuthorization;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.conf.ConfigurationContext;

public class Twitter4jActivity extends Activity implements View.OnClickListener{

   public static RequestToken    _req;

//    Consumer Key (API Key)	hHgIOMviNVzzIgkPWJgBMFIS2 (manage keys and access tokens)
//    Callback URL	None
//    Sign in with Twitter	Yes
//    App-only authentication	https://api.twitter.com/oauth2/token
//    Request token URL	https://api.twitter.com/oauth/request_token
//    Authorize URL	https://api.twitter.com/oauth/authorize
//    Access token URL	https://api.twitter.com/oauth/access_token

//    THIS AUTHTOKEN: 3280222154-nqIYOXPCvIJyLLca3IvLWgyhvv7CVYoo9DtcSfB
//    THIS secret AUTHTOKEN: tC5V2KnOwejm3ruUO7ApT3zTywybByV34s9X9TmIDefje

    private String mytoken;
    private String mySecrettoken;

    // Fabric TwitterKit --------------------------------------------------------
    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    public static final String CONSUMER_KEY = "hHgIOMviNVzzIgkPWJgBMFIS2";
    public static final String CONSUMER_SECRET_KEY = "4ay07UeZHS1HFEzhSU9tsIRribWk4MeF1DHjfrb18s87johA6S";
//    private static final String CONSUMER_KEY = "QM2hvu3kLXffduqfWcnB4Xo8O";
//    private static final String CONSUMER_SECRET_KEY = "z4hDwXN5s8JNi2TU5AG720PbTio5pD04Y4dx6fLndqRJeK9Qpm";
    private final String CALLBACKURL = "T4J_OAuth://callback_main";
    Twitter twitter;
    RequestToken requestToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t4j);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        findViewById(R.id.test_login).setOnClickListener(this);
        findViewById(R.id.read_info_from_device).setOnClickListener(this);
        findViewById(R.id.executeOauth).setOnClickListener(this);

//        checkLoginStatus();
    }


    void OAuthLogin() {
        try {

            ConfigurationBuilder cb = new ConfigurationBuilder();


            //the following is set without accesstoken- desktop client
//            cb.setDebugEnabled(true)
//                    .setOAuthConsumerKey(CONSUMER_KEY)
//                    .setOAuthConsumerSecret(CONSUMER_SECRET_KEY);
//
//            TwitterFactory tf = new TwitterFactory(cb.build());
//
//            System.out.println("Got request token.");
//            System.out.println("Request token: " + requestToken.getToken());
//            System.out.println("Request token secret: " + requestToken.getTokenSecret());


            twitter = new TwitterFactory().getInstance();
            twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET_KEY);
            requestToken = twitter.getOAuthRequestToken(CALLBACKURL);
            String authUrl = requestToken.getAuthenticationURL();
            this.startActivity(new Intent(Intent.ACTION_VIEW, Uri
                    .parse(authUrl)));
        } catch (TwitterException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("in Main.OAuthLogin", ex.getMessage());
        }
    }

    private void executeOauth(){
        //Twitetr4Jの設定を読み込む
        Configuration conf = ConfigurationContext.getInstance();

        //Oauth認証オブジェクト作成
        OAuthAuthorization   _oauth = new OAuthAuthorization(conf);
        //Oauth認証オブジェクトにconsumerKeyとconsumerSecretを設定
        _oauth.setOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET_KEY);
        //アプリの認証オブジェクト作成
        try {
             _req = _oauth.getOAuthRequestToken("oauth://callback");
            String _uri;
            _uri = _req.getAuthorizationURL();
            startActivityForResult(new Intent(Intent.ACTION_VIEW , Uri.parse(_uri)), 0);
        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }

    private void getTwitterInfoFromDevice() {
        AccountManager am = AccountManager.get(this);
        Account[] accounts = am.getAccountsByType("com.twitter.android.auth.login");

        if (accounts.length > 0) {
            Account acct = accounts[0];
            am.getAuthToken(acct, "com.twitter.android.oauth.token", null, this, new AccountManagerCallback<Bundle>() {

                public void run(AccountManagerFuture<Bundle> arg0) {
                    // Accessing token
                    Log.d("twitterTest", "***************************");
                    Log.d("twitterTest", "token before: " + mytoken);

                    try {
                        Bundle b = arg0.getResult();
                        mytoken = b.getString(AccountManager.KEY_AUTHTOKEN);
                        Log.d("twitterTest", "token After: " + mytoken);
                        Log.d("twitterTest", "***************************");
                    } catch (Exception e) {
                        Log.e("TrendDroid", "EXCEPTION@AUTHTOKEN");
                    }
                }
            }, null);

            am.getAuthToken(acct, "com.twitter.android.oauth.token.secret", null, this, new AccountManagerCallback<Bundle>() {

                @Override
                public void run(AccountManagerFuture<Bundle> arg0) {
                    try {
                        Log.d("twitterTest", "***************************");
                        Log.d("twitterTest", "token secret before: " + mySecrettoken);
                        Bundle b = arg0.getResult();
                        mySecrettoken =  b.getString(AccountManager.KEY_AUTHTOKEN);
                        Log.d("twitterTest", "token secret After: " + mySecrettoken);
                        Log.d("twitterTest", "***************************");

                    }
                    catch (Exception e) {
                        Log.e("TrendDroid", "EXCEPTION@AUTHTOKEN");
                    }
                }}, null);
        } else {
            Toast.makeText(getApplicationContext(), "There is no defined account", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkLoginStatus() {
        String testStatus="Hello from twitter4j";

        ConfigurationBuilder cb = new ConfigurationBuilder();


        //the following is set without accesstoken- desktop client
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(CONSUMER_KEY)
                .setOAuthConsumerSecret(CONSUMER_SECRET_KEY);

        try {
            TwitterFactory tf = new TwitterFactory(cb.build());
            Twitter twitter = tf.getInstance();

            try {
                System.out.println("-----");

                // get request token.
                // this will throw IllegalStateException if access token is already available
                // this is oob, desktop client version
                RequestToken requestToken = twitter.getOAuthRequestToken();

                System.out.println("Got request token.");
                System.out.println("Request token: " + requestToken.getToken());
                System.out.println("Request token secret: " + requestToken.getTokenSecret());

                System.out.println("|-----");

                AccessToken accessToken = null;

                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

                while (null == accessToken) {
                    System.out.println("Open the following URL and grant access to your account:");
                    System.out.println(requestToken.getAuthorizationURL());
                    System.out.print("Enter the PIN(if available) and hit enter after you granted access.[PIN]:");
                    String pin = br.readLine();

                    try {
                        if (pin.length() > 0) {
                            accessToken = twitter.getOAuthAccessToken(requestToken, pin);
                        } else {
                            accessToken = twitter.getOAuthAccessToken(requestToken);
                        }
                    } catch (TwitterException te) {
                        if (401 == te.getStatusCode()) {
                            System.out.println("Unable to get the access token.");
                        } else {
                            te.printStackTrace();
                        }
                    }
                }
                System.out.println("Got access token.");
                System.out.println("Access token: " + accessToken.getToken());
                System.out.println("Access token secret: " + accessToken.getTokenSecret());

            } catch (IllegalStateException ie) {
                // access token is already available, or consumer key/secret is not set.
                if (!twitter.getAuthorization().isEnabled()) {
                    System.out.println("OAuth consumer key/secret is not set.");
                    System.exit(-1);
                }
            }

            Status status = twitter.updateStatus(testStatus);

            System.out.println("Successfully updated the status to [" + status.getText() + "].");

            System.out.println("ready exit");

            System.exit(0);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get timeline: " + te.getMessage());
            System.exit(-1);
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.out.println("Failed to read the system input.");
            System.exit(-1);
        }

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Uri uri = intent.getData();
        try {
            String verifier = uri.getQueryParameter("oauth_verifier");
            AccessToken accessToken = twitter.getOAuthAccessToken(requestToken,
                    verifier);
            String token = accessToken.getToken(), secret = accessToken
                    .getTokenSecret();


        } catch (TwitterException ex) {
            Log.e("Main.onNewIntent", "" + ex.getMessage());
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.test_login:
                OAuthLogin();
                break;
            case R.id.read_info_from_device:
                getTwitterInfoFromDevice();
                break;
            case R.id.executeOauth:
                executeOauth();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("Main.onNewIntent", "");

    }
}
