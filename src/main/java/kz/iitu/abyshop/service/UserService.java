package kz.iitu.abyshop.service;

import kz.iitu.abyshop.dto.user.SignInDto;
import kz.iitu.abyshop.dto.user.SignInResponseDto;
import kz.iitu.abyshop.dto.user.SignupResponseDto;
import kz.iitu.abyshop.dto.user.SignupDto;
import kz.iitu.abyshop.exception.AuthenticationFailedException;
import kz.iitu.abyshop.exception.CustomException;
import kz.iitu.abyshop.model.AuthenticationToken;
import kz.iitu.abyshop.model.User;
import kz.iitu.abyshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;
    @Transactional
    public SignupResponseDto signup(SignupDto signupDto) {

        if (Objects.nonNull(userRepository.findByEmail(signupDto.getEmail())))
            throw new CustomException("User already present");
        String encryptedPassword = signupDto.getPassword();
        try {
            encryptedPassword = hashPassword(encryptedPassword);
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        User user = new User();
        user.setEmail(signupDto.getEmail());
        user.setPassword(encryptedPassword);
        userRepository.save(user);

        final AuthenticationToken authenticationToken = new AuthenticationToken(user);
        authenticationService.saveConfirmationToken(authenticationToken);
        SignupResponseDto signupResponseDto = new SignupResponseDto("success", "user created successfully");
        return signupResponseDto;
    }

    private String hashPassword(String encryptedPassword) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(encryptedPassword.getBytes());
        byte[] digest = messageDigest.digest();
        String hash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return hash;
    }

    public SignInResponseDto signIn(SignInDto signInDto) {
        User user = userRepository.findByEmail(signInDto.getEmail());
        if(Objects.isNull(user))
            throw new AuthenticationFailedException("User is not valid");
        try {
            if(!user.getPassword().equals(hashPassword(signInDto.getPassword())))
                throw new AuthenticationFailedException("Password is not correct");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        AuthenticationToken authenticationToken=authenticationService.getToken(user);

        if(Objects.isNull(authenticationToken))
            throw new CustomException("Token is present");
        return new SignInResponseDto("success", authenticationToken.getToken());
    }
}
