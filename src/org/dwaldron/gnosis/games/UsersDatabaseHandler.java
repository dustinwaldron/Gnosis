package org.dwaldron.gnosis.games;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UsersDatabaseHandler extends SQLiteOpenHelper {
	// Database Version
		private static final int DATABASE_VERSION = 1;

		// Database Name
		private static final String DATABASE_NAME = "usersManager";

		// Questions table name
		private static final String TABLE_USERS = "users";

		// Questions Table Columns names
		private static final String KEY_ID = "id";
		private static final String KEY_USERNAME = "username";
		private static final String KEY_SCORE = "score";

		private static SQLiteDatabase db;

		public UsersDatabaseHandler(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_USERNAME + " TEXT,"
					+ KEY_SCORE + " INTEGER" + ")";
			db.execSQL(CREATE_USERS_TABLE);
			UsersDatabaseHandler.db = db;
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
			onCreate(db);
		}

		public void addUser(User user) {

			ContentValues values = new ContentValues();
			values.put(KEY_USERNAME, user.getUsername());
			values.put(KEY_SCORE, user.getScore());
			SQLiteDatabase db = getWritableDatabase();
			User tmp = getUser(user.getUsername());
			if (tmp == null) {
				db.insert(TABLE_USERS, null, values);
			} else if(user.getScore() > tmp.getScore()) {
				deleteUser(tmp);
				db.insert(TABLE_USERS, null, values);
			}
		}

		public User getUser(String username) {
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor = db.query(TABLE_USERS, new String[] { KEY_ID, KEY_USERNAME, KEY_SCORE }, KEY_ID + "=?", new String[] { username },
					null, null, null, null);
			if (cursor != null) {
				cursor.moveToFirst();
			} else
				return null;
			if(cursor.getCount() < 1)
				return null;
			User user = new User(Integer.parseInt(cursor.getString(0)), cursor.getString(1), Integer.parseInt(cursor.getString(2)));

			return user;
		}

		public List<User> getAllUsers() {
			List<User> userList = new ArrayList<User>();

			String selectQuery = "SELECT  * FROM " + TABLE_USERS;
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			if (cursor.moveToFirst()) {
				do {
					User user = new User();
					user.setID(Integer.parseInt(cursor.getString(0)));
					user.setUsername(cursor.getString(1));
					user.setScore(Integer.parseInt(cursor.getString(2)));

					userList.add(user);
				} while (cursor.moveToNext());
			}
			db.close();
			return userList;
		}

		// Getting question Count
		public int getUsersCount() {
			String questionQuery = "SELECT  * FROM " + TABLE_USERS;
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor = db.rawQuery(questionQuery, null);
			int count = cursor.getCount();
			cursor.close();

			return count;
		}

		// Updating single question
		public int updateQuestion(User user) {
			SQLiteDatabase db = this.getWritableDatabase();

			ContentValues values = new ContentValues();
			values.put(KEY_USERNAME, user.getUsername());
			values.put(KEY_SCORE, user.getScore());

			return db.update(TABLE_USERS, values, KEY_ID + " = ?", new String[] { String.valueOf(user.getID()) });
		}

		// Deleting single question
		public void deleteUser(User user) {
			SQLiteDatabase db = this.getWritableDatabase();
			db.delete(TABLE_USERS, KEY_ID + " = ?", new String[] { String.valueOf(user.getID()) });
		}
}
