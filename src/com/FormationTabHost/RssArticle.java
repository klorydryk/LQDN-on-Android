package com.FormationTabHost;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

public class RssArticle extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.articleview);
        
        String[] valeurs = getIntent().getStringArrayExtra("datas");
        String title = valeurs[0];
		TextView titleView = (TextView) findViewById(R.id.title);
        String content = valeurs[1];
		titleView.setText(title);
        WebView contentView = (WebView) findViewById(R.id.content);

// modification de la page affichée
        String cssBegin = "<html>" +
    					"<head>" +
					        "<meta charset=\"utf-8\" />" +
					        "<style>"+
					            "body"+
					            "{"+
					            	"font-size: 12px;"+
					                "color: #CCCCCC ;"+
					                "background-color: black"+
					            "}"+
					            "a"+
					            "{"+
				                "color: white;"+
				                "}"+
					        "</style>"+
					        "<title>Premiers tests du CSS</title>"+
					    "</head>";
        String cssEnd = "</html>";
        
        String finalString = cssBegin + content + cssEnd;
 		contentView.loadData(finalString, "text/html", "unicode");
    }
}
