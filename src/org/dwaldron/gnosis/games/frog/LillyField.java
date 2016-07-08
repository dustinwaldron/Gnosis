package org.dwaldron.gnosis.games.frog;

import java.util.ArrayList;

import android.util.Log;

public class LillyField {
	public ArrayList<LillyRow> rows = new ArrayList<LillyRow>(5);

	public boolean add(LillyRow row) {
		if (rows.size() == 5) {
			Log.e("FrogGame", "Rows already full");
			return false;
		}
		rows.add(row);
		return true;
	}

	public void showAll() {
		for (LillyRow row : rows)
			for (LillyPad lilly : row.lillies)
				lilly.setVisible(true);
	}}
