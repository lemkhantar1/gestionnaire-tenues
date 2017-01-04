package bankelmaghrib;

import com.opensymphony.xwork2.ActionSupport;
import entities.*;
import java.util.*;

import org.apache.struts2.interceptor.SessionAware;

import model.*;


public class PostesAction extends ActionSupport implements SessionAware {
	
	PosteModel pm = new PosteModel();
	List<Poste> postes = new ArrayList<Poste>();
	Poste poste = new Poste();
	
	private Map<String,Object> session ;
	
	TenueModel tm = new TenueModel();
	List<Tenue> tenues = new ArrayList<Tenue>();
	
	
	public List<Tenue> getTenues() {
		return tenues;
	}

	public void setTenues(List<Tenue> tenues) {
		this.tenues = tenues;
	}

	private String intitule;
	private String tenue;
	
	
	
	
	public List<Poste> getPostes() {
		return postes;
	}

	public void setPostes(List<Poste> postes) {
		this.postes = postes;
	}

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public String getTenue() {
		return tenue;
	}

	public void setTenue(String tenue) {
		this.tenue = tenue;
	}

	public String execute()
	{
		if(!session.containsKey("admin"))return "logout";
		postes = pm.findAll();
		tenues  = tm.findAll();
		if(postes.size()>10)postes = new ArrayList<Poste>(postes.subList(0, 9));
		return SUCCESS;
	}
	
	public String searchPagePostes()
	{
		if(!session.containsKey("admin"))return "logout";
		postes = pm.findAll();
		return SUCCESS;
	}
	
	public String searchPoste()
	{
		if(!session.containsKey("admin"))return "logout";
		if(intitule!=null)
		{
			intitule = intitule.toUpperCase();
			tenue = tenue.toUpperCase();
			postes = new ArrayList<Poste>();
			List<Poste> postesTmpDebut = pm.findAll();
			List<Poste> postesTmp2 = new ArrayList<Poste>();
			if(!intitule.equals(""))
			{
				for(int i=0; i<postesTmpDebut.size(); i++)
				{
					if(postesTmpDebut.get(i).getIntitule().contains(intitule))
						postesTmp2.add(postesTmpDebut.get(i));
				}
			}
			else postesTmp2 = postesTmpDebut;
			
			if(!tenue.equals(""))
			{
				for(int i=0; i<postesTmp2.size(); i++)
				{
					if(postesTmp2.get(i).getTenue().getIntitule().contains(tenue))
						postes.add(postesTmp2.get(i));
				}
			}
			else postes = postesTmp2;
		}
		else
			postes = pm.findAll();
		return SUCCESS;
	}
	
	public String detailsPoste()
	{
		if(!session.containsKey("admin"))return "logout";
		poste = pm.find(intitule);
		tenues = tm.findAll();
		return SUCCESS;
	}
	
	public String updatePoste()
	{
		if(!session.containsKey("admin"))return "logout";
		if(intitule!=null)
		{
			Poste poste2 = pm.find(intitule);
			poste2.setTenue(new Tenue(tenue));
			pm.update(poste2);
		}
		return SUCCESS;
	}

	public Poste getPoste() {
		return poste;
	}

	public void setPoste(Poste poste) {
		this.poste = poste;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session = arg0;
	}

}
