package tr.edu.itu.cs.db;

import java.io.Serializable;

import org.apache.wicket.markup.html.WebPage;


public class DefaultPage extends WebPage implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public DefaultPage() {

        add(new MasterPageContentHeaderPanel("panel1"));
        add(new MasterPageContentBodyPanel("panel2"));

    }

}
