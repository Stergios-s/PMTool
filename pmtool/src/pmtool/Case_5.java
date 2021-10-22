package pmtool;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Case_5 {

	public static void main(String[] args) {
		// Step 0: Invoke chromedriver.exe file from your drive needed from Chrome
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\stergios\\Downloads\\Java_and_Selenium_course_files\\browser_drivers\\chromedriver.exe");
						
		// Step 1: Create driver object for Chrome browser
		WebDriver driver = new ChromeDriver();
					
		// Hit URL in browser
		driver.get("https://node-fs-app.herokuapp.com/");
		// Add a small delay before giving inputs
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		    System.out.println(e);
		}
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");  
		LocalDateTime current_date_time = LocalDateTime.now();  
		String formateddatetime = dtf.format(current_date_time);
		
		driver.findElement(By.id("signup")).click();
				
		driver.findElement(By.id("fullName")).sendKeys("John Doe"+ formateddatetime);
		driver.findElement(By.id("email")).sendKeys("John_D" + formateddatetime + "@in.gr");
		
		driver.findElement(By.cssSelector("button[name='action']")).click();
		
		try {
		    Thread.sleep(200);
		} catch (InterruptedException ie) {
		    Thread.currentThread().interrupt();
		}
		
		String required_field_msg = driver.findElement(By.xpath("//*[@class='row'][3]/div/p")).getText();
		
		String expected_text = "This field is required";
		
		if (required_field_msg.equals(expected_text)) {
			System.out.println("Succesfull scenario");
		} else {
			System.out.println("Scenario failed! Mandatory field message is not printed");
		}
		
		// Teardown
		driver.close(); 
		driver.quit();
	}

}
