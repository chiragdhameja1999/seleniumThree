import io.github.bonigarcia.wdm.WebDriverManager;
import org.asynchttpclient.util.Assertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class seleniumThreeRunner {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://www.amazon.in/");
        webDriver.manage().window().maximize();

        WebElement webElement = webDriver.findElement(By.cssSelector("[name='field-keywords']"));

        String s_search = "Mobiles";
        webElement.sendKeys(s_search);
        webElement.sendKeys(Keys.ENTER);

        List<String> actuallist = webDriver.findElements(By.cssSelector(".s-card-container")).stream()
                .map(webElement1 -> webElement1.getText().toLowerCase(Locale.ROOT))
                .collect(Collectors.toList());

        System.out.println("No of list items : "+actuallist.size());
        System.out.println("Actual List : "+actuallist);

        //String s_assert="selenium";
        String s_assert_f="redmi";

        //Now Assertions
        //Our search phrase should capture
        Assert.assertTrue(actuallist.stream().anyMatch(s -> s.contains(s_assert_f)));

        webDriver.quit();
    }
}