package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.assertj.core.api.Assertions.assertThat;

public class TripServiceTest {

    private final TripService tripService = new TestableTripService();

    @Test
    public void should_throw_exception_when_no_logged_in_user() {

        // Arrange
        final User unusedUser = null;

        // Act
        catchException(tripService).getTripsByUser(unusedUser);

        // Assert
        assertThat(caughtException()).isInstanceOf(UserNotLoggedInException.class);
    }

    private class TestableTripService extends TripService {

        @Override
        protected User getLoggedUser() {
            return null;
        }
    }
}
