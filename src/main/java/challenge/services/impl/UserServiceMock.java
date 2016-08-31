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
    List<User> mockUsers = new ArrayList<>();
    public UserServiceMock() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("mockUsers.csv");
        if (resource == null) {
            return;
        }
        File file = new File(resource.getFile());
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String userType = fields[6];
                boolean isAdmin = userType.equals("1");
                User user = new User(fields[0], fields[2], fields[3], fields[4], fields[5], isAdmin, fields[1]);
                mockUsers.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getUsers(User requestingUser) {
        return mockUsers;
    }

}
