package bankelmaghrib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import entities.*;
import freemarker.template.TemplateNumberModel;
import model.*;
import sun.management.resources.agent;

public class demandeAction  extends ActionSupport  implements ServletRequestAware, SessionAware {
	
	
	private HttpServletRequest servletRequest;
	private Map<String,Object> session ;
	FileInputStream fileInputStream;


	public FileInputStream getFileInputStream() {
		return fileInputStream;
	}
	public void setFileInputStream(FileInputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}
	public Hashtable<String, Integer> getQperAgent() {
		return QperAgent;
	}
	public void setQperAgent(Hashtable<String, Integer> qperAgent) {
		QperAgent = qperAgent;
	}
	public HttpServletRequest getServletRequest() {
		return servletRequest;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	Hashtable<String, Hashtable<String, Integer>> QperArticle;
	Hashtable<String, Integer> QperAgent;
	
	public Hashtable<String, Hashtable<String, Integer>> getQperArticle() {
		return QperArticle;
	}
	public void setQperArticle(Hashtable<String, Hashtable<String, Integer>> qperArticle) {
		QperArticle = qperArticle;
	}
	
	
	public String execute()
	{
		if(!session.containsKey("admin"))return "logout";
		Vector<String> chaussures = new Vector<String>(Arrays.asList("ESPADRILLES","PAIRE DE SOQUETTES","SANDALES", "CHAUSSURE CLASSIQUE", "SABOTS"));
		ArticleModel articleModel = new ArticleModel();
		List<Article> articles = articleModel.findAll();
		
		AgentModel agentModel = new AgentModel();
		List<Agent> agents = agentModel.findAll();
		
		QperArticle = new Hashtable<String, Hashtable<String, Integer>>();
		
		for(int i=0; i<articles.size(); i++)
		{
			Article article = articles.get(i);
			QperAgent = new Hashtable<>();
			for(int k=0; k<agents.size(); k++)
			{
				Agent angent = agents.get(k);
				Hashtable<String, Integer> articlesDuAgent = getArticlesAgent(angent);
				if(articlesDuAgent.containsKey(article.getIntitule()))
				{
					Integer nouvelleQuantite = articlesDuAgent.get(article.getIntitule()); 
					String taille;
					if(chaussures.contains(article.getIntitule()))
						taille = Integer.toString(angent.getMesure().getPointure());
					else
						taille = angent.getMesure().getTaille();
					if(QperAgent.containsKey(taille))
					{
						Integer ancienneQuantite = QperAgent.get(taille);
						QperAgent.put(taille, ancienneQuantite+nouvelleQuantite);
					}
					else
					{
						QperAgent.put(taille, nouvelleQuantite);
					}
				}
			}
			if(QperAgent.size()>0)
			QperArticle.put(article.getIntitule(), QperAgent);
		}
		System.out.println(QperArticle);
		session.put("commande", QperArticle);
		return SUCCESS;
	}
	
	public Hashtable<String, Integer> getArticlesAgent(Agent agent)
	{
		
		PosteModel posteModel = new PosteModel();
		String tenue = posteModel.find(agent.getPoste().getIntitule()).getTenue().getIntitule();
		
		TenueArticleModel tenueArticleModel = new TenueArticleModel();
		List<TenueArticle> relations = tenueArticleModel.findAll();
		
		Hashtable<String, Integer> ArticlePerQuantite = new Hashtable<>();
		for(int i=0; i<relations.size(); i++)
		{
			TenueArticle relation = relations.get(i);
			if(tenue.equals(relation.getTenue().getIntitule()))
			{
				ArticlePerQuantite.put(relation.getArticle().getIntitule(), relation.getQuantite());
			}
		}
		return ArticlePerQuantite;
	}

	public String downloadDemande() throws FileNotFoundException
	{
		if(!session.containsKey("admin"))return "logout";
		File file;
		String filePath = servletRequest.getSession().getServletContext().getRealPath("/")+"upload";
	    File folder = new File(filePath);
	    if(!folder.exists()){
	    	folder.mkdir();
	    }
		file = new File(filePath, "COMMANDE_ACHAT.XLSX.xlsx");
		
		
		//Blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook(); 
         
        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("COMMANDE_ACHAT");
        
        XSSFCellStyle myStyle = workbook.createCellStyle();           

          
        //This data needs to be written (Object[])
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        data.put("1", new Object[] {"Article", "Taille/Pointure", "Quantite"});
        Hashtable<String, Hashtable<String, Integer>> commande = (Hashtable<String, Hashtable<String, Integer>>) session.get("commande");
        Set<String> articles = commande.keySet();
        int i=10000;
        for(String key1: articles)
        {
        	data.put(i+"", new Object[] {key1,"----","----"});
        	i++;
        	Set<String> tailles = commande.get(key1).keySet();
        	for(String key2: tailles)
        	{
        		data.put(i+"", new Object[] {"",key2,commande.get(key1).get(key2)});
            	i++;
        	}
        }
          
        //Iterate over data and write to sheet
        Set<String> keyset = data.keySet();
        int rownum = 0;
        for (String key : keyset)
        {
            Row row = sheet.createRow(rownum++);
            if(rownum==0)row.setRowStyle(myStyle);
            Object [] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr)
            {
               Cell cell = row.createCell(cellnum++);
               if(obj instanceof String)
                    cell.setCellValue((String)obj);
                else if(obj instanceof Integer)
                    cell.setCellValue((Integer)obj);
            }
        }
        try
        {
            //Write the workbook in file system
        	FileOutputStream out = new FileOutputStream(file);
            workbook.write(out);
            out.close();
            
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        fileInputStream = new FileInputStream(file);
		return SUCCESS;
	}
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		
		this.servletRequest = arg0;
		
	}
	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session = arg0;
		
	}
	
	

}
