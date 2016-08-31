package challenge.services.impl;

import challenge.dto.User;
import challenge.services.UserService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;


@Service("userService")
public class UserServiceMock implements UserService {

    public static final String MOCK_USERS =
            "1,team turbo,user1,test,user1,testuser1@acme.com,0\n" +
            "2,team turbo,user2,test,user2,testuser2@acme.com,0\n" +
            "3,team turbo,user3,test,user3,testuser3@acme.com,0\n" +
            "4,team turbo,user4,test,user4,testuser4@acme.com,0\n" +
            "5,team turbo,user5,test,user5,testuser5@acme.com,0\n" +
            "6,team turbo,user6,test,user6,testuser6@acme.com,0\n" +
            "7,team turbo,user7,test,user7,testuser7@acme.com,0\n" +
            "8,team turbo,user8,test,user8,testuser8@acme.com,0\n" +
            "9,team turbo,user9,test,user9,testuser9@acme.com,0\n" +
            "10,team turbo,user10,test,user10,testuser10@acme.com,0\n" +
            "11,team turbo,user11,test,user11,testuser11@acme.com,0\n" +
            "12,team turbo,user12,test,user12,testuser12@acme.com,0\n" +
            "13,team turbo,user13,test,user13,testuser13@acme.com,0\n" +
            "14,team turbo,user14,test,user14,testuser14@acme.com,0\n" +
            "15,team turbo,user15,test,user15,testuser15@acme.com,0\n" +
            "16,team turbo,user16,test,user16,testuser16@acme.com,0";

    List<User> mockUsers = new ArrayList<>();
    public UserServiceMock() {
        String[] split = MOCK_USERS.split("\n");
        for (String line : split) {
            String[] fields = line.split(",");
            String userType = fields[6];
            boolean isAdmin = userType.equals("1");
            User user = new User(fields[0], fields[2], fields[3], fields[4], fields[5], isAdmin, fields[1]);
            mockUsers.add(user);
        }
    }

    @Override
    public List<User> getUsers(User requestingUser) {
        return mockUsers;
    }

}
