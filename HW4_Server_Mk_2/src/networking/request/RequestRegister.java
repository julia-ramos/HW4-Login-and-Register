package networking.request;

// Java Imports
import java.io.IOException;

// Other Imports
import core.GameServer;
import core.NetworkManager;
import model.Player;
import networking.response.ResponseRegister;
import networking.response.ResponseName;
import utility.DataReader;
import database.Database;
import utility.Log;

import static database.Database.authenticate;
import static database.Database.writeAccountInfo;

public class RequestRegister extends GameRequest {

    // Data
    private String userID;
    private String password;
    private short status;

    // Responses
    private ResponseRegister responseRegister;

    public RequestRegister() {
        Log.printf("Register request detected");
        responses.add(responseRegister = new ResponseRegister());
    }

    /**
     * Reads and stores the given userID and password using DataReader
     * @throws IOException
     */
    @Override
    public void parse() throws IOException {
        userID = DataReader.readString(dataInput).trim();
        password = DataReader.readString(dataInput).trim();
        Log.printf("User is trying to register with the account info: userID = " + userID + ", password = " + password);
    }

    /**
     * Authenticates the given userID and password using Database
     * @throws Exception
     */
    @Override
    public void doBusiness() throws Exception {
        boolean isRegistered = writeAccountInfo(userID, password);

        if(isRegistered) {
            Log.printf("The user has successfully been registered");
            responseRegister.setStatus((short) 0);

        } else {
            Log.printf("User has not been registered.");
            responseRegister.setStatus((short) 1);
        }
    }

}
