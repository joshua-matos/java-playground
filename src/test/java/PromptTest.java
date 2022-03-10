import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PromptTest {

	@Test
	void testPromptPresent(){
		Prompt prompt = new Prompt();

		prompt.present();
	}

}