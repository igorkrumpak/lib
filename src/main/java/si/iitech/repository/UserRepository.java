package si.iitech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import si.iitech.entity.EtUser;

@Repository
public interface UserRepository extends JpaRepository<EtUser, Long> {

	public EtUser findByEmail(String email);

}
