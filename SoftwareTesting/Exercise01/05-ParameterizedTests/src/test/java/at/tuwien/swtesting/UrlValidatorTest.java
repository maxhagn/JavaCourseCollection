/**
 * Hagn Maximilian
 * 11808237
 * Exercise 01
 **/

package at.tuwien.swtesting;

import org.apache.commons.validator.routines.UrlValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UrlValidatorTest {

    private static final char TSV_FILE_DELIMITER = '\t';
    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @ParameterizedTest(name = "[{index}] Checks if url isValid returns {10}. scheme: {0}, subdomain: {1}, domain: {2}, topLevelDomain: {3}, portNumber: {4}, path: {5}, querySeperator: {6}, queryString: {7}, fragment: {8}")
    @DisplayName(value = "isValid on given url should return true if the url is valid or false if the url is not valid.")
    @CsvFileSource(resources = "/testdata.tsv", delimiter = TSV_FILE_DELIMITER, numLinesToSkip = 1)
    void apacheCommon_isValid_returnsValid(String scheme,
                                           String subdomain,
                                           String domain,
                                           String ip,
                                           String topLevelDomain,
                                           String portNumber,
                                           String path,
                                           String querySeperator,
                                           String queryString,
                                           String fragment,
                                           String expectedOutput) {
        String url = (scheme != null ? scheme : "") +
                (subdomain != null ? subdomain : "") +
                (domain != null ? domain : "") +
                (ip != null ? ip : "") +
                (topLevelDomain != null ? topLevelDomain : "") +
                (portNumber != null ? portNumber : "") +
                (path != null ? path : "") +
                (querySeperator != null ? querySeperator : "") +
                (queryString != null ? queryString : "") +
                (fragment != null ? fragment : "");

        logger.info(url);
        assertEquals(Boolean.valueOf(expectedOutput), UrlValidator.getInstance().isValid(url), "The specified URL should be " + expectedOutput + ".");
    }
}
