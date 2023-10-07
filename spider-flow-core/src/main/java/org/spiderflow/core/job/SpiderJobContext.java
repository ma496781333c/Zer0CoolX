package org.spiderflow.core.job;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spiderflow.context.SpiderContext;

public class SpiderJobContext extends SpiderContext{

	private static final long serialVersionUID = 9099787449108938453L;
	
	private static Logger logger = LoggerFactory.getLogger(SpiderJobContext.class);
	
	private OutputStream outputstream;

	public SpiderJobContext(OutputStream outputstream) {
		super();
		this.outputstream = outputstream;
	}
	
	public void close(){
		try {
			this.outputstream.close();
		} catch (Exception e) {
		}
	}

	public OutputStream getOutputstream(){
		return this.outputstream;
	}
	
	public static SpiderJobContext create(String directory,String id,Integer taskId){
		OutputStream os = null;
		try {
			File file = new File(new File(directory),id + File.separator + "logs" + File.separator + taskId + ".log");
			File dirFile = file.getParentFile();
			if(!dirFile.exists()){
				dirFile.mkdirs();
			}
			os = new FileOutputStream(file, true);
		} catch (Exception e) {
			logger.error("创建日志文件出错",e);
		}
		return new SpiderJobContext(os);
	}
}
