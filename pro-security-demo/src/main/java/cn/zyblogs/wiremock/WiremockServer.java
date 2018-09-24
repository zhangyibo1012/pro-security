package cn.zyblogs.wiremock;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

/**
 * @Title: WiremockServer.java
 * @Package cn.zyblogs.wiremock
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class WiremockServer {

    public static void main(String[] args) throws IOException {
        configureFor(8082);
        removeAllMappings();

        mock("/order/1", "01");
        mock("/order/2", "02");
    }

    private static void mock(String url, String file) throws IOException {
        ClassPathResource resource = new ClassPathResource("mock/response/" + file + ".txt");
        String content = StringUtils.join(FileUtils.readLines(resource.getFile(), "UTF-8").toArray(), "\n");
        stubFor(get(urlPathEqualTo(url)).willReturn(aResponse().withBody(content).withStatus(200)));
    }


}
