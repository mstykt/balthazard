package com.balthazard.proje;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

@ManagedBean(name="operations")
public class Operations {
	@ManagedProperty(value="#{hastalik}")
	private Hastalik hastalik;
	
	public Operations() {
		
	}

	public Hastalik getHastalik() {
		return hastalik;
	}

	public void setHastalik(Hastalik hastalik) {
		this.hastalik = hastalik;
	}

	public float sonucAl(Hastalik hastalik,boolean altmisYas){
		float sonuc=0;
		float ilkDeger;
		int ikinciDeger;
		List<Integer> liste=new ArrayList<>();
		try {
			liste = listeOlustur(hastalik);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		//ilkDeger ilk ataması yapılır, ilk atamadan sonra işlem sonucu ilkDeger olur
		ilkDeger=liste.get(0);
		for (int i = 0; i < liste.size(); i++) {
			//ikinciDeger liste elemanlrını sırasıyla alır
			ikinciDeger=liste.get(i+1);
			
			//Balthaza formülü
			sonuc=100-ilkDeger;
			sonuc=sonuc*ikinciDeger;
			sonuc=sonuc/100;
			sonuc+=ilkDeger;
			
			//ilkDeger artık dinamik olarak değişir
			ilkDeger=sonuc;
			
			if (i==liste.size()-2) break;
			
		}
		
		if (altmisYas) {
			sonuc=100-ilkDeger;
			sonuc=sonuc*10;
			sonuc=sonuc/100;
			sonuc+=ilkDeger;
		}
		
		
		System.out.println(liste);
		System.out.println(sonuc);
		
		return sonuc;
	}
	
	
	public List<Integer> listeOlustur(Hastalik hastalik) throws Throwable {
		Field[] hastaliklar=hastalik.getClass().getDeclaredFields();
		List<Integer> liste=new ArrayList<>();
		
		/*
		 * Haslik degiskenleri ileride değişebileceğiden
		 * katılı olarak listeye eklenmemelidir
		 * Yanlış kullanım: liste.add(hastalik.getHastalikAdi())
		 */
		for (Field h : hastaliklar) {
			if (h.getType().toString().equalsIgnoreCase("int")) {
				Field element=hastalik.getClass().getDeclaredField(h.getName());
				element.setAccessible(true);
				Integer i=(Integer) element.get(hastalik);
				liste.add(i);
			}
		}
		
		Collections.sort(liste);
		Collections.reverse(liste);
		return liste;
	}
}
