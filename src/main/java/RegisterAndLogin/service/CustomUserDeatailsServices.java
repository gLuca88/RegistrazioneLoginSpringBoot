package RegisterAndLogin.service;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import RegisterAndLogin.model.User;
import RegisterAndLogin.repositoris.UserRepository;

@Service
public class CustomUserDeatailsServices implements UserDetailsService {
	
	
    @Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("username or password no found");
		}

		return new CustomUserDetails(

				user.getUsername(), user.getPassword(), authorities(), user.getFullname()

		);
	}

	public Collection<? extends GrantedAuthority> authorities() {

		return Arrays.asList(new SimpleGrantedAuthority("USER"));
	}

}// close the class datails
