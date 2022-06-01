package top.kaluna.pipelineMonitor.config.parsetxtConfig;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

/**
 * @author Yuery
 * @date 2022/5/22/0022 - 13:33
 */
public class NameValue {
    /**
     * The name of the id
     */
    @JacksonXmlProperty(isAttribute = true, localName = "id")
    private String id;

    /**
     * The value of the mapping
     */
    @JacksonXmlText
    private String value;

    public String getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    /**
     * Default constructor (needed for jaxb)
     */
    public NameValue() {

    }

    /**
     * @param id    The name of the id
     * @param value The value of the mapping
     */
    public NameValue(String id, String value) {
        this();
        this.id = id;
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("'%s' => '%s'", id, value);
    }

}
