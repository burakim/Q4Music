package tr.edu.itu.cs.db;

import java.util.ArrayList;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.Model;


public class SearchUser extends DefaultPage {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Button btnSearch;
    private Form formSearch;
    private TextField tbSearch;
    private ArrayList<UserStatisticsModel> list;
    private DbOperations dbop;
    private DataView dv;
    // ///////
    private TextField tbUsername;
    private TextField tbFullname;
    private Form formDetails;

    // /////////

    public SearchUser(ArrayList<UserStatisticsModel> newlist) {

        if (newlist == null) {
            list = new ArrayList<UserStatisticsModel>();
        } else {
            list = newlist;
        }

        dbop = new DbOperations();
        btnSearch = new Button("btnSearch");
        tbSearch = new TextField("tbSearch", new Model(""));
        formSearch = new Form("formSearch") {
            @Override
            protected void onSubmit() {
                String _tbSearch = (String) tbSearch.getModelObject();
                if (_tbSearch != null) {
                    list = dbop.SearchUserTotalScoreByFullName(_tbSearch);
                    setResponsePage(new SearchUser(list));
                }
            }
        };
        formSearch.add(btnSearch);
        formSearch.add(tbSearch);
        this.add(formSearch);

        formDetails = new Form("formDetails");
        tbUsername = new TextField("tbUsername", new Model(""));
        tbFullname = new TextField("tbFullname", new Model(""));
        formDetails.add(tbUsername);
        formDetails.add(tbFullname);
        this.add(formDetails);

        dv = new DataView("usertablerow", new ListDataProvider(list)) {
            @Override
            protected void populateItem(Item item) {
                // TODO Auto-generated method stub
                final UserStatisticsModel usm = (UserStatisticsModel) item
                        .getModelObject();
                item.add(new Label("tdUsername", usm.GetUserModel()
                        .GetUserName()));
                item.add(new Label("tdFullName", usm.GetUserModel()
                        .GetFullName()));
                item.add(new Label("tdTotalScore", usm.GetTotalScore()));
                item.add(new Label("tdTrueAnswer", usm.GetTrueAnswerCount()));
                item.add(new Label("tdFalseAnswer", usm.GetFalseAnswerCount()));
                // item.add(new Label("lnDetails", "Details"));
                Link lnkDetails = new Link("lnDetails") {
                    @Override
                    public void onClick() {
                        // tbUsername.setModel(new Model(usm.GetUserModel()
                        // .GetUserName()));
                        setResponsePage(new UserSearchDetails(usm
                                .GetUserModel().GetObjectId()));
                    }
                };
                item.add(lnkDetails);
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

        Link lnkSearchUser = new Link("lnkSearchUser") {
            @Override
            public void onClick() {
                // TODO Auto-generated method stub
                setResponsePage(new SearchUser(null));
            }
        };

        this.add(lnkSearchUser);

    }
}
