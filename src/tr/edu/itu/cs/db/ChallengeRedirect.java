package tr.edu.itu.cs.db;

import org.apache.wicket.RestartResponseException;


public class ChallengeRedirect extends DefaultPage {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private DbOperations ChallengeOp;

    public ChallengeRedirect(String code) {
        super();
        ChallengeOp = new DbOperations();
        ChallengeModel gmodel = ChallengeOp.GetChallenge(code);
        throw new RestartResponseException(new QuestionView(gmodel));
        // setResponsePage(new QuestionView(gmodel));
    }
}
