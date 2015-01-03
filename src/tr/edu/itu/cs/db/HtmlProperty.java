package tr.edu.itu.cs.db;

import java.io.Serializable;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;


/*
 * Author: Halim Burak Yesilyurt
 * Copyright burakim 2014
 */
public class HtmlProperty implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private AttributeModifier attributeModifier;

    public HtmlProperty(String key, final String data) {
        IModel<String> propertyModel = new AbstractReadOnlyModel<String>() {
            public String getObject() {
                return data; // Dummy example
            }
        };

        setAttributeModifier(new AttributeModifier(key, propertyModel));

    }

    public AttributeModifier getAttributeModifier() {
        return attributeModifier;
    }

    public void setAttributeModifier(AttributeModifier attributeModifier) {
        this.attributeModifier = attributeModifier;
    };

}
