package pmtool;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Case_11 {

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
		
		// Register a new account
		driver.findElement(By.id("signup")).click();
				
		driver.findElement(By.id("fullName")).sendKeys("John Doe"+ formateddatetime);
		// Sign up form accepts upper case in email but requires lower case in login
		driver.findElement(By.id("email")).sendKeys("John_D" + formateddatetime + "@in.gr");
		driver.findElement(By.id("address")).sendKeys("Konstantinou Karamanli " + formateddatetime);
		driver.findElement(By.id("password")).sendKeys("123456");
		
		driver.findElement(By.cssSelector("button[name='action']")).click();
		
		try {
		    Thread.sleep(1000);
		} catch (InterruptedException ie) {
		    Thread.currentThread().interrupt();
		}
		
		String expected_text = "Successfull registration, login to start using PPMTool";
		String sunny_day_text = driver.findElement(By.xpath("//*[@class='row']/div/div/div/p")).getText();
		//System.out.println(sunny_day_text);
		
		if (sunny_day_text.equals(expected_text)) {
			System.out.println("Registration was successful");
		} else {
			System.out.println("Registration failed");
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
		
		//Create new project attempt #1 
		driver.findElement(By.xpath("//*[text()='Create']")).click();
		driver.findElement(By.id("description")).sendKeys("This is a project created by John Doe "+ formateddatetime);
		driver.findElement(By.cssSelector("button[name='action']")).click();
		try {
		    Thread.sleep(400);
		} catch (InterruptedException ie) {
		    Thread.currentThread().interrupt();
		}		
		
		String required_field_msg = driver.findElement(By.xpath("//*[@class='row']/form/div/div/p")).getText();
		
		expected_text = "This field is required";
		
		if (required_field_msg.equals(expected_text)) {
			System.out.println("Succesfull scenario when name is missing");
		} else {
			System.out.println("Scenario failed! Mandatory field message is not printed when name is missing");
		}		
		
		
		driver.navigate().back();
		
		//Create new project attempt #2 
		driver.findElement(By.xpath("//*[text()='Create']")).click();

		driver.findElement(By.id("name")).sendKeys("John Doe "+ formateddatetime);
		driver.findElement(By.cssSelector("button[name='action']")).click();
		
		try {
		    Thread.sleep(400);
		} catch (InterruptedException ie) {
		    Thread.currentThread().interrupt();
		}		
		
		required_field_msg = driver.findElement(By.xpath("//*[@class='row']/form/div[2]/div/p")).getText();
		
		expected_text = "This field is required";
		
		if (required_field_msg.equals(expected_text)) {
			System.out.println("Succesfull scenario when description is missing");
		} else {
			System.out.println("Scenario failed! Mandatory field message is not printed when description is missing");
		}		
		
		
		driver.navigate().back();
		
		//Create new project attempt #3 
		driver.findElement(By.xpath("//*[text()='Create']")).click();

		driver.findElement(By.cssSelector("button[name='action']")).click();
		
		try {
		    Thread.sleep(400);
		} catch (InterruptedException ie) {
		    Thread.currentThread().interrupt();
		}		
		
		expected_text = "This field is required";
		
		required_field_msg = driver.findElement(By.xpath("//*[@class='row']/form/div/div/p")).getText();	
		if (required_field_msg.equals(expected_text)) {
			System.out.println("Succesfull scenario when name is missing");
		} else {
			System.out.println("Scenario failed! Mandatory field message is not printed when name is missing");
		}	
		
		
		required_field_msg = driver.findElement(By.xpath("//*[@class='row']/form/div[2]/div/p")).getText();
		if (required_field_msg.equals(expected_text)) {
			System.out.println("Succesfull scenario when description is missing");
		} else {
			System.out.println("Scenario failed! Mandatory field message is not printed when description is missing");
		}
		
		// Teardown
		driver.close(); 
		driver.quit();
		
	}

}
