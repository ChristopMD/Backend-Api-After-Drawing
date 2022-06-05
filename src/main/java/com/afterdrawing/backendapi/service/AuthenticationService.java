package com.afterdrawing.backendapi.service;

import com.afterdrawing.backendapi.core.entity.PasswordResetToken;
import com.afterdrawing.backendapi.core.entity.User;
import com.afterdrawing.backendapi.core.repository.PasswordResetTokenRepository;
import com.afterdrawing.backendapi.core.repository.UserRepository;
import com.afterdrawing.backendapi.exception.ResourceNotFoundException;
import com.afterdrawing.backendapi.resource.authentication.*;
import com.afterdrawing.backendapi.sercurity.JwtCenter;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;





    private final PasswordResetTokenRepository passwordResetTokenRepository;



    private final UserDetailsServiceImpl userDetailsService;

    private final AuthenticationManager authenticationManager;

    //@Autowired
    private JwtCenter jwtCenter;



    @Transactional
    public void signUp(SignUpResource signUpResource) {
        // Creating a new user based of registration dto
        User user = new User();
        user.setEmail(signUpResource.getEmail());
        user.setFirstName(signUpResource.getFirstName());
        user.setLastName(signUpResource.getLastName());
        user.setUserName(signUpResource.getUserName());
        user.setPassword(passwordEncoder.encode(signUpResource.getPassword()));

        user.setUsing2FA(false);

        user.setEnabled(false);

        // Saving the new user to the database
        userRepository.save(user);
        // User e-mail verification

    }

    public AuthenticationResource signIn(SignInResource loginRequest)  {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));


        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());


        User user = userRepository.findByEmail(loginRequest.getEmail())
                    .orElseThrow(() -> new ResourceNotFoundException("Invalid user"));

        /*if(user.getUsing2FA()){
            return new AuthenticationResource("","",null,"", true);
        }else {*/
            //
            //SecurityContextHolder.getContext().setAuthentication(authenticate);
            String token = jwtCenter.generateToken(userDetails);
            //String token = jwtProvider.generateToken(authenticate);

            return new AuthenticationResource(
                    token,
                    //Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()),
                    //jwtCenter.getExpirationDate().toInstant(),
                    user.getId(),
                    user.getEmail(),
                    user.getUserName()

            );
            //}

    }

    /*public AuthenticationResource refreshToken(RefreshTokenResource refreshTokenRequest){
        // Refresh the token
        refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
        String token = jwtProvider.generateTokenWithEmail(refreshTokenRequest.getEmail());

        return new AuthenticationResource(
                token,
                refreshTokenRequest.getRefreshToken(),
                Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()),
                refreshTokenRequest.getEmail(),
                false);
    }*/

    /*public void signOut(RefreshTokenResource refreshTokenRequest) {
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
    }*/

    /*public void verifyAccount(String token) {
        // Checking token validation
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        verificationToken.orElseThrow(() -> new ResourceNotFoundException("Invalid token"));

        // Activating user's account
        User user = verificationToken.get().getUser();
        if (user.getEnabled()){
            throw new ResourceNotFoundException("Account has already been activated");
        } else {
            user.setEnabled(true);
            userRepository.save(user);
        }
    }*/

    public void forgotPassword(ForgotPasswordResource forgotPasswordRequest) {
        // Getting user from email parameter
        Optional<User> user = userRepository.findByEmail(forgotPasswordRequest.getEmail());
        if(!user.isEmpty()){
            // Generate password reset token
            String token = UUID.randomUUID().toString();
            PasswordResetToken passwordResetToken = new PasswordResetToken();
            passwordResetToken.setToken(token);
            passwordResetToken.setUser(user.get());
            passwordResetTokenRepository.save(passwordResetToken);

            // TODO: Send an email to the user with the token
        }
    }

    /*public void resetPassword(ResetPasswordResource resetPasswordRequest) {
        // Validating the token
        String token = resetPasswordRequest.getToken();
        Optional<PasswordResetToken> passwordResetToken = passwordResetTokenRepository.findByToken(token);
        if(!passwordResetToken.isEmpty()){
            // Changing user password
            passwordResetToken.get().getUser().setPassword(passwordEncoder.encode(resetPasswordRequest.getNewPassword()));
            userRepository.save(passwordResetToken.get().getUser());
        }else{
            throw new ResourceNotFoundException("Invalid token");
        }
    }*/

    public ResponseEntity<?> changePassword(Long userId, ChangePasswordResource changePasswordRequest) {
        // Validating user and passwords
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            if(passwordEncoder.matches(changePasswordRequest.getOldPassword(), user.get().getPassword())){
                // Changing user password
                user.get().setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
                userRepository.save(user.get());
                return ResponseEntity.ok().body("Password changed");
            }else{
                throw new ResourceNotFoundException("Invalid password");
            }
        }else{
            throw new ResourceNotFoundException("Invalid user");
        }


    }




}
