package tr.edu.itu.cs.db;

import java.util.ArrayList;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.request.mapper.parameter.PageParameters;


public class SongsPage extends DefaultPage {

    PageParameters pp = new PageParameters();
    private DbOperations SongsOp;
    private ArrayList<SongModel> list;

    public SongsPage() {
        super();

        SongsOp = new DbOperations();
        list = SongsOp.FetchAllSongs();

        final DataView dv = new DataView("songtablerow", new ListDataProvider(
                list)) {
            @Override
            protected void populateItem(final Item item) {
                final SongModel song = (SongModel) item.getModelObject();
                item.add(new Label("tdName", song.getName()));
                item.add(new Label("tdSinger", SongsOp.getSingerNameById(song
                        .getSingerId())));
                item.add(new Label("tdType", SongsOp.getSongTypeNameById(song
                        .getTypeId())));
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

        Link lnkAdminUserManagement = new Link("lnkSongOperations") {
            @Override
            public void onClick() {
                // TODO Auto-generated method stub
                setResponsePage(new SongsPage());
            }
        };

        this.add(lnkAdminUserManagement);
    }
}
