package com.netcat.meow.User.Auth;

import com.netcat.meow.Apps.Notes.NotesController;
import com.netcat.meow.Email.EmailTemplate;
import com.netcat.meow.Email.SendMail;
import com.netcat.meow.Service.TokenService;
import com.netcat.meow.Utility.Literal;
import com.netcat.meow.Utility.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class UserController {
	Logger logger = LoggerFactory.getLogger(UserController.class);
	/**
	 * Hold the user service
	 */
	private UserService userservice;


	/**
	 * This module is used for user login, signup, forgot password.
	 */
	public UserController() {
		userservice = UserService.getInstance();
	}
	@Autowired
	private HttpServletRequest request;


	/**
	 * send test mail
	 * @return
	 */
	@RequestMapping(value = "user/testEmail", method = RequestMethod.GET, headers = Literal.APPLICATION_JSON)
	public boolean testEmail() {
		SendMail.getInstance().send("zengsarmah@gmail.com", EmailTemplate.VERIFICATION("Abhinov Gogoi", "http://localhost:8080/user/verifyemail?login_id=abhinov.in@gmail.com&token=PsIlQIQIxLOfjNG"));
		return Literal.TRUE;
	}

	/**
	 * @param req_map

						{
						"login_id": "admin@meow.com",
						"password": "root",
						"device_type": "A",
						"device_os": "android 6.0",
						"device_token": "YxueNFEN1lqhXNO6JelqZ"
						}

	 * 				  <br>
	 *                <table border=1px>
	 *                <tr>
	 *                <th>Key</th>
	 *                <th>Sample Data</th>
	 *                <th>Data Type</th>
	 *                <th>Constraint</th>
	 *                <th>Description</th>
	 *                </tr>
	 *                <tr>
	 *                <td>login_id</td>
	 *                <td>admin@meow.com</td>
	 *                <td>String</td>
	 *                <td>Mandatory</td>
	 *                <td>should be valid email id which already exists for a login</td>
	 *                </tr>
	 *                <tr>
	 *                <td>password</td>
	 *                <td>root</td>
	 *                <td>String</td>
	 *                <td>Mandatory</td>
	 *                <td>Password validation: length should be between 8-14
	 *                characters,should contain atleast one digit from 0-9,one lower
	 *                case alphabet between a-z and one upper case alphabet between
	 *                A-Z,should contain atleast one special character from @#$%
	 *                </td>
	 *                </tr>
	 *                <tr>
	 *                <td>device_type</td>
	 *                <td>W</td>
	 *                <td>String</td>
	 *                <td>Mandatory</td>
	 *                <td>Device type should be either A (android) or I (ios) or W (web)</td>
	 *                </tr>
	 *                <tr>
	 *                <td>device_os</td>
	 *                <td>android</td>
	 *                <td>String</td>
	 *                <td>Mandatory</td>
	 *                <td>device os</td>
	 *                </tr>
	 *                <tr>
	 *                <td>device_token</td>
	 *                <td>ASE48965953</td>
	 *                <td>String</td>
	 *                <td>Mandatory</td>
	 *                <td>device_token</td>
	 *                </tr>
	 *                </table>
	 * @param
	 *                <table border=1px>
	 *                <tr>
	 *                <th>Header Key</th>
	 *                <th>Sample Data</th>
	 *                <th>Data Type</th>
	 *                <th>Constraint</th>
	 *                <th>Description</th>
	 *                </tr>
	 *                <tr>
	 *                <td>User-Agent</td>
	 *                <td>Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36</td>
	 *                <td>String</td>
	 *                <td>Mandatory</td>
	 *                <td>user agent</td>
	 *                </tr>
	 *                <tr>
	 *                <td>Content-Type</td>
	 *                <td>application/json</td>
	 *                <td>String</td>
	 *                <td>Mandatory</td>
	 *                <td>Request content type.</td>
	 *                </tr>
	 *                </table>
	 *                <br>
	 * @see <b>Functionality: </b> This API is used for login.
	 * @return <b>SUCCESS MESSAGE:</b>
	 * 		   {"MESSAGE":"Login successfully.","LOGIN_TIME":"2021-12-10 14:58:32.189","STATUS":"Success",
	 * 		   "TOKEN":"H4LMzUz9vCp3UbQbvXNYLHQxf7SduugcNlC+/Yczd+qhgzQSNAK1unL0MwChkBheNjEtXBxEQvL3btUmgPgYhVYyGNzAeXlC8/0Qi9ATzAW3VaBMaOALseCQnsr3KBLHFGLBF13eTRwkN8Z0xcQyh1C8sYOJzmqTGrZDn7cd77QJBoXtvl8p0GEZQX1yHZoOmTXTpdA/ZYa5TMp7twSkbxHV8Qa8GcGQoTOfFbpAeVkyw+04zRoHalwkDSr6o2ZioGQSuVlzrJXWdXPSOIJnVo0erHNGLBXBa90Faa3BJ96o8ppyIRb8leSyBXwv+WnQ0jclMWgyksMx1Eskj1YDr8z/++Iss6r10///GJhTtgd4axjrnvS28iaaYNoBEeUqcDpxhKJxu0ZNuJjzVVKdBVOEHpO1KobWZZnP/dOlWGO/ysmckHX4kJmWv17n8Z08rxGoUlExZWoYkDtG1Rwasg=="}<br>
	 *
	 *         <b>ERROR MESSAGE:</b> <br>
	 *
	 *
	 */
	@RequestMapping(value = "user/login", method = RequestMethod.POST, headers = Literal.APPLICATION_JSON)
	public Map<String, Object> login(@RequestBody Map<String, Object> req_map) {
		Map<String, Object> ret_map = new HashMap<String, Object>(Literal.SIX);
		try {
			/**
			 * Check for the null login_id
			 */
			if (req_map.get(Literal.login_id) == null || req_map.get(Literal.login_id).toString().equals(Literal.EMPTY_STRING)) {
				ret_map.put(Literal.STATUS, Literal.ERROR);
				ret_map.put(Literal.MESSAGE, Literal.EMAIL_NULL);
				ret_map.put(Literal.REQUEST_DATA, req_map);
				return ret_map;
			} 
			/**
			 * Check for null password
			 */
			if (req_map.get(Literal.password) == null || req_map.get(Literal.password).toString().equals(Literal.EMPTY_STRING)) {
				ret_map.put(Literal.STATUS, Literal.ERROR);
				ret_map.put(Literal.MESSAGE, Literal.NULL_PASSWORD);
				ret_map.put(Literal.REQUEST_DATA, req_map);
				return ret_map;
			}
			/**
			 * Check for null device_type
			 */
			if (req_map.get(Literal.device_type) == null || req_map.get(Literal.device_type).toString().equals(Literal.EMPTY_STRING)) {
				ret_map.put(Literal.STATUS, Literal.ERROR);
				ret_map.put(Literal.MESSAGE, Literal.DEVICE_TYPE_NULL);
				ret_map.put(Literal.REQUEST_DATA, req_map);
				return ret_map;
			}
			/**
			 * Check for device type value - A-android, I-ios, W-web
			 */
			if (!Utility.chkDeviceType(req_map.get(Literal.device_type).toString().trim())) {
				ret_map.put(Literal.STATUS, Literal.ERROR);
				ret_map.put(Literal.MESSAGE, Literal.WRONG_DEVICE_TYPE);
				ret_map.put(Literal.REQUEST_DATA, req_map);
				return ret_map;
			}
			/**
			 * Check for device token in case of web login
			 */
			if(!(req_map.get(Literal.device_type).toString().trim().equalsIgnoreCase(Literal.W))) {
				/**
				 * Check for null device_token
				 */
				if (req_map.get(Literal.device_token) == null || req_map.get(Literal.device_token).toString().equals(Literal.EMPTY_STRING)) {
					ret_map.put(Literal.STATUS, Literal.ERROR);
					ret_map.put(Literal.MESSAGE, Literal.DEVICE_TOKENNULL);
					ret_map.put(Literal.REQUEST_DATA, req_map);
					return ret_map;
				}
			}
			/**
			 * Check for null device_os
			 */
			if (req_map.get(Literal.device_os) == null || req_map.get(Literal.device_os).toString().equals(Literal.EMPTY_STRING)) {
				ret_map.put(Literal.STATUS, Literal.ERROR);
				ret_map.put(Literal.MESSAGE, Literal.DEVICE_OSNULL);
				ret_map.put(Literal.REQUEST_DATA, req_map);
				return ret_map;
			}
			/**
			 * Make user login
			 */
			return userservice.login(request.getHeader(Literal.USER_AGENT), request.getRemoteAddr(), req_map);
		} catch (Exception e) {
			ret_map = new HashMap<String, Object>(Literal.EIGHT);
			ret_map.put(Literal.STATUS, Literal.ERROR);
			ret_map.put(Literal.MESSAGE, Literal.SOMETHING_WENT_WORNG);
			ret_map.put(Literal.EXCEPTION_MESAGE, e.getStackTrace());
			ret_map.put(Literal.REQUEST_DATA, req_map);
			Utility.printStackTrace(e, this.getClass().getName());
			return ret_map;
		}
	}

	/**
	 * @param req_map

						{
						"name": "John Doe",
						"login_id": "john.doe@gmail.com",
						"password": "demo",
						"device_type": "A",
						"device_os": "Android 12.0",
						"device_token": "YxueNFEN1lqhXNO6JelqZ"
						}

	 * 				  <br>
	 *                <table border=1px>
	 *                <tr>
	 *                <th>Key</th>
	 *                <th>Sample Data</th>
	 *                <th>Data Type</th>
	 *                <th>Constraint</th>
	 *                <th>Description</th>
	 *                </tr>
	 *                <tr>
	 *                <td>name</td>
	 *                <td>John Doe</td>
	 *                <td>String</td>
	 *                <td>Mandatory</td>
	 *                <td>Name of the User</td>
	 *                </tr>
	 *                <tr>
	 *                <td>login_id</td>
	 *                <td>john.doe@gmail.com</td>
	 *                <td>String</td>
	 *                <td>Mandatory</td>
	 *                <td>should be valid email id which does not already exist</td>
	 *                </tr>
	 *                <tr>
	 *                <td>password</td>
	 *                <td>JohnDoe@123</td>
	 *                <td>String</td>
	 *                <td>Mandatory</td>
	 *                <td>Password validation
	 * 					a digit must occur at least once
	 * 					a lower case letter must occur at least once
	 * 					an upper case letter must occur at least once
	 * 					a special character must occur at least once
	 *					no whitespace allowed in the entire string
	 * 					at least eight places though
	 *                </td>
	 *                </tr>
	 *                <tr>
	 *                <td>device_type</td>
	 *                <td>W</td>
	 *                <td>String</td>
	 *                <td>Mandatory</td>
	 *                <td>Device type should be either A (android) or I (ios) or W (web)</td>
	 *                </tr>
	 *                <tr>
	 *                <td>device_os</td>
	 *                <td>Android 12.0</td>
	 *                <td>String</td>
	 *                <td>Mandatory</td>
	 *                <td>device os / version</td>
	 *                </tr>
	 *                </table>
	 * @param
	 *                <table border=1px>
	 *                <tr>
	 *                <th>Header Key</th>
	 *                <th>Sample Data</th>
	 *                <th>Data Type</th>
	 *                <th>Constraint</th>
	 *                <th>Description</th>
	 *                </tr>
	 *                <tr>
	 *                <td>User-Agent</td>
	 *                <td>Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36</td>
	 *                <td>String</td>
	 *                <td>Mandatory</td>
	 *                <td>user agent</td>
	 *                </tr>
	 *                <tr>
	 *                <td>Content-Type</td>
	 *                <td>application/json</td>
	 *                <td>String</td>
	 *                <td>Mandatory</td>
	 *                <td>Request content type.</td>
	 *                </tr>
	 *                </table>
	 *                <br>
	 * @see <b>Functionality: </b> This API is used for signup.
	 * @return <b>SUCCESS MESSAGE:</b>
	 *
	 *         <b>ERROR MESSAGE:</b> <br>
	 *
	 *
	 */
	@RequestMapping(value = "user/signup", method = RequestMethod.POST, headers = Literal.APPLICATION_JSON)
	public Map<String, Object> signup(@RequestBody Map<String, Object> req_map) {
		Map<String, Object> ret_map = new HashMap<String, Object>(Literal.SIX);
		try {
			/**
			 * Check for the null name
			 */
			if (Utility.chkNullObj(req_map.get(Literal.name))) {
				ret_map.put(Literal.STATUS, Literal.ERROR);
				ret_map.put(Literal.MESSAGE, Literal.NAME_NULL);
				ret_map.put(Literal.REQUEST_DATA, req_map);
				return ret_map;
			}
			/**
			 * Check for the name regex
			 */
			if (Utility.chkName(req_map.get(Literal.name).toString())) {
				ret_map.put(Literal.STATUS, Literal.ERROR);
				ret_map.put(Literal.MESSAGE, Literal.NAME_INVALID);
				ret_map.put(Literal.REQUEST_DATA, req_map);
				return ret_map;
			}
			/**
			 * Check for the null login_id
			 */
			if (Utility.chkNullObj(req_map.get(Literal.login_id))) {
				ret_map.put(Literal.STATUS, Literal.ERROR);
				ret_map.put(Literal.MESSAGE, Literal.EMAIL_NULL);
				ret_map.put(Literal.REQUEST_DATA, req_map);
				return ret_map;
			}
			/**
			 * Check for the login_id / email regex
			 */
			if (Utility.chkEmailRegex(req_map.get(Literal.login_id).toString())) {
				ret_map.put(Literal.STATUS, Literal.ERROR);
				ret_map.put(Literal.MESSAGE, Literal.EMAIL_INVALID);
				ret_map.put(Literal.REQUEST_DATA, req_map);
				return ret_map;
			}
			/**
			 * Check if this login_id is available for signup
			 */
			if (!UserMongoDao.getInstance().chkIdAvailable(req_map.get(Literal.login_id).toString())) {
				/**
				 * User does not exist
				 */
				ret_map.put(Literal.STATUS, Literal.ERROR);
				ret_map.put(Literal.MESSAGE, Literal.ID_ALREADY_TAKEN);
				ret_map.put(Literal.REQUEST_DATA, req_map);
				return ret_map;
			}
			/**
			 * Check for null password
			 */
			if (Utility.chkNullObj(req_map.get(Literal.password))) {
				ret_map.put(Literal.STATUS, Literal.ERROR);
				ret_map.put(Literal.MESSAGE, Literal.NULL_PASSWORD);
				ret_map.put(Literal.REQUEST_DATA, req_map);
				return ret_map;
			}
			/**
			 * Password validation  https://stackoverflow.com/questions/3802192/regexp-java-for-password-validation
			 * regex : ^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\S+$).{8,}$
			 *
			 * a digit must occur at least once
			 * a lower case letter must occur at least once
			 * an upper case letter must occur at least once
			 * a special character must occur at least once
			 * no whitespace allowed in the entire string
			 * at least eight places though
			 *
			 */
			if (!Utility.chkValidPassword(req_map.get(Literal.password).toString())) {
				ret_map.put(Literal.STATUS, Literal.ERROR);
				ret_map.put(Literal.MESSAGE, Literal.INVALID_PASSWORD);
				ret_map.put(Literal.REQUEST_DATA, req_map);
				return ret_map;
			}
			/**
			 * Check for null device_type
			 */
			if (req_map.get(Literal.device_type) == null || req_map.get(Literal.device_type).toString().equals(Literal.EMPTY_STRING)) {
				ret_map.put(Literal.STATUS, Literal.ERROR);
				ret_map.put(Literal.MESSAGE, Literal.DEVICE_TYPE_NULL);
				ret_map.put(Literal.REQUEST_DATA, req_map);
				return ret_map;
			}
			/**
			 * Check for device type value - A-android, I-ios, W-web
			 */
			if (!Utility.chkDeviceType(req_map.get(Literal.device_type).toString().trim())) {
				ret_map.put(Literal.STATUS, Literal.ERROR);
				ret_map.put(Literal.MESSAGE, Literal.WRONG_DEVICE_TYPE);
				ret_map.put(Literal.REQUEST_DATA, req_map);
				return ret_map;
			}
			/**
			 * Check for device token in case of web login
			 */
			if(!(req_map.get(Literal.device_type).toString().trim().equalsIgnoreCase(Literal.W))) {
				/**
				 * Check for null device_token
				 */
				if (req_map.get(Literal.device_token) == null || req_map.get(Literal.device_token).toString().equals(Literal.EMPTY_STRING)) {
					ret_map.put(Literal.STATUS, Literal.ERROR);
					ret_map.put(Literal.MESSAGE, Literal.DEVICE_TOKENNULL);
					ret_map.put(Literal.REQUEST_DATA, req_map);
					return ret_map;
				}
			}
			/**
			 * Check for null device_os
			 */
			if (req_map.get(Literal.device_os) == null || req_map.get(Literal.device_os).toString().equals(Literal.EMPTY_STRING)) {
				ret_map.put(Literal.STATUS, Literal.ERROR);
				ret_map.put(Literal.MESSAGE, Literal.DEVICE_OSNULL);
				ret_map.put(Literal.REQUEST_DATA, req_map);
				return ret_map;
			}
			/**
			 * Make user login
			 */
			return userservice.signup(request.getHeader(Literal.USER_AGENT), request.getRemoteAddr(), req_map);
		} catch (Exception e) {
			ret_map = new HashMap<String, Object>(Literal.EIGHT);
			ret_map.put(Literal.STATUS, Literal.ERROR);
			ret_map.put(Literal.MESSAGE, Literal.SOMETHING_WENT_WORNG);
			ret_map.put(Literal.EXCEPTION_MESAGE, e.getStackTrace());
			ret_map.put(Literal.REQUEST_DATA, req_map);
			Utility.printStackTrace(e, this.getClass().getName());
			return ret_map;
		}
	}

	/**
	 * 				  login_id=abhinov.in@gmail.com&token=PsIlQIQIxYiZBTAuSC
	 *
	 * 				  <br>
	 *                <table border=1px>
	 *                <tr>
	 *                <th>Key</th>
	 *                <th>Sample Data</th>
	 *                <th>Data Type</th>
	 *                <th>Constraint</th>
	 *                <th>Description</th>
	 *                </tr>
	 *                <tr>
	 *                <td>login_id</td>
	 *                <td>john.doe@example.com</td>
	 *                <td>String</td>
	 *                <td>Mandatory</td>
	 *                <td>login_id of the user</td>
	 *                </tr>
	 *                <tr>
	 *                <td>token</td>
	 *                <td>PsIlQIQIxYiZBTAuSC</td>
	 *                <td>String</td>
	 *                <td>Mandatory</td>
	 *                <td>token</td>
	 *                </tr>
	 *                </table>
	 * @param
	 *                <table border=1px>
	 *                <tr>
	 *                <th>Header Key</th>
	 *                <th>Sample Data</th>
	 *                <th>Data Type</th>
	 *                <th>Constraint</th>
	 *                <th>Description</th>
	 *                </tr>
	 *                <tr>
	 *                <td>User-Agent</td>
	 *                <td>Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36</td>
	 *                <td>String</td>
	 *                <td>Mandatory</td>
	 *                <td>user agent</td>
	 *                </tr>
	 *                <tr>
	 *                <td>Content-Type</td>
	 *                <td>application/json</td>
	 *                <td>String</td>
	 *                <td>Mandatory</td>
	 *                <td>Request content type.</td>
	 *                </tr>
	 *                </table>
	 *                <br>
	 * @see <b>Functionality: </b> This API is used for signup email verification.
	 * @return <b>SUCCESS MESSAGE:</b>
	 *
	 *         <b>ERROR MESSAGE:</b> <br>
	 *
	 */
	/**
	 * @param login_id
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "user/verifyemail", method = RequestMethod.GET, headers = Literal.APPLICATION_JSON)
	public Map<String, Object> verifySignupEmail(@RequestParam(value = "login_id", required = true) String login_id,
												 @RequestParam(value = "token", required = true) String token) {
		Map<String, Object> ret_map = new HashMap<String, Object>(Literal.SIX);
		try {
			/**
			 * Check for the null login_id
			 */
			if (Utility.chkNullObj(login_id)) {
				ret_map.put(Literal.STATUS, Literal.ERROR);
				ret_map.put(Literal.MESSAGE, Literal.EMAIL_NULL);
				return ret_map;
			}
			/**
			 * Check for the null token
			 */
			if (Utility.chkNullObj(token)) {
				ret_map.put(Literal.STATUS, Literal.ERROR);
				ret_map.put(Literal.MESSAGE, Literal.TOKEN_NULL);
				return ret_map;
			}
			/**
			 * decode from base64
			 */
			token = new String(Base64.getDecoder().decode(token));
			login_id = new String(Base64.getDecoder().decode(login_id));
			/**
			 * Validate token and login id
			 */
			return userservice.verifySignupEmail(login_id, token);
		} catch (Exception e) {
			ret_map = new HashMap<String, Object>(Literal.EIGHT);
			ret_map.put(Literal.STATUS, Literal.ERROR);
			ret_map.put(Literal.MESSAGE, Literal.SOMETHING_WENT_WORNG);
			ret_map.put(Literal.EXCEPTION_MESAGE, e.getStackTrace());
			Utility.printStackTrace(e, this.getClass().getName());
			return ret_map;
		}
	}

	/**
	 * 				  email=john.doe@example.com
	 * 				  <br>
	 *                <table border=1px>
	 *                <tr>
	 *                <th>Key</th>
	 *                <th>Sample Data</th>
	 *                <th>Data Type</th>
	 *                <th>Constraint</th>
	 *                <th>Description</th>
	 *                </tr>
	 *                <tr>
	 *                <td>email</td>
	 *                <td>john.doe@example.com</td>
	 *                <td>String</td>
	 *                <td>Mandatory</td>
	 *                <td>email of the user</td>
	 *                </tr>
	 *                </table>
	 * @param
	 *                <table border=1px>
	 *                <tr>
	 *                <th>Header Key</th>
	 *                <th>Sample Data</th>
	 *                <th>Data Type</th>
	 *                <th>Constraint</th>
	 *                <th>Description</th>
	 *                </tr>
	 *                <tr>
	 *                <td>User-Agent</td>
	 *                <td>Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36</td>
	 *                <td>String</td>
	 *                <td>Mandatory</td>
	 *                <td>user agent</td>
	 *                </tr>
	 *                <tr>
	 *                <td>Content-Type</td>
	 *                <td>application/json</td>
	 *                <td>String</td>
	 *                <td>Mandatory</td>
	 *                <td>Request content type.</td>
	 *                </tr>
	 *                </table>
	 *                <br>
	 * @see <b>Functionality: </b> This API is used to send link to email on forgot password click
	 * @return <b>SUCCESS MESSAGE:</b>
	 *
	 *         <b>ERROR MESSAGE:</b> <br>
	 *
	 */
	@RequestMapping(value = "user/forgot-password", method = RequestMethod.GET, headers = Literal.APPLICATION_JSON)
	public Map<String, Object> forgotPassword(@RequestParam(value = "email", required = true) String email) {
		Map<String, Object> ret_map = new HashMap<String, Object>(Literal.SIX);
		try {
			/**
			 * Check for the null login_id
			 */
			if (Utility.chkNullObj(email)) {
				ret_map.put(Literal.STATUS, Literal.ERROR);
				ret_map.put(Literal.MESSAGE, Literal.EMAIL_NULL);
				return ret_map;
			}
			/**
			 * send email with reset link
			 */
			return userservice.sendPasswordResetEmail(email);
		} catch (Exception e) {
			ret_map = new HashMap<>(Literal.EIGHT);
			ret_map.put(Literal.STATUS, Literal.ERROR);
			ret_map.put(Literal.MESSAGE, Literal.SOMETHING_WENT_WORNG);
			ret_map.put(Literal.EXCEPTION_MESAGE, e.getStackTrace());
			Utility.printStackTrace(e, this.getClass().getName());
			return ret_map;
		}
	}

	/**
	 * 				  email=john.doe@example.com
	 * 				  <br>
	 *                <table border=1px>
	 *                <tr>
	 *                <th>Key</th>
	 *                <th>Sample Data</th>
	 *                <th>Data Type</th>
	 *                <th>Constraint</th>
	 *                <th>Description</th>
	 *                </tr>
	 *                <tr>
	 *                <td>email</td>
	 *                <td>john.doe@example.com</td>
	 *                <td>String</td>
	 *                <td>Mandatory</td>
	 *                <td>email of the user</td>
	 *                </tr>
	 *                </table>
	 * @param
	 *                <table border=1px>
	 *                <tr>
	 *                <th>Header Key</th>
	 *                <th>Sample Data</th>
	 *                <th>Data Type</th>
	 *                <th>Constraint</th>
	 *                <th>Description</th>
	 *                </tr>
	 *                <tr>
	 *                <td>User-Agent</td>
	 *                <td>Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36</td>
	 *                <td>String</td>
	 *                <td>Mandatory</td>
	 *                <td>user agent</td>
	 *                </tr>
	 *                <tr>
	 *                <td>Content-Type</td>
	 *                <td>application/json</td>
	 *                <td>String</td>
	 *                <td>Mandatory</td>
	 *                <td>Request content type.</td>
	 *                </tr>
	 *                </table>
	 *                <br>
	 * @see <b>Functionality: </b> This API is used to reset password
	 * @return <b>SUCCESS MESSAGE:</b>
	 *
	 *         <b>ERROR MESSAGE:</b> <br> TODO test this
	 *
	 */
	@RequestMapping(value = "user/reset-password", method = RequestMethod.POST, headers = Literal.APPLICATION_JSON)
	public Map<String, Object> resetPassword(@RequestBody Map<String, Object> req_map) {
		Map<String, Object> ret_map = new HashMap<String, Object>(Literal.SIX);
		try {
			String accessToken = new String(Base64.getDecoder().decode(request.getHeader(Literal.accessToken)));
			String login_id = new String(Base64.getDecoder().decode(request.getHeader(Literal.username)));
			if(!TokenService.getInstance().validateToken(accessToken, login_id)) {
				logger.info("token validation failed" + request.getRequestURI());
				return null;
			}
			/**
			 * Check for null password
			 */
			if (Utility.chkNullObj(req_map.get(Literal.password))) {
				ret_map.put(Literal.STATUS, Literal.ERROR);
				ret_map.put(Literal.MESSAGE, Literal.NULL_PASSWORD);
				ret_map.put(Literal.REQUEST_DATA, req_map);
				return ret_map;
			}
			/**
			 * Password validation  https://stackoverflow.com/questions/3802192/regexp-java-for-password-validation
			 * regex : ^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\S+$).{8,}$
			 *
			 * a digit must occur at least once
			 * a lower case letter must occur at least once
			 * an upper case letter must occur at least once
			 * a special character must occur at least once
			 * no whitespace allowed in the entire string
			 * at least eight places though
			 *
			 */
			if (!Utility.chkValidPassword(req_map.get(Literal.password).toString())) {
				ret_map.put(Literal.STATUS, Literal.ERROR);
				ret_map.put(Literal.MESSAGE, Literal.INVALID_PASSWORD);
				ret_map.put(Literal.REQUEST_DATA, req_map);
				return ret_map;
			}
			/**
			 * reset password
			 */
			if(userservice.resetPassword(req_map.get(Literal.password).toString(), accessToken)) {
				ret_map.put(Literal.STATUS, Literal.SUCCESS);
				ret_map.put(Literal.MESSAGE, Literal.PASSWORD_SET_SUCCESS);
				return ret_map;
			}
			ret_map = new HashMap<>(Literal.EIGHT);
			ret_map.put(Literal.STATUS, Literal.ERROR);
			ret_map.put(Literal.MESSAGE, Literal.SOMETHING_WENT_WORNG);
			return ret_map;
		} catch (Exception e) {
			ret_map = new HashMap<>(Literal.EIGHT);
			ret_map.put(Literal.STATUS, Literal.ERROR);
			ret_map.put(Literal.MESSAGE, Literal.SOMETHING_WENT_WORNG);
			ret_map.put(Literal.EXCEPTION_MESAGE, e.getStackTrace());
			Utility.printStackTrace(e, this.getClass().getName());
			return ret_map;
		}
	}
}
