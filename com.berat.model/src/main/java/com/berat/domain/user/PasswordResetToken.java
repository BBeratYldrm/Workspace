package com.berat.domain.user;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
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
@Table(name = "PasswordResetToken")
@NamedQueries({
		@NamedQuery(name = "PasswordResetToken.findByToken", query = "SELECT p FROM PasswordResetToken p WHERE p.token = :token"),
		@NamedQuery(name = "PasswordResetToken.findByUserId", query = "SELECT p FROM PasswordResetToken p WHERE p.user = :userId"),
		@NamedQuery(name = "PasswordResetToken.deleteAllExpiredTokens", query = "SELECT p FROM PasswordResetToken p WHERE p.expiryDate <= :date"),
		@NamedQuery(name = "PasswordResetToken.findAllByExpiredTokens", query = "SELECT p FROM PasswordResetToken p WHERE p.expiryDate <= :expiryDate")

})
public class PasswordResetToken {

	@Transient
	private final int EXPIRY_DATE = 24 * 60;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "prt_id")
	private long id;

	@Column(name = "prt_token")
	private String token;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", foreignKey = @ForeignKey(foreignKeyDefinition = "user_fk"))
	private User user;

	@Temporal(TemporalType.TIMESTAMP)
	private Date expiryDate;

	public PasswordResetToken() {
		this.expiryDate = calculateExpiryDate(EXPIRY_DATE);

	}

	private Date calculateExpiryDate(int eXPIRY_DATE2) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(new Date().getTime());
		calendar.add(Calendar.DATE, EXPIRY_DATE);

		return new Date(calendar.getTime().getTime());
	}

	public PasswordResetToken(String token, User user) {
		this.token = token;
		this.user = user;

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
		result = prime * result + (int) (id ^ (id >>> 32));
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
		PasswordResetToken other = (PasswordResetToken) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
