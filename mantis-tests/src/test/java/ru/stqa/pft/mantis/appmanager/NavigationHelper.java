package ru.stqa.pft.mantis.appmanager;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(ApplicationManager app){
        super(app);
    }

    public void loginPage(){
        wd.get(app.getProperty("web.baseUrl")+"login_page.php");
    }


    public void manageUsersPage() {
        wd.get(app.getProperty("web.baseUrl") + "manage_user_page.php");
    }

    public void confirmationLink(String confirmationLink) {
        wd.get(confirmationLink);
    }
}
