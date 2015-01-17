package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.craftedsw.tripservicekata.trip.UserBuilder.aUser;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TripServiceTest {

    private static final Trip TO_BRAZIL = new Trip();
    private static final User GUEST = null;
    private static final User REGISTERED_USER = new User();
    private static final User UNUSED_USER = new User();

    private final TripDAO tripDAOMock = mock(TripDAO.class);
    private TripService realTripService;

    private User loggedInUser = REGISTERED_USER;

    @Before
    public void setUp() {
        realTripService = new TripService(tripDAOMock);
    }

    @Test
    public void should_throw_exception_when_no_logged_in_user() {

        // Arrange
        final User unusedUser = null;
        loggedInUser = GUEST;

        // Act
        catchException(realTripService).getTripsByUser(unusedUser, loggedInUser);

        // Assert
        assertThat(caughtException()).isInstanceOf(UserNotLoggedInException.class);
    }

    @Test
    public void should_return_no_trips_when_users_are_not_friends() {

        // Arrange
        final User friend = aUser()
                .friendWith(UNUSED_USER)
                .withTrips(TO_BRAZIL)
                .build();

        // Act
        final List<Trip> friendTrips = realTripService.getTripsByUser(friend, loggedInUser);

        // Assert
        assertThat(friendTrips).isEmpty();
    }

    @Test
    public void should_return_friend_trips_when_users_are_friends() {

        // Arrange
        final User friend = aUser()
                .friendWith(REGISTERED_USER, UNUSED_USER)
                .withTrips(TO_BRAZIL)
                .build();

        when(tripDAOMock.tripsOf(friend)).thenReturn(friend.trips());

        // Act
        final List<Trip> friendTrips = realTripService.getTripsByUser(friend, loggedInUser);

        // Assert
        assertThat(friendTrips)
                .hasSize(1)
                .contains(TO_BRAZIL);
    }

}
