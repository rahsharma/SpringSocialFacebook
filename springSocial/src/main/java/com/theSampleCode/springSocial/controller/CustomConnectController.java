package com.theSampleCode.springSocial.controller;

import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/connect")
public class CustomConnectController extends ConnectController {
	private String viewPath = "connect/";
	public CustomConnectController(ConnectionFactoryLocator connectionFactoryLocator,
			ConnectionRepository connectionRepository) {
		super(connectionFactoryLocator, connectionRepository);
	}
	
	@Override
    protected String connectedView(String providerId) {
        return viewPath + providerId + "Connected1";
    }
	protected String connectView(String providerId) {
		return viewPath + providerId + "Connect1";		
	}
}
