package com.FormationTabHost;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MonActivite extends TabActivity {

	private TabHost tabHost;
	private TabSpec tabSpec;
	
	Intent actuRss, RpRss;
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        tabHost = getTabHost();
        
        actuRss = new Intent(this, MessageList.class);
        actuRss.putExtra("flux", "http://www.laquadrature.net/fr/rss.xml");
        tabSpec = tabHost.newTabSpec("Actu").setIndicator("Actualités LQDN").setContent(actuRss);
        
        tabHost.addTab(tabSpec);
        
        RpRss = new Intent(this, MessageList.class);
        RpRss.putExtra("flux", "http://www.laquadrature.net/fr/revue-de-presse/feed");
        tabSpec = tabHost.newTabSpec("RP").setIndicator("Revue de Presse").setContent(RpRss);
        //tabSpec = tabHost.newTabSpec("RP").setIndicator("Revue de Presse", getResources().getDrawable(R.drawable.icon)).setContent(rss);
        
        tabHost.addTab(tabSpec);
    }
	
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.option, menu);
		return true;
		}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.refresh:
				actuRss.putExtra("flux", "http://www.laquadrature.net/fr/rss.xml");
				RpRss.putExtra("flux", "http://www.laquadrature.net/fr/revue-de-presse/feed");
				return true;
		case R.id.quitter:
				finish();
				return true;
		}
		return false;}
}