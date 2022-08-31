package com.netcat.meow.User.Auth;

import com.netcat.meow.Email.EmailTemplate;
import com.netcat.meow.Email.SendMail;
import com.netcat.meow.Service.TokenService;
import com.netcat.meow.Utility.Literal;
import com.netcat.meow.Utility.Utility;
import jdk.jshell.execution.Util;
import org.bson.Document;
import org.springframework.data.mongodb.core.query.Update;

import javax.crypto.SealedObject;
import java.lang.reflect.Constructor;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserService {


    private UserMongoDao dao_mongo;
    private static UserService instance;
    private TokenService tokenservice;

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public UserService() {
        dao_mongo = UserMongoDao.getInstance();
        tokenservice = TokenService.getInstance();
    }

    public Map<String, Object> login(String user_agent, String host, Map<String, Object> req_map) {
        Map<String, Object> ret_map = new HashMap<String, Object>(Literal.EIGHT);
        try {
            /**
             * Get user detail from MONGODB
             */
            Document user_details = dao_mongo.getUserLoginDetails(req_map.get(Literal.login_id).toString(), req_map.get(Literal.password).toString());
            /**
             * Check for null login data value
             */
            if (user_details == null || user_details.isEmpty()) {
                /**
                 * means user does not exist
                 */
                ret_map.put(Literal.STATUS, Literal.ERROR);
                ret_map.put(Literal.MESSAGE, Literal.WRONG_USERNAME_PASSWORD);
                ret_map.put(Literal.REQUEST_DATA, req_map);
                return ret_map;
                /**
                 * check the signup email is verified or not
                 */
            } else if (user_details.get(Literal.email_confirmed) == null || !(boolean) user_details.get(Literal.email_confirmed)) {
                /**
                 * send confirmation email once again to confirm the user signup against a working email
                 */
                String link = Literal.BASE_URL_LOCAL_BACKEND + "user/verifyemail?login_id=" + user_details.get(Literal.login_id) + "&token=" + URLEncoder.encode(user_details.get(Literal.session_id).toString());
                SendMail.getInstance().send(user_details.get(Literal.login_id).toString(), EmailTemplate.VERIFICATION(user_details.get(Literal.name).toString(), link));
                /**
                 * means email is not verified
                 */
                ret_map.put(Literal.STATUS, Literal.ERROR);
                ret_map.put(Literal.MESSAGE, Literal.EMAIL_NOT_VERIFIED);
                ret_map.put(Literal.REQUEST_DATA, req_map);
                return ret_map;

            } else {
                /**
                 * Update login related data
                 */
                return updateLoginData(user_details, req_map, host, user_agent);
            }
        } catch (Exception e) {
            ret_map.put(Literal.STATUS, Literal.ERROR);
            ret_map.put(Literal.MESSAGE, Literal.SOMETHING_WENT_WORNG);
            ret_map.put(Literal.EXCEPTION_MESAGE, e.getStackTrace());
            ret_map.put(Literal.REQUEST_DATA, req_map);
            Utility.printStackTrace(e, this.getClass().getName());
            return ret_map;
        }
    }

    /**
     *
     */
    public Map<String, Object> updateLoginData(Document user_details, Map<String, Object> req_map, String host, String user_agent) {
        /**
         * Map to hold return data
         */
        Map<String, Object> ret_map = new HashMap<String, Object>(Literal.EIGHT);
        try {
            Date curr_time = Utility.getISODateTimeFromMS(System.currentTimeMillis());
            /**
             * Set update data
             */
            Update updt_login = new Update();
            updt_login.set(Literal.last_login_time, curr_time);
            updt_login.set(Literal.device_token, req_map.get(Literal.device_token));
            updt_login.set(Literal.device_os, req_map.get(Literal.device_os));
            updt_login.set(Literal.device_type, req_map.get(Literal.device_type));
            updt_login.set(Literal.region_id, host);
            /**
             * Make the Login user uuid
             */
            final String login_uuid = UUID.randomUUID().toString();
            /**
             * Generate token
             */
            String token = tokenservice.generateTokenLogin(user_details, user_agent, login_uuid);
            /**
             * Store token
             */
            updt_login.set(Literal.session_id, token);
            /**
             * Update fresh login data, insert into mongo
             */
            if (dao_mongo.updtLoginUsr(user_details.getString(Literal.login_id), updt_login)) {
                /**
                 * ret success
                 */
                ret_map.put(Literal.STATUS, Literal.SUCCESS);
                ret_map.put(Literal.MESSAGE, Literal.LOGIN_SUCCESSFULY);
                ret_map.put(Literal.LOGIN_TIME, curr_time);
                ret_map.put(Literal.TOKEN, token);
                ret_map.put(Literal.USERNAME, user_details.getString(Literal.login_id));
                return ret_map;
            }
            /**
             * ret error
             */
            ret_map.put(Literal.STATUS, Literal.ERROR);
            ret_map.put(Literal.MESSAGE, Literal.SOMETHING_WENT_WORNG);
            ret_map.put(Literal.REQUEST_DATA, req_map);
            return ret_map;
        } catch (Exception e) {
            /**
             * ret error
             */
            ret_map.put(Literal.STATUS, Literal.ERROR);
            ret_map.put(Literal.MESSAGE, Literal.SOMETHING_WENT_WORNG);
            ret_map.put(Literal.REQUEST_DATA, req_map);
            Utility.printStackTrace(e, this.getClass().getName());
            return ret_map;
        }
    }

    /**
     * Signup for new user
     */
    public Map<String, Object> signup(String user_agent, String host, Map<String, Object> req_map) {
        /**
         * Map to hold return data
         */
        Map<String, Object> ret_map = new HashMap<String, Object>(Literal.EIGHT);
        try {
            /**
             * hold the curr time
             */
            Date curr_time = Utility.getISODateTimeFromMS(System.currentTimeMillis());
            /**
             * Prep are the signup to insert into login collection
             */
            Document sign_up = new Document();
            /**
             * Set signup data
             */
            sign_up.append(Literal.name, req_map.get(Literal.name));
            sign_up.append(Literal.login_id, req_map.get(Literal.login_id));
            sign_up.append(Literal.password, req_map.get(Literal.password));
            sign_up.append(Literal.last_login_time, curr_time);
            sign_up.append(Literal.device_token, req_map.get(Literal.device_token));
            sign_up.append(Literal.device_os, req_map.get(Literal.device_os));
            sign_up.append(Literal.device_type, req_map.get(Literal.device_type));
            sign_up.append(Literal.region_id, host);
            sign_up.append(Literal.email_confirmed, Literal.FALSE);
            sign_up.append(Literal.avatar, Utility.DEFAULT_AVATAR);
            sign_up.append(Literal.active_status, Utility.DEFAULT_ACTIVE_STATUS);
            /**
             * Make the Login user uuid
             */
            final String login_uuid = UUID.randomUUID().toString();
            /**
             * Generate token
             */
            String token = tokenservice.generateTokenLogin(sign_up, user_agent, login_uuid);
            /**
             * Store token
             */
            sign_up.append(Literal.session_id, token);
            /**
             * Update fresh login data, insert into mongo
             */
            if (dao_mongo.insertLoginData(sign_up)) {
                /**
                 * send confirmation email to confirm the user signup against a working email
                 */
                String link = Literal.BASE_URL_LOCAL_FRONTEND + "email-confirmed?login_id=" + req_map.get(Literal.login_id) + "&token=" + URLEncoder.encode(token);
                SendMail.getInstance().send(req_map.get(Literal.login_id).toString(), EmailTemplate.VERIFICATION(req_map.get(Literal.name).toString(), link));
                /**
                 * ret success
                 */
                ret_map.put(Literal.STATUS, Literal.SUCCESS);
                ret_map.put(Literal.MESSAGE, Literal.SIGNUP_SUCCESSFULLY);
                ret_map.put(Literal.LOGIN_TIME, curr_time);
                ret_map.put(Literal.TOKEN, token);
                return ret_map;
            }
            /**
             * ret error
             */
            ret_map.put(Literal.STATUS, Literal.ERROR);
            ret_map.put(Literal.MESSAGE, Literal.SOMETHING_WENT_WORNG);
            ret_map.put(Literal.LOGIN_TIME, curr_time);
            ret_map.put(Literal.TOKEN, token);
            return ret_map;

        } catch (Exception e) {
            /**
             * ret error
             */
            ret_map.put(Literal.STATUS, Literal.ERROR);
            ret_map.put(Literal.MESSAGE, Literal.SOMETHING_WENT_WORNG);
            ret_map.put(Literal.EXCEPTION_MESAGE, e.getStackTrace());
            ret_map.put(Literal.REQUEST_DATA, req_map);
            Utility.printStackTrace(e, this.getClass().getName());
            return ret_map;
        }
    }

    public Map<String, Object> verifySignupEmail(String login_id, String token) {
        /**
         * Map to hold return data
         */
        Map<String, Object> ret_map = new HashMap<String, Object>(Literal.EIGHT);
        try {
            /**
             * Get user detail from MONGODB
             */
            Document user_details = dao_mongo.getUserLoginDetails(login_id);
            /**
             * Check for null login data value
             */
            if (user_details == null || user_details.isEmpty()) {
                /**
                 * means user does not exist
                 */
                ret_map.put(Literal.STATUS, Literal.ERROR);
                ret_map.put(Literal.MESSAGE, Literal.SOMETHING_WENT_WORNG);
                return ret_map;
            } else if (!user_details.get(Literal.session_id).equals(token)) {
                /**
                 * means token is invalid
                 */
                ret_map.put(Literal.STATUS, Literal.ERROR);
                ret_map.put(Literal.MESSAGE, Literal.SESSION_TOKEN_DOES_NOT_MATCH);
                return ret_map;
            } else {
                /**
                 * login_id and token matches in mongo, update the email_confirmed field
                 */
                Update update = new Update();
                update.set(Literal.email_confirmed, true);

                if (dao_mongo.updtLoginUsr(login_id, update)) {
                    /**
                     * ret success
                     */
                    ret_map.put(Literal.STATUS, Literal.SUCCESS);
                    ret_map.put(Literal.MESSAGE, Literal.EMAIL_VERIFIED_SUCCESSFULLY);
                    ret_map.put(Literal.TOKEN, token);
                    return ret_map;
                }
                /**
                 * ret error
                 */
                ret_map.put(Literal.STATUS, Literal.ERROR);
                ret_map.put(Literal.MESSAGE, Literal.SOMETHING_WENT_WORNG);
                ret_map.put(Literal.TOKEN, token);
                return ret_map;
            }
        } catch (Exception e) {
            /**
             * ret error
             */
            ret_map.put(Literal.STATUS, Literal.ERROR);
            ret_map.put(Literal.MESSAGE, Literal.SOMETHING_WENT_WORNG);
            ret_map.put(Literal.EXCEPTION_MESAGE, e.getStackTrace());
            Utility.printStackTrace(e, this.getClass().getName());
            return ret_map;
        }
    }

    public Document getUserDetails(String login_id) {
        return dao_mongo.getUserLoginDetails(login_id);
    }

    public boolean uploadProfilePic(String image, String login_id) {
        return dao_mongo.uploadProfilePic(image, login_id);
    }

    public Map<String, Object> sendPasswordResetEmail(String email) {
        Map<String, Object> ret_map = new HashMap<>();

        // check if email exist in our database
        Document user_details =  dao_mongo.getUserLoginDetails(email);

        try {
            if(user_details != null && !user_details.isEmpty()) {
                /**
                 * send password reset email to user
                 */
                String link = Literal.BASE_URL_LOCAL_FRONTEND + "reset-password?email="+URLEncoder.encode(email)+"&token=" + URLEncoder.encode(user_details.get("session_id").toString());
                SendMail.getInstance().send(email, EmailTemplate.PASSWORD_RESET(user_details.get(Literal.name).toString(), link));
                /**
                 * ret success
                 */
                ret_map.put(Literal.STATUS, Literal.SUCCESS);
                ret_map.put(Literal.MESSAGE, Literal.PASSWORD_RESET_EMAIL_SENT_SUCCESSFULLY);
                return ret_map;
            } else {
                /**
                 * ret error
                 */
                ret_map.put(Literal.STATUS, Literal.ERROR);
                ret_map.put(Literal.MESSAGE, Literal.EMAIL_INVALID);
                return ret_map;
            }

        } catch (Exception e) {
            /**
             * ret success
             */
            ret_map.put(Literal.STATUS, Literal.ERROR);
            ret_map.put(Literal.MESSAGE, Literal.SOMETHING_WENT_WORNG);
            return ret_map;
        }
    }

    public boolean resetPassword(String password, String token) {
        Document filter_doc = new Document().append(Literal.session_id, token);
        Update update = new Update().set(Literal.password, password);
        return dao_mongo.updateLoginData(update, filter_doc);
    }
}
 