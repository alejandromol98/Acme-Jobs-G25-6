
package acme.entities.companyRecords;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CompanyRecord extends DomainEntity {

	/**
	 *
	 */
	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				name;

	@NotBlank
	private String				sector;

	@NotBlank
	private String				CEO;

	@Column(length = 1024)
	@NotBlank
	private String				description;

	@URL
	private String				website;

	@Pattern(regexp = "\\+\\d{1,3}\\s\\(\\d{1,4}\\)\\s\\d{6,10}")
	private String				telephone;

	@NotBlank
	@Email
	private String				email;

	private boolean				incorporated;

}
