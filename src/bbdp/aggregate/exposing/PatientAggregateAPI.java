package bbdp.aggregate.exposing;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.jdbc.pool.DataSource;

import com.google.gson.Gson;

public class PatientAggregateAPI extends HttpServlet {
	public PatientAggregateAPI() {
		super();
	}	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		String operation = request.getParameter("operation");
		
        DataSource datasource = (DataSource) getServletContext().getAttribute("db");
        Gson gson = new Gson();

		switch(operation) { 
			//get name of the patient by ID
			case "getPatientNameById":{
				String patientID = request.getParameter("patientID");
				response.getWriter().write(gson.toJson(PatientRepo.getPatientNameById(datasource,patientID)));
				break;
			}	
	
			
			default:{
				HashMap api = new HashMap();
				api.put("getPatientNameById", "get name of the patient by ID");
				response.getWriter().write(gson.toJson(api));
				break;
			}
		}
	}
}


