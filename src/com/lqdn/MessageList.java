package com.lqdn;

import java.util.ArrayList;
import java.util.List;

import com.FormationTabHost.R;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MessageList extends ListActivity {
	
	private List<Message> messages;
	
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.rssreader);
        String valeur = getIntent().getStringExtra("flux");
        loadFeed(valeur);
    }

	private void loadFeed(String flux){
    	try{
	    	BaseFeedParser parser = new BaseFeedParser(flux);
	    	messages = parser.parse();
	    	List<String> titles = new ArrayList<String>(messages.size());
	    	for (Message msg : messages){
	    		titles.add(msg.getTitle());
	    	}
	    	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.row,titles);
	    	this.setListAdapter(adapter);
    	} catch (Throwable t){
    		Log.e("AndroidNews",t.getMessage(),t);
    	}
    }
    
	protected void onListItemClick(ListView l, View v, int position, long id)
	{
		String[] datas = {messages.get(position).getTitle(), messages.get(position).getDescription()};
		Intent articleView = new Intent(this, RssArticle.class);
		articleView.putExtra("datas", datas);
		startActivity(articleView);
	}
}