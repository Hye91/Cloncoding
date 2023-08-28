package hello.core;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CoreApplicationTests {

	//이 테스트는 스프링이 실행될때 자동으로 컨테이너를 띄워서 테스트를 통합해서 실행해준다.
	//그래서 Autowired 해서 실행할수 있게된다.

	@Test
	void contextLoads() {
	}

}
