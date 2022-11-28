package com.example.excel.Excel;

import com.example.excel.Entity.DsCapTinh;
import com.example.excel.Repository.DsCapTinhRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class ExcelExport implements ExcelEp {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    private final DsCapTinhRepository dsCapTinhRepository;

    public ExcelExport( DsCapTinhRepository dsCapTinhRepository) {
        this.workbook = new XSSFWorkbook();
        this.dsCapTinhRepository = dsCapTinhRepository;
    }


    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
            sheet.autoSizeColumn(columnCount);
            Cell cell = row.createCell(columnCount);
            if (value instanceof Long) {
                cell.setCellValue((Long) value);
            } else if (value instanceof Integer) {
                cell.setCellValue((Integer) value);
            } else if (value instanceof Boolean) {
                cell.setCellValue((Boolean) value);
            } else {
                cell.setCellValue((String) value);
            }
            cell.setCellStyle(style);
        }

        private void writeHeaderLine() {
            sheet = workbook.createSheet("City1");

            Row row = sheet.createRow(0);
            CellStyle style = workbook.createCellStyle();
            XSSFFont font = workbook.createFont();
            font.setBold(true);
            font.setFontHeight(20);
            style.setFont(font);
            style.setAlignment(HorizontalAlignment.CENTER);
            createCell(row, 0, "Thong tin Tinh", style);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
            font.setFontHeightInPoints((short) (10));

            row = sheet.createRow(1);
            font.setBold(true);
            font.setFontHeight(16);
            style.setFont(font);
            createCell(row, 0, "MaTp", style);
            createCell(row, 1, "Tinh", style);

        }

        private void writeDataLines() {
            int rowCount = 2;

            CellStyle style = workbook.createCellStyle();
            XSSFFont font = workbook.createFont();
            font.setFontHeight(14);
            style.setFont(font);

            for (DsCapTinh dsCapTinh : dsCapTinhRepository.findAll()) {
                Row row = sheet.createRow(rowCount++);
                int columnCount = 0;
                createCell(row, columnCount++, dsCapTinh.getCode(), style);
                createCell(row, columnCount++, dsCapTinh.getName(), style);

            }
        }


    @Override
    public String Export(HttpServletResponse response) throws IOException{

        writeHeaderLine();
        writeDataLines();
        ServletOutputStream outputStream=response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
        return  null;
    }
}
