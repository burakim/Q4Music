package tr.edu.itu.cs.db;

import java.io.Serializable;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;


public class HomePage extends DefaultPage implements Serializable {

    public HomePage(boolean isrequestok) {
        WebMarkupContainer w1 = new WebMarkupContainer("successinfo");
        if (isrequestok == true) {
            w1.setVisible(true);
        } else
            w1.setVisible(false);
        Model<String> mstr = Model.of("Welcome "
                + UserSession.getInstance().getUserModel().GetFullName());
        Label lbl = new Label("WelcomeMsg", mstr);
        add(lbl);

        Link linkQuestions = new Link("linkQuestions") {
            @Override
            public void onClick() {
                // TODO Auto-generated method stub
                setResponsePage(new QuestionCategories(false));
            }
        };

        Link linkArchiveSongs = new Link("linkArchiveSongs") {
            @Override
            public void onClick() {
                // TODO Auto-generated method stub
                setResponsePage(new SongsPage());
            }
        };

        Link HomePageLink = new Link("HomePageLink") {
            @Override
            public void onClick() {
                // TODO Auto-generated method stub
                setResponsePage(new HomePage(false));
            }
        };

        this.add(HomePageLink);

        this.add(w1);
        this.add(linkArchiveSongs);
        this.add(linkQuestions);

    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

}
