package si.iitech.entity.impl;

import javax.persistence.Entity;

import si.iitech.entity.EtEntity;

@Entity
public class EtExample extends EtEntity {

	private String text;
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
}
