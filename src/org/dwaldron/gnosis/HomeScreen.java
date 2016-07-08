package org.dwaldron.gnosis;

import org.dwaldron.gnosis.bible.BibleBookMenu;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class HomeScreen extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_screen);
		
		// Add event listener to buttons here
		View gamesButton = this.findViewById(R.id.games_button);
		gamesButton.setOnClickListener(this);
		View bibleButton = this.findViewById(R.id.bible_button);
		bibleButton.setOnClickListener(this);
		View videoButton = this.findViewById(R.id.video_button);
		videoButton.setOnClickListener(this);
		View aboutButton = this.findViewById(R.id.about_button);
		aboutButton.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.games_button:
				startActivity(new Intent(this, GamesMenu.class));
				break;
			case R.id.bible_button:
				startActivity(new Intent(this, BibleBookMenu.class));
				break;
			case R.id.video_button:
				startActivity(new Intent(this, VideoScreen.class));
				break;
			case R.id.about_button:
				startActivity(new Intent(this, About.class));
				break;
			default:
				startActivity(new Intent(this, About.class));
				break;
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home_screen, menu);
		return true;
	}

}
