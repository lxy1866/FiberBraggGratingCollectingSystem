//package top.kaluna.pipelineMonitor.txtConfig;
//
//
//import org.junit.jupiter.api.Assertions;
//import top.kaluna.pipelineMonitor.config.parsetxtConfig.Config;
//import top.kaluna.pipelineMonitor.config.parsetxtConfig.ConfigLoader;
//import top.kaluna.pipelineMonitor.util.UtahParserTxtUtil;
//import org.junit.jupiter.api.Test;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.Reader;
//import java.net.URL;
//import java.util.*;
//
//
//
///**
// * @author Yuery
// * @date 2022/5/22/0022 - 13:59
// */
//
///**
// * A test to load up a file and confirm it parses ok
// */
//public class ExampleTest {
//    /**
//     * In this test we load up the sample config and then process the sample input file, and then confirm the
//     * data records are as expected.
//     *
//     * @throws IOException
//     */
//    @Test
//    public void testExampleF10IPBgpSurvey() throws IOException {
//        String configResource = "txtExample/f10_ip_bgp_summary_example.xml";
//        String fileResource = "txtExample/f10_ip_bgp_summary_example.txt";
//
//        // these are the files we expect from the config
//        List<Map<String, String>> expectedResults = new ArrayList<Map<String, String>>() {{
//
//            add(new TreeMap<String, String>() {{
//                put("localAS", "65551");
//                put("remoteAS", "65551");
//                put("remoteIp", "10.10.10.10");
//                put("routerId", "192.0.2.1");
//                put("status", "5");
//                put("uptime", "10:37:12");
//            }});
//            add(new TreeMap<String, String>() {{
//                put("localAS", "65551");
//                put("remoteAS", "65552");
//                put("remoteIp", "10.10.100.1");
//                put("routerId", "192.0.2.1");
//                put("status", "0");
//                put("uptime", "10:38:27");
//            }});
//            add(new TreeMap<String, String>() {{
//                put("localAS", "65551");
//                put("remoteAS", "65553");
//                put("remoteIp", "10.100.10.9");
//                put("routerId", "192.0.2.1");
//                put("status", "1");
//                put("uptime", "07:55:38");
//            }});
//        }};
//        testFileProcessing(configResource, fileResource, expectedResults);
//    }
//
//
//    /**
//     * Test file processing
//     *
//     * @param configResource  the resource name for a config
//     * @param fileResource    the resource name for a file that is expected to match the config
//     * @param expectedResults the json results expected from the processing
//     * @throws IOException
//     */
//    private void testFileProcessing(String configResource, String fileResource, List<Map<String, String>>
//            expectedResults) throws IOException {
//        // load the config
//        URL configURL = Thread.currentThread().getContextClassLoader().getResource(configResource);
//        Config config = new ConfigLoader().loadConfig(configURL);
//
//        // load a real file
//        List<Map<String, String>> observedValues = new ArrayList<>();
//        try (Reader in = new InputStreamReader(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResourceAsStream
//                (fileResource)))) {
//            UtahParserTxtUtil parser = UtahParserTxtUtil.parse(config, in);
//            while (true) {
//                Map<String, String> record = parser.next();
//                if (null == record) {
//                    break;
//                } else {
//                    observedValues.add(record);
//                }
//            }
//        }
//        Assertions.assertEquals(expectedResults, observedValues);
//    }
//    @Test
//    public void testExample() throws IOException {
//        String configResource = "txtExample/f10_ip_bgp_summary_example.xml";
//        String fileResource = "txtExample/f10_ip_bgp_summary_example.txt";
//
//        // these are the files we expect from the config
//        List<Map<String, String>> expectedResults = new ArrayList<Map<String, String>>() {{
//
//            add(new TreeMap<String, String>() {{
//                put("localAS", "65551");
//                put("remoteAS", "65551");
//                put("remoteIp", "10.10.10.10");
//                put("routerId", "192.0.2.1");
//                put("status", "5");
//                put("uptime", "10:37:12");
//            }});
//            add(new TreeMap<String, String>() {{
//                put("localAS", "65551");
//                put("remoteAS", "65552");
//                put("remoteIp", "10.10.100.1");
//                put("routerId", "192.0.2.1");
//                put("status", "0");
//                put("uptime", "10:38:27");
//            }});
//            add(new TreeMap<String, String>() {{
//                put("localAS", "65551");
//                put("remoteAS", "65553");
//                put("remoteIp", "10.100.10.9");
//                put("routerId", "192.0.2.1");
//                put("status", "1");
//                put("uptime", "07:55:38");
//            }});
//        }};
//        testFileProcessing(configResource, fileResource, expectedResults);
//    }
//}
