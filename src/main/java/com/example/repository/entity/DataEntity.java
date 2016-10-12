package com.example.repository.entity;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

//import java.util.Base64;
//import org.apache.commons.codec.binary.Base64;

// JPA enable POJO
@Entity
public class DataEntity {

	@Id @GeneratedValue
	private Long id;
	
	private String firstName = null;
	private String lastName = null;
	private String email = null;
	private String comment = null;
	
	private final static int STD_LEN = 100;
	private final static int COMMENT_LEN = 200;
	
	// Developed/tested regex expressions using http://regexr.com/
	private final static String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
	private final static String MALICIOUS_CHARS_REGEX = "[^A-Za-z0-9 +@_.-]";
	
	    
	public DataEntity () {}

	public DataEntity(String fullName, String em) {
		System.out.println("Entering DataEntity(String fullName, String em) constructor");
		parseFullName(fullName);
		setEmail(em);
	}
	
	public DataEntity(String fullName, String em, String cm) {
		System.out.println("Entering DataEntity(String fullName, String em, String cm) constructor");
		parseFullName(fullName);
		setEmail(em);
		setComment(cm);
	}

	public DataEntity(String first, String last, String em, String cm) {
		System.out.println("Entering DataEntity(String first, String last, String em, String cm) constructor");
		setFirstName(first);
		setLastName(last);
		setEmail(em);
		setComment(cm);
	}
	
	private void parseFullName(String fullName) {
		List<String> list = Arrays.asList(fullName.split(" "));		
		list.forEach(part -> {
			if (this.firstName == null)
				this.setFirstName(part);
			else if (this.lastName == null)
				this.setLastName(part);
			else
				this.setLastName(this.getLastName() + " " + part);
		});
	}

	public Long getId() {
		return this.id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	void setFirstName(String fn) {		
		if (fn != null)
			firstName = processMaliciousCharactersAndLength(fn, STD_LEN);
		else
			throw createException("setFirstName() : Invalid argument", "FirstName parameter may not be null");
	}
	
	public String getLastName() {
		return lastName;
	}
	void setLastName(String ln) {
		if (ln != null)
			lastName = processMaliciousCharactersAndLength(ln, STD_LEN);
		else
			throw createException("setLastName() : Invalid argument", "LastName parameter may not be null");
	}
	
	public String getEmail() {
		return email;
	}
	void setEmail(String em) {
		if (em != null)
			em = processMaliciousCharactersAndLength(em, STD_LEN);
		else
			throw createException("setEmail() : Invalid argument", "Email parameter may not be null");
		
		if (validateEmail(em))
			email = em;
		else
			throw createException("setEmail() : Invalid argument", "Email does not conform to valid email format");
	}
	
	public String getComment() {
		return comment;
	}
	void setComment(String c) {
		if (c != null)
			comment = processMaliciousCharactersAndLength(c, COMMENT_LEN);
		else
			throw createException("setComment() : Invalid argument", "Comment parameter may not be null");
	}

	private IllegalArgumentException createException(String msg, String reason) {
		return new IllegalArgumentException(msg, new Throwable(reason));
	}
	
	private String processMaliciousCharactersAndLength (String str, int len) {
		str = str.replaceAll(MALICIOUS_CHARS_REGEX, "");
		try {
			str = str.substring(0, len - 1);
		}
		catch (IndexOutOfBoundsException e) {
			// swallow exception
		}
		return str;
	}
	
	private boolean validateEmail (String em) {
		Pattern pattern = Pattern.compile(EMAIL_REGEX);
		Matcher matcher = pattern.matcher(em);
		return matcher.matches();
	}
	
	@Override
	public String toString () {
		return String.format("dataEntity:{\n id:%d,\n firstName:%s,\n lastName:%s,\n email:%s,\n comment:%s\n}",
				id,
				getFirstName(),
				getLastName(),
				getEmail(),
				getComment()
		);
	}
	
}

@Component
class DataResourceProcessor implements ResourceProcessor<Resource<DataEntity>> {

	@Override
	public Resource<DataEntity> process(Resource<DataEntity> resource) {
		//String img = "data:image/png;base64," + java.util.Base64.getEncoder().encodeToString(resource.getContent().getImage().getBytes());
		//String img = "data:image/png;base64," + new String(org.apache.commons.codec.binary.Base64.encodeBase64(resource.getContent().getImage().getBytes()));
		String img = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAUAAAAFCAYAAACNbyblAAAAHElEQVQI12P4//8/w38GIAXDIBKE0DHxgljNBAAO9TXL0Y4OHwAAAABJRU5ErkJggg==";
		resource.add(new Link(img, "data-img"));
		return resource;
	}
	
}
