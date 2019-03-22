package si.iitech.lib.entity.impl;

import javax.persistence.Entity;

import si.iitech.lib.entity.EtEntity;

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
