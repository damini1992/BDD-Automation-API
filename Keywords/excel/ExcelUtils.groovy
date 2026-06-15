package excel

import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook

class ExcelUtils {

    static void writeDataToSheet(
            String filePath,
            String sheetName,
            List<Map<String, Object>> dataList) {

        FileInputStream fis = new FileInputStream(filePath)

        Workbook workbook = new XSSFWorkbook(fis)

        Sheet sheet = workbook.getSheet(sheetName)

        // Create sheet if not exists
        if (sheet == null) {
            sheet = workbook.createSheet(sheetName)
        }

        // Clear existing rows (optional)
        int lastRow = sheet.getLastRowNum()

        for (int i = lastRow; i >= 0; i--) {
            Row row = sheet.getRow(i)
            if (row != null) {
                sheet.removeRow(row)
            }
        }

        if (dataList == null || dataList.isEmpty()) {

            println("No data found to write into Excel")

            fis.close()
            workbook.close()

            return
        }

        // =========================
        // CREATE HEADER ROW
        // =========================

        Row headerRow = sheet.createRow(0)

        List<String> headers =
                new ArrayList<>(dataList.get(0).keySet())

        for (int i = 0; i < headers.size(); i++) {

            Cell cell = headerRow.createCell(i)

            cell.setCellValue(headers.get(i))
        }

        // =========================
        // WRITE DATA ROWS
        // =========================

        int rowNum = 1

        for (Map<String, Object> rowData : dataList) {

            Row row = sheet.createRow(rowNum++)

            for (int i = 0; i < headers.size(); i++) {

                String columnName = headers.get(i)

                Cell cell = row.createCell(i)

                Object value = rowData.get(columnName)

                cell.setCellValue(
                        value != null ? value.toString() : "")
            }
        }

        // Auto size columns
        for (int i = 0; i < headers.size(); i++) {
            sheet.autoSizeColumn(i)
        }

        fis.close()

        FileOutputStream fos = new FileOutputStream(filePath)

        workbook.write(fos)

        fos.close()

        workbook.close()

        println("Data successfully written to Excel Sheet => " + sheetName)
    }
}