package github.driver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class SeleniumCommon extends Report {

	private static final String NOT_DISPLAYED = " is not displayed to perform the action.";

	public void checkVisiblity(WebElement WebElement) {
		try {
			if (!WebElement.isDisplayed()) {
				throw new ElementNotVisibleException("WebElement " + WebElement.getTagName() + NOT_DISPLAYED);
			} else {
				highlightElement(WebElement);
			}
		} catch (NoSuchElementException npe) {
			// fail("NoSuchElementException occured for WebElement: "+
			// npe.getStackTrace());
		}
	}

	public static void checkVisiblity1(WebElement element) {
		if (!element.isDisplayed()) {
			throw new ElementNotVisibleException("TextInput " + element + NOT_DISPLAYED);
		} else {
			highlightElement(element);
		}
	}

	public static void bringElementInView(WebElement WebElement) {
		((JavascriptExecutor) DriverObject.getDriver()).executeScript("arguments[0].scrollIntoView(true);", WebElement);
	}

	public static void highlightElement(WebElement WebElement) {
		for (int i = 0; i < 2; i++) {
			JavascriptExecutor js = (JavascriptExecutor) DriverObject.getDriver();
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", WebElement,
					"color: yellow; border: 2px solid green;");
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", WebElement, "");
		}
	}

	public static void readBashScript() {
		try {
			// Whatever you want to execute
			Process proc = Runtime.getRuntime().exec("date");
			BufferedReader read = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			try {
				proc.waitFor();
			} catch (InterruptedException e) {
				// LOG.error(e.getMessage());
			}
			while (read.ready()) {
				// LOG.error(read.readLine());
			}
		} catch (IOException e) {
			// LOG.error(e.getMessage());
		}
	}

	public static void appendTextFieldValue(WebElement textfield, String text) {
		checkVisiblity1(textfield);
		textfield.sendKeys(text);
	}

	public static void checkElement(WebElement WebElement) {
		bringElementInView(WebElement);
		WebElement.click();
	}

	public static void clearTextFieldValue(WebElement textfield) {
		clickUsingJSExecutor(textfield);
		Wait.oneSecond();
		checkVisiblity1(textfield);
		textfield.clear();
	}
	
	public static void clearTextFieldValue1(WebElement textfield) {
		textfield.clear();
	}
	public static void clearTextFieldValue2(WebElement textfield) {
		textfield.click();
	}
	public static void clearTextFieldValue8(WebElement textfield,String vALUE) {
	Select select=new Select(textfield);
	select.selectByValue(vALUE);
	}
	public static void clickElement(WebElement WebElement) {
		bringElementInView(WebElement);
		checkVisiblity1(WebElement);
		WebElement.click();
	}

	public static void clickUsingJSExecutor(WebElement webElement) {
		JavascriptExecutor ex = (JavascriptExecutor)DriverObject.getDriver();
		ex.executeScript("arguments[0].click();", webElement);
	}

	protected static List<String> getComboboxOptions(WebElement WebElement) {
		List<WebElement> list = ((Select) WebElement).getOptions();
		List<String> listItems = new ArrayList<String>();
		for (WebElement item : list) {
			listItems.add(item.getText());
		}
		return listItems;
	}



	public static String getComboboxSelectedText(WebElement webElement) {
		checkVisiblity1(webElement);
		Select select = new Select(webElement);
		return select.getFirstSelectedOption().getText();

	}

	public static String getElementText(WebElement WebElement) {
		bringElementInView(WebElement);
		checkVisiblity1(WebElement);
		return WebElement.getText();
	}

	/*
	 * public static List<String> getGridColumnData(WebTable table, Integer
	 * column) { return table.getColumnData(column); }
	 * 
	 * public static List<String> getGridHeaderNames(WebTable table) {
	 * bringElementInView(table); Integer colCount =
	 * table.getTableHeaderColCount(); ArrayList<String> list = new
	 * ArrayList<String>(); String xpaths = "//table/thead/tr/td"; int
	 * webTableType = 1;
	 * 
	 * if (DriverObject.getDriver().findElements(By.xpath(xpaths)).size() > 0) {
	 * webTableType = 1; } else { webTableType = 2; }
	 * 
	 * for (Integer count = 1; count <= colCount; count++) { WebElement
	 * WebElement = null; if (webTableType == 1) { xpaths =
	 * "//table/thead/tr/td[" + count + "]";
	 * 
	 * } else { xpaths = "//table/thead/tr/td[" + count + "]"; } WebElement =
	 * DriverObject.getDriver().findElement(By.xpath(xpaths));
	 * 
	 * String str = null; try { str = WebElement.getText(); } catch (Exception
	 * e) { str = " "; } list.add(str); } return list;
	 * 
	 * }
	 */

	/*
	 * public static int getTableHeaderColumn(WebTable table, String
	 * tableHeaderName) { int index = -1; List<String> list =
	 * getGridHeaderNames(table);
	 * 
	 * List<String> trimList = new ArrayList<String>();
	 * 
	 * for (String item : list) { trimList.add(item.trim()); } index =
	 * trimList.indexOf(tableHeaderName);
	 * 
	 * return index; }
	 */

	public static String getTextFieldValue(WebElement textfield) {
		checkVisiblity1(textfield);
		return textfield.getText();
	}

	public static boolean isCheckboxChecked(WebElement webElement) {

		return webElement.isSelected();
	}

	protected static boolean isCombobox(WebElement WebElement) {
		String jsScript = "var WebElement = document.getElementById(arguments[0]);"
				+ "if(WebElement.parentNode.nodeName === 'TD'){"
				+ "if(WebElement.parentNode.nextSibling.nodeName === 'TD' && "
				+ "WebElement.parentNode.nextSibling.getAttribute('class') === 'x-trigger-cell'){" + "return true;"
				+ "}" + "}" + "return false;";
		JavascriptExecutor js = (JavascriptExecutor) DriverObject.getDriver();
		Object response = js.executeScript(jsScript, WebElement.getAttribute("id"));
		return (boolean) response;
	}

	protected static boolean isComboboxExists(WebElement WebElement) {
		return (isElementExists(WebElement) && isCombobox(WebElement));
	}

	public static boolean isElementEditable(WebElement WebElement) {
		try {
			checkVisiblity1(WebElement);
			String readonly = WebElement.getAttribute("disabled");
			Assert.assertNotNull(readonly);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static boolean isElementEnabled(WebElement webElement) {
		checkVisiblity1(webElement);
		return webElement.isEnabled();
	}

	public static boolean isElementExists(WebElement webElement) {
		try {
			return webElement.isDisplayed();
		} catch (NoSuchElementException | StaleElementReferenceException e) {
			return false;
		}
	}

	public static boolean isNull(Object object) {

		if (object != null) {
			return false;
		}
		return true;
	}

	public static boolean isRadioButtonSelected(WebElement WebElement) {
		bringElementInView(WebElement);
		return WebElement.isSelected();
	}

	protected static boolean isTabSelected(WebElement WebElement) {
		WebElement parent = WebElement.findElement(By.xpath(".."));
		if (parent.getAttribute("class").equals("current") || WebElement.getAttribute("class").equals("current")) {
			return true;
		}
		return false;
	}

	protected static boolean isTextField(WebElement WebElement) {
		String jsScript = "var WebElement = document.getElementById(arguments[0]);"
				+ "if(WebElement.parentNode.nodeName === 'TD'){" + "if(!WebElement.parentNode.nextSibling){"
				+ "return true;" + "}" + "}" + "return false;";
		JavascriptExecutor js = (JavascriptExecutor) DriverObject.getDriver();
		Object response = js.executeScript(jsScript, WebElement.getAttribute("id"));
		return (boolean) response;
	}

	protected static boolean isTextfieldExists(WebElement WebElement) {
		return (isElementExists(WebElement) && isTextField(WebElement));
	}

	/*
	 * public static boolean isWebTableExists(WebElement webTable) { try {
	 * String id = webTable.getId(); LOG.debug("Table ID: " + id);
	 * 
	 * if (DriverObject.getDriver().findElements(By.id(id)).size() != 0) {
	 * return true; } } catch (NoSuchElementException |
	 * StaleElementReferenceException e) { return false; } return false; }
	 */
	public static boolean refreshUntilElementAppears(WebElement WebElement, long maxTimeout) {
		Wait.refreshUntilElementAppears(WebElement, maxTimeout);
		return isElementExists(WebElement);
	}

	public static boolean refreshUntilElementDisappears(WebElement WebElement, long maxTimeOut) {
		checkVisiblity1(WebElement);
		return Wait.refreshUntilElementDisappears(WebElement, maxTimeOut);
	}

	protected static void selectComboboxValue(WebElement webElement, String value) {
		Select select = new Select(webElement);
        List<WebElement> options = getComboboxOptionsElements(select);
        List<String> list = new ArrayList<String>();

        for (WebElement option : options) {
            list.add(option.getText().trim().replaceAll("\n", ""));
        }
        Wait.sleep(1L);
        int index = list.indexOf(value.trim().replaceAll("\n", ""));
        select.selectByIndex(index);
	}

	  protected static List<WebElement> getComboboxOptionsElements(WebElement element) {
	        return ((Select) element).getOptions();
	    }
	public static void setTextFieldValue(WebElement textfield, String text) {
		checkVisiblity1(textfield);
		bringElementInView(textfield);
		clickUsingJSExecutor(textfield);
		textfield.sendKeys(Keys.CONTROL + "a");
		textfield.sendKeys(Keys.DELETE);
		Wait.oneSecond();
		textfield.sendKeys(text);
		Wait.oneSecond();
	}
	
	

	    protected static List<WebElement> getComboboxOptionsElements(Select element) {
	        return element.getOptions();
	    }


	public static void enterTextFieldValue(WebElement textfield, String text) {
		checkVisiblity1(textfield);
		bringElementInView(textfield);
		clickUsingJSExecutor(textfield);
		textfield.sendKeys(Keys.CONTROL + "a");
		textfield.sendKeys(Keys.DELETE);
		Wait.oneSecond();
		textfield.sendKeys(text);
		textfield.sendKeys(Keys.ENTER);
		Wait.oneSecond();
	}

	public static void toLowerCase(List<String> list) {
		for (int i = 0, l = list.size(); i < l; i++) {
			list.set(i, list.get(i).toLowerCase());
		}
	}

	public static void trimList(List<String> list) {
		for (int i = 0, l = list.size(); i < l; ++i) {
			list.set(i, list.get(i).trim());
		}
	}

	public static void unCheckElement(WebElement webElement) {

		webElement.click();
	}

	public static boolean waitUntilElementAppears(WebElement WebElement, long maxTimeout) {
		Wait.untilElementAppears(WebElement, maxTimeout);
		return isElementExists(WebElement);
	}

	public static boolean waitUntilElementDisappears(WebElement WebElement, long maxTimeout) {
		Wait.untilElementDisappears(WebElement, maxTimeout);
		return !isElementExists(WebElement);
	}

	public static boolean waitUntilElementIsDisabled(WebElement WebElement, long maxTimeOut) {
		checkVisiblity1(WebElement);
		return Wait.untilElementIsDisabled(WebElement, maxTimeOut);
	}

	public static boolean waitUntilElementIsEnabled(WebElement WebElement, long maxTimeout) {
		checkVisiblity1(WebElement);
		return Wait.untilElementIsEnabled(WebElement, maxTimeout);
	}

}
