package si.iitech.lib.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import si.iitech.lib.entity.EtUser;
import si.iitech.lib.exception.UserExceptionMessages;
import si.iitech.lib.exception.impl.UserException;
import si.iitech.lib.repository.UserRepository;
import si.iitech.lib.util.JWTUtils;

public abstract class UserController<T extends EtUser> {

	@Autowired
	private UserRepository<T> userRepository;
	
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public UserController(BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@PostMapping("/register")
	public ResponseEntity<String> register(@Valid @RequestBody T user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		return ResponseEntity.ok("Register is complete");
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}
	
	protected T getUser(String token) throws UserException {
		T user = userRepository.findByEmail(JWTUtils.getUser(token));
		if(user == null) throw new UserException(UserExceptionMessages.USER_DOES_NOT_EXIST);
		return user;
	}
}