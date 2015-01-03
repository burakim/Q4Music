package tr.edu.itu.cs.db;

import java.util.Random;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.Model;

import tr.edu.itu.cs.db.models.QuizStatistic;


public class QuizResultPage extends DefaultPage {
    private Label score;
    private Label wrongAnswer;
    private Label correctAnswer;
    private Label history;
    private DbOperations resultOp;
    private QuizStatistic data;

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public QuizResultPage(QuizStatistic result, Boolean isChallange) {
        super();
        data = result;

        score = new Label("userscore", "You earned "
                + result.getUserScoreDifference()
                + " points. This earned points added your total score.");
        wrongAnswer = new Label("userwronganswer", "You gave "
                + result.getWrongAnswer() + " wrong anwers");
        correctAnswer = new Label("usercorrectanswer", "You gave "
                + result.getCorrectAnswer() + " correct answers");
        RepeatingView repatingview = new RepeatingView("usermistakes");
        System.out.println("Yanlis yapilan soru sayisi => "
                + result.getWrongQuestions().size());
        System.out.println("Dogru yapilan soru sayisi  => "
                + result.getTrueQuestions());

        for (int i = 0; i < result.getWrongQuestions().size(); i++) {
            String correctansw = result.getWrongQuestions().get(i).getChoices()[result
                    .getWrongQuestions().get(i).getCorrectanw()];
            Label tempMistake = new Label(repatingview.newChildId(), " - "
                    + result.getWrongQuestions().get(i).getQuestiontext()
                    + "\n " + "     Correct Answer is " + correctansw);
            repatingview.add(tempMistake);
            if (isChallange == true) {
                System.out.println("Opponent Id = " + result.getOwnerId());
            } else {
                System.out.println("Opponent not found");

            }
        }

        this.add(wrongAnswer);
        this.add(correctAnswer);
        this.add(repatingview);

        this.add(score);

        final TextField tb_email_area = new TextField("tb_Email_area",
                new Model(""));
        final TextField tb_email_to = new TextField("tb_Email_area_name",
                new Model(""));
        final Button sendButton = new Button("sendemailbttn");

        Form Form_Details = new Form("Form_Send_Email") {

            @Override
            protected void onSubmit() {
                String key = GenerateKey(20);
                if (data != null) {
                    resultOp = new DbOperations();

                    resultOp.AddChallengeRequest(data, key);

                }
                MailSend mailsend = new MailSend();
                System.out.println("Mail gonderme isini baslat. -> "
                        + (String) tb_email_area.getModelObject());
                mailsend.sendCode(UserSession.getInstance().getUserModel()
                        .GetFullName(), key,
                        (String) tb_email_area.getModelObject(),
                        (String) tb_email_to.getModelObject());

                setResponsePage(new HomePage(true));
            }

        };

        Form_Details.add(tb_email_area);
        Form_Details.add(tb_email_to);
        Form_Details.add(sendButton);
        this.add(Form_Details);
    }

    public String GenerateKey(int size) {
        String returnedval = "";
        for (int i = 0; i < size; i++) {
            Random ran = new Random();
            returnedval += ran.nextInt(10);
        }
        return returnedval;
    }

}
