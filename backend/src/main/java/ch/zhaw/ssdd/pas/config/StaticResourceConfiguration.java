package ch.zhaw.ssdd.pas.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StaticResourceConfiguration implements WebMvcConfigurer {

    @Value("${app.storage.path:./uploads}")
    private String storagePath;

    public static final String IMAGES_URL_PATH = "/images";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(IMAGES_URL_PATH + "/**")
                .addResourceLocations("file:" + storagePath + "/");
    }
}
