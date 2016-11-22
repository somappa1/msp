package com.UtilsFiles;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.filechooser.FileSystemView;

import org.apache.commons.io.FileUtils;
import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;
import org.ini4j.Wini;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnhandledAlertException;

import github.driver.DriverObject;
import github.driver.Wait;
import nl.flotsam.xeger.Xeger;

public class Utils {
	private static final int CHARACTER_LENGTH = 160;
	private static final int TWO_SECONDS = 2000;
	private static int reTry = 0;
	private static final String NUMBER_NATIONALID_FORMAT = "N";
	private static final String AlLPHABET_NATIONALID_FORMAT = "A";
	private static final String APLHANUMERIC_NATIONALID_FORMAT = "X";
	private static final String YEAR_NATIONALID_FORMAT = "Y";
	private static final String MONTH_NATIONALID_FORMAT = "M";
	private static final String DATE_NATIONALID_FORMAT = "D";
	private static final String SPECIAL_CHARACTER = "*";
	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	static SecureRandom rnd = new SecureRandom();
	public static final String iniFilePath = "C:\\Users\\NUTHAN\\workspace\\SeleniumPrathi\\files\\DateFile.ini";

	public static String getMonth(String monthValue) {
		String monthString = null;
		switch (monthValue) {
		case "January":
			monthString = "01";
			break;
		case "February":
			monthString = "02";
			break;
		case "March":
			monthString = "03";
			break;
		case "April":
			monthString = "04";
			break;
		case "May":
			monthString = "05";
			break;
		case "June":
			monthString = "06";
			break;
		case "July":
			monthString = "07";
			break;
		case "August":
			monthString = "08";
			break;
		case "September":
			monthString = "09";
			break;
		case "October":
			monthString = "10";
			break;
		case "November":
			monthString = "11";
			break;
		case "December":
			monthString = "12";
			break;
		default:
			monthString = "Invalid month";
			break;
		}

		return monthString;
	}

	public static String getDate(int period, String format) {
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		currentDate.add(Calendar.DAY_OF_MONTH, period);
		String date = formatter.format(currentDate.getTime());
		return date;
	}

	public static String getCurrentDate(String format) {
		Date date = new Date();
		SimpleDateFormat dateFormate = new SimpleDateFormat(format);
		return dateFormate.format(date);

	}

