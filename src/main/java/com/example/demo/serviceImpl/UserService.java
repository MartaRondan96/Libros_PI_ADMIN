package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.repository.UserRepository;

@Service("userService")
public class UserService implements UserDetailsService {

	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;


	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public com.example.demo.entity.User register(com.example.demo.entity.User user) {
		user.setPassword(passwordEncoder().encode(user.getPassword()));
		user.setEnabled(true);
		user.setRole("ROLE_USER");
		user.setListFavs(new ArrayList());
		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.example.demo.entity.User usuario = userRepository.findByUsername(username);

		UserBuilder builder = null;

		if (usuario != null) {
			builder = User.withUsername(username);
			builder.disabled(false);
			builder.password(usuario.getPassword());
			builder.authorities(new SimpleGrantedAuthority(usuario.getRole()));

		} else
			throw new UsernameNotFoundException("Usuario no encontrado");
		return builder.build();
	}

	public com.example.demo.entity.User findUser(String username) {
		return userRepository.findByUsername(username);
	}
	public com.example.demo.entity.User findUserId(int id) {
		return userRepository.findById(id);
	}

	public int activate(String username) {
		int a=0;
		com.example.demo.entity.User u=userRepository.findByUsername(username);
		com.example.demo.entity.User user=new com.example.demo.entity.User();
		
		user.setPassword(passwordEncoder().encode(u.getPassword()));
		user.setUsername(u.getUsername());
		user.setEmail(u.getEmail());
		user.setId(u.getId());
		
		if(u.isEnabled()==false) {
			user.setEnabled(true);
			a=1;
		}else {
			user.setEnabled(false);
			a=0;
		}
		user.setRole(u.getRole());
		
		userRepository.save(user);
		return a;
	}
	
	public com.example.demo.entity.User addFav(int id,String username) {
		com.example.demo.entity.User u= findUser(username);
		List<Integer> list = u.getListFavs();
		if(!list.contains(id)) {
			list.add(id);
		}
		u.setListFavs(list);
		return userRepository.save(u);
	}
	
	public com.example.demo.entity.User deleteFav(int id,String username) {
		com.example.demo.entity.User u= findUser(username);
		List<Integer> list = u.getListFavs();
		if(list.contains(id)) {
			list.remove(Integer.valueOf(id));
		}
		u.setListFavs(list);
		return userRepository.save(u);
	}
	
	public List<Integer> getFavs(int id) {
		com.example.demo.entity.User u = userRepository.findById(id);
		List<Integer> list = u.getListFavs();
		return list;
	}
	public void deleteUser(String username) throws Exception {
		com.example.demo.entity.User u = userRepository.findByUsername(username);
		userRepository.delete(u);
	}
	public List<com.example.demo.entity.User> listAllUsuarios() {
        List<com.example.demo.entity.User> list = userRepository.findAll().stream().collect(Collectors.toList());
        List<com.example.demo.entity.User> listwithoutAdmin = list.stream()
                .filter(x -> x.getRole().equals("ROLE_USER")).collect(Collectors.toList());
        return listwithoutAdmin;
    }
	
	public com.example.demo.entity.User updateUser(com.example.demo.entity.User user) {
		user.setPassword(passwordEncoder().encode(user.getPassword()));
		return userRepository.save(user);
	}	
	public com.example.demo.entity.User updateUserWP(com.example.demo.entity.User user) {
		return userRepository.save(user);
	}
}
