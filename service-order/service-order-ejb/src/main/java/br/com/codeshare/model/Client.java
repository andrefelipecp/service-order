package br.com.codeshare.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.codeshare.annotation.validator.PhoneLengthPattern;

@Entity
@NamedQueries(
		{@NamedQuery(name="Client.findAll", query="select distinct c from Client c"),
		@NamedQuery(name="Client.findByNameEager",query="select distinct c from Client c join fetch c.phones where lower (c.name) like :name")})
public class Client implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public static final String FIND_ALL = "Client.findAll";
	public static final String FIND_BY_NAME_EAGER = "Client.findByNameEager";
	
	@SequenceGenerator(name="SEQ_CLIENT",sequenceName="SEQ_CLIENT",initialValue=1,allocationSize=1)
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_CLIENT")
	@Column(name="client_id")
	private Long id;
	
	@NotNull(message="{name.notempty}")
	@NotEmpty(message="{name.notempty}")
	private String name;
	
	private String adress;
	
	@PhoneLengthPattern(min=13,max=14)
	@Column(name="home_phone")
	private String homePhone;
	
	@PhoneLengthPattern(min=13,max=14)
	@Column(name="business_phone")
	private String bisenessPhone;
	
	@OneToMany(mappedBy="client")
	private List<ServiceOrder> ordemServicos;
	
	@OneToMany(mappedBy="client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Phone> phones;
	
	public Client() {
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getHomePhone() {
		return homePhone;
	}
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}
	public String getBisenessPhone() {
		return bisenessPhone;
	}
	public void setBisenessPhone(String bisenessPhone) {
		this.bisenessPhone = bisenessPhone;
	}
	public List<ServiceOrder> getOrdemServicos() {
		return ordemServicos;
	}
	public void setOrdemServicos(List<ServiceOrder> ordemServicos) {
		this.ordemServicos = ordemServicos;
	}
	public List<Phone> getPhones() {
		return phones;
	}
	public void setTelefones(List<Phone> phones) {
		this.phones = phones;
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
		Client other = (Client) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
