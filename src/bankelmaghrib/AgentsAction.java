package bankelmaghrib;

import com.opensymphony.xwork2.ActionSupport;
import entities.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import model.*;

public class AgentsAction extends ActionSupport implements ServletRequestAware ,SessionAware  {
	
	
	private AgentModel am = new AgentModel();
	private List<Agent> agents = new ArrayList<Agent>();
	
	private PosteModel pm = new PosteModel();
	private List<Poste> postes = new ArrayList<Poste>();
	
	MesureModel mm = new MesureModel();
	
	private HttpServletRequest servletRequest;
	
	private Map<String,Object> session ;
	
	FileInputStream fileInputStream;
	
	private Integer idAgent;
	private String nomAgent;
	private String prenomAgent;
	private String posteAgent;
	private String sexeAgent;
	private String entiteAgent;
	private Integer pointureAgent;
	private String tailleAgent;
	


	private Agent agent;
	
	
	
	public FileInputStream getFileInputStream() {
		return fileInputStream;
	}
	public void setFileInputStream(FileInputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}
	public Integer getPointureAgent() {
		return pointureAgent;
	}
	public void setPointureAgent(Integer pointureAgent) {
		this.pointureAgent = pointureAgent;
	}
	public String getTailleAgent() {
		return tailleAgent;
	}
	public void setTailleAgent(String tailleAgent) {
		this.tailleAgent = tailleAgent;
	}
	public String getSexeAgent() {
		return sexeAgent;
	}
	public void setSexeAgent(String sexeAgent) {
		this.sexeAgent = sexeAgent;
	}
	public String getEntiteAgent() {
		return entiteAgent;
	}
	public void setEntiteAgent(String entiteAgent) {
		this.entiteAgent = entiteAgent;
	}
	public Agent getAgent() {
		return agent;
	}
	public void setAgent(Agent agent) {
		this.agent = agent;
	}
	public Integer getIdAgent() {
		return idAgent;
	}
	public void setIdAgent(Integer idAgent) {
		this.idAgent = idAgent;
	}
	public String getNomAgent() {
		return nomAgent;
	}
	public void setNomAgent(String nomAgent) {
		this.nomAgent = nomAgent;
	}
	public String getPrenomAgent() {
		return prenomAgent;
	}
	public void setPrenomAgent(String prenomAgent) {
		this.prenomAgent = prenomAgent;
	}
	public String getPosteAgent() {
		return posteAgent;
	}
	public void setPosteAgent(String posteAgent) {
		this.posteAgent = posteAgent;
	}
	@Override
	public String execute() {
		if(!session.containsKey("admin"))return "logout";
		postes = pm.findAll();
		agents = am.findAll();
		if(agents.size()>10)agents = new ArrayList<Agent>(agents.subList(0, 9));
		return SUCCESS;
	}

	public List<Poste> getPostes() {
		return postes;
	}
	public void setPostes(List<Poste> postes) {
		this.postes = postes;
	}
	public List<Agent> getAgents() {
		return agents;
	}
	public void setAgents(List<Agent> agents) {
		this.agents = agents;
	}
	
	public String validateAgents()
	{
		if(!session.containsKey("admin"))return "logout";
		if(session.containsKey("agents"))
		{
			Mesure mesure = new Mesure();
			List<Agent> agents2 = (List<Agent>) session.get("agents");
			for(int i=0; i<agents2.size(); i++)
			{
				mesure = agents2.get(i).getMesure();
				mm.create(mesure);
				agents2.get(i).setMesure(mesure);
				am.create(agents2.get(i));
			}
			session.remove("agents");
		}
		return SUCCESS;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session = arg0;
		
	}
	
	public String updateAgent() {

		if(!session.containsKey("admin"))return "logout";
		if(nomAgent!=null)
		{
			
			Agent agent2 = am.find(this.idAgent);
			
			
			Mesure mesure2 = mm.find(agent2.getMesure().getId());
			mesure2.setPointure(pointureAgent);
			mesure2.setTaille(tailleAgent);
			mm.update(mesure2);
			
			
			agent2.setNom(this.nomAgent);
			agent2.setPrenom(this.prenomAgent);
			agent2.setPoste(pm.find(posteAgent));
			agent2.setSexe(sexeAgent);
			agent2.setEntite(entiteAgent);
			agent2.setMesure(mm.find(mesure2.getId()));
			am.update(agent2);
		}
		return SUCCESS;
	}
	

