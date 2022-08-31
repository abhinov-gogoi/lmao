package com.netcat.meow.Utility;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

public class Literal {
    /**
     * @Message="Something went wrong."
     */
    public final static String SOMETHING_WENT_WORNG = "Something went wrong.";
    /**
     * @Message="Login id already exists."
     */
    public final static String LOGIN_EXISTS = "Login id already exists.";
    /**
     * @Message="Data updated."
     */
    public final static String DATA_UPDATED = "Data updated.";
    /**
     * @Message="Data not found in the master list."
     */
    public final static String DATA_NOTFOUND = "Data not found in the master list.";
    /**
     * @Message="Invalid file."
     */
    public final static String INVALID_FILE = "Invalid file.";
    /**
     * @Message="Unable to upload the file."
     */
    public final static String NOT_UPLOAD = "Unable to upload the file.";
    /**
     * @Message="Data"
     */
    public final static String DATA = "DATA";
    /**
     * @Message="Data updated."
     */
    public final static String DATA_NOT_UPDATED = "Data not updated.";
    /**
     * @Message="Data already exists."
     */
    public final static String DATA_ALRDYXIST = "Data already exists.";
    /**
     * @Message="Kindly provide id."
     */
    public final static String PROVIDE_ID = "Kindly provide id.";
    /**
     * @Message="Kindly provide type."
     */
    public final static String PROVIDE_TYPE = "Kindly provide type.";
    /**
     * @Message="Kindly provide address."
     */
    public final static String PROVIDE_ADDRESS = "Kindly provide address.";
    /**
     * @Message="Wrong type."
     */
    public final static String WRONG_TYPE = "Wrong type.";
    /**
     * @Message="Error"
     */
    public final static String ERROR = "Error";
    /**
     * @Message="You are already logged in with some other device."
     */
    public final static String ALRDY_LOGGEDIN = "You are already logged in with some other device.";
    /**
     * @Message="Success"
     */
    public final static String SUCCESS = "Success";
    /**
     * @Message="STATUS"
     */
    public final static String STATUS = "STATUS";
    /**
     * @Message="MESSAGE"
     */
    public final static String MESSAGE = "MESSAGE";
    /**
     * @Message="REQUEST_DATA"
     */
    public final static String REQUEST_DATA = "REQUEST_DATA";
    /**
     * @Message=Accept=application/json
     */
    public final static String APPLICATION_JSON = "Accept=application/json";
    /**
     * @Message="EXCEPTION"
     */
    public final static String EXCEPTION_MESAGE = "EXCEPTION";

