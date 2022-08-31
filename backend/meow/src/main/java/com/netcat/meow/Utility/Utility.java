package com.netcat.meow.Utility;

import org.apache.commons.io.FileUtils;
import org.bson.Document;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Utility {
	public static final String DEFAULT_AVATAR = "assets/images/avatars/no-profile-picture.jpg";
	public static final String DEFAULT_ACTIVE_STATUS = "online"; // online, busy, away, invisible
	public static final boolean DEBUG_EMAIL = false;
    /**
	 * login collection
	 */
	public static String col_login = "login";
	public static String col_master = "master";
	public static String col_notes = "notes";
	public static String col_notes_label = "notes_label";
    /**
	 * Make and load the object
	 */
	private static Utility INSTANCE;
	/***
	 * Hold the class path Resource
	 */
	static ClassPathResource resource = new ClassPathResource("/application.properties");
	/**
	 * Hold the property
	 */
	public static Properties props;
	/**
	 * Get the root directory
	 */
	public static String rootDir = System.getenv().get("DM_HOME");
	/**
	 * Utility file name
	 */
	private final static String db_filename = "db.properties";
	/**
	 * is MONGODB connection on Localhost
	 */
	public final static boolean isLocalMango = false;
	/**
	 * Get the database local
	 */
	public static String local_mongoDb_Databse = "meow";
	/**
	 * Get the database
	 */
	public static String mongoDb_Databse = "meow";
	/**
	 * MONGO IP
	 */
	public static String mongoDb_ip = "meow-shard-00-01.nqrji.mongodb.net";
	/**
	 * MONGO IP local
	 */
	public static String local_mongoDb_ip = "localhost";
	/**
	 * MONGO PORT
	 */
	public static int mongoDb_port = 27017;
	/**
	 * MONGO PORT local
	 */
	public static int local_mongoDb_port = 27017;
	/**
	 * MONGO CONNECTION POOL COUNT
	 */
	public static int mongoDb_pool_count = 40;
	/**
	 * MONGO Username
	 */
	public static String mongoDb_usr_name = "groot";
	/**
	 * MONGO Username local
	 */
	public static String local_mongoDb_usr_name = "groot";
	/**
	 * MONGO Password
	 */
	public static String mongoDb_pwd = "root";
	/**
	 * MONGO Password local
	 */
	public static String local_mongoDb_pwd = "root";
	/**
	 * SET NOTIFICATION PERMISSION
	 */
	public static boolean isNotify = true;
	/**
	 * UTF_8 IP
	 */
	public static final String UTF_8 = StandardCharsets.UTF_8.toString();
	/**
	 * Set error log file path
	 */
	public static String ERROR_HTML_PATH = "/tmp" + File.separator + "error.txt";
	/**
	 * Set MASTER_AUTH
	 */
	public static final String MASTER_AUTH = "groot";
	/**
	 * Set File asset Path
	 */
	public static String FOLDERASSETPATH = rootDir + File.separator + "Files" + File.separator;
	/**
	 * For the loading the property
	 */
	private Utility() {
		if (rootDir == null) {
			rootDir = ".";
		}
		try {
			props = PropertiesLoaderUtils.loadProperties(resource);
			/**
			 * Get notification permission
			 */
			Utility.isNotify=Boolean.parseBoolean(props.getProperty("notification").trim());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			ERROR_HTML_PATH = rootDir + File.separator + "logs" + File.separator + "error.txt";
			File error_file = new File(ERROR_HTML_PATH);
			error_file.createNewFile();
			FOLDERASSETPATH = rootDir + File.separator + "Files" + File.separator;
		} catch (Exception e) {
			Utility.EmptyErrorFile();
		}
		/** Read db.properties file */
		readDbProperties();
	}
	/**
	 *
	 * @return
	 */
	public static void EmptyErrorFile() {
		try {
			/**
			 * Empty the file
			 */
			Files.write(Paths.get(Utility.ERROR_HTML_PATH), " ".getBytes());
		} catch (Exception e) {
			Utility.printStackTrace(e);
		}
	}

	/**
	 *
	 * @param e
	 */
	public static void printStackTrace(Exception e) {
		try {
			PrintWriter stacktrace = new PrintWriter(
					new BufferedWriter(new FileWriter(Paths.get(Utility.ERROR_HTML_PATH).toFile().toString(), true)));
			stacktrace.write("<tr><td>" + new Date() + "</td>");
			if (e.getClass().getName().equals("com.fasterxml.jackson.core.JsonParseException")) {
				stacktrace.write("<td>6001 </td><td>");
			} else if (e.getClass().getName().equals("java.io.FileNotFoundException")) {
				stacktrace.write("<td>2001 </td><td>");
			} else if (e.getClass().getName().equals("java.lang.NullPointerException")) {
				stacktrace.write("<td>3001 </td><td>");
			} else if (e.getClass().getName().equals("java.lang.IllegalArgumentException")) {
				stacktrace.write("<td>4001 </td><td>");
			} else if (e.getClass().getName().equals("java.lang.JsonMappingException")) {
				stacktrace.write("<td>5001 </td><td>");
			} else if (e.getClass().getName().equals("javax.xml.parsers.ParserConfigurationException")) {
				stacktrace.write("<td>7001 </td><td>");
			} else if (e.getClass().getName().equals("org.xml.sax.SAXException")) {
				stacktrace.write("<td>8001 </td>");
			} else if (e.getClass().getName().equals("java.io.IOException")) {
				stacktrace.write("<td>9001 </td><td>");
			} else if (e.getClass().getName().equals("org.w3c.dom.DOMException")) {
				stacktrace.write("<td>9901 </td><td>");
			} else if (e.getClass().getName().equals("java.net.MalformedURLException")) {
				stacktrace.write("<td>9801 </td><td>");
			} else if (e.getClass().getName().equals("java.net.ProtocolException")) {
				stacktrace.write("<td>9802 </td><td>");
			} else if (e.getClass().getName().equals("java.io.UnsupportedEncodingException")) {
				stacktrace.write("<td>2201 </td><td>");
			} else if (e.getClass().getName().equals("java.net.UnknownHostException")) {
				stacktrace.write("<td>9803 </td><td>");
			} else {
				stacktrace.write("<td>1001 </td><td>");
			}
			e.printStackTrace(stacktrace);
			stacktrace.write("</td><td>-</td></tr>");
			stacktrace.flush();
			stacktrace.close();
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public static void printStackTrace(Exception e, String class_name) {
		try {
			PrintWriter stacktrace = new PrintWriter(
					new BufferedWriter(new FileWriter(Paths.get(Utility.ERROR_HTML_PATH).toFile().toString(), true)));
			stacktrace.write("<tr><td>" + new Date() + "</td>");
			if (e.getClass().getName().equals("com.fasterxml.jackson.core.JsonParseException")) {
				stacktrace.write("<td>6001 </td><td>");
			} else if (e.getClass().getName().equals("java.io.FileNotFoundException")) {
				stacktrace.write("<td>2001 </td><td>");
			} else if (e.getClass().getName().equals("java.lang.NullPointerException")) {
				stacktrace.write("<td>3001 </td><td>");
			} else if (e.getClass().getName().equals("java.lang.IllegalArgumentException")) {
				stacktrace.write("<td>4001 </td><td>");
			} else if (e.getClass().getName().equals("java.lang.JsonMappingException")) {
				stacktrace.write("<td>5001 </td><td>");
			} else if (e.getClass().getName().equals("javax.xml.parsers.ParserConfigurationException")) {
				stacktrace.write("<td>7001 </td><td>");
			} else if (e.getClass().getName().equals("org.xml.sax.SAXException")) {
				stacktrace.write("<td>8001 </td><td>");
			} else if (e.getClass().getName().equals("java.io.IOException")) {
				stacktrace.write("<td>9001 </td><td>");
			} else if (e.getClass().getName().equals("org.w3c.dom.DOMException")) {
				stacktrace.write("<td>9901 </td><td>");
			} else if (e.getClass().getName().equals("java.net.MalformedURLException")) {
				stacktrace.write("<td>9801 </td><td>");
			} else if (e.getClass().getName().equals("java.net.ProtocolException")) {
				stacktrace.write("<td>9802 </td><td>");
			} else if (e.getClass().getName().equals("java.io.UnsupportedEncodingException")) {
				stacktrace.write("<td>2201 </td><td>");
			} else if (e.getClass().getName().equals("java.net.UnknownHostException")) {
				stacktrace.write("<td>9803 </td><td>");
			} else {
				stacktrace.write("<td>1001 </td><td>");
			}
			e.printStackTrace(stacktrace);
			stacktrace.write("</td><td>" + class_name + "</td></tr>");
			stacktrace.flush();
			stacktrace.close();
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	/**
	 *
	 * @return
	 */
	public static void loadProperties() {
		INSTANCE = new Utility();
	}

	/**
	 * @return
	 */
	public static Utility getInstance() {
		return INSTANCE;
	}

	/**
	 *
	 * @param postJsonData
	 * @param url
	 * @param hitfor
	 * @return
	 */
	public static void POSTHIT(String postJsonData, String url, String hitfor) {
		new Thread() {
			public void run() {
				Thread.currentThread().setName("Send EMAIL");
				try {
					URL obj = new URL(url);
					HttpURLConnection con = (HttpURLConnection) obj.openConnection();
					/**
					 * Setting basic post request
					 */
					con.setRequestMethod("POST");
					con.setRequestProperty(Literal.USER_AGENT, " Mozilla/5.0");
					con.setRequestProperty("Content-Type", "application/json");
					/**
					 * Send post request
					 */
					con.setDoOutput(true);
					DataOutputStream wr = new DataOutputStream(con.getOutputStream());
					wr.writeBytes(postJsonData);
					wr.flush();
					wr.close();
					/**
					 *
					 */
					con.getResponseCode();
					/**
					 * Read
					 */
					BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
					String output;
					StringBuffer response = new StringBuffer();

					while ((output = in.readLine()) != null) {
						response.append(output);
					}
					in.close();
					/**
					 * Check for the response
					 */
					System.out.println(hitfor + " :: " + new Timestamp(System.currentTimeMillis()) + " :: Response :: "
							+ response + "URL :: " + url);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	/**
	 * @param data String
	 * @return true if match Mobile RegEx else False
	 */
	public static boolean chkMobileRegEx(String data) {
		if (data.matches(Literal.MobileRegEx)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param data String
	 * @return true if match Name RegEx else False
	 */
	public static boolean chkName(String data) {
		if (data.matches(Literal.NameRegEx)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param data String
	 * @return true if match Email RegEx else False
	 */
	public static boolean chkEmailRegex(String data) {
		if (data.matches(Literal.EmailRegEx)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param data String
	 * @return true if match PinCode RegEx else False
	 */
	public static boolean chkPinCode(String data) {
		if (data.matches(Literal.PinCodeRegEx)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Hold the Aes key
	 */
	private final static String KEY = "meowmeowmeow";
	/**
	 * Hold the Aes key
	 */
	private final static String INITVECTOR = "iamgrootuser";
	/**
	 * Hold the Aes key
	 */
	private final static String TOKEN_KEY = "netcatnetcatmeow";
	/**
	 * Hold the Aes key
	 */
	private final static String TOKEN_INITVECTOR = "meowmeowmeowmeow";
	private final static String MD5 = "MD5";
	private final static String SUN = "SUN";
	public static String getKey() {
		return KEY;
	}

	public static String getInitvector() {
		return INITVECTOR;
	}

	public static String getTokenKey() {
		return TOKEN_KEY;
	}

	public static String getTokenInitvector() {
		return TOKEN_INITVECTOR;
	}

	public static String getMd5() {
		return MD5;
	}

	public static String getSun() {
		return SUN;
	}

	public static boolean chkDOB(String data) {
		return data.matches(Literal.DOBRegEx) ? true : false;
	}

	public static boolean chkValidPassword(String data) {
		return data.matches(Literal.PasswordRegEx) ? true : false;
	}

	/**
	 */
	public void readDbProperties() {
		try {
			/**
			 * Get the file input stream
			 */
			FileInputStream reader = new FileInputStream(rootDir + File.separator + "conf" + File.separator + db_filename);
			/**
			 * Check for the stream
			 */
			if (reader != null) {
				/**
				 * Load the file
				 */
				props.load(reader);
				Utility.mongoDb_Databse = props.getProperty("mongoDb_Databse").trim();
				Utility.mongoDb_usr_name = props.getProperty("mongoDb_usr_name").trim();
				Utility.mongoDb_pwd = props.getProperty("mongoDb_pwd").trim();
				Utility.mongoDb_ip = props.getProperty("mongoDb_ip").trim();
				Utility.mongoDb_port = Integer.parseInt(props.getProperty("mongoDb_port").trim());
				Utility.mongoDb_pool_count = Integer.parseInt(props.getProperty("mongoDb_pool_count").trim());
			}

		}catch(Exception e) {
			Utility.printStackTrace(e, this.getClass().getName());
		}
	}

	public static String convertMStoHHMMSS(long ms) {
		try {
			long HH = TimeUnit.MILLISECONDS.toHours(ms);
			long MM = TimeUnit.MILLISECONDS.toMinutes(ms) % 60;
			long SS = TimeUnit.MILLISECONDS.toSeconds(ms) % 60;

			return String.format("%02d:%02d:%02d", HH, MM, SS);
		} catch(Exception e) {
			Utility.printStackTrace(e, Utility.class.getName());
			return null;
		}
	}
	/**
	 * @param list_user
	 * @return
	 */
	public static StringBuilder listToSBConverter(final List<String> list_user) {
		/**
		 * Object to hold the data
		 */
		StringBuilder sb_data=new StringBuilder(Literal.EMPTY_STRING);
		try{
			/**
			 * Check for list length
			 */
			if(list_user==null || list_user.size()==Literal.ZERO_VALUE) {
				return sb_data;
			}
			/**
			 * Flag to frame string builder
			 */
			boolean bool_flag=Literal.TRUE;
			/**
			 * Parse the list
			 */
			for(String str_user:list_user) {
				if(bool_flag) {
					sb_data.append(str_user);
					bool_flag=Literal.FALSE;
				}else {
					sb_data.append(Literal.COMMA);
					sb_data.append(str_user);
				}
			}
		}catch(Exception e) {
			Utility.printStackTrace(e, Utility.class.getName());
		}
		/**
		 * Return builder object
		 */
		return sb_data;
	}
	/**
	 * @param str_ts
	 * @return
	 */
	public Date getISODateTimeFromString(final String str_ts) {
		try{
			/**
			 * Objects for date time format
			 */
			DateFormat sdf_iso = new SimpleDateFormat(Literal.iso_format);
			SimpleDateFormat sdf = new SimpleDateFormat(Literal.ts_format);
			/**
			 * Set timezone for the simple date format
			 */
			sdf.setTimeZone(TimeZone.getTimeZone(Literal.UTC));
			/**
			 * Return iso date formatted data
			 */
			return sdf_iso.parse(sdf_iso.format(sdf.parse(str_ts)));
		}catch(Exception e) {
			Utility.printStackTrace(e, Utility.class.getName());
			return null;
		}
	}
	/**
	 * @param doc
	 * @param key
	 * @return
	 */
	public static String getDocStr(Document doc,String key) {
		try{
			if(doc.get(key)==null) {
				return Literal.EMPTY_STRING;
			}
			return doc.getString(key);
		}catch(Exception e) {
			Utility.printStackTrace(e, Utility.class.getName());
			return Literal.EMPTY_STRING;
		}
	}
	/**
	 * @param doc_login
	 * @param key
	 * @return
	 */
	public static long getDocLong(Document doc_login,String key) {
		try{
			if(doc_login.get(key)==null) {
				return Literal.ZERO_VALUE;
			}
			return doc_login.getLong(key);
		}catch(Exception e) {
			Utility.printStackTrace(e, Utility.class.getName());
			return Literal.ZERO_VALUE;
		}
	}
	/**
	 * @param name
	 * @return
	 */
	public static String getExtension(String name) {
		try {
			String f[] = name.split("\\.");
			return f[f.length - 1];
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	/**
	 * Change the folder
	 *
	 * @param o
	 * @return
	 */
	public static void MAKEFOLDER(File file) {
		try {
			if(!file.exists()) {
				file.mkdirs();
			}
		} catch (Exception e) {
			Utility.printStackTrace(e, Utility.class.getName());
		}
	}
	/**
	 *
	 * @return
	 */
	public static String getCurrDate() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(date);
	}
	/**
	 * Get the File name
	 *
	 * @param parent_path
	 * @param file_name
	 * @return
	 */
	public static File getFile(String parent_path, String file_name) {
		File file = new File(parent_path + File.separator + file_name);
		String f[] = file_name.split("\\.");
		String name = f[0];
		String extension = f[f.length - 1];
		int i = 0;
		while (file.exists()) {
			file = new File(parent_path + File.separator + name + "_" + i + "_." + extension);
			i++;
		}
		return file;
	}
	/**
	 * @param obj_data
	 * @return
	 */
	public static boolean chkNullObj(Object obj_data) {
		if(obj_data==null || obj_data.toString().trim().equals(Literal.EMPTY_STRING)) {
			return Literal.TRUE;
		}
		return Literal.FALSE;
	}
	/**
	 * @param data String
	 * @return true if I,A,W Device Type else False
	 * android, IOS, Web
	 */
	public static boolean chkDeviceType(String data) {
		if (data.equals(Literal.I) || data.equals(Literal.W) || data.equals(Literal.A)) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * @param rqst_map
	 * @param doc_srch
	 */
	public static void appendDateSrch(Map<String,Object> rqst_map,Document doc_srch) {
		try {
			/**
			 * Document to hold date range condition
			 */
			Document doc_date=new Document();;
			/**
			 * Check for start date,Append start date time
			 */
			if (!(rqst_map.get(Literal.start_date) == null
					|| rqst_map.get(Literal.start_date).equals(Literal.EMPTY_STRING))) {
				doc_date.append(Literal.$gte,Timestamp.valueOf(rqst_map.get(Literal.start_date)+" 00:00:00.00").getTime());
			}
			/**
			 * Check for end date,Append end_date time
			 */
			if (!(rqst_map.get(Literal.end_date) == null
					|| rqst_map.get(Literal.end_date).equals(Literal.EMPTY_STRING))) {
				doc_date.append(Literal.$lte,Timestamp.valueOf(rqst_map.get(Literal.end_date)+" 23:59:59.00").getTime());
			}
			/**
			 * Check for empty doc date
			 */
			if(!doc_date.isEmpty()) {
				doc_srch.append(Literal.CREATION_TIME_MS,doc_date);
			}
		}catch(Exception e) {
			Utility.printStackTrace(e,Utility.class.getName());
		}
	}
	/**
	 * @param file
	 * @param id
	 * @return
	 */
	private static String uploadImgAttachment(MultipartFile file,String type, String id,String file_name,String extension) {
		File tmp_file = null;
		try {
			/**
			 * Create new path
			 */
			String file_path=Utility.FOLDERASSETPATH+type+ File.separator + Utility.getCurrDate()+ File.separator + id;
			/**
			* Make the folder
			*/
			Utility.MAKEFOLDER(new File(file_path));
			/**
			* Get the write file
			*/
			tmp_file = Utility.getFile(file_path, file_name);
			FileOutputStream fos = new FileOutputStream(tmp_file);
			/**
			 * Write image to the file
			 */
			fos.write(file.getBytes());
			fos.close();
			/**
			 * Check for non-png format for the image
			 */
			if(extension.equalsIgnoreCase(Literal.PNG)) {
				/**
		         * Return image path
		         */
		        return type+ File.separator+Utility.getCurrDate()+ File.separator + id+File.separator+file_name;
			}else {
				/**
				 * Read the file
				 */
				BufferedImage originalImage= ImageIO.read(tmp_file);
				/**
				 * Frame new file name
				 */
				String new_filename=file_name.split("\\.")[0]+".png";
				/**
				 * Create png file
				 */
				File conv_file = Utility.getFile(file_path, new_filename);
		       /**
		        * Save the file to png format
		        */
		        ImageIO.write(originalImage, Literal.PNG, conv_file);
		        /**
		         * Return image path
		         */
		        return type+ File.separator+Utility.getCurrDate()+ File.separator + id+File.separator+new_filename;
			}
	        
		} catch (Exception e) {
			Utility.printStackTrace(e, Utility.class.getName());
		}finally {
			if(!extension.equalsIgnoreCase(Literal.PNG) && tmp_file!=null && tmp_file.exists()) {
				/**
				 * Remove the temporary file
				 */
				tmp_file.delete();
			}
		}
		return Literal.EMPTY_STRING;
	}
	/**
	 * @param file
	 * @param id
	 * @return
	 */
	public static String uploadAttachment(MultipartFile file,String type, String id) {
		try {
			/**
			* Get file name
			*/
			String fileName = file.getOriginalFilename();
			String extension = Utility.getExtension(fileName);
			/**
			* Check for the extension
			*/
			if (extension.equalsIgnoreCase(Literal.JPG) || extension.equalsIgnoreCase(Literal.PNG)
				|| extension.equalsIgnoreCase(Literal.JPEG)) {
			/**
			 * Upload image attachment
			 */
			return Utility.uploadImgAttachment(file,type,id,fileName,extension);	
			}else if(extension.equalsIgnoreCase(Literal.XLS) || extension.equalsIgnoreCase(Literal.XLSX)
					 || extension.equalsIgnoreCase(Literal.PDF) || extension.equalsIgnoreCase(Literal.CSV)
					 || extension.equalsIgnoreCase(Literal.DOC) || extension.equalsIgnoreCase(Literal.DOCX)
					 || extension.equalsIgnoreCase(Literal.TXT) || extension.equalsIgnoreCase(Literal.ZIP)) {
				/**
				 * Upload excel attachment
				 */
				return Utility.uploadDocAttachment(file,type,id,fileName);	
			}else {
				return Literal.MINUS_ONE;
			}
		} catch (Exception e) {
			Utility.printStackTrace(e, Utility.class.getName());
		}
		return Literal.THREE;
	}
	/**
	 * @param file
	 * @param id
	 * @return
	 */
	private static String uploadDocAttachment(MultipartFile file,String type, String id,String file_name) {
		File tmp_file = null;
		try {
			/**
			 * Create new path
			 */
			String file_path=Utility.FOLDERASSETPATH+type+ File.separator + Utility.getCurrDate()+ File.separator + id;
			/**
			* Make the folder
			*/
			Utility.MAKEFOLDER(new File(file_path));
			/**
			* Get the write file
			*/
			tmp_file = Utility.getFile(file_path, file_name);
			FileOutputStream fos = new FileOutputStream(tmp_file);
			/**
			 * Write image to the file
			 */
			fos.write(file.getBytes());
			fos.close();
	        /**
	         * Return image path
	         */
			return type+ File.separator+Utility.getCurrDate()+ File.separator + id+File.separator+file_name;
		} catch (Exception e) {
			Utility.printStackTrace(e, Utility.class.getName());
		}
		return Literal.EMPTY_STRING;
	}
	/**
	 * @param old_file
	 * @param new_file
	 */
	public static void copyFile(File old_file,File new_file) {
		try {
			FileUtils.copyFile(old_file, new_file);
		} catch (Exception e) {
			Utility.printStackTrace(e, Utility.class.getName());
		}
	}

	/**
	 * @param log_time
	 * @return
	 */
	public static Date getISODateTimeFromMS(final long log_time) {
		try{
			/**
			 * Objects for date time format
			 */
			DateFormat sdf_iso = new SimpleDateFormat(Literal.iso_format);
			SimpleDateFormat sdf = new SimpleDateFormat(Literal.ts_format);
			/**
			 * Set timezone for the simple date format
			 */
			sdf.setTimeZone(TimeZone.getTimeZone(Literal.UTC));
			/**
			 * Return iso date formatted data
			 */
			return sdf_iso.parse(sdf_iso.format(sdf.parse(new Timestamp(log_time).toString())));
		}catch(Exception e) {
			Utility.printStackTrace(e, Utility.class.getName());
			return null;
		}
	}

	public List<LocalDate> getDatesBetween(LocalDate startDate, LocalDate endDate) {
		long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate);
		return IntStream.iterate(0, i -> i + 1)
				.limit(numOfDaysBetween+1)
				.mapToObj(startDate::plusDays)
				.collect(Collectors.toList());
	}

	public static String getVerificationMessage(String name, String verification_link) {
		return "Hello "+name+"\n" +
				"You registered an account on LMAO, before being able to use your account you need to verify that this is your email address by clicking here: "+verification_link+"\n" +
				"\n" +
				"Kind Regards, LMAO Team";
	}

}