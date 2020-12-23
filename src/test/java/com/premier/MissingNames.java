package com.premier;

import java.io.File;
import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MissingNames {

	public static void main(String[] args) throws Throwable {
		
		ArrayList<String> oldTeams = new ArrayList<String>();
		ArrayList<String> newTeams = new ArrayList<String>();
		
		FileInputStream fis = new FileInputStream(new File("./alamExcel/ApacheReader.xls"));
		HSSFWorkbook wb = new HSSFWorkbook(fis);
		HSSFSheet sheet = wb.getSheetAt(0);
		for(int i=0; i < sheet.getLastRowNum();i++) {
			oldTeams.add(sheet.getRow(i).getCell(0).getStringCellValue());	
		}
		System.out.println("List of teams in old list: "+oldTeams);
		
        //Bring the url and this code here
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Selenium\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.premierleague.com/tables");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"advertClose\"]")).click();
		driver.findElement(By.xpath("//*[contains(text(),'I accept cookies from this site')]")).click();
		
		List<WebElement> teams = driver.findElements(By.xpath("//table/tbody/tr/td[3]/a/span[2]"));
		
		for(int i=0; i <teams.size();i++) {
			newTeams.add(teams.get(i).getText().toString());
		}
		
		System.out.println("List of team in new List" +newTeams);
		
		newTeams.removeAll(oldTeams);
		
		System.out.println("These teams are missing: "+newTeams);
		
		
		
		
		
		
		
		
		
		
		
	}

}