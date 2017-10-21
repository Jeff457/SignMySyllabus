public class AuthorizationHeader
{
    public static final AuthorizationHeader INSTANCE = new AuthorizationHeader();

    private AuthorizationHeader() {}

    private final String Username = "\"jstanton397@gmail.com\"";
    private final String Password = "\"dfui4016\"";
    private final String IntegratorKey = "\"1c01c227-f26b-4d86-b194-f8fd20171a99\"";

    public String toString() {
        return "{ \"Username\": " + Username + ", \"Password\": " + Password + ", \"IntegratorKey\": " + IntegratorKey + "}";
    }
}