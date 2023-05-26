package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import javax.servlet.ServletContext;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.productManagement.demo.entity.Images;

public class CommonMethods {

	@Autowired
	static ServletContext context;
	



	public static String generateRandomNumber() {
		int randomNumber = (int)(Math.random()*99999)+10000;
		String number = String.valueOf(randomNumber);
		return number;
	}
	
	
	   /* method for getting bs64image */
	public static String getBase64Image(String imageName) {
		String encodedBase64 = null;
		String extension = null;
		try {
		    File file = new File((Constants.PATH+imageName));
		    extension = FilenameUtils.getExtension(imageName);
			InputStream is = new FileInputStream(file);
			byte[] bytes = new byte[(int)file.length()];
			is.read(bytes);
			encodedBase64 = Base64.getEncoder().encodeToString(bytes);
			is.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return ("data:image/image/jpeg;base64,"+encodedBase64);
	}

	public static String getContentType(String imageName) {
	if (imageName.contains(".pdf"))
		return "application/pdf";
	else if (imageName.contains(".dwg"))
		return "imsage/vnd.dwg";
	else if (imageName.contains(".gif"))
		return "image/gif";
	else
		return "image/jpeg";
}

	


}


