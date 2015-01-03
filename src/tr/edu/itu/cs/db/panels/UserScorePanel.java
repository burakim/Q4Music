package tr.edu.itu.cs.db.panels;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;


public class UserScorePanel extends Panel {

    private Label tbScore;
    private Label tbType;

    public UserScorePanel(String id, int score, String Type) {
        super(id);
        // TODO Auto-generated constructor stub

        tbScore = new Label("tbScore", new Model(score));
        tbType = new Label("tbType", new Model(Type));

        this.add(tbScore);
        this.add(tbType);
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

}
