package fr.eliott.app.car_a_mel.models.entities;

import java.util.Date;

import fr.eliott.app.car_a_mel.models.enums.BrandEnum;

public class Car {

	public BrandEnum brand;
	public String model;
	public int horsePower;
	public String registration;
	public Date created_at;
	public int iD;

	public Car(BrandEnum brand, String model, int horsePower, String registration, Date created_at, int iD) {
		this.brand = brand;
		this.model = model;
		this.horsePower = horsePower;
		this.registration = registration;
		this.created_at = created_at;
		this.iD = iD;
	}

	public int getiD() {
		return iD;
	}

	public void setiD(int iD) {
		this.iD = iD;
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
		return "\t " iD " | " + brand.name() + " | " + model + " | " + horsePower + " | " + registration + " | " + created_at;
	}

}
