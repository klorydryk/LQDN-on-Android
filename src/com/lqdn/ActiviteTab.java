package com.lqdn;

import com.FormationTabHost.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ActiviteTab extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab);
		
		// On récupère notre intent et la valeur nommée valeur
		String valeur = getIntent().getStringExtra("valeur");
		
		// On affiche cette chaîne dans le textview
		TextView textView = (TextView) findViewById(R.id.monTextView);
		textView.setText(valeur);
	}
}
