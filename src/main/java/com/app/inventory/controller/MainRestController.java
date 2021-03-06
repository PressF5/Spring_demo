package com.app.inventory.controller;

import com.app.inventory.entity.Item;
import com.app.inventory.service.InventoryService;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@Scope("session")
public class MainRestController {
    @Autowired
    private InventoryService inventoryService;

    private List<Item> resultList;

    @PostMapping(value = "/searchItems")
    public List<Item> searched(@RequestParam("officeOrInvNumber") int search, @RequestParam("choice") String select, Model model) {
        resultList = inventoryService.getItemsByInvNumberOrOfficeNumber(search, select);
        return resultList;
    }

    @GetMapping("/deleteItem/{id}")
    public void deleteItem(@PathVariable("id") int itemId) {
        inventoryService.deleteItemById(itemId);
        //return "redirect:/search";
    }

    @GetMapping("/downloadExcel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Inventory_search_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        ServletOutputStream outputStream = response.getOutputStream();

        List<Item> items = resultList;

        XSSFWorkbook book = new XSSFWorkbook();

        XSSFSheet sheet = book.createSheet("List of inventory");

        sheet.setColumnWidth(0, 9000);
        sheet.setColumnWidth(1, 15000);
        sheet.setColumnWidth(2, 10000);
        sheet.setColumnWidth(3, 7000);

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

        XSSFCell cellHead1 = head.createCell(0);
        cellHead1.setCellType(CellType.STRING);
        cellHead1.setCellStyle(style1);
        cellHead1.setCellValue("?????????????????????? ??????????");

        XSSFCell cellHead2 = head.createCell(1);
        cellHead2.setCellType(CellType.STRING);
        cellHead2.setCellStyle(style1);
        cellHead2.setCellValue("???????????????? ??????????????????");

        XSSFCell cellHead3 = head.createCell(2);
        cellHead3.setCellType(CellType.STRING);
        cellHead3.setCellStyle(style1);
        cellHead3.setCellValue("???????????????????? ??????????????????");

        XSSFCell cellHead4 = head.createCell(3);
        cellHead4.setCellType(CellType.STRING);
        cellHead4.setCellStyle(style1);
        cellHead4.setCellValue("?????????? ????????????????");

        for(int i = 0; i < items.size(); i++) {
            XSSFRow row = sheet.createRow(i + 1);

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
            cell4.setCellValue(items.get(i).getOffice().getOfficeNumber());
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

        book.write(outputStream);
        book.close();

        outputStream.close();
        //resultList = null;
    }

}