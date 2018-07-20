package bbdp.patient.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import bbdp.patient.model.LoginVerification;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

@WebServlet("/LoginVerificationServlet")
public class LoginVerificationServlet extends HttpServlet {
	public LoginVerificationServlet() {
		super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		Gson gson = new Gson();
		//連接資料庫
		DataSource datasource = (DataSource) getServletContext().getAttribute("db");
		LoginVerification login = new LoginVerification();
		/*******************************************************************************************/
		String state = request.getParameter("state");
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String uuid = request.getParameter("uuid");

		String patientID = request.getParameter("patientID");
		/*******************************************************************************************/
		/*******************************************************************************************/
		// 登入驗證
		if(state.equals("login")){
			response.getWriter().write(gson.toJson(login.verification(datasource,account, password, uuid)));
		}
		// 判斷登入
		if(state.equals("judgeLogin")){
			response.getWriter().write(gson.toJson(login.judgeLogin(datasource, patientID, uuid)));
		}
		// 更新uuid
		if(state.equals("updateUUID")){
			response.getWriter().write(gson.toJson(login.updateUUID(datasource, patientID, uuid)));
		}
	}
}
