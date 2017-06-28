package kettleDemo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DataBase {
	private String name;
	private String type;
	private String access;
	private String host;
	private String db;
	private String port;
	private String user;
	private String pass ;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAccess() {
		return access;
	}
	public void setAccess(String access) {
		this.access = access;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getDb() {
		return db;
	}
	public void setDb(String db) {
		this.db = db;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
    
	public void getdatabase(String proName){
		InputStream in = getClass().getResourceAsStream("/cfg.properties");
		try {
			// FileInputStream方法当前目录下路径不可行
			
			/* * InputStream in = new BufferedInputStream(new FileInputStream(
			 * "E:/01workFiles/git/sgpmiClientDemo/src/demo/main/cfg.properties"
			 * ));*/
			 
			Properties prop = new Properties();
			prop.load(in);
			this.setName(prop.getProperty(proName+"name").toString());
			this.setType(prop.getProperty(proName+"type").toString());
			this.setAccess(prop.getProperty(proName+"access").toString());
			this.setHost(prop.getProperty(proName+"host").toString());
			this.setDb(prop.getProperty(proName+"db").toString());
			this.setPort(prop.getProperty(proName+"port").toString());
			this.setUser(prop.getProperty(proName+"user").toString());
			this.setPass(prop.getProperty(proName+"pass").toString());
			
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getFilename(){
		InputStream in = getClass().getResourceAsStream("/cfg.properties");
		try{
			Properties prop = new Properties();
			prop.load(in);
			String path = prop.getProperty("filename").toString();
			return path;
		}catch(IOException e){
			e.printStackTrace();
		}
		return null;
	}
    

}
