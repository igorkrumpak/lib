package si.iitech.lib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import si.iitech.lib.entity.impl.EtExample;

@Repository
public interface ExampleRepository extends JpaRepository<EtExample, Long> {

	public EtExample findByText(String text);

}
