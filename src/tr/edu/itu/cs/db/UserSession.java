package tr.edu.itu.cs.db;

import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;


public class UserSession extends WebSession {

    public UserSession(Request request) {
        super(request);
        // TODO Auto-generated constructor stub
    }

    private UserModel UserModel;

    public static UserSession getInstance() {
        return (UserSession) Session.get();
    }

    public UserModel getUserModel() {
        return UserModel;
    }

    public void setUserModel(UserModel userModel) {
        this.UserModel = userModel;
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

}
