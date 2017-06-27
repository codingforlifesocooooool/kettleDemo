package kettleDemo;

import java.util.List;

import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.exception.KettleMissingPluginsException;
import org.pentaho.di.core.exception.KettleXMLException;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.StepMetaDataCombi;

public class GetTrans {
	
	public static void main(String[] args) throws KettleException {
		try {
			getTrans();
		} catch (KettleXMLException e) {
			e.printStackTrace();
		} catch (KettleMissingPluginsException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void getTrans() throws KettleException {
		try {
			KettleEnvironment.init();
		} catch (KettleException e) {
			e.printStackTrace();
		}  	
	String path =  "E:/01workFiles/kettle/pdi-ce-6.1.0.1-196/pdi-ce-6.1.0.1-196/ktrs/u3tosgp3.ktr";
	TransMeta tm = new TransMeta(path);
	
	//替换输入输出数据库
	 DatabaseMeta inputDataBase = new DatabaseMeta("koalusap", "Oracle", "Native", "127.0.0.1", "xe", "1521", "koalusap", "koalusap"); 
	 DatabaseMeta outputDataBase = new DatabaseMeta("sgpmi", "Oracle", "Native", "127.0.0.1", "xe", "1521", "sgpmi", "sgpmi");
	 tm.getDatabase(0).replaceMeta(inputDataBase);
	 tm.getDatabase(1).replaceMeta(outputDataBase);
	Trans trans = new Trans(tm);  
	trans.setVariable("param-appid", "WUDIWUDI");
	trans.setVariable("param-authortype", "200");
	trans.prepareExecution(null); 
	List<StepMetaDataCombi> steps = trans.getSteps();
	for(StepMetaDataCombi s: steps){
		System.out.println(s.stepname);
	}
	// 启动并等待执行完成  
	trans.startThreads();  
	trans.waitUntilFinished();
	
	}
	
}
