package com.example.mateofornescatalog.test;

import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.mateofornescatalog.MainActivity;
import com.example.mateofornescatalog.MovieCatalogService;
import com.example.mateofornescatalog.SearchFilm;

public class SearchFilmActivityTest extends
		ActivityInstrumentationTestCase2<SearchFilm> {

	SearchFilm searchFilm;
	SearchFilm searchFilm2;
	EditText movieName;
	ImageView confirm;
	ImageView home;
	MovieCatalogService service;
	Runnable saveRun;

	public SearchFilmActivityTest() {
		super("com.example.mateofornescatalog.SearchFilm", SearchFilm.class);
	}

	protected void setUp() throws Exception {
		searchFilm = getActivity();
		searchFilm2 = getActivity();
		movieName = (EditText) searchFilm
				.findViewById(com.example.mateofornescatalog.R.id.editName);
		confirm = (ImageView) searchFilm
				.findViewById(com.example.mateofornescatalog.R.id.imageView3);
		home = (ImageButton) searchFilm
				.findViewById(com.example.mateofornescatalog.R.id.imageButton1);
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();

	}
	
	/*
	 * @Justify: This test check if all the components of our SearchFilm activity 
	 * has been set up, when the activity is shown to the user.
	 * 
	 */
	@SmallTest
	public void testViewsCreated() {
		assertNotNull(getActivity());
		assertNotNull(movieName);
		assertNotNull(confirm);
		assertNotNull(home);
	}

	/*
	 * @Justify: This test check if all the elements are visible on the screen.
	 * 
	 */
	@SmallTest
	public void testViewsVisible() {
		ViewAsserts.assertOnScreen(movieName.getRootView(), confirm);
		ViewAsserts.assertOnScreen(confirm.getRootView(), movieName);
	}

	/*
	 * @Justify: This test check if the textbox component is empty at the set up moment.
	 * 
	 */
	@SmallTest
	public void testStartingEmpty() {
		assertTrue("movieName field is empty",
				"".equals(movieName.getText().toString()));
	}

	/*
	 * @Justify: This test check if the textbox component realize it's task (getting 
	 * or receiving text) is done correctly.
	 * 
	 */
	@SmallTest
	public void testGetText() {
		movieName.clearComposingText();
		sendKeys(KeyEvent.KEYCODE_P);
		saveRun = new Runnable() {
			public void run() {
				confirm.performClick();
				assertEquals(movieName.getText(), "P");
			}
		};
	}

	/*
	 * @Justify: This test check if the home button realize it task effectively.
	 * 
	 */
	public void testHomeButton() {
		 saveRun = new Runnable() {
			public void run() {
				home.performClick();
				assertEquals(getActivity().getClass(), MainActivity.class);
			}
		};
	}

}
