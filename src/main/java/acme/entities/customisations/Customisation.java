
package acme.entities.customisations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customisation extends DomainEntity {

	//Serialisation indetifier ------------------------

	private static final long	serialVersionUID	= 1L;

	//Atributes ---------------------------------------

	@NotBlank
	@Column(length = 1024)
	private String				customisations;


	@NotNull
	private Integer				threshold;

}
