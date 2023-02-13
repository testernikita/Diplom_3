package site.nomoreparties.stellarburgers.pageObject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class HomePage {
    //url домашней (главной) страницы приложения
    public static final String URL = "https://stellarburgers.nomoreparties.site/";

    @FindBy(how = How.XPATH, using = ".//p[text()='Личный Кабинет']")
    private SelenideElement buttonPersonalArea;

    @FindBy(how = How.XPATH, using = ".//p[text()='Конструктор']")
    private SelenideElement buttonConstruction;

    @FindBy(how = How.XPATH, using = ".//div[@class='AppHeader_header__logo__2D0X2']")
    private SelenideElement buttonLogo;

    @FindBy(how = How.XPATH, using = ".//button[text()='Войти в аккаунт']")
    private SelenideElement buttonLogin;

    // Модуль "Соберите бургер"
    @FindBy(how = How.XPATH, using = ".//span[text()='Булки']")
    private SelenideElement spanBunsTab;

    @FindBy(how = How.XPATH, using = ".//span[text()='Соусы']")
    private SelenideElement spanSauceTab;

    @FindBy(how = How.XPATH, using = ".//span[text()='Начинки']")
    private SelenideElement spanFillingTab;


    // Метод для нажатия на вкладку "Булки"
    @Step("Нажать на вкладку \"Булки\"")
    public void clickBunsTab() {
        spanBunsTab.shouldBe(visible).doubleClick();
    }

    // Метод для нажатия на вкладку "Соусы"
    @Step("Нажать на вкладку \"Соусы\"")
    public void clickSauceTab() {
        spanSauceTab.shouldBe(visible).click();
    }

    // Метод для нажатия на вкладку "Начинки"
    @Step("Нажать на кнопку \"Начинки\"")
    public void clickFillingTab() {
        spanFillingTab.shouldBe(visible).click();
    }

    // Метод для проверки активна ли выбранная вкладка
    public boolean checkCurrentConstructorTabSelected(String tabName) {
        return $(By.xpath("//div[contains(@class, 'current') and //span[contains(text(), '" + tabName + "')]]")).exists();
    }

    // Метод для нажатия на кнопку "Личный кабинет"
    @Step("Нажать на кнопку \"Личный кабинет\"")
    public HomePage clickPersonalAccountButtonHeader() {
        buttonPersonalArea.click();
        return this;
    }

    // Метод для нажатия на кропку "Войти в аккаунт" в шапке
    @Step("Нажать на кнопку \"Войти в аккаунт\"")
    public void clickLoginButton() {
        buttonLogin.click();
    }

    // Метод для проверки видимости кнопки на странице
    @Step("Проверить текст на кнопке")
    public boolean checkButtonVisible(String buttonText) {
        return $(By.xpath("//button[contains(text(), '" + buttonText + "')]")).shouldBe(visible).exists();
    }

    // Метод для проверки значения отображаемого заголовка на странице
    @Step("Проверить заголовок на странице")
    public boolean checkHeaderVisible(String headerText) {
        return $(By.xpath("//h1[contains(text(), '" + headerText + "')]")).shouldBe(visible).exists();
    }
}