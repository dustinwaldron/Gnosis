package org.dwaldron.gnosis.games.frog;

import java.util.ArrayList;
import java.util.Collections;

public class LillyRow {
public ArrayList<LillyPad> lillies = new ArrayList<LillyPad>(4);
	
	/**
	 * Will only add first 4
	 * @param lillyPads
	 */
	public LillyRow(LillyPad...lillyPads) {
		for(LillyPad lilly : lillyPads) {
			if (lillies.size() < 4)
				lillies.add(lilly);
			else
				break;
		}
		Collections.shuffle(lillies);
	}
	
	public boolean add(LillyPad pad) {
		if (lillies.size() == 4)
			return false;
		lillies.add(pad);
		return true;
	}

	public void clearText() {
		for(LillyPad lilly : lillies)
			lilly.setText("");
	}
}
