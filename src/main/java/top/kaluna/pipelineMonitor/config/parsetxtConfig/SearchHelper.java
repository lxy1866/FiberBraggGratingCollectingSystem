package top.kaluna.pipelineMonitor.config.parsetxtConfig;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author Yuery
 * @date 2022/5/22/0022 - 13:35
 */
/**
 * A helper to deal with searches
 */
public class SearchHelper {
    /**
     * Apply the search translations to the candidate string
     *
     * @param candidate the candidate
     * @param searches
     * @return the fully translated text
     */
    static String translate(String candidate, List<NameValue> searches) {
        String valueText = candidate;
        if (null != searches) {
            for (NameValue search : searches) {
                String formattedId = String.format("{%s}", search.getId());
                valueText = StringUtils.replace(valueText, formattedId, search.getValue());
            }
        }
        return valueText;
    }
}
