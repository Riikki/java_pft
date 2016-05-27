package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;

public class TestBase {

	protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

	public static final String dataProvider = app.getProperties().getProperty("provider.format");
	private static final int asd = 917;
	@BeforeSuite
	public void setUp() throws Exception {
		app.init();
	}

	@AfterSuite
	public void tearDown() {
		app.stop();
	}

	public static String dataProvider(){
		return app.getProperties().getProperty("provider.format");
	}

}
