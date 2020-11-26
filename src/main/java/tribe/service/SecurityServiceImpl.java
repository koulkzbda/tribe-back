package tribe.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import tribe.service.interfaces.SecurityService;

@Service
public class SecurityServiceImpl implements SecurityService {

	@Override
	public String getUserEmail() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}

}