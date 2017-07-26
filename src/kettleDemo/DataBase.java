package kettleDemo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.pentaho.di.core.database.DatabaseMeta;

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
    
	public  DataBase(String proName){
		try {
			InputStream in = new FileInputStream("./config/cfg.properties");
			// FileInputStream方法当前目录下路径不可行
			
			/* * InputStream in = new BufferedInputStream(new FileInputStream(
			 * "E:/01workFiles/git/sgpmiClientDemo/src/demo/main/cfg.properties"
			 * ));*/
			 
			Properties prop = new Properties();
			prop.load(in);
			this.setName(proName);
			this.setType(prop.getProperty(proName+"type").toString());
			this.setAccess("Native");
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
		try{
			InputStream in = new FileInputStream("./config/cfg.properties");
			Properties prop = new Properties();
			prop.load(in);
			String path = prop.getProperty("filename").toString();
			in.close();
			return path;
		}catch(IOException e){
			e.printStackTrace();
		}
		return null;
	}
	
	public String getAppcode(){
		try{
			InputStream in = new FileInputStream("./config/cfg.properties");
			Properties prop = new Properties();
			prop.load(in);
			String appcode = prop.getProperty("appcode").toString();
			in.close();
			return appcode;
		}catch(IOException e){
			e.printStackTrace();
		}
		return null;
	}
    
	public DatabaseMeta getDatabase(){
		System.out.println("type="+this.getType());
		DatabaseMeta inputDataBase = new DatabaseMeta(this.getName(), 
				this.getType(),
				"Native", 
				this.getHost(),
				this.getDb(), 
				this.getPort(), 
				this.getUser(),
				this.getPass());
		return inputDataBase;
	}

}
