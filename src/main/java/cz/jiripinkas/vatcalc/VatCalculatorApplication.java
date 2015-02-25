package cz.jiripinkas.vatcalc;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;

@SpringBootApplication
public class VatCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(VatCalculatorApplication.class, args);
	}

	/**
	 * turns on gzip compression
	 */
	@Bean
	public EmbeddedServletContainerCustomizer servletContainerCustomizer() {
		return new EmbeddedServletContainerCustomizer() {
			@Override
			public void customize(ConfigurableEmbeddedServletContainer servletContainer) {
				((TomcatEmbeddedServletContainerFactory) servletContainer).addConnectorCustomizers(new TomcatConnectorCustomizer() {
					@Override
					public void customize(Connector connector) {
						@SuppressWarnings("rawtypes")
						AbstractHttp11Protocol httpProtocol = (AbstractHttp11Protocol) connector.getProtocolHandler();
						httpProtocol.setCompression("on");
						httpProtocol.setCompressionMinSize(256);
						String mimeTypes = httpProtocol.getCompressableMimeTypes();
						String mimeTypesWithJson = mimeTypes + "," + MediaType.APPLICATION_JSON_VALUE;
						httpProtocol.setCompressableMimeTypes(mimeTypesWithJson);
					}
				});
			}
		};
	}
}
