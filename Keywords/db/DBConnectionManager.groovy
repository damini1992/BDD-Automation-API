package db

import java.sql.Connection
import java.sql.DriverManager

import com.kms.katalon.util.CryptoUtil

import common.ConfigManager


class DBConnectionManager {

    private static Connection connection = null

    static Connection getConnection() {

        try {

            if (connection != null && !connection.isClosed()) {
                return connection
            }

            String url = ConfigManager.getDbUrl()
            String user = ConfigManager.getDbUser()
//            String password = SecurityUtil.decrypt(ConfigManager.getDbPassword())
			String password = ConfigManager.getDbPassword()
//			String decryptedPwd = EncryptionUtil.decrypt(encryptedPwd)/
			
			
			def decryptedText = (CryptoUtil.decode(CryptoUtil.getDefault(password)))
		
			

			

            Class.forName("com.mysql.cj.jdbc.Driver")

            connection = DriverManager.getConnection(url, user, decryptedText)

            println("✅ DB Connected Successfully")

        } catch (Exception e) {
            throw new RuntimeException("DB Connection Failed: " + e.getMessage())
        }

        return connection
    }

    static void closeConnection() {
        if (connection != null) {
            connection.close()
            connection = null
        }
    }
}