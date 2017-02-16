package org.jasig.cas.thrift.server;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.jasig.cas.thrift.server.UserServiceThrift.Processor;
import org.jasig.cas.thrift.server.custom.UsersServiceImplThrift;

/**
 * @author dongtian
 * @date   2015年5月28日 上午9:18:09
 */
public class BootstrapThriftServer {
	
	private static  Logger logger = Logger.getLogger(BootstrapThriftServer.class);
	
	private UsersServiceImplThrift usersServiceImplThrift;
	private int port;

	public BootstrapThriftServer(UsersServiceImplThrift usersServiceImplThrift,
			int port) {
		super();
		this.usersServiceImplThrift = usersServiceImplThrift;
		this.port = port;
	}


	private TThreadedSelectorServer tThreadedSelectorServer ;
	public void startServe() {
		
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
		
		fixedThreadPool.submit(new Runnable() {
			@Override
			public void run() {
					
					logger.info("正在启动thrift 服务......");
				UserServiceThrift.Processor<UserServiceThrift.Iface> processor = new Processor<UserServiceThrift.Iface>(usersServiceImplThrift); 
				TNonblockingServerTransport nonblockingServerTransport;
				try {
					nonblockingServerTransport = new TNonblockingServerSocket(port);
					TThreadedSelectorServer.Args args3 = new TThreadedSelectorServer.Args(nonblockingServerTransport);
					args3.processor(processor);
					tThreadedSelectorServer = new TThreadedSelectorServer(args3);
						logger.info("成功启动thrift服务");
					tThreadedSelectorServer.serve();
				} catch (TTransportException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
	}

	
	public void stopServe () {
		
		if(tThreadedSelectorServer!= null) {
			tThreadedSelectorServer.stop();
		}
	}
}
