package TechHub.ShopErp.helper;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {

	//public final... String UPLOAD_DIR="C:\\Users\\Pratik\\Desktop\\SHOPERP\\ShopErpNewFolder\\ShopErp\\src\\main\\resources\\static\\uploadedFiles";
	// public final String UPLOAD_DIR= new ClassPathResource("static/uploadedFiles/").getFile().getAbsolutePath();
	public FileUploadHelper()throws Exception{
	}
	
	public boolean uploadFile(MultipartFile file) {
		boolean f=false;
		try {
		String	fileName=file.getOriginalFilename();
		File uploadfile=	new ClassPathResource("static/uploadedFiles").getFile();
		Path  path	=Paths.get(uploadfile.getAbsolutePath()+File.separator+fileName);
		Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		
		f=true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
}
