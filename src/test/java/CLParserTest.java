import org.aleks.CLParser;
import org.aleks.Function;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CLParserTest {
    CLParser parser;
    String defaultFunction = "update";
    String defaultArgument = "0";
    String defaultAdditionalArgument = "new updated value";

    @Test
    void parserAcceptsInput(){
        createParser();
    }

    @Test
    void parserParsesFunction(){
        createParser(new String[] {"add", "test"});
        assertEquals(parser.getFunction(), Function.ADD);
    }

    @Test
    void parserParsesArgument(){
        createParser();
        assertEquals(parser.getArgument(), defaultArgument);
    }

    @Test
    void parserParsesAdditionalArgument(){
        createParser();
        assertEquals(parser.getAdditionalArgument(), defaultAdditionalArgument);
    }

    @Test
    void parserValidatesInput(){
        assertThrows(IllegalArgumentException.class, () -> createParser(new String[] {"invalid", "test"}));
    }

    void createParser(String[] arguments){
        parser = new CLParser(arguments);
    }

    void createParser(){
        parser = new CLParser(new String[] {defaultFunction, defaultArgument, defaultAdditionalArgument});
    }
}
