package RegisterAndLogin.service;

import RegisterAndLogin.dto.UserDto;
import RegisterAndLogin.model.User;

public interface UserService {

	
	User findByUsername(String username);
	
	User save(UserDto userDto);
}
