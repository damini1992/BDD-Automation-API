package excel

import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook

class ExcelManager {

    private static Workbook workbook
    private static Sheet sheet

    static void openWorkbook(String filePath, String sheetName) {

        FileInputStream fis = new FileInputStream(filePath)
        workbook = new XSSFWorkbook(fis)
        sheet = workbook.getSheet(sheetName)

        println("Excel Workbook Loaded => " + filePath)
    }

	/*static String getQuery(String queryName) {

        if (sheet == null) {
            throw new Exception("Excel not initialized")
        }

        int rows = sheet.getLastRowNum()

        for (int i = 1; i <= rows; i++) {

            Row row = sheet.getRow(i)
            if (row == null) continue

            String name = row.getCell(1)?.toString()

            if (name != null && name.equalsIgnoreCase(queryName)) {
                return row.getCell(2)?.toString()
            }
        }

        throw new Exception("Query not found in Excel: " + queryName)
    }*/
	
	static String getQuery(String queryName) {
		
			if (sheet == null) {
				throw new Exception("Excel not initialized")
			}
		
			int rows = sheet.getLastRowNum()
		
			for (int i = 1; i <= rows; i++) {
		
				Row row = sheet.getRow(i)
				if (row == null) continue
		
				// Column A = QueryName
				String name = row.getCell(0)?.toString()?.trim()
		
				if (name != null && name.equalsIgnoreCase(queryName)) {
		
					// Column B = Query
					return row.getCell(1)?.toString()?.trim()
				}
			}
		
			throw new Exception("Query not found in Excel: " + queryName)
		}

    static void closeWorkbook() {

        if (workbook != null) {
            workbook.close()
        }
    }
}