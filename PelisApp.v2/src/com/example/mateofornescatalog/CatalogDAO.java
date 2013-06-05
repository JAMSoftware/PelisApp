package com.example.mateofornescatalog;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CatalogDAO {

	private SQLiteDatabase db;
	private MovieCatalogSQLiteOpenHelper usdbh;

	public CatalogDAO(Context context) {
		usdbh = new MovieCatalogSQLiteOpenHelper(context);
		db = usdbh.getWritableDatabase();
	}

	// Close the db
	public void close() {
		db.close();
	}

	public MovieCatalog getCatalogue() {

		MovieCatalog movies = new MovieCatalog();
		Movie m = new Movie();
		Cursor c = db.rawQuery(" SELECT * FROM Catalog ", null);
		if (c.moveToFirst()) {
			do {
				m = new Movie();
				m.setName(c.getString(0));
				m.setSummary(c.getString(1));
				m.setQualification(c.getFloat(2));
				movies.setFilm(m.getName(), m);
			} while (c.moveToNext());
		}
		return movies;
	}

	public void insertMovie(ContentValues values) {

		db.insert("Catalog", null, values);
		
	}

}
