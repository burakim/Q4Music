package tr.edu.itu.cs.db;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;


public class UsersProfile extends DefaultPage {

    TextField tbFullName;
    TextField tbEMail;
    TextField tbUserName;
    PasswordTextField tbOldPassword;
    PasswordTextField TbPassword;
    PasswordTextField TbPasswordConfirm;
    Form FormUserProfile;
    Form FormChangePassword;
    DbOperations UserOp;

    public UsersProfile() {

        UserOp = new DbOperations();
        UserModel um = UserOp.FetchAllUserByUser((UserSession.getInstance()
                .getUserModel().GetObjectId()));

        tbFullName = new TextField("tbFullName", new Model(um.GetFullName()));
        tbEMail = new TextField("tbEMail", new Model(um.GetEMailAddress()));
        tbUserName = new TextField("tbUserName", new Model(um.GetUserName()));
        tbOldPassword = new PasswordTextField("tbOldPassword", new Model(""));
        TbPassword = new PasswordTextField("TbPassword", new Model(""));
        TbPasswordConfirm = new PasswordTextField("TbPasswordConfirm",
                new Model(""));

        FormUserProfile = new Form("FormUserProfile") {
            @Override
            protected void onSubmit() {

                String _fullname = (String) tbFullName.getModelObject();
                String _email = (String) tbEMail.getModelObject();
                UserModel controlum = new UserModel();
                controlum.SetObjectId(UserSession.getInstance().getUserModel()
                        .GetObjectId());
                controlum.SetEMailAddress(_email);
                controlum.SetFullName(_fullname);
                if (UserOp.UpdateUsersWithUserById(controlum))
                    setResponsePage(new UsersProfile());
            }
        };

        FormUserProfile.add(tbFullName);
        FormUserProfile.add(tbEMail);
        this.add(FormUserProfile);

        FormChangePassword = new Form("FormChangePassword") {
            @Override
            protected void onSubmit() {
                String _oldpassword = (String) tbOldPassword.getModelObject();
                String _newpassord = (String) TbPassword.getModelObject()
                        .trim();
                String _newpassordconfirm = (String) TbPasswordConfirm
                        .getModelObject().trim();
                UserModel controlum = new UserModel();
                controlum.SetObjectId(UserSession.getInstance().getUserModel()
                        .GetObjectId());
                controlum.SetPassword(_oldpassword);
                // UserOp.TheUserCheckIfExist(controlum)
                if (UserOp.TheUserCheckIfExist(controlum)
                        && _newpassord.equals(_newpassordconfirm)) {

                    if ((_newpassord.equals("")))
                        controlum.SetPassword(UserSession.getInstance()
                                .getUserModel().GetPassword());
                    else
                        controlum.SetPassword(_newpassord);
                    if (UserOp.UpdateUserPasswordById(controlum)) {
                        setResponsePage(new UsersProfile());
                    }
                }
            }
        };

        FormChangePassword.add(tbUserName);
        FormChangePassword.add(tbOldPassword);
        FormChangePassword.add(TbPassword);
        FormChangePassword.add(TbPasswordConfirm);
        this.add(FormChangePassword);

        Link lnkHomePage = new Link("lnkHomePage") {
            @Override
            public void onClick() {
                // TODO Auto-generated method stub
                setResponsePage(new HomePage(false));
            }
        };

        this.add(lnkHomePage);

        Link lnkProfileSettings = new Link("lnkProfileSettings") {
            @Override
            public void onClick() {
                // TODO Auto-generated method stub
                setResponsePage(new UsersProfile());
            }
        };

        this.add(lnkProfileSettings);

    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

}
