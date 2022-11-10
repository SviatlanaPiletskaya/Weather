import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.ArrayList;

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

        driver.quit();
    }

    /*TC_11_01
1.  Открыть базовую ссылку
2.  Нажать на пункт меню Guide
3.  Подтвердить, что вы перешли на страницу со ссылкой https://openweathermap.org/guide
и что title этой страницы OpenWeatherMap API guide - OpenWeatherMap*/
    @Test
    public void testGuideUrlAndHeader() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/svetikpileckaa/Applications/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResultTitle = "OpenWeatherMap API guide - OpenWeatherMap";
        String expectedResultUrl = "https://openweathermap.org/guide";

        driver.get(url);
        Thread.sleep(5000);

        WebElement guideElementMenu = driver.findElement(
                By.xpath("//a[@href='/guide']")
        );
        guideElementMenu.click();

        String actualResultUrl = driver.getCurrentUrl();
        String actualResultTitle = driver.getTitle();

        Assert.assertEquals(actualResultUrl, expectedResultUrl);
        Assert.assertEquals(actualResultTitle, expectedResultTitle);

        driver.quit();
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

        Thread.sleep(2000);

        WebElement menuImperialByF = driver.findElement(
                By.xpath("//div[@class='option'][contains(text(), 'F')]")
        );
        menuImperialByF.click();

        Thread.sleep(2000);

        WebElement getImperialName = driver.findElement(
                By.xpath("//span[@class = 'heading']")
        );

        String newImperial = getImperialName.getText();

        Assert.assertEquals(newImperial.charAt(newImperial.length() - 1), expectedResult);
        driver.quit();
    }

    /*TC_11_03
1.  Открыть базовую ссылку
2. Подтвердить, что внизу страницы есть панель с текстом “We use cookies which are essential for the site to work. We also use non-essential cookies to help us improve our services. Any data collected is anonymised. You can allow all cookies or manage them individually.”
3. Подтвердить, что на панели внизу страницы есть 2 кнопки “Allow all” и “Manage cookies”*/
    @Test
    public void testCheckingForCookies() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/svetikpileckaa/Applications/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();
        String expectedResult = "We use cookies which are essential for the site to work. "
                + "We also use non-essential cookies to help us improve our services. Any data collected is anonymised. "
                + "You can allow all cookies or manage them individually.";
        String button1Text = "Allow all";
        String button2Text = "Manage cookies";

        String url = "https://openweathermap.org/";
        driver.get(url);

        Thread.sleep(2000);

        WebElement elementWithCookiesOnPage = driver.findElement(
                By.xpath("//div[@class = 'stick-footer-panel__container']/p")
        );
        WebElement buttonAllowAll = driver.findElement(
                By.xpath("//button[text()= 'Allow all']")
        );
        WebElement buttonManageCookies = driver.findElement(
                By.xpath("//a[@href='/cookies-settings']")
        );

        String actualResult = elementWithCookiesOnPage.getText();

        Assert.assertEquals(actualResult, expectedResult);
