package com.utility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ui.pojo.User;

public class ExcelReaderUtility {

	public static Iterator<User> readExcelFile(String fileName) {
		
		File excleFile= new File(System.getProperty("user.dir")+"//testData//"+fileName);
		XSSFWorkbook xssfWorkBook = null;
		List<User> userList;
		XSSFSheet xssfSheet;
		 Row row;
		 Cell emailAddressCell;
		 Cell passwordCell;
		 User user;
		try {
			 xssfWorkBook= new XSSFWorkbook(excleFile);
		}
		catch (InvalidFormatException | IOException e) {
		
			e.printStackTrace();
		}
			 userList= new ArrayList<User>();
			xssfSheet= xssfWorkBook.getSheet("LoginTestData");
			 Iterator<Row> rowItertator=xssfSheet.iterator();
			 while(rowItertator.hasNext()) {
				  row =rowItertator.next();
				  emailAddressCell=row.getCell(0);
				  passwordCell=row.getCell(1);
				 user = new User(emailAddressCell.toString(),passwordCell.toString());
				 userList.add(user);
				 
			
			 try {
				xssfWorkBook.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		return userList.iterator();
	}
}
