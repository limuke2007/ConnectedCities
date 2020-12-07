package com.example.service;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class Commuter {
	
	
	private Commuter() { 
	}

	public static boolean commute(City originCity, City destCity) {

		if (originCity.equals(destCity)) {
			return true;
		}
			

		if (originCity.getConnectedCities().contains(destCity)) {
			return true;
		}
			
		Set<City> visited = new HashSet<>(Collections.singleton(originCity));

		Deque<City> checkList = new ArrayDeque<>(originCity.getConnectedCities());

		while (!checkList.isEmpty()) {

			City city = checkList.getLast();
			if (city.equals(destCity)) {
				return true;
			}

			if (!visited.contains(city)) {
				visited.add(city);

				checkList.addAll(city.getConnectedCities());
				checkList.removeAll(visited);
			} else {
				checkList.removeAll(Collections.singleton(city));
			}
		}
		return false;
	}
}
