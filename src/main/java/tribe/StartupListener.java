package tribe;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import tribe.domain.CommentOfComment;
import tribe.domain.CommentOfPublication;
import tribe.domain.Habit;
import tribe.domain.HabitContract;
import tribe.domain.HabitStack;
import tribe.domain.Identity;
import tribe.domain.IdentityCategory;
import tribe.domain.Like;
import tribe.domain.Location;
import tribe.domain.Member;
import tribe.domain.MemberProfile;
import tribe.domain.MemberProfilePictures;
import tribe.domain.MemberWall;
import tribe.domain.MemberWallNotification;
import tribe.domain.Membership;
import tribe.domain.Metric;
import tribe.domain.MetricValue;
import tribe.domain.NotificationStatus;
import tribe.domain.Progression;
import tribe.domain.Publication;
import tribe.domain.PublicationPictures;
import tribe.domain.ReactionNotification;
import tribe.domain.Repetition;
import tribe.domain.RepetitionStatus;
import tribe.domain.RoleMember;
import tribe.domain.RoleTribe;
import tribe.domain.Step;
import tribe.domain.System;
import tribe.domain.Tribe;
import tribe.domain.TribeCategory;
import tribe.domain.TribeProfile;
import tribe.domain.TribeProfilePictures;
import tribe.domain.TribeWall;
import tribe.domain.Weekday;
import tribe.domain.Weighting;
import tribe.domain.enumaration.IdentityCategoryEnum;
import tribe.domain.enumaration.NotificationStatusEnum;
import tribe.domain.enumaration.RepetitionStatusEnum;
import tribe.domain.enumaration.Role;
import tribe.domain.enumaration.RoleTribeEnum;
import tribe.domain.enumaration.WeekdayEnum;
import tribe.repository.HabitRepo;
import tribe.repository.HabitStackRepo;
import tribe.repository.IdentityCategoryRepo;
import tribe.repository.IdentityRepo;
import tribe.repository.LikeRepo;
import tribe.repository.MemberProfilePicturesRepo;
import tribe.repository.MemberRepo;
import tribe.repository.ProgressionRepo;
import tribe.repository.PublicationRepo;
import tribe.repository.StepRepo;
import tribe.repository.SystemRepo;
import tribe.repository.TribeRepo;
import tribe.repository.WeightingRepo;

@Component
@Transactional
public class StartupListener {

	private MemberRepo memberRepo;
	private PublicationRepo publicationRepo;
	private LikeRepo likeRepo;
	private SystemRepo systemRepo;
	private WeightingRepo weightingRepo;
	private HabitRepo habitRepo;
	private StepRepo stepRepo;
	private ProgressionRepo progressionRepo;
	private IdentityRepo identityRepo;
	private HabitStackRepo habitStackRepo;
	private IdentityCategoryRepo identityCategoryRepo;
	private MemberProfilePicturesRepo memberProfilePicturesRepo;
	private TribeRepo tribeRepo;
	private PasswordEncoder passwordEncoder;

	public StartupListener(
			PasswordEncoder passwordEncoder,
			MemberRepo memberRepo,
			MemberProfilePicturesRepo memberProfilePicturesRepo,
			PublicationRepo publicationRepo,
			LikeRepo likeRepo,
			SystemRepo systemRepo,
			IdentityCategoryRepo identityCategoryRepo,
			WeightingRepo weightingRepo,
			HabitRepo habitRepo,
			ProgressionRepo progressionRepo,
			IdentityRepo identityRepo,
			HabitStackRepo habitStackRepo,
			StepRepo stepRepo,
			TribeRepo tribeRepo
			) {
		this.passwordEncoder = passwordEncoder;
		this.memberRepo = memberRepo;
		this.memberProfilePicturesRepo = memberProfilePicturesRepo;
		this.publicationRepo = publicationRepo;
		this.likeRepo = likeRepo;
		this.systemRepo = systemRepo;
		this.identityCategoryRepo = identityCategoryRepo;
		this.weightingRepo = weightingRepo;
		this.habitRepo = habitRepo;
		this.identityRepo = identityRepo;
		this.progressionRepo = progressionRepo;
		this.habitStackRepo = habitStackRepo;
		this.stepRepo = stepRepo;
		this.tribeRepo = tribeRepo;
	}

