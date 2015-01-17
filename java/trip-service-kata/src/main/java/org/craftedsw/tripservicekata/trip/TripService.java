package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;

import java.util.ArrayList;
import java.util.List;

public class TripService {

    public List<Trip> getTripsByUser(User user, User loggedInUser) throws UserNotLoggedInException {
        List<Trip> tripList = new ArrayList<Trip>();
        boolean isFriend = false;
        if (loggedInUser != null) {
            for (User friend : user.getFriends()) {
                if (friend.equals(loggedInUser)) {
                    isFriend = true;
                    break;
                }
            }
            if (isFriend) {
                tripList = tripsByUser(user);
            }
            return tripList;
        } else {
            throw new UserNotLoggedInException();
        }
    }

    protected List<Trip> tripsByUser(User user) {
        return TripDAO.findTripsByUser(user);
    }

}
