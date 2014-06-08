package simpleblog;

import io.github.jhipster.loaded.JHipsterReloaderAutoConfiguration;
import io.github.jhipster.loaded.reloader.SpringReloader;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.AnnotatedTypeMetadata;

@Configuration
@Conditional(HotReloadConfiguration.HotReloadCondition.class)
public class HotReloadConfiguration {

	public static class HotReloadCondition implements Condition {
		@Override
		public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
			return context.getEnvironment().getProperty("hotReload.enabled").contains("true");
		}
	}

	@Bean
	public JHipsterReloaderAutoConfiguration reloaderAutoConfiguration() {
		return new JHipsterReloaderAutoConfiguration();
	}

	@Bean
	public SpringReloader springReloader() {
		return new SpringReloader();
	}
}
