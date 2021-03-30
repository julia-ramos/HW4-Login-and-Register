using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ResponseLoginEventArgs : ExtendedEventArgs
{
    public short status { get; set; } // 0 = success
    public ResponseLoginEventArgs()
    {
        event_id = Constants.SMSG_LOGIN;
    }
}

public class ResponseLogin : NetworkResponse
{
    private short status;

    public ResponseLogin()
    {
    }

    public override void parse()
    {
        status = DataReader.ReadShort(dataStream);
    }

    public override ExtendedEventArgs process()
    {
        ResponseLoginEventArgs args = new ResponseLoginEventArgs
        {
            status = status
        };

        return args;
    }
}