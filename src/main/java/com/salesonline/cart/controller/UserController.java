package com.salesonline.cart.controller;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.salesonline.cart.dto.User;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/auth")
@Slf4j
@PropertySource(value = "classpath:/application.properties", ignoreResourceNotFound = true)
public class UserController {

    public static void writeLog(String text) {

        log.error(text);

    }

    /**
     * Log an user into API
     * @param username name of the user
     * @param password password of the user
     * @return User data once login it's successful
     * @throws IllegalArgumentException
     */
    @PostMapping(value = "/user")
    public User login(@RequestParam("user") String username, @RequestParam("password") String password) throws IllegalArgumentException {
		log.info("REST request to login an user: {}", username);

		if (checkUser(username, password)) {
			log.info("User: {} authenticated", username);
            String token = getJWTToken(username);
            User user = new User();
            user.setUser(username);
            user.setToken(token);
            log.info("REST request to login user: {}", username);
            return user;

        } else{
            throw new IllegalArgumentException("Invalid credentials. Please check you data.");

        }

    }

    /**
     * Get the JWTToken for an user
     * @param username name of the user
     * @return usre's JWTToken
     */
    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("JWTFactory")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }

    /**
     * Check if user exist in users file
     * @param username name of the user
     * @param password passord of the user
     * @return true if user exits false otherwise
     */
    private boolean checkUser(String username, String password) {
        Path root = FileSystems.getDefault().getPath("").toAbsolutePath();
        Path filePath = Paths.get(root.toString(), "src", "main", "resources", "users_file.txt");
        try (Stream<String> stream = Files.lines(Paths.get(filePath.toUri()))) {

            Optional<String[]> response = stream.map(str -> str.split(","))
                    .filter(str -> str[0].trim().equalsIgnoreCase(username) && str[1].trim().equals(password))
                    .findFirst();

            return response.isPresent();

        } catch (IOException e) {
            return false;

        }
    }


}
