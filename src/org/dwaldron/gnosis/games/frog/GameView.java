package org.dwaldron.gnosis.games.frog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.dwaldron.gnosis.R;
import org.dwaldron.gnosis.games.Question;
import org.dwaldron.gnosis.games.QuestionsDatabaseHandler;
import org.dwaldron.gnosis.games.User;
import org.dwaldron.gnosis.games.UsersDatabaseHandler;

import android.view.View;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.StaticLayout;
import android.text.Layout.Alignment;
import android.text.TextPaint;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

public class GameView extends View {
	private Paint paint;
	private TextPaint textPaint;

	private Bitmap lillyImg;
	private Bitmap frog;
	private Bitmap frogJump;
	private Bitmap log;
	private Bitmap heart;

	private enum FrogState {
		SIT, JUMP
	}

	private FrogState curFrogState = FrogState.SIT;

	private LillyPad frogLoc;
	private LillyPad logLilly;
	private LillyPad startLilly;

	private LillyField field;

	private Random rand;

	private int qNum;
	private int y;
	private int curLevel;
	private int lives = 3;
	private Question curQ;
	ArrayList<Question> questions;

	StaticLayout layout;

	QuestionsDatabaseHandler qDB;
	UsersDatabaseHandler uDB;

	Display display;
	private boolean nextScreen;

