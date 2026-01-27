package com.swaglabs.util;


import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class ExcelReport {
    private String sheetName;
    private String filePath = "src/test/Test-Documents/Swag Labs-Test Scenarios.xlsx";


    public ExcelReport(String sheetName){
        this.sheetName = sheetName;
    }

     public  void report( Boolean condition, int rawName, String comment, String actualResult){
        try{
            FileInputStream fileInputStream = new FileInputStream(filePath);
            Workbook workbook = WorkbookFactory.create(fileInputStream);
            Sheet sheet = workbook.getSheet(sheetName);
            Row row = sheet.getRow(rawName);
            Cell cell = row.createCell(9);

            CellStyle style = cell.getSheet().getWorkbook().createCellStyle();
            Font font = cell.getSheet().getWorkbook().createFont();

            if (condition) {
                cell.setCellStyle(testPass(style, font));
                cell.setCellValue("P");
                comment("pass", cell, row,"Test Case passed successfully.");
                actualResult("blue", cell, row, actualResult);
            }
            else {
                cell.setCellStyle(testFail(style, font));
                cell.setCellValue("X");
                comment("fail", cell, row, comment);
                actualResult("blue", cell, row, actualResult);
            }

            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            workbook.write(fileOutputStream);

        }catch (Exception e){
            System.out.println("Writ Report catch block.");
            e.printStackTrace();
        }

     }

    public  String  comment(String type, Cell cell, Row row, String comment ){

        cell = row.createCell(10);

        CellStyle style = cell.getSheet().getWorkbook().createCellStyle();
        Font font = cell.getSheet().getWorkbook().createFont();

        cell.setCellStyle(commentStyle(type, style, font));
        cell.setCellValue(comment);

        return comment;
    }

    public  String  actualResult(String type, Cell cell, Row row, String actualResult ){

        cell = row.createCell(7);

        CellStyle style = cell.getSheet().getWorkbook().createCellStyle();
        Font font = cell.getSheet().getWorkbook().createFont();

        cell.setCellStyle(commentStyle(type, style, font));
        cell.setCellValue(actualResult);

        return actualResult;
    }
    private CellStyle testPass(CellStyle style, Font font){
        font.setFontHeightInPoints((short) 72);
        font.setColor(HSSFColor.HSSFColorPredefined.DARK_GREEN.getIndex());
        style.setFont(font);

        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        style.setBorderLeft(BorderStyle.MEDIUM);
        style.setBorderRight(BorderStyle.MEDIUM);
        style.setBorderTop(BorderStyle.MEDIUM);
        style.setBorderBottom(BorderStyle.MEDIUM);
        style.setRightBorderColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        style.setLeftBorderColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        style.setTopBorderColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        style.setBottomBorderColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());

        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFillForegroundColor(HSSFColor.HSSFColorPredefined.LIGHT_GREEN.getIndex());
        return style;
    }
    private CellStyle testFail(CellStyle style, Font font){
        font.setFontHeightInPoints((short) 72);
        font.setColor(HSSFColor.HSSFColorPredefined.DARK_RED.getIndex());
        style.setFont(font);

        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        style.setBorderLeft(BorderStyle.MEDIUM);
        style.setBorderRight(BorderStyle.MEDIUM);
        style.setBorderTop(BorderStyle.MEDIUM);
        style.setBorderBottom(BorderStyle.MEDIUM);
        style.setRightBorderColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        style.setLeftBorderColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        style.setTopBorderColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        style.setBottomBorderColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());

        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFillForegroundColor(HSSFColor.HSSFColorPredefined.RED.getIndex());
        return style;
    }
    private CellStyle commentStyle(String type, CellStyle style, Font font){
        font.setFontHeightInPoints((short) 14);
        if(type.equals("pass"))
            font.setColor(HSSFColor.HSSFColorPredefined.GREEN.getIndex());
        else if (type.equals("fail"))
            font.setColor(HSSFColor.HSSFColorPredefined.RED.getIndex());
        else if (type.equals("blue"))
            font.setColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
        style.setFont(font);

        style.setAlignment(HorizontalAlignment.LEFT);
        style.setVerticalAlignment(VerticalAlignment.TOP);

        style.setWrapText(true);

        style.setBorderLeft(BorderStyle.MEDIUM);
        style.setBorderRight(BorderStyle.MEDIUM);
        style.setBorderTop(BorderStyle.MEDIUM);
        style.setBorderBottom(BorderStyle.MEDIUM);
        style.setRightBorderColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        style.setLeftBorderColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        style.setTopBorderColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        style.setBottomBorderColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        return style;
    }


}
