package modele;

public class Map_P {

	private int id;
	private String login;
	private String password;
	private String rq_sql;
	
	public Map_P() {
		
	}
	
	public String selectIDbyLoginPassword(String login, String password) {
		rq_sql = "SELECT * FROM tb_personne WHERE login = '" + login + "' AND password = '" + password + "'";
		return rq_sql;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRq_sql() {
		return rq_sql;
	}
	public void setRq_sql(String rq_sql) {
		this.rq_sql = rq_sql;
	}
}
