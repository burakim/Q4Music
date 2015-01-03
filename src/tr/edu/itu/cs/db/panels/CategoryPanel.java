package tr.edu.itu.cs.db.panels;

import java.util.ArrayList;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.resource.ContextRelativeResource;

import tr.edu.itu.cs.db.DbOperations;
import tr.edu.itu.cs.db.Question;
import tr.edu.itu.cs.db.QuestionCategories;
import tr.edu.itu.cs.db.QuestionView;


public class CategoryPanel extends Panel {
    private ArrayList<Question> data;

    public CategoryPanel(String id) {
        super(id);
        data = new ArrayList<Question>();

    }

    public void AddCategoryName(final String name) {

        final Link l1 = (Link) new Link("test1") {

            @Override
            public void onClick() {
                // TODO Auto-generated method stub
                DbOperations dboperations = new DbOperations();
                int[] arg1 = dboperations.FetchQuestionIdsByCategories(name, 5);
                int categoryId = dboperations.getCategoryId(name);
                ArrayList<Question> databank = dboperations
                        .FetchLimitedQuestions(arg1, name);

                if (databank != null) {
                    if (databank.size() != 0) {
                        setResponsePage(new QuestionView(databank, categoryId));
                    } else {

                    }
                } else {
                    setResponsePage(new QuestionCategories(true));
                    System.out.println("Questions set empty!");
                }
            }

        }.add(new Label("test2", name));

        this.add(l1);

    }

    public void AddCategoryPicture(String imagepath) {
        this.add(new Image("categoryicon", new ContextRelativeResource(
                "nopic.png")));
    }

}
