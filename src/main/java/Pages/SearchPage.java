package Pages;

import Framework.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class SearchPage extends BasePage {


    @FindBy(xpath = FIRST_REGISTRATION_BUTTON_XPATH)
    WebElement firstRegistrationButton;
    @FindBy(xpath = YEAR_DROP_DOWN_XPATH)
    WebElement yearDropDown;
    @FindBy (xpath = SORT_DROP_DOWN_XPATH)
    WebElement sortDropDown;

    public void firstRegistrationFilterSelection() {
        firstRegistrationButton.click();
        yearDropDown.click();
        Select dropDown = new Select(yearDropDown);
        dropDown.selectByVisibleText("2015");
    }

    public void sortCarsByDesc(){
        sortDropDown.click();
        Select dropDown = new Select(sortDropDown);
        dropDown.selectByVisibleText("Höchster Preis");
    }

    public void verifyDescPrice() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<WebElement> allPricesOnPage = driver.findElements(By.cssSelector(".totalPrice___3yfNv"));
        List<String> pricesValues = new ArrayList<String>(allPricesOnPage.size());
        List<String> pricesValuesWithoutCurrency = new ArrayList<String>(allPricesOnPage.size());
        List<Integer> intPrices = new ArrayList<Integer>(allPricesOnPage.size());

        int numberOfPrices = allPricesOnPage.size();

        for (int i = 0; i < numberOfPrices; i++) {
            String price = allPricesOnPage.get(i).getText();
            pricesValues.add(price);
            price = price.replaceAll("\\D+", "");
            pricesValuesWithoutCurrency.add(price);
        }

        for (String price : pricesValuesWithoutCurrency) {
            intPrices.add(Integer.parseInt(price));
        }

        //Main assert logic

        for (int i = 0; i < (numberOfPrices - 1); i++) {
            int z = i + 1;
            int firstCar = intPrices.get(i);
            int nextCar = intPrices.get(z);
            Boolean pricesIsDesc;

            if (firstCar >= nextCar){
                pricesIsDesc = true;
                Assert.assertTrue(pricesIsDesc);
            } else pricesIsDesc = false;
            Assert.assertTrue(pricesIsDesc);
        }
    }




    public void verifyCarsYear() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<WebElement> allCarsYear = driver.findElements(By.cssSelector(".specList___2i0rY>li:nth-child(1)"));
        List<String> yearValues = new ArrayList<String>(allCarsYear.size());
        List<String> yearValuesWithoutSymbols = new ArrayList<String>(allCarsYear.size());
        List<Integer> intYearValues = new ArrayList<Integer>(allCarsYear.size());

        int numberOfValues = allCarsYear.size();

        for (int i = 0; i < numberOfValues; i++) {
            String year = allCarsYear.get(i).getText();
            yearValues.add(year);
            year = year.replaceAll("\\D+","");
            yearValuesWithoutSymbols.add(year);
        }

        for (String year : yearValuesWithoutSymbols){
            intYearValues.add(Integer.parseInt(year));
        }

        //Main assert logic

        for (int i = 0; i < numberOfValues; i++) {
            Boolean checkCarYear;
            int year = intYearValues.get(i);
            if (year >= 2015){
                checkCarYear = true;
                Assert.assertTrue(checkCarYear);
            } else checkCarYear = false;
            Assert.assertTrue(checkCarYear);
        }
    }


    public static final String FIRST_REGISTRATION_BUTTON_XPATH = ".//*[@id='app']/div/main/div[4]/div/div[1]/div/div/div/div[3]";
    public static final String YEAR_DROP_DOWN_XPATH = ".//*[@id='app']/div/main/div[4]/div/div[1]/div/div/div/div[3]/div[2]/div/select";
    public static final String SORT_DROP_DOWN_XPATH = ".//*[@id='app']/div/main/div[4]/div/div[2]/div/div[1]/div[3]/div/div/select";
}
