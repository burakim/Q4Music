package tr.edu.itu.cs.db;

import java.util.ArrayList;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.Model;


public class UserSearchDetails extends DefaultPage {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private DbOperations db;
    private TextField tbUsername;
    private TextField tbFullname;
    private TextField tbEMail;
    private Form formInfo;
    private UserModel usermodel;
    private DataView dv;
    private ArrayList<UserStatisticsModel> list;

    public UserSearchDetails(int id) {

        db = new DbOperations();
        usermodel = db.FetchAllUserByUser(id);

        tbUsername = new TextField("tbUsername", new Model(
                usermodel.GetUserName()));
        tbFullname = new TextField("tbFullname", new Model(
                usermodel.GetFullName()));
        tbEMail = new TextField("tbEMail", new Model(
                usermodel.GetEMailAddress()));

        formInfo = new Form("formInfo");
        formInfo.add(tbUsername);
        formInfo.add(tbFullname);
        formInfo.add(tbEMail);
        this.add(formInfo);

        list = db.GetUserDetailsById(id);

        dv = new DataView("usertablerow", new ListDataProvider(list)) {
            @Override
            protected void populateItem(Item item) {
                // TODO Auto-generated method stub
                final UserStatisticsModel usm = (UserStatisticsModel) item
                        .getModelObject();

                item.add(new Label("tdSongType", usm.GetQuizSongType()));
                item.add(new Label("tdTotalScore", usm.GetTotalScore()));
                item.add(new Label("tdTrueAnswer", usm.GetTrueAnswerCount()));
                item.add(new Label("tdFalseAnswer", usm.GetFalseAnswerCount()));
            }

        };
        this.add(dv);

        Link lnkHomePage = new Link("lnkHomePage") {
            @Override
            public void onClick() {
                // TODO Auto-generated method stub
                setResponsePage(new HomePage(false));
            }
        };

        this.add(lnkHomePage);

        Link lnkUserSearchDetails = new Link("lnkUserSearchDetails") {
            @Override
            public void onClick() {
                // TODO Auto-generated method stub
                setResponsePage(new UserSearchDetails(UserSession.getInstance()
                        .getUserModel().GetObjectId()));
            }
        };

        this.add(lnkUserSearchDetails);

    }
}
