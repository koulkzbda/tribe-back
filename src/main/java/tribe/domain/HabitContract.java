package tribe.domain;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;


@Entity
public class HabitContract {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	protected String id;
	
	protected String commitment;
	
    protected String punishment;
    
    @CreationTimestamp
    protected LocalDateTime createdAt;
    
    protected LocalDateTime signedAt;
    
    protected LocalDateTime endedAt;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    protected Progression progression;
    
    @ManyToOne(cascade = CascadeType.ALL)
    protected Member accountabilityPartner;

    public HabitContract() {}

	public HabitContract(String commitment, String punishment, Progression progression, Member accountabilityPartner) {
		this.commitment = commitment;
		this.punishment = punishment;
		this.progression = progression;
		this.accountabilityPartner = accountabilityPartner;
	}
	
	public void sign() {
		this.signedAt = LocalDateTime.now();
	}
	
	public void end() {
		this.endedAt = LocalDateTime.now();
	}

	public String getCommitment() {
		return commitment;
	}

	public void setCommitment(String commitment) {
		this.commitment = commitment;
	}

	public String getPunishment() {
		return punishment;
	}

	public void setPunishment(String punishment) {
		this.punishment = punishment;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getSignedAt() {
		return signedAt;
	}

	public void setSignedAt(LocalDateTime signedAt) {
		this.signedAt = signedAt;
	}

	public LocalDateTime getEndedAt() {
		return endedAt;
	}

	public void setEndedAt(LocalDateTime endedAt) {
		this.endedAt = endedAt;
	}

	public Progression getProgression() {
		return progression;
	}

	public void setProgression(Progression progression) {
		this.progression = progression;
	}

	public Member getAccountabilityPartner() {
		return accountabilityPartner;
	}

	public void setAccountabilityPartner(Member accountabilityPartner) {
		this.accountabilityPartner = accountabilityPartner;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
