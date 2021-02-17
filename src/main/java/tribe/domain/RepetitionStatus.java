package tribe.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import tribe.domain.enumaration.RepetitionStatusEnum;

@Entity
public class RepetitionStatus {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	protected String id;
	
	@CreationTimestamp
	protected LocalDateTime createdAt;
	
	@UpdateTimestamp
    protected LocalDateTime updatedAt;

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

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
}
