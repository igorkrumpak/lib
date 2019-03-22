package si.iitech.lib.security.impl;

public class SecurityConstants {
	public static final String SECRET = "IITechSuperSecretKey";
	public static final long EXPIRATION_TIME = 864_000_000; // 10 days
	public static final String TOKEN_PREFIX = "IITech ";
	public static final String TOKEN = "Key";
	public static final String REGISTER_URL = "/users/register";
	public static final String H2CONSOLE = "/h2-console/**";
}
