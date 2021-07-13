//package com.kosta.springbootproject;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.thymeleaf.spring5.SpringTemplateEngine;
//import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
//import org.thymeleaf.spring5.view.ThymeleafViewResolver;
//import org.thymeleaf.templatemode.TemplateMode;
//
//@Configuration
//public class ThymeleafConfiguration {
//	private static final String HTML_5 = "HTML5";
//
//	@Bean
//	public SpringTemplateEngine templateEngine() {
//		final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//		templateEngine.addTemplateResolver(templateResolver());
//		return templateEngine;
//	}
//
//	@Bean public ThymeleafViewResolver viewResolver(){ 
//		final ThymeleafViewResolver viewResolver = new ThymeleafViewResolver(); 
//		viewResolver.setTemplateEngine(templateEngine()); 
//		viewResolver.setViewNames(new String[]{"*.html,*.xhtml,*.txt"}); 
//		return viewResolver; 
//		}
//
//	@Bean
//    public SpringResourceTemplateResolver templateResolver() {
//        SpringResourceTemplateResolver springResourceTemplateResolver = new SpringResourceTemplateResolver();
//        springResourceTemplateResolver.setPrefix("classpath:templates");
//        springResourceTemplateResolver.setCharacterEncoding("UTF-8");
//        springResourceTemplateResolver.setSuffix(".html");
//        springResourceTemplateResolver.setTemplateMode(TemplateMode.HTML);
//        springResourceTemplateResolver.setCacheable(false);
//        
//        return springResourceTemplateResolver;
//    }
//}