package tr.edu.itu.cs.db;

import java.io.Serializable;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import tr.edu.itu.cs.db.Admin.AdminHomePage;


public class Login extends WebPage implements Serializable {

    private TextField UserName;
    private TextField EMail;
    private PasswordTextField Password;
    private TextField FullName;
    private Button BtnSubmit;
    private Form RegisterForm;
    private DbOperations RegisterOp;
    private String Code;
    private TextField UserNameSignIn;
    private PasswordTextField PasswordSignIn;
    private Button BtnSubmitSignIn;
    private Form LoginForm;

    public Login() {
        Code = "";

        StartLoginPage();
    }

    public void StartLoginPage() {
        if (!(UserSession.getInstance().getUserModel() == null)) {
            if (UserSession.getInstance().getUserModel().GetUserType()
                    .equals("admin")) {
                setResponsePage(new AdminHomePage());
            } else {
                setResponsePage(new HomePage(false));
            }
        }

        UserName = new TextField("TxtUserNameSignUp", new Model(""));
        Password = new PasswordTextField("TxtPasswordSignUp", new Model(""));
        FullName = new TextField("TxtFullName", new Model(""));
        EMail = new TextField("TxtEmailSignUp", new Model(""));
        BtnSubmit = new Button("BtnSignUp", new Model(""));

        RegisterForm = new Form("FormSignUp") {
            @Override
            public void onSubmit() {
                String _UserName = (String) UserName.getModelObject();
                String _EMail = (String) EMail.getModelObject();
                String _Password = (String) Password.getModelObject();
                String _FullName = (String) FullName.getModelObject();

                UserModel register = new UserModel(_UserName, _FullName,
                        _EMail, "user", _Password, true, true, true);

                if (RegisterOp.AddUser(register)) {
                    UserSession.getInstance().setUserModel(register);
                    RegisterOp.CloseConnection();
                    if (Code != "" && Code != null) {
                        setResponsePage(new ChallengeRedirect(Code));
                    } else
                        setResponsePage(new HomePage(false));
                }
            }
        };

        RegisterForm.add(UserName);
        RegisterForm.add(EMail);
        RegisterForm.add(Password);
        RegisterForm.add(FullName);
        RegisterForm.add(BtnSubmit);
        this.add(RegisterForm);

        RegisterOp = new DbOperations();
        UserNameSignIn = new TextField("TxtUserNameSignIn", new Model(""));
        PasswordSignIn = new PasswordTextField("TxtPasswordSignIn", new Model(
                ""));
        BtnSubmitSignIn = new Button("BtnSignIn", new Model(""));

        LoginForm = new Form("FormSignIn") {
            @Override
            protected void onSubmit() {

                String _UserNameSignIn = (String) UserNameSignIn
                        .getModelObject();
                String _PasswordSignIn = (String) PasswordSignIn
                        .getModelObject();

                UserModel login = new UserModel(_UserNameSignIn,
                        _PasswordSignIn);
                UserModel loginedUser = RegisterOp.TryLogin(login);
                if (loginedUser != null) {
                    UserSession.getInstance().setUserModel(loginedUser);
                    RegisterOp.CloseConnection();
                    if (loginedUser.GetUserType().equals("admin"))
                        setResponsePage(new AdminHomePage());
                    else {
                        if (Code != "" && Code != null) {
                            setResponsePage(new ChallengeRedirect(Code));
                        } else
                            setResponsePage(new HomePage(false));
                    }
                }
                // setResponsePage(Login.class, pp);

            }
        };
        LoginForm.add(UserNameSignIn);
        LoginForm.add(PasswordSignIn);
        LoginForm.add(BtnSubmitSignIn);
        // LoginForm.add(wmc);
        this.add(LoginForm);
    }

    public Login(PageParameters parameters) {
        Code = "";
        if (!parameters.isEmpty()) {
            try {
                System.out.println(parameters.get("code"));
                Code = (parameters.get("code").toString());
            } catch (Exception ex) {
                System.out.println("Parameter get error => " + ex.toString());
            }
        }
        StartLoginPage();

    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

}
