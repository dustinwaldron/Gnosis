package org.dwaldron.gnosis.games.ski;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.dwaldron.gnosis.R;
import org.dwaldron.gnosis.R.layout;
import org.dwaldron.gnosis.R.menu;
import org.dwaldron.gnosis.games.User;
import org.dwaldron.gnosis.games.UsersDatabaseHandler;
import org.dwaldron.gnosis.games.frog.FrogGame;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TableLayout;
import android.widget.TextView;

public class SkiGameMain extends Activity implements OnClickListener {

	TableLayout layout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ski_game_main);
		findViewById(R.id.ski_play_button).setOnClickListener(this);
		findViewById(R.id.ski_back_button).setOnClickListener(this);
		layout = (TableLayout) findViewById(R.id.ski_scores_tablelayout);
	}
	
	@Override
	public void onResume(){
		super.onResume();
		layout.removeAllViews();
		
		//UsersDatabaseHandler db = new UsersDatabaseHandler(this);
		ArrayList<TextView> tViews = new ArrayList<TextView>();
		TextView tView = new TextView(this);
		tView.setText("High Scores");
		tView.setGravity(Gravity.CENTER);
		tView.setTextSize(30);
		tView.setTypeface(null, Typeface.BOLD);
		tView.setTextColor(Color.BLACK);
		tViews.add(tView);
		/*List<User> list = db.getAllUsers();
		Collections.sort(list, new Comparator<User>() {

			@Override
			public int compare(User lhs, User rhs) {
				return rhs.getScore() - lhs.getScore();
			}
			
		});
		for (User user : list) {
			TextView tmp = new TextView(this);
			tmp.setText(user.getUsername() + ": " + user.getScore());
			tmp.setGravity(Gravity.CENTER);
			tmp.setTextSize(20);
			tViews.add(tmp);
		} */
		for (TextView view : tViews)
			layout.addView(view);
			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ski_game_main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ski_play_button:
			//startActivity(new Intent(this, SkiGame.class));
			break;
		case R.id.ski_back_button:
			finish();
			break;
	}
		
}
	
	

}
