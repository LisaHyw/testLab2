package webTest;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;


class WebTest {
	WebDriver driver;
	WebElement webElement=null;
	Workbook book;
	Sheet sheet;
	Cell cell;

	@BeforeEach
	void setUp() throws Exception {
		book=Workbook.getWorkbook(new File("input.xls"));
		sheet=book.getSheet(0);
		System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
		driver = new ChromeDriver();
		
	}

	@Test
	void test() throws InterruptedException {
		boolean flag=true;
		for (int i=0;i<97;i++) {
			flag=true;
			driver.get("https://psych.liebes.top/st");
			cell=sheet.getCell(0,i);
			String id=cell.getContents();
			cell=sheet.getCell(1,i);
			String result=cell.getContents();
			if(result=="")
				continue;
			webElement=driver.findElement(By.xpath("//*[@id=\"username\"]"));
			webElement.sendKeys(id);
			webElement=driver.findElement(By.xpath("//*[@id=\"password\"]"));
			webElement.sendKeys(id.substring(4));
			webElement=driver.findElement(By.xpath("//*[@id=\"submitButton\"]"));
			webElement.click();
			
			//Thread.sleep(3000);
			webElement=driver.findElement(By.xpath("/html/body/div/div[2]/a/p"));
			String s=webElement.getText();
			flag=s.equals(result);
			if(flag==false) {
				System.out.println("表格中的学号"+id+"和git地址的对应关系不正确");
			}	
		}
		if(flag==true) {
			System.out.println("表格中的学号和git地址的对应关系全部正确");
		}
		
		
	}

}
