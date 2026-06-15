package common

import internal.GlobalVariable

class ConfigManager {

    static String getDbUrl() {
        return GlobalVariable.DB_URL
    }

    static String getDbUser() {
        return GlobalVariable.DB_USERNAME
    }

    static String getDbPassword() {
        return GlobalVariable.DB_PASSWORD
    }

    static String getExcelPath() {
        return GlobalVariable.EXCEL_PATH
    }

    static String getExcelSheet() {
        return GlobalVariable.EXCEL_SHEET
    }
}