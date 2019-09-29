package controler;

import modele.CAD;
import modele.Map_P;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Wkf_cpte {

	Map_P map_p = new Map_P();
	CAD cad = new CAD("jdbc:mysql://localhost:3306/projet_grue?useSSL=false", "root", "");
	String rq_sql;
	ResultSet result;
	
	public Wkf_cpte() {
		
	}
	
	public Boolean pcs_authentifier(String user_login, char[] password) {
		String pass = "";

		for(int i=0; i<password.length; i++) {
			pass += password[i];
		}
		rq_sql = map_p.selectIDbyLoginPassword(user_login, pass);
		result = cad.GetRows(rq_sql);
		
		try {
			while (result.next()) {
				
				if(result.getString(1) != null) {
	    			System.out.println("Row found !");
					return true;
				}else {
					return false;
				}
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("No row found !");
		return false;
	}
}