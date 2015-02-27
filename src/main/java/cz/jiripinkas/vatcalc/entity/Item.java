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

	private double castkaDph;

	private boolean cenaBezDphDisabled;

	private boolean cenaSDphDisabled;

	public double getCastkaDph() {
		return castkaDph;
	}

	public void setCastkaDph(double castkaDph) {
		this.castkaDph = castkaDph;
	}

	public boolean isCenaBezDphDisabled() {
		return cenaBezDphDisabled;
	}

	public void setCenaBezDphDisabled(boolean cenaBezDphDisabled) {
		this.cenaBezDphDisabled = cenaBezDphDisabled;
	}

	public boolean isCenaSDphDisabled() {
		return cenaSDphDisabled;
	}

	public void setCenaSDphDisabled(boolean cenaSDphDisabled) {
		this.cenaSDphDisabled = cenaSDphDisabled;
	}

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

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", cenaBezDph=" + cenaBezDph + ", cenaSDph=" + cenaSDph + ", dph=" + dph + ", castkaDph=" + castkaDph + ", cenaBezDphDisabled="
				+ cenaBezDphDisabled + ", cenaSDphDisabled=" + cenaSDphDisabled + "]";
	}

}
