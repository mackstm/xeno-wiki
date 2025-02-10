package es.ies.puerto;

import es.ies.puerto.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class XenoWikiApplicationTests extends TestUtilities {

	@Mock
	private SpringApplicationBuilder springApplicationBuilder;
	@Test
	void contextLoads() {
		ServletInitializer servletInitializer = new ServletInitializer();
		when(springApplicationBuilder.sources(XenoWikiApplication.class)).thenReturn(springApplicationBuilder);

		SpringApplicationBuilder result = servletInitializer.configure(springApplicationBuilder);

		verify(springApplicationBuilder).sources(XenoWikiApplication.class);
		Assertions.assertEquals(springApplicationBuilder,result, MESSAGE_ERROR);
	}

	@Test
	void mainTest(){
		XenoWikiApplication.main(new String[]{});
		Assertions.assertNotNull(XenoWikiApplication.class);
	}

}