package com.example.demo.model;

import java.util.List;

import com.example.demo.entity.Comentario;
import com.example.demo.entity.Valoracion;

public class UserDTO {
	private int id;
	private String username;
	private String password;
	private String email;
	private boolean enabled;
	private String role;
	private String token;
	private List<Integer> listFavs;
    private List<Comentario> comentariosList;
    private List<Valoracion> listValoraciones;
	
	public UserDTO() {
		super();
	}
	
	public UserDTO(int id, String username, String password, String email, boolean enabled, String role, String token,
			List<Integer> listFavs, List<Comentario> comentariosList, List<Valoracion> listValoraciones) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.enabled = enabled;
		this.role = role;
		this.token = token;
		this.listFavs = listFavs;
		this.comentariosList = comentariosList;
		this.listValoraciones = listValoraciones;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public List<Integer> getListFavs() {
		return listFavs;
	}
	public void setListFavs(List<Integer> listFavs) {
		this.listFavs = listFavs;
	}
	public List<Comentario> getComentariosList() {
		return comentariosList;
	}
	public void setComentariosList(List<Comentario> comentariosList) {
		this.comentariosList = comentariosList;
	}

	public List<Valoracion> getListValoraciones() {
		return listValoraciones;
	}

	public void setListValoraciones(List<Valoracion> listValoraciones) {
		this.listValoraciones = listValoraciones;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", enabled=" + enabled + ", role=" + role + ", token=" + token + ", listFavs=" + listFavs
				+ ", comentariosList=" + comentariosList + ", listValoraciones=" + listValoraciones + "]";
	}
	
}

