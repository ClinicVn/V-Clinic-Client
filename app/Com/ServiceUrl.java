package Com;

public class ServiceUrl {
	private static String SERVICE_HOST = "http://45.117.160.37";
	// User 
	public static String GET_LIST_USER = SERVICE_HOST +   "/users";
	public static String SAVE_USER = SERVICE_HOST + "/users";
	public static String GET_USER_BY_CODE = SERVICE_HOST + "/users/";
	public static String DELETE_USER = SERVICE_HOST + "/users/";
	// login
	public static String LOGIN_SUBMIT = SERVICE_HOST + "/login";
}