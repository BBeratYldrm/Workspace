package com.berat.domain.user;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "Doðrulama")
@NamedQueries({
	@NamedQuery(name = "VerificationToken.findByToken", query = "SELECT v FROM VerificationToken v WHERE v.token = :token"),
	@NamedQuery(name = "VerificationToken.findByUserId", query = "SELECT v FROM VerificationToken v WHERE v.user = :userId"),
	@NamedQuery(name = "VerificationToken.deleteAllExpiredTokens", query = "SELECT v FROM VerificationToken v WHERE v.expiryDate <= :date"),
	@NamedQuery(name = "VerificationToken.findAllByExpiredTokens", query = "SELECT v FROM VerificationToken v WHERE v.expiryDate <= :expiryDate")

})
public class VerificationToken {

	@Transient
	private final int EXPIRY_DATE = 60 * 24;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private long Id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;

	@Column
	private String token;

	@Temporal(TemporalType.TIMESTAMP)
	private Date expiryDate;

	public VerificationToken(User user, String token) {
		this.user = user;
		this.token = token;
	}

	public VerificationToken() {
		this.expiryDate = calculateExpiryDate(EXPIRY_DATE);
	}

	private Date calculateExpiryDate(int eXPIRY_DATE2) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(new Date().getTime());
		calendar.add(Calendar.DATE, EXPIRY_DATE);

		return new Date(calendar.getTime().getTime());
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public int getEXPIRY_DATE() {
		return EXPIRY_DATE;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (Id ^ (Id >>> 32));
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
		VerificationToken other = (VerificationToken) obj;
		if (Id != other.Id)
			return false;
		return true;
	}

}
