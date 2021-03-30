using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class RequestRegister : NetworkRequest
{
    public RequestRegister()
    {
        request_id = Constants.CMSG_REGISTER;
    }

    public void send(string userID, string password)
    {
        packet = new GamePacket(request_id);
        packet.addString(userID);
        packet.addString(password);
    }
}