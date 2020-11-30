package info.stepanoff.trsis.samples.config;

import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TomcatConfig {

    @Value("${server.http.port}")
    private int httpPort;

    @Value("${server.port}")
    private int httpsPort;

    //Конфигурируем http порт для того, чтобы сервер слушал по двум портам - https (по умолчанию) и http
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return (ConfigurableEmbeddedServletContainer container) -> {
            if (container instanceof TomcatEmbeddedServletContainerFactory) {
                TomcatEmbeddedServletContainerFactory containerFactory
                        = (TomcatEmbeddedServletContainerFactory) container;

                Connector connector = new Connector(TomcatEmbeddedServletContainerFactory.DEFAULT_PROTOCOL);
                connector.setPort(httpPort);
                connector.setRedirectPort(httpsPort);//Иначе редирект будет на 443 Вместо 8443
                containerFactory.addAdditionalTomcatConnectors(connector);
            }
        };
    }

    //Для настройки редиректа логина всегда на https
    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
            @Override
            protected void postProcessContext(org.apache.catalina.Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/login");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };

        return tomcat;
    }

}
