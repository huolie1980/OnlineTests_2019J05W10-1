package com.acquisio.app.controller;

import com.acquisio.app.domain.Employee;
import com.acquisio.app.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.MessageDigest;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @RequestMapping("/employees")
    @CrossOrigin(origins = "http://localhost:4200")
    public Collection<Employee> listEmployees() throws SQLException {
    	//Convert the email to he URL of the gravatar avatar
    	List<Employee> list = employeeRepository.findAll();
    	list.stream().forEach(employee ->{
    		employee.setEmail(getGravatar(employee.getEmail()));
    	});
        return list;
    }
    /**
	 * 
		Get gravatar avatar according to email
	 * @param email
	 * @return
	 */
    private String getGravatar(String email) {
    	String emailMd5 = emailToMd5(email);
    	//Set image size 32px
    	String avatar = "https://s.gravatar.com/avatar/"+emailMd5+"?s=32";
        return avatar;
    }
    /**
	 * 
		Convert email to md5
	 * @param message
	 * @return
	 */
    private String emailToMd5(String message) {
		String temp = "";
		try{
			MessageDigest md5Digest = MessageDigest.getInstance("MD5");
			byte[] encodeMd5Digest = md5Digest.digest(message.getBytes());
			temp = convertByteToHexString(encodeMd5Digest);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return temp;
    }

	private String convertByteToHexString(byte[] bytes){
		String result = "";
		for(int i=0;i<bytes.length;i++){
			int temp = bytes[i]&0xff;
			String tempHex = Integer.toHexString(temp);
			if(tempHex.length()<2){
				result +="0"+tempHex;
			}else{
				result +=tempHex;
			}
		}
		return result;
	}
}
