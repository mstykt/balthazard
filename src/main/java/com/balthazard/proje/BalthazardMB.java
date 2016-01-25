package com.balthazard.proje;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class BalthazardMB {
	@ManagedProperty(value = "#{hastalik}")
	private Hastalik hastalik;
	
	@ManagedProperty(value="#{operations}")
	private Operations operations;
	
	private boolean altmisYas=false;
	private boolean sonucRender=false;
	private float sonuc;

	public BalthazardMB() {
		
	}

	public Hastalik getHastalik() {
		return hastalik;
	}

	public void setHastalik(Hastalik hastalik) {
		this.hastalik = hastalik;
	}

	public Operations getOperations() {
		return operations;
	}

	public void setOperations(Operations operations) {
		this.operations = operations;
	}

	public boolean isAltmisYas() {
		return altmisYas;
	}

	public void setAltmisYas(boolean altmisYas) {
		this.altmisYas = altmisYas;
	}

	public boolean isSonucRender() {
		return sonucRender;
	}

	public void setSonucRender(boolean sonucRender) {
		this.sonucRender = sonucRender;
	}

	public float getSonuc() {
		return sonuc;
	}

	public void setSonuc(float sonuc) {
		this.sonuc = sonuc;
	}
	
	public void hesapla(){
		sonuc=operations.sonucAl(hastalik,altmisYas);
		setSonucRender(true);
	}
	
	public void temizle() {
		hastalik=new Hastalik();
		setSonucRender(false);
		setAltmisYas(false);
	}
}
