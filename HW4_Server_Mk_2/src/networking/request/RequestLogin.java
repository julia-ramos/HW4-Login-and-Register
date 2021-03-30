package networking.request;

// Java Imports
import java.io.IOException;

// Other Imports
import core.GameServer;
import core.NetworkManager;
import model.Player;
import networking.response.ResponseLogin;
import networking.response.ResponseName;
import utility.DataReader;
import database.Database;
import utility.Log;

import static database.Database.authenticate;
import static database.Database.writeAccountInfo;

public class RequestLogin extends GameRequest {

    // Data
    private String userID;
    private String password;
    private short status;

    // Responses
    private ResponseLogin responseLogin;

    public RequestLogin() {
        Log.printf("Login request detected");
        responses.add(responseLogin = new ResponseLogin());
    }

    /**
     * Reads and stores the given userID and password using DataReader
     * @throws IOException
     */
    @Override
    public void parse() throws IOException {
        userID = DataReader.readString(dataInput).trim();
        password = DataReader.readString(dataInput).trim();
        Log.printf("User is trying to log in with the account info: userID = " + userID + ", password = " + password);
    }

    /**
     * Authenticates the given userID and password using Database
     * @throws Exception
     */
    @Override
    public void doBusiness() throws Exception {
        boolean isAuthenticated = authenticate(userID, password);

        if(isAuthenticated) {
            Log.printf("The user has successfully been authenticated. Login successful");
            responseLogin.setStatus((short) 0);

        } else {
            Log.printf("User has not been authenticated. Login failed.");
            responseLogin.setStatus((short) 1);
        }
    }

}
