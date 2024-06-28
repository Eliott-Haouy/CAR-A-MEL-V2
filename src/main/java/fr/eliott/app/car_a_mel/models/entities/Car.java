package fr.eliott.app.car_a_mel.models.entities;

import java.util.Date;

import fr.eliott.app.car_a_mel.models.enums.BrandEnum;

public class Car {

	private BrandEnum brand;
	private String model;
	private int horsePower;
	private String registration;
	private Date created_at;
	private int id;

	public Car(BrandEnum brand, String model, int horsePower, String registration, Date created_at) {
		this.brand = brand;
		this.model = model;
		this.horsePower = horsePower;
		this.registration = registration;
		this.created_at = created_at;
	}

	public Car(BrandEnum brand, String model, int horsePower, String registration, Date created_at, int id) {
		this.brand = brand;
		this.model = model;
		this.horsePower = horsePower;
		this.registration = registration;
		this.created_at = created_at;
		this.id = id;
	}

	public int getid() {
		return id;
	}

	public void setid(int id) {
		this.id = id;
	}

	public BrandEnum getBrand() {
		return brand;
	}

	public void setBrand(BrandEnum brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getHorsePower() {
		return horsePower;
	}

	public void setHorsePower(int horsePower) {
		this.horsePower = horsePower;
	}

	public String getRegistration() {
		return registration;
	}

	public void setRegistration(String registration) {
		this.registration = registration;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public String toString() {
		return "\t " + id + " | " + brand.name() + " | " + model + " | " + horsePower + " | " + registration + " | "
				+ created_at;
	}

}
