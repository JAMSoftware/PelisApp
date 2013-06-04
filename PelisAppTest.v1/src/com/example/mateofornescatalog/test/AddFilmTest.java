package com.example.mateofornescatalog.test;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.example.mateofornescatalog.AddFilm;


public class AddFilmTest extends ActivityInstrumentationTestCase2<AddFilm> {

	AddFilm addfilm;
	public ImageButton home;
	public ImageView confirm;
	public EditText filmName;
	public EditText filmSumarize;
	public RatingBar filmQualification;
	public CheckBox filmNo;
	public CheckBox filmNoWant;
	public CheckBox filmYes;
	public CheckBox filmYesWant;
	public Runnable saveRun;

	public AddFilmTest() {
		super("com.example.mateofornescatalog.AddFilm", AddFilm.class);
	}

	protected void setUp() throws Exception {
		addfilm = getActivity();
		filmName = (EditText) addfilm
				.findViewById(com.example.mateofornescatalog.R.id.editName);
		filmSumarize = (EditText) addfilm
				.findViewById(com.example.mateofornescatalog.R.id.editResume);
		filmQualification = (RatingBar) addfilm
				.findViewById(com.example.mateofornescatalog.R.id.ratingBar1);
		filmNo = (CheckBox) addfilm
				.findViewById(com.example.mateofornescatalog.R.id.CheckNo);
		filmYes = (CheckBox) addfilm
				.findViewById(com.example.mateofornescatalog.R.id.checkYes);
		filmNoWant = (CheckBox) addfilm
				.findViewById(com.example.mateofornescatalog.R.id.CheckNoWant);
		filmYesWant = (CheckBox) addfilm
				.findViewById(com.example.mateofornescatalog.R.id.CheckYesWant);
		confirm = (ImageView) addfilm
				.findViewById(com.example.mateofornescatalog.R.id.imageView3);
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/*
	 * @Justify: This test check if all the components of our SearchFilm
	 * activity has been set up, when the activity is shown to the user.
	 */
	@SmallTest
	public void testViewsCreated() {
		assertNotNull(getActivity());
	}

	/*
	 * @Justify: These tests check if the condition of shown the alertbox1 is done correctly.
	 * 
	 * testSetAlertBox1_1: check if all the conditions are setting true the alertbox must be displayed to the user.
	 * testSetAlertBox1_2: check if all the conditions are setting false the alertbox must be not displayed to the user.
	 * testSetAlertBox1_3: testSetAlertBox1_4: testSetAlertBox1_5: testSetAlertBox1_6:  check other combinations of true/false.
	 * 
	 */
	@SmallTest
	public void testSetAlertBox1_1() {

		filmNo.setChecked(true);
		filmYes.setChecked(true);
		addfilm.filmNoWant.setChecked(true);
		addfilm.filmYesWant.setChecked(true);
		 saveRun = new Runnable() {
			public void run() {
				confirm.performClick();
				assertNotNull(addfilm.alert1);
			}
		};
	}

	@SmallTest
	public void testSetAlertBox1_2() {

		filmNo.setChecked(false);
		filmYes.setChecked(false);
		addfilm.filmNoWant.setChecked(false);
		addfilm.filmYesWant.setChecked(false);
		 saveRun = new Runnable() {
			public void run() {
				confirm.performClick();
				assertNull(addfilm.alert1);
			}
		};
	}

	@SmallTest
	public void testSetAlertBox1_3() {

		filmNo.setChecked(true);
		filmYes.setChecked(true);
		addfilm.filmNoWant.setChecked(false);
		addfilm.filmYesWant.setChecked(false);
		 saveRun = new Runnable() {
			public void run() {
				confirm.performClick();
				assertNotNull(addfilm.alert1);
			}
		};
	}

	@SmallTest
	public void testSetAlertBox1_4() {

		filmNo.setChecked(false);
		filmYes.setChecked(false);
		addfilm.filmNoWant.setChecked(true);
		addfilm.filmYesWant.setChecked(true);
		 saveRun = new Runnable() {
			public void run() {
				confirm.performClick();
				assertNotNull(addfilm.alert1);
			}
		};
	}

	@SmallTest
	public void testSetAlertBox1_5() {

		filmNo.setChecked(true);
		filmYes.setChecked(false);
		addfilm.filmNoWant.setChecked(true);
		addfilm.filmYesWant.setChecked(false);
		saveRun = new Runnable() {
			public void run() {
				confirm.performClick();
				assertNull(addfilm.alert1);
			}
		};
	}

	@SmallTest
	public void testSetAlertBox1_6() {

		filmNo.setChecked(false);
		filmYes.setChecked(true);
		addfilm.filmNoWant.setChecked(false);
		addfilm.filmYesWant.setChecked(true);
		 saveRun = new Runnable() {
			public void run() {
				confirm.performClick();
				assertNull(addfilm.alert1);
			}
		};
	}

	/*
	 * @Justify: These tests check if the condition of shown the alertbox2 is done correctly.
	 * 
	 * testSetAlertBox2_1: check if the 2 textbox components have text inside.
	 * testSetAlertBox2_2: check if only filmSummarize textbox have text inside.
	 * testSetAlertBox2_3:   check if only filmName textbox have text inside.
	 * testSetAlertBox2_4:   check if both textbox have not text inside.
	 * 
	 */
	@SmallTest
	public void testSetAlertBox2_1() {

		saveRun = new Runnable() {
			public void run() {
				addfilm.filmName.setText("Final Fantasy");
				addfilm.filmSumarize.setText("summary");
				confirm.performClick();
				assertNull(addfilm.alert2);
			}
		};
	}

	@SmallTest
	public void testSetAlertBox2_2() {

		saveRun = new Runnable() {
			public void run() {
				addfilm.filmSumarize.setText("summary");
				confirm.performClick();
				assertNotNull(addfilm.alert2);
			}
		};
	}

	@SmallTest
	public void testSetAlertBox2_3() {

		saveRun = new Runnable() {
			public void run() {
				addfilm.filmName.setText("Final Fantasy");
				confirm.performClick();
				assertNotNull(addfilm.alert2);
			}
		};
	}

	@SmallTest
	public void testSetAlertBox2_4() {

		saveRun = new Runnable() {
			public void run() {
				confirm.performClick();
				assertNotNull(addfilm.alert2);
			}
		};
	}

}
