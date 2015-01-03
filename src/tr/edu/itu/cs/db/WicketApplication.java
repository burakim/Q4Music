package tr.edu.itu.cs.db;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;


public class WicketApplication extends WebApplication {
    @Override
    public void init() {

        super.init();
        getApplicationSettings().setPageExpiredErrorPage(Login.class);
        getDebugSettings().setAjaxDebugModeEnabled(false);
    }

    @Override
    public Class<? extends WebPage> getHomePage() {

        return Login.class;
    }

    @Override
    public Session newSession(Request request, Response response) {
        // TODO Auto-generated method stub
        return new UserSession(request);
    }

}
