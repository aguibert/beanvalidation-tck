/**
 * Bean Validation TCK
 *
 * License: Apache License, Version 2.0
 * See the license.txt file in the root directory or <http://www.apache.org/licenses/LICENSE-2.0>.
 */
package org.hibernate.beanvalidation.tck.tests.bootstrap;

import java.util.EnumSet;
import javax.validation.BootstrapConfiguration;
import javax.validation.executable.ExecutableType;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.test.audit.annotations.SpecAssertion;
import org.jboss.test.audit.annotations.SpecVersion;
import org.testng.annotations.Test;

import org.hibernate.beanvalidation.tck.util.TestUtil;
import org.hibernate.beanvalidation.tck.util.shrinkwrap.WebArchiveBuilder;

import static org.hibernate.beanvalidation.tck.util.TestUtil.asSet;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * @author Gunnar Morling
 */
@SpecVersion(spec = "beanvalidation", version = "2.0.0")
public class BootstrapConfigurationTest extends Arquillian {

	@Deployment
	public static WebArchive createTestArchive() {
		return new WebArchiveBuilder()
				.withTestClass( BootstrapConfigurationTest.class )
				.withValidationXml( "validation-BootstrapConfigurationTest.xml" )
				.build();
	}

	@Test
	@SpecAssertion(section = "5.5.3", id = "f")
	public void testGetBootstrapConfiguration() {
		BootstrapConfiguration bootstrapConfiguration = TestUtil.getConfigurationUnderTest()
				.getBootstrapConfiguration();

		assertNotNull( bootstrapConfiguration );

		assertNotNull( bootstrapConfiguration.getConstraintMappingResourcePaths() );
		assertEquals(
				bootstrapConfiguration.getConstraintMappingResourcePaths(),
				asSet( "mapping1", "mapping2" )
		);

		assertEquals(
				bootstrapConfiguration.getConstraintValidatorFactoryClassName(),
				"com.acme.ConstraintValidatorFactory"
		);
		assertEquals(
				bootstrapConfiguration.getDefaultProviderClassName(),
				"com.acme.ValidationProvider"
		);
		assertEquals(
				bootstrapConfiguration.getMessageInterpolatorClassName(),
				"com.acme.MessageInterpolator"
		);
		assertEquals(
				bootstrapConfiguration.getParameterNameProviderClassName(),
				"com.acme.ParameterNameProvider"
		);

		assertNotNull( bootstrapConfiguration.getProperties() );
		assertEquals( bootstrapConfiguration.getProperties().size(), 2 );
		assertEquals( bootstrapConfiguration.getProperties().get( "com.acme.Foo" ), "Bar" );
		assertEquals( bootstrapConfiguration.getProperties().get( "com.acme.Baz" ), "Qux" );

		assertEquals(
				bootstrapConfiguration.getTraversableResolverClassName(),
				"com.acme.TraversableResolver"
		);

		assertNotNull( bootstrapConfiguration.getDefaultValidatedExecutableTypes() );
		assertEquals(
				bootstrapConfiguration.getDefaultValidatedExecutableTypes(),
				EnumSet.of( ExecutableType.CONSTRUCTORS, ExecutableType.NON_GETTER_METHODS )
		);
	}
}