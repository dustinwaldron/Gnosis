package org.dwaldron.gnosis.bible;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/*
 * THIS DB HANDLER NEEDS WORK! LOOK OVER THE SQL STATEMENTS!
 */

public class BibleDatabaseHandler extends SQLiteOpenHelper {
	// Database Version
    private static final int DATABASE_VERSION = 1;

    String[] booksOfBible = {"Genesis","Exodus","Leviticus","Numbers","Deuteronomy","Joshua","Judges",
    		"Ruth","1 Samuel","2 Samuel","1 Kings","2 Kings","1 Chronicles","2 Chronicles","Ezra",
    		"Nehemiah","Esther","Job","Psalms","Proverbs","Ecclesiastes","Song of Solomon",
    		"Isaiah","Jeremiah","Lamentations","Ezekiel","Daniel","Hosea","Joel","Amos","Obadiah","Jonah",
    		"Micah","Nahum","Habakkuk","Zephaniah","Haggai","Zechariah","Malachi","Matthew","Mark","Luke","John","Acts",
    		"Romans","1 Corinthians","2 Corinthians","Galatians","Ephesians","Philippians","Colossians","1 Thessalonians",
    		"2 Thessalonians","1 Timothy","2 Timothy","Titus","Philemon","Hebrews","James","1 Peter","2 Peter",
    		"1 John","2 John","3 John","Jude","Revelation"};
    
    int[] chaptersInEachBook = { 50,40,27,36,34,24,21,4,31,24,22,25,29,36,10,
    							 13, 10, 42, 150, 31, 12, 8, 66, 52, 5, 48, 12,
    							 14, 3, 9, 1, 4, 7, 3, 3, 3, 2, 14, 4, 28, 16,
    							 24, 21, 28,16,16,13,6,6,4,4,5,3,6,4,3,1,13,5,5,
    							 3,5,1,1,1,22 };
    
    String[] versesOfGenesis1 = {"In the beginning God created the heaven and the earth.",
    	 "And the earth was without form, and void; and darkness was upon the face of the deep. And the Spirit of God moved upon the face of the waters.",
    	 "And God said, Let there be light: and there was light.",
    	 "And God saw the light, that it was good: and God divided the light from the darkness.",
    	 "And God called the light Day, and the darkness he called Night. And the evening and the morning were the first day.",
    	 "And God said, Let there be a firmament in the midst of the waters, and let it divide the waters from the waters.",
    	 "And God made the firmament, and divided the waters which were under the firmament from the waters which were above the firmament: and it was so.",
    	 "And God called the firmament Heaven. And the evening and the morning were the second day.",
    	 "And God said, Let the waters under the heaven be gathered together unto one place, and let the dry land appear: and it was so.",
    	 "And God called the dry land Earth; and the gathering together of the waters called he Seas: and God saw that it was good.",
    	 "And God said, Let the earth bring forth grass, the herb yielding seed, and the fruit tree yielding fruit after his kind, whose seed is in itself, upon the earth: and it was so.",
    	 "And the earth brought forth grass, and herb yielding seed after his kind, and the tree yielding fruit, whose seed was in itself, after his kind: and God saw that it was good.",
    	 "And the evening and the morning were the third day.",
    	 "And God said, Let there be lights in the firmament of the heaven to divide the day from the night; and let them be for signs, and for seasons, and for days, and years:",
    	 "And let them be for lights in the firmament of the heaven to give light upon the earth: and it was so.",
    	 "And God made two great lights; the greater light to rule the day, and the lesser light to rule the night: he made the stars also.",
    	 "And God set them in the firmament of the heaven to give light upon the earth,",
    	 "And to rule over the day and over the night, and to divide the light from the darkness: and God saw that it was good.",
    	 "And the evening and the morning were the fourth day.",
    	 "And God said, Let the waters bring forth abundantly the moving creature that hath life, and fowl that may fly above the earth in the open firmament of heaven.",
    	 "And God created great whales, and every living creature that moveth, which the waters brought forth abundantly, after their kind, and every winged fowl after his kind: and God saw that it was good.",
    	 "And God blessed them, saying, Be fruitful, and multiply, and fill the waters in the seas, and let fowl multiply in the earth.",
    	 "And the evening and the morning were the fifth day.",
    	 "And God said, Let the earth bring forth the living creature after his kind, cattle, and creeping thing, and beast of the earth after his kind: and it was so.",
    	 "And God made the beast of the earth after his kind, and cattle after their kind, and every thing that creepeth upon the earth after his kind: and God saw that it was good.",
    	 "And God said, Let us make man in our image, after our likeness: and let them have dominion over the fish of the sea, and over the fowl of the air, and over the cattle, and over all the earth, and over every creeping thing that creepeth upon the earth.",
    	 "So God created man in his own image, in the image of God created he him; male and female created he them.",
    	 "And God blessed them, and God said unto them, Be fruitful, and multiply, and replenish the earth, and subdue it: and have dominion over the fish of the sea, and over the fowl of the air, and over every living thing that moveth upon the earth.",
    	 "And God said, Behold, I have given you every herb bearing seed, which is upon the face of all the earth, and every tree, in the which is the fruit of a tree yielding seed; to you it shall be for meat.",
    	 "And to every beast of the earth, and to every fowl of the air, and to every thing that creepeth upon the earth, wherein there is life, I have given every green herb for meat: and it was so.",
    	 "And God saw every thing that he had made, and, behold, it was very good. And the evening and the morning were the sixth day."};
    
    
    // Database Name
    private static final String DATABASE_NAME = "bible";

