package tr.edu.itu.cs.db;

import java.io.Serializable;
import java.util.ArrayList;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;

import tr.edu.itu.cs.db.panels.TopList;


public class MasterPageContentBodyPanel extends Panel implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private DbOperations op;

    public MasterPageContentBodyPanel(String id) {
        super(id);
        // TODO Auto-generated constructor stub

        QuestionLink questionpage = new QuestionLink("quizbttn");
        this.add(questionpage);

        Link linkSongs = new Link("songsPage") {
            @Override
            public void onClick() {
                // TODO Auto-generated method stub
                setResponsePage(new SongsPage());
            }
        };

        Link linkSingers = new Link("singersPage") {
            @Override
            public void onClick() {
                // TODO Auto-generated method stub
                setResponsePage(new SingersPage());
            }
        };

        Link linkHomePage = new Link("lblHomePage") {
            @Override
            public void onClick() {
                // TODO Auto-generated method stub
                setResponsePage(new HomePage(false));
            }
        };

        Link lblLogOutBody = new Link("logoutbody") {
            @Override
            public void onClick() {
                // TODO Auto-generated method stub
                UserSession.getInstance().invalidate();
                setResponsePage(new Login());
            }
        };
        Link lblProfileSettings = new Link("lblProfileSettings") {
            @Override
            public void onClick() {

                setResponsePage(new UsersProfile());
            }
        };

        Link searchuser = new Link("searchuser") {
            @Override
            public void onClick() {

                setResponsePage(new SearchUser(null));
            }
        };

        Link lblMyStatistics = new Link("lblMyStatistics") {
            @Override
            public void onClick() {
                // TODO Auto-generated method stub
                setResponsePage(new UserSearchDetails(UserSession.getInstance()
                        .getUserModel().GetObjectId()));
            }
        };

        op = new DbOperations();

        ArrayList<UserStatisticsModel> data = op.GetTop5List();

        RepeatingView repatingview = new RepeatingView("liTopList");
        int dataSize = data.size();

        for (int i = 0; i < dataSize; i++) {
            WebMarkupContainer c = new WebMarkupContainer(
                    repatingview.newChildId());
            repatingview.add(c);
            TopList usp = new TopList("topListPanel", data.get(i)
                    .GetUserModel().GetFullName(), data.get(i).GetTotalScore(),
                    i + 1);
            c.add(usp);
        }
        this.add(repatingview);

        this.add(linkHomePage);
        this.add(lblLogOutBody);
        this.add(linkSingers);
        this.add(linkSongs);
        this.add(lblProfileSettings);

        this.add(searchuser);
        this.add(lblMyStatistics);

    }
}
