package tr.edu.itu.cs.db.Admin;

import java.text.ParseException;
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
import tr.edu.itu.cs.db.SingerModel;


public class AdminSingerOperationsPage extends AdminDefaultPage {

    PageParameters pp = new PageParameters();
    private DbOperations SingersOp;
    private ArrayList<SingerModel> list;

    public AdminSingerOperationsPage() {
        super();

        SingersOp = new DbOperations();
        list = SingersOp.FetchAllSingers();

        final DataView dv = new DataView("singertablerow",
                new ListDataProvider(list)) {
            @Override
            protected void populateItem(final Item item) {
                final SingerModel singer = (SingerModel) item.getModelObject();
                item.add(new Label("tdID", singer.getID()));
                item.add(new Label("tdNameSurname", singer.getNameSurname()));
                item.add(new Label("tdBirthDate", singer.getBirthDate()));

                Link testLnk = new Link("tdEdit") {
                    @Override
                    public void onComponentTag(ComponentTag tag) {
                        // TODO Auto-generated method stub
                        super.onComponentTag(tag);
                        tag.put("id", singer.getID());
                        tag.put("namesurname", singer.getNameSurname());
                        tag.put("birthdate", singer.getBirthDate());
                        tag.put("singertype", singer.getType());
                    }

                    @Override
                    public void onClick() {
                        // // TODO Auto-generated method stub
                    }
                };
                testLnk.add(new AttributeAppender("class", " SingerModal"));
                item.add(testLnk);

                Label lblType = new Label("tdType", singer.getType());
                if (singer.getType().equals("Person")) {
                    lblType.add(new AttributeAppender("class",
                            " label label-success"));
                } else {
                    lblType.add(new AttributeAppender("class",
                            " label label-danger"));
                }
                item.add(lblType);
            }
        };

        this.add(dv);

        final TextField tb_ID = new TextField("tb_ID", new Model(""));
        final TextField tb_NameSurname = new TextField("tb_NameSurname",
                new Model(""));
        final TextField tb_BirthDate = new TextField("tb_BirthDate", new Model(
                ""));
        final TextField tb_inputForSingerType = new TextField(
                "tbForSingerType", new Model(""));
        final Button btnSaveChanges = new Button("btnSaveChanges");

        Form Form_Details = new Form("Form_Details") {
            @Override
            public void onSubmit() {
                int _tb_ID = Integer.parseInt((String) tb_ID.getModelObject());
                String _tb_NameSurname = (String) tb_NameSurname
                        .getModelObject();
                String _tb_BirthDate = (String) tb_BirthDate.getModelObject();
                String _tb_inputForSingerType = (String) tb_inputForSingerType
                        .getModelObject();
                try {
                    if (SingersOp.UpdateSingerById(_tb_ID, _tb_NameSurname,
                            _tb_BirthDate, _tb_inputForSingerType)) {
                        setResponsePage(new AdminSingerOperationsPage());
                    }
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        };

        Form_Details.add(tb_ID);
        Form_Details.add(tb_NameSurname);
        Form_Details.add(tb_BirthDate);
        Form_Details.add(tb_inputForSingerType);
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

        Link lnkAdminUserManagement = new Link("lnkAdminSingerOperations") {
            @Override
            public void onClick() {
                // TODO Auto-generated method stub
                setResponsePage(new AdminSingerOperationsPage());
            }
        };

        this.add(lnkAdminUserManagement);

    }
}
