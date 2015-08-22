package core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class TickerLoader {
	
	private Properties props;
	private String propPath;
    private static TickerLoader instance;
    
    private TickerLoader() {
    	
    	
    	props = new Properties();
    	
        
        
    }
    
    public static TickerLoader getInstance() throws IOException {
        if (instance == null) {
            instance = new TickerLoader();
        }
        return instance;
    }
    
    public String get(String key) {
        return props.getProperty(key, "");
    }

	public void setPropPath(String propPath) {
		this.propPath = propPath;
	}
	
	public void loadProperties() throws FileNotFoundException, IOException {
		props.load(new FileInputStream(propPath));
	}

    
    

}
