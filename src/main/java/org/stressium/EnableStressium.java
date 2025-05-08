package org.stressium;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;
import org.stressium.controller.PerformanceController;

/**
 * Annotation to enable Stressium.
 *
 * @author Radu Sebastian LAZIN
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({ PerformanceRunner.class, PerformanceController.class })
public @interface EnableStressium {

	// empty

}
