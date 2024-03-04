package RegisterAndLogin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import RegisterAndLogin.dto.UserDto;
import RegisterAndLogin.model.User;
import RegisterAndLogin.repositoris.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	PasswordEncoder passwordEncoder;

	private UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {

		this.userRepository = userRepository;
	}

	@Override
	public User findByUsername(String username) {

		return userRepository.findByUsername(username);
	}
    /*
	@Override
	public User save(UserDto userDto) {
		User user = new User(userDto.getUsername(),passwordEncoder.encode(userDto.getPassword()),userDto.getFullname());
		return userRepository.save(user);
	}
	*/
	@Override
	public User save(UserDto userDto) {
	    // Verifica se i campi sono vuoti o nulli
	    if (userDto.getUsername() == null || userDto.getUsername().isEmpty() ||
	        userDto.getPassword() == null || userDto.getPassword().isEmpty() ||
	        userDto.getFullname() == null || userDto.getFullname().isEmpty()) {
	        // Notifica che la registrazione è fallita a causa di campi vuoti
	        return null;
	    }

	    // Salva l'utente nel database
	    User user = new User(userDto.getUsername(), passwordEncoder.encode(userDto.getPassword()), userDto.getFullname());
	    return userRepository.save(user);
	}

}
