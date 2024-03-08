package ngoproject.donation;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import ngoproject.user.User;

@Entity
@Table(name="donation")
@Getter
@Setter
class Donation {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	private Integer eventId;
	private Integer amount;
	private Date donationDate;
	private boolean reocurring;
	private String firstName;
	private String lastName;
	private String phone;
	private String address;
	private String city;
	private String state;
	private String zipcode;
	private String country;
	
	@ManyToOne
	@JoinColumn(name="userId", nullable=false)
	private User user;
	
	
//	What to do with General Donation Fund, Run For the Sun, Mission Trip sponsorship, Memorial Gift?
// reocurring boolean, amount. Each of the above have their own reocurring
}
