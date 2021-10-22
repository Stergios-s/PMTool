package pmtool;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class Case_25 {

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
		    Thread.sleep(1500);
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
		    Thread.sleep(1000);
		} catch (InterruptedException ie) {
		    Thread.currentThread().interrupt();
		}	
		
		//Create and view first project 
		driver.findElement(By.xpath("//*[text()='Create']")).click();
		driver.findElement(By.id("name")).sendKeys("Project 1");
		driver.findElement(By.id("description")).sendKeys("This is project number 1");
		driver.findElement(By.cssSelector("button[name='action']")).click();
		try {
		    Thread.sleep(1000);
		} catch (InterruptedException ie) {
		    Thread.currentThread().interrupt();
		}		

		expected_text = "Project 1";
		String extracted_text = driver.findElement(By.xpath("//*[@class='card-title']")).getText();
		
		if (extracted_text.equals(expected_text)) {
			System.out.println("Creation of project 1 was successful");
		} else {
			System.out.println("Project 1 creation failed");
		}
		
		try {
		    Thread.sleep(400);
		} catch (InterruptedException ie) {
		    Thread.currentThread().interrupt();
		}	
		
		//Create and view second project 
		driver.findElement(By.xpath("//*[text()='Create']")).click();
		driver.findElement(By.id("name")).sendKeys("Project 2");
		driver.findElement(By.id("description")).sendKeys("This is project number 2");
		driver.findElement(By.cssSelector("button[name='action']")).click();
		try {
		    Thread.sleep(1000);
		} catch (InterruptedException ie) {
		    Thread.currentThread().interrupt();
		}		

		expected_text = "Project 2";
		extracted_text = driver.findElement(By.xpath("//*[text()='" + expected_text + "']")).getText();
		
		if (extracted_text.equals(expected_text)) {
			System.out.println("Creation of project 2 was successful");
		} else {
			System.out.println("Project 2 creation failed");
		}
		
		try {
		    Thread.sleep(400);
		} catch (InterruptedException ie) {
		    Thread.currentThread().interrupt();
		}
		
		
		//Local variable initialization
		String in_prog_task_summ = "";
		String in_prog_task_desc = "";
		String upload_file_name = "";
		String label_tags = "";
				
		
		//Add Task 1 to Project 1
		driver.findElement(By.id("btn_add_task")).click();
		try {
		    Thread.sleep(400);
		} catch (InterruptedException ie) {
		    Thread.currentThread().interrupt();
		}
		
		driver.findElement(By.id("summary")).sendKeys("Task #1");
		driver.findElement(By.id("description")).sendKeys("This is task #1");
		//Set status "In progress"
		driver.findElement(By.className("select-wrapper")).click();
		driver.findElement(By.xpath("(//*[@class='select-wrapper']/ul/li)[2]")).click();
		
		//In labels set "Backend", "CI", "Testing"
		driver.findElement(By.id("multiselectContainerReact")).click();
		driver.findElement(By.xpath(("((//*[@id='multiselectContainerReact']/div)[2]/ul/li)[1]"))).click();
		driver.findElement(By.xpath(("((//*[@id='multiselectContainerReact']/div)[2]/ul/li)[4]"))).click();
		driver.findElement(By.xpath(("((//*[@id='multiselectContainerReact']/div)[2]/ul/li)[6]"))).click();
		
		//Upload file
		WebElement uploadElement = driver.findElement(By.id("attachments"));

        //Enter the file path
        uploadElement.sendKeys("C:\\Users\\stergios\\Desktop\\Workable_Assigment\\dummy_text.txt");
        
		
        driver.findElement(By.cssSelector("button[name='action']")).click();
		
		try {
		    Thread.sleep(1000);
		} catch (InterruptedException ie) {
		    Thread.currentThread().interrupt();
		}
		
		
		//View Task 1 of Project 1
		in_prog_task_summ = driver.findElement(By.xpath("//*[@id='in_progress_items']/div/div/span")).getText();
		in_prog_task_desc = driver.findElement(By.xpath("//*[@id='in_progress_items']/div/div/p")).getText();
		label_tags = driver.findElement(By.xpath("(//*[@id='in_progress_items']/div/div/div)[1]")).getText();
		label_tags = label_tags + " " + driver.findElement(By.xpath("(//*[@id='in_progress_items']/div/div/div)[2]")).getText();
		label_tags = label_tags + " " + driver.findElement(By.xpath("(//*[@id='in_progress_items']/div/div/div)[3]")).getText();
		upload_file_name = driver.findElement(By.xpath("(//*[@id='in_progress_items']/div/div/p)[2]")).getText();
		
		//Check if summary is correct in view mode
		if ("Task #1".equals(in_prog_task_summ)) {
			System.out.println("Summary of task 1 is correct");
		} else {
			System.out.println("Task 1 creation failed due to wrong summary");
		}

		//Check if description is correct in view mode
		if ("This is task #1".equals(in_prog_task_desc)) {
			System.out.println("Description of task 1 is correct");
		} else {
			System.out.println("Task 1 creation failed due to wrong description");
		}
		
		//Check if labes are correct in view mode
		if ("BACKEND CI TESTING".equals(label_tags)) {
			System.out.println("Labels of task 1 are correct");
		} else {
			System.out.println("Task 1 creation failed due to wrong labels");
		}
		
		//Check if uploaded file name is correct in view mode
		if ("DUMMY_TEXT.TXT".equals(upload_file_name)) {
			System.out.println("Uploaded file name of task 1 is correct");
		} else {
			System.out.println("Task 1 creation failed due to wrong uploaded file name");
		}
		
		
		driver.findElement(By.id("dashboard")).click();

		//Add Task 2 to Project 1
		driver.findElement(By.id("btn_add_task")).click();
		try {
		    Thread.sleep(400);
		} catch (InterruptedException ie) {
		    Thread.currentThread().interrupt();
		}
				
		driver.findElement(By.id("summary")).sendKeys("Task #2");
		driver.findElement(By.id("description")).sendKeys("This is task #2");

		//Set status "In progress"
		driver.findElement(By.className("select-wrapper")).click();
		try {
		    Thread.sleep(1000);
		} catch (InterruptedException ie) {
		    Thread.currentThread().interrupt();
		}
		driver.findElement(By.xpath("(//*[@class='select-wrapper']/ul/li)[2]")).click();
				
		//In labels set "Backend", "CI", "Testing"
		driver.findElement(By.id("multiselectContainerReact")).click();
		driver.findElement(By.xpath(("((//*[@id='multiselectContainerReact']/div)[2]/ul/li)[1]"))).click();
		driver.findElement(By.xpath(("((//*[@id='multiselectContainerReact']/div)[2]/ul/li)[4]"))).click();
		driver.findElement(By.xpath(("((//*[@id='multiselectContainerReact']/div)[2]/ul/li)[6]"))).click();
				
		//Upload file
		WebElement uploadElement_2 = driver.findElement(By.id("attachments"));

		//Enter the file path
		uploadElement_2.sendKeys("C:\\Users\\stergios\\Desktop\\Workable_Assigment\\dummy_text.txt");
		        
				
		driver.findElement(By.cssSelector("button[name='action']")).click();
				
		try {
		    Thread.sleep(1500);
		} catch (InterruptedException ie) {
		    Thread.currentThread().interrupt();
		}
				
				
		//View Task 2 of Project 1
		in_prog_task_summ = driver.findElement(By.xpath("(//*[@id='in_progress_items']/div/div/span)[2]")).getText();
		in_prog_task_desc = driver.findElement(By.xpath("(//*[@id='in_progress_items']/div/div/p)[3]")).getText();
		label_tags = driver.findElement(By.xpath("(//*[@id='in_progress_items']/div/div)[3]/div[1]")).getText();
		label_tags = label_tags + " " + driver.findElement(By.xpath("(//*[@id='in_progress_items']/div/div)[3]/div[2]")).getText();
		label_tags = label_tags + " " + driver.findElement(By.xpath("(//*[@id='in_progress_items']/div/div)[3]/div[3]")).getText();
		upload_file_name = driver.findElement(By.xpath("(//*[@id='in_progress_items']/div)[2]/div/p[2]")).getText();
				
		//Check if summary is correct in view mode
		if ("Task #2".equals(in_prog_task_summ)) {
			System.out.println("Summary of task 2 is correct");
		} else {
			System.out.println("Task 2 creation failed due to wrong summary");
		}

		//Check if description is correct in view mode
		if ("This is task #2".equals(in_prog_task_desc)) {
			System.out.println("Description of task 2 is correct");
		} else {
			System.out.println("Task 2 creation failed due to wrong description");
		}
				
		//Check if labes are correct in view mode
		if ("BACKEND CI TESTING".equals(label_tags)) {
			System.out.println("Labels of task 2 are correct");
		} else {
			System.out.println("Task 2 creation failed due to wrong labels");
		}
				
		//Check if uploaded file name is correct in view mode
		if ("DUMMY_TEXT.TXT".equals(upload_file_name)) {
			System.out.println("Uploaded file name of task 2 is correct");
		} else {
			System.out.println("Task 2 creation failed due to wrong uploaded file name");
		}
		
		
		driver.findElement(By.id("dashboard")).click();

		//Add Task 3 to Project 1
		driver.findElement(By.id("btn_add_task")).click();
		try {
		    Thread.sleep(400);
		} catch (InterruptedException ie) {
		    Thread.currentThread().interrupt();
		}
				
		driver.findElement(By.id("summary")).sendKeys("Task #3");
		driver.findElement(By.id("description")).sendKeys("This is task #3");
		//Set status "In progress"
		driver.findElement(By.className("select-wrapper")).click();
		try {
		    Thread.sleep(1500);
		} catch (InterruptedException ie) {
		    Thread.currentThread().interrupt();
		}
		driver.findElement(By.xpath("(//*[@class='select-wrapper']/ul/li)[2]")).click();
				
		//In labels set "Backend", "CI", "Testing"
		driver.findElement(By.id("multiselectContainerReact")).click();
		driver.findElement(By.xpath(("((//*[@id='multiselectContainerReact']/div)[2]/ul/li)[1]"))).click();
		driver.findElement(By.xpath(("((//*[@id='multiselectContainerReact']/div)[2]/ul/li)[4]"))).click();
		driver.findElement(By.xpath(("((//*[@id='multiselectContainerReact']/div)[2]/ul/li)[6]"))).click();
				
		//Upload file
		WebElement uploadElement_3 = driver.findElement(By.id("attachments"));

		//Enter the file path
		uploadElement_3.sendKeys("C:\\Users\\stergios\\Desktop\\Workable_Assigment\\dummy_text.txt");
		        
				
		driver.findElement(By.cssSelector("button[name='action']")).click();
				
		try {
		    Thread.sleep(1500);
		} catch (InterruptedException ie) {
		    Thread.currentThread().interrupt();
		}
				
				
		//View Task 3 of Project 1
		in_prog_task_summ = driver.findElement(By.xpath("(//*[@id='in_progress_items']/div/div/span)[3]")).getText();
		in_prog_task_desc = driver.findElement(By.xpath("(//*[@id='in_progress_items']/div/div/p)[5]")).getText();
		label_tags = driver.findElement(By.xpath("(//*[@id='in_progress_items']/div/div)[5]/div[1]")).getText();
		label_tags = label_tags + " " + driver.findElement(By.xpath("(//*[@id='in_progress_items']/div/div)[5]/div[2]")).getText();
		label_tags = label_tags + " " + driver.findElement(By.xpath("(//*[@id='in_progress_items']/div/div)[5]/div[3]")).getText();
		upload_file_name = driver.findElement(By.xpath("(//*[@id='in_progress_items']/div)[3]/div/p[2]")).getText();
				
		//Check if summary is correct in view mode
		if ("Task #3".equals(in_prog_task_summ)) {
			System.out.println("Summary of task 3 is correct");
		} else {
			System.out.println("Task 3 creation failed due to wrong summary");
		}

		//Check if description is correct in view mode
		if ("This is task #3".equals(in_prog_task_desc)) {
			System.out.println("Description of task 3 is correct");
		} else {
			System.out.println("Task 3 creation failed due to wrong description");
		}
				
		//Check if labes are correct in view mode
		if ("BACKEND CI TESTING".equals(label_tags)) {
			System.out.println("Labels of task 3 are correct");
		} else {
			System.out.println("Task 3 creation failed due to wrong labels");
		}
				
		//Check if uploaded file name is correct in view mode
		if ("DUMMY_TEXT.TXT".equals(upload_file_name)) {
			System.out.println("Uploaded file name of task 3 is correct");
		} else {
			System.out.println("Task 3 creation failed due to wrong uploaded file name");
		}
		
		
		//Drag and drop Task 1 in To Do status
		WebElement fromElement = driver.findElement(By.xpath("(//*[@id='in_progress_items']/div)[1]"));
		WebElement toElement = driver.findElement(By.xpath("//*[@id='to_do_items']"));
		
		Actions builder_tsk1 = new Actions(driver);
		Action dragAndDrop = builder_tsk1.clickAndHold(fromElement)
				.moveToElement(toElement)
				.release(toElement)
				.build();
		
		dragAndDrop.perform();
		
		//Drag and drop didn't work
		
		//Drag and drop Task 2 in To Do status and then back "In progress" status

		
		//Drag and drop Task 3 in "In review" status and then in "Done" status

		
		
		
	}

}
