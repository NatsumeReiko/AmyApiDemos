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

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.apis.R;

import java.util.Calendar;
import java.util.TimeZone;

public class EditTestActivity extends Activity {
    private static final int MAX_TASK = 3;

    private EditText editTest;
    private Button sendMsgBtn;
    TextView gotoAllParts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edittext_test_layout);
        getStartTimeOfDayByJapanTimeZone();

       gotoAllParts = (TextView)findViewById(R.id.gotoAllParts);

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("goalstart")
                .authority("screen")
                .appendPath("browse")
                .appendQueryParameter("url", "web/support/form")
                .appendQueryParameter("login", "true")
                .appendQueryParameter("home_button_gone", "true");
        String myUrl = builder.build().toString();

//        gotoAllParts.setText(
//                Html.fromHtml(
//                        "<a href=\"http://www.google.com\">go to all parts</a> "));

        setTextViewHTML(gotoAllParts, "※いただきましたご意見・ご要望などは必ず確認<br />　させていただき、サービスの向上のために活用<br />　させていただきます。<br />※本コンテンツへのご意見・ご要望に関しまし<br />　て、ご回答はお約束するものではありません。<br />　予めご了承ください。<br />※使い方に関するご不明な点は<a href=\"goalstart://screen/browse\">こちら</a>よりお問い<br />　合わせください。<br />※わいせつ・暴力的な内容や、その他不適切な投<br />　稿はアカウント停止となります。\n");

        gotoAllParts.setMovementMethod(LinkMovementMethod.getInstance());
//        gotoAllParts.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext()," ",Toast.LENGTH_SHORT);
//            }
//        });

        editTest = (EditText)findViewById(R.id.edit_test);
        editTest.setImeOptions(DEFAULT_KEYS_DISABLE);

        sendMsgBtn = (Button)findViewById(R.id.send_message);
        sendMsgBtn.setEnabled(false);


        sendMsgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(" EditTestActivity "," sendMsgBtn onClick ");
                editTest.setText("");
            }
        });
        editTest.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = s.toString();
                boolean couldClick = textLength(text) > 0 && text.length() <= 140;
                Log.d(" EditTestActivity "," onTextChanged btn click:" + String.valueOf(couldClick) + " text: "+ text);
                sendMsgBtn.setEnabled(couldClick);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

            private int textLength(String text) {
                int length = 0;
                String[] texts = text.split("\n");
                for (String t : texts) {
                    length += t.length();
                }
                return length;
            }
        });

    }


    protected void makeLinkClickable(SpannableStringBuilder strBuilder, final URLSpan span)
    {
        int start = strBuilder.getSpanStart(span);
        int end = strBuilder.getSpanEnd(span);
        int flags = strBuilder.getSpanFlags(span);
        ClickableSpan clickable = new ClickableSpan() {
            public void onClick(View view) {
                // Do something with span.getURL() to handle the link click...
                Toast.makeText(getApplicationContext(),"ClickableSpan ",Toast.LENGTH_SHORT).show();


                setTextViewHTML(gotoAllParts, "※いただきましたご意見・ご要望などは必ず確認<br />　させていただき、サービスの向上のために活用<br />　させていただきます。<br />※本コンテンツへのご意見・ご要望に関しまし<br />　て、ご回答はお約束するものではありません。<br />　予めご了承ください。<br />※使い方に関するご不明な点は<a href=\"goalstart://screen/browse\">こちら</a>よりお問い<br />　合わせください。<br />※わいせつ・暴力的な内容や、その他不適切な投<br />　稿はアカウント停止となります。\n");


            }
        };
        strBuilder.setSpan(clickable, start, end, flags);
        strBuilder.removeSpan(span);
    }

    protected void setTextViewHTML(TextView text, String html)
    {
        CharSequence sequence = Html.fromHtml(html);
        SpannableStringBuilder strBuilder = new SpannableStringBuilder(sequence);
        URLSpan[] urls = strBuilder.getSpans(0, sequence.length(), URLSpan.class);
        for(URLSpan span : urls) {
            makeLinkClickable(strBuilder, span);
        }
        text.setText(strBuilder);
    }


    private long getStartTimeOfDayByJapanTimeZone() {

        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+9"));
        calendar.setTimeInMillis(System.currentTimeMillis());

        System.out.println( DateFormat.format("yyyy-MM-dd hh:mm:ss", calendar.getTime()));

        calendar.set(Calendar.HOUR_OF_DAY, 13);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        System.out.println(DateFormat.format("yyyy-MM-dd hh:mm:ss", calendar.getTime()));
        System.out.println( "calendar.getTimeInMillis() " +calendar.getTimeInMillis());


        getTimeSecondByJapanTimeZone();

        return calendar.getTimeInMillis();
    }

    private long getTimeSecondByJapanTimeZone() {
        System.out.println( DateFormat.format("yyyy-MM-dd hh:mm:ss", Calendar.getInstance(TimeZone.getTimeZone("GMT+9")).get(Calendar.SECOND)));
        System.out.println( "calendar.get(Calendar.SECOND) " +Calendar.getInstance(TimeZone.getTimeZone("GMT+9")).get(Calendar.SECOND));

        return Calendar.getInstance(TimeZone.getTimeZone("GMT+9")).get(Calendar.SECOND);
    }

}
