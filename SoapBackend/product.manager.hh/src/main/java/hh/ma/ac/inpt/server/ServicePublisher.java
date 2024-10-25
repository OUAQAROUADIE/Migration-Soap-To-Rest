package hh.ma.ac.inpt.server;

import java.util.logging.Logger;

import hh.ma.ac.inpt.service.ProductsManagerImpl;
import hh.ma.ac.inpt.translationLayer.SoapRestTranslationLayer;
import jakarta.xml.ws.Endpoint;

public class ServicePublisher {
	
	private static final Logger LOGGER = Logger.getLogger(ServicePublisher.class.getName());

	public static void main(String[] args) {
		SoapRestTranslationLayer soapRestTranslationLayer = new SoapRestTranslationLayer();
		LOGGER.info("starting server ....");
		Endpoint.publish("http://localhost:8886/webservices/ProductManager",  soapRestTranslationLayer);
		LOGGER.info("Server started");
		}
	}