package si.iitech.lib.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
public abstract class EtEntity {

	@JsonIgnore
	private Long oid;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	public Long getOid() {
		return oid;
	}
	
	public void setOid(Long oid) {
		this.oid = oid;
	}
	
	private int version;

	protected void setVersion(int version) {
		this.version = version;
	}

	@Version
	protected int getVersion() {
		return version;
	}
}
