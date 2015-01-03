package tr.edu.itu.cs.db.Admin;

import org.apache.wicket.markup.html.WebPage;

import tr.edu.itu.cs.db.ErrorPage;
import tr.edu.itu.cs.db.UserSession;


public class AdminDefaultPage extends WebPage {

    public AdminDefaultPage() {
        if (UserSession.getInstance().getUserModel().GetUserType() != "admin")
            setResponsePage(new ErrorPage());
        add(new AdminMasterPageHeaderPanel("panel1"));
        add(new AdminMasterPageBodyPanel("panel2"));
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

}
