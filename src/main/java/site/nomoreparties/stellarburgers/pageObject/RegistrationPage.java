package site.nomoreparties.stellarburgers.pageObject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationPage {
    //url страницы регистрации
    public static final String URL = "https://stellarburgers.nomoreparties.site/register";

    //Электронная форма "Регистрация"
    @FindBy(how = How.XPATH, using = ".//label[text() ='Имя']/following::input[1]")
    private SelenideElement inputName;

    @FindBy(how = How.XPATH, using = ".//label[text() ='Email']/following::input[1]")
    private SelenideElement inputEmail;

    @FindBy(how = How.XPATH, using = ".//label[text() ='Пароль']/following::input[1]")
    private SelenideElement inputPassword;

    @FindBy(how = How.XPATH, using = ".//button[text()='Зарегистрироваться']")
    private SelenideElement registrationButton;

    @FindBy(how = How.XPATH, using = ".//a[text()='Войти']")
    private SelenideElement loginButton;

    @FindBy(how = How.XPATH, using = ".//p[@class='input__error' and text()='Некорректный пароль']")
    private SelenideElement errorMessage;


    // Метод для нажатия на кнопку "Войти"
    @Step("Нажать на кнопку \"Войти\"")
    public RegistrationPage clickLoginButton() {
        loginButton.click();
        return this;
    }

    // Метод нажатия на кнопку "Зарегистрироваться"
    @Step("Нажать на кнопку \"Зарегистрироваться\"")
    public RegistrationPage clickRegistrationButton() {
        registrationButton.click();
        return this;
    }

    // Метод заполнения значениями полей формы регистрации пользователя
    private void fillFormFields(SelenideElement formElement, String value) {
        formElement.sendKeys(value);
    }

    // Метод для заполнения формы регистрации пользователя
    @Step("Заполнить поля формы регистрации")
    public RegistrationPage successfulFillRegistrationForm(String name, String email, String password) {
        fillFormFields(inputName, name);
        fillFormFields(inputEmail, email);
        fillFormFields(inputPassword, password);
        return this;
    }

    // Метод для проверки отображаемого текста ошибки на странице
    @Step("Проверить текст ошибки")
    public boolean checkErrorMessageVisible(String errorText) {
        return $(By.xpath("//p[contains(text(), '" + errorText + "')]")).shouldBe(visible).exists();
    }
}