    // Contacts table name
    private static final String TABLE_BOOKS = "books";
    private static final String TABLE_CHAPTERS = "chapters";
    private static final String TABLE_VERSES = "verses";

    //Books Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_VERSION = "version";
    private static final String KEY_NUMBER = "number";
    private static final String KEY_TEXT = "text";
    private static final String KEY_BOOK_ID = "book_id";
    private static final String KEY_CHAPTER_ID = "chapter_id";
    private static final String KEY_VERSE_ID = "verse_id";
    

    public BibleDatabaseHandler(Context context) {
    	super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

	    //create books table
		String CREATE_BOOKS_TABLE = "CREATE TABLE " + TABLE_BOOKS + "("
			+ KEY_ID + " INTEGER PRIMARY KEY autoincrement," + KEY_TITLE
			+ " TEXT not null," + KEY_VERSION + " TEXT not null" + ")";
		
		//create chapters table
		String CREATE_CHAPTERS_TABLE = "CREATE TABLE " + TABLE_CHAPTERS
			+ "(" + KEY_ID + " INTEGER PRIMARY KEY autoincrement,"
			+ KEY_NUMBER + " INTEGER," + KEY_BOOK_ID + " INTEGER not null"
			+ ")";
		
		//create verses table
		String CREATE_VERSES_TABLE = "CREATE TABLE " + TABLE_VERSES
			+ "(" + KEY_ID + " INTEGER PRIMARY KEY autoincrement,"
			+ KEY_NUMBER + " INTEGER," + KEY_TEXT + " TEXT not null," + KEY_CHAPTER_ID + " INTEGER not null"
			+ ")";
		
		db.execSQL(CREATE_VERSES_TABLE);
		db.execSQL(CREATE_CHAPTERS_TABLE);
		db.execSQL(CREATE_BOOKS_TABLE);
		
		/*
		 * A really bad hack to get the verses to show up in Bible Activity
		 * Need to elegantly add verses, chapters, and books to bible db
		 * used to finish prototype app
		 */
		for(int i = 0; i < booksOfBible.length;i++){
			addBook(db , new Book(booksOfBible[i], "KJV", new ArrayList<Chapter>()));
		}
		
		Book book = getBook(db, "Genesis");
		int genesis_id = book.getID();
		ArrayList<Chapter> new_chaps = getAllChapters(db, genesis_id);
		for(int i=0;i < 50;i++){
			//addChapter(db, new Chapter(i, book, new ArrayList<Verse>(),book));
			new_chaps.add(new Chapter(i, book, new ArrayList<Verse>()));
		}
		book.setChapters(new_chaps);
		
		Log.e("Chapters: ", String.valueOf(getAllChapters(db,genesis_id).isEmpty()));
		/*
		Chapter genesis_1 = getAllChapters(db,genesis_id).get(0);
		
		int genesis_chapter_1_id = genesis_1.getID();
		
		ArrayList<Verse> new_verses = getAllVerses(db,genesis_chapter_1_id);
		
		for(int i=0; i < versesOfGenesis1.length; i++){
			new_verses.add(new Verse(i,versesOfGenesis1[i],genesis_1));
		}
		
		genesis_1.setVerses(new_verses);
		book.getChapters().add(genesis_1);
		*/
		//addVerse()
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHAPTERS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_VERSES);
		// Create tables again
		onCreate(db);
    }
    
    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new verse, chapter, book
    void addVerse(SQLiteDatabase db,Verse verse, Chapter chapter) {
	
		ContentValues values = new ContentValues();
		values.put(KEY_NUMBER, verse.getNumber());
		values.put(KEY_TEXT, verse.getText());
		values.put(KEY_CHAPTER_ID, chapter.getID()); 
	
		// Inserting Row
		db.insert(TABLE_VERSES, null, values);
		//db.close(); // Closing database connection
    }
    
    void addChapter(SQLiteDatabase db,Chapter chapter, Book book) {
	
		ContentValues values = new ContentValues();
		values.put(KEY_NUMBER, chapter.getNumber()); 
		values.put(KEY_BOOK_ID, book.getID()); 
	
		// Inserting Row
		db.insert(TABLE_CHAPTERS, null, values);
		//db.close(); // Closing database connection
    }
    
    void addBook(SQLiteDatabase db,Book book) {
	
		ContentValues values = new ContentValues();
		values.put(KEY_TITLE, book.getTitle()); 
		values.put(KEY_VERSION, book.getVersion()); 
	
		// Inserting Row
		db.insert(TABLE_BOOKS, null, values);
		//db.close(); // Closing database connection
    }
    
    //get books, chapters, and verses
    
    public Book getBook(SQLiteDatabase db, String title){
    	String selectQuery = "SELECT  * FROM " + TABLE_BOOKS + " WHERE " + KEY_TITLE + " = '" + title + "' ";

		Cursor cursor = db.rawQuery(selectQuery, null);
		cursor.moveToFirst();

		Book book = new Book(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), new ArrayList<Chapter>());

		return book;
    }
    
    public ArrayList<Verse> getAllVerses(SQLiteDatabase db,int chapter_id) {
		ArrayList<Verse> verseList = new ArrayList<Verse>();

		/*
		StringBuilder sb = new StringBuilder();
		sb.append("");
		sb.append(book_id);
		String b_id = sb.toString();
		
		sb = new StringBuilder();
		sb.append("");
		sb.append(chapter_id);
		String c_id = sb.toString();
		
		
		String selectQuery = 
		"SELECT * " +
		" FROM " + TABLE_VERSES +
		" JOIN " + TABLE_CHAPTERS + " ON (" + TABLE_CHAPTERS + ".id = " + TABLE_VERSES + ".chapter_id " +
		" JOIN " + TABLE_BOOKS + " ON (" + TABLE_CHAPTERS + ".book_id = " + TABLE_BOOKS + ".id " +
		" WHERE " + TABLE_VERSES + ".book_id = " + b_id + " AND " + TABLE_VERSES + ".chapter_id = " + c_id;
		*/
		
		String selectQuery = "SELECT * FROM " + TABLE_VERSES + " WHERE " + KEY_CHAPTER_ID + " = " + String.valueOf(chapter_id) +" ";
		//SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				Verse verse = new Verse();
				verse.setID(Integer.getInteger(cursor.getString(0)));
				verse.setNumber(Integer.getInteger(cursor.getString(1)));
				verse.setText(cursor.getString(2));
				verseList.add(verse);
			} while (cursor.moveToNext());
		}

		return verseList;
	}
    
    public ArrayList<Chapter> getAllChapters(SQLiteDatabase db, int book_id) {
		ArrayList<Chapter> chapterList = new ArrayList<Chapter>();
		
		String selectQuery = "SELECT * FROM " + TABLE_CHAPTERS + " WHERE " + KEY_BOOK_ID + " = " + String.valueOf(book_id);
		//SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				Chapter chapter = new Chapter();
				chapter.setID(Integer.getInteger(cursor.getString(0)));
				chapter.setNumber(Integer.getInteger(cursor.getString(1)));
				chapterList.add(chapter);
			} while (cursor.moveToNext());
		}


		return chapterList;
	}
    
    public ArrayList<Book> getAllBooks(SQLiteDatabase db) {
		ArrayList<Book> bookList = new ArrayList<Book>();
		
		String selectQuery = "SELECT * FROM " + TABLE_BOOKS;
		//SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				Book book = new Book();
				book.setID(Integer.getInteger(cursor.getString(0)));
				book.setTitle(cursor.getString(1));
				book.setVersion(cursor.getString(2));
				bookList.add(book);
			} while (cursor.moveToNext());
		}

		return bookList;
	}
}
