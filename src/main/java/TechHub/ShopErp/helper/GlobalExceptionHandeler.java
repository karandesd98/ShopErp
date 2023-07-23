package TechHub.ShopErp.helper;

import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandeler {

	@ExceptionHandler(ResorcenotFoundException.class)
	public ResponseEntity<Map<String,Object>>  notFoundHandeler(ResorcenotFoundException r)
	{
		Map map=new HashMap<>();
		map.put("msg", r.getMessage());
		map.put("success", false);
		map.put("status", HttpStatus.NOT_FOUND);
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
	}
	
}