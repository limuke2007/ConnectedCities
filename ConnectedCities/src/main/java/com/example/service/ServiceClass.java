package com.example.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiParam;
import net.bytebuddy.asm.Advice.OffsetMapping.ForOrigin.Renderer.ForReturnTypeName;

@RestController
public class ServiceClass {
	
	
	
	@Autowired
	County county;
	
	@GetMapping(value = "/connected", produces = "text/plain")
	public String isConnected(
			@ApiParam(name = "origin", value = "Origin City name", required = true) @RequestParam String origin,
			@ApiParam(name = "destination", value = "Destination City name", required = true) @RequestParam String destination) {

		City originCity = county.getCity(origin.toUpperCase());
		City destCity = county.getCity(destination.toUpperCase());

		Objects.requireNonNull(originCity);
		Objects.requireNonNull(destCity);
		
		if (Commuter.commute(originCity, destCity)) {
			return "yes";
		} else {
			return "no";
		}
	}

}
