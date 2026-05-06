package com.hospitalmanagement.repositories;

import com.hospitalmanagement.entities.User;
import com.hospitalmanagement.enums.OAuthProviderType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {
    Optional<UserDetails> findByUserName(String username);
    Optional<User> findByOAuthProvideIdAndOAuthProviderTypeAndEmailId(String providerId, OAuthProviderType providerType,String emailId);

    Optional<User> findByEmailId(String emailId);
}
