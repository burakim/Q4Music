package tr.edu.itu.cs.db;

import java.util.ArrayList;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.request.mapper.parameter.PageParameters;


public class SingersPage extends DefaultPage {

    PageParameters pp = new PageParameters();
    private DbOperations SingersOp;
    private ArrayList<SingerModel> list;

    public SingersPage() {
        super();

        SingersOp = new DbOperations();
        list = SingersOp.FetchAllSingers();

        final DataView dv = new DataView("singertablerow",
                new ListDataProvider(list)) {
            @Override
            protected void populateItem(final Item item) {
                final SingerModel singer = (SingerModel) item.getModelObject();
                item.add(new Label("tdNameSurname", singer.getNameSurname()));
                item.add(new Label("tdBirthDate", singer.getBirthDate()));

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

        Link HomePageLink = new Link("HomePageLink") {
            @Override
            public void onClick() {
                // TODO Auto-generated method stub
                setResponsePage(new HomePage(false));
            }
        };

        this.add(HomePageLink);

        Link lnkAdminUserManagement = new Link("lnkSingerOperations") {
            @Override
            public void onClick() {
                // TODO Auto-generated method stub
                setResponsePage(new SingersPage());
            }
        };

        this.add(lnkAdminUserManagement);
    }
}
