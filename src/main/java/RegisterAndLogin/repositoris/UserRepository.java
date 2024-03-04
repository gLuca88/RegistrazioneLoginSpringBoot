package RegisterAndLogin.repositoris;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import RegisterAndLogin.model.User;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {

	User findByUsername(String username);
	
	
}//close interface
