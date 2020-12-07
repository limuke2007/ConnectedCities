package com.example.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class County {


	private Map<String, City> cityMap = new HashMap<>();

	@Value("${data.file:classpath:city pairs.txt}")
	private String CITIES;

	@Autowired
	private ResourceLoader resourceLoader;

	public Map<String, City> getCityMap() {
		return cityMap;
	}

	@PostConstruct
	private void read() throws IOException {


		Resource resource = resourceLoader.getResource(CITIES);

		InputStream is;

		if (!resource.exists()) {
			
			is = new FileInputStream(new File(CITIES));
		} else {
			is = resource.getInputStream();
		}

		Scanner scanner = new Scanner(is);

		while (scanner.hasNext()) {

			String line = scanner.nextLine();
			if (StringUtils.isEmpty(line)) {
				continue;
			}

			String[] split = line.split(",");
			String Akey = split[0].trim().toUpperCase();
			String Bkey = split[1].trim().toUpperCase();

			if (!Akey.equals(Bkey)) {
				City A = cityMap.getOrDefault(Akey, City.build(Akey));
				City B = cityMap.getOrDefault(Bkey, City.build(Bkey));

				A.addConnection(B);
				B.addConnection(A);

				cityMap.put(A.getName(), A);
				cityMap.put(B.getName(), B);
			}
		}
		scanner.close();
	}

	public City getCity(String name) {
		return cityMap.get(name);
	}

}
