package networking.response;

// Other Imports

import metadata.Constants;

/**
 * The ResponseLogin class contains information about the authentication
 * process.
 */
public class ResponseRegister extends GameResponse {

    public ResponseRegister() {
        responseCode = Constants.SMSG_REGISTER;
    }

    // TODO: Implement this function
    @Override
    public byte[] constructResponseInBytes() {
        return null;
    }

}