package ru.stqa.pft.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase {

    private String username;
    private String email;
    private String password;
    private String adminUsername = "administrator";
    private String adminPassword = "root";

    @BeforeMethod
    public void prepareForTest() throws IOException, MessagingException {
        app.mail().start();

        long now = System.currentTimeMillis();
        this.email = "email" + now + "@email.com";
        this.username = "user" + now;
        this.password = "password";

        if(!app.newSession().login(username,password)){
            app.registration().start(username, email);
            List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
            String confirmationLink = findConfirmationLink(mailMessages, email);
            app.registration().finish(confirmationLink, password);
        }
        app.goTo().loginPage();
        app.users().loginAsAdmin(adminUsername,adminPassword);
    }

    @Test
    public void testAdminChangePassword() throws IOException, MessagingException {
        String newPassword = password + "1";

        app.users().selectUser(username).resetPassword();
        List<MailMessage> mailMessages = app.mail().waitForMail(3, 10000);
        String confirmationLink = findChangePasswordLink(mailMessages, email);
        app.users().changePassword(confirmationLink, newPassword);

        assertTrue(app.newSession().login(username,newPassword));
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }

    public String findChangePasswordLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream()
                .filter((m) -> m.to.equals(email))
                .filter((m) -> m.text.contains("requested a password"))
                .findFirst().get();

        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);

    }

}