//        Assert.assertEquals(driver.findElements(By.xpath("//div[@class='stick-footer-panel__btn-container']/*")).size(), 2);

        Assert.assertTrue(driver.findElement(
                By.xpath("//div[@class = 'stick-footer-panel__container']/*[text() = '" + button1Text + "]")
        ).isDisplayed());

        Assert.assertTrue(driver.findElement(
                By.xpath("//div[@class = 'stick-footer-panel__container']/*[text() = '" + button2Text + "]")
        ).isDisplayed());

        Assert.assertEquals(buttonAllowAll.getText(), button1Text);
        Assert.assertEquals(buttonManageCookies.getText(), button2Text);

        driver.quit();
    }

    /*TC_11_04
    1.  Открыть базовую ссылку
    2.  Подтвердить, что в меню Support есть 3 подменю с названиями “FAQ”, “How to start” и “Ask a question”*/
    @Test
    public void testSupportMenu() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/svetikpileckaa/Applications/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();
        String expectedResultFAQ = "FAQ";
        String expectedResultHowToStart = "How to start";
        String expectedResultAskAQuestion = "Ask a question";

        String url = "https://openweathermap.org/";
        driver.get(url);

        Thread.sleep(2000);

        WebElement elementSupport = driver.findElement(
                By.xpath("//div[@id='support-dropdown']")
        );
        elementSupport.click();

        WebElement subMenuFaq = driver.findElement(
                By.xpath("//a[@href='/faq']")
        );
        WebElement subMenuHowToStart = driver.findElement(
                By.xpath("//a[@href='/appid']")
        );
        WebElement subMenuAskAQuestion = driver.findElement(
                By.xpath("//a[@href='https://home.openweathermap.org/questions']")
        );

        Assert.assertEquals(driver.findElements(By.xpath("//ul[@class='dropdown-menu dropdown-visible']/li")).size(), 3);
        Assert.assertEquals(subMenuFaq.getText(), expectedResultFAQ);
        Assert.assertEquals(subMenuHowToStart.getText(), expectedResultHowToStart);
        Assert.assertEquals(subMenuAskAQuestion.getText(), expectedResultAskAQuestion);
        driver.quit();
    }

    /*TC_11_05
1. Открыть базовую ссылку
2. Нажать пункт меню Support → Ask a question
3. Заполнить поля Email, Subject, Message
4. Не подтвердив CAPTCHA, нажать кнопку Submit
5. Подтвердить, что пользователю будет показана ошибка “reCAPTCHA verification failed, please try again.”*/
    @Test
    public void testCaptchaErrorMessage() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/svetikpileckaa/Applications/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();
        String email = "test@test.com";
        String message = "Test";
        String expectedResultTextCAPTHA = "reCAPTCHA verification failed, please try again.";

        String url = "https://openweathermap.org/";
        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(2000);

        WebElement elementSupport = driver.findElement(By.xpath("//div[@id='support-dropdown']"));
        elementSupport.click();

        WebElement subMenuAskAQuestion = driver.findElement(By.xpath("//a[@href='https://home.openweathermap.org/questions']"));
        subMenuAskAQuestion.click();

        ArrayList<String> page = new ArrayList<>(driver.getWindowHandles()); //переходит на новую страницу
        driver.switchTo().window(page.get(1));

        WebElement inputEmail = driver.findElement(By.xpath("//input[@class = 'form-control string email required']"));
        inputEmail.click();
        inputEmail.sendKeys(email);

        WebElement selectField = driver.findElement(By.xpath("//select[@class='form-control select required']"));
        selectField.click();

        WebElement selectFieldChoice = driver.findElement(By.xpath("//option[1]"));
        selectFieldChoice.click();

        WebElement inputMessage = driver.findElement(By.xpath("//textarea[@class='form-control text required']"));
        inputMessage.sendKeys(message);

        WebElement submitButton = driver.findElement(By.xpath("//input[@type='submit'][@name='commit']"));
        submitButton.click();
        Thread.sleep(2000);

        WebElement reCapthaText = driver.findElement(By.xpath("//div[@class='help-block']"));

        Assert.assertEquals(reCapthaText.getText(), expectedResultTextCAPTHA);
        driver.quit();
    }

