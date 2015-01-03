package tr.edu.itu.cs.db;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;


public class ErrorPage extends WebPage {

    public ErrorPage() {
        Link lnk = new Link("errorRouting") {
            @Override
            public void onClick() {
                // TODO Auto-generated method stub
                setResponsePage(new HomePage(false));
            }
        };
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

}
