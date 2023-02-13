package site.nomoreparties.stellarburgers.pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class PersonalAccountPage {

    //url станицы "Личный кабинет"
    public static final String URL = "https://stellarburgers.nomoreparties.site/account/profile";

    @FindBy(how = How.XPATH, using = "//button[text()='Выход']")
    private SelenideElement buttonLogout;

    @FindBy(how = How.XPATH, using = ".//p[text()='Конструктор']")
    private SelenideElement buttonConstruction;

    @FindBy(how = How.XPATH, using = ".//div[@class='AppHeader_header__logo__2D0X2']")
    private SelenideElement buttonLogo;

    // Метод для нажатия на кнопку "Выход"
    @Step("Нажать на кнопку \"Выход\"")
    public void clickLogoutButton() {
        buttonLogout.shouldBe(visible).click();
    }

    // Метод для выбора вкладки "Конструктор"
    @Step("Нажать на кнопку \"Конструктор\"")
    public void clickConstructorButton() {
        buttonConstruction.shouldBe(visible).click();
    }

    // Метод для нажатия на "Логотип" приложения
    @Step("Нажать на доготип приложения")
    public void clickLogoButton() {
        buttonLogo.shouldBe(visible).click();
    }

    // Метод для проверки значения текста для кнопки
    @Step("Проверить значение текста для кнопки")
    public boolean checkCurrentPersonalAreaButton(String buttonText) {
        return $(By.xpath("//button[contains(text(), '" + buttonText + "')]")).shouldBe(visible).exists();
    }
}