package tribe;

import java.util.Arrays;

import javax.transaction.Transactional;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import tribe.domain.Member;
import tribe.domain.Profile;
import tribe.domain.ProfilePictures;
import tribe.domain.RoleMember;
import tribe.domain.enumaration.Role;
import tribe.repository.MemberRepo;
import tribe.repository.ProfilePicturesRepo;
import tribe.repository.ProfileRepo;

@Component
@Transactional
public class StartupListener {

	private MemberRepo memberRepo;
	private ProfileRepo profileRepo;
	private ProfilePicturesRepo profilePicturesRepo;
	private PasswordEncoder passwordEncoder;

	public StartupListener(
			PasswordEncoder passwordEncoder,
			MemberRepo memberRepo,
			ProfileRepo profileRepo,
			ProfilePicturesRepo profilePicturesRepo
			) {
		this.passwordEncoder = passwordEncoder;
		this.memberRepo = memberRepo;
		this.profileRepo = profileRepo;
		this.profilePicturesRepo = profilePicturesRepo;
	}

	@EventListener(ContextRefreshedEvent.class)
	public void onStart() {

		if (memberRepo.count() < 1) {

			// Creating users

			Member user1 = new Member();
			user1.setLastName("Admin");
			user1.setFirstName("DEV");
			user1.setEmail("admin@dev.fr");
			user1.setPass(passwordEncoder.encode("superpass"));
			user1.setRoles(Arrays.asList(new RoleMember(user1, Role.ROLE_ADMIN), new RoleMember(user1, Role.ROLE_USER),
					new RoleMember(user1, Role.ROLE_MODERATOR)));
			this.memberRepo.save(user1);

//			Member user2 = new Member();
//			user2.setLastName("User");
//			user2.setFirstName("DEV");
//			user2.setEmail("user@dev.fr");
//			user2.setPass(passwordEncoder.encode("superpass"));
//			user2.setRoles(Arrays.asList(new RoleMember(user2, Role.ROLE_USER)));
//			this.memberRepo.save(user2);
//
//			Member user3 = new Member();
//			user3.setLastName("Moderator");
//			user3.setFirstName("DEV");
//			user3.setEmail("moderator@dev.fr");
//			user3.setPass(passwordEncoder.encode("superpass"));
//			user3.setRoles(
//					Arrays.asList(new RoleMember(user3, Role.ROLE_USER), new RoleMember(user3, Role.ROLE_MODERATOR)));
//			this.memberRepo.save(user3);
//
//			Member user4 = new Member();
//			user4.setLastName("User4");
//			user4.setFirstName("DEV");
//			user4.setEmail("user4@dev.fr");
//			user4.setPass(passwordEncoder.encode("superpass"));
//			user4.setRoles(Arrays.asList(new RoleMember(user4, Role.ROLE_USER)));
//			this.memberRepo.save(user4);
//
//			Member user5 = new Member();
//			user5.setLastName("User5");
//			user5.setFirstName("DEV");
//			user5.setEmail("user5@dev.fr");
//			user5.setPass(passwordEncoder.encode("superpass"));
//			user5.setRoles(Arrays.asList(new RoleMember(user5, Role.ROLE_USER)));
//			this.memberRepo.save(user5);
//
//			Member user7 = new Member();
//			user7.setLastName("User7");
//			user7.setFirstName("DEV");
//			user7.setEmail("user7@dev.fr");
//			user7.setPass(passwordEncoder.encode("superpass"));
//			user7.setRoles(Arrays.asList(new RoleMember(user7, Role.ROLE_USER)));
//			this.memberRepo.save(user7);
//
//			Member user8 = new Member();
//			user8.setLastName("User8");
//			user8.setFirstName("DEV");
//			user8.setEmail("user8@dev.fr");
//			user8.setPass(passwordEncoder.encode("superpass"));
//			user8.setRoles(Arrays.asList(new RoleMember(user8, Role.ROLE_USER)));
//			this.memberRepo.save(user8);
//
//			Member user9 = new Member();
//			user9.setLastName("Julo");
//			user9.setFirstName("DEV");
//			user9.setEmail("souleymanethiam22@gmail.com");
//			user9.setPass(passwordEncoder.encode("superpass"));
//			user9.setRoles(
//					Arrays.asList(new RoleMember(user9, Role.ROLE_USER), new RoleMember(user9, Role.ROLE_MODERATOR)));
//			this.memberRepo.save(user9);
//
//			Member teamColor = new Member();
//			teamColor.setLastName("Team Color");
//			teamColor.setFirstName("DEV");
//			teamColor.setEmail("color.team.dta@gmail.com");
//			teamColor.setPass(passwordEncoder.encode("superpass"));
//			teamColor.setRoles(Arrays.asList(new RoleMember(teamColor, Role.ROLE_USER)));
//			this.memberRepo.save(teamColor);
//
//			Member koulk = new Member();
//			koulk.setLastName("Koulk");
//			koulk.setFirstName("Zbda");
//			koulk.setEmail("koulkzbda@gmail.com");
//			koulk.setPass(passwordEncoder.encode("superpass"));
//			koulk.setRoles(Arrays.asList(new RoleMember(koulk, Role.ROLE_USER)));
//			this.memberRepo.save(koulk);
			
			//  Create profiles
			ProfilePictures profilePictures1 = new ProfilePictures();
			Profile profile1 = new Profile("This is my bio", profilePictures1, user1);
			user1.setProfile(profile1);
			profile1.setMember(user1);
			profilePictures1.setProfile(profile1);
			this.profilePicturesRepo.save(profilePictures1);
		}
	}
}
