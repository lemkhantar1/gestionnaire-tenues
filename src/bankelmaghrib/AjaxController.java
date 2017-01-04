package bankelmaghrib;

import java.io.IOException;
import java.io.PrintWriter;
import com.opensymphony.xwork2.ActionSupport;
import entities.*;
import java.util.*;
import model.*;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;


public class AjaxController  extends ActionSupport implements SessionAware  {
	
	private String nom;
	private String prenom;
	private String sexe;
	private String poste;
	private String entite;
	private String taille;
	private Integer pointure;
	private Integer idAgent;
	private String idT;
	private String idA;
	private Integer quantite;
	private String tenue;
	private String article;
	
	private AgentModel am = new AgentModel();
	private ArticleModel articlemodel = new ArticleModel();
	private PosteModel pm = new PosteModel();
	private MesureModel mm = new MesureModel();
	private TenueModel tm = new TenueModel();
	private TenueArticleModel tam = new TenueArticleModel();
	private Map<String,Object> session ;

	

	
	
	public Integer getQuantite() {
		return quantite;
	}

	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}

	public String getTenue() {
		return tenue;
	}

	public void setTenue(String tenue) {
		this.tenue = tenue;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public String getIdT() {
		return idT;
	}

	public void setIdT(String idT) {
		this.idT = idT;
	}

	public String getIdA() {
		return idA;
	}

	public void setIdA(String idA) {
		this.idA = idA;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public String getEntite() {
		return entite;
	}

	public void setEntite(String entite) {
		this.entite = entite;
	}

	public Integer getPointure() {
		return pointure;
	}

	public void setPointure(Integer pointure) {
		this.pointure = pointure;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getTaille() {
		return taille;
	}

	public void setTaille(String taille) {
		this.taille = taille;
	}

	public String getPoste() {
		return poste;
	}

	public void setPoste(String poste) {
		this.poste = poste;
	}


	
	public int getIdAgent() {
		return idAgent;
	}

	public void setIdAgent(Integer idAgent) {
		this.idAgent = idAgent;
	}

	
	
	public void deleteAgent()
	{	
		
		try {
			Mesure mesure  = am.find(idAgent).getMesure();
			am.delete(am.find(idAgent));
			mm.delete(mesure);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter out;
			out = response.getWriter();
			out.println("OK");
			out.flush();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deletePoste()
	{
		try {
			List<Agent> agents = am.findAll();
			for(int i=0; i<agents.size(); i++)
			{
				if(agents.get(i).getPoste().getIntitule().equals(poste))
				{
					HttpServletResponse response = ServletActionContext.getResponse();
					response.setContentType("text/plain;charset=utf-8");
					PrintWriter out;
					out = response.getWriter();
					out.println("NONOK");
					out.flush();
					return;
				}
				
			}
			pm.delete(pm.find(poste));
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter out;
			out = response.getWriter();
			out.println("OK");
			out.flush();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteRelation()
	{
		try {
			tam.delete(tam.find(new TenueArticleId(idA, idT)));
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter out;
			out = response.getWriter();
			out.println("OK");
			out.flush();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void incrementerQuantite()
	{
		try {
			TenueArticle rel = tam.find(new TenueArticleId(idA, idT));
			rel.setQuantite(rel.getQuantite()+1);
			tam.update(rel);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter out;
			out = response.getWriter();
			out.println("OK");
			out.flush();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void decrementerQuantite()
	{
		try {
			TenueArticle rel = tam.find(new TenueArticleId(idA, idT));
			if(rel.getQuantite()>0)
			{
				rel.setQuantite(rel.getQuantite()-1);
				tam.update(rel);
			}

			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter out;
			out = response.getWriter();
			out.println("OK");
			out.flush();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addAgent()
	{
		try {
			Mesure mesure = new Mesure("--",0);
			if(taille!="--" || pointure != 0) mesure = new Mesure(taille, pointure);
			mm.create(mesure);
			am.create(new Agent(pm.find(poste),mm.find(mesure.getId()),nom,prenom,entite,sexe));
			System.out.println("Mesure id : "+mesure.getId());
			System.out.println("Mesure id : "+mesure.getTaille());
			System.out.println("Mesure id : "+mesure.getPointure());
				
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter out;
			out = response.getWriter();
			out.println("OK");
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addRelation()
	{
		try {
			if(tam.find(new TenueArticleId(article, tenue))!=null)
			{
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("text/plain;charset=utf-8");
				PrintWriter out;
				out = response.getWriter();
				out.println("NONOK");
				out.flush();
			}
			else
			{
				tam.create(new TenueArticle(new TenueArticleId(article, tenue),articlemodel.find(article),tm.find(tenue),quantite));
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("text/plain;charset=utf-8");
				PrintWriter out;
				out = response.getWriter();
				out.println("OK");
				out.flush();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addPoste() throws IOException
	{
		if(pm.find(poste)!=null)
		{
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter out;
			out = response.getWriter();
			out.println("NONOK");
			out.flush();
		}
		else
		{
			pm.create(new Poste(poste, new Tenue(tenue)));
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter out;
			out = response.getWriter();
			out.println("OK");
			out.flush();
		}
		
		
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session = arg0;
		
	}

}
