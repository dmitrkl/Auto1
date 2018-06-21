package Tests;

import Framework.BaseTest;

import Pages.HomePage;
import Pages.SearchPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static Framework.BasePage.driver;
import static Framework.BasePage.initPage;

public class SearchFunctionalityTest extends BaseTest {



    private HomePage homePage;
    private SearchPage searchPage;



    @BeforeMethod(alwaysRun = true)
    public void openHomePage() {
        homePage = initPage(HomePage.class);
        searchPage = initPage(SearchPage.class);

    }

    @Test
    public void test () {
        homePage.navigateToSearchPage();
        searchPage.firstRegistrationFilterSelection();
        searchPage.sortCarsByDesc();
        searchPage.verifyDescPrice();
        searchPage.verifyCarsYear();


        }

    }




