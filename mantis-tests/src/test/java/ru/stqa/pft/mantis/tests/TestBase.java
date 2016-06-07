package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import java.io.File;
import java.io.IOException;

public class TestBase {

	protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

	private String file;
	private String backup;
	@BeforeSuite
	public void setUp() throws Exception {
		app.init();
		file = app.getProperty("ftp.pathToMantis") + "config_inc.php";
		backup = app.getProperty("ftp.pathToMantis") + "config_inc.php.bac";
		String absolutePath = new File("src/test/resources/config_inc.php").getAbsolutePath();
		app.ftp().upload(new File("src/test/resources/config_inc.php"), file, backup);
	}

	@AfterSuite(alwaysRun = true)
	public void tearDown() throws IOException {
		app.ftp().restore(backup, file);
		app.stop();
	}


}
