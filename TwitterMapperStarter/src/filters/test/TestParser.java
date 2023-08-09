package filters.test;

import filters.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test the parser.
 */
public class TestParser {
    @Test
    public void testBasic() throws SyntaxError {
        Filter f = new Parser("trump").parse();
        assertTrue(f instanceof BasicFilter);
        assertTrue(((BasicFilter)f).getWord().equals("trump"));
    }

    @Test
    public void testAnd() throws SyntaxError {
        Filter f = new Parser("trump and red").parse();
        assertTrue(f instanceof AndFilter);
        assertTrue(((AndFilter)f).toString().equals("(trump and red)"));
    }

    @Test
    public void testOr() throws SyntaxError {
        Filter f = new Parser("trump or blue").parse();
        assertTrue(f instanceof OrFilter);
        assertTrue(((OrFilter)f).toString().equals("(trump or blue)"));
    }

    @Test
    public void testHairy() throws SyntaxError {
        Filter x = new Parser("trump and (big or blue) and red or green and not not purple").parse();
        assertTrue(x instanceof OrFilter);
        assertTrue(x.toString().equals("(((trump and (big or blue)) and red) or (green and not not purple))"));
    }
}
