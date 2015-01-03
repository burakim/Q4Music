package tr.edu.itu.cs.db;

import org.apache.wicket.markup.html.WebPage;


public class Content extends WebPage {

    public Content() {
        add(new MasterPageContentHeaderPanel("panel1"));
        add(new MasterPageContentBodyPanel("panel2"));

    }

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

}
