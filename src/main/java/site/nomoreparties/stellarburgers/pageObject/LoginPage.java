package site.nomoreparties.stellarburgers.pageObject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    //url страницы авторизации "Вход"
    public static final String URL = "https://stellarburgers.nomoreparties.site/login";

    // Электронная форма "Вход"
    @FindBy(how = How.XPATH, using = ".//label[text() ='Email']/following::input[1]")
    private SelenideElement inputEmail;

    @FindBy(how = How.XPATH, using = ".//label[text() ='Пароль']/following::input[1]")
    private SelenideElement inputPassword;

    @FindBy(how = How.XPATH, using = ".//button[text()='Войти']")
    private SelenideElement buttonLogin;

    @FindBy(how = How.XPATH, using = ".//a[text()='Зарегистрироваться']")
    private SelenideElement buttonRegistration;

    @FindBy(how = How.XPATH, using = ".//a[text()='Восстановить пароль']")
    private SelenideElement buttonForgotPassword;


    // Метод для нажатия на кнопку "Войти"
    @Step("Нажать на кнопку 'Войти'")
    public void clickLoginButton() {
        buttonLogin.click();
    }

    // Метод для нажатия на кнопку "Зарегистрироваться"
    public LoginPage clickRegistrationButton() {
        buttonRegistration.click();
        return this;
    }

    // Метод для нажатия на кнопку "Восстановить пароль"
    public LoginPage clickForgotPasswordButton() {
        buttonForgotPassword.click();
        return this;
    }

    // Метод для заполнения формы значениеми
    private void fillInTheField(SelenideElement formElement, String value) {
        formElement.sendKeys(value);
    }

    // Метод для заполнения элементов формы входа значениями
    @Step("Заполнить форму входа")
    public LoginPage successfulFillLoginForm(String email, String password) {
        fillInTheField(inputEmail, email);
        fillInTheField(inputPassword, password);
        return this;
    }

    // Метод для проверки видимочти кнопка "Войти"
    @Step("Проверить текст для кнопки авторизации пользователя")
    public boolean checkButtonVisible(String buttonText) {
        return $(By.xpath("//button[contains(text(), '" + buttonText + "')]")).shouldBe(visible).exists();
    }
}