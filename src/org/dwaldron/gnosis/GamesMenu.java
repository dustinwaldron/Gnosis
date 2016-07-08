package org.dwaldron.gnosis;

import org.dwaldron.gnosis.games.frog.FrogGameMain;
import org.dwaldron.gnosis.games.ski.SkiGameMain;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class GamesMenu extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.games_menu);
		
		View frogButton = this.findViewById(R.id.frog_button);
		frogButton.setOnClickListener(this);
		View skiButton = this.findViewById(R.id.ski_button);
		skiButton.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.games_menu, menu);
		return true;
	}
	
	@Override
	public void onClick(View v) {
		switch(v.getId()) {
			case R.id.frog_button:
				Intent i = new Intent(this, FrogGameMain.class );
				startActivity(i);
				break;
			case R.id.ski_button:
				startActivity(new Intent(this,SkiGameMain.class));
				break;
		}
	}

}
