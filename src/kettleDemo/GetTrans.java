package kettleDemo;

import java.io.File;
import java.util.List;

import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.StepMetaDataCombi;

public class GetTrans {
	
	public static void main(String[] args) throws KettleException {
		DataBase fromdb = new DataBase("input");
    	DataBase todb = new DataBase("output");
		runTrans(fromdb, todb);
	}

	public static void runTrans(DataBase fromdb,DataBase todb) throws KettleException {
		
		try {
			KettleEnvironment.init();
		} catch (KettleException e) {
			e.printStackTrace();
		}
		
		DatabaseMeta inputDataBase = fromdb.getDatabase();
		DatabaseMeta outputDataBase = todb.getDatabase();
		
		String filename = "syn.ktr";
		
			//得到需要加载的转换文件
			String path = "./lib/"+filename;
			System.out.println("当前文件所在路径："+new File(path).getAbsolutePath());
				
			TransMeta tm = new TransMeta(path);
			
			// 替换输入输出数据库
			for(int i = 0;i < tm.getDatabases().size();i++){
				String tableName = tm.getDatabase(i).getName();
				if(tableName.equals("input")){
					tm.getDatabase(i).replaceMeta(inputDataBase);
				}else if(tableName.equals("output")){
					tm.getDatabase(i).replaceMeta(outputDataBase);
				}
			
			//得到转换
			Trans trans = new Trans(tm);
			//预执行，否走没法得到steps
			trans.prepareExecution(null);
			//没什么用吧。。。。。
			List<StepMetaDataCombi> steps = trans.getSteps();
			for (StepMetaDataCombi s : steps) {
				System.out.println(s.stepname);
			}
			// 启动并等待执行完成
			trans.startThreads();
			trans.waitUntilFinished();
		}
	}
	
	 /** 
     * java 调用 kettle 的job 
     *  
     * @param jobname 
     *            如： String fName= "D:\\kettle\\informix_to_am_4.ktr"; 
     */  
    public static void runJob(String[] params, String jobPath) {  
        try {  
        	
            KettleEnvironment.init();  
            // jobname 是Job脚本的路径及名称  
            JobMeta jobMeta = new JobMeta(jobPath, null); 
            Job job = new Job(null, jobMeta);  
            // 向Job 脚本传递参数，脚本中获取参数值：${参数名}  
            // job.setVariable(paraname, paravalue);  
            job.start();  
            job.waitUntilFinished();
            int errorNum = job.getErrors();
            System.out.println("errorNum=" + errorNum);
            if(errorNum <= 0){
            	System.out.println("成功");
            	//runTrans(fromdb,todb);
            }else{
            	System.out.println("校验没通过");
            }
        } catch (Exception e) {
        	System.out.println("输入视图字段不合法！");
          //  e.printStackTrace();  
        }  
    }  

}
