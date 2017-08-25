package generator;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class FibonacciGeneratorTest {

    private FibonacciGenerator generator;

    @Test
    public void shouldGetNext() throws Exception {
        generator = new FibonacciGenerator();

        assertThat(generator.next().getValue()).isEqualTo(1);
        assertThat(generator.next().getValue()).isEqualTo(1);
        assertThat(generator.next().getValue()).isEqualTo(2);
        assertThat(generator.next().getValue()).isEqualTo(3);
        assertThat(generator.next().getValue()).isEqualTo(5);
        assertThat(generator.next().getValue()).isEqualTo(8);
        assertThat(generator.next().getValue()).isEqualTo(13);
        assertThat(generator.next().getValue()).isEqualTo(21);
    }

    @Test
    public void shouldGetFirstNNumber() throws Exception {
        generator = new FibonacciGenerator(3);

        assertThat(generator.hasNext()).isTrue();
        assertThat(generator.next().getValue()).isEqualTo(1);
        assertThat(generator.hasNext()).isTrue();
        assertThat(generator.next().getValue()).isEqualTo(1);
        assertThat(generator.hasNext()).isTrue();
        assertThat(generator.next().getValue()).isEqualTo(2);
        assertThat(generator.hasNext()).isFalse();
    }

    @Test(expected = NoSuchElementException.class)
    public void shoulExceptionBeyondLimit() throws Exception {
        generator = new FibonacciGenerator(1);

        assertThat(generator.hasNext()).isTrue();
        assertThat(generator.next().getValue()).isEqualTo(1);
        assertThat(generator.hasNext()).isFalse();
        generator.next();
    }
}