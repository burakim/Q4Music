package tr.edu.itu.cs.db;

import java.io.Serializable;
import java.util.ArrayList;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.Model;

import tr.edu.itu.cs.db.Admin.AdminHomePage;
import tr.edu.itu.cs.db.panels.UserScorePanel;


public class MasterPageContentHeaderPanel extends Panel implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private DbOperations op;

    public MasterPageContentHeaderPanel(String id) {
        super(id);
        Model<String> mstr = Model.of(UserSession.getInstance().getUserModel()
                .GetFullName());
        Label lbl = new Label("LoginedUsername", mstr);
        add(lbl);

        Link lnkAnsayfaLogo = new Link("AnsayfaLogo") {
            public void onClick() {
                if (UserSession.getInstance().getUserModel().GetUserType()
                        .equals("user")) {
                    setResponsePage(new HomePage(false));
                } else
                    setResponsePage(new AdminHomePage());

            };
        };

        Link myStatistics = new Link("myStatistics") {
            public void onClick() {
                setResponsePage(new UserSearchDetails(UserSession.getInstance()
                        .getUserModel().GetObjectId()));
            };
        };

        Link lblLogOut = new Link("logout") {
            @Override
            public void onClick() {
                // TODO Auto-generated method stub
                UserSession.getInstance().invalidate();
                setResponsePage(Login.class);
            }
        };
        add(lblLogOut);
        // TODO Auto-generated constructor stub
        Link lblMyProfile = new Link("lnkMyProfile") {
            @Override
            public void onClick() {
                setResponsePage(new UsersProfile());

            }
        };
        add(lblMyProfile);

        add(myStatistics);

        op = new DbOperations();
        ArrayList<UserStatisticsModel> data = op.GetUserDetailsById(UserSession
                .getInstance().getUserModel().GetObjectId());

        RepeatingView repatingview = new RepeatingView("repeater");
        for (int i = 0; i < data.size(); i++) {
            WebMarkupContainer c = new WebMarkupContainer(
                    repatingview.newChildId());
            repatingview.add(c);
            UserScorePanel usp = new UserScorePanel("scorelist", data.get(i)
                    .GetTotalScore(), data.get(i).GetQuizSongType());
            c.add(usp);
        }
        this.add(repatingview);

    }
}
