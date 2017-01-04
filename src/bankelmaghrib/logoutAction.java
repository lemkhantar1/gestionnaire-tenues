package bankelmaghrib;

import java.util.Map;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class logoutAction extends ActionSupport implements SessionAware   {
	
	private Map<String,Object> session ;

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
		
	}
	
	@Override
	public String execute() throws Exception {
		session.remove("admin");
		return SUCCESS;
	}

}
