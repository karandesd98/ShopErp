package TechHub.ShopErp.helper;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {

	//public final... String UPLOAD_DIR="C:\\Users\\Pratik\\Desktop\\SHOPERP\\ShopErpNewFolder\\ShopErp\\src\\main\\resources\\static\\uploadedFiles";
	public final String UPLOAD_DIR= new ClassPathResource("static/uploadedFiles/").getFile().getAbsolutePath();
	public FileUploadHelper()throws Exception{
	}
	
	public boolean uploadFile(MultipartFile file) {
		boolean f=false;
		try {
			//System.out.println(UPLOAD_DIR);
			Files.copy(file.getInputStream(),Paths.get( UPLOAD_DIR+"\\"+file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
		f=true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
}
