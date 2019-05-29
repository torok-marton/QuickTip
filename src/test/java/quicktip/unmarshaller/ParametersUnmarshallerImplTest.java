package quicktip.unmarshaller;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import quicktip.bean.unmarshaller.Parameters;

@RunWith(JUnit4.class)
public class ParametersUnmarshallerImplTest {

    private static final String RESOURCES_PATH = "src/test/resources/";
    private static final String TEST_PARAMETERS_PATH = RESOURCES_PATH + "test_parameters.xml";
    private static final String NONEXISTENT_FILE_PATH = RESOURCES_PATH + "nonexistent_file";
    private static final String INVALID_CONTENT_PATH = RESOURCES_PATH + "test_parameters_invalid_content.xml";
    private static final String NO_ALGORITHM = RESOURCES_PATH + "test_parameters_no_algorithm.xml";
    private static final String NO_PIECES_OF_RANDOMNUMBERS = RESOURCES_PATH + "test_parameters_no_pieces_of_randomnumbers.xml";
    private static final String TEST_PARAMETERS_FIRST_ALGORITHM = RESOURCES_PATH + "test_parameters_first_algorithm.xml";
    private static final String TEST_PARAMETERS_SECOND_ALGORITHM = RESOURCES_PATH + "test_parameters_second_algorithm.xml";
    private static final String TEST_PARAMETERS_THIRD_ALGORITHM_NO_PIECES_OF_SHEETS = RESOURCES_PATH + "test_parameters_third_algorithm_no_pieces_of_sheets.xml";
    private static final String TEST_PARAMETERS_THIRD_ALGORITHM_NO_MAX_NUMBER = RESOURCES_PATH + "test_parameters_third_algorithm_no_max_number.xml";
    private static final String TEST_PARAMETERS_WRONG_ALGORITHM = RESOURCES_PATH + "test_parameters_wrong_algorithm.xml";
    private static final int ALGORITHM = 3;
    private static final int MAX_RANDOM_NUMBER = 90;
    private static final int PIECES_OF_RANDOM_NUMBERS = 5;
    private static final int PIECES_OF_SHEETS = 10;

    private ParametersUnmarshaller underTest;

    @Before
    public void setUp() {
        underTest = new ParametersUnmarshallerImpl();
    }

    @Test
    public void testUnmarshal() throws Exception {
        Parameters parameters = underTest.unmarshal(TEST_PARAMETERS_PATH);

        assertThat(parameters, is(not(nullValue())));
        assertThat(parameters.getAlgorithm(), is(not(nullValue())));
        assertThat(parameters.getAlgorithm(), is(ALGORITHM));
        assertThat(parameters.getMaxRandomNumber(), is(MAX_RANDOM_NUMBER));
        assertThat(parameters.getPiecesOfRandomNumbers(), is(PIECES_OF_RANDOM_NUMBERS));
        assertThat(parameters.getPiecesOfSheets(), is(PIECES_OF_SHEETS));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFileNotFound() throws Exception {
        underTest.unmarshal(NONEXISTENT_FILE_PATH);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidContent() throws Exception {
        underTest.unmarshal(INVALID_CONTENT_PATH);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNoAlgorithm() throws Exception {
        underTest.unmarshal(NO_ALGORITHM);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNoPiecesOfRandomNumbers() throws Exception {
        underTest.unmarshal(NO_PIECES_OF_RANDOMNUMBERS);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFirstAlgorithmMissesMaxRandomNumber() throws Exception {
        underTest.unmarshal(TEST_PARAMETERS_FIRST_ALGORITHM);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSecondAlgorithmMissesPiecesOfSheets() throws Exception {
        underTest.unmarshal(TEST_PARAMETERS_SECOND_ALGORITHM);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThirdAlgorithmMissesPiecesOfSheets() throws Exception {
        underTest.unmarshal(TEST_PARAMETERS_THIRD_ALGORITHM_NO_PIECES_OF_SHEETS);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThirdAlgorithmMissesMaxRandomNumber() throws Exception {
        underTest.unmarshal(TEST_PARAMETERS_THIRD_ALGORITHM_NO_MAX_NUMBER);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrongAlgorithm() throws Exception {
        underTest.unmarshal(TEST_PARAMETERS_WRONG_ALGORITHM);
    }

}