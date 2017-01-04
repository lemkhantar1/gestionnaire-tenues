package bankelmaghrib;



import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

import entities.Agent;
import entities.Article;
import entities.Categorie;
import entities.Mesure;
import entities.Poste;
import entities.Tenue;
import model.AgentModel;
import model.ArticleModel;
import model.CategorieModel;
import model.MesureModel;
import model.PosteModel;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class UploadAction extends ActionSupport implements ServletRequestAware,SessionAware 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private File fileUpload;
	private String fileUploadContentType;
	private String fileUploadFileName;
	private List<Agent> agents;
	private List<MesureRow> mesuresRows;
	private Map<String, Object> session ;
	private PosteModel pm = new PosteModel();
	private MesureModel mm = new MesureModel();
	private AgentModel am = new AgentModel();
	private HttpServletRequest servletRequest;

	
	
	public List<MesureRow> getMesuresRows() {
		return mesuresRows;
	}

	public void setMesuresRows(List<MesureRow> mesuresRows) {
		this.mesuresRows = mesuresRows;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public List<Agent> getAgents() {
		return agents;
	}

	public void setAgents(List<Agent> agents) {
		this.agents = agents;
	}

	public String getFileUploadContentType() {
		return fileUploadContentType;
	}

	public void setFileUploadContentType(String fileUploadContentType) {
		this.fileUploadContentType = fileUploadContentType;
	}

	public String getFileUploadFileName() {
		return fileUploadFileName;
	}

	public void setFileUploadFileName(String fileUploadFileName) {
		this.fileUploadFileName = fileUploadFileName;
	}

	public File getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(File fileUpload) {
		this.fileUpload = fileUpload;
	}

	public String execute() {
		
		if(!session.containsKey("admin"))return "logout";
		agents = new ArrayList<Agent>();
		List<String> sexe = new ArrayList<>();sexe.add("homme");sexe.add("femme");
		List<Poste> postesTmp = pm.findAll();
		List<String> postes = new ArrayList<>();
		for(int i=0; i<postesTmp.size(); i++)
		{
			postes.add(postesTmp.get(i).getIntitule());
		}
		Agent agent = new Agent();
		Mesure mesure = new Mesure();
		int ligne=0,colonne=0;
		try
		{
			String filePath = servletRequest.getSession().getServletContext().getRealPath("/")+"upload";
		    File folder = new File(filePath);
		    if(!folder.exists()){
		    	folder.mkdir();
		    }
			System.out.println("Server path:" + filePath);
			File fileToCreate = new File(filePath, this.fileUploadFileName);
			FileUtils.copyFile(this.fileUpload, fileToCreate);
			try
	        {
	            FileInputStream file = new FileInputStream(fileToCreate);
	 
	            //Create Workbook instance holding reference to .xlsx file
	            XSSFWorkbook workbook = new XSSFWorkbook(file);
	 
	            //Get first/desired sheet from the workbook
	            XSSFSheet sheet = workbook.getSheetAt(0);
	 
	            //Iterate through each rows one by one
	            Iterator<Row> rowIterator = sheet.iterator();
	            while (rowIterator.hasNext()) 
	            {
	            	
	            	ligne++;
	                Row row = rowIterator.next();
	                //For each row, iterate through all the columns
	                Iterator<Cell> cellIterator = row.cellIterator();
	                if(ligne!=1)
                	{
	                	agent = new Agent();
	                	mesure = new Mesure("--",0);
		                while (cellIterator.hasNext()) 
		                {
		                	colonne++;
		                	System.out.println("("+ligne+" - "+colonne+")");
		                	
		                		
			                    Cell cell = cellIterator.next();
			                    if(cell.getCellType() != Cell.CELL_TYPE_STRING || cell.getStringCellValue().equals("") ) return ERROR; // erreur : l'une des cases est vides.
			                    switch(colonne)
			                    {
			                    	case 1:agent.setNom(cell.getStringCellValue());break;
			                    	case 2:agent.setPrenom(cell.getStringCellValue());break; 
			                    	case 3:if(sexe.contains(cell.getStringCellValue()))agent.setSexe(cell.getStringCellValue());else {System.out.println("ni homme ni femme !!!!!!");return ERROR;}break; // ni homme ni femme
			                    	case 4:if(postes.contains(cell.getStringCellValue().toUpperCase()))agent.setPoste(pm.find(cell.getStringCellValue().toUpperCase()));else {System.out.println("poste inconnu !!!!!!");return ERROR;}break; // poste inconnu 
			                    	case 5:agent.setEntite(cell.getStringCellValue());;break; 
			                    	default : return ERROR; // erreur : une des lignes contient plus de 5 colonnes
			                    }
		                	
		                }
		                agent.setMesure(mesure);
		                agents.add(agent);
	                }
	                if(colonne!=5 && colonne !=0) return ERROR; // erreur : une des lignes contient mois de 4 colonnes
	                colonne=0;
	            }
	            file.close();
	        } 
	        catch (Exception e) 
	        {
	            e.printStackTrace();
	        }
			FileUtils.deleteQuietly(fileToCreate);
			session.put("agents", agents);
			return SUCCESS;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			addActionError(e.getMessage());
			return ERROR;
		}


	}
	
	public String execute2() {
		
		if(!session.containsKey("admin"))return "logout";
		mesuresRows = new ArrayList<MesureRow>();
		List<String> sexe = new ArrayList<>();sexe.add("homme");sexe.add("femme");
		Agent agent = new Agent();
		int ligne=0,colonne=0;
		try
		{
			String filePath = servletRequest.getSession().getServletContext().getRealPath("/")+"upload";
		    File folder = new File(filePath);
		    if(!folder.exists()){
		    	folder.mkdir();
		    }
			System.out.println("Server path:" + filePath);
			File fileToCreate = new File(filePath, this.fileUploadFileName);
			FileUtils.copyFile(this.fileUpload, fileToCreate);
			try
	        {
	            FileInputStream file = new FileInputStream(fileToCreate);
	 
	            //Create Workbook instance holding reference to .xlsx file
	            XSSFWorkbook workbook = new XSSFWorkbook(file);
	 
	            //Get first/desired sheet from the workbook
	            XSSFSheet sheet = workbook.getSheetAt(0);
	 
	            //Iterate through each rows one by one
	            Iterator<Row> rowIterator = sheet.iterator();
	            while (rowIterator.hasNext()) 
	            {
	            	
	            	ligne++;
	                Row row = rowIterator.next();
	                //For each row, iterate through all the columns
	                Iterator<Cell> cellIterator = row.cellIterator();
	                if(ligne!=1)
                	{
	                	MesureRow mesureRow = new MesureRow();
		                while (cellIterator.hasNext()) 
		                {
		                	colonne++;
		                	System.out.println("("+ligne+" - "+colonne+")");
		                	
		                		
			                    Cell cell = cellIterator.next();
			                    if(cell.getCellType() == Cell.CELL_TYPE_STRING && cell.getStringCellValue().equals("") ) return ERROR; // erreur : l'une des cases est vides.
			                    switch(colonne)
			                    {
			                    	case 1:
			                    		mesureRow.setId((int) cell.getNumericCellValue());
			                    		agent = am.find(mesureRow.getId());
			                    		if(agent==null) return ERROR; // id inexsistant
			                    		break;
			                    	case 2:
			                    		mesureRow.setNom(cell.getStringCellValue());
			                    		if(!mesureRow.getNom().equals(agent.getNom())) return ERROR; //nom inexsistant
			                    		break; 
			                    	case 3:
			                    		mesureRow.setPrenom(cell.getStringCellValue());
			                    		if(!mesureRow.getPrenom().equals(agent.getPrenom())) return ERROR; //prenom inexsistant
			                    		break; 		
			                    	case 4:
			                    		mesureRow.setSexe(cell.getStringCellValue());
			                    		if(!mesureRow.getSexe().equals(agent.getSexe())) return ERROR; //sexe inexsistant
			                    		break; 	
			                    	case 5:
			                    		mesureRow.setTaille(cell.getStringCellValue());
			                    		break; 	
			                    	case 6:
			                    		mesureRow.setPointure((int)cell.getNumericCellValue());
			                    		break;
			                    		
		                    		default : return ERROR; // erreur : une des lignes contient plus de 5 colonnes
			                    }
		                	
		                }
		                mesuresRows.add(mesureRow);
	                }
	                if(colonne!=6 && colonne !=0) return ERROR; // erreur : une des lignes contient mois de 4 colonnes
	                colonne=0;
	            }
	            file.close();
	        } 
	        catch (Exception e) 
	        {
	        	e.printStackTrace();
	        	return ERROR;
	            
	        }
			FileUtils.deleteQuietly(fileToCreate);
			session.put("mesures", mesuresRows);
			return SUCCESS;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			addActionError(e.getMessage());
			return ERROR;
		}


	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.servletRequest = arg0;
	}

	@Override
	public void setSession(java.util.Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session = arg0;
	}
	


}
