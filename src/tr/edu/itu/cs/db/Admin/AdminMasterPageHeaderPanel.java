package tr.edu.itu.cs.db.Admin;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import tr.edu.itu.cs.db.Login;
import tr.edu.itu.cs.db.UserSession;


public class AdminMasterPageHeaderPanel extends Panel {

    public AdminMasterPageHeaderPanel(String id) {
        super(id);
        // TODO Auto-generated constructor stub
        Model<String> mstr = Model.of(UserSession.getInstance().getUserModel()
                .GetFullName());
        Label lbl = new Label("LoginedUsername", mstr);
        add(lbl);

        Link lblLogOut = new Link("logout") {
            @Override
            public void onClick() {
                // TODO Auto-generated method stub
                UserSession.getInstance().invalidate();
                setResponsePage(Login.class);
            }
        };
        add(lblLogOut);

        Link adminHomePageLink = new Link("adminHomePageLink") {
            public void onClick() {
                setResponsePage(new AdminHomePage());
            };
        };
        add(adminHomePageLink);
    }

}
