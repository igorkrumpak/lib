package si.iitech.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public abstract class EtEntity {

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
