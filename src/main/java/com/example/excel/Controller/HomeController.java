package com.example.excel.Controller;


import com.example.excel.Excel.ExcelExport;
import com.example.excel.Excel.ExcelIpImport;
import com.example.excel.Repository.DsCapTinhRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/excel")
public class HomeController {
    private final DsCapTinhRepository dsCapTinhRepository;
    private final ExcelIpImport excelImport;
    private final ExcelExport excelExport;

    public HomeController(DsCapTinhRepository dsCapTinhRepository, ExcelIpImport excelImport, ExcelExport excelExport) {
        this.dsCapTinhRepository = dsCapTinhRepository;
        this.excelImport = excelImport;
        this.excelExport = excelExport;
    }


    @GetMapping ( "/import")
    @ResponseBody
    public Map<String, String> importExcel(){
        excelImport.ImportExcel();
        return null;
    }

    @GetMapping("/export")
    public void exportExcel(HttpServletResponse response) throws IOException {
        String headerKey = "Content-Disposition";
        String headervalue = "attachment;filename=City.xlsx";
        response.setHeader(headerKey, headervalue);
        excelExport.Export(response);
    }
}