	public String detailsAgent()
	{
		if(!session.containsKey("admin"))return "logout";
		this.agent = am.find(this.idAgent);
		postes = pm.findAll();
		return SUCCESS;
	}
	
	public String searchPage()
	{
		if(!session.containsKey("admin"))return "logout";
		postes = pm.findAll();
		agents = am.findAll();
		return SUCCESS;
	}
	
	public String prepareFile() throws FileNotFoundException
	{
		if(!session.containsKey("admin"))return "logout";
		File file;
		String filePath = servletRequest.getSession().getServletContext().getRealPath("/")+"upload";
	    File folder = new File(filePath);
	    if(!folder.exists()){
	    	folder.mkdir();
	    }
		file = new File(filePath, "AGENTS.xlsx");
		
		
		//Blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook(); 
         
        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("AGENTS");
        
        XSSFCellStyle myStyle = workbook.createCellStyle();           

          
        //This data needs to be written (Object[])
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        data.put("1", new Object[] {"ID", "nom", "prenom", "sexe","poste","entite", "taille", "pointure"});
        List<Agent> agents = am.findAll();
        Agent a;
        for(int i=0;i<agents.size(); i++)
        {
        	a = agents.get(i);
        	data.put((i+2)+"", new Object[] {a.getId(), a.getNom(), a.getPrenom(), a.getSexe(), a.getPoste().getIntitule(),a.getEntite(), a.getMesure().getTaille(),  a.getMesure().getPointure()});
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
	
	public String searchAgent()
	{
		if(!session.containsKey("admin"))return "logout";
		postes = pm.findAll();
		if(nomAgent!=null)
		{
			agents = new ArrayList<Agent>();
			List<Agent> agentsTmpDebut = am.findAll();
			List<Agent> agentsTmp1 = new ArrayList<Agent>();
			List<Agent> agentsTmp2 = new ArrayList<Agent>();
			List<Agent> agentsTmp3 = new ArrayList<Agent>();
			List<Agent> agentsTmp4 = new ArrayList<Agent>();
			if(!nomAgent.equals(""))
			{
				for(int i=0; i<agentsTmpDebut.size(); i++)
				{
					if(agentsTmpDebut.get(i).getNom().contains(nomAgent))
						agentsTmp1.add(agentsTmpDebut.get(i));
				}
			}
			else
			{
				agentsTmp1 = agentsTmpDebut;
			}
			if(!prenomAgent.equals(""))
			{
				for(int i=0; i<agentsTmp1.size(); i++)
				{
					if(agentsTmp1.get(i).getPrenom().contains(prenomAgent))
						agentsTmp2.add(agentsTmp1.get(i));
				}
			}
			else
			{
				agentsTmp2 = agentsTmp1;
			}
			
			if(!sexeAgent.equals(""))
			{
				for(int i=0; i<agentsTmp2.size(); i++)
				{
					if(agentsTmp2.get(i).getSexe().equals(sexeAgent))
						agentsTmp3.add(agentsTmp2.get(i));
				}
			}
			else
			{
				agentsTmp3 = agentsTmp2;
			}
			if(!posteAgent.equals(""))
			{
				for(int i=0; i<agentsTmp3.size(); i++)
				{
					if(agentsTmp3.get(i).getPoste().getIntitule().equals(posteAgent))
						agentsTmp4.add(agentsTmp3.get(i));
				}
			}
			else
			{
				agentsTmp4 = agentsTmp3;
			}
			if(!entiteAgent.equals(""))
			{
				for(int i=0; i<agentsTmp4.size(); i++)
				{
					if(agentsTmp4.get(i).getEntite().contains(entiteAgent))
						agents.add(agentsTmp4.get(i));
				}
			}
			else
			{
				agents = agentsTmp4;
			}
		}
		else{agents = am.findAll();}
		
		return SUCCESS;
	}
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.servletRequest = arg0;
		
	}
	

	
	

}
