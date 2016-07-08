package org.dwaldron.gnosis.bible;

import org.dwaldron.gnosis.R;
import org.dwaldron.gnosis.R.layout;
import org.dwaldron.gnosis.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class BibleBookMenu extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bible_book_menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bible_book_menu, menu);
		return true;
	}

}
