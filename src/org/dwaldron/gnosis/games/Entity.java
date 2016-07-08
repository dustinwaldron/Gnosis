package org.dwaldron.gnosis.games;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

/*Credit to Adam Keenan for the creation of this code */

public abstract class Entity {
	public float x, y, width, height;
	protected Bitmap bitmap;
	private boolean visible;

	public Entity(float x, float y, float width, float height, Bitmap bitmap) {
		//Log.e("Entity", width + ":" + height);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.bitmap = bitmap;
		this.visible = true;

	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public RectF getRectF() {
		return new RectF(x - 5, y - 5, x + width + 5, y + height + 5);
	}

	public void setCoord(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void draw(Canvas canvas) {
		if (this.visible)
			canvas.drawBitmap(Bitmap.createScaledBitmap(bitmap, (int) width, (int) height, true), x, y, new Paint());
	}
}
