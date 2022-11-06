import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class SviatlanaPiletskayaTest {

    //TC_1_1  - Тест кейс:
    //1. Открыть страницу https://openweathermap.org/
    //2. Набрать в строке поиска город Paris
    //3. Нажать пункт меню Search
    //4. Из выпадающего списка выбрать Paris, FR
    //5. Подтвердить, что заголовок изменился на "Paris, FR"

    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/svetikpileckaa/Applications/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        driver.get(url);
        Thread.sleep(5000);

        WebElement searchCityField = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']")
        );
        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//button[@type='submit']")
        );

        searchButton.click();

        Thread.sleep(1000);

        WebElement parisFRChoiceInDropdownMenu = driver.findElement(
                By.xpath("//ul[@class='search-dropdown-menu']/li/span[text() = 'Paris, FR ']")
        );
        parisFRChoiceInDropdownMenu.click();

        WebElement h2CityCountryHeader = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );

        Thread.sleep(2000);
        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);

//        Thread.sleep(5000);

        driver.quit();
//        driver.close();
    }


    /*TC_11_01
1.  Открыть базовую ссылку
2.  Нажать на пункт меню Guide
3.  Подтвердить, что вы перешли на страницу со ссылкой https://openweathermap.org/guide
и что title этой страницы OpenWeatherMap API guide - OpenWeatherMap*/

@Ignore
    @Test
    public void test() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/svetikpileckaa/Applications/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";

        String expectedResult = "OpenWeatherMap API guide - OpenWeatherMap";

        driver.get(url);
        Thread.sleep(5000);

        WebElement searchButton = driver.findElement(
                By.xpath("//*[@id=\"desktop-menu\"]/ul/li[1]/a")
        );
        searchButton.click();


        Thread.sleep(1000);

        WebElement parisFRChoiceInDropdownMenu = driver.findElement(
                By.xpath("//ul[@class='search-dropdown-menu']/li/span[text() = 'Paris, FR ']")
        );
        parisFRChoiceInDropdownMenu.click();

        WebElement h2CityCountryHeader = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );

        Thread.sleep(2000);
        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);

//        Thread.sleep(5000);

        driver.quit();
//        driver.close();
    }

/*    TC_11_02
1.  Открыть базовую ссылку
2.  Нажать на единицы измерения Imperial: °F, mph
3.  Подтвердить, что температура для города показана в Фарингейтах*/

    @Test
    public void testOnChangeImperial() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/svetikpileckaa/Applications/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        char expectedResult = 'F';

        driver.get(url);
        Thread.sleep(5000);

        WebElement changeImperialByF = driver.findElement(
                By.xpath("//*[@id=\"weather-widget\"]/div[1]/div/div/div[1]/div[2]/div[3]")
        );

        changeImperialByF.click();
        Thread.sleep(5000);

        WebElement getImperialName = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//div[@class = 'section-content']//span[@class = 'heading']")
        );

        String newImperial = getImperialName.getText();

        char actualResult = newImperial.charAt(newImperial.length() - 1);

        Assert.assertEquals(actualResult, expectedResult);
        driver.quit();

    }



    /*TC_11_03
1.  Открыть базовую ссылку
2. Подтвердить, что внизу страницы есть панель с текстом “We use cookies which are essential for the site to work. We also use non-essential cookies to help us improve our services. Any data collected is anonymised. You can allow all cookies or manage them individually.”
3. Подтвердить, что на панели внизу страницы есть 2 кнопки “Allow all” и “Manage cookies”*/

    public void checkingForCookies() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/svetikpileckaa/Applications/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();
        String actualResult = "We use cookies which are essential for the site to work. "
                + "We also use non-essential cookies to help us improve our services. Any data collected is anonymised. "
                + " You can allow all cookies or manage them individually.";
        String url = "https://openweathermap.org/";
        driver.get(url);
        Thread.sleep(5000);



    }










//    @Test
//    public void test_name() {
//        System.setProperty("webdriver.chrome.driver", "/Users/svetikpileckaa/Applications/ChromeDriver/chromedriver");
//        WebDriver driver = new ChromeDriver();
//
//
//
//        driver.quit();
//        driver.close();
//    }
}




/*import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class SviatlanaPiletskayaTest extends BaseTest {

    @Test
    public void testPageSales() {
        getDriver().get("https://klinik.by/");
        WebElement bottomSales = getDriver().findElement(By.xpath("//*[@id=\"menu-item-2570\"]/a[text() = 'Акции']"));

        bottomSales.click();

        WebElement pageSales = getDriver().findElement(By.xpath("//*[@id=\"page\"]//h1[text() ='Специальные предложения']"));

        Assert.assertEquals(pageSales.getText(), "Специальные предложения");
    }


}*/