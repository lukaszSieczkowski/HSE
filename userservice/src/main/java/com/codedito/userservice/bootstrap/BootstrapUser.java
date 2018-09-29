package com.codedito.userservice.bootstrap;


import com.codedito.userservice.domain.ApplicationUser;
import com.codedito.userservice.repository.AppUserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BootstrapUser implements ApplicationListener<ContextRefreshedEvent> {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public BootstrapUser(final AppUserRepository appUserRepository, final PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent contextRefreshedEvent) {
        init();
    }

    private void init() {
        final ApplicationUser user = ApplicationUser.builder()
                .username(UsersNames.USER.getName())
                .password(passwordEncoder.encode(UsersNames.USER.getPassword()))
                .email(UsersNames.USER.getEmail())
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(false)
                .enabled(true).build();
        //  .authorities(Sets.newHashSet(new Authority(Role.USER))).build();

        final ApplicationUser admin = ApplicationUser.builder()
                .username(UsersNames.ADMINISTRATOR.getName())
                .password(passwordEncoder.encode(UsersNames.ADMINISTRATOR.getPassword()))
                .email(UsersNames.ADMINISTRATOR.getEmail())
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true).build();
        //     .authorities(Sets.newHashSet(new Authority(Role.ADMIN))).build();

        final ApplicationUser userWitchExpriredCredentials = ApplicationUser.builder()
                .username(UsersNames.EXPIRED_CREDENTIALS_USER.getName())
                .password(passwordEncoder.encode(UsersNames.EXPIRED_CREDENTIALS_USER.getPassword()))
                .email(UsersNames.EXPIRED_CREDENTIALS_USER.getEmail())
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(false)
                .enabled(true).build();
        //     .authorities(Sets.newHashSet(new Authority(Role.ADMIN))).build();

        final ApplicationUser userWitchExpriredAccount = ApplicationUser.builder()
                .username(UsersNames.EXPIRED_ACCOUNT_USER.getName())
                .password(passwordEncoder.encode(UsersNames.EXPIRED_ACCOUNT_USER.getPassword()))
                .email(UsersNames.EXPIRED_ACCOUNT_USER.getEmail())
                .accountNonExpired(false)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true).build();
        //    .authorities(Sets.newHashSet(new Authority(Role.ADMIN))).build();


        final ApplicationUser userWitchLockedAccount = ApplicationUser.builder()
                .username(UsersNames.LOCKED_ACCOUNT_USER.getName())
                .password(passwordEncoder.encode(UsersNames.LOCKED_ACCOUNT_USER.getPassword()))
                .email(UsersNames.LOCKED_ACCOUNT_USER.getEmail())
                .accountNonExpired(true)
                .accountNonLocked(false)
                .credentialsNonExpired(true)
                .enabled(true).build();
        //   .authorities(Sets.newHashSet(new Authority(Role.ADMIN))).build();

        final ApplicationUser userEnabled = ApplicationUser.builder()
                .username(UsersNames.ENABLED_USER.getName())
                .password(passwordEncoder.encode(UsersNames.ENABLED_USER.getPassword()))
                .email(UsersNames.ENABLED_USER.getEmail())
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(false).build();
        //  .authorities(Sets.newHashSet(new Authority(Role.ADMIN))).build();

        appUserRepository.save(user);
        appUserRepository.save(admin);
        appUserRepository.save(userWitchExpriredCredentials);
        appUserRepository.save(userWitchExpriredAccount);
        appUserRepository.save(userWitchLockedAccount);
        appUserRepository.save(userEnabled);
    }
}
