package com.app.inventory.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Item it1 = new Item(1, "Сисблокqwqwqwqwqwqwqwqwqwqwqwqwqwqwqwqwqwqwqwqwqwqwqwqwqwqwqwqwqwqwqwqwqwqwqwqwqwqwqwqwqwqwqwqwqwqwqwqwqwqw", 3, 100);
        Item it2 = new Item(2, "Моник", 3, 101);
        Item it3 = new Item(3, "Клава", 5, 102);
        Item it4 = new Item(4, "Мышь", 6, 103);
        Item it5 = new Item(5, "Колонки", 1, 104);

        List<Item> items = List.of(it1, it2, it3, it4, it5);

//C:\Users\Komp\Desktop\Excel

// Создание книги Excel
        XSSFWorkbook book = new XSSFWorkbook();
        FileOutputStream fileOut = new FileOutputStream("C:/Users/Komp/Desktop/Excel/workbook.xlsx");

// создания страниц
        XSSFSheet sheet = book.createSheet("List of inventory");
//        sheet.autoSizeColumn(0);
//        sheet.autoSizeColumn(1);
//        sheet.autoSizeColumn(2);
//        sheet.autoSizeColumn(3);
        sheet.setColumnWidth(0, 9000);
        sheet.setColumnWidth(1, 15000);
        sheet.setColumnWidth(2, 10000);
        sheet.setColumnWidth(3, 7000);
// создание строк
        XSSFFont font = book.createFont();
        font.setFontHeightInPoints((short) 16);
        font.setFontName("Times New Roman");

        XSSFFont fontHead = book.createFont();
        fontHead.setFontHeightInPoints((short) 18);
        fontHead.setFontName("Times New Roman");
        fontHead.setBold(true);

        XSSFCellStyle style = book.createCellStyle();
        style.setWrapText(true);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        XSSFCellStyle style1 = style.copy();
        style1.setFont(fontHead);

        XSSFRow head = sheet.createRow(0);
        //head.setRowStyle(style);
        XSSFCell cellHead1 = head.createCell(0);
        cellHead1.setCellType(CellType.STRING);
        cellHead1.setCellStyle(style1);
        cellHead1.setCellValue("Инвентарный номер");

        XSSFCell cellHead2 = head.createCell(1);
        cellHead2.setCellType(CellType.STRING);
        cellHead2.setCellStyle(style1);
        cellHead2.setCellValue("Описание инвентаря");

        XSSFCell cellHead3 = head.createCell(2);
        cellHead3.setCellType(CellType.STRING);
        cellHead3.setCellStyle(style1);
        cellHead3.setCellValue("Количество инвентаря");

        XSSFCell cellHead4 = head.createCell(3);
        cellHead4.setCellType(CellType.STRING);
        cellHead4.setCellStyle(style1);
        cellHead4.setCellValue("Номер кабинета");

        for(int i = 0; i < items.size(); i++) {
            XSSFRow row = sheet.createRow(i + 1);
            //row.setRowStyle(style);
            XSSFCell cell1 = row.createCell(0);
            cell1.setCellType(CellType.NUMERIC);
            cell1.setCellStyle(style);
            cell1.setCellValue(items.get(i).getNumber());

            XSSFCell cell2 = row.createCell(1);
            cell2.setCellType(CellType.STRING);
            cell2.setCellStyle(style);
            cell2.setCellValue(items.get(i).getDescription());

            XSSFCell cell3 = row.createCell(2);
            cell3.setCellType(CellType.NUMERIC);
            cell3.setCellStyle(style);
            cell3.setCellValue(items.get(i).getCountItems());

            XSSFCell cell4 = row.createCell(3);
            cell4.setCellType(CellType.NUMERIC);
            cell4.setCellStyle(style);
            cell4.setCellValue(items.get(i).getOffice());
        }

        XSSFRow dateRow = sheet.createRow(items.size() + 3);
        CreationHelper createHelper = book.getCreationHelper();

        XSSFCellStyle cellDateStyle = book.createCellStyle();
        cellDateStyle.setDataFormat(createHelper
                .createDataFormat()
                .getFormat("dd.MM.yyyy"));
        cellDateStyle.setFont(font);
        style.setWrapText(true);
        cellDateStyle.setAlignment(HorizontalAlignment.LEFT);
        cellDateStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        XSSFCell cell = dateRow.createCell(0);
        cell.setCellStyle(cellDateStyle);
        cell.setCellValue(new Date());

// создание и форматирование ячеек
// запись информации в ячейки

// Закрытие
        book.write(fileOut);
        fileOut.close();





    }
}
