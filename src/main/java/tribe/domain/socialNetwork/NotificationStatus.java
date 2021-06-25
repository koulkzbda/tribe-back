package tribe.domain.socialNetwork;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import tribe.domain.enumaration.NotificationStatusEnum;

@Entity
public class NotificationStatus {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	protected String id;

    @Enumerated(EnumType.STRING)
    private NotificationStatusEnum notificationStatus;
    
    @UpdateTimestamp
    protected LocalDateTime updatedAt;
    
    public NotificationStatus() {
    }
    
	public NotificationStatus(NotificationStatusEnum notificationStatus) {
		this.notificationStatus = notificationStatus;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public NotificationStatusEnum getNotificationStatus() {
		return notificationStatus;
	}

	public void setNotificationStatus(NotificationStatusEnum notificationStatus) {
		this.notificationStatus = notificationStatus;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
}
