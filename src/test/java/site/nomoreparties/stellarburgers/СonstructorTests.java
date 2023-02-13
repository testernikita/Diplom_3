package site.nomoreparties.stellarburgers;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.pageObject.HomePage;

import static com.codeborne.selenide.Selenide.open;

public class СonstructorTests extends BaseTests{
    @Before
    public void setUp() {
        setUpYandexDriver();
    }

    @After
    public void tearDown() {
        closeYandexDriver();
    }

    @Test
    @DisplayName("Переход на вкладку \"Булки\"")
    @Description("Переход на вкладку конструктора \"Булки\" по нажатию на кнопку с именем \"Булки\"")
    public void selectBunsConstructorTab() {
        HomePage homePage = open(HomePage.URL, HomePage.class);
        homePage.clickBunsTab();

        Assert.assertTrue(homePage.checkCurrentConstructorTabSelected("Булки"));
    }

    @Test
    @DisplayName("Переход к разделу \"Соусы\"")
    @Description("Переход на вкладку конструктора \"Соусы\" по нажатию на кнопку с именем \"Соусы\"")
    public void selectSauceConstructorTab() {
        HomePage homePage = open(HomePage.URL, HomePage.class);
        homePage.clickSauceTab();

        Assert.assertTrue(homePage.checkCurrentConstructorTabSelected("Соусы"));
    }



    @Test
    @DisplayName("Переход к разделу \"Начинки\"")
    @Description("Переход на вкладку конструктора \"Начинки\" по нажатию на кнопку с именем \"Начинки\"")
    public void selectFillingConstructorTab() {
        HomePage homePage = open(HomePage.URL, HomePage.class);
        homePage.clickFillingTab();

        Assert.assertTrue(homePage.checkCurrentConstructorTabSelected("Начинки"));
    }
}