package com.mardeveloper.wine.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
    	String userName;
    	
    	try {
    		User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    		userName = principal.getUsername();
		} catch (Exception e) {
			userName = "auto";
		}    	    	
    	
        return Optional.of(userName);
    }

}
