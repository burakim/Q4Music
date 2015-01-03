package tr.edu.itu.cs.db.Admin;

import java.util.ArrayList;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import tr.edu.itu.cs.db.DbOperations;
import tr.edu.itu.cs.db.UserModel;


public class AdminUserManagement extends AdminDefaultPage {

    PageParameters pp = new PageParameters();
    private DbOperations UsersOp;
    private ArrayList<UserModel> list;
    private String selected = "user";

    public AdminUserManagement() {
        super();

        UsersOp = new DbOperations();
        list = UsersOp.FetchAllUser();

        // BEGIN OF THE TABLE
        final DataView dv = new DataView("usertablerow", new ListDataProvider(
                list)) {
            @Override
            protected void populateItem(final Item item) {
                final UserModel um = (UserModel) item.getModelObject();
                item.add(new Label("tdID", um.GetObjectId()));
                item.add(new Label("tdUsername", um.GetUserName()));
                item.add(new Label("tdEMail", um.GetEMailAddress()));
                item.add(new Label("tdFullName", um.GetFullName()));

                Link testLnk = new Link("tdEdit") {
                    @Override
                    public void onComponentTag(ComponentTag tag) {
                        // TODO Auto-generated method stub
                        super.onComponentTag(tag);
                        tag.put("objectid", um.GetObjectId());
                        tag.put("username", um.GetUserName());
                        tag.put("fullname", um.GetFullName());
                        tag.put("email", um.GetEMailAddress());
                        tag.put("usertype", um.GetUserType());
                        tag.put("isactive", um.GetIsActive());
                        tag.put("isapproved", um.GetIsAproved());
                    }

                    @Override
                    public void onClick() {
                        // // TODO Auto-generated method stub
                        // pp.add("id", um.GetObjectId());
                        // setResponsePage(AdminUserManagement.class, pp);
                        // // AdminUserManagement.class, pp
                    }

                };
                testLnk.add(new AttributeAppender("class", " MODELEDIT"));
                item.add(testLnk);

                Label lblUserType = new Label("tdUserType", um.GetUserType());
                if (um.GetUserType().equals("user")) {
                    if (um.GetIsActive() && um.GetIsAproved())
                        lblUserType.add(new AttributeAppender("class",
                                " label label-success"));
                    else
                        lblUserType.add(new AttributeAppender("class",
                                " label label-warning"));
                } else {
                    if (um.GetIsActive() && um.GetIsAproved())
                        lblUserType.add(new AttributeAppender("class",
                                " label label-danger"));
                    else
                        lblUserType.add(new AttributeAppender("class",
                                " label label-info"));
                }
                item.add(lblUserType);

                Link testDeleteLink = new Link("tdDelete") {
                    @Override
                    public void onClick() {
                        // TODO Auto-generated method stub
                        Boolean sonuc = UsersOp
                                .DeleteUserById(um.GetObjectId());
                        UsersOp.CloseConnection();
                        setResponsePage(new AdminUserManagement());
                    }
                };
                item.add(testDeleteLink);

            }
        };

        dv.setItemsPerPage(100);
        add(dv);

        Label lblCount = new Label("usercount", new Model("("
                + Integer.toString(list.size()) + ")"));
        this.add(lblCount);

        // END OF THE TABLE

        // BEGIN THE UPDATE MODAL
        final TextField tb_ObjectId = new TextField("tb_ObjectId",
                new Model(""));

        final TextField tb_Username = new TextField("tb_Username",
                new Model(""));
        final TextField tb_EMail = new TextField("tb_EMail", new Model(""));
        final TextField tb_Fullname = new TextField("tb_Fullname",
                new Model(""));
        final TextField tb_inputForUserType = new TextField("tbForUserType",
                new Model(""));
        final TextField tbForIsActive = new TextField("tbForIsActive",
                new Model(""));
        final TextField tbForIsApproved = new TextField("tbForIsApproved",
                new Model(""));

        final Button btnSaveChanges = new Button("btnSaveChanges");

        Form Form_Details = new Form("Form_Details") {
            @Override
            public void onSubmit() {
                String _tb_ObjectId = (String) tb_ObjectId.getValue();
                String _tb_Username = (String) tb_Username.getModelObject();
                String _tb_EMail = (String) tb_EMail.getModelObject();
                String _tb_Fullname = (String) tb_Fullname.getModelObject();
                String _tb_type = (String) tb_inputForUserType.getModelObject();
                String _tbForIsActive = (String) tbForIsActive.getModelObject();
                String _tbForIsApproved = (String) tbForIsApproved
                        .getModelObject();

                if (UsersOp.UpdateUsersWithAdminById(_tb_ObjectId,
                        _tb_Username, _tb_EMail, _tb_Fullname, _tb_type,
                        _tbForIsActive, _tbForIsApproved))
                    setResponsePage(new AdminUserManagement());
            }
        };

        Form_Details.add(btnSaveChanges);
        Form_Details.add(tb_ObjectId);
        Form_Details.add(tb_Username);
        Form_Details.add(tb_EMail);
        Form_Details.add(tb_Fullname);
        Form_Details.add(tb_inputForUserType);
        Form_Details.add(tbForIsActive);
        Form_Details.add(tbForIsApproved);
        this.add(Form_Details);
        // END THE UPDATE MODAL

        // BEGIN THE ADD USER MODAL
        final TextField tb_UsernameAddUser = new TextField(
                "tb_UsernameAddUser", new Model(""));
        final PasswordTextField tb_PasswordAddUser = new PasswordTextField(
                "tb_PasswordAddUser", new Model(""));
        final TextField tb_EMailAddUser = new TextField("tb_EMailAddUser",
                new Model(""));

        final TextField tb_FullnameAddUser = new TextField(
                "tb_FullnameAddUser", new Model(""));

        final TextField tbForGenderAddUser = new TextField(
                "tbForGenderAddUser", new Model(""));
        final TextField tbForUserTypeAddUser = new TextField(
                "tbForUserTypeAddUser", new Model(""));
        final TextField tbForIsActiveAddUser = new TextField(
                "tbForIsActiveAddUser", new Model(""));
        final TextField tbForIsApprovedAddUser = new TextField(
                "tbForIsApprovedAddUser", new Model(""));
        final Button btnSaveChangesAddUser = new Button("btnSaveChangesAddUser");

        final UserModel umodel = new UserModel();
        Form Form_AddUser = new Form("Form_AddUser") {
            @Override
            public void onSubmit() {
                umodel.SetUserName((String) tb_UsernameAddUser.getModelObject());
                umodel.SetPassword((String) tb_PasswordAddUser.getModelObject());
                umodel.SetEMailAddress((String) tb_EMailAddUser
                        .getModelObject());
                umodel.SetFullName((String) tb_FullnameAddUser.getModelObject());

                umodel.SetGender(Integer.parseInt((String) tbForGenderAddUser
                        .getModelObject()) == 0 ? false : true);
                umodel.SetUserType((String) tbForUserTypeAddUser
                        .getModelObject());
                umodel.SetIsActive(Integer
                        .parseInt((String) tbForIsActiveAddUser
                                .getModelObject()) == 0 ? false : true);
                umodel.SetIsApproved(Integer
                        .parseInt((String) tbForIsApprovedAddUser
                                .getModelObject()) == 0 ? false : true);

                UsersOp.AddUser(umodel);
                // setResponsePage(new AdminUserManagement());
            }
        };

        Form_AddUser.add(tb_UsernameAddUser);
        Form_AddUser.add(tb_PasswordAddUser);
        Form_AddUser.add(tb_EMailAddUser);
        Form_AddUser.add(tb_FullnameAddUser);
        Form_AddUser.add(tbForGenderAddUser);
        Form_AddUser.add(tbForUserTypeAddUser);
        Form_AddUser.add(tbForIsActiveAddUser);
        Form_AddUser.add(tbForIsApprovedAddUser);
        Form_AddUser.add(btnSaveChangesAddUser);
        this.add(Form_AddUser);
        // END THE ADD USER MODAL

        Link HomePageLink = new Link("HomePageLink") {
            @Override
            public void onClick() {
                // TODO Auto-generated method stub
                setResponsePage(new AdminHomePage());
            }
        };

        this.add(HomePageLink);

        Link lnkAdminUserManagement = new Link("lnkAdminUserManagement") {
            @Override
            public void onClick() {
                // TODO Auto-generated method stub
                setResponsePage(new AdminUserManagement());
            }
        };

        this.add(lnkAdminUserManagement);

    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

}
