import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(description = "User should be able to log in")
    public void userLogInTest() {
        boolean isLoggedIn = site.loginSteps()
                .openSite()
                .loginAs(user, password)
                .isUserLoggedIn();
        Assert.assertTrue(isLoggedIn, "User not logged in!");
    }
}
