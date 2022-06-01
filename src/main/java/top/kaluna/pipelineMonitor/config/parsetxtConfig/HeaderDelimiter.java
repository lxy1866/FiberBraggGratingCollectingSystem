package top.kaluna.pipelineMonitor.config.parsetxtConfig;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Yuery
 * @date 2022/5/22/0022 - 13:35
 */
/**
 * A header delimiter - used to identify the header where there are values in the header of the file that are to be
 * added to every record.
 */
public class HeaderDelimiter {
    /**
     * Raw delimiter string from the config file
     */
    @JacksonXmlText
    public String delimiter;

    /**
     * The compiled pattern, this is the one used at runtime
     */
    private Pattern compiledPattern;

    public boolean matches(String candidate) {
        return compiledPattern.matcher(candidate).matches();
    }

    /**
     * Compile the delimiter based on the searches
     *
     * @param searches the searches, processed in this order
     */
    void compile(List<NameValue> searches) {
        String valueText = SearchHelper.translate(delimiter, searches);
        compiledPattern = Pattern.compile(".*?" + valueText + ".*?");
    }
}
