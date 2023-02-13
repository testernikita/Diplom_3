package site.nomoreparties.stellarburgers;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.client.UserClient;
import site.nomoreparties.stellarburgers.model.User;
import site.nomoreparties.stellarburgers.pageObject.LoginPage;
import site.nomoreparties.stellarburgers.pageObject.HomePage;
import site.nomoreparties.stellarburgers.pageObject.RegistrationPage;
import site.nomoreparties.stellarburgers.utils.UserCredentials;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class RegistrationTest extends BaseTest {
    private UserClient userClient;
    private User user;
    private String userToken;

    @Before
    public void setUp() {
        setUpYandexDriver();

        user = User.getRandom();
        userClient = new UserClient();
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
    @DisplayName("Успешная регистрация пользователя")
    @Description("Успешная регистрация пользователя с валидными данными")
    public void checkingSuccessfulRegistration() {
        final String buttonText = "Войти";
        final String buttonLoginText = "Оформить заказ";

        RegistrationPage registrationPage = open(RegistrationPage.URL, RegistrationPage.class);
        registrationPage.successfulFillRegistrationForm(user.name, user.email, user.password)
                .clickRegistrationButton();

        LoginPage loginPageForm = page(LoginPage.class);

        Assert.assertTrue("Пользователь не был зарегистрирован", loginPageForm.checkButtonVisible(buttonText));

        loginPageForm.successfulFillLoginForm(user.email, user.password).clickLoginButton();

        HomePage homePage = page(HomePage.class);

        Assert.assertTrue("Пользователь не авторизован", homePage.checkButtonVisible(buttonLoginText));
    }

    @Test
    @DisplayName("Ошибка 'Некорректный пароль' введён пароль, длиной меньее 6 символов")
    @Description("Регистрация пользователя невозможна, в случае ввода пароля длиною менее 6 символов")
    public void checkingRegistrationAnIncorrectPassword() {
        final String buttonWarningText = "Некорректный пароль";

        RegistrationPage registrationPage = open(RegistrationPage.URL, RegistrationPage.class);
        registrationPage.successfulFillRegistrationForm(user.name, user.email, RandomStringUtils.randomAlphabetic(3)).clickRegistrationButton();

        Assert.assertTrue("Пользователь не был авторизован", registrationPage.checkErrorMessageVisible(buttonWarningText));
    }
}