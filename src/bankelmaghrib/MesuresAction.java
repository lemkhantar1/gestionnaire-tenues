package bankelmaghrib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

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

import com.opensymphony.xwork2.ActionSupport;
import com.sun.prism.paint.Color;

import entities.Agent;
import entities.Mesure;
import model.AgentModel;
import model.MesureModel;


public class MesuresAction  extends ActionSupport implements ServletRequestAware , SessionAware   {
	
	FileInputStream fileInputStream;
	private HttpServletRequest servletRequest;
	private AgentModel am = new AgentModel();
	private MesureModel mm = new MesureModel();
	private Map<String,Object> session ;


	public Map<String, Object> getSession() {
		return session;
	}


	public void setSession(Map<String, Object> session) {
		this.session = session;
	}


	public FileInputStream getFileInputStream(){
		return fileInputStream;
	}


	public void setFileInputStream(FileInputStream file) {
		this.fileInputStream = file;
	}







	@Override
	public String execute() throws Exception {
		if(!session.containsKey("admin"))return "logout";
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
		file = new File(filePath, "MESURES.wlsx");
		
		
		//Blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook(); 
         
        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("MESURES");
        
        XSSFCellStyle myStyle = workbook.createCellStyle();           

        myStyle.setFillBackgroundColor(new XSSFColor(java.awt.Color.YELLOW));
        myStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
          
        //This data needs to be written (Object[])
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        data.put("1", new Object[] {"ID", "nom", "prenom", "sexe", "taille (XS/S/M/L/XL/XXL/XXXL)", "pointure"});
        List<Agent> agents = am.findAll();
        Agent a;
        for(int i=0;i<agents.size(); i++)
        {
        	a = agents.get(i);
        	data.put((i+2)+"", new Object[] {a.getId(), a.getNom(), a.getPrenom(), a.getSexe(), "",""});
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
	
	public String validateMesures()
	{
		if(!session.containsKey("admin"))return "logout";
		Agent agent;
		if(session.containsKey("mesures"))
		{
			Mesure mesure;
			List<MesureRow> mesures = (List<MesureRow>) session.get("mesures");
			for(int i=0; i<mesures.size(); i++)
			{
				agent = am.find(mesures.get(i).getId());
				mesure = mm.find(agent.getMesure().getId());
				mesure.setTaille(mesures.get(i).getTaille());
				mesure.setPointure(mesures.get(i).getPointure());
				mm.update(mesure);
			}
			session.remove("mesures");
		}
		return SUCCESS;
	}




	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.servletRequest = arg0;
		
	}

}
