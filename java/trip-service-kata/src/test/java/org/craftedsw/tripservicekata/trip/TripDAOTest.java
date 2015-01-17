package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.CollaboratorCallException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.assertj.core.api.Assertions.assertThat;

public class TripDAOTest {

    private final TripDAO tripDAO = new TripDAO();

    @Test
    public void should_throw_exception_when_finding_user_trips() {

        // Arrange
        final User unusedUser = new User();

        // Act
        catchException(tripDAO).tripsOf(unusedUser);

        // Assert
        assertThat(caughtException()).isInstanceOf(CollaboratorCallException.class);
    }
}
