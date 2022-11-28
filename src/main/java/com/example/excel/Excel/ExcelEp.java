package com.example.excel.Excel;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ExcelEp {
    String Export(HttpServletResponse response) throws IOException;
}
