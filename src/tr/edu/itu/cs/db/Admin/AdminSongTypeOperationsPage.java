package tr.edu.itu.cs.db.Admin;

import java.util.ArrayList;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import tr.edu.itu.cs.db.DbOperations;
import tr.edu.itu.cs.db.SongTypeModel;


public class AdminSongTypeOperationsPage extends AdminDefaultPage {

    PageParameters pp = new PageParameters();
    private DbOperations SongTypesOp;
    private ArrayList<SongTypeModel> list;

    public AdminSongTypeOperationsPage() {
        super();

        SongTypesOp = new DbOperations();
        list = SongTypesOp.FetchAllSongTypes();

        final DataView dv = new DataView("songtypetablerow",
                new ListDataProvider(list)) {
            @Override
            protected void populateItem(final Item item) {
                final SongTypeModel songType = (SongTypeModel) item
                        .getModelObject();
                item.add(new Label("tdID", songType.getID()));
                item.add(new Label("tdName", songType.getName()));

                Link testLnk = new Link("tdEdit") {
                    @Override
                    public void onComponentTag(ComponentTag tag) {
                        // TODO Auto-generated method stub
                        super.onComponentTag(tag);
                        tag.put("id", songType.getID());
                        tag.put("name", songType.getName());
                    }

                    @Override
                    public void onClick() {
                        // // TODO Auto-generated method stub
                    }
                };
                testLnk.add(new AttributeAppender("class", " SongTypeModal"));
                item.add(testLnk);

            }
        };

        this.add(dv);

        final TextField tb_ID = new TextField("tb_ID", new Model(""));
        final TextField tb_Name = new TextField("tb_Name", new Model(""));
        final Button btnSaveChanges = new Button("btnSaveChanges");

        Form Form_Details = new Form("Form_Details") {
            @Override
            public void onSubmit() {
                int _tb_ID = Integer.parseInt(tb_ID.getValue());
                String _tb_Name = (String) tb_Name.getModelObject();

                if (SongTypesOp.UpdateSongTypeById(_tb_ID, _tb_Name))
                    setResponsePage(new AdminSongTypeOperationsPage());
            }
        };

        Form_Details.add(tb_ID);
        Form_Details.add(tb_Name);
        Form_Details.add(btnSaveChanges);
        this.add(Form_Details);

        Link HomePageLink = new Link("HomePageLink") {
            @Override
            public void onClick() {
                // TODO Auto-generated method stub
                setResponsePage(new AdminHomePage());
            }
        };

        this.add(HomePageLink);

        Link lnkAdminUserManagement = new Link("lnkAdminSongTypeOperations") {
            @Override
            public void onClick() {
                // TODO Auto-generated method stub
                setResponsePage(new AdminSongTypeOperationsPage());
            }
        };

        this.add(lnkAdminUserManagement);

    }
}
