package com.jby.thrift7;

import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;
import thrift.generated.PersonService;

/**
 *
 */
public class ThriftServer {
    public static void main(String[] args) throws TTransportException {
        TNonblockingServerSocket socket=new TNonblockingServerSocket(8899);
        THsHaServer.Args arg=new THsHaServer.Args(socket).minWorkerThreads(2).maxWorkerThreads(4);
        PersonService.Processor<PersonServiceImpl> processor = new PersonService.Processor<>(new PersonServiceImpl());
        arg.protocolFactory(new TCompactProtocol.Factory());//压缩的二进制 传输格式
        arg.transportFactory(new TFramedTransport.Factory());// 以frame 为单位，非阻塞
        arg.processorFactory(new TProcessorFactory(processor));
        THsHaServer tHsHaServer = new THsHaServer(arg);
        System.out.println("Thrift Server Started!!");
        tHsHaServer.serve();//线程池，半同步，半异步， 依赖TFramedTransport
    }
}
