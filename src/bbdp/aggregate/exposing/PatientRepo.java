package bbdp.aggregate.exposing;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import org.apache.tomcat.jdbc.pool.DataSource;

public class PatientRepo{
	public static String getPatientNameById(DataSource datasource, String patientID) {
		String name = "not be found";
		Connection con = null;
		
		try {
			con = datasource.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select name from patient where patientID='"+ patientID+"' ");

			while (rs.next()) {
				name = rs.getString("name");
			}
			rs.close();
		    st.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("'getPatientNameById' Exception :" + e.toString());
		} finally {
		      if (con!=null) try {con.close();}catch (Exception ignore) {}
		}
		return name;
	}

	
}