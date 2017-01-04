package bankelmaghrib;

import com.opensymphony.xwork2.ActionSupport;
import entities.*;
import java.util.*;

import org.apache.struts2.interceptor.SessionAware;

import model.*;
import sun.security.jca.GetInstance;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginAction extends ActionSupport  implements SessionAware {
	
	
	private Admin admin = new Admin();
	private AdminModel am = new AdminModel();
	private String password;
	private Map<String,Object> session ;

	public String execute() throws NoSuchAlgorithmException {
		
		this.admin = this.am.find(1);
	    MessageDigest m=MessageDigest.getInstance("MD5");
	    m.update(password.getBytes(),0,password.length());
	    String mdp = "0"+new BigInteger(1,m.digest()).toString(16);
		if(mdp.equals(this.admin.getPassword()))
		{
			session.put("admin", "true");
			return SUCCESS;
		}
		else
		{
			addActionError(getText("error.login"));
			return INPUT;
		}
			
	}

	
	public Admin getAdmin() {
		return admin;
	}


	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session = arg0;
		
	}
	

}