	@EventListener(ContextRefreshedEvent.class)
	public void onStart() {

		if (memberRepo.count() < 1) {

			// Creating users

			Member user1 = new Member();
			user1.setLastName("Dev");
			user1.setFirstName("Admin");
			user1.setEmail("admin@dev.fr");
			user1.setPass(passwordEncoder.encode("superpass"));
			user1.setRoles(Arrays.asList(new RoleMember(user1, Role.ROLE_ADMIN), new RoleMember(user1, Role.ROLE_USER),
					new RoleMember(user1, Role.ROLE_MODERATOR)));
			this.memberRepo.save(user1);

			Member user2 = new Member();
			user2.setLastName("Dev");
			user2.setFirstName("Member");
			user2.setEmail("user@dev.fr");
			user2.setPass(passwordEncoder.encode("superpass"));
			user2.setRoles(Arrays.asList(new RoleMember(user2, Role.ROLE_USER)));
			this.memberRepo.save(user2);
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
			MemberProfilePictures profilePictures1 = new MemberProfilePictures();
			MemberProfile profile1 = new MemberProfile("This is my bio", profilePictures1, user1);
			user1.setMemberProfile(profile1);
			profile1.setMember(user1);
			profilePictures1.setMemberProfile(profile1);
			this.memberProfilePicturesRepo.save(profilePictures1);
			
			MemberProfilePictures profilePictures2 = new MemberProfilePictures();
			MemberProfile profile2 = new MemberProfile("I'm the user", profilePictures2, user2);
			user2.setMemberProfile(profile2);
			profile2.setMember(user2);
			profilePictures2.setMemberProfile(profile2);
			this.memberProfilePicturesRepo.save(profilePictures2);
			
			//  Create publications
			List<Like> likes1 = new ArrayList<>();
			Like like1 = new Like(user2, null);
			
			
			Publication publication1 = new Publication("First contribution", null, user2, null);
			PublicationPictures publicationPictures1 = new PublicationPictures(null, publication1);
			publication1.setPublicationPictures(publicationPictures1);
			like1.setPublication(publication1);
			likes1.add(like1);
			publication1.setLikes(likes1);
			
			
			//  Create comments of publication
			List<CommentOfPublication> commentsOfPublication1 = new ArrayList<>();
			List<CommentOfComment> commentsOfComment1 = new ArrayList<>();
			List<CommentOfComment> commentsOfComment2 = new ArrayList<>();
			
			CommentOfPublication commentOfPublication1 = new CommentOfPublication("Petit commentaire", user2, null, publication1);
			CommentOfPublication commentOfPublication2 = new CommentOfPublication("Petit bateau", user2, null, publication1);
			CommentOfComment commentOfComment1 = new CommentOfComment("J'aime ton petit commentaire", user1, commentOfPublication1);
			CommentOfComment commentOfComment2 = new CommentOfComment("Savon de Marseille", user1, commentOfPublication2);
			commentsOfComment1.add(commentOfComment1);
			commentsOfComment2.add(commentOfComment2);
			commentOfPublication1.setCommentsOfComment(commentsOfComment1);
			commentOfPublication2.setCommentsOfComment(commentsOfComment2);
			commentsOfPublication1.add(commentOfPublication1);
			commentsOfPublication1.add(commentOfPublication2);
			
			publication1.setCommentsOfPublication(commentsOfPublication1);
			
			List<Publication> publications1 = new ArrayList<>();
			publications1.add(publication1);
			
			MemberWall memberWall1 = new MemberWall(publications1, user1);
			
				// Create reaction notifications
			NotificationStatus notificationStatus1 = new NotificationStatus(NotificationStatusEnum.NOT_SEEN);
			
			ReactionNotification reactionNotification1 = new ReactionNotification(notificationStatus1, user1, publication1);
			publication1.setReactionNotification(reactionNotification1);
			
			// Create wall notifications
			NotificationStatus notificationStatus2 = new NotificationStatus(NotificationStatusEnum.NOT_SEEN);
			
			MemberWallNotification memberWellNotification1 = new MemberWallNotification(notificationStatus2, user1, memberWall1);
			memberWall1.setMemberWallNotification(memberWellNotification1);
			publication1.setMemberWall(memberWall1);
			
			
			this.publicationRepo.save(publication1);
			this.likeRepo.save(like1);
			
		//  Create Tribes
					Tribe tribe1 = new Tribe("Les loubards", "C'est pour les vrais.", true, null, null, null);
					TribeWall tribeWall1 = new TribeWall(null, tribe1);
					TribeProfile tribeProfile1 = new TribeProfile("On aime la choucroute ici.", null, tribe1);
					TribeProfilePictures tribeProfilePictures1 = new TribeProfilePictures(null, tribeProfile1);
					tribeProfile1.setTribeProfilePictures(tribeProfilePictures1);
					tribe1.setTribeProfile(tribeProfile1);
					tribe1.setTribeWall(tribeWall1);
					
					List<Tribe> tribes1 = new ArrayList<>();
					tribes1.add(tribe1);
					
					TribeCategory tribeCategory1 = new TribeCategory("Seduction", "Améliore votre jeu de séduction.", true, tribes1);
					TribeCategory tribeCategory2 = new TribeCategory("Productivity", "Pour être plus productif.", true, tribes1);
					List<TribeCategory> tribeCategories1 = new ArrayList<>();
					tribeCategories1.add(tribeCategory1);
					tribeCategories1.add(tribeCategory2);
					tribe1.setTribeCategory(tribeCategories1);
					
					Membership membership1 = new Membership(user1, tribe1, null);
					RoleTribe roleTribe1 = new RoleTribe(RoleTribeEnum.CHIEF, membership1);
					RoleTribe roleTribe2 = new RoleTribe(RoleTribeEnum.MEMBER, membership1);
					RoleTribe roleTribe3 = new RoleTribe(RoleTribeEnum.PILAR, membership1);
					RoleTribe roleTribe4 = new RoleTribe(RoleTribeEnum.STAFF, membership1);
					
					List<RoleTribe> rolesTribe1 = new ArrayList<>();
					rolesTribe1.add(roleTribe1);
					rolesTribe1.add(roleTribe2);
					rolesTribe1.add(roleTribe3);
					rolesTribe1.add(roleTribe4);
					membership1.setRolesTribe(rolesTribe1);
					
					Membership membership2 = new Membership(user2, tribe1, null);
					RoleTribe roleTribe5 = new RoleTribe(RoleTribeEnum.MEMBER, membership2);
					List<RoleTribe> rolesTribe2 = new ArrayList<>();
					rolesTribe2.add(roleTribe5);
					membership2.setRolesTribe(rolesTribe2);
					
					List<Membership> memberships1 = new ArrayList<>();
					memberships1.add(membership1);
					memberships1.add(membership2);
					
					tribe1.setMemberships(memberships1);
					tribeRepo.save(tribe1);
			
			
			//  Create systems
			System system1 = new System("Magic System", "Avec Magic System y a pas d'problèmes", true, user1, null);
			Identity identity1 = new Identity("Mec cool", 2, system1);
			
			IdentityCategory identityCategory1 = new IdentityCategory(null, IdentityCategoryEnum.ATTRACTIVE);
			IdentityCategory identityCategory2 = new IdentityCategory(null, IdentityCategoryEnum.RELAX);
			Weighting weighting1 = new Weighting(identity1, identityCategory1, 0.5);
			Weighting weighting2 = new Weighting(identity1, identityCategory2, 1.0);
			List<Weighting> weightings1 = new ArrayList<>();
			weightings1.add(weighting1);
			weightings1.add(weighting2);
			identityCategory1.setWeightings(weightings1);
			identityCategory2.setWeightings(weightings1);
			
			Identity identity2 = new Identity("Marathonien", 5, system1);
			
			IdentityCategory identityCategory3 = new IdentityCategory(null, IdentityCategoryEnum.ATHLETIC);
			Weighting weighting3 = new Weighting(identity2, identityCategory1, 0.5);
			Weighting weighting4 = new Weighting(identity2, identityCategory2, 0.5);
			Weighting weighting5 = new Weighting(identity2, identityCategory3, 1.0);
			List<Weighting> weightings2 = new ArrayList<>();
			weightings2.add(weighting5);
			weightings2.add(weighting3);
			weightings2.add(weighting4);
			identityCategory1.setWeightings(weightings2);
			identityCategory2.setWeightings(weightings2);
			identityCategory3.setWeightings(weightings2);
			
			identity1.setWeightings(weightings1);
			identity2.setWeightings(weightings2);
			
			List<Identity> identities1 = new ArrayList<>();
			identities1.add(identity1);
			identities1.add(identity2);
			
			system1.setIdentities(identities1);
			system1.setTribe(tribe1);
			systemRepo.save(system1);
			identityCategoryRepo.save(identityCategory1);
			identityCategoryRepo.save(identityCategory2);
			identityCategoryRepo.save(identityCategory3);
			weightingRepo.saveAll(weightings1);
			weightingRepo.saveAll(weightings2);
			
			//  Create Habits
			List<Identity> identityList1 = new ArrayList<>();
			identityList1.add(identity1);
			Habit habit1 = new Habit("Intellectual masturbation", "Stimuler son intelligence", "Ouvrir un livre", user1, identityList1);
			
			List<Habit> habitList = new ArrayList<>();
			habitList.add(habit1);
			identity1.setHabits(habitList);
			identityList1.set(0, identity1);
			habit1.setIdentities(identityList1);
			
			Location location1 = new Location("Café Carrion", "Avenue Moulay Hassan", "Rabat", 10010, 31.79230f, -7.08016f, null);
			Progression progression1 = new Progression("first version", true, location1, "V1.0", habit1, null, null);
			location1.setHabitVersion(progression1);
			progression1.setLocation(location1);
			
			Step conditionningHabit1 = new Step("Sortir un bouquin et ses boules quiès.", true, location1, null, user1);
			Step reward1 = new Step("Un bon café", true, location1, null, user1);
			
			progression1.setConditionningHabit(conditionningHabit1);
			progression1.setReward(reward1);
			
			List<Progression> progressionList = new ArrayList<>();
			progressionList.add(progression1);
			habit1.setProgressions(progressionList);
			
			habitRepo.save(habit1);
			
				// Habit 2
			List<Identity> identityList2 = new ArrayList<>();
			identityList2.add(identity2);
			
			Habit habit2 = new Habit("Courir tous les jours", "Marathon en moins de 3h30", "Enfiler ses baskets", user1, identityList2);
			
			List<Habit> habitList2 = new ArrayList<>();
			habitList2.add(habit2);
			identity2.setHabits(habitList2);
			identityList2.set(0, identity2);
			habit2.setIdentities(identityList2);
			
			Location location2 = new Location("Moving", "Avenue de la gare, Résidence Naim", "Meknès", 50000, 33.89500f, -5.55472f, null);
			Progression progression2 = new Progression("first version", true, location2, "V1.0", habit2, null, null);
			location2.setHabitVersion(progression2);
			progression2.setLocation(location2);
			
			Metric metric2 = new Metric("Temps", "min", true, null, progression2);
			Metric metric3 = new Metric("Distance parcourue", "km", false, null, progression2);
			
			List<Metric> metrics2 = new ArrayList<>();
			metrics2.add(metric2);
			metrics2.add(metric3);
			progression2.setMetrics(metrics2);
			
			List<Progression> progressionList2 = new ArrayList<>();
			progressionList2.add(progression2);
			habit2.setProgressions(progressionList2);
			
			habitRepo.save(habit2);
			
			Habit habit3 = new Habit("Douche froide tous les jours", "Un mental d'acier", "Se mettre sous la douche", user1, identityList2);
			
			List<Habit> habitList3 = new ArrayList<>();
			habitList3.add(habit2);
			habitList3.add(habit3);
			identity2.setHabits(habitList3);
			identityList2.set(0, identity2);
			habit3.setIdentities(identityList2);
			
			Location location3 = new Location("A la maison", "Avenue des FAR", "Meknès", 50000, 33.89530f, -5.56472f, null);
			Progression progression3 = new Progression("first version", true, location3, "V1.0", habit3, null, null);
			location3.setHabitVersion(progression3);
			progression3.setLocation(location3);
			
			List<Progression> progressionList3 = new ArrayList<>();
			progressionList3.add(progression3);
			habit3.setProgressions(progressionList3);
			
			habitRepo.save(habit3);
			
			//  Create habitContracts
			HabitContract habitContract1 = new HabitContract("Se prendre en photo tous les jours dehors en tenue de sport", "Acheter 1 pizza à Jonhny", progression2, user2);
			progression2.setHabitContract(habitContract1);
			
			//  Create HabitStacks
			HabitStack habitStack1 = new HabitStack("Creator routine", system1, progressionList);
			progression1.setHabitStack(habitStack1);
			
				// Create repetitions
			List<Like> likes2 = new ArrayList<>();
			Like like2= new Like(user2, null);
			
			RepetitionStatus repetitionStatus1 = new RepetitionStatus(RepetitionStatusEnum.DONE);
			
			Metric metric1 = new Metric("Nombre de repétitions", "nb", true, null, progression1);
			
			List<MetricValue> metricValues1 = new ArrayList<>();
			MetricValue metricValue1 = new MetricValue(2f, metric1, null);
			metric1.setMetricValue(metricValue1);
			
			
			Repetition repetition1 = new Repetition("Izi pizi", null, user1, null, repetitionStatus1, progression1, null);
			PublicationPictures publicationPictures2 = new PublicationPictures(null, repetition1);
			repetition1.setPublicationPictures(publicationPictures2);
			metricValue1.setRepetition(repetition1);
			metricValue1.setMetric(metric1);
			metricValues1.add(metricValue1);
			repetition1.setMetricValues(metricValues1);
			
			like2.setPublication(repetition1);
			likes2.add(like2);
			repetition1.setLikes(likes2);
			
			List<Repetition> repetitions1 = new ArrayList<>();
			repetitions1.add(repetition1);
			progression1.setRepetitions(repetitions1);
			
			progressionList.set(0, progression1);
			habitStack1.setProgressions(progressionList);
			
			Weekday weekday1 = new Weekday(habitStack1, WeekdayEnum.WEDNESDAY, LocalTime.now());
			Weekday weekday2 = new Weekday(habitStack1, WeekdayEnum.MONDAY, LocalTime.now());
			Weekday weekday3 = new Weekday(habitStack1, WeekdayEnum.FRIDAY, LocalTime.now());
			
			List<Weekday> weekdays = new ArrayList<>();
			weekdays.add(weekday1);
			weekdays.add(weekday2);
			weekdays.add(weekday3);
			habitStack1.setWeekdays(weekdays);
			
			habitStackRepo.save(habitStack1);
			
			RepetitionStatus repetitionStatus2 = new RepetitionStatus(RepetitionStatusEnum.TO_DO);
			RepetitionStatus repetitionStatus3 = new RepetitionStatus(RepetitionStatusEnum.TO_DO);
			
			Repetition repetition2 = new Repetition(repetitionStatus2, progression2, null);
			Repetition repetition3 = new Repetition(repetitionStatus3, progression3, null);
			PublicationPictures publicationPictures3 = new PublicationPictures(null, repetition2);
			repetition2.setPublicationPictures(publicationPictures3);
			PublicationPictures publicationPictures4 = new PublicationPictures(null, repetition3);
			repetition3.setPublicationPictures(publicationPictures4);
			
			List<Repetition> repetitions2 = new ArrayList<>();
			repetitions2.add(repetition2);
			List<Repetition> repetitions3 = new ArrayList<>();
			repetitions2.add(repetition3);
			
			progression2.setRepetitions(repetitions2);
			progression3.setRepetitions(repetitions3);
			
			List<Progression> progressionList4 = new ArrayList<>();
			progressionList4.add(progression2);
			progressionList4.add(progression3);
			
			HabitStack habitStack2 = new HabitStack("Morning routine", system1, progressionList4);
			progression2.setHabitStack(habitStack2);
			progression3.setHabitStack(habitStack2);
			
			Weekday weekday4 = new Weekday(habitStack2, WeekdayEnum.MONDAY, LocalTime.now());
			Weekday weekday5 = new Weekday(habitStack2, WeekdayEnum.TUESDAY, LocalTime.now());
			Weekday weekday6 = new Weekday(habitStack2, WeekdayEnum.WEDNESDAY, LocalTime.now());
			Weekday weekday7 = new Weekday(habitStack2, WeekdayEnum.THURSDAY, LocalTime.now());
			Weekday weekday8 = new Weekday(habitStack2, WeekdayEnum.FRIDAY, LocalTime.now());
			Weekday weekday9 = new Weekday(habitStack2, WeekdayEnum.SATURDAY, LocalTime.now());
			Weekday weekday10 = new Weekday(habitStack2, WeekdayEnum.SUNDAY, LocalTime.now());
			
			List<Weekday> weekdays2 = new ArrayList<>();
			weekdays.add(weekday4);
			weekdays.add(weekday5);
			weekdays.add(weekday6);
			weekdays.add(weekday7);
			weekdays.add(weekday8);
			weekdays.add(weekday9);
			weekdays.add(weekday10);
			habitStack2.setWeekdays(weekdays2);
			
			habitStackRepo.save(habitStack2);
			
			//  Create HabitScorecards
			Location lit = new Location("Lit de la maison", null, null, null, null, null, null);
			Step currentHabit1 = new Step("Kafet", true, lit, "Mauvais", user1);
			Step currentHabit2 = new Step("Manger des chips", true, lit, "Mauvais", user1);
			stepRepo.save(currentHabit1);
			stepRepo.save(currentHabit2);
		}
	}
}
