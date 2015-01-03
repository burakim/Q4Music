package tr.edu.itu.cs.db.Admin;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;

import tr.edu.itu.cs.db.HomePage;
import tr.edu.itu.cs.db.UserSession;


public class AdminHomePage extends AdminDefaultPage {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public AdminHomePage() {
        Model<String> mstr = Model.of("Welcome "
                + UserSession.getInstance().getUserModel().GetFullName());
        Label lbl = new Label("WelcomeMsg", mstr);
        add(lbl);

        Link lnkHomePage = new Link("lnkHomePage") {
            @Override
            public void onClick() {
                // TODO Auto-generated method stub
                setResponsePage(new HomePage(false));
            }
        };

        this.add(lnkHomePage);

        Link lnkAdminDashboard = new Link("lnkAdminDashboard") {
            @Override
            public void onClick() {
                // TODO Auto-generated method stub
                setResponsePage(new AdminHomePage());
            }
        };

        this.add(lnkAdminDashboard);

    }
}
