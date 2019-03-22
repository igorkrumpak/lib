package si.iitech.lib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import si.iitech.lib.entity.EtUser;

@NoRepositoryBean
public interface UserRepository<T extends EtUser> extends JpaRepository<T, Long> {

	public T findByEmail(String email);

}
