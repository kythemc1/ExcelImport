package com.example.excel.Excel;

import com.example.excel.Entity.DsCapHuyen;
import com.example.excel.Entity.DsCapTinh;
import com.example.excel.Entity.DsCapXa;
import com.example.excel.Repository.DsCapHuyenRepository;
import com.example.excel.Repository.DsCapTinhRepository;
import com.example.excel.Repository.DsCapXaRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

@Service
public class ExcelIpImport implements ExcelIp {
    private final DsCapTinhRepository dsCapTinhRepository;
    private final DsCapHuyenRepository dsCapHuyenRepository;
    private final DsCapXaRepository dsCapXaRepository;

    public ExcelIpImport(DsCapTinhRepository dsCapTinhRepository, DsCapHuyenRepository dsCapHuyenRepository, DsCapXaRepository dsCapXaRepository) {
        this.dsCapTinhRepository = dsCapTinhRepository;
        this.dsCapHuyenRepository = dsCapHuyenRepository;
        this.dsCapXaRepository = dsCapXaRepository;
    }

    @Override
    public void ImportExcel() {
        Map<String, String > mapC = new HashMap<>();
        Map<String, String > mapD = new HashMap<>();
        Map<String, String > mapW = new HashMap<>();
        List<DsCapTinh> dsCapTinhs=new ArrayList<>();
        List<DsCapHuyen> dsCapHuyens=new ArrayList<>();
        List<DsCapXa> dsCapXas =new ArrayList<>();
        String CName ="";
        String CCode="";
        String CCodess= "";


        String DName="";
        String DCode="";
        String DCodess="";
        DsCapTinh dsT =new DsCapTinh();

        String WName="";
        String WCode="";
        String WCodess="";
        DsCapHuyen dsH=new DsCapHuyen();


        String excelFilePath = "E:\\office\\New folder\\Book1.xlsx";

        FileInputStream inputStream;

        try {
            inputStream = new FileInputStream(excelFilePath);
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet firstSheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = firstSheet.iterator();
            rowIterator.next();

            while (rowIterator.hasNext()) {
                Row nextRow = rowIterator.next();
                Iterator<Cell> cellIterator = nextRow.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell nextCell = cellIterator.next();
                    int columnIndex = nextCell.getColumnIndex();
                    DataFormatter formatter = new DataFormatter();
                    switch (columnIndex) {
                        case 0:
                            CName = nextCell.getStringCellValue();
                            break;
                        case 1:
                            CCode = nextCell.getStringCellValue();
                            break;
                        case 2:
                            DName = nextCell.getStringCellValue();
                            break;
                        case 3:
                            DCode = formatter.formatCellValue(nextCell);
                            break;
                        case 4:
                            WName = nextCell.getStringCellValue();
                            break;
                        case 5:
                            WCode = nextCell.getStringCellValue();
                            break;
                    }

                    if (CCodess!=CCode) {
                        DsCapTinh dsCapTinh = new DsCapTinh();
                    mapC.put(CName, CCode);
                    dsCapTinh.setCode(CCode);
                    dsCapTinh.setName(CName);
                    dsCapTinhs.add(dsCapTinh);
                    dsT=dsCapTinh;
                    workbook.close();
                }
                    if(DCodess!=DCode)
                    {
                       DsCapHuyen dsCapHuyen=new DsCapHuyen();
                       mapD.put(DName,DCode);
                       dsCapHuyen.setName(DName);
                       dsCapHuyen.setCode(DCode);
                       dsCapHuyen.setCityId(dsT);
                        dsCapHuyens.add(dsCapHuyen);
                        dsH=dsCapHuyen;
                        workbook.close();
                    }
                    if (WCodess!=WCode){
                        DsCapXa dsCapXa=new DsCapXa();
                        mapW.put(WName,WCode);
                        dsCapXa.setName(WName);
                        dsCapXa.setCode(WCode);
                        dsCapXa.setDistrictId(dsH);
                        dsCapXas.add(dsCapXa);
                        workbook.close();
                    }
                     CCodess=CCode;
                    DCodess=DCode;
                    WCodess=WCode;
            }
                }

        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
         dsCapTinhRepository.saveAll(dsCapTinhs);
        dsCapHuyenRepository.saveAll(dsCapHuyens);
        dsCapXaRepository.saveAll(dsCapXas);

    }
}

