package com.lqdn;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.FormationTabHost.R;


public class sendUrl extends Activity implements OnClickListener
{
	Button sendButton = null;
	URL finalUrl = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send2lqdn);
        
        Intent thisIntent = getIntent();
        String url = thisIntent.getStringExtra(Intent.EXTRA_TEXT);
        EditText urlText = (EditText) findViewById(R.id.editText);
        urlText.setText(url);
        
        sendButton = (Button)findViewById(R.id.button1);
        sendButton.setOnClickListener(this);
    }

	public void onClick(View v)
	{
		android.util.Log.v("sendUrl", "bouton cliqué");
	// Vérification de la vue cliquée
		if(v == sendButton)
		{
			android.util.Log.v("sendUrl", "bon bouton");
			try {
		        EditText urlText = (EditText) findViewById(R.id.editText);
		        String url = urlText.getText().toString();
		        
		        CheckBox quoteBox = (CheckBox) findViewById(R.id.quoteBox);
		        boolean isQuotingLQDN = quoteBox.isChecked();

		        CheckBox talkBox = (CheckBox) findViewById(R.id.talkBox);
		        boolean isTalkingLQDN = talkBox.isChecked();
		        
				callURL(url, isQuotingLQDN, isTalkingLQDN);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


    Boolean callURL(String page, boolean isQuotingLQDN, boolean isTalkingLQDN) throws IOException
    {
		TextView connectionTextView = (TextView)findViewById(R.id.connectionState);

		android.util.Log.v("sendUrl", "Ouverture connexion");
		
		HttpClient httpclient = new DefaultHttpClient();
    	HttpPost httppost = new HttpPost("http://www.laquadrature.net/rp-proposer/");
    	
    	String typeArticle = "rp";
    	if(isQuotingLQDN)
    		typeArticle += "c";
    	if(isTalkingLQDN)
    		typeArticle += "p";
    	
    	List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
    	nameValuePairs.add(new BasicNameValuePair("url", page));
    	nameValuePairs.add(new BasicNameValuePair("type", typeArticle));
        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        HttpResponse response = httpclient.execute(httppost);
        String responseString = response.getStatusLine().toString();
        
        if(responseString.contains("200") && responseString.contains("OK"))
        {
			connectionTextView.setText("This article has been sent (and maybe received)");
			return true;
        }
        else
        {
			connectionTextView.setText(responseString);
			return false;
        }
    }
    
	public String convertStreamToString(InputStream is) throws IOException
	{
	/*
	 * To convert the InputStream to String we use the
	 * Reader.read(char[] buffer) method. We iterate until the
	 * Reader return -1 which means there's no more data to
	 * read. We use the StringWriter class to produce the string.
	 */
		if (is != null)
		{
		    Writer writer = new StringWriter();
		
		    char[] buffer = new char[1024];
		    try
		    {
		        Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		        int n;
		        while ((n = reader.read(buffer)) != -1)
		            writer.write(buffer, 0, n);
		    }
		    finally
		    {
		        is.close();
		    }
		    return
		    	writer.toString();
		}
		else   
		    return "";
	}
}