//  /*  *//*TC_11_06
//1.  Открыть базовую ссылку
//2.  Нажать пункт меню Support → Ask a question
//3.  Оставить значение по умолчанию в checkbox Are you an OpenWeather user?
//4. Оставить пустым поле Email
//5. Заполнить поля  Subject, Message
//6. Подтвердить CAPTCHA
//7. Нажать кнопку Submit
//8. Подтвердить, что в поле Email пользователю будет показана ошибка “can't be blank”*//*
//    @Ignore
//    @Test
//   */ public void testCaptchaWithEmptyEmail() throws InterruptedException {
//        System.setProperty("webdriver.chrome.driver", "/Users/svetikpileckaa/Applications/ChromeDriver/chromedriver");
//        WebDriver driver = new ChromeDriver();
//
//        String message = "Test";
//        String expectedResultTextCAPTHA = "can't be blank";
//
//        String url = "https://openweathermap.org/";
//        driver.get(url);
//        driver.manage().window().maximize();
//        Thread.sleep(2000);
//
//        WebElement elementSupport = driver.findElement(By.xpath("//div[@id='support-dropdown']"));
//        elementSupport.click();
//
//        WebElement subMenuAskAQuestion = driver.findElement(By.xpath("//a[@href='https://home.openweathermap.org/questions']"));
//        subMenuAskAQuestion.click();
//
//        String mainWind = driver.getWindowHandle();
//        for (String windowsHandle : driver.getWindowHandles()) {
//            if (!mainWind.contentEquals(windowsHandle)) {
//                driver.switchTo().window(windowsHandle);
//                break;
//            }
//        }
//        Thread.sleep(4000);
//
//        WebElement selectField = driver.findElement(By.xpath("//select[@class='form-control select required']"));
//        selectField.click();
//
//        WebElement selectFieldChoice = driver.findElement(By.xpath("//option[1]"));
//        selectFieldChoice.click();
//
//        WebElement inputMessage = driver.findElement(By.xpath("//textarea[@class='form-control text required']"));
//        inputMessage.sendKeys(message);
//        Thread.sleep(4000);
//
//        WebElement reCAPTCHA = driver.findElement(
//                By.xpath("//div[@class='recaptcha-checkbox-border']")
//        );
//        reCAPTCHA.click();
//
//        Thread.sleep(4000);
//
//        WebElement submitButton = driver.findElement(By.xpath("//input[@type='submit'][@name='commit']"));
//        submitButton.click();
//        Thread.sleep(6000);
//
//        WebElement reCapthaText = driver.findElement(By.xpath("//span[@class='help-block'] "));
//
//        Assert.assertEquals(reCapthaText.getText(), expectedResultTextCAPTHA);
//        driver.quit();
//    }

    /*TC_11_07
1.  Открыть базовую ссылку
2.  Нажать на единицы измерения Imperial: °F, mph
3.  Нажать на единицы измерения Metric: °C, m/s
4.  Подтвердить, что в результате этих действий, единицы измерения температуры изменились с F на С*/
    @Test
    public void testChangingTemp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/svetikpileckaa/Applications/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String expectedResult = "C";
        String url = "https://openweathermap.org/";
        driver.get(url);

        Thread.sleep(2000);

        WebElement changeTempToF = driver.findElement(
                By.xpath("//div[@class='option'][contains(text(), 'F')]")
        );
        changeTempToF.click();

        Thread.sleep(2000);

        WebElement changeTempToC = driver.findElement(By.xpath("//div[@class='option'][contains(text(), 'C')]"));
        changeTempToC.click();

        Thread.sleep(2000);

        String tempC = driver.findElement(
                By.xpath("//span[@class='heading'][contains(text(), 'C')]")
        ).getText();

        String actualResult = tempC.substring(tempC.length() - 1);

        Assert.assertEquals(actualResult, expectedResult);
        driver.quit();
    }


    /*TC_11_08
1.  Открыть базовую ссылку
2.  Нажать на лого компании
3.  Дождаться, когда произойдет перезагрузка сайта, и подтвердить, что текущая ссылка не изменилась*/
    @Test
    public void reloadingSiteWithLogo() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/svetikpileckaa/Applications/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        driver.get(url);
        Thread.sleep(2000);

        WebElement imageBanner = driver.findElement(By.xpath("//img[@src='/themes/openweathermap/assets/img/logo_white_cropped.png']"));
        imageBanner.click();
        Thread.sleep(2000);

        Assert.assertEquals(driver.getCurrentUrl(), url);
        driver.quit();
    }

    /*TC_11_09
1.  Открыть базовую ссылку
2.  В строке поиска в навигационной панели набрать “Rome”
3.  Нажать клавишу Enter
4.  Подтвердить, что вы перешли на страницу в ссылке которой содержатся слова “find” и “Rome”
5. Подтвердить, что в строке поиска на новой странице вписано слово “Rome”*/
    @Test
    public void testsearchForCityOfRome() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/svetikpileckaa/Applications/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String cityName = "Rome";
        String searchValue = "find";
        driver.get(url);

        Thread.sleep(2000);

        WebElement searchBar = driver.findElement(
                By.xpath("//div[@id='desktop-menu']//input[@placeholder='Weather in your city']")
        );
        searchBar.click();
        searchBar.sendKeys(cityName);
        searchBar.sendKeys(Keys.ENTER);

        Thread.sleep(2000);

        boolean actualResult1 = driver.getCurrentUrl().contains(searchValue) && driver.getCurrentUrl().contains(cityName);

        Assert.assertTrue(actualResult1);

        String actualResult2 = driver.findElement(
                By.xpath("//input[@id='search_str']")
        ).getAttribute("value");

        Assert.assertEquals(actualResult2, "Rome");
        driver.quit();
    }

    /*TC_11_10
1.  Открыть базовую ссылку
2.  Нажать на пункт меню API
3.  Подтвердить, что на открывшейся странице пользователь видит 30 оранжевых кнопок*/
    @Test
    public void testAPIList() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/svetikpileckaa/Applications/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        int expectedResult = 30;

        driver.get(url);

        Thread.sleep(2000);

        WebElement sectionApi = driver.findElement(
                By.xpath("//div[@id='desktop-menu']//a[@href='/api']")
        );
        sectionApi.click();

        Thread.sleep(2000);

        int actualResult = driver.findElements(
                By.xpath("//a[contains(@class, 'orange')]")
        ).size();

        Assert.assertEquals(actualResult, expectedResult);
        driver.quit();
    }
}