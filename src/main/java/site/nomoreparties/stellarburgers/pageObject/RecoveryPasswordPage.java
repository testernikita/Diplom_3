package site.nomoreparties.stellarburgers.pageObject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class RecoveryPasswordPage {
    //url страницы восстановления пароля
    public static final String URL = "https://stellarburgers.nomoreparties.site/forgot-password";

    @FindBy(how = How.XPATH, using = ".//a[text()='Войти']")
    private SelenideElement buttonLogin;

    // Метод для нажатия на кнопку "Войти"
    @Step("Нажать на кнопку \"Войти\"")
    public RecoveryPasswordPage clickRecoveryButton() {
        buttonLogin.click();
        return this;
    }
}