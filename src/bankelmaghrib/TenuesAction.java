package bankelmaghrib;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import entities.*;

import com.opensymphony.xwork2.ActionSupport;

import model.ArticleModel;
import model.CategorieModel;
import model.PosteModel;
import model.TenueArticleModel;
import model.TenueModel;

public class TenuesAction  extends ActionSupport implements SessionAware  {
	
	private TenueModel tm = new TenueModel();
	private List<Tenue> tenues = new ArrayList<>();
	
	private TenueArticleModel tam = new TenueArticleModel();
	private List<TenueArticle> relations = new ArrayList<>();
	
	private ArticleModel am = new ArticleModel();
	private List<Article> articles = new ArrayList<Article>();
	
	private CategorieModel cm = new CategorieModel();
	private List<Categorie> categories = new ArrayList<Categorie>();
	
	private PosteModel pm = new PosteModel();
	private String poste;
	
	private String intitule;
	private Map<String,Object> session ;
	
	
	
	
	public Map<String, Object> getSession() {
		return session;
	}



	public List<Categorie> getCategories() {
		return categories;
	}



	public void setCategories(List<Categorie> categories) {
		this.categories = categories;
	}



	public List<Article> getArticles() {
		return articles;
	}



	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}



	public List<TenueArticle> getRelations() {
		return relations;
	}



	public void setRelations(List<TenueArticle> relations) {
		this.relations = relations;
	}
	public String getIntitule() {
		return intitule;
	}



	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}



	public List<Tenue> getTenues() {
		return tenues;
	}



	public void setTenues(List<Tenue> tenues) {
		this.tenues = tenues;
	}


	@Override
	public String execute() throws Exception {
		tenues = tm.findAll();
		System.out.println(tenues.size());
		return SUCCESS;
	}
	
	public String details()
	{
		if(!session.containsKey("admin"))return "logout";
		articles  = am.findAll();
		if(intitule==null) {intitule = (String) session.get("intitule");}
		else {System.out.println("input : "+intitule);session.put("intitule", intitule);}

			categories = cm.findAll();
			relations = new ArrayList<>();
			List<TenueArticle> relationsTmp = new ArrayList<>();
			relationsTmp = tam.findAll();
			for(int i=0; i<relationsTmp.size(); i++)
			{
				if(relationsTmp.get(i).getId().getIdTenue().equals(intitule))
					relations.add(relationsTmp.get(i));
			}

		return SUCCESS;
	}
	
	public String detailsTenuePoste()
	{
		if(!session.containsKey("admin"))return "logout";
		Poste p = pm.find(poste);
		intitule=p.getTenue().getIntitule();
		articles  = am.findAll();
		categories = cm.findAll();
		relations = new ArrayList<>();
		List<TenueArticle> relationsTmp =  tam.findAll();
		for(int i=0; i<relationsTmp.size(); i++)
		{
			if(relationsTmp.get(i).getId().getIdTenue().equals(intitule))
				relations.add(relationsTmp.get(i));
		}

		return SUCCESS;
	}



	public String getPoste() {
		return poste;
	}



	public void setPoste(String poste) {
		this.poste = poste;
	}



	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session = arg0;
		
	}

}
