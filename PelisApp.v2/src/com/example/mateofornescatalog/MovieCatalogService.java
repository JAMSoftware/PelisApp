/*---------------------------------------------------------------
Pr‡ctica V Tutorial Android
Codi Font : MovieCatalogService.java
Master en Informatica
47903898G Mateo Fornes, Jordi
Descripci—n breve del codigo/fichero:
Servicio que contiene la base de datos de las pel’culas.
--------------------------------------------------------------- */

package com.example.mateofornescatalog;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.util.Iterator;
import android.app.Service;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

public class MovieCatalogService extends Service {
	public static MovieCatalog movies = new MovieCatalog();
	MovieMessageParceable inMovie;
	MovieMessageParceable outMovie;
	String movieName;
	Movie m = new Movie();
	static final int MSG_SEND_MOVIE = 1;
	static final int MSG_SAVE_MOVIE = 2;
	static final int MSG_VETE_MIERDA = 3;
	static final int MSG_SAVE_CATALOG = 4;
	static final int MSG_RESTORE_CATALOG = 5;
	static final int MSG_RESTORE_CATALOG_BD = 6;
	Context context;
	final public static String MOVIECATALOG_SERVICE = "MOVIECATALOG_SERVICE";
	final public static String USER_CONNECT_SERVER_REQUEST = "USER_CONNECT_SERVER";
	final public static String USER_CLOSE_SERVER_CONNECTION__REQUEST = "USER_CLOSE_SERVER_CONNECTION";

	class IncomingHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			outMovie = new MovieMessageParceable(MSG_VETE_MIERDA);
			Bundle b;
	
			switch (msg.what) {
			case MSG_SAVE_MOVIE:
				b = msg.getData();
				b.setClassLoader(MovieMessageParceable.class.getClassLoader());
				inMovie = (MovieMessageParceable) b
						.getParcelable("MovieMessageParcelable");
				movies.setFilm(inMovie.getMovie().getName(), inMovie.getMovie());
				break;

			case MSG_SEND_MOVIE:
				b = msg.getData();
				b.setClassLoader(MovieMessageParceable.class.getClassLoader());
				inMovie = (MovieMessageParceable) b
						.getParcelable("MovieMessageParcelable");
				movieName = inMovie.getMovieName();
				if (movies.existFilm(movieName)) {
					m = (movies.getFilm(movieName));

				}
				try {

					MovieMessageParceable ofmsg = new MovieMessageParceable(0,
							inMovie.getFrom(), null, "", m.getName(), m);
					Bundle b2 = new Bundle();
					b2.putParcelable("MovieMessageParcelable", ofmsg);
					Message msg2 = Message.obtain(null,
							SearchFilm.MSG_VETE_MIERDA, 0, 0);
					msg2.setData(b2);

					msg.replyTo.send(msg2);

				} catch (Exception e) {
					e.printStackTrace();
				}

				break;
			case MSG_SAVE_CATALOG:
				context = getApplicationContext();
				FileOutputStream fStream = null;
				ObjectOutputStream oStream = null;

				try {
					fStream = context.openFileOutput("catalog.xml",
							Context.MODE_PRIVATE);
					;
					oStream = new ObjectOutputStream(fStream);
					Iterator<String> myVeryOwnIterator = movies.getMovies()
							.keySet().iterator();
					while (myVeryOwnIterator.hasNext()) {
						String key = (String) myVeryOwnIterator.next();
						Movie value = (Movie) movies.getMovies().get(key);

						oStream.writeObject(value);
						ContentValues values = new ContentValues();
						values.put("name", value.getName());
						values.put("description", value.getSummary());
						values.put("qualification", value.getQualification());
						CatalogDAO daoCatalog = new CatalogDAO(MovieCatalogService.this);
						daoCatalog.insertMovie(values);
					}
					
					oStream.flush();
					oStream.close();
					fStream.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				break;
			case MSG_RESTORE_CATALOG:
				context = getApplicationContext();
				ObjectInputStream ois = null;
				FileInputStream fint = null;
				try {
					fint = context.openFileInput("catalog.xml");

					ois = new ObjectInputStream(fint);

					movies.reset();
					CatalogDAO daoCatalog = new CatalogDAO(MovieCatalogService.this);
					movies=daoCatalog.getCatalogue();
					Movie m = (Movie) ois.readObject();
					while (m != null) {
						movies.setFilm(m.getName(), m);
						m = (Movie) ois.readObject();
					}

					ois.close();
					fint.close();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			case MSG_RESTORE_CATALOG_BD:
				movies.reset();
				
			default:
				super.handleMessage(msg);
			}
		}
	}

	final Messenger mMessenger = new Messenger(new IncomingHandler());

	@Override
	public IBinder onBind(Intent intent) {
		return mMessenger.getBinder();
	}
}
