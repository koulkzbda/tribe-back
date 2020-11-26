package tribe;

import java.util.Arrays;

import javax.transaction.Transactional;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import tribe.domain.Profile;
import tribe.domain.RoleUser;
import tribe.domain.User;
import tribe.domain.enumaration.Role;
import tribe.repository.ProfileRepo;
import tribe.repository.UserRepo;

@Component
@Transactional
public class StartupListener {

	private UserRepo userRepo;
	private ProfileRepo profileRepo;
	private PasswordEncoder passwordEncoder;

	public StartupListener(
			PasswordEncoder passwordEncoder,
			UserRepo userRepo,
			ProfileRepo profileRepo
			) {
		this.passwordEncoder = passwordEncoder;
		this.userRepo = userRepo;
		this.profileRepo = profileRepo;
	}

	@EventListener(ContextRefreshedEvent.class)
	public void onStart() {

		if (userRepo.count() < 1) {

			// Creating users

			User user1 = new User();
			user1.setLastName("Admin");
			user1.setFirstName("DEV");
			user1.setEmail("admin@dev.fr");
			user1.setPass(passwordEncoder.encode("superpass"));
			user1.setRoles(Arrays.asList(new RoleUser(user1, Role.ROLE_ADMIN), new RoleUser(user1, Role.ROLE_USER),
					new RoleUser(user1, Role.ROLE_MODERATOR)));
			this.userRepo.save(user1);

			User user2 = new User();
			user2.setLastName("User");
			user2.setFirstName("DEV");
			user2.setEmail("user@dev.fr");
			user2.setPass(passwordEncoder.encode("superpass"));
			user2.setRoles(Arrays.asList(new RoleUser(user2, Role.ROLE_USER)));
			this.userRepo.save(user2);

			User user3 = new User();
			user3.setLastName("Moderator");
			user3.setFirstName("DEV");
			user3.setEmail("moderatorr@dev.fr");
			user3.setPass(passwordEncoder.encode("superpass"));
			user3.setRoles(
					Arrays.asList(new RoleUser(user3, Role.ROLE_USER), new RoleUser(user3, Role.ROLE_MODERATOR)));
			this.userRepo.save(user3);

			User user4 = new User();
			user4.setLastName("User4");
			user4.setFirstName("DEV");
			user4.setEmail("user4@dev.fr");
			user4.setPass(passwordEncoder.encode("superpass"));
			user4.setRoles(Arrays.asList(new RoleUser(user4, Role.ROLE_USER)));
			this.userRepo.save(user4);

			User user5 = new User();
			user5.setLastName("User5");
			user5.setFirstName("DEV");
			user5.setEmail("user5@dev.fr");
			user5.setPass(passwordEncoder.encode("superpass"));
			user5.setRoles(Arrays.asList(new RoleUser(user5, Role.ROLE_USER)));
			this.userRepo.save(user5);

			User user7 = new User();
			user7.setLastName("User7");
			user7.setFirstName("DEV");
			user7.setEmail("user7@dev.fr");
			user7.setPass(passwordEncoder.encode("superpass"));
			user7.setRoles(Arrays.asList(new RoleUser(user7, Role.ROLE_USER)));
			this.userRepo.save(user7);

			User user8 = new User();
			user8.setLastName("User8");
			user8.setFirstName("DEV");
			user8.setEmail("user8@dev.fr");
			user8.setPass(passwordEncoder.encode("superpass"));
			user8.setRoles(Arrays.asList(new RoleUser(user8, Role.ROLE_USER)));
			this.userRepo.save(user8);

			User user9 = new User();
			user9.setLastName("Julo");
			user9.setFirstName("DEV");
			user9.setEmail("souleymanethiam22@gmail.com");
			user9.setPass(passwordEncoder.encode("superpass"));
			user9.setRoles(
					Arrays.asList(new RoleUser(user9, Role.ROLE_USER), new RoleUser(user9, Role.ROLE_MODERATOR)));
			this.userRepo.save(user9);

			User teamColor = new User();
			teamColor.setLastName("Team Color");
			teamColor.setFirstName("DEV");
			teamColor.setEmail("color.team.dta@gmail.com");
			teamColor.setPass(passwordEncoder.encode("superpass"));
			teamColor.setRoles(Arrays.asList(new RoleUser(teamColor, Role.ROLE_USER)));
			this.userRepo.save(teamColor);

			User koulk = new User();
			koulk.setLastName("Koulk");
			koulk.setFirstName("Zbda");
			koulk.setEmail("koulkzbda@gmail.com");
			koulk.setPass(passwordEncoder.encode("superpass"));
			koulk.setRoles(Arrays.asList(new RoleUser(koulk, Role.ROLE_USER)));
			this.userRepo.save(koulk);
			
			//  Create profiles
			Profile profile1 = new Profile("This my bio", user1);
			this.profileRepo.save(profile1);
		}
	}
}
