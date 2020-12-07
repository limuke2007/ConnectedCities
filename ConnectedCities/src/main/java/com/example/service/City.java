package com.example.service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class City {
	
	private String name;
	
	private Set<City> connections = new HashSet<>();

	public City(String name) {
		this.name = name;
	}

	public City() {
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<City> getConnectedCities() {
		return connections;
	}

	public void setConnectedCities(Set<City> connectedCities) {
		this.connections = connectedCities;
	}
	
	public static City build(String name) {
        return new City(name);
    }
	
	public City addConnection(City city) {
        connections.add(city);
        return this;
    }
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;
        City city = (City) o;
        return Objects.equals(name, city.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
