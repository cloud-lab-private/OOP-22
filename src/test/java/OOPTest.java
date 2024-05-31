import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.JavascriptExecutor;

public class OOPTest {

    private static StringBuilder foodClass;

    @BeforeAll
    public static void setUp() {
        foodClass = TestingUtils.getFoodClass();
        
    }

    @Test
    public void testFoodProductIsSubclass() {
        System.out.println(foodClass);
        Assertions.assertTrue(foodClass.toString().contains("FoodProduct extends Product"));
    }

    @Test
    public void testFoodProductContainsCorrectState() {
        System.out.println(foodClass);
        Assertions.assertTrue(foodClass.toString().contains("#expirationDate"));
    }

    @Test
    public void testFoodProductContainsCorrectConstructor() {
        
        Assertions.assertTrue(foodClass.toString().contains("FoodProduct("));
        Assertions.assertTrue(foodClass.toString().contains("name"));
        Assertions.assertTrue(foodClass.toString().contains("description"));
        Assertions.assertTrue(foodClass.toString().contains("expirationDate"));
        Assertions.assertTrue(foodClass.toString().contains("this.#expirationDate"));
    }

    @Test
    public void testFoodProductContainsCorrectMutators() {

        Assertions.assertTrue(foodClass.toString().contains("getExpirationDate()"));
        Assertions.assertTrue(foodClass.toString().contains("setExpirationDate("));
        Assertions.assertTrue(foodClass.toString().contains("expirationDate"));
    }

}

class TestingUtils {
    public static StringBuilder getFoodClass() {
        String filePath = "./src/main/java/index.js";
        String startPattern = "class FoodProduct";
        String endPattern = "/* Note: You do not need to edit or view any code below this point. */";
        StringBuilder contentBuilder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));

            boolean foundStart = false;

            for (String line : lines) {
                if (line.startsWith(startPattern)) {
                    foundStart = true;
                    contentBuilder.append(line).append("\n");
                } else if (foundStart) {
                    if (line.endsWith(endPattern)) {
                        contentBuilder.append(line);
                        System.out.println(contentBuilder.toString());
                        foundStart = false;
                    } else {
                        contentBuilder.append(line).append("\n");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contentBuilder;
    }
}
