package com.example.mateofornescatalog.test;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;

import com.example.mateofornescatalog.MainActivity;

public class MainActivityTest extends
		ActivityInstrumentationTestCase2<MainActivity> {

	public MainActivity mainActivity;

	public MainActivityTest() {
		super("com.example.mateofornescatalog.MainActivity", MainActivity.class);

	}

	protected void setUp() throws Exception {
		mainActivity = getActivity();
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/*
	 * @Justify: This test check if the service mIsBound variable is set up at
	 * the onStart() method. If it is true the service and the activity have a
	 * connection, if not there will be a problem.
	 */
	@SmallTest
	public void testOnStart() {
		assertEquals(true, MainActivity.mIsBound);
	}

}
