package com.example.Homebrew.rest;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Brew {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String type;
	private String name;
	private int percentage;
	private int brewTime;

	public Brew(int id, String type, String name, int percentage, int brewTime) {
		super();
		this.id = id;
		this.type = type;
		this.name = name;
		this.percentage = percentage;
		this.brewTime = brewTime;
	}

	public Brew() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPercentage() {
		return percentage;
	}

	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}

	public int getBrewTime() {
		return brewTime;
	}

	public void setBrewTime(int brewTime) {
		this.brewTime = brewTime;
	}

	@Override

	public String toString() {
		return "Brews[type=" + type + ", name=" + percentage + ", brewTime]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(brewTime, id, name, percentage, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Brew other = (Brew) obj;
		return brewTime == other.brewTime && id == other.id && Objects.equals(name, other.name)
				&& percentage == other.percentage && Objects.equals(type, other.type);
	}

}
