package bankelmaghrib;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import entities.Article;
import entities.Poste;
import model.ArticleModel;

public class ArticlesAction  extends ActionSupport implements SessionAware {
	
	private ArticleModel am = new ArticleModel();
	private List<Article> articles = new ArrayList<Article>();
	
	private String intitule;
	private String categorie;
	private Map<String,Object> session ;
	
	@Override
	public String execute() {
		articles = am.findAll();
		return SUCCESS;
	}
	
	public String searchArticle()
	{
		
		if(!session.containsKey("admin"))return "logout";
		if(intitule!=null)
		{
			intitule = intitule.toUpperCase();
			categorie = categorie.toUpperCase();
			articles = new ArrayList<Article>();
			List<Article> articlesTmpDebut = am.findAll();
			List<Article> articlesTmp2 = new ArrayList<Article>();
			if(!intitule.equals(""))
			{
				for(int i=0; i<articlesTmpDebut.size(); i++)
				{
					if(articlesTmpDebut.get(i).getIntitule().contains(intitule))
						articlesTmp2.add(articlesTmpDebut.get(i));
				}
			}
			else articlesTmp2 = articlesTmpDebut;
			
			if(!categorie.equals(""))
			{
				for(int i=0; i<articlesTmp2.size(); i++)
				{
					if(articlesTmp2.get(i).getCategorie().getIntitule().contains(categorie))
						articles.add(articlesTmp2.get(i));
				}
			}
			else articles = articlesTmp2;
		}
		else
			articles = am.findAll();
		return SUCCESS;
	}
	
	public List<Article> getArticles() {
		return articles;
	}
	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
	public String getIntitule() {
		return intitule;
	}
	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session = arg0;
		
	}
	
	
	
	

}
