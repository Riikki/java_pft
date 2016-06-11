package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.List;

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

	public String findConfirmationLink(List<MailMessage> mailMessages, String email) {
		MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
		VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
		return regex.getText(mailMessage.text);

	}

	public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
		if (isIssueOpen(issueId)) {
			throw new SkipException("Ignored because of issue " + issueId);
		}
	}

	private boolean isIssueOpen(int issueId) throws RemoteException, ServiceException, MalformedURLException {
		IssueData issue = app.soap().getIssue(issueId);
		return issue.getStatus().getId().intValue() != 90;
	}

}