	public static String getDateTime() {
		// This is compulsory. Dont remove
		try {
			Thread.sleep(TWO_SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
		return timeStamp;
	}

	public static String getDate() {
		// This is compulsory. Dont remove
		Wait.sleep(2000);
		String timeStamp = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
		return timeStamp;
	}

	public static String getTime() {
		// This is compulsory. Dont remove
		Wait.sleep(2000);
		String timeStamp = new SimpleDateFormat("HHmmss").format(Calendar.getInstance().getTime());
		return timeStamp;
	}

	public static String getMinSeconds() {
		// This is compulsory. Dont remove
		Wait.sleep(2000);
		String timeStamp = new SimpleDateFormat("mmss").format(Calendar.getInstance().getTime());
		return timeStamp;
	}

	public static String captureScreenshot(String filePath) {
		String path = filePath + "\\" + getDateTime() + ".png";
		try {
			Thread.sleep(2000);
			if (DriverObject.getDriver() != null) {
				File source = ((TakesScreenshot) DriverObject.getDriver()).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(source, new File(path));
				// LOG.debug("captured image to: {}", path);
			} else {
				// LOG.error("failed to capture the screenshot");
			}
		} catch (Exception e) {
			// LOG.debug("Failed to capture screenshot: {} " +
			// e.getMessage(),path);
		}
		return path;
	}

	public static List<String> getNetworkDrives() {
		final String NETWORK_DRIVE = "Network Drive";
		List<String> list = new ArrayList<String>();
		List<File> files = Arrays.asList(File.listRoots());
		for (File f : files) {
			String s1 = FileSystemView.getFileSystemView().getSystemDisplayName(f);
			String s2 = FileSystemView.getFileSystemView().getSystemTypeDescription(f);

			if (s2.trim().equalsIgnoreCase(NETWORK_DRIVE)) {
				String networkName = s1.substring(s1.lastIndexOf("(") + 1, s1.lastIndexOf(")"));
				String networkLocation = s1.substring(s1.indexOf("(") + 1, s1.indexOf(")")) + "\\"
						+ s1.substring(0, s1.indexOf("(")).trim();
				list.add(networkName + "=" + networkLocation);
			}
		}
		return list;
	}

	public static String getTimeStamp() {
		java.util.Date date = new java.util.Date();
		return String.valueOf(date.getTime());
	}

	public static String randomString(String chars, int length) {
		Random rand = new Random();
		StringBuilder buf = new StringBuilder();
		for (int i = 0; i < length; i++) {
			buf.append(chars.charAt(rand.nextInt(chars.length())));
		}
		return buf.toString();
	}

	public static String trimNonBreakingSpace(String text) {
		String nbspChar = String.valueOf(Character.toChars(CHARACTER_LENGTH));
		return text.replace(nbspChar, "");

	}

	public static void executeAndExitCommand(String command) {
		// LOG.debug("about to execute command - {}", command.intern());
		try {
			Process proc = Runtime.getRuntime().exec(command.intern());
			proc.waitFor();
		} catch (IOException | InterruptedException e) {
			throw new UnhandledAlertException(command + " didn't get executed successfully");
		}

	}

	public static List<String> executeCommand(String[] command) {
		String strCommand = new String();
		for (int count = 0; count < command.length; count++) {
			if (strCommand.isEmpty() || strCommand == null) {
				strCommand = command[count];
			} else {
				if (command[count] != null && !command[count].isEmpty()) {
					strCommand = strCommand + " " + command[count];
				}
			}
		}
		try {
			return executeCommand(strCommand.intern());
		} catch (InterruptedException e) {
			List<String> list = new ArrayList<String>();
			list.add(e.getLocalizedMessage().toString());
			return list;
		} catch (IOException e) {
			List<String> list = new ArrayList<String>();
			list.add(e.getLocalizedMessage().toString());
			return list;
		}
	}

	public static List<String> executeCommand(String command) throws InterruptedException, IOException {

		List<String> list = new ArrayList<String>();
		List<String> errorList = new ArrayList<String>();
		try {
			// Whatever you want to execute
			// LOG.debug("about to execute command - {}", command.intern());
			Process proc = Runtime.getRuntime().exec(command.intern());
			BufferedReader read = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			BufferedReader error = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

			String line;
			int count = 0;
			while ((line = read.readLine()) != null || count <= 1000000) {
				if (line != null) {
					if (!"null".equals(line)) {
						list.add(line);
					}
				}
				count += 1;
			}

			count = 0;
			while ((line = error.readLine()) != null || count <= 1000000) {
				if (line != null) {
					if (!"null".equals(line)) {
						errorList.add(line);
					}
				}
				count += 1;
			}

			int exitVal = -1000;
			try {
				exitVal = proc.waitFor();
			} catch (Exception e) {
				// LOG.error("InterruptedException occured while executing
				// command: {}",command);
				throw new InterruptedException("InterruptedException occured while executing command: " + e);
			}

			if (exitVal != -2147016656) {
				if (exitVal > 0) {
					if (exitVal == 6 && reTry <= 3) {
						reTry++;
						Wait.sleep(1000);
						// LOG.debug( "Re-executing the previous command line
						// for - {} times",reTry);
						list = executeCommand(command);
					}

					if (list.size() > 0) {
						// LOG.debug(list.toString());
					}
					if (errorList.size() > 0) {
						// LOG.debug(errorList.toString());
					}

					throw new UnhandledAlertException(command + " command exited with value: " + exitVal);
				}
				// LOG.debug("command <b>" + command + "</b> executed
				// succesfully");
				if (!errorList.isEmpty()) {
					// LOG.debug(errorList.toString());
				}
			}
			error.close();
			read.close();

			return list;
		} catch (IOException e) {
			throw new IOException("failed: " + e.getCause().getMessage());
		}
	}

	public static Boolean isMachineUp(String ip) {

		InetAddress address;
		try {

			address = InetAddress.getByName(ip);

			if (!address.isReachable(3000)) {

				// sometimes isReachable doesn't work as expected
				// Hence doing ping
				List<String> list = executeCommand("ping -a " + ip);
				if (list.toString().contains("Destination host unreachable")) {
					return false;
				} else if (list.toString().toLowerCase().contains("request timed out")) {
					return false;
				} else if (list.toString().toLowerCase().contains("general failure")) {
					return false;
				} else {
					return true;
				}
			} else {
				return true;
			}
		} catch (IOException e) {
			return false;
		} catch (InterruptedException e) {
			return false;
		}
	}

	public static int convertStringToInteger(String toConvertInt) {
		Integer intValue = null;
		try {
			intValue = Integer.parseInt(toConvertInt);
			return intValue;
		} catch (NumberFormatException e) {
			// LOG.error(e.getMessage()+ " exception occurred while converting
			// the string to integer");
			return intValue;
		}
	}

	public static void takeScreenShot(String fileName) throws IOException {
		File srcFile = ((TakesScreenshot) DriverObject.getDriver()).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile,
				new File("C:\\Users\\NUTHAN\\workspace\\SeleniumPrathi\\ScreenShot\\" + fileName + ".png"));
	}

	public static String randomString(int len) {
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		return sb.toString();
	}

	public static void writeIni(String sectionName, ArrayList<String> list) {
		File file = new File(iniFilePath);
		try {
			file.createNewFile();

			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write("[" + sectionName + "]");
			writer.newLine();
			Iterator<String> listIterator = list.iterator();
			while (listIterator.hasNext()) {
				writer.write(listIterator.next());
				writer.newLine();
			}
			writer.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void writeIni(String sectionKey,String key,String value){
		
		try {
			Wini ini1 = new Wini(new File(iniFilePath));
			ini1.put(sectionKey, key, value);
			 ini1.store();
		} catch (InvalidFileFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	


	public static String getIniValue(String sectionName, String keyName) {
		Ini ini = null;
		try {
			ini = new Ini(new File(iniFilePath));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ini.get(sectionName, keyName);
	}

	public static String generateNationalID(String nationalIDFormate) {

		// Declaring the local variables

		LinkedHashMap<String, Integer> charCountMap = new LinkedHashMap<String, Integer>();
		int count = 1;
		StringBuilder nationalID = new StringBuilder();

		try {
			nationalIDFormate = nationalIDFormate.replaceAll("([DMY])\\1", "$1");
			nationalIDFormate = nationalIDFormate.replaceAll("[\\s./]+[-]+", "");
		} catch (NullPointerException e) {
			// LOG.error("Null occurred in national ID formate");
			throw new NullPointerException();
		}

		if (nationalIDFormate.equals("")) {
			// LOG.error("national ID value is empty");

		} else {

			// putting all the format sequences in the map

			char[] charArray = nationalIDFormate.toCharArray();
			for (int charCount = 0; charCount < nationalIDFormate.length(); charCount++) {
				if (charCountMap.containsKey(count + charArray[charCount])) {
					if ((charCountMap.containsKey("1Y") || charCountMap.containsKey("1M")
							|| charCountMap.containsKey("1D"))) {
						charCountMap.put(count + "" + charArray[charCount],
								charCountMap.get(count + "" + charArray[charCount]) + 1);
					}
				} else {

					charCountMap.put(count + "" + charArray[charCount], 1);
					count++;
				}
			}
			// depending upon the national id format generate random sequence
			// using regex and appending the same to get random national id

			for (Map.Entry<String, Integer> me : charCountMap.entrySet()) {
				if (me.getKey().contains(NUMBER_NATIONALID_FORMAT)) {

					String regexNum = "[0-9]{" + Integer.parseInt(me.getValue().toString()) + "}";
					Xeger generatorNum = new Xeger(regexNum);
					String resultNum = generatorNum.generate();
					nationalID = nationalID.append(resultNum);

				} else if (me.getKey().contains(AlLPHABET_NATIONALID_FORMAT)) {

					String regexAplha = "[A-Za-z]{" + Integer.parseInt(me.getValue().toString()) + "}";
					Xeger generatorAplha = new Xeger(regexAplha);
					String resultAplha = generatorAplha.generate();
					nationalID = nationalID.append(resultAplha);

				} else if (me.getKey().contains(APLHANUMERIC_NATIONALID_FORMAT)) {

					String regexAplhaNum = "[A-Za-z0-9]{" + Integer.parseInt(me.getValue().toString()) + "}";
					Xeger generatorAplhaNum = new Xeger(regexAplhaNum);
					String resultAplhaNum = generatorAplhaNum.generate();
					nationalID = nationalID.append(resultAplhaNum);

				} else if (me.getKey().contains(YEAR_NATIONALID_FORMAT)
						|| me.getKey().contains(YEAR_NATIONALID_FORMAT.toLowerCase())) {
					Date date = Calendar.getInstance().getTime();
					SimpleDateFormat dateValue = new SimpleDateFormat("yy");
					nationalID = nationalID.append(String.valueOf(dateValue.format(date)));

				} else if (me.getKey().contains(MONTH_NATIONALID_FORMAT)
						|| me.getKey().contains(MONTH_NATIONALID_FORMAT.toLowerCase())) {
					Date date = Calendar.getInstance().getTime();
					SimpleDateFormat dateValue = new SimpleDateFormat("MM");
					nationalID = nationalID.append(String.valueOf(dateValue.format(date)));

				} else if (me.getKey().contains(DATE_NATIONALID_FORMAT)
						|| me.getKey().contains(DATE_NATIONALID_FORMAT.toLowerCase())) {
					Date date = Calendar.getInstance().getTime();
					SimpleDateFormat dateValue = new SimpleDateFormat("dd");
					nationalID = nationalID.append(String.valueOf(dateValue.format(date)));

				} else if (me.getKey().contains(SPECIAL_CHARACTER)
						|| me.getKey().contains(SPECIAL_CHARACTER.toLowerCase())) {

					String regexAplhaNum = "[!@#$%&^]{" + Integer.parseInt(me.getValue().toString()) + "}";
					Xeger generatorAplhaNum = new Xeger(regexAplhaNum);
					String resultAplhaNum = generatorAplhaNum.generate();
					nationalID = nationalID.append(resultAplhaNum);
				}

			}

		}
		return nationalID.toString();
	}

	
}
