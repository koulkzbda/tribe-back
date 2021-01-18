package tribe.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import tribe.domain.enumaration.RepetitionStatusEnum;

@Entity
public class RepetitionStatus {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	protected String id;

    @Enumerated(EnumType.STRING)
    private RepetitionStatusEnum repetitionStatus;
    
    public RepetitionStatus() {
    }

	public RepetitionStatus(RepetitionStatusEnum repetitionStatus) {
		this.repetitionStatus = repetitionStatus;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public RepetitionStatusEnum getRepetitionStatus() {
		return repetitionStatus;
	}

	public void setRepetitionStatus(RepetitionStatusEnum repetitionStatus) {
		this.repetitionStatus = repetitionStatus;
	}
}
