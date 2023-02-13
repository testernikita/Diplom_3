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

public class HeaderButtonsTests extends BaseTests{

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
    @DisplayName("Переход в личный кабинет")
    @Description("Авторизовавшейся пользователь может перейти в личный кабинет по кнопке \"Личный кабинет\"")
    public void checkingUserRedirectionToPersonalAccountByClickPersonalAccountButton() {
        final String buttonText = "Выход";

        LoginPage loginPageForm = open(LoginPage.URL, LoginPage.class);
        loginPageForm.successfulFillLoginForm(user.email, user.password).clickLoginButton();

        HomePage homePage = page(HomePage.class);
        homePage.clickPersonalAccountButtonHeader();

        PersonalAccountPage personalAccountPage = page(PersonalAccountPage.class);

        Assert.assertTrue("Пользователь не перешел в личный кабинет", personalAccountPage.checkCurrentPersonalAreaButton(buttonText));
    }

    @Test
    @DisplayName("Переход в конструктор по клику на элемент с именем «Конструктор» в заголовке страницы")
    @Description("Авторизованный пользователь выполняет переход в конструктор, нажав на элемент \"Конструктор\" в заголовке страницы")
    public void checkingUserCanGoToMainPageByClickToConstructorButton() {
        final String headerText = "Соберите бургер";

        LoginPage loginPageForm = open(LoginPage.URL, LoginPage.class);
        loginPageForm.successfulFillLoginForm(user.email, user.password).clickLoginButton();

        HomePage homePage = page(HomePage.class);
        homePage.clickPersonalAccountButtonHeader();

        PersonalAccountPage personalAccountPage = page(PersonalAccountPage.class);
        personalAccountPage.clickConstructorButton();

        Assert.assertTrue("Пользователь не перешел на главную страницу", homePage.checkHeaderVisible(headerText));
    }

    @Test
    @DisplayName("Переход в конструктор по клику на логотип Stellar Burgers в заголовке страницы")
    @Description("Авторизованный пользователь выполняет переход в конструктор, нажав на логотип в заголовке страницы")
    public void checkingUserCanGoToHomePageByClickToLogo() {
        final String headerText = "Соберите бургер";

        LoginPage loginPageForm = open(LoginPage.URL, LoginPage.class);
        loginPageForm.successfulFillLoginForm(user.email, user.password).clickLoginButton();

        HomePage homePage = page(HomePage.class);
        homePage.clickPersonalAccountButtonHeader();

        PersonalAccountPage personalAccountPage = page(PersonalAccountPage.class);
        personalAccountPage.clickLogoButton();

        Assert.assertTrue("Пользователь не перешел на главную страницу", homePage.checkHeaderVisible(headerText));
    }
}