	public GameView(Context context) {
		super(context);
		WindowManager wm = (WindowManager) this.getContext().getSystemService(Context.WINDOW_SERVICE);
		display = wm.getDefaultDisplay();
		paint = new Paint();
		textPaint = new TextPaint();
		textPaint.setColor(Color.YELLOW);
		lillyImg = BitmapFactory.decodeResource(getResources(), R.drawable.lily_pad2_trans);
		int fsize = (int) (100 * ((float) display.getHeight() / 1200));
		frog = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.sitting_frog_trans), fsize, fsize, true);
		frogJump = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.jumping_frog_trans), 100, 120, true);
		Bitmap tmp = BitmapFactory.decodeResource(getResources(), R.drawable.log_trans);
		log = Bitmap.createScaledBitmap(tmp, display.getWidth(), (int) (tmp.getHeight() * ((float) display.getHeight() / 2000)), true);
		tmp = BitmapFactory.decodeResource(getResources(), R.drawable.heart);
		int hsize = (int) (30 * ((float) display.getHeight() / 1200));
		heart = Bitmap.createScaledBitmap(tmp, hsize, hsize, true);
		rand = new Random();
		qDB = new QuestionsDatabaseHandler(getContext());
		uDB = new UsersDatabaseHandler(getContext());
		init();
	}

	private void init() {
		questions = new ArrayList<Question>();
		startLilly = new LillyPad("", 0, 0, (display.getWidth() - frog.getWidth()) / 2, (display.getHeight() - log.getHeight())
				+ log.getHeight() / 2 - frog.getHeight() / 2, 100, 100, -1, lillyImg);
		frogLoc = startLilly;
		logLilly = new LillyPad("", 0, 0, 0, 0, display.getWidth(), 150, 5, lillyImg);
		logLilly.setVisible(false);
		for (Question question : qDB.getAllQuestions()) {
			questions.add(question);
		}
		Collections.shuffle(questions, rand);
		build();
	}

	private void build() {
		if (field != null) {
			curQ = questions.get(qNum);
			field.showAll();
			field.rows.get(qNum - (curLevel * 5 + 1)).clearText();
			logLilly.setText(curQ.getBody());
			qNum++;
		} else {
			qNum = curLevel * 5;
			curQ = questions.get(qNum);
			logLilly.setText(curQ.getBody());
			Question cur = curQ;
			int[] dirs = new int[5];
			dirs[0] = rand.nextInt(2);
			dirs[1] = rand.nextInt(2);
			dirs[2] = rand.nextInt(2);
			dirs[3] = rand.nextInt(2);
			dirs[4] = rand.nextInt(2);
			field = new LillyField();
			int dx = display.getWidth() / 4;
			int x = 0;
			int speed, size = (int) (100 * ((float) display.getWidth() / 800));
			int y = display.getHeight() - log.getHeight() - size;
			for (int i = 0; i < dirs.length; i++) {
				speed = rand.nextInt(3) + 2;
				field.add(new LillyRow(new LillyPad(cur.getAnswer(), speed, dirs[i], x + rand.nextInt(size), y, size, size, i, lillyImg),
						new LillyPad(cur.getOption1(), speed, dirs[i], x + dx + rand.nextInt(size), y, size, size, i, lillyImg),
						new LillyPad(cur.getOption2(), speed, dirs[i], x + dx * 2 + rand.nextInt(size), y, size, size, i, lillyImg),
						new LillyPad(cur.getOption3(), speed, dirs[i], x + dx * 3 + rand.nextInt(size), y, size, size, i, lillyImg)));
				y -= size * 1.75;
				cur = questions.get(qNum + i + 1);
				x = rand.nextInt(display.getWidth() - 100);
			}
			qNum++;
		}
		textPaint.setTextSize(36 / (this.curQ.getBody().length() > 500 ? 2 : 1));
		layout = new StaticLayout(this.curQ.getBody(), 0, this.curQ.getBody().length(), textPaint, log.getWidth(), Alignment.ALIGN_CENTER,
				1f, 1f, false);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (nextScreen)
			y += 30;
		if (y > display.getHeight() - log.getHeight()) {
			nextScreen = false;
			y = 0;
			frogLoc = startLilly;
			field = null;
			build();
		}
		canvas.translate(0, y);
		paint.setColor(Color.BLUE);
		canvas.drawPaint(paint);

		// Question log
		canvas.drawBitmap(log, 0, 0, null);
		paint.setColor(Color.YELLOW);

		canvas.drawBitmap(log, 0, canvas.getHeight() - log.getHeight(), null);
		logLilly.draw(canvas);
		for (LillyRow row : field.rows)
			for (LillyPad pad : row.lillies) {
				pad.move(this, canvas);
				pad.draw(canvas);
			}

		// Draw frog
		canvas.drawBitmap(curFrogState == FrogState.SIT ? frog : frogJump, frogLoc.x + frogLoc.width / 2 - frog.getWidth() / 2, frogLoc.y,
				null);
		paint.setTextSize(36);
		canvas.drawText("Level: " + (curLevel + 1), 60, display.getHeight() - log.getHeight() / 2, paint);
		canvas.drawText("Lives: " /* + lives */, 60, display.getHeight() - log.getHeight() / 2 + 40, paint);
		for (int i = 0; i < lives; i++)
			canvas.drawBitmap(heart, 150 + i * heart.getWidth(), display.getHeight() - log.getHeight() / 2 + 15, paint);

		this.invalidate();

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			for (LillyRow row : field.rows)
				for (LillyPad lilly : row.lillies) {
					if (new RectF(lilly.getRectF()).contains(event.getX(), event.getY())) {
						if (lilly.inRow(qNum - (5 * curLevel + 1))) {
							if (curQ.getAnswer() != lilly.text) {
								lilly.setVisible(false);
								lilly.setTextVisible(false);
								lives--;
								frogLoc = startLilly;
								field = null;
								if (lives == 0) {
									Toast.makeText(getContext(), "You lost all of your lives.", Toast.LENGTH_SHORT).show();
									final EditText input = new EditText(getContext());
									new AlertDialog.Builder(getContext()).setTitle("HighScores").setView(input)
											.setMessage("Enter you name for HighScores list")
											.setPositiveButton("Save", new DialogInterface.OnClickListener() {
												@Override
												public void onClick(DialogInterface dialog, int which) {
													uDB.addUser(new User(input.getText().toString(), curLevel));
													((Activity) getContext()).onBackPressed();
												}
											}).setNegativeButton("Don't Save", new DialogInterface.OnClickListener() {
												@Override
												public void onClick(DialogInterface dialog, int which) {
													((Activity) getContext()).onBackPressed();
												}
											}).show();
								} else
									Toast.makeText(getContext(), "You perished in the depths.", Toast.LENGTH_SHORT).show();
							} else
								frogLoc = lilly;

							build();
						}
						break;
					}
				}
			if (qNum == 5 * (curLevel + 1) + 1) {
				logLilly.setText("");
				if (new RectF(logLilly.getRectF()).contains(event.getX(), event.getY())) {
					curLevel++;
					if (curLevel == 8) {
						Toast.makeText(getContext(), "You win all the smarts!", Toast.LENGTH_LONG).show();
						final EditText input = new EditText(getContext());
						new AlertDialog.Builder(getContext()).setTitle("HighScores").setView(input)
								.setMessage("Enter you name for HighScores list")
								.setPositiveButton("Save", new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog, int which) {
										uDB.addUser(new User(input.getText().toString(), curLevel));
										((Activity) getContext()).onBackPressed();
									}
								}).setNegativeButton("Don't Save", new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog, int which) {
										((Activity) getContext()).onBackPressed();
									}
								}).show();
					} else {
						nextScreen = true;
						frogLoc = logLilly;
						lives += lives < 3 ? 1 : 0;
					}
				}
			}
		}
		return true;
	}

	interface Executable {
		public void exec(Object... obj);
	}

	class Timer extends Thread {
		Executable func;
		int wait;
		boolean repeat;
		Object[] params;

		public Timer(int wait, boolean repeat, Executable func, Object... params) {
			this.wait = wait;
			this.repeat = repeat;
			this.func = func;
			this.params = params;
		}

		@Override
		public void run() {
			do {
				try {
					super.sleep(this.wait);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				func.exec(params);
			} while (repeat);
		}
	}
}
