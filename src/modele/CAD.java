package modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CAD {
	private String connectionUrl;
	private String login;
	private String psw;
	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	
    public CAD(String url, String login, String psw ) {
        try { 
            Class.forName("com.mysql.jdbc.Driver"); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.connectionUrl= url;
        this.login = login;
        this.psw = psw;

        try {
            this.con = DriverManager.getConnection(this.connectionUrl, this.login, this.psw);
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    
    public ResultSet GetRows(String rq_sql) {
        try {
            this.stmt = con.createStatement();
            this.rs = stmt.executeQuery(rq_sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return rs;
    }
}  






