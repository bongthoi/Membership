package vn.vmall.Controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonParseException;

import vn.vmall.DAL.ContactDAL;
import vn.vmall.Entity.ItemContact;



@RestController
@RequestMapping(value="ContactController")
public class ContactController {
	ObjectMapper mapper = new ObjectMapper();
	@RequestMapping(value="send_contact",method=RequestMethod.POST)
	public String send_contact(@RequestParam("str") String str,
			HttpServletRequest request,
			HttpServletResponse response) throws com.fasterxml.jackson.core.JsonParseException, JsonMappingException, IOException{
		ItemContact item = new ItemContact();
		item = mapper.readValue(str, ItemContact.class);		
		int result = ContactDAL.add_contact_dao(item.getEmail(), item.getFullname(), item.getPhone(),item.getTitle(),item.getContent());
		return String.valueOf(result);		
	}
}
