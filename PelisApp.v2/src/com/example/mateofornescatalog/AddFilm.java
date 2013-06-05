/*---------------------------------------------------------------
Pr‡ctica V Tutorial Android
Codi Font : AddFilm.java
Master en Informatica
47903898G Mateo Fornes, Jordi
Descripci—n breve del codigo/fichero:
Permite introducir el t’tulo, la descripci—n, la calificaci—n y
si el usuario ha visto/ho quiere ver la pel’cula.
--------------------------------------------------------------- */

package com.example.mateofornescatalog;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;

public class AddFilm extends Activity {

	private ImageButton home;
	private ImageView confirm;
	private EditText filmName;
	private EditText filmSumarize;
	private RatingBar filmQualification;
	private CheckBox filmYes;
	private CheckBox filmYesWant;
	private static Movie movie;
	private AlertDialog alert1;

	private boolean filmSeenByTheUser;
	private boolean userWantToSeeTheFilm;

	private int movieNameTextSize;
	private int movieSummarySize;

	public void initHomeListener() {

		home = (ImageButton) findViewById(R.id.imageButton1);
		home.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent a = new Intent(v.getContext(), MainActivity.class);
				v.getContext().startActivity(a);
			}
		});
	}

	public void initComponents() {
		movie = new Movie();
		filmName = (EditText) findViewById(R.id.editName);
		filmSumarize = (EditText) findViewById(R.id.editResume);
		filmQualification = (RatingBar) findViewById(R.id.ratingBar1);
		filmYes = (CheckBox) findViewById(R.id.checkYes);
		filmYesWant = (CheckBox) findViewById(R.id.CheckYesWant);
		confirm = (ImageView) findViewById(R.id.imageView3);
	}

	public void checkUserChoices() {
		filmSeenByTheUser = filmYes.isChecked();
		userWantToSeeTheFilm = filmYesWant.isChecked();

		

		if (!filmSeenByTheUser) {
			movie.setSee(false);
		} else {
			movie.setSee(true);
		}

		if (!userWantToSeeTheFilm) {
			movie.setWant2see(false);
		} else {
			movie.setWant2see(true);
		}
	}

	public Boolean checkForWarning() {
		int warningWrongParametersMessageIdentifier = R.string.alert_msg1;
		int warningEmptyTextBoxMessageIdentifier = R.string.alert_msg2;
		movieNameTextSize = filmName.getText().length();
		movieSummarySize = filmSumarize.getText().length();
		if (filmSeenByTheUser || userWantToSeeTheFilm) {
			warningDialog(warningWrongParametersMessageIdentifier);
			return true;
		} else if (movieNameTextSize == 0 || movieSummarySize == 0) {
			warningDialog(warningEmptyTextBoxMessageIdentifier);
			return true;
		} else {
			return false;
		}

	}

	public void initConfirmListener() {
		confirm.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Boolean warnings = checkForWarning();
				if (warnings==false) {

					movie.setName(filmName.getText().toString());
					movie.setSummary(filmSumarize.getText().toString());
					movie.setQualification(filmQualification.getRating());
					Intent a = new Intent(v.getContext(), AddFilmCont.class);
					v.getContext().startActivity(a);
				}
			}

		});
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_film);

		initHomeListener();
		initComponents();
		checkUserChoices();
		initConfirmListener();

	}

	public void warningDialog(int warningMessageIdentifier) {
		int warningTitleIdentifier = R.string.alert;
		Builder alert = new AlertDialog.Builder(AddFilm.this);
		alert.setTitle(warningTitleIdentifier);
		alert.setMessage(warningMessageIdentifier);
		alert.setPositiveButton(R.string.alert_button,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						dialog.cancel();
					}
				});
		alert1 = alert.create();
		alert1.show();
	}

	public ImageButton getHome() {
		return home;
	}

	public ImageView getConfirm() {
		return confirm;
	}

	public RatingBar getFilmQualification() {
		return filmQualification;
	}

	public CheckBox getFilmYes() {
		return filmYes;
	}

	public CheckBox getFilmYesWant() {
		return filmYesWant;
	}

	public AlertDialog getAlert1() {
		return alert1;
	}
	
	public boolean isFilmSeenByTheUser() {
		return filmSeenByTheUser;
	}

	public boolean isUserWantToSeeTheFilm() {
		return userWantToSeeTheFilm;
	}

	public int getMovieNameTextSize() {
		return movieNameTextSize;
	}

	public int getMovieSummarySize() {
		return movieSummarySize;
	}

	public static Movie getMovie() {
		return movie;
	}

}
