package Com;

public class ServiceUrl {
	private static String SERVICE_HOST = "http://localhost:1234";
	// User 
	public static String GET_LIST_USER = SERVICE_HOST +   "/users";
	public static String SAVE_USER = SERVICE_HOST + "/users";
	// login
	public static String LOGIN_SUBMIT = SERVICE_HOST + "/login";
}