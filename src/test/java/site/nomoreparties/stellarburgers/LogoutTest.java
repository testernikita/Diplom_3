package site.nomoreparties.stellarburgers;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.client.UserClient;
import site.nomoreparties.stellarburgers.model.User;
import site.nomoreparties.stellarburgers.pageObject.HomePage;
import site.nomoreparties.stellarburgers.pageObject.LoginPage;
import site.nomoreparties.stellarburgers.pageObject.PersonalAccountPage;
import site.nomoreparties.stellarburgers.utils.UserCredentials;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class LogoutTest extends BaseTests{

    private UserClient userClient;
    private User user;
    private String userToken;

    @Before
    public void setUp() {
        setUpYandexDriver();

        user = User.getRandom();
        userClient = new UserClient();
        userClient.registrationUser(user);
    }

    @After
    public void tearDown() {
        int statusCode = userClient.loginUser(UserCredentials.from(user)).statusCode();
        if(statusCode == 200) {
            userToken = userClient.loginUser(UserCredentials.from(user)).body().path("accessToken").toString();
            userClient.deleteUser(userToken);
        }

        closeYandexDriver();
    }

    @Test
    @DisplayName("Выход по кнопке «Выйти» в личном кабинете")
    @Description("Авторизованный пользователь выходит из аккаунта по кнопке 'Выход' в личном кабинете")
    public void checkingLogoutPersonalArea() {
        final String buttonText = "Войти";

        LoginPage loginPageForm = open(LoginPage.URL, LoginPage.class);
        loginPageForm.successfulFillLoginForm(user.email, user.password)
                .clickLoginButton();

        HomePage homePage = page(HomePage.class);
        homePage.clickPersonalAccountButtonHeader();

        PersonalAccountPage personalAccountPage = page(PersonalAccountPage.class);
        personalAccountPage.clickLogoutButton();

        Assert.assertTrue("Пользователь не вышел из аккаунта", loginPageForm.checkButtonVisible(buttonText));
    }

}