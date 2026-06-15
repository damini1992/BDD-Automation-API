package excelutils

import java.text.SimpleDateFormat

import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import com.kms.katalon.core.annotation.Keyword

public class ExcelReader {


	@Keyword
	def updateMultipleColumns(String filePath, String testCaseName, String status, String errorMessage) {

		FileInputStream fis = new FileInputStream(filePath)
		Workbook workbook = new XSSFWorkbook(fis)
		Sheet sheet = workbook.getSheetAt(0)
		
		
		Row headerRow = sheet.getRow(0)
	
		int statusCol = -1
	

		String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())

		// ✅ Get header row


		println "Status column index Before = " + statusCol


		// ✅ Find column indexes dynamically
		for (int i = 0; i < headerRow.getLastCellNum(); i++) {

			
			String header = headerRow.getCell(i)?.toString()?.trim()
	
			if (header.equalsIgnoreCase("Status")) {
				statusCol = i
			}
	
		}
		
		
		println "Status column index After = " + statusCol
	
		// ✅ Loop rows
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
					
				Row row = sheet.getRow(i)
				if (row == null) continue
		
				Cell tcCell = row.getCell(0)
		
				println "Checking row value: " + tcCell + " vs " + testCaseName
		
				if (tcCell != null &&
					tcCell.toString().trim().equalsIgnoreCase(testCaseName.trim())) {
		
					println "✅ Match found at row: " + i
		
					Cell statusCell = row.getCell(statusCol)
					if (statusCell == null) statusCell = row.createCell(statusCol)
		
					statusCell.setCellValue(status.toUpperCase())
		
					break
				}
		

		}

		fis.close()

		// ✅ ✅ VERY IMPORTANT (SAVE FILE)
		FileOutputStream fos = new FileOutputStream(filePath)
		workbook.write(fos)

		fos.close()
		workbook.close()

		println "✅ Excel updated successfully"
	}
}
