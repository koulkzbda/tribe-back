package tribe.service;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tribe.controller.dto.MemberCreatedDto;
import tribe.controller.dto.MemberDto;
import tribe.domain.Member;
import tribe.exception.EmailAlreadyExistsException;
import tribe.exception.NoMemberFoundException;
import tribe.repository.MemberProfileRepo;
import tribe.repository.MemberRepo;
import tribe.service.interfaces.SecurityService;

@Service
public class AuthService {

	protected MemberRepo memberRepo;
	protected MemberProfileRepo memberProfileRepo;
	protected SecurityService securityService;
	protected MessageSource messageSource;
	protected PasswordEncoder passwordEncoder;
	protected EmailServiceImpl emailService;
	protected ProfileService profileService;

	public AuthService(MemberRepo memberRepo, MemberProfileRepo memberProfileRepo, SecurityService securityService,
			MessageSource messageSource, PasswordEncoder passwordEncoder, EmailServiceImpl emailService,
			ProfileService profileService) {
		
		this.memberRepo = memberRepo;
		this.memberProfileRepo = memberProfileRepo;
		this.securityService = securityService;
		this.messageSource = messageSource;
		this.passwordEncoder = passwordEncoder;
		this.emailService = emailService;
		this.profileService = profileService;
	}

	@Transactional
	public MemberDto getMemberWithNewToken() {
		String token = this.securityService.getNewToken();

		return new MemberDto(
				memberRepo.findByEmail(securityService.getUserEmail()).orElseThrow(NoMemberFoundException::new), token);

	}

	@Transactional
	public MemberCreatedDto createMember(MemberCreatedDto member) {
		memberRepo.findByEmail(member.getEmail()).ifPresent(mb -> {
			throw new EmailAlreadyExistsException(messageSource.getMessage("errorMessage.EmailAlreadyExists",
					new Object[] { member.getEmail() }, LocaleContextHolder.getLocale()));
		});
		member.setPassword(passwordEncoder.encode(member.getPassword()));

		Member memberCreated = new Member(member);
		UUID uuid = UUID.randomUUID();
		String confirmationToken = uuid.toString();
		memberCreated.setConfirmationToken(confirmationToken);
		memberCreated = profileService.addNewProfile(memberCreated);
		memberCreated = memberRepo.save(memberCreated);
		sendAccountConfirmationEmail(memberCreated, member.getEmailConfirmationUrlTemplate());

		return new MemberCreatedDto(member);
	}

	@Transactional
	public MemberDto confirmMember(String id, String token) {
		Member member = memberRepo.findById(id).orElseThrow(() -> new NoMemberFoundException(
				messageSource.getMessage("errorMessage.NoMemberFoundWithId", null, LocaleContextHolder.getLocale())
						+ id));

		if (member.getConfirmationToken() == null) {
			throw new NoMemberFoundException(messageSource.getMessage("errorMessage.InvalidConfirmationToken",
					new Object[] { member.getConfirmationToken() }, LocaleContextHolder.getLocale()));
		}
		if (!member.getConfirmationToken().equals(token)) {
			throw new NoMemberFoundException(messageSource.getMessage("errorMessage.InvalidConfirmationToken",
					new Object[] { token }, LocaleContextHolder.getLocale()));
		}
		member.setConfirmationToken(null);
		member.setIsConfirmed(true);
		memberRepo.save(member);

		return new MemberDto(member);
	}

	public MemberDto forgetPassword(String email, String forgetPasswordUrl) {
		Member member = memberRepo.findByEmail(email).orElseThrow(() -> new NoMemberFoundException(messageSource
				.getMessage("errorMessage.NoMemberFoundWithEmail" + email, null, LocaleContextHolder.getLocale())));
		UUID uuid = UUID.randomUUID();
		String forgetPasswordToken = uuid.toString();
		member.setConfirmationToken(forgetPasswordToken);
		sendResetPasswordEmail(member, forgetPasswordUrl);
		memberRepo.save(member);

		return new MemberDto(null, null, email, null);
	}

	public MemberDto resetPassword(String id, String token, String password) {
		Member member = memberRepo.findById(id).orElseThrow(() -> new NoMemberFoundException(
				messageSource.getMessage("errorMessage.NoMemberFoundWithId", null, LocaleContextHolder.getLocale())
						+ id));
		
		if (member.getConfirmationToken() == null) {
			throw new NoMemberFoundException(messageSource.getMessage("errorMessage.InvalidConfirmationToken",
					new Object[] { member.getConfirmationToken() }, LocaleContextHolder.getLocale()));
		}
		
		if (!member.getConfirmationToken().equals(token)) {
			throw new NoMemberFoundException(messageSource.getMessage("errorMessage.InvalidConfirmationToken",
					new Object[] { token }, LocaleContextHolder.getLocale()));
		}
		member.setConfirmationToken(null);
		member.setPass(passwordEncoder.encode(password));
		memberRepo.save(member);

		return new MemberDto(member);
	}

	protected void sendResetPasswordEmail(Member member, String forgetPasswordUrl) {

		String text = messageSource.getMessage("service.AuthService.resetPasswordEmail",
				new Object[] { member.getEmail(),
						forgetPasswordUrl + ";token=" + member.getConfirmationToken() + ";id=" + member.getId() },
				LocaleContextHolder.getLocale());

		emailService.sendSimpleMessage(member.getEmail(), messageSource.getMessage(
				"service.AuthService.resetPasswordEmailSubject", null, LocaleContextHolder.getLocale()), text);
	}

	protected void sendAccountConfirmationEmail(Member member, String confirmationUrlTemplate) {

		String text = messageSource.getMessage("service.AuthService.confirmationEmail",
				new Object[] { member.getFirstName(),
						confirmationUrlTemplate + ";token=" + member.getConfirmationToken() + ";id=" + member.getId() },
				LocaleContextHolder.getLocale());

		emailService.sendSimpleMessage(member.getEmail(), messageSource.getMessage(
				"service.AuthService.confirmationEmailSubject", null, LocaleContextHolder.getLocale()), text);
	}

}
