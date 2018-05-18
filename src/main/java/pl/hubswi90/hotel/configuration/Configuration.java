package pl.hubswi90.hotel.configuration;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import pl.hubswi90.hotel.models.Hotel;

public class Configuration {

	static String USER_HOME = System.getProperty("user.home");
	private static final String FILE_SEPARATOR = System.getProperty("file.separator");
	static String FILE_NAME = "hmrc.xml";
	static String FOLDER_NAME = ".hotelManager";
	
	private static Configuration instance;
	private File ConfigurationFolder;
	private File configurationFile;
	private Hotel hotel;
	
	private Configuration() {
		ConfigurationFolder = new File(USER_HOME +FILE_SEPARATOR +FOLDER_NAME);
		configurationFile = new File(ConfigurationFolder, FILE_NAME);
		
		if(ConfigurationFolder.exists()) {
			if (configurationFile.exists())
				readFile();
			else {
				initConfigurationFile();
			}
		} else {
			ConfigurationFolder.mkdir();
			initConfigurationFile();
		}
	}
	
	private void initConfigurationFile() {
		hotel = new Hotel();
	}

	private void readFile() {
		try {
			hotel = loadConfigurationFromFile();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public static Configuration getInstance() {
		if(instance == null)
			instance = new Configuration();
		
		return instance;
	}
	
	public static int centeringScreenOnWidth(int frameWidth) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int width = dimension.width / 2 - frameWidth / 2;
		return width;
	}
	
	public static int centeringScreenOnHeight(int frameHeight) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int height = dimension.height / 2 - frameHeight / 2;
		return height;
	}
	
	/**
	 * This method converts the object to xml file
	 * @throws JAXBException
	 */
	public void saveConfigurationToFile()  throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Hotel.class);
	    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	    jaxbMarshaller.marshal(hotel, configurationFile);
	}
	
	/**
	 * This method converts the xml file on the object
	 * @return
	 * @throws JAXBException
	 */
	private Hotel loadConfigurationFromFile() throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(Configuration.class);
        Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();
        return (Hotel) jaxbUnmarshaller.unmarshal(configurationFile);
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
}