    /**
     * @Message="application"
     */
    public final static String application = "application";
    /**
     * @Message="UTF-8"
     */
    public final static String UTF8 = "UTF-8";
    /**
     * @Message=","
     */
    public final static String COMMA = ",";
    /**
     * @Message="DESCRIPTION"
     */
    public final static String DESCRIPTION = "DESCRIPTION";
    /**
     * @Message="location_id"
     */
    public final static String location_id = "location_id";
    /**
     * Return EMPTY_DOCUMENT
     */
    public final static Document EMPTY_DOCUMENT = new Document();
    /**
     * @Message="Kindly Provide mobile no."
     */
    public static final String MOBILENULL = "Kindly Provide mobile no.";
    /**
     * @Message="Kindly Provide correct mobile no."
     */
    public static final String WRONG_MOBILE = "Kindly Provide correct mobile no.(Ex xxxxxxxxxx)";
    /**
     * @Message="Kindly Provide device type."
     */
    public static final String DEVICE_TYPE_NULL = "Kindly Provide device type.";
    /**
     * @Message="Kindly Provide correct mobile no."
     */
    public static final String WRONG_DEVICE_TYPE = "Kindly Provide correct device type.(Ex A,I,W)";
    /**
     * @Message="I"
     */
    public final static String I = "I";
    /**
     * @Message="C"
     */
    public final static String C = "C";
    /**
     * @Message="R"
     */
    public final static String R = "R";
    /**
     * @Message="RO"
     */
    public final static String RO = "RO";
    /**
     * @Message="W"
     */
    public final static String W = "W";
    /**
     * @Message="A"
     */
    public final static String A = "A";
    /**
     * @Message="login_id"
     */
    public final static String login_id = "login_id";
    /**
     * @Message="role_id"
     */
    public final static String role_id = "role_id";
    /**
     * @Message="HELPER"
     */
    public final static String HELPER = "HELPER";
    /**
     * @Message="LOGIN_ID"
     */
    public final static String LOGIN_ID = "LOGIN_ID";
    /**
     * @Message="MOBILE"
     */
    public final static String MOBILE = "MOBILE";
    /**
     * @Message="Kindly Provide device token."
     */
    public static final String DEVICE_TOKEN_NULL = "Kindly Provide device token.";
    /**
     * @Message="Kindly Provide device OS."
     */
    public static final String DEVICE_OS_NULL = "Kindly Provide device os.";
    /**
     * @Message="AES"
     */
    public final static String AES = "AES";
    /**
     * AES CHIPER CLASS
     */
    public final static String AES_CHIPER_CLASS = "AES/CBC/PKCS5PADDING";
    /**
     * @Message="OTP_LENGTH"
     */
    public final static String OTP_LENGTH = "%04d";
    /**
     * @Message="OTP send successfully."
     */
    public final static String OTP_SEND_SUCCESSFULY = "OTP send successfully.";
    /**
     * {@value #MobileRegEx} Note : use as a variable
     */
    public static final String MobileRegEx = "^[0-9]{10}$";
    /**
     * {@value #PasswordRegEx} Note : https://stackoverflow.com/questions/3802192/regexp-java-for-password-validation
     */
    public static final String PasswordRegEx = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
    /**
     * @Message="ref_id"
     */
    public final static String ref_id = "ref_id";
    /**
     * @Message="name"
     */
    public final static String name = "name";
    /**
     * @Message="profile_name"
     */
    public final static String profile_name = "profile_name";
    /**
     * @Message="PROFILE_NAME"
     */
    public final static String PROFILE_NAME = "PROFILE_NAME";
    /**
     * @Message="NAME"
     */
    public final static String NAME = "NAME";
    /**
     * @Message="N"
     */
    public final static String N = "N";
    /**
     * @Message="email"
     */
    public final static String email = "email";
    /**
     * @Message="email_id"
     */
    public final static String email_id = "email_id";
    /**
     * @Message="EMAIL_ID"
     */
    public final static String EMAIL_ID = "EMAIL_ID";
    /**
     * @Message="punch_in"
     */
    public final static String punch_in = "punch_in";
    /**
     * @Message="punch_out"
     */
    public final static String punch_out = "punch_out";
    /**
     * @Message="password"
     */
    public final static String password = "password";
    /**
     * @Message="login_enable"
     */
    public final static String login_enable = "login_enable";
    /**
     * @Message="Y"
     */
    public final static String Y = "Y";
    /**
     * @Message="device_type"
     */
    public final static String device_type = "device_type";
    /**
     * @Message="host"
     */
    public final static String host = "host";
    /**
     * @Message="device_os"
     */
    public final static String device_os = "device_os";
    /**
     * @Message="device_token"
     */
    public final static String device_token = "device_token";
    /**
     * @Message="last_login_time"
     */
    public final static String last_login_time = "last_login_time";
    /**
     * @Message="region_id"
     */
    public final static String region_id = "region_id";
    /**
     * @Message="device_token_win_time"
     */
    public final static String device_token_win_time = "device_token_win_time";
    /**
     * @Message="session_id"
     */
    public final static String session_id = "session_id";
    /**
     * @Message="circle_id"
     */
    public final static String circle_id = "circle_id";
    /**
     * @Message="Kindly Provide reference id."
     */
    public static final String REFIDNULL = "Kindly provide reference id.";
    /**
     * @Message="Kindly provide valid name."
     */
    public static final String INVALID_NAME = "Kindly provide valid name.";
    /**
     * @Message="DEVICE NAME"
     */
    public static final String DEVICE_NAME = "DEVICE NAME";
    /**
     * @Message="Kindly provide valid email id."
     */
    public static final String INVALID_EMAIL = "Kindly provide valid email id.";
    /**
     * @Message="You have entered invalid amount value."
     */
    public static final String INVALID_AMT = "You have entered invalid amount value.";
    /**
     * @Message="Kindly provide valid pin code."
     */
    public static final String INVALID_PINCODE = "Kindly provide valid pin code.";
    /**
     * @Message="Kindly provide valid reference id."
     */
    public static final String INVALID_REFID = "Kindly provide valid reference id.";
    /**
     * @Message="Provide valid login id."
     */
    public static final String INVALID_LOGINID = "Provide valid login id.";
    /**
     * @Message="Kindly verify the mobile number first."
     */
    public static final String MOBILE_NOT_VERIFIED = "Kindly verify the mobile number first.";
    /**
     * @Message="Kindly provide device type."
     */
    public static final String INVALID_DEVICE_TYPE = "Kindly provide valid device type.";
    /**
     * @Message="Kindly provide device os."
     */
    public static final String DEVICE_OSNULL = "Kindly provide device os.";
    /**
     * @Message="Kindly provide device token."
     */
    public static final String DEVICE_TOKENNULL = "Kindly provide device token.";
    /**
     * {@value #NameRegEx} Note : use as a variable
     * https://stackoverflow.com/a/15806080
     */
    public static final String NameRegEx = "/^[a-z ,.'-]+$/i";
    /**
     * {@value #EmailRegEx}
     */
    public static final String EmailRegEx = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
    /**
     * {@value #EmailRegEx}
     */
    public static final String PinCodeRegEx = "^[1-9]{1}[0-9]{2}\\s{0,1}[0-9]{3}$";
    /**
     * @Message="make"
     */
    public final static String make = "make";
    /**
     * @Message="type"
     */
    public final static String type = "type";
    /**
     * @Message="TYPE"
     */
    public final static String TYPE = "TYPE";
    /**
     * @Message="VISITOR_TYPE"
     */
    public final static String VISITOR_TYPE = "VISITOR_TYPE";
    /**
     * @Message="master"
     */
    public final static String master = "master";
    /**
     * @Message="site"
     */
    public final static String site = "site";
    /**
     * @Message="site_id"
     */
    public final static String site_id = "site_id";
    /**
     * @Message="User not found."
     */
    public static final String USER_NOT_FOUND = "User not found.";
    /**
     * @Message="Invalid Id."
     */
    public static final String INVALID_ID = "Invalid Id.";
    /**
     * @Message="Reference id not matched."
     */
    public static final String REFERENCE_NOT_MATCHED = "Reference id not matched.";
    /**
     * @Message="OTP not processed.Please send otp."
     */
    public static final String InvalidOTP = "OTP not processed.Please send otp.";
    /**
     * @Message="otp"
     */
    public final static String otp = "otp";
    /**
     * @Message="UTC"
     */
    public final static String UTC = "UTC";
    /**
     * @Message="yyyy-MM-dd HH:mm:ss.SSS"
     */
    public final static String ts_format = "yyyy-MM-dd HH:mm:ss.SSS";
    /**
     * @Message="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
     */
    public final static String iso_format = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    /**
     * @Message="otp_time"
     */
    public final static String otp_time = "otp_time";
    /**
     * @Message="otp_time_ms"
     */
    public final static String otp_time_ms = "otp_time_ms";
    /**
     * @Message="Kindly Provide OTP."
     */
    public static final String OTPNULL = "Kindly provide OTP.";
    /**
     * @Message="OTP verify successfully."
     */
    public final static String OTP_VERIFY_SUCCESSFULY = "OTP verify successfully.";
    /**
     * Hold 8 value
     */
    public final static int EIGHT = 8;
    /**
     * Hold 6 value
     */
    public final static int SIX = 6;
    /**
     * Hold 0 value
     */
    public final static int ZERO_VALUE = 0;
    /**
     * Return EMPTY Map
     */
    public final static Map<String, Object> EMPTY_MAP = new WeakHashMap<String, Object>(Literal.ZERO_VALUE);
    /**
     * Return EMPTY ARRAYLISTMAP
     */
    public final static List<Map<String, Object>> EMPTYARRAYLISTMAP = new ArrayList<Map<String, Object>>(
            Literal.ZERO_VALUE);
    /**
     * Return EMPTYARRAYLISTSTR
     */
    public final static List<String> EMPTYARRAYLISTSTR = new ArrayList<String>(Literal.ZERO_VALUE);
    /**
     * Return EMPTYARRAYLISTSTR
     */
    public final static ArrayList<String> EMPTYARRAY = new ArrayList<String>(Literal.ZERO_VALUE);
    /**
     * Return EMPTYARRAYLIST
     */
    public final static List<Document> EMPTYARRAYLIST = new ArrayList<Document>(Literal.ZERO_VALUE);
    /**
     * Hold 0 string
     */
    public final static String ZERO = "0";
    /**
     * @Message="Wrong OTP."
     */
    public static final String OTP_NOT_MATCHED = "Wrong OTP.";
    /**
     * @Message="OTP expired."
     */
    public static final String OTP_EXPIRED = "OTP expired.";
    /**
     * @Message="Wrong mobile."
     */
    public static final String MOBILE_NOT_MATCHED = "Wrong mobile.";
    /**
     * @Message="::"
     */
    public static final String TOKEN_SPLITER = "::";
    /**
     * @Message="jpg"
     */
    public static final String JPG = "jpg";
    /**
     * @Message="jpeg"
     */
    public static final String JPEG = "jpeg";
    /**
     * @Message="png"
     */
    public static final String PNG = "png";
    /**
     * @Message="xls"
     */
    public static final String XLS = "xls";
    /**
     * @Message="xlsx"
     */
    public static final String XLSX = "xlsx";
    /**
     * @Message="pdf"
     */
    public static final String PDF = "pdf";
    /**
     * @Message="csv"
     */
    public static final String CSV = "csv";
    /**
     * @Message="doc"
     */
    public static final String DOC = "doc";
    /**
     * @Message="docx"
     */
    public static final String DOCX = "docx";
    /**
     * @Message="txt"
     */
    public static final String TXT = "txt";
    /**
     * @Message="zip"
     */
    public static final String ZIP = "zip";
    /**
     * @Message="notice"
     */
    public static final String NOTICE = "notice";
    /**
     * @Message="TOKEN_USER_ID"
     */
    public static final String TOKEN_USER_ID = "TOKEN_USER_ID";
    /**
     * @Message="TOKEN_LOGIN_ID"
     */
    public static final String TOKEN_LOGIN_ID = "TOKEN_LOGIN_ID";
    /**
     * @Message="TOKEN_NAME"
     */
    public static final String TOKEN_NAME = "TOKEN_NAME";
    /**
     * @Message="VALUE"
     */
    public static final String VALUE = "VALUE";
    /**
     * @Message="ID"
     */
    public static final String ID = "ID";
    /**
     * @Message="id"
     */
    public static final String id = "id";
    /**
     * @Message="_id"
     */
    public static final String _id = "_id";
    /**
     * @Message="TOKEN_MOBILE"
     */
    public static final String TOKEN_MOBILE = "TOKEN_MOBILE";
    /**
     * @Message="TOKEN_EMAIL"
     */
    public static final String TOKEN_EMAIL = "TOKEN_EMAIL";
    /**
     * @Message="TOKEN_TYPE"
     */
    public static final String TOKEN_TYPE = "TOKEN_TYPE";
    /**
     * @Message="TOKEN_SITE_ID"
     */
    public static final String TOKEN_SITE_ID = "TOKEN_SITE_ID";
    public static final String TOKEN_TIME = "TOKEN_TIME";
    public static final String TOKEN = "TOKEN";
    public static final String TOKEN_USER_AGENT = "TOKEN_USER_AGENT";
    public static final String LOGIN_UUID = "LOGIN_UUID";
    public final static String INVALID_TOKEN = "Invalid token.";
    public final static String TOKEN_MESSAGE = "TOKEN_MESSAGE";
    public final static String WRONG_TOKEN = "Wrong token.";
    public final static String USER_AGENT_NOT_MATCHED = "User-Agent not matched.";
    public final static String WRONG_USER = "Wrong user.";
    public final static String TOKEN_EXPIRE = "Token expire.";
    public final static String WRONG_SESSION = "Wrong session.";
    public static final boolean TRUE = true;
    public static final boolean FALSE = false;
    public static final String TOKEN_NULL = "Please provide token.";
    public static final String USER_AGENT_NULL = "Please provide User-Agent.";
    public final static String EMPTY_STRING = "";
    public final static String LOGIN_SUCCESSFULY = "Login successful";
    public final static String LOGIN_TIME = "LOGIN_TIME";
    public final static String USER_AGENT = "User-Agent";
    public final static String MINUS_ONE = "-1";
    public final static String THREE = "3";
    public final static String REFERENCE_ID = "REFERENCE_ID";
    public final static long FIFTEEN_MINUTE_MILI = 900000;
    public final static float TWENTY4HOUR_MILI = 2592000000F;
    public static final String INVALID_PASSWORD = "Kindly provide strong password. " +
            "Password should be of at least 8 characters with a mix of uppercase, lowercase and special characters and numbers";
    public final static String CREATION_TIME = "CREATION_TIME";
    public final static String creation_time = "creation_time";
    public final static String CREATION_TIME_MS = "CREATION_TIME_MS";
    public final static String LAST_UPDATED_TIME = "LAST_UPDATED_TIME";
    public final static String LAST_UPDATED_TIME_MS = "LAST_UPDATED_TIME_MS";
    public final static String COUNT = "COUNT";
    public final static String count = "count";
    public final static String start_date = "start_date";
    public final static String end_date = "end_date";
    public final static String r_count = "r_count";
    public final static String $elemMatch = "$elemMatch";
    public final static String $eq = "$eq";
    public final static String $gte = "$gte";
    public final static String $lte = "$lte";
    public static final String EMAIL_NULL = "Kindly provide email id";
    /**
     * @Message= stackoverflow.com/questions/15491894/
     */
    public static final String DOBRegEx = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
    public static final String message = "message";
    public static final String WRONG_USERNAME_PASSWORD = "Incorrect username or password";
    public static final String NULL_PASSWORD = "Kindly provide a password";
    public static final String ID_ALREADY_TAKEN = "This email id is already taken";
    public static final String SIGNUP_SUCCESSFULLY = "Signup successful";
    public static final String email_confirmed = "email_confirmed";
    public static final String APPLICATION = "LMAO";
    public static final String FROM = "noreply@lmao.com";
    public static final int PORT_465 = 465;
    public static final String WELCOME_TO_LMAO = "Welcome to LMAO!";
    public static final String EMAIL_INVALID = "Kindly provide a valid email";
    public static final String NAME_INVALID = "Kindly provide a valid name";
    public static final String NAME_NULL = "Kindly provide a name";
    public static final String EMAIL_NOT_VERIFIED = "A verification link was sent to your email. Please verify.";
    public static final String WRONG_USERNAME = "Kindly check your email";
    public static final String EMAIL_VERIFIED_SUCCESSFULLY = "Your email was verified successfully, please continue to sign in";
    public static final String SESSION_TOKEN_DOES_NOT_MATCH = "Session Expired";
    public static final String BASE_URL_LOCAL_BACKEND = "http://localhost:8080/";
    public static final String note = "note";
    public static final int ONE = 1;
    public static final String HOST = "";
    public static final String SMTP_USERNAME = "";
    public static final String SMTP_PASSWORD = "";
    public static final String USERNAME = "USERNAME";
    public static final String accessToken = "accessToken";
    public static final String username = "username";
    public static final String title = "title";
    public static final String content = "content";
    public static final String archived = "archived";
    public static final String tasks = "tasks";
    public static final String createdAt = "createdAt";
    public static final String updatedAt = "updatedAt";
    public static final String image = "image";
    public static final String labels = "labels";
    public static final String $pull = "$pull";
    public static final String avatar = "avatar";
    public static final String active_status = "active_status";
    public static final String status = "status";
    public static final String PASSWORD_RESET_EMAIL_SENT_SUCCESSFULLY = "A link to reset your password was sent to your email.";
    public static final String BASE_URL_LOCAL_FRONTEND = "http://localhost:4200/";
    public static final String BASE_URL_PROD_FRONTEND = "https://abhinov-gogoi.github.io/lmao/";
    public static final String LMAO = "LMAO !!";
    public static final String token = "token";
    public static final String PASSWORD_SET_SUCCESS = "Successfully set new password";
}




