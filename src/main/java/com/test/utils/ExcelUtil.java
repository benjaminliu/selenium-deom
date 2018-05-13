package com.test.utils;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;

/**
 * Created by admin on 2018/5/1.
 */
public class ExcelUtil {
    private static Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    public static XSSFWorkbook loadExcel(String path) {
        XSSFWorkbook book = null;

        try {
            FileInputStream e = new FileInputStream(path);
            book = new XSSFWorkbook(e);
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return book;
    }

    public static XSSFSheet loadSheet(String sheetName, XSSFWorkbook book) {
        XSSFSheet sheet = null;

        try {
            sheet = book.getSheet(sheetName);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return sheet;
    }

    public static String getCellData(int row, int col, XSSFSheet sheet) {
        --row;
        --col;
        String data = null;

        try {
            XSSFCell e = sheet.getRow(row).getCell(col);
            data = e.getStringCellValue();
        } catch (NullPointerException var5) {
            logger.warn("No Information in Sheet: " + sheet.getSheetName() + ", Row: " + (row + 1) + ", Col: " + (col + 1));
            data = "No Information";
        }

        return data;
    }

    public static int getLastRow(XSSFSheet sheet) {
        int lastRow = 0;

        try {
            lastRow = sheet.getLastRowNum();
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return lastRow + 1;
    }

    public static int getLastCell(XSSFRow row) {
        short lastCell = 0;

        try {
            lastCell = row.getLastCellNum();
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return lastCell + 1;
    }

    public static XSSFWorkbook getBook() {
        XSSFWorkbook book = new XSSFWorkbook();
        return book;
    }

    public static XSSFSheet getSheet(String sheetName, XSSFWorkbook book) {
        XSSFSheet sheet = book.createSheet(sheetName);
        return sheet;
    }

    public static XSSFRow getRow(int rowNum, XSSFSheet sheet) {
        XSSFRow row = sheet.createRow(rowNum);
        return row;
    }

    public static XSSFCell getCell(int cellNum, XSSFRow row) {
        XSSFCell cell = row.createCell(cellNum);
        return cell;
    }
}

