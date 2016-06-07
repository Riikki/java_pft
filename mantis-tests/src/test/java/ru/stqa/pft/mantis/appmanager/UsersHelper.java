package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.lanwen.verbalregex.VerbalExpression;

public class UsersHelper extends HelperBase {

    public UsersHelper(ApplicationManager app){
        super(app);
    }

    public UsersHelper selectUser(String username) {
        app.goTo().manageUsersPage();
        click(By.linkText(username));
        return this;
    }

    public void loginAsAdmin(String adminUsername, String adminPassword) {
        type(By.name("username"),adminUsername);
        type(By.name("password"),adminPassword);
        click(By.cssSelector("input[value='Login']"));
    }

    public void resetPassword() {
        click(By.cssSelector("input[value='Reset Password']"));
    }

    public void changePassword(String confirmationLink, String newPassword) {
        app.goTo().confirmationLink(confirmationLink);
        type(By.name("password"),newPassword);
        type(By.name("password_confirm"),newPassword);
        click(By.cssSelector("input[value='Update User']"));
    }
}
