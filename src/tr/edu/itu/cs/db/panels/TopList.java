package tr.edu.itu.cs.db.panels;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;


public class TopList extends Panel {

    private Label tbOrder;
    private Label tbName;
    private Label tbPoint;

    public TopList(String id, String name, int score, int order) {
        super(id);
        // TODO Auto-generated constructor stub

        tbOrder = new Label("tbOrder", new Model(order));
        tbName = new Label("tbName", new Model(name));
        tbPoint = new Label("tbPoint", new Model(score));

        this.add(tbOrder);
        this.add(tbName);
        this.add(tbPoint);
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

}
