package tribe.controller.dto;

import tribe.domain.habitTracking.HabitContract;

public class HabitContractDto {

	protected String id;

	protected String commitment;
	
	protected String punishment;
	
	protected String accountablePartnerId;
	
	protected String accountablePartnerName;
	
	public HabitContractDto() {}
	
	public HabitContractDto(HabitContract habitContract) {
		this.id = habitContract.getId();
		this.commitment = habitContract.getCommitment();
		this.punishment = habitContract.getPunishment();
		if (habitContract.getAccountabilityPartner() != null) {
			this.accountablePartnerId = habitContract.getAccountabilityPartner().getId();
			this.accountablePartnerName = habitContract.getAccountabilityPartner().getName();
		}
	}

	public HabitContractDto(String id, String commitment, String punishment, String accountablePartnerId,
			String accountablePartnerName) {
		this.id = id;
		this.commitment = commitment;
		this.punishment = punishment;
		this.accountablePartnerId = accountablePartnerId;
		this.accountablePartnerName = accountablePartnerName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getAccountablePartnerId() {
		return accountablePartnerId;
	}

	public void setAccountablePartnerId(String accountablePartnerId) {
		this.accountablePartnerId = accountablePartnerId;
	}

	public String getAccountablePartnerName() {
		return accountablePartnerName;
	}

	public void setAccountablePartnerName(String accountablePartnerName) {
		this.accountablePartnerName = accountablePartnerName;
	}
}
