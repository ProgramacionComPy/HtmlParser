package com.example.htmlparser;

/*
 * Utilizar JSOUP en Android
 * www.programacion.com.py - Recursos y documentación para desarrolladores - By Rodrigo Paszniuk
 * PD: Para agregar una libreria .jar cualquiera al proyecto solamente se debe agregar a la carpeta libs del mismo.
 * Para actualizar cambios hacer click derecho al proyecto y luego click izquierdo a Refresh.
 */

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private String title;
	private String imagen;
	private TextView tit;
	private TextView text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Vincular textviews a utilizar
		tit = (TextView)findViewById(R.id.tit);
		text = (TextView)findViewById(R.id.text);
		
		//Crear un nuevo hilo
		new Thread(new Runnable() {
		    public void run() {
		    	Document doc;
		    	try {		     
		    		// necesitará protocolo http
		    		doc = Jsoup.connect("http://www.google.com.py/")			        
		    				.userAgent("Mozilla")
		    		        .get();	     
		    		title = doc.title();     	    		
		            Element image = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]").first();
		            imagen=image.attr("alt").toString();
		            
		    	} catch (IOException e) {
		    		e.printStackTrace();
		    	}		     
		    	//Mostrar los resultados.
		    	runOnUiThread(new Runnable() {
		    	    public void run() {
		    	    	tit.setText(title);
		    	    	text.setText(imagen);
		    	        Toast.makeText(MainActivity.this, "Tarea finalizada!", Toast.LENGTH_SHORT).show();
		    	    }
		    	});
		    }
		}).start();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
