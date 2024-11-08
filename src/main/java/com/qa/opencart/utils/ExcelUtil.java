package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	private static final  String REG_DATA_EXCEL_PATH="./src/main/resources/testdata/data.xlsx";
	static Object[][] data=null;
	private static  Workbook book;
	private static Sheet sheet;
	public  static Object[][] productData(String sheetName)
	{
		System.out.println("Sheet name is :"+sheetName);
	
		try {
			FileInputStream ip=new FileInputStream(REG_DATA_EXCEL_PATH);
			try {
				book=WorkbookFactory.create(ip);
				sheet=book.getSheet(sheetName);
				data=new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
				for(int i=0;i<sheet.getLastRowNum();i++)
				{
					for(int j=0;j<sheet.getRow(0).getLastCellNum();j++)
					{
						data[i][j]=sheet.getRow(i+1).getCell(j).toString();
					}
				}
			} catch (EncryptedDocumentException | IOException e) {
			e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
						e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

}
