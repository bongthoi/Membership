package vn.vmall.Helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class UploadFPTP {
	public static String FLD_SLIDE="slide";
	public static String FLD_PRODUCT="product";
	public static String FLD_CKEDITOR="ckeditor";
	public static String FLD_ULP="upload";
	public static void main(String[] args) {
		uploadFTP("C:/Users/Public/Pictures/Sample Pictures/Chrysanthemum.jpg","aaa.jpg",FLD_SLIDE);
	}
	public static boolean uploadFTP(String file_path,String file_name,String folder) {
		String server = "103.3.249.101";
		int port = 21;
		String user = "ServerChat2";
		String pass = "Ch@uPDQ$$";
		FTPClient ftpClient = new FTPClient();
		try {

			ftpClient.connect(server, port);
			if(ftpClient.login(user, pass)==false){
				System.out.println("FAIL");
				return false;
			}
			ftpClient.enterLocalPassiveMode();

			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			ftpClient.changeWorkingDirectory(FLD_ULP+"/"+folder);
			File firstLocalFile = new File(file_path);
			String firstRemoteFile = file_name;
			InputStream inputStream = new FileInputStream(firstLocalFile);			
			boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
			inputStream.close();
			if (ftpClient.isConnected()) {
				ftpClient.logout();
				ftpClient.disconnect();
			}
			if (done) {
				return true;
			}
			else{
				return false;
			}
		} catch (IOException ex) {
			System.out.println("Error: " + ex.getMessage());
			ex.printStackTrace();
			
		}
		return false;
	}
}
