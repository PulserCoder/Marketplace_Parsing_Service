package yandex.parsing.market.parsing.services;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;
import yandex.parsing.market.parsing.models.Measurement;
import yandex.parsing.market.parsing.models.Product;
import yandex.parsing.market.parsing.models.TrackedUrl;
import yandex.parsing.market.parsing.repositories.MeasurementRepository;
import yandex.parsing.market.parsing.repositories.ProductRepository;
import yandex.parsing.market.parsing.repositories.TrackedUrlRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class SeleniumService {
    private final MeasurementRepository measurementRepository;
    private final ProductRepository productRepository;
    private final TrackedUrlRepository trackedUrlRepository;

    public SeleniumService(MeasurementRepository measurementRepository, ProductRepository productRepository, TrackedUrlRepository trackedUrlRepository) {
        this.measurementRepository = measurementRepository;
        this.productRepository = productRepository;
        this.trackedUrlRepository = trackedUrlRepository;
        schedulePeriodicTask();
    }

    private void schedulePeriodicTask() {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(this::processTrackedUrls, 0, 20, TimeUnit.SECONDS);
    }

    private void processTrackedUrls() {

        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.0.0 Safari/537.36");

        List<TrackedUrl> trackedUrls = trackedUrlRepository.findAll();

        for (TrackedUrl trackedUrl : trackedUrls) {
            WebDriver driver = null;
            try {
                driver = new ChromeDriver(options);
                driver.get(trackedUrl.getUrl());

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                WebElement priceElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h3[data-auto='snippet-price-current']")));
                System.out.println(priceElement.getText());
                String rawPrice = priceElement.getAttribute("innerText").split("Цена с картой Яндекс Пэй:")[1].trim();
                int price = Integer.parseInt(rawPrice.replaceAll("[^\\d]", ""));

                System.out.println("price = " + price);
                WebElement titleElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[@data-auto='productCardTitle']")));
                String title = titleElement.getText();



                Product product = trackedUrl.getProduct();
                if (product == null) {
                    product = new Product();
                    product.setTrackedUrl(trackedUrl);
                }
                product.setPrice(price);
                product.setTitle(title);
                productRepository.save(product);

                Measurement measurement = new Measurement();
                measurement.setTrackedUrl(trackedUrl);
                measurement.setPrice(price);
                measurement.setTimestamp(LocalDateTime.now());
                measurementRepository.save(measurement);

                System.out.println("Processed URL: " + trackedUrl.getUrl() + " | Price: " + price + " | Title: " + title);

            } catch (Exception e) {
                System.err.println("Error processing URL: " + trackedUrl.getUrl());
                e.printStackTrace();
            } finally {
                if (driver != null) {

                    driver.quit();
                }
            }
        }
    }
}
