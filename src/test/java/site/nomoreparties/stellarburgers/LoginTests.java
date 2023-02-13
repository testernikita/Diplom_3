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
import site.nomoreparties.stellarburgers.pageObject.RecoveryPasswordPage;
import site.nomoreparties.stellarburgers.pageObject.RegistrationPage;
import site.nomoreparties.stellarburgers.utils.UserCredentials;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class LoginTests extends BaseTests {
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
    @DisplayName("Вход по кнопке \"Войти в аккаунт\" на главной")
    @Description("Пользователь может осуществить вход в свой аккаунт, нажав на кнопку \"Войти в аккаунт\" на главной странице")
    public void checkingLoginByClickOnTheButtonOnTheMainPage() {
        final String buttonText = "Оформить заказ";

        HomePage homePage = open(HomePage.URL, HomePage.class);
        homePage.clickLoginButton();

        LoginPage loginPageForm = page(LoginPage.class);
        loginPageForm.successfulFillLoginForm(user.email, user.password).clickLoginButton();
        Assert.assertTrue("Пользователь не был авторизован", homePage.checkButtonVisible(buttonText));
    }

    @Test
    @DisplayName("Вход через кнопку \"Личный кабинет\"")
    @Description("Пользователь может осуществить вход в свой аккаунт, нажав на кнопку \"Личный кабинет\" на главной странице")
    public void checkingLoginThroughPersonalArea() {
        final String buttonText = "Оформить заказ";

        HomePage homePage = open(HomePage.URL, HomePage.class);
        homePage.clickPersonalAccountButtonHeader();

        LoginPage loginPageForm = page(LoginPage.class);
        loginPageForm.successfulFillLoginForm(user.email, user.password).clickLoginButton();

        Assert.assertTrue("Пользователь не был авторизован", homePage.checkButtonVisible(buttonText));
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Пользователь может осуществить вход в свой аккаунт, нажав на кнопку \"Войти\" на странице регистрации")
    public void checkingLoginFromRegistrationPage() {
        final String buttonText = "Оформить заказ";

        RegistrationPage registrationPage = open(RegistrationPage.URL, RegistrationPage.class);
        registrationPage.clickLoginButton();

        LoginPage loginPageForm = page(LoginPage.class);
        loginPageForm.successfulFillLoginForm(user.email, user.password).clickLoginButton();

        HomePage homePage = page(HomePage.class);

        Assert.assertTrue("Пользователь не был авторизован", homePage.checkButtonVisible(buttonText));
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Пользователь может осуществить вход в свой аккаунт нажав на кнопку кнопку \"Войти\" на странице восстановления пароля")
    public void checkingLoginFromRecoveryPasswordPage() {
        final String buttonText = "Оформить заказ";

        RecoveryPasswordPage recoveryPasswordPage = open(RecoveryPasswordPage.URL, RecoveryPasswordPage.class);
        recoveryPasswordPage.clickRecoveryButton();

        LoginPage loginPageForm = page(LoginPage.class);
        loginPageForm.successfulFillLoginForm(user.email, user.password).clickLoginButton();

        HomePage homePage = page(HomePage.class);

        Assert.assertTrue("Пользователь не был авторизован", homePage.checkButtonVisible(buttonText));
    }
}