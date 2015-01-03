package tr.edu.itu.cs.db.Admin;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
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
import tr.edu.itu.cs.db.SongModel;
import tr.edu.itu.cs.db.SongTypeModel;


public class AdminSongOperationsPage extends AdminDefaultPage {

    PageParameters pp = new PageParameters();
    private DbOperations SongsOp;
    private ArrayList<SongModel> list;
    int typeIdToDelete;
    int singerIdToDelete;

    public AdminSongOperationsPage() {
        super();

        SongsOp = new DbOperations();
        list = SongsOp.FetchAllSongs();
        Form Form_Details = null;
        typeIdToDelete = 0;
        singerIdToDelete = 0;

        final DataView dv = new DataView("songtablerow", new ListDataProvider(
                list)) {
            @Override
            protected void populateItem(final Item item) {
                final SongModel song = (SongModel) item.getModelObject();
                item.add(new Label("tdID", song.getID()));
                item.add(new Label("tdName", song.getName()));
                item.add(new Label("tdSinger", SongsOp.getSingerNameById(song
                        .getSingerId())));
                item.add(new Label("tdType", SongsOp.getSongTypeNameById(song
                        .getTypeId())));

                Link testLnk = new Link("tdEdit") {
                    @Override
                    public void onComponentTag(ComponentTag tag) {
                        // TODO Auto-generated method stub
                        super.onComponentTag(tag);
                        tag.put("id", song.getID());
                        tag.put("name", song.getName());
                        tag.put("singerName",
                                SongsOp.getSingerNameById(song.getSingerId()));
                        ;
                        tag.put("typeName",
                                SongsOp.getSongTypeNameById(song.getTypeId()));
                    }

                    @Override
                    public void onClick() {
                        // // TODO Auto-generated method stub
                    }
                };
                testLnk.add(new AttributeAppender("class", " SongModal"));
                item.add(testLnk);

                Link testDeleteLink = new Link("tdDelete") {
                    @Override
                    public void onClick() {
                        // TODO Auto-generated method stub

                        if (SongsOp.getSongCountByTypeId(song.getID()) < 2
                                && SongsOp.getQuestionCountByTypeId(song
                                        .getTypeId()) < 1) {
                            typeIdToDelete = song.getTypeId();
                        }

                        if (SongsOp.getSongCountBySingerId(song.getSingerId()) < 2) {
                            singerIdToDelete = song.getSingerId();
                        }

                        SongsOp.DeleteSongById(song.getID());

                        if (typeIdToDelete != 0) {
                            SongsOp.DeleteSongTypeById(typeIdToDelete);
                        }
                        if (singerIdToDelete != 0) {
                            SongsOp.DeleteSingerById(singerIdToDelete);
                        }
                        SongsOp.CloseConnection();
                        setResponsePage(new AdminSongOperationsPage());
                    }
                };
                item.add(testDeleteLink);
            }
        };

        this.add(dv);

        Label lblCount = new Label("songcount", new Model("("
                + Integer.toString(list.size()) + ")"));
        this.add(lblCount);

        final TextField tb_ID = new TextField("tb_ID", new Model(""));
        final TextField tb_Name = new TextField("tb_Name", new Model(""));

        List<String> singerNameList = SongsOp.FetchAllSingerNames();
        singerNameList.add("Other");
        // final DropDownChoice singerChoice = new DropDownChoice("tb_Singer",
        // singerNameList);
        final DropDownChoice singerChoice = new DropDownChoice("tb_Singer",
                new Model<String>(""), singerNameList);

        // SongsOp
        // .getSingerNameById(SongsOp.GetSongBySongId(
        // Integer.parseInt((String) tb_ID
        // .getModelObject())).getSingerId())
        final TextField tb_SingerName = new TextField("tb_SingerName",
                new Model(""));
        final TextField tb_SingerBirthDate = new TextField(
                "tb_SingerBirthDate", new Model(""));
        final TextField tb_inputForSingerType = new TextField(
                "tbForSingerType", new Model(""));

        List<String> typeNameList = SongsOp.FetchAllSongTypeNames();
        typeNameList.add("Other");
        // DropDownChoice typeChoice = new DropDownChoice("tb_Type",
        // typeNameList);
        final DropDownChoice songTypeChoice = new DropDownChoice("tb_Type",
                new Model<String>(""), typeNameList);
        final TextField tb_TypeName = new TextField("tb_TypeName",
                new Model(""));

        final Button btnSaveChanges = new Button("btnSaveChanges");

        Form_Details = new Form("Form_Details") {
            @Override
            public void onSubmit() {

                String choiceSingerSelected = (String) singerChoice
                        .getModelObject();
                String choiceSongTypeSelected = (String) songTypeChoice
                        .getModelObject();

                int _tb_ID = Integer.parseInt((String) tb_ID.getModelObject());
                String _tb_Name = (String) tb_Name.getModelObject();

                String _tb_SingerName = (String) tb_SingerName.getModelObject();
                String _tb_SingerBirthDate = (String) tb_SingerBirthDate
                        .getModelObject();
                String _tb_inputForSingerType = (String) tb_inputForSingerType
                        .getModelObject();

                String _tb_TypeName = (String) tb_TypeName.getModelObject();

                if (choiceSingerSelected.equals("Other")) {
                    if (choiceSongTypeSelected.equals("Other")) {

                        if (SongsOp.getSongTypeNameById(SongsOp
                                .GetSongBySongId(_tb_ID).getTypeId()) != choiceSongTypeSelected
                                && SongsOp.getSongCountByTypeId(SongsOp
                                        .getSongTypeIdByName(_tb_TypeName)) < 2
                                && SongsOp.getQuestionCountByTypeId(SongsOp
                                        .getSongTypeIdByName(_tb_TypeName)) < 1
                                && SongsOp
                                        .getUserStatisticsCountByTypeId(SongsOp
                                                .getSongTypeIdByName(_tb_TypeName)) < 1
                                && SongsOp.getChallengeCountByTypeId(SongsOp
                                        .getSongTypeIdByName(_tb_TypeName)) < 1) {
                            typeIdToDelete = SongsOp
                                    .getSongTypeIdByName(_tb_TypeName);
                        }

                        if (SongsOp.getSingerNameById(SongsOp.GetSongBySongId(
                                _tb_ID).getSingerId()) != choiceSingerSelected
                                && SongsOp.getSongCountBySingerId(SongsOp
                                        .getSingerIdByName(_tb_SingerName)) < 2) {
                            singerIdToDelete = SongsOp.GetSongBySongId(_tb_ID)
                                    .getSingerId();
                        }

                        SongTypeModel songType = new SongTypeModel();
                        songType.setName(_tb_TypeName);
                        SongsOp.AddSongType(songType);

                        SingerModel singer = new SingerModel();
                        singer.setNameSurname(_tb_SingerName);
                        singer.setBirthDate(_tb_SingerBirthDate);
                        singer.setType(_tb_inputForSingerType);
                        try {
                            SongsOp.AddSinger(singer);
                        } catch (ParseException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                        singer.setType(_tb_inputForSingerType);

                        SongsOp.UpdateSongById(_tb_ID, _tb_Name,
                                SongsOp.getSongTypeIdByName(_tb_TypeName),
                                SongsOp.getSingerIdByName(_tb_SingerName));

                        if (typeIdToDelete != 0) {
                            SongsOp.DeleteSongTypeById(typeIdToDelete);
                        }
                        if (singerIdToDelete != 0) {
                            SongsOp.DeleteSingerById(singerIdToDelete);
                        }

                    } else {

                        if (SongsOp.getSongTypeNameById(SongsOp
                                .GetSongBySongId(_tb_ID).getTypeId()) != choiceSongTypeSelected
                                && SongsOp.getSongCountByTypeId(SongsOp
                                        .GetSongBySongId(_tb_ID).getTypeId()) < 2
                                && SongsOp.getQuestionCountByTypeId(SongsOp
                                        .GetSongBySongId(_tb_ID).getTypeId()) < 1
                                && SongsOp
                                        .getUserStatisticsCountByTypeId(SongsOp
                                                .GetSongBySongId(_tb_ID)
                                                .getTypeId()) < 1
                                && SongsOp.getChallengeCountByTypeId(SongsOp
                                        .GetSongBySongId(_tb_ID).getTypeId()) < 1) {
                            typeIdToDelete = SongsOp
                                    .getSongTypeIdByName(_tb_TypeName);
                        }

                        if (SongsOp.getSingerNameById(SongsOp.GetSongBySongId(
                                _tb_ID).getSingerId()) != choiceSingerSelected
                                && SongsOp.getSongCountBySingerId(SongsOp
                                        .getSingerIdByName(_tb_SingerName)) < 2) {
                            singerIdToDelete = SongsOp.GetSongBySongId(_tb_ID)
                                    .getSingerId();
                        }

                        SingerModel singer = new SingerModel();
                        singer.setNameSurname(_tb_SingerName);
                        singer.setBirthDate(_tb_SingerBirthDate);
                        singer.setType(_tb_inputForSingerType);

                        try {
                            SongsOp.AddSinger(singer);
                        } catch (ParseException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                        SongsOp.UpdateSongById(_tb_ID, _tb_Name, SongsOp
                                .getSongTypeIdByName(choiceSongTypeSelected),
                                SongsOp.getSingerIdByName(_tb_SingerName));

                        if (typeIdToDelete != 0) {
                            SongsOp.DeleteSongTypeById(typeIdToDelete);
                        }
                        if (singerIdToDelete != 0) {
                            SongsOp.DeleteSingerById(singerIdToDelete);
                        }
                    }
                } else {
                    if (choiceSongTypeSelected.equals("Other")) {

                        if (SongsOp.getSongTypeNameById(SongsOp
                                .GetSongBySongId(_tb_ID).getTypeId()) != choiceSongTypeSelected
                                && SongsOp.getSongCountByTypeId(SongsOp
                                        .getSongTypeIdByName(_tb_TypeName)) < 2
                                && SongsOp.getQuestionCountByTypeId(SongsOp
                                        .getSongTypeIdByName(_tb_TypeName)) < 1
                                && SongsOp
                                        .getUserStatisticsCountByTypeId(SongsOp
                                                .getSongTypeIdByName(_tb_TypeName)) < 1
                                && SongsOp.getChallengeCountByTypeId(SongsOp
                                        .getSongTypeIdByName(_tb_TypeName)) < 1) {
                            typeIdToDelete = SongsOp
                                    .getSongTypeIdByName(_tb_TypeName);
                        }

                        if (SongsOp.getSingerNameById(SongsOp.GetSongBySongId(
                                _tb_ID).getSingerId()) != choiceSingerSelected
                                && SongsOp.getSongCountBySingerId(SongsOp
                                        .GetSongBySongId(_tb_ID).getSingerId()) < 2) {
                            singerIdToDelete = SongsOp.GetSongBySongId(_tb_ID)
                                    .getSingerId();
                        }

                        SongTypeModel songType = new SongTypeModel();
                        songType.setName(_tb_TypeName);
                        SongsOp.AddSongType(songType);

                        SongsOp.UpdateSongById(_tb_ID, _tb_Name,
                                SongsOp.getSongTypeIdByName(_tb_TypeName),
                                SongsOp.getSingerIdByName(choiceSingerSelected));

                        if (typeIdToDelete != 0) {
                            SongsOp.DeleteSongTypeById(typeIdToDelete);
                        }
                        if (singerIdToDelete != 0) {
                            SongsOp.DeleteSingerById(singerIdToDelete);
                        }

                    } else {
                        if (SongsOp.getSongTypeNameById(SongsOp
                                .GetSongBySongId(_tb_ID).getTypeId()) != choiceSongTypeSelected
                                && SongsOp.getSongCountByTypeId(SongsOp
                                        .GetSongBySongId(_tb_ID).getTypeId()) < 2
                                && SongsOp.getQuestionCountByTypeId(SongsOp
                                        .GetSongBySongId(_tb_ID).getTypeId()) < 1
                                && SongsOp
                                        .getUserStatisticsCountByTypeId(SongsOp
                                                .GetSongBySongId(_tb_ID)
                                                .getTypeId()) < 1
                                && SongsOp.getChallengeCountByTypeId(SongsOp
                                        .GetSongBySongId(_tb_ID).getTypeId()) < 1) {
                            typeIdToDelete = SongsOp.GetSongBySongId(_tb_ID)
                                    .getTypeId();
                        }

                        if (SongsOp.getSingerNameById(SongsOp.GetSongBySongId(
                                _tb_ID).getSingerId()) != choiceSingerSelected
                                && SongsOp.getSongCountBySingerId(SongsOp
                                        .GetSongBySongId(_tb_ID).getSingerId()) < 2) {
                            singerIdToDelete = SongsOp.GetSongBySongId(_tb_ID)
                                    .getSingerId();
                        }

                        SongsOp.UpdateSongById(_tb_ID, _tb_Name, SongsOp
                                .getSongTypeIdByName(choiceSongTypeSelected),
                                SongsOp.getSingerIdByName(choiceSingerSelected));

                        if (typeIdToDelete != 0) {
                            SongsOp.DeleteSongTypeById(typeIdToDelete);
                        }
                        if (singerIdToDelete != 0) {
                            SongsOp.DeleteSingerById(singerIdToDelete);
                        }

                    }
                }

                setResponsePage(new AdminSongOperationsPage());
            }
        };

        Form_Details.add(tb_ID);
        Form_Details.add(tb_Name);
        Form_Details.add(singerChoice);
        Form_Details.add(songTypeChoice);
        Form_Details.add(tb_SingerName);
        Form_Details.add(tb_SingerBirthDate);
        Form_Details.add(tb_TypeName);
        Form_Details.add(tb_inputForSingerType);
        Form_Details.add(btnSaveChanges);
        this.add(Form_Details);

        final TextField tb_NameAddSong = new TextField("tb_NameAddSong",
                new Model(""));
        final Button btnSaveChangesAddSong = new Button("btnSaveChangesAddSong");

        List<String> singerNameListAddSong = SongsOp.FetchAllSingerNames();
        singerNameListAddSong.add("Other");
        final DropDownChoice singerChoiceAddSong = new DropDownChoice(
                "tb_SingerAddSong", new Model<String>(""),
                singerNameListAddSong);

        final TextField tb_SingerNameAddSong = new TextField(
                "tb_SingerNameAddSong", new Model(""));
        final TextField tb_SingerBirthDateAddSong = new TextField(
                "tb_SingerBirthDateAddSong", new Model(""));

        List<String> typeNameListAddSong = SongsOp.FetchAllSongTypeNames();
        typeNameListAddSong.add("Other");
        final DropDownChoice songTypeChoiceAddSong = new DropDownChoice(
                "tb_TypeAddSong", new Model<String>(""), typeNameListAddSong);

        final TextField tb_TypeNameAddSong = new TextField(
                "tb_TypeNameAddSong", new Model(""));
        final TextField tb_inputForSingerTypeAddSong = new TextField(
                "tbForSingerTypeAddSong", new Model(""));

        Form Form_AddSong = new Form("Form_AddSong") {
            @Override
            public void onSubmit() {
                String choiceSingerSelected = (String) singerChoiceAddSong
                        .getModelObject();
                String choiceSongTypeSelected = (String) songTypeChoiceAddSong
                        .getModelObject();

                String _tb_Name = (String) tb_NameAddSong.getModelObject();

                String _tb_SingerName = (String) tb_SingerNameAddSong
                        .getModelObject();
                String _tb_SingerBirthDate = (String) tb_SingerBirthDateAddSong
                        .getModelObject();
                String _tb_inputForSingerType = (String) tb_inputForSingerTypeAddSong
                        .getModelObject();

                String _tb_TypeName = (String) tb_TypeNameAddSong
                        .getModelObject();

                if (choiceSingerSelected.equals("Other")) {
                    if (choiceSongTypeSelected.equals("Other")) {

                        SongTypeModel songType = new SongTypeModel();
                        songType.setName(_tb_TypeName);
                        SongsOp.AddSongType(songType);

                        SingerModel singer = new SingerModel();
                        singer.setNameSurname(_tb_SingerName);
                        singer.setBirthDate(_tb_SingerBirthDate);
                        singer.setType(_tb_inputForSingerType);
                        try {
                            SongsOp.AddSinger(singer);
                        } catch (ParseException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                        SongModel song = new SongModel();
                        song.setName(_tb_Name);
                        song.setTypeId(SongsOp
                                .getSongTypeIdByName(_tb_TypeName));
                        song.setSingerId(SongsOp
                                .getSingerIdByName(_tb_SingerName));

                        SongsOp.AddSong(song);

                    } else {

                        SingerModel singer = new SingerModel();
                        singer.setNameSurname(_tb_SingerName);
                        singer.setBirthDate(_tb_SingerBirthDate);
                        singer.setType(_tb_inputForSingerType);

                        try {
                            SongsOp.AddSinger(singer);
                        } catch (ParseException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                        SongModel song = new SongModel();
                        song.setName(_tb_Name);
                        song.setTypeId(SongsOp
                                .getSongTypeIdByName(choiceSongTypeSelected));
                        song.setSingerId(SongsOp
                                .getSingerIdByName(_tb_SingerName));

                        SongsOp.AddSong(song);
                    }
                } else {
                    if (choiceSongTypeSelected.equals("Other")) {

                        SongTypeModel songType = new SongTypeModel();
                        songType.setName(_tb_TypeName);
                        SongsOp.AddSongType(songType);

                        SongModel song = new SongModel();
                        song.setName(_tb_Name);
                        song.setTypeId(SongsOp
                                .getSongTypeIdByName(_tb_TypeName));
                        song.setSingerId(SongsOp
                                .getSingerIdByName(choiceSingerSelected));

                        SongsOp.AddSong(song);

                    } else {

                        SongModel song = new SongModel();
                        song.setName(_tb_Name);
                        song.setTypeId(SongsOp
                                .getSongTypeIdByName(choiceSongTypeSelected));
                        song.setSingerId(SongsOp
                                .getSingerIdByName(choiceSingerSelected));

                        SongsOp.AddSong(song);

                    }
                }
                setResponsePage(new AdminSongOperationsPage());
            }
        };

        Form_AddSong.add(tb_NameAddSong);
        Form_AddSong.add(singerChoiceAddSong);
        Form_AddSong.add(songTypeChoiceAddSong);
        Form_AddSong.add(tb_SingerNameAddSong);
        Form_AddSong.add(tb_SingerBirthDateAddSong);
        Form_AddSong.add(tb_TypeNameAddSong);
        Form_AddSong.add(tb_inputForSingerTypeAddSong);
        Form_AddSong.add(btnSaveChangesAddSong);
        this.add(Form_AddSong);

        Link HomePageLink = new Link("HomePageLink") {
            @Override
            public void onClick() {
                // TODO Auto-generated method stub
                setResponsePage(new AdminHomePage());
            }
        };

        this.add(HomePageLink);

        Link lnkAdminUserManagement = new Link("lnkAdminSongOperations") {
            @Override
            public void onClick() {
                // TODO Auto-generated method stub
                setResponsePage(new AdminSongOperationsPage());
            }
        };

        this.add(lnkAdminUserManagement);
    }
}
