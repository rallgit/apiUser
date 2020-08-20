package apiUser.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="phone")
public class Phone {

	@Id
	@Column( name = "id")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@GeneratedValue( strategy = GenerationType.IDENTITY,generator = "uuid" )
	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getCitycode() {
		return citycode;
	}

	public void setCitycode(int citycode) {
		this.citycode = citycode;
	}

	public int getContrycode() {
		return contrycode;
	}

	public void setContrycode(int contrycode) {
		this.contrycode = contrycode;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	@Column( name = "number")
	private int number;
	
	@Column( name = "citycode")
	private int citycode;
	
	@Column( name = "contrycode")
	private int contrycode;
	
	@Column( name = "idUser")
	private String idUser;
	
}
