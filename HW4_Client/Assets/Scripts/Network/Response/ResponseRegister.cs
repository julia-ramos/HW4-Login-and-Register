using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ResponseRegisterEventArgs : ExtendedEventArgs
{
    public short status { get; set; } // 0 = success
    public ResponseRegisterEventArgs()
    {
        event_id = Constants.SMSG_REGISTER;
    }
}

public class ResponseRegister : NetworkResponse
{
    private short status;

    public ResponseRegister()
    {
    }

    public override void parse()
    {
        status = DataReader.ReadShort(dataStream);
    }

    public override ExtendedEventArgs process()
    {
        ResponseRegisterEventArgs args = new ResponseRegisterEventArgs
        {
            status = status
        };

        return args;
    }
}