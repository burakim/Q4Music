package tr.edu.itu.cs.db.Admin;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

import tr.edu.itu.cs.db.Login;
import tr.edu.itu.cs.db.UserSession;


public class AdminMasterPageBodyPanel extends Panel {

    public AdminMasterPageBodyPanel(String id) {
        super(id);
        // TODO Auto-generated constructor stub
        Link linkHomePage = new Link("lblHomePage") {
            @Override
            public void onClick() {
                // TODO Auto-generated method stub
                setResponsePage(new AdminHomePage());
            }
        };

        Link linkUsers = new Link("MenuUsers") {
            @Override
            public void onClick() {
                // TODO Auto-generated method stub
                setResponsePage(new AdminUserManagement());
            }
        };

        Link linkSongs = new Link("MenuSongs") {
            @Override
            public void onClick() {
                // TODO Auto-generated method stub
                setResponsePage(new AdminSongOperationsPage());
            }
        };

        Link linkSingers = new Link("MenuSingers") {
            @Override
            public void onClick() {
                // TODO Auto-generated method stub
                setResponsePage(new AdminSingerOperationsPage());
            }
        };

        Link linkSongTypes = new Link("MenuSongTypes") {
            @Override
            public void onClick() {
                // TODO Auto-generated method stub
                setResponsePage(new AdminSongTypeOperationsPage());
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
        Link lblQuestionBody = new Link("questionPage") {
            @Override
            public void onClick() {
                // TODO Auto-generated method stub
                setResponsePage(new AdminQuestionsPage());
            }
        };
        Link lblHintBody = new Link("hintPage") {
            @Override
            public void onClick() {
                // TODO Auto-generated method stub
                setResponsePage(new AdminHintsPage());
            }
        };
        Link lblChoiceBody = new Link("choicePage") {
            @Override
            public void onClick() {
                // TODO Auto-generated method stub
                setResponsePage(new AdminChoicePage());
            }
        };
        this.add(lblQuestionBody);
        this.add(lblHintBody);
        this.add(lblChoiceBody);
        this.add(linkHomePage);
        this.add(lblLogOutBody);
        this.add(linkUsers);
        this.add(linkSongs);
        this.add(linkSingers);
        this.add(linkSongTypes);
    }

}
