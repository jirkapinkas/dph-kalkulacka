package cz.jiripinkas.vatcalc.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Item {

	@Id
	@GeneratedValue
	private int id;

	private String name;

	private double cenaBezDph;

	private double cenaSDph;

	private int dph;

	public double getCenaBezDph() {
		return cenaBezDph;
	}

	public void setCenaBezDph(double cenaBezDph) {
		this.cenaBezDph = cenaBezDph;
	}

	public double getCenaSDph() {
		return cenaSDph;
	}

	public void setCenaSDph(double cenaSDph) {
		this.cenaSDph = cenaSDph;
	}

	public int getDph() {
		return dph;
	}

	public void setDph(int dph) {
		this.dph = dph;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
