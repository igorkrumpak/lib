package si.iitech.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity(name = "USER")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class EtUser extends EtEntity {

	private String email;
	private String password;

	public EtUser() {
	}

	public EtUser(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	@Email
	@NotBlank(message = "Email is mandatory")
	@Column(nullable = false, unique = true)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@NotBlank(message = "Password is mandatory")
	@Size(message = "Password must be at least 8 characters long", min = 8)
	@Column(nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
