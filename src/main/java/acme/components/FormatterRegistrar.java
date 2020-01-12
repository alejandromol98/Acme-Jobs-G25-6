
package acme.components;

import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

class FormatterRegistrar implements WebMvcConfigurer {

	@Override
	public void addFormatters(final FormatterRegistry registry) {
		PhoneFormatter phoneFormatter;

		phoneFormatter = new PhoneFormatter();
		registry.addFormatter(phoneFormatter);
	}

}
