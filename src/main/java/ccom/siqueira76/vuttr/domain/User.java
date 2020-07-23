package ccom.siqueira76.vuttr.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import ccom.siqueira76.vuttr.domain.enums.Perfil;

@Entity
@Table(name = "W_USER")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(unique = true)
	@NotEmpty(message = "Preenchimento Obrigatorio")
	private String email;

	@NotEmpty(message = "Preenchimento Obrigatorio")
	private String senha;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "PERFIS")
	private Set<Integer> perfis = new HashSet<>();

	public User() {
		addPerfil(Perfil.CLIENTE);
	}

	public User(Integer id, @NotEmpty(message = "Preenchimento Obrigatorio") String email,
			@NotEmpty(message = "Preenchimento Obrigatorio") String senha) {
		super();
		this.id = id;
		this.email = email;
		this.senha = senha;
		addPerfil(Perfil.CLIENTE);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}

	public void addPerfil(Perfil perfil) {
		perfis.add(perfil.getCod());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
