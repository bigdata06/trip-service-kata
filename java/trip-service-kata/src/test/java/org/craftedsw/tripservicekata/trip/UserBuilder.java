package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.user.User;

class UserBuilder {

    private Trip[] trips = new Trip[]{};
    private User[] friends = new User[]{};

    public static UserBuilder aUser() {
        return new UserBuilder();
    }

    public UserBuilder friendWith(User... friends) {
        this.friends = friends;
        return this;
    }

    public UserBuilder withTrips(Trip... trips) {
        this.trips = trips;
        return this;
    }

    public User build() {
        final User user = new User();
        addFriends(user);
        addTrips(user);

        return user;
    }

    private void addTrips(User user) {
        for (Trip trip : trips) {
            user.addTrip(trip);
        }
    }

    private void addFriends(User user) {
        for (User friend : friends) {
            user.addFriend(friend);
        }
    }
}
