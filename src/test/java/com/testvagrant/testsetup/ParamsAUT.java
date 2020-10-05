package com.testvagrant.testsetup;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ParamsAUT {
	
	private static ParamsAUT instance = null;
    private Properties properties;


    protected ParamsAUT() throws IOException{

        properties = new Properties();
        FileInputStream filePropsAPI = new FileInputStream(new File("./src/test/resources/application-API.properties"));
        FileInputStream filePropsUI = new FileInputStream(new File("./src/test/resources/application-UI.properties"));
        FileInputStream filePropsTempVariance = new FileInputStream(new File("./src/test/resources/TemperatureVariance.properties"));
        properties.load(filePropsAPI);
        properties.load(filePropsUI);
        properties.load(filePropsTempVariance);

    }

    public static ParamsAUT getInstance() {
        if(instance == null) {
            try {
                instance = new ParamsAUT();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        return instance;
    }

    public String getValue(String key) {
        return properties.getProperty(key);
    }

}
