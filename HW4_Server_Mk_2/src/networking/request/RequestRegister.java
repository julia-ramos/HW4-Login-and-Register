package networking.request;

// Java Imports
import java.io.IOException;
import networking.response.ResponseRegister;
import utility.DataReader;

// Other Imports
import static database.Database.writeAccountInfo;

public class RequestRegister extends GameRequest {

    // Data
    private String userID;
    private String password;

    // Responses
    private ResponseRegister responseRegister;

    public RequestRegister() {
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
    }

    /**
     * Writes unique account information to the text file.
     * @throws Exception
     */
    @Override
    public void doBusiness() throws Exception {
        writeAccountInfo(userID, password);
        // TODO: do stuff after successful writing account information to the file
    }
}
