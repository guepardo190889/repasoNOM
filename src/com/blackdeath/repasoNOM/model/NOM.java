package com.blackdeath.repasoNOM.model;

public class NOM {
	private String norma;
	private String definicion;

	public NOM(String norma, String definici�n) {
		this.norma = norma;
		this.definicion = definici�n;
	}

	public String getNorma() {
		return norma;
	}

	public void setNorma(String norma) {
		this.norma = norma;
	}

	public String getDefinicion() {
		return definicion;
	}

	public void setDefinici�n(String definicion) {
		this.definicion = definicion;
	}

	@Override
	public String toString() {
		return "NOM [norma=" + norma + ", definicion=" + definicion + "]";
	}

}
