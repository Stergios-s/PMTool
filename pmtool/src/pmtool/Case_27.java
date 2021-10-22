package pmtool;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Case_27 {

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
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.id("company")).sendKeys("John_D" + formateddatetime);
		driver.findElement(By.id("address")).sendKeys("John_Doe " + formateddatetime);
		
		driver.findElement(By.cssSelector("button[name='action']")).click();
		
		try {
		    Thread.sleep(1500);
		} catch (InterruptedException ie) {
		    Thread.currentThread().interrupt();
		}
		
		String expected_text = "Successfull registration, login to start using PPMTool";
		String sunny_day_text = driver.findElement(By.xpath("//*[@class='row']/div/div/div/p")).getText();
		System.out.println(sunny_day_text);
		
		if (sunny_day_text.equals(expected_text)) {
			System.out.println("Succesfull registration");
		} else {
			System.out.println("Registration failed due to wrong message after user registration");
		}
		
		// Login to account
		driver.findElement(By.id("login")).click();				

		// Form requires email to be lower case
		driver.findElement(By.id("email")).sendKeys("john_d" + formateddatetime + "@in.gr");
		driver.findElement(By.id("password")).sendKeys("123456");
			
		try {
		    Thread.sleep(200);
		} catch (InterruptedException ie) {
		    Thread.currentThread().interrupt();
		}
		driver.findElement(By.cssSelector("button[name='action']")).click();
				
		try {
		    Thread.sleep(500);
		} catch (InterruptedException ie) {
		    Thread.currentThread().interrupt();
		}
		// Create new project is a common element found in both new and existing accounts
		driver.findElement(By.xpath("//*[text()='Create']"));
		
		driver.findElement(By.id("settings")).click();
		
		//Remove company info
		try {
		    Thread.sleep(3000);
		} catch (InterruptedException ie) {
		    Thread.currentThread().interrupt();
		}
		driver.findElement(By.id("company")).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
		try {
		    Thread.sleep(3000);
		} catch (InterruptedException ie) {
		    Thread.currentThread().interrupt();
		}
		driver.findElement(By.id("update_info")).click();
		
		try {
		    Thread.sleep(300);
		} catch (InterruptedException ie) {
		    Thread.currentThread().interrupt();
		}
		
		
		System.out.println(driver.findElement(By.xpath("//div[@id='root']/following-sibling::div")).getText());
		
		
		try {
		    Thread.sleep(3000);
		} catch (InterruptedException ie) {
		    Thread.currentThread().interrupt();
		}
		
		driver.findElement(By.id("settings")).click();
		
		
		
		//Remove mandatory info fullName
		try {
		    Thread.sleep(2000);
		} catch (InterruptedException ie) {
		    Thread.currentThread().interrupt();
		}
		driver.findElement(By.id("fullName")).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
		try {
		    Thread.sleep(3000);
		} catch (InterruptedException ie) {
		    Thread.currentThread().interrupt();
		}
		driver.findElement(By.id("update_info")).click();
		
		try {
		    Thread.sleep(300);
		} catch (InterruptedException ie) {
		    Thread.currentThread().interrupt();
		}
		
		String required_field_msg = driver.findElement(By.xpath("//*[@class='row'][1]/div/p")).getText();
		
		expected_text = "This field is required";
		
		if (required_field_msg.equals(expected_text)) {
			System.out.println("Succesfull scenario");
		} else {
			System.out.println("Scenario failed! Mandatory field message is not printed");
		}
		
		
		//Remove mandatory info email
		try {
		    Thread.sleep(2000);
		} catch (InterruptedException ie) {
		    Thread.currentThread().interrupt();
		}
		driver.findElement(By.id("email")).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
		try {
		    Thread.sleep(3000);
		} catch (InterruptedException ie) {
		    Thread.currentThread().interrupt();
		}
		driver.findElement(By.id("update_info")).click();
				
		try {
		    Thread.sleep(300);
		} catch (InterruptedException ie) {
		    Thread.currentThread().interrupt();
		}
				
		required_field_msg = driver.findElement(By.xpath("//*[@class='row'][2]/div/p")).getText();
				
		expected_text = "This field is required";
		
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
