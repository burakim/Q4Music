package tr.edu.itu.cs.db;

import org.apache.wicket.markup.html.link.Link;


public class QuestionLink extends Link {
    public QuestionLink(String id) {
        super(id);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onClick() {
        // TODO Auto-generated method stub

        this.setResponsePage(new QuestionCategories(false));
    